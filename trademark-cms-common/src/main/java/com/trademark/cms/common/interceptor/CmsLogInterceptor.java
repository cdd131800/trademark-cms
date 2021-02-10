package com.trademark.cms.common.interceptor;

import com.trademark.cms.common.utils.CmsTraceIdUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description ：
 * @Author : itw_zhangsy05
 * @Date : 2020/9/4 13:42
 **/
public class CmsLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(CmsTraceIdUtil.TRACE_ID_KEY);
        traceId = StringUtils.isEmpty(traceId) || CmsTraceIdUtil.isDefaultTraceId(traceId) ? CmsTraceIdUtil.generalTraceId() : traceId;
        CmsTraceIdUtil.setTraceId(traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CmsTraceIdUtil.rempveTraceId();
    }
}
