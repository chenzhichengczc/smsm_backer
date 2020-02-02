package com.hc.smsm_backer.modules.information.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.smsm_backer.modules.information.entity.InformationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资讯信息表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 18:07:05
 */
public interface InformationMapper extends BaseMapper<InformationEntity> {

    public List<InformationEntity> getInformationList();


    public Integer insertInformation(InformationEntity informationEntity);


    public InformationEntity getInformationById(@Param(value = "informationId") Integer informationId);

    public Integer removeInformationById(@Param(value = "informationId") Integer informationId);

    public Integer onlineInformationById(@Param(value = "informationId") Integer informationId);

    public Integer offlineInformationById(@Param(value = "informationId") Integer informationId);
}
