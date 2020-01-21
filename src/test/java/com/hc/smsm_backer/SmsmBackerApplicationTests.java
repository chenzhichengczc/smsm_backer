package com.hc.smsm_backer;

import com.hc.smsm_backer.modules.user.entity.UserEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationPO;
import com.hc.smsm_backer.modules.userapplication.service.UserApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SmsmBackerApplicationTests {

    @Resource
    private UserApplicationService userApplicationService;

    @Test
    void contextLoads() {
        UserEntity userEntity = new UserEntity();
        List<UserApplicationPO> userApplication = userApplicationService.getUserApplication(userEntity);
        System.out.println("userApplication = " + userApplication);
    }

}
