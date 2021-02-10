package com.trademark.cms.dao.mapper;

import com.trademark.cms.dao.entity.CmsAdminBase;
import com.trademark.cms.dao.vo.CmsAdminBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CmsAdminBaseMapper extends IBaseMapper<CmsAdminBase> {

    /**
     * 根据adminAccount查询
     */
    CmsAdminBaseVo findAdminByAccount(@Param("adminAccount") String adminAccount );

    /**
     * 根据adminId查询
     */
    CmsAdminBaseVo findAdminById(Integer adminId);
}