package com.njl.oa.shiro;

import com.njl.oa.dao.EmployeeDao;
import com.njl.oa.entity.Employee;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String number = principals.getPrimaryPrincipal().toString();  //获取到登录工号
        System.out.println(number+"*************************************************************************************");
        /* 如果工号为100001就是管理员*/
        if(number.equals("100001")){
            info.addStringPermission("*");  //赋予管理员所有权限
        }else{
            Set<String> strings = employeeDao.selectEmployeeByPermissions(number);  //去数据库查询所拥有权限
            for(String s : strings){
                info.addStringPermission(s); // 赋予权限
            }
        }
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("RealmForDouble认证中---->用户：" + token.getPrincipal());
        //  获取雇员输入的工号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //  数据库查找是否有此工号
        Employee employee = employeeDao.selectEmployeeByNumber(upToken.getUsername());
        //  盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(employee.getEmployeeNumber());
        //  保存到shiro缓存中
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(upToken.getUsername(), employee.getEmployeePassword(), credentialsSalt, this.getName());
        if (info == null) {
            return null;
        }
        return info;
    }
}
