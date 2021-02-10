package com.trademark.cms.controller.shiro;


import com.alibaba.fastjson.JSON;
import com.trademark.cms.dao.entity.CmsAdminBase;
import com.trademark.cms.dao.entity.CmsMenuBase;
import com.trademark.cms.dao.entity.CmsRoleBase;
import com.trademark.cms.dao.vo.CmsAdminBaseVo;
import com.trademark.cms.service.account.ICmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class CmsShiroRealm extends AuthorizingRealm {
    @Resource
    private ICmsAdminService adminService;

    /**
     * 目的授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CmsAdminBaseVo adminBase = (CmsAdminBaseVo) principals.getPrimaryPrincipal();
        if (null == adminBase) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<CmsMenuBase> menuList = adminBase.getMenus();
        if (CollectionUtils.isEmpty(menuList)) {
            for (CmsMenuBase menu : menuList) {
                authorizationInfo.addStringPermission(menu.getMenuCode().replace(":", "/"));
            }
        }
        List<CmsRoleBase> roleList = adminBase.getRoles();
        if (CollectionUtils.isEmpty(roleList)) {
            for (CmsRoleBase role : roleList) {
                authorizationInfo.addRole(role.getRoleKey());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String adminAccount = (String) token.getPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        CmsAdminBaseVo adminBase = adminService.findAdminByAccount(adminAccount);
        if (null == adminBase) {
            return null;
        }
        if (adminBase.getIsDeleted() == 1) { //账户冻结
            throw new LockedAccountException();
        }

        Session session = SecurityUtils.getSubject().getSession();
        adminBase.setToken(session.getId().toString());
        session.setAttribute(CmsShiroConstant.CURRENT_ADMIN, adminBase);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                adminBase.getAdminAccount(), //用户名
                adminBase.getAdminPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /**
     * 判断是否拥有权限
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        return super.isPermitted(principals, permission);
    }

    /**
     * 判断是否拥有角色
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean hasRole(PrincipalCollection principals, String permission) {
        return super.hasRole(principals, permission);
    }


}
