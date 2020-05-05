package com.example.htgh.common;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展application用于存储一些全局信息
 */
public class MyApplication extends Application {
    private static Map map;
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        map=new HashMap();
        //将context封住到静态方法，以方便获取
        application=this;
    }
    public static MyApplication getApplication(){
        return application;
    }
    public static void setAttribute(String key,Object value){
        map.put(key,value);
    }
    public static Object getAttribute(String key){
        return map.get(key);
    }
    public static String getSessionId(){
        return   (String)getAttribute("sessionId");
    }
    public static void setSessionId(String sessionId){
        setAttribute("sessionId",sessionId);
    }

    public static void setCurrentUserId(int userId){
        setAttribute("userId",userId);
    }
    public static int getCurrentUserId(){
        return (int)getAttribute("userId");
    }
}
