package com.trademark.cms.dao.mapper;

import com.trademark.cms.dao.entity.CmsMenuBase;

import java.util.List;

public interface CmsMenuBaseMapper extends IBaseMapper<CmsMenuBase> {
    List<CmsMenuBase> queryMenuAll();
}