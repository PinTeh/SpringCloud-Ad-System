package cn.imhtb.ad.service;

import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.vo.CreateUserRequest;
import cn.imhtb.ad.vo.CreateUserResponse;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface IUserService {

    CreateUserResponse createUser (CreateUserRequest request) throws AdException;

}
