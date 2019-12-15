package com.example.pay_demo.controller.admin;

import com.example.pay_demo.entity.Admin;
import com.example.pay_demo.service.AdminService;
import com.example.pay_demo.util.Md5Utils;
import com.example.pay_demo.util.ResultVO;
import com.example.pay_demo.util.ResultVOUtils;
import com.example.pay_demo.util.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
@Controller
@RequestMapping("admin")
public class admin {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("checkLogin")
    public ResultVO checkLogin(@RequestParam("acount")String acount, @RequestParam("password") String password, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Admin user = adminService.findUserByAcount(acount);
        System.out.println("username:"+acount);
        if(user == null){
            return ResultVOUtils.error(ResultEnum.USER_NOT_EXISTS.getCode(),ResultEnum.USER_NOT_EXISTS.getMsg());
        }
        //系统查询出来的密码
        String pwd = user.getPassword();
        //用户输入的密码
        String  pwd1 = Md5Utils.EncoderByMd5(password);

        if (Md5Utils.checkpassword(pwd1,pwd))
            return ResultVOUtils.error(ResultEnum.USER_PWD_ERROR.getCode(),ResultEnum.USER_PWD_ERROR.getMsg());
        session.setAttribute("user",acount);
        return ResultVOUtils.success(ResultEnum.USER_LOGIN_SUCCESS.getCode(),ResultEnum.USER_LOGIN_SUCCESS.getMsg());

    }



    @RequestMapping("adminPage")
    public String adminPage(){

        return  "admin/adminPage";
    }
}
