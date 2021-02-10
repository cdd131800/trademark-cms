package com.trademark.cms.common.enums;

import com.trademark.cms.common.rest.ICmsErrorCode;

/**
 * @Description: 错误类型定义
 * created by andy on 2019-05-08
 **/
public enum CmsErrorCodeEnum implements ICmsErrorCode {
    /**
     * 错误码编码说明
     * 错误码共分为6位 ABCCDD
     * A位为模块标识，模块共分为8类：
     * 1:page 页面管理；
     * 2:template 模板管理；3:article 内容管理；4:product 产品管理；5:file 素材管理；6互动管理；7运维管理；8系统管理；
     * B位为子模块标识
     * CC位为子菜单下的功能模块
     * DD位为具体操作的错误码
     * 常量定义方式：ERR_模块名称_子模块_功能_错误码
     * 例：素材库（5）下的素材管理（1）的上传素材（01）错误（01）
     * 则完整的定义方式为：ERR_FILE_UPLOAD_EMPTY("510101", "上传文件不能为空")
     */
    SUCCESS_CODE("0000", "请求成功"),
    ERR_SERVICE_ERROR("9999", "服务器异常,请稍后再试"),
    // 1000 - 1999 全局code
    REQUEST_PARAM_ERROR("1000", "入参异常,请检查入参后再试"),
    REQUEST_PARAM_LENGTH_ERROR("1001", "入参异常,参数长度超过限制"),
    REQUEST_UNAUTHORIZED_ERROR("1002", "请求失败,没有权限"),
    NULL_POINTER_EXCEPTION("1003", "空指针异常"),

    // 2000 - 2999 用户
    ADMIN_NOT_EXIST_ERROR("2000", "管理员不存在"),
    ADMIN_DO_LOGIN_ERROR("2001", "管理员登录失败"),
    ADMIN_VERIFICATION_CODE_ERROR("2002", "验证码错误"),
    ;
    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    CmsErrorCodeEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
