package com.cyj.controller;

import com.cyj.entity.Customer;
import com.cyj.entity.ResultData;
import com.cyj.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * author:aizhishang
 * time:2020/8/28
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customerManager")
    public String customerManagerPage(){
        return "business/customerManager";
    }

    @RequestMapping("/getcustomer")
    @ResponseBody
    public Map<String, Object> getCustomer(@RequestParam(value = "page",defaultValue = "1",required = false)Integer pageNum, @RequestParam(value = "limit",defaultValue = "10",required = false)Integer pageSize){
        return customerService.queryAll(pageNum,pageSize);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultData delete(@RequestParam("id")String  id, @RequestParam(value = "page",defaultValue = "1",required = false)Integer pageNum, @RequestParam(value = "limit",defaultValue = "10",required = false)Integer pageSize){
        int count = customerService.deleteById(id);
        ResultData resultData = null;
        if (count>0){
            resultData = ResultData.createSuccessResult(count);
        }else {
            resultData = ResultData.createFailResult(10003, "删除0项");
        }
        return resultData;
    }

    @RequestMapping("/editPage")
    public String editPage(){
        return "business/customerEdit";
    }

    @RequestMapping("/updateCusotmer")
    @ResponseBody
    public ResultData updateCustomer(@RequestBody Customer customer){
        ResultData resultData = null;
        customer.setUpdateTime(new Date());
        if(customerService.update(customer)>0){
            resultData = ResultData.createSuccessResult(null);
        }else {
            resultData = ResultData.createFailResult(10004, "未找到要修改的数据");
        }

        return resultData;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ResultData updateState(@RequestParam("id") String id,@RequestParam(value = "state",defaultValue = "1",required = false) Integer state){
        ResultData resultData = null;
        Customer customer = new Customer();
        customer.setCustomerId(id);
        customer.setUpdateTime(new Date());
        customer.setState(state);
        if(customerService.update(customer)>0){
            resultData = ResultData.createSuccessResult(customer);
        }else {
            resultData = ResultData.createFailResult(10004, "未找到要修改的数据");
        }

        return resultData;
    }

    @RequestMapping("/addCusotmer")
    @ResponseBody
    public ResultData addCusotmer(@RequestBody Customer customer){
        ResultData resultData = null;
        customer.setCustomerId(UUID.randomUUID().toString().replace("-", ""));
        customer.setCreatetime(new Date());
        customer.setState(1);
        if(customerService.inser(customer)>0){
            resultData = ResultData.createSuccessResult(null);
        }else {
            resultData = ResultData.createFailResult(10004, "受影响行数为0");
        }

        return resultData;
    }
}
