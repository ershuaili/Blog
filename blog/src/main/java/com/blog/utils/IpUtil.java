package com.blog.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author 委稼祥
 * @date : 2022/5/15 21:24
 * @description : 获取用户ip工具类
 */
public class IpUtil {


    //private static final String UNKNOWN = "unknown";

    protected IpUtil() {

    }

    /**
     * 获取IP地址 使用 Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非
     * unknown的有效IP字符串，则为真实IP地址
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // log.info("获取客户端ip: " + ip);
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
    /**
     * 判断是否为内网
     * @param ipAddress IP地址
     * @return IP地址
     */
    public static boolean isInnerIp(String ipAddress) {
        boolean isInnerIp;
        if (ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0];
        }
        ipAddress = ipAddress.replaceAll(",", ".").replaceAll(" ", "");
        long ipNum = getIpNum(ipAddress);

        // 私有IP：A类 10.0.0.0-10.255.255.255  B类  172.16.0.0-172.31.255.255 C类 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
        long aBegin = getIpNum("10.0.0.0");
        long aEnd = getIpNum("10.255.255.255");
        long bBegin = getIpNum("172.16.0.0");
        long bEnd = getIpNum("172.31.255.255");
        long cBegin = getIpNum("192.168.0.0");
        long cEnd = getIpNum("192.168.255.255");
        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
                || "127.0.0.1".equals(ipAddress);
        return isInnerIp;
    }

    private static long getIpNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);

        //long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
        return a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    }

    private static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }
    /**
     * 此方法调用百度AIP来查询IP所在地域(YYR)
     * @param strIp（传入的IP地址）
     * @return 地域
     */
    public static String getAddressByIp(String strIp) {
        try {
            URL url = new URL("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+strIp);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            String ipAddr = result.toString();
            try {
                JSONObject obj1= JSON.parseObject(ipAddr);
                if("0".equals(obj1.get("status").toString())){
                    JSONObject obj2= JSON.parseObject(obj1.get("content").toString());
                    JSONObject obj3= JSON.parseObject(obj2.get("address_detail").toString());
                    return obj3.get("province").toString()+obj3.get("city").toString();
                }else{
                    return "读取失败";
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "读取失败";
            }

        } catch (IOException e) {
            return "读取失败";
        }
    }

    public static String getAddress(String ip) {
        String loginAddress;
        try {
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }

            if("127.0.0.1".equals(ip) || isInnerIp(ip)) {
                loginAddress = "局域网";
            }else{
                loginAddress = getAddressByIp(ip);
                if("读取失败".equals(loginAddress)) {
                    loginAddress = null;
                }
            }
            return loginAddress;
        } catch (Exception e) {
            return null;
        }

    }
}