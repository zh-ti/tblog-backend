package com.tian.tblog.bean;

import com.tian.tblog.mapper.ManagerMapper;
import com.tian.tblog.utils.SpringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

public class TblogRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("PrimaryPrincipal: "+principal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Manager manager = getManagerMapper().queryManager(principal);
        System.out.println(manager);
        if(manager == null){
            simpleAuthorizationInfo.addRole("visitor");
        }else {
            String position;
            switch(Integer.parseInt(manager.getPosition())){
                case 0:
                    position = "root";
                    break;
                case 1:
                    position = "manager";
                    break;
                default:
                    position = "visitor";
            }
            simpleAuthorizationInfo.addRole(position);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String)authenticationToken.getPrincipal();
        Manager manager  = getManagerMapper().queryManager(principal);
        if(!ObjectUtils.isEmpty(manager)){
            return new SimpleAuthenticationInfo(
                    manager.getName(),
                    manager.getPassword(),
                    ByteSource.Util.bytes(manager.getSalt()),
                    this.getName()
                    );
        }
        return null;
    }

    private ManagerMapper getManagerMapper(){
        return SpringUtils.getIoc().getBean(ManagerMapper.class);
    }
}
