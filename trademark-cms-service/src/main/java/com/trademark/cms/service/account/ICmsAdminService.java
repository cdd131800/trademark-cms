package com.trademark.cms.service.account;

import com.trademark.cms.dao.vo.CmsAdminBaseVo;

public interface ICmsAdminService {
    CmsAdminBaseVo findAdminByAccount(String adminAccount);
}
