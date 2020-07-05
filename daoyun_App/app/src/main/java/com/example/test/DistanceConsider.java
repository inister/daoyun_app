package com.example.test;

import android.util.Log;


/**
 * @author xulin
 * @date create in 9:06 2018/11/1
 * Description 根据提供的两个国标经纬度，计算两个点之间的距离
 */
public class DistanceConsider {
    // 地球半径
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @author xulin
     * date 9:09 2018/11/1
     * Description 计算距离
     * 参数传入：所在位置的坐标  目标位置的坐标
     * 一个参数的经纬之前要求使用","英文的逗号进行分隔
     * 返回： 输入的两个坐标点之间的距离  精确到小数点后两位
     */
    public static double getDistance(double lngLocation, double latLocation, double lngTarget, double latTarget) {
        double lat1 = rad(latLocation);
        double lat2 = rad(latTarget);
        double lat = lat1 - lat2;
        double lng = rad(lngLocation) - rad(lngTarget);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(lat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(lng / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000d) / 10000d;
        distance = distance * 1000;
        return distance;
    }

}
