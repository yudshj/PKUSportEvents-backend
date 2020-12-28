package org.pkuse2020grp4.pkusporteventsbackend.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.pkuse2020grp4.pkusporteventsbackend.dto.UserDTO;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.perm.Perms;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtToken;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        int userId = JwtUtils.getUserId(principals.toString());
        UserDTO user = null;
        try {
            user = userService.getUserDTOByUserId(userId);
        } catch (UserNotFoundException e) {
            //e.printStackTrace();
        }
        assert user != null;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(Perms.permToString(user.getPermission()));
        Set<String> permission = new HashSet<>(Perms.permAPI.get(user.getPermission()));
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        int userId = JwtUtils.getUserId(token);
        if (userId == -1) {
            throw new AuthenticationException("token invalid");
        }

        UserDTO userBean;
        try {
            userBean = userService.getUserDTOByUserId(userId);
        } catch (UserNotFoundException e) {
            throw new AuthenticationException("User didn't existed!");
        }
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        return new SimpleAuthenticationInfo(token, token, "realm");
    }



}
