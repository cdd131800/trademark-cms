package com.trademark.cms.controller.shiro;

import com.google.code.kaptcha.Constants;
import com.trademark.cms.common.enums.CmsErrorCodeEnum;
import com.trademark.cms.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: 验证码验证拦截器
 *
 * 如果isAccessAllowed方法返回True，则不会再调用onAccessDenied方法，
 * 如果isAccessAllowed方法返回Flase,则会继续调用onAccessDenied方法。
 * 而onAccessDenied方法里面则是具体执行登陆的地方。由于我们已经登陆，
 * 所以此方法就会返回True(filter放行),所以上面的onPreHandle方法里面的onAccessDenied方法就不会被执行
 **/
@Slf4j
public class CmsCaptchaValidateFilter extends AccessControlFilter {
    //前台提交的验证码参数名
    private String captchaParam = "captchaCode";
    //验证失败后存储到的属性名
    private String failureKeyAttribute = CmsShiroConstant.SHIRO_LOGIN_FAILURE;

    /**
     * 判断是否登录
     * 在登录的情况下会走此方法，此方法返回true直接访问控制器
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // 从请求中获取参数验证码
        String captchaCode = WebUtils.getCleanParam(request, getCaptchaParam());
        // 从session获取正确的验证码
        String validateCode = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (!"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
       /* if(StringUtils.isEmpty(captchaCode)){
            log.info("验证码不能为空，请填写验证码");
            throw new BizException(CmsErrorCodeEnum.ADMIN_VERIFICATION_CODE_ERROR,"验证码不能为空，请填写验证码");
        }
        if(StringUtils.isEmpty(validateCode)){
            log.info("验证码已过期，请刷新验证码");
            throw new BizException(CmsErrorCodeEnum.ADMIN_VERIFICATION_CODE_ERROR,"验证码已过期，请刷新验证码");
        }*/
        return captchaCode.equalsIgnoreCase(validateCode);
    }

    /**
     * 是否是拒绝登录 没有登录的情况下会走此方法 被拒绝登录
     * 根据请求，拒绝通过处理，如果返回false，则不再去访问web控制器或action
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            // 没有登录
            return false;
        }
        HttpSession session = WebUtils.toHttp(request).getSession();
        Object o = session.getAttribute(CmsShiroConstant.CURRENT_ADMIN);
        if (o == null) {
            // 你自己的逻辑
        }
        return true;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }
}