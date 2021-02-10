package com.trademark.cms.service.account;

import com.trademark.cms.dao.entity.CmsMenuBase;

import java.util.List;

public interface ICmsMenuService {
    List<CmsMenuBase> queryMenuAll();
}
