package com.trademark.cms.controller.shiro;

import com.trademark.cms.dao.vo.CmsAdminBaseVo;
import com.trademark.cms.service.account.ICmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 重写FormAuthenticationFilter中部分实现
 * created by andy on 2019/7/30
 **/
@Slf4j
public class CmsFormAuthenticationFilter extends FormAuthenticationFilter {
    @Autowired
    ICmsAdminService accountService;

    /**
     * 当未登录请求被拦截
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("isLoginSubmission(request, response):{}",   WebUtils.toHttp(request).getRequestURI());
        //shiro会调用isLoginRequest来判断当前请求是不是 登录请求
        // 判断请求是否是post方法
        if (isLoginSubmission(request, response)) {
            return executeLogin(request, response);
        } else {
            log.info(httpServletRequest.getHeader("X-Requested-With"));
            log.info(request.getParameter("ajax"));
            if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))
                    || request.getParameter("ajax") != null) {
                HttpServletResponse res = WebUtils.toHttp(response);
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return true;
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //log.info("用户 [ " + username + " ] 进行了登陆操作");
        if (username == null) {
            return true;
        }
        CmsAdminBaseVo user = accountService.findAdminByAccount(username);
        SecurityUtils.getSubject().getSession().setAttribute(CmsShiroConstant.CURRENT_ADMIN, user);

        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))
                || request.getParameter("ajax") == null) {// 不是ajax请求
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + this.getSuccessUrl());
        } else {
            httpServletRequest.getRequestDispatcher("/login/timeout/success").forward(httpServletRequest,
                    httpServletResponse);
        }

        return false;
    }
}
