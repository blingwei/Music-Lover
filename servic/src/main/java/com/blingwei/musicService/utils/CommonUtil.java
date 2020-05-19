package com.blingwei.musicService.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static String upload() {
        return "";
    }

    public static String DateToString(Date date, String params) {
        try {
            return new SimpleDateFormat(params).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
