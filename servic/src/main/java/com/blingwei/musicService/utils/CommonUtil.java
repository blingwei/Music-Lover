package com.blingwei.musicService.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    public static String DateToString(Date date, String params){
        try{
            return new SimpleDateFormat(params).format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
