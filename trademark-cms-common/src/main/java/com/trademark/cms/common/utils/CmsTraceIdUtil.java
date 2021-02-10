package com.trademark.cms.common.utils;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @Description ：traceId 工具类
 * @Author : itw_zhangsy05
 * @Date : 2020/9/4 13:44
 **/
public class CmsTraceIdUtil {

    public static final String TRACE_ID_KEY = "TRACE_ID_KEY";

    public static final String DEFAULT_TRACE_ID = "DEFAULT_TRACE_ID_0";

    public static void setTraceId(String traceId) {
        traceId = StringUtils.isEmpty(traceId) ? DEFAULT_TRACE_ID : traceId;
        MDC.put(TRACE_ID_KEY, traceId);
    }

    public static String getTraceId(){
        String traceId = MDC.get(TRACE_ID_KEY);
        return StringUtils.isEmpty(traceId) ? DEFAULT_TRACE_ID : traceId;
    }
    public static void rempveTraceId(){
        MDC.remove(TRACE_ID_KEY);
    }

    public static boolean isDefaultTraceId(String traceId){
        return DEFAULT_TRACE_ID.equals(traceId);
    }

    public static String generalTraceId() {
        return UUID.randomUUID().toString();
    }

}
