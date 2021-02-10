package com.trademark.cms.service.account.impl;

import com.trademark.cms.common.enums.CmsErrorCodeEnum;
import com.trademark.cms.common.exception.BizException;
import com.trademark.cms.dao.mapper.CmsAdminBaseMapper;
import com.trademark.cms.dao.vo.CmsAdminBaseVo;
import com.trademark.cms.service.account.ICmsAdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsAdminServiceImpl implements ICmsAdminService {
    @NonNull
    private CmsAdminBaseMapper adminBaseMapper;

    @Override
    public CmsAdminBaseVo findAdminByAccount(String adminAccount) {

        if (adminAccount.length() > 50) {
            throw new BizException(CmsErrorCodeEnum.REQUEST_PARAM_LENGTH_ERROR);
        }
        return adminBaseMapper.findAdminByAccount (adminAccount);
    }
}
