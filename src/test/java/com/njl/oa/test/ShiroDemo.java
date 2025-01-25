package com.njl.oa.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.taglibs.standard.tag.common.fmt.MessageSupport;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class ShiroDemo {
    private static final Logger log = LoggerFactory.getLogger(ShiroDemo.class);

    public static void main(String[] args) {
        //1.创建SecurityManagerFactory
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.获取SecurityManager,绑定到SecurityUtils中
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        //3.获取一个用户信息
        Subject subject = SecurityUtils.getSubject();
        //4.判断是否已经身份验证
        if (!subject.isAuthenticated()) {
            // 4.1把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest1");
            // 4.2设置rememberme,记住我
            token.setRememberMe(true);
            try {
                // 4.3登录.
                subject.login(token);
            } catch (UnknownAccountException uae) { //用户不存在异常
                System.out.println("****---->用户名不存在： " + token.getPrincipal());
                return;
            } catch (IncorrectCredentialsException ice) {// 密码不匹配异常
                System.out.println("****---->" + token.getPrincipal() + " 的密码错误!");
                return;
            } catch (LockedAccountException lae) {// 用户被锁定
                System.out.println("****---->用户 " + token.getPrincipal() + " 已被锁定");
                return;
            } catch (AuthenticationException ae) { // 其他异常，认证异常的父类
                System.out.println("****---->用户" + token.getPrincipal() + " 验证发生异常");
                return;
            }
        }
        // 5.权限测试：
        //5.1判断用户是否有某个角色
        if (subject.hasRole("guest")) {
            System.out.println("****---->用户拥有角色guest!");
        } else {
            System.out.println("****---->用户没有拥有角色guest");
        }

        //5.2判断用户是否执行某个操作的权限
        if (subject.isPermitted("see")) {
            System.out.println("****----> 用户拥有执行此功能的权限");
        } else {
            System.out.println("****---->用户没有拥有执行此功能的权限");
        }

        //6.退出
        System.out.println("****---->" + subject.isAuthenticated());
        subject.logout();
        System.out.println("****---->" + subject.isAuthenticated());
    }
}
