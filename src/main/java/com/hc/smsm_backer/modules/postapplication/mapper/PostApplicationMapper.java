package com.hc.smsm_backer.modules.postapplication.mapper;

import com.hc.smsm_backer.modules.postapplication.entity.PostApplicationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * 申请岗位表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 19:05:56
 */
public interface PostApplicationMapper extends BaseMapper<PostApplicationEntity> {

    public List<PostApplicationEntity> getPostApplicationList(@Param(value = "postName") String postName,
                                                              @Param(value = "hireDepartment") String hireDepartment,
                                                              @Param(value = "recruitment") String recruitment,
                                                              @Param(value = "educationRequirement") String educationRequirement,
                                                              @Param(value = "major") String major);


    public Integer insertPostApplication(PostApplicationEntity postApplicationEntity);


    public PostApplicationEntity getPostApplicationById(@Param(value = "postApplicationId") Integer postApplicationId);

    public Integer removePostApplicationById(@Param(value = "postApplicationId") Integer postApplicationId);

    public List<String> selectPostName();

    public List<String> selectHireDepartment();

    public List<String> selectRecruitment();

    public List<String> selectEducationRequirement();

    public List<String> selectMajor();
}
