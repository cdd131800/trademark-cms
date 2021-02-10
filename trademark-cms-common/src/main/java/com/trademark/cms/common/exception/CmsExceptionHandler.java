package com.trademark.cms.common.exception;

import com.trademark.cms.common.enums.CmsErrorCodeEnum;
import com.trademark.cms.common.rest.CmsResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(basePackages = "com.trademark.cms")
public class CmsExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CmsResponseData bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return CmsResponseData.createBizExceptionRespBody(e);
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CmsResponseData exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return CmsResponseData.createErrorRespBody(CmsErrorCodeEnum.ERR_SERVICE_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CmsResponseData exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return CmsResponseData.createErrorRespBody(CmsErrorCodeEnum.ERR_SERVICE_ERROR);
    }

}
