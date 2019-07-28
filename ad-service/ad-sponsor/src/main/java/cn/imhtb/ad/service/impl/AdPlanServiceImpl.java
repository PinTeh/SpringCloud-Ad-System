package cn.imhtb.ad.service.impl;

import cn.imhtb.ad.constant.CommonStatus;
import cn.imhtb.ad.constant.Constants;
import cn.imhtb.ad.dao.AdUserRepository;
import cn.imhtb.ad.entity.AdPlan;
import cn.imhtb.ad.entity.AdUser;
import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.IAdPlanService;
import cn.imhtb.ad.utils.CommonUtils;
import cn.imhtb.ad.vo.AdPlanGetRequest;
import cn.imhtb.ad.vo.AdPlanRequest;
import cn.imhtb.ad.vo.AdPlanResponse;
import cn.imhtb.ad.dao.AdPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        //请求参数判断
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //关联User是否存在
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        //判断名字是否相同
        AdPlan oldAdPlan = adPlanRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldAdPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan newAdPlan = adPlanRepository.save(new AdPlan(
                request.getUserId(), request.getPlanName(),
                CommonUtils.String2Date(request.getStarDate()),
                CommonUtils.String2Date(request.getEndDate())));
        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return adPlanRepository.findAllByIdAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if(!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(plan == null){
            throw  new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        //如果名字不为空则更新
        if(request.getPlanName() != null){
            plan.setPlanName(request.getPlanName());
        }

        //如果起始时间不为空则更新
        if(request.getStarDate() != null){
            plan.setStartDate(CommonUtils.String2Date(request.getStarDate()));
        }

        //如果结束时间不为空则更新
        if(request.getEndDate() != null){
            plan.setEndDate(CommonUtils.String2Date(request.getEndDate()));
        }

        plan.setUpdateTime(new Date());
        AdPlan save = adPlanRepository.save(plan);
        return  new AdPlanResponse(save.getId(), save.getPlanName());
    }

    @Override
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if(!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
    }
}
