package com.cyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String loginPage(){
        return "/index";
    }
    @RequestMapping("/main")
    public String mainPage(){
        return "main";
    }

    @RequestMapping("/userinfo/userinfopage")
    public String userInfoPage(){
        return "user/userInfo";
    }

    @RequestMapping("/userinfo/safepage")
    public String safeSettingsPage(){
        return "/user/safesettings";
    }

    @RequestMapping("userinfo/usermanagerpage")
    public String userManagerPage(){
        return "/property/userManager";
    }

    @RequestMapping("userinfo/usereditpage")
    public String userEditPage(){
        return "/property/userEdit";
    }

    @RequestMapping("/goods/goodslist")
    public String goodsListPage(){
        return "/business/goodsList";
    }

    @RequestMapping("/goods/goodsDeatil")
    public String goodsDetailPage(){
        return "/business/goodsDeatil";
    }

    @RequestMapping("/goodsType/goodsType")
    public String goodsTypePage(){
        return "/business/goodsType";
    }

    @RequestMapping("/goodsType/goodsTypeEdit")
    public String goodsTypeEdit(){
        return "/business/goodsTypeEdit";
    }
}
