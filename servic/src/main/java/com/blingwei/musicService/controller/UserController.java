package com.blingwei.musicService.controller;


import com.blingwei.musicService.bean.requestBaen.EditUserInfoRequest;
import com.blingwei.musicService.pojo.User;
import com.blingwei.musicService.pojo.UserInfo;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.service.UserService;
import com.blingwei.musicService.utils.ConvertUtil;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConvertUtil convertUtil;

    @RequestMapping("/api/login")
    public Result login(@RequestBody User user){
        String name = user.getUsername();
        name = HtmlUtils.htmlEscape(name);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, user.getPassword());
        try{
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult("登录成功", usernamePasswordToken);
        }catch (Exception e){
            return ResultFactory.buildFailResult("账号或密码错误");
        }
    }

    @RequestMapping("/api/register")
    public Result register(@RequestBody User user){
        String name = user.getUsername();
        String pass = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setUsername(name);
        if(userService.findUserByName(name)!=null){
            return ResultFactory.buildFailResult("用户名已存在");
        }
        //随机生成16位的盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String passInDB = new SimpleHash("md5", pass, salt, 2).toString();
        user.setPassword(passInDB);
        user.setSalt(salt);
        userService.addUser(user);
        return ResultFactory.buildSuccessResult("注册成功", null);
    }

    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("注销成功", null);
}

    @GetMapping(value = "api/authentication")
    public String authentication(){
        if(SecurityUtils.getSubject().getPrincipal()!=null){
            return "身份认证成功";
        }
        return null;
    }

    @RequestMapping("api/getUserInfo")
    public Result getUserInfo(){
        UserInfo userInfo = userService.getCurrentUserInfo();
        return ResultFactory.buildSuccessResult("", convertUtil.showUserInfo(userInfo));
    }

    @PostMapping("api/editUserInfo")
    public Result editUserInfo(@RequestBody EditUserInfoRequest userInfo){
        try{
            userInfo.setUserId(userService.getCurrentUser().getId());
            userService.editUser(convertUtil.convertUserInfo(userInfo));
            return ResultFactory.buildSuccessResult("修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }


    }



}
