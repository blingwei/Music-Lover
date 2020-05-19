package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contact record);

    Contact selectByPrimaryKey(Integer id);

    List<Contact> selectAll();

    int updateByPrimaryKey(Contact record);

    void deleteContact(@Param("id") Integer id, @Param("contactId") Integer contactId);

    void addContact(@Param("id") Integer id, @Param("contactId") Integer contactId);

    Contact getContact(@Param("id") Integer id, @Param("contactId") Integer contactId);
}