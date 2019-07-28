package cn.imhtb.ad.service;

import cn.imhtb.ad.entity.AdPlan;
import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.vo.AdPlanGetRequest;
import cn.imhtb.ad.vo.AdPlanResponse;
import cn.imhtb.ad.vo.AdPlanRequest;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface IAdPlanService {

    /**创建推广计划*/
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**批量获取*/
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**更新广告计划*/
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws  AdException;

    /**删除广告计划，没有返回值
     * */
    void deleteAdPlan (AdPlanRequest request) throws  AdException;
}
