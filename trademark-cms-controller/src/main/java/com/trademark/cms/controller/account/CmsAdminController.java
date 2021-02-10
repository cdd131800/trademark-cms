package com.trademark.cms.controller.account;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.trademark.cms.common.enums.CmsErrorCodeEnum;
import com.trademark.cms.common.exception.BizException;
import com.trademark.cms.common.rest.CmsResponseData;
import com.trademark.cms.common.utils.CmsDateUtil;
import com.trademark.cms.controller.CmsBaseController;
import com.trademark.cms.service.account.ICmsAdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsAdminController extends CmsBaseController {

    @NonNull
    private ICmsAdminService adminService;

    @Resource
    private Producer captchaProducer;

    /**
     * 登录方法
     *
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public CmsResponseData doLogin(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        captchaValidate(request,subject.getSession());
        UsernamePasswordToken token = new UsernamePasswordToken(username, (password));
        String errorMsg = "";
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            errorMsg = "用户名或密码错误";
        } catch (IncorrectCredentialsException ice) {
            errorMsg = "用户名或密码错误";
        } catch (LockedAccountException lae) {
            errorMsg = "您的账号已被锁定，请稍后再试";
        } catch (ExcessiveAttemptsException eae) {
            errorMsg = "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            errorMsg = "用户名或密码错误";
        } catch (Exception ex) {
            errorMsg = "未知错误";
        }
        if (StringUtils.isNotEmpty(errorMsg)) {
            return CmsResponseData.createErrorRespBody(CmsErrorCodeEnum.ADMIN_DO_LOGIN_ERROR, errorMsg);
        }
        return CmsResponseData.createSuccessRespBody(getCurrentAdmin());
    }


    /**
     * 验证码控制器
     */
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();

        //将验证码存到session
        Subject subject = SecurityUtils.getSubject();
        log.info("存储验证码的sessionId="+subject.getSession().getId());
        log.info("存储验证码的session时间="+ CmsDateUtil.getTimeByTimestamp(subject.getSession().getTimeout()));
        subject.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 登录方法
     *
     * @return
     */
    @RequestMapping(value = "/getCurrentAdmin", method = RequestMethod.POST)
    @ResponseBody
    public CmsResponseData getCurrentAdmin(HttpServletRequest request ) {
        log.info("进入获取当前登录信息");
        return CmsResponseData.createSuccessRespBody(getCurrentAdmin());
    }



    /**
     * 进行验证码验证
     * @param request
     */
    public void captchaValidate(HttpServletRequest request, Session session){
        // 从请求中获取参数验证码
        String captchaCode = WebUtils.getCleanParam(request, "captchaCode");
        // 从session获取正确的验证码
        String validateCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(StringUtils.isEmpty(captchaCode)){
            log.info("验证码不能为空，请填写验证码");
            throw new BizException(CmsErrorCodeEnum.ADMIN_VERIFICATION_CODE_ERROR,"验证码不能为空，请填写验证码");
        }
        if(StringUtils.isEmpty(validateCode)){
            log.info("验证码已过期，请刷新验证码");
            throw new BizException(CmsErrorCodeEnum.ADMIN_VERIFICATION_CODE_ERROR,"验证码已过期，请刷新验证码");
        }
        if(!captchaCode.equalsIgnoreCase(validateCode)){
            log.info("验证码错误");
            throw new BizException(CmsErrorCodeEnum.ADMIN_VERIFICATION_CODE_ERROR);
        }
    }

}
