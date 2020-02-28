package com.blingwei.musicService.service;

import com.blingwei.musicService.pojo.AdminPermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPermService {

    List<AdminPermission> getAllPerm();

    List<Integer> findPermsByRoleId(Integer Id);

}
