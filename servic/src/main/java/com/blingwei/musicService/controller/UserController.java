package com.blingwei.musicService.controller;


import com.blingwei.musicService.pojo.User;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/api/login")
    public Result login(@RequestBody User user){
        String name = user.getUsername();
        name = HtmlUtils.htmlEscape(name);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, user.getPassword());
        try{
            subject.login(usernamePasswordToken);
            return new Result(200,"登录成功",usernamePasswordToken);
        }catch (Exception e){
            return new Result(400,"账号或密码错误",usernamePasswordToken);
        }
    }

    @RequestMapping("/api/register")
    public Result register(@RequestBody User user){
        String name = user.getUsername();
        String pass = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setUsername(name);
        if(userService.findUserByName(name)!=null){
            return new Result(400, "用户名已存在", null);
        }
        //随机生成16位的盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String passInDB = new SimpleHash("md5", pass, salt, 2).toString();
        user.setPassword(passInDB);
        user.setSalt(salt);
        userService.addUser(user);
        return new Result(200, "注册成功", null);
    }

    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(200,"注销成功", null);
}

    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }

}
