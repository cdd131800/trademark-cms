package com.trademark.cms.service.account.impl;

import com.trademark.cms.dao.entity.CmsMenuBase;
import com.trademark.cms.dao.mapper.CmsMenuBaseMapper;
import com.trademark.cms.service.account.ICmsMenuService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsMenuServiceImpl implements ICmsMenuService {

    @NonNull
    CmsMenuBaseMapper menuBaseMapper;

    @Override
    public List<CmsMenuBase> queryMenuAll() {
        return menuBaseMapper.queryMenuAll();
    }
}
