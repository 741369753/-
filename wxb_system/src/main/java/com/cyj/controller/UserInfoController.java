package com.cyj.controller;

import com.cyj.entity.*;
import com.cyj.service.ModuleService;
import com.cyj.service.RoleService;
import com.cyj.service.UserExpInfoService;
import com.cyj.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Controller
@RequestMapping("/userinfo")
@SessionAttributes("userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserExpInfoService userExpInfoService;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/login")
    public String login(UserInfo userInfo, String remeber, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserInfo queryUserInfo = userInfoService.login(userInfo.getAccount(), userInfo.getPassword());
        if (queryUserInfo == null) {
            model.addAttribute("msg", "用户名、密码错误");
            return "/index";
        }
        if (remeber != null) {
            Cookie cookie = new Cookie("userpw", userInfo.getAccount() + "|" + userInfo.getPassword());
            cookie.setPath("/");
            cookie.setMaxAge(14 * 24 * 60 * 60);
            response.addCookie(cookie);
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("userpw".equals(cookie.getName())) {
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        model.addAttribute("userinfo", queryUserInfo);
        return "redirect:/main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus) {
        session.removeAttribute("userinfo");

        sessionStatus.setComplete();

        return "/index";
    }

    @RequestMapping("userCookie")
    @ResponseBody
    public UserInfo userCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("userpw".equals(cookie.getName())) {
                UserInfo userInfo = new UserInfo();
                String[] strs = cookie.getValue().split("\\|");
                userInfo.setAccount(strs[0]);
                userInfo.setPassword(strs[1]);
                return userInfo;
            }
        }
        return null;
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public ResultData getMenu(@SessionAttribute(value = "userinfo", required = false) UserInfo userInfo) {
        if (userInfo == null) {
            return ResultData.createFailResult(10001, "未登录");
        }
        List<Module> modules = moduleService.queryMenu(userInfo.getRole().getRoleCode());
        return ResultData.createSuccessResult(modules);
    }

    @RequestMapping("getUserInfo")
    @ResponseBody
    public ResultData getUserInfo(@SessionAttribute("userinfo") UserInfo userInfo) {
        ResultData resultData = null;
        UserExpInfo userExpInfo = userExpInfoService.queryById(userInfo.getUserId());
        if (userInfo != null) {
            resultData = ResultData.createSuccessResult(userExpInfo);
        } else {
            resultData = ResultData.createFailResult(10005, "用户不存在或未登录");
        }
        return resultData;
    }

    @RequestMapping("updateLoginUserExpInfo")
    @ResponseBody
    public ResultData updateUserExpInfo(@RequestBody UserExpInfo userExpInfo, @SessionAttribute("userinfo") UserInfo userInfo) {
        ResultData resultData = null;
        try {
            if (userInfo == null) {
                resultData = ResultData.createFailResult(10001, "用户未登录");
            }
            userExpInfo.setUserId(userInfo.getUserId());
            int count = userExpInfoService.update(userExpInfo);
            if (count > 0) {
                resultData = ResultData.createSuccessResult(count);
            } else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }
        return resultData;
    }

    @RequestMapping("safe")
    @ResponseBody
    public ResultData updateUserInfo(@RequestParam("oldPassword") String oldPassword, UserInfo userInfo, @SessionAttribute("userinfo") UserInfo userInfoSession, Model model) {
        ResultData resultData = null;
        try {
            if (userInfo == null) {
                resultData = ResultData.createFailResult(10001, "用户未登录");
                return resultData;
            }
            if (!userInfoSession.getPassword().equals(oldPassword)) {
                resultData = ResultData.createFailResult(10008, "密码不正确");
                return resultData;
            }
            userInfo.setUserId(userInfoSession.getUserId());
            int count = userInfoService.updateById(userInfo);
            if (count > 0) {
                resultData = ResultData.createSuccessResult(count);
                model.addAttribute("userinfo", userInfo);
            } else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }
        return resultData;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String, Object> getAll(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNum, @RequestParam(value = "limit", defaultValue = "10", required = false) Integer pageSize) {
        return userInfoService.queryAll(pageNum, pageSize);
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public ResultData updateUserInfo(@RequestBody UserInfo userInfo) {
        ResultData resultData = null;
        try {
         if(userInfoService.updateUserAndExpAndFun(userInfo)>0){
             resultData = ResultData.createSuccessResult(null);
         }else {
             resultData = ResultData.createFailResult(10006, "受影响行数为0");
         }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }
        return resultData;
    }

    @RequestMapping("updateUsserEnabled")
    @ResponseBody
    public ResultData updateUserEnabled(UserInfo userInfo) {
        ResultData resultData = null;
        try {
            userInfoService.updateUserAndExpAndFun(userInfo);
            resultData = ResultData.createSuccessResult(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }
        return resultData;
    }


    @RequestMapping("insertUser")
    @ResponseBody
    public ResultData insertUser(@RequestBody UserInfo userInfo) {
        return userInfoService.insertUser(userInfo);
    }

    @RequestMapping("deletUser")
    @ResponseBody
    public ResultData deletUser(UserInfo userInfo) {
        ResultData resultData = null;
        try {
            int count = userInfoService.deleteUser(userInfo.getUserId());
            if (count > 0) {
                resultData = ResultData.createSuccessResult(null);
            } else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
            }
        } catch (Exception e) {
            resultData = ResultData.createFailResult(10007, "出现异常");
        }
        return resultData;
    }

}
