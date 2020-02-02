package com.hc.smsm_backer.modules.information.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.modules.information.entity.InformationEntity;
import com.hc.smsm_backer.modules.information.mapper.InformationMapper;
import com.hc.smsm_backer.modules.information.service.InformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, InformationEntity> implements InformationService {

    @Resource
    private InformationMapper informationMapper;


    @Override
    public List<InformationEntity> getInformationList(Integer id) {

        List<InformationEntity> informationEntityList = null;

        if (id == null) {
            informationEntityList = informationMapper.selectList(new EntityWrapper<InformationEntity>().orderBy("create_time", false));
        } else {
            informationEntityList = informationMapper.selectList(new EntityWrapper<InformationEntity>().eq("id", id).orderBy("create_time", false));
        }

        return informationEntityList;
    }

    @Override
    public void insertInformation(InformationEntity informationEntity) {
        Integer result = informationMapper.insertInformation(informationEntity);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public InformationEntity getInformationById(Integer informationId) {
        InformationEntity informationEntity = informationMapper.getInformationById(informationId);
        return informationEntity;
    }

    @Override
    public void removeInformationById(Integer informationId) {
        Integer result = informationMapper.removeInformationById(informationId);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public void updateInformation(InformationEntity informationEntity) {
        Integer result = informationMapper.updateById(informationEntity);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public void onlineInformationById(Integer informationId) {
        Integer result = informationMapper.onlineInformationById(informationId);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public void offlineInformationById(Integer informationId) {
        Integer result = informationMapper.offlineInformationById(informationId);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }
}
