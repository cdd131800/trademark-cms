package com.trademark.cms.controller.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CmsAuthenticationListener implements AuthenticationListener {
    @Override
    public void onSuccess(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        log.info("登录成功");
    }

    @Override
    public void onFailure(AuthenticationToken authenticationToken, AuthenticationException e) {
        log.info("登录失败");
    }

    @Override
    public void onLogout(PrincipalCollection principalCollection) {
        log.info("登录推出");
    }
}
