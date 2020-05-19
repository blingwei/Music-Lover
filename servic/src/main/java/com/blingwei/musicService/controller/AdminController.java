package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.commanBean.OperateBean;
import com.blingwei.musicService.bean.requestBaen.VerifyPublishRequest;
import com.blingwei.musicService.bean.responseBean.AdminRolesResponse;
import com.blingwei.musicService.bean.responseBean.AdminUserInfoResponse;
import com.blingwei.musicService.manage.AdminManage;
import com.blingwei.musicService.pojo.AdminMenu;
import com.blingwei.musicService.pojo.AdminRole;
import com.blingwei.musicService.pojo.Recommend;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AdminController {

    @Autowired
    private AdminManage adminManage;


    @GetMapping("/menu")
    public Result getCurrentUserMenu() {
        return ResultFactory.buildSuccessResult(" ", adminManage.getCurrentUserMenu());
    }

    @GetMapping("/admin/user/list")
    public Result getListUser() {
        return ResultFactory.buildSuccessResult(" ", adminManage.getUsers());
    }

    @GetMapping("/admin/role/list")
    public Result getListRole() {
        return ResultFactory.buildSuccessResult(" ", adminManage.getListRole());
    }

    @PostMapping("admin/user/editUser")
    public Result editUser(@RequestBody AdminUserInfoResponse selectedUser) {
        try {
            adminManage.editUser(selectedUser);
            return ResultFactory.buildSuccessResult(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }

    }

    @GetMapping("admin/role/menu")
    public Result getAllMenu() {
        return ResultFactory.buildSuccessResult(null, adminManage.getAllMenu());
    }

    @GetMapping("/admin/role/perm")
    public Result getAllPerm() {
        return ResultFactory.buildSuccessResult(null, adminManage.getAllPerm());
    }


    @GetMapping("/admin/assessor/publishes")
    public Result getPublishesWithoutVerify(@Param("start") Integer start, @Param("size") Integer size) {
        return ResultFactory.buildSuccessResult(null, adminManage.getPublishesWithoutVerify(start, size));
    }

    @GetMapping("/admin/assessor/topics")
    public Result getTopicsWithoutVerify(@Param("start") Integer start, @Param("size") Integer size) {
        return ResultFactory.buildSuccessResult(null, adminManage.getTopicsWithoutVerify(start, size));
    }

    @GetMapping("/admin/content/publishes")
    public Result getAllPublishes(@Param("start") Integer start, @Param("size") Integer size, @Param("input") String input, @Param("status") Integer status) {
        return ResultFactory.buildSuccessResult(null, adminManage.getPublishesWithRestrict(start, size, input, status));
    }

    @PostMapping("/admin/role/editRole")
    public Result editRole(@RequestBody AdminRolesResponse adminRolesRequest) {
        try {
            adminManage.editRole(adminRolesRequest);
            return ResultFactory.buildSuccessResult(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }

    @PostMapping("/admin/role")
    public Result editRole(@RequestBody AdminRole adminRole) {
        try {
            adminManage.addRole(adminRole);
            return ResultFactory.buildSuccessResult("添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }

    }

    @GetMapping("/admin/assessor/viewPublish")
    public Result viewPublish(@Param("id") Integer id) {
        return ResultFactory.buildSuccessResult("", adminManage.getPublishView(id));
    }

    @PostMapping("/admin/assessor/verifyPublish")
    public Result verifyPublish(@NotNull @RequestBody VerifyPublishRequest verifyPublishRequest) {
        adminManage.verifyPublish(verifyPublishRequest.getId(), verifyPublishRequest.getStatus(), verifyPublishRequest.getType(), verifyPublishRequest.getUserName(), verifyPublishRequest.getPublishName());
        return ResultFactory.buildSuccessResult("", null);
    }

    @PostMapping("/admin/content/publish/delete")
    public Result deletePublish(@NotNull @RequestBody OperateBean deleteRequest) {
        adminManage.deletePublish(deleteRequest.getId());
        return ResultFactory.buildSuccessResult("删除成功", null);
    }

    @PostMapping("/admin/content/publish/recommend")
    public Result recommendPublish(@NotNull @RequestBody Recommend recommend) {
        adminManage.recommendPublish(recommend);
        return ResultFactory.buildSuccessResult("删除成功", null);
    }


}
