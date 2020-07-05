package com.example.test;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtill {
    private String ip;
    public static Properties getProperties(){

        Properties props = new Properties();
        try {
            InputStream in = PropertiesUtill.class.getResourceAsStream("/assets/config.properties");
            props.load(in);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return props;
    }

    public static String initProperties(Context context) {
        Properties props = new Properties();
        try {
            props.load(context.getAssets().open("config.properties"));
            FileOutputStream out = context.openFileOutput("config.properties",Context.MODE_PRIVATE);
            props.store(out, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改配置文件失败!";
        }
        return "设置成功";
    }

    public static String setProperties(Context context, String keyName, String keyValue) {
        Properties properties = new Properties();
        try {
            properties.load(context.openFileInput("config.properties"));
            properties.setProperty(keyName, keyValue);
            FileOutputStream out = context.openFileOutput("config.properties",Context.MODE_PRIVATE);
            properties.store(out, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改配置文件失败!";
        }
        return "设置成功";
    }

    public static String getProperties(Context context, String key) {
        String data = null;
        Properties properties = getProperties();
        try {
            properties.load(context.openFileInput("config.properties"));
            data = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }



}

