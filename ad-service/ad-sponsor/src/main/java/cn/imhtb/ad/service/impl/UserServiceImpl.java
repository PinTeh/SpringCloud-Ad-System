package cn.imhtb.ad.service.impl;

import cn.imhtb.ad.constant.Constants;
import cn.imhtb.ad.dao.AdUserRepository;
import cn.imhtb.ad.entity.AdUser;
import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.IUserService;
import cn.imhtb.ad.utils.CommonUtils;
import cn.imhtb.ad.vo.CreateUserRequest;
import cn.imhtb.ad.vo.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {

        //验证传入的参数是否正确
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //判断名字是否与数据库重复
        AdUser oldUser = adUserRepository.findByUsername(request.getUsername());
        if(oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        //获取新的用户，获取token
        AdUser newUser = adUserRepository.save(new AdUser(
                request.getUsername(), CommonUtils.md5(request.getUsername())
        ));
        return new CreateUserResponse(newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime());
    }
}
