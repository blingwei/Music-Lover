package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.commanBean.PublishManageBean;
import com.blingwei.musicService.bean.responseBean.*;
import com.blingwei.musicService.dao.esServise.EssayWithSongEsService;
import com.blingwei.musicService.enums.SexEnum;
import com.blingwei.musicService.enums.StatusEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.*;
import com.blingwei.musicService.service.*;
import com.blingwei.musicService.utils.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminManage {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminPermService adminPermService;

    @Autowired
    private EssayWithSongService essayWithSongService;

    @Autowired
    private TopicService topicService;


    @Autowired
    private MessageManage messageManage;


    public List<AdminMenu> getCurrentUserMenu(){
        Integer currentUserId = userService.getCurrentUser().getId();
        List<AdminMenu> adminMenuList = adminMenuService.findMenuByUserId(currentUserId);
        List<AdminMenu> res = new ArrayList<>();

        for(int i=0; i<adminMenuList.size(); i++){
            AdminMenu adminMenuTemp = adminMenuList.get(i);
            if(adminMenuList.get(i).getParentId() == 0){
                adminMenuTemp.setChildren(new ArrayList<>());
                res.add(adminMenuList.get(i));
                adminMenuList.remove(adminMenuTemp);
                i--; //删除后长度会减1
            }
        }
        for(int i=0; i<res.size(); i++){
            menuSetChildren(res.get(i), adminMenuList);
        }
        return res;
    }

    private void menuSetChildren(AdminMenu adminMenu, List<AdminMenu> list){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        for(int i=0; i<list.size(); i++){
            AdminMenu adminMenuTemp = list.get(i);
            if(list.get(i).getParentId().equals(adminMenu.getId())){
                adminMenuTemp.setChildren(new ArrayList<>());
                adminMenu.getChildren().add(list.get(i));
                list.remove(adminMenuTemp);
                i--;
                menuSetChildren(adminMenuTemp, list);
            }
        }
    }

    public List<AdminUserInfoResponse> getUsers(){
        List<AdminUserInfoResponse> adminUserInfoResponses = userService.getAdminUserInfos();
        return adminUserInfoResponses;
    }

    public List<AdminRolesResponse> getListRole(){
        List<AdminRolesResponse> res = new ArrayList<>();
        for(AdminRole adminRole: adminRoleService.getAllAdminRole()){
            AdminRolesResponse adminRolesResponse = new AdminRolesResponse();
            adminRolesResponse.setAdminRole(adminRole);

            adminRolesResponse.setMenus(adminMenuService.findMenuByRoleId(adminRole.getId()));
            adminRolesResponse.setPerms(adminPermService.findPermsByRoleId(adminRole.getId()));
            res.add(adminRolesResponse);
        }
        return res;
    }

    public void  editUser(AdminUserInfoResponse selectedUser){
        UserInfo userInfo = new UserInfo();
        Integer userId = userService.findUserByName(selectedUser.getUsername()).getId();
        userInfo.setUserId(userId);
        userInfo.setIntroduce(selectedUser.getIntroduce());
        if(selectedUser.getSex()!= null){
            userInfo.setSex(SexEnum.valueOf(selectedUser.getSex()));
        }
        userInfo.setAge(selectedUser.getAge());
        userService.editUser(userInfo);
        adminRoleService.changeAdminRoleByUserId(userId, selectedUser.getRoleId());
    }

    public List<AdminMenu> getAllMenu(){
        List<AdminMenu> adminMenuList = adminMenuService.findMenuByUserId(1);
        List<AdminMenu> res = new ArrayList<>();
        for(int i=0; i<adminMenuList.size(); i++){
            AdminMenu adminMenuTemp = adminMenuList.get(i);
            if(adminMenuList.get(i).getParentId() == 0){
                adminMenuTemp.setChildren(new ArrayList<>());
                res.add(adminMenuList.get(i));
                adminMenuList.remove(adminMenuTemp);
                i--; //删除后长度会减1
            }
        }
        for(int i=0; i<res.size(); i++){
            menuSetChildren(res.get(i), adminMenuList);
        }
        return res;
    }

    public List<AdminPermission> getAllPerm(){
        return adminPermService.getAllPerm();
    }

    public void editRole(AdminRolesResponse adminRolesRequest){
        AdminRole role = adminRolesRequest.getAdminRole();
        adminRoleService.editRole(role, adminRolesRequest.getMenus(), adminRolesRequest.getPerms());
    }

    public void addRole(AdminRole adminRole){
        adminRoleService.addRole(adminRole);
    }

    public PublishVerifyResponse getPublishesWithoutVerify(Integer start, Integer size){
        PublishVerifyResponse res = new PublishVerifyResponse();
        res.setPublishVerifyBeans(essayWithSongService.getPublishesWithoutVerify(start, size));
        res.setNums(essayWithSongService.getAllPublishNums());
        return  res;
    }

    public PublishVerifyResponse getTopicsWithoutVerify(Integer start, Integer size){
        PublishVerifyResponse res = new PublishVerifyResponse();
        res.setPublishVerifyBeans(topicService.getTopicsWithoutVerify(start, size));
        res.setNums(topicService.getAllTopicNums());
        return  res;
    }

    public AdminViewPublishResponse getPublishView(Integer id){
        return essayWithSongService.getPublishView(id);
    }

    public void verifyPublish(Integer id, Integer status, int type, String userName, String publishName){
        if(type == 1){
            essayWithSongService.AdminVerifyPublish(id, status);
            if(status == 1){//审核通过
                EssayWithSongEsService.add(essayWithSongService.findEssayForElasticById(id));//存入es便于搜索
            }
        }else{
            topicService.AdminVerifyTopic(id, status);
        }
        Message message = new Message();
        message.setSendUserId(1);//系统管理员
        message.setReceiveUserId(userService.getUserInoByUserName(userName).getUserId());
        String content = "您的"+ TypeEnum.valueOf(type).getMessage()+ " '"+ publishName+ "' 审核"+ StatusEnum.valueOf(status).getMessage();
        message.setContent(content);
        messageManage.sendOneMessage(userName, message);
    }

    public PublishManageResponse getPublishesWithRestrict(Integer start, Integer size, String input, Integer status) {
        PublishManageResponse publishManageResponse = new PublishManageResponse();
        List<PublishManageBean> list = essayWithSongService.getPublishesWithRestrict(start, size, input, status);
        for(PublishManageBean publishManageBean: list){
            publishManageBean.setStatus(StatusEnum.getName(Integer.parseInt(publishManageBean.getStatus())));
        }
        publishManageResponse.setPublishManageBeans(list);
        publishManageResponse.setNums(essayWithSongService.getPublishesWithRestrictNums(start, size, input, status));
        return publishManageResponse;
    }

    public void  deletePublish(int id){
        essayWithSongService.deletePublish(id);
    }
}
