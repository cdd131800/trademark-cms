package com.trademark.cms.controller;

import com.trademark.cms.controller.shiro.CmsShiroConstant;
import com.trademark.cms.dao.vo.CmsAdminBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CmsBaseController {

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public CmsAdminBaseVo getCurrentAdmin() {
        CmsAdminBaseVo adminBaseVo = (CmsAdminBaseVo) SecurityUtils.getSubject().getSession().getAttribute(CmsShiroConstant.CURRENT_ADMIN);
        return adminBaseVo;
    }
}
