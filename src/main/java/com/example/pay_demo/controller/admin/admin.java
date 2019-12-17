package com.example.pay_demo.controller.admin;

import com.example.pay_demo.entity.Admin;
import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.entity.VO.DoctorInfoVO;
import com.example.pay_demo.service.AdminService;
import com.example.pay_demo.service.DoctorService;
import com.example.pay_demo.service.RegistrationService;
import com.example.pay_demo.util.Md5Utils;
import com.example.pay_demo.util.ResultVO;
import com.example.pay_demo.util.ResultVOUtils;
import com.example.pay_demo.util.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("checkLogin")
    public ModelAndView checkLogin(@RequestParam("acount")String acount, @RequestParam("password") String password, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Admin user = adminService.findUserByAcount(acount);
        System.out.println("请求过来。。。");
        ModelAndView mv = new ModelAndView();
        if(user == null){
//            return ResultVOUtils.error(ResultEnum.USER_NOT_EXISTS.getCode(),ResultEnum.USER_NOT_EXISTS.getMsg());
            mv.setViewName("admin/login");
            mv.addObject("msg","用户不存在");
            mv.addObject("success",false);
            return  mv;
       }
        //系统查询出来的密码
        String pwd = user.getPassword();
        //用户输入的密码
        String  pwd1 = Md5Utils.EncoderByMd5(password);

        if (Md5Utils.checkpassword(pwd1,pwd)){
            mv.setViewName("admin/login");
          mv.addObject("msg","密码错误");
            mv.addObject("success",false);
          return  mv;
        }
        session.setAttribute("user",acount);
        mv.setViewName("admin/header");
        mv.addObject("success",true);
        mv.addObject("msg","");

        return  mv;

    }



    @RequestMapping("adminPage")
    public String adminPage(){

        return  "admin/adminPage";
    }

    @RequestMapping("doctor")
    public ModelAndView doctorPage(ModelAndView modelAndView){
        List<DoctorInfoVO>  doctors = doctorService.findAllDoctors();
        if(doctors == null)
            return  null;
        modelAndView.addObject("doctors",doctors);
        modelAndView.setViewName("admin/doctorInfo");


        return  modelAndView;
    }

    @RequestMapping("showAllRegistration")
    public ModelAndView showAllRegistration(ModelAndView modelAndView){
        List<Registration>  Registration = registrationService.findAllRegistration();

        modelAndView.addObject("Registration",Registration);

        modelAndView.setViewName("admin/showAllRegistration");

        return modelAndView;

    }

    @RequestMapping("header")
    public String  header(){

        return "/admin/header";
    }


    @ResponseBody
    @RequestMapping("selectRegistration")
    public  List<Registration> selectRegistration( @RequestParam("status") String status){

        List<Registration>   reg = registrationService.findAllByStatus(status);

        return reg;
    }



}
