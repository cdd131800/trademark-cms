package com.trademark.cms.common.rest;

import com.trademark.cms.common.enums.CmsErrorCodeEnum;
import com.trademark.cms.common.exception.BizException;
import com.trademark.cms.common.utils.CmsTraceIdUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.Date;

/**
 * 响应报文
 */
@Data
public class CmsResponseData implements Serializable {
    /**
     * 响应结果码，成功为0000，其他值均为失败
     */
    private String code = CmsErrorCodeEnum.SUCCESS_CODE.getResultCode();
    /**
     * 返回信息，当code为0000时为空
     */
    private String message;
    /**
     * 响应报文体
     */
    private Object data;
    private String requestId;
    private long timestamp;

    public static long timestamp() {
        return new Date().getTime();
    }

    public static String requestId() {
        return MDC.get(CmsTraceIdUtil.TRACE_ID_KEY);
    }

    public static CmsResponseData createResponseBody(String code, String message, Object data) {
        CmsResponseData body = new CmsResponseData();
        body.code = code;
        body.message = message;
        body.data = data;
        body.timestamp = timestamp();
        if (StringUtils.equals("0000", code)) {
            body.requestId = requestId();
        }
        return body;
    }

    //创建错误响应报文
    public static CmsResponseData createErrorRespBody(ICmsErrorCode error) {
        return createResponseBody(error.getResultCode(), error.getResultMsg(), null);
    }

    //创建错误响应报文
    public static CmsResponseData createErrorRespBody(ICmsErrorCode error, String errorMsg) {
        return createResponseBody(error.getResultCode(), errorMsg, null);
    }

    //创建成功时的报文
    public static CmsResponseData createSuccessRespBody(Object data) {
        return createResponseBody(CmsErrorCodeEnum.SUCCESS_CODE.getResultCode(), CmsErrorCodeEnum.SUCCESS_CODE.getResultMsg(), data);
    }

    //创建抛异常的保文
    public static CmsResponseData createBizExceptionRespBody(BizException e) {
        return createResponseBody(e.getErrorCode(), e.getErrorMsg(), null);
    }


}
