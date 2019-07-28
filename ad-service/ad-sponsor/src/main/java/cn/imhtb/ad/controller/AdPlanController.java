package cn.imhtb.ad.controller;

import cn.imhtb.ad.entity.AdPlan;
import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.IAdPlanService;
import cn.imhtb.ad.vo.AdPlanGetRequest;
import cn.imhtb.ad.vo.AdPlanRequest;
import cn.imhtb.ad.vo.AdPlanResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Slf4j
@RestController
public class AdPlanController {

    @Autowired
    private IAdPlanService adPlanService;

    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan (@RequestBody AdPlanRequest request) throws AdException {
        log.info("[AdPlanOPController] -> createAdPlan",JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }


    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlan(@RequestBody AdPlanGetRequest request)throws AdException {
        log.info("[AdPlanOPController] -> getAdPlan",JSON.toJSONString(request));

        return adPlanService.getAdPlanByIds(request);
    }

    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("[AdPlanOPController] -> updateAdPlan",JSON.toJSONString(request));

        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(
            @RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: deleteAdPlan -> {}",
                JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }
}
