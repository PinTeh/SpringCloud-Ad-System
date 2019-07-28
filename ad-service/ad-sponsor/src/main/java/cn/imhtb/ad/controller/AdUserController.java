package cn.imhtb.ad.controller;

import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.IUserService;
import cn.imhtb.ad.vo.CreateUserRequest;
import cn.imhtb.ad.vo.CreateUserResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Slf4j
@RestController
public class AdUserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/create/user")
    public CreateUserResponse creativeUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("[createUser] -> /create/user");
        JSON.toJSONString(request);
        return userService.createUser(request);
    }

}
