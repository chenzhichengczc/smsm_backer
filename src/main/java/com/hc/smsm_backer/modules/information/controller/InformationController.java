package com.hc.smsm_backer.modules.information.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.modules.information.entity.InformationEntity;
import com.hc.smsm_backer.modules.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 资讯信息表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 18:07:05
 */
@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

    /**
     * 列表
     */
    @RequestMapping(value = "/information/list", method = RequestMethod.GET)
    public ResponseUtil getInformationList(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<InformationEntity> informationEntities = informationService.getInformationList();
        PageInfo<InformationEntity> pageInfo = new PageInfo<>(informationEntities);
        return ResponseUtil.success(pageInfo);
    }




    /**
    * 新增
    */
    @RequestMapping(value = "/information/insert", method = RequestMethod.POST)
    public ResponseUtil insertInformation(InformationEntity informationEntity){
        informationService.insertInformation(informationEntity);
        return ResponseUtil.success();
    }

    /**
    *  获取
    */
    @RequestMapping(value = "/information/getById", method = RequestMethod.GET)
    public ResponseUtil getInformationById(Integer informationId){
        InformationEntity informationEntity = informationService.getInformationById(informationId);
        return ResponseUtil.success(informationEntity);
    }

    /**
    * 删除
    */
    @RequestMapping(value = "/information/delete", method = RequestMethod.POST)
    public ResponseUtil removeInformationById(Integer informationId){
        informationService.removeInformationById(informationId);
        return ResponseUtil.success();
    }

    /**
    * 更新
    */
    @RequestMapping(value = "/information/update", method = RequestMethod.POST)
    public ResponseUtil updateInformation(InformationEntity informationEntity){
        informationService.updateInformation(informationEntity);
        return ResponseUtil.success();
    }

}
