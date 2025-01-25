package com.njl.oa.shiro;

import java.util.LinkedHashMap;

public class FilterChainMap {
    // 使用静态工厂
    public static LinkedHashMap<String, String> getFilterChainMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //  anon通过，authc拦截
        //  放行
        map.put("/", "anon");    //根目录
        map.put("/main/unLogin", "anon");    //未登录
        map.put("/main/toLogin", "anon");    //登录页面
        map.put("/main/login", "anon");    //提交登录
        map.put("/static/**", "anon");  //静态资源
        //  拦截
        map.put("/**", "authc");        //必须在最后一排
        return map;
    }
}
