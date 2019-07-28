package cn.imhtb.ad.controller;

import cn.imhtb.ad.annotation.IgnoreResponseAdvice;
import cn.imhtb.ad.client.SponsorClient;
import cn.imhtb.ad.client.vo.AdPlan;
import cn.imhtb.ad.client.vo.AdPlanGetRequest;
import cn.imhtb.ad.vo.ServerResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Slf4j
@RestController
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SponsorClient sponsorClient;


    @IgnoreResponseAdvice
    @GetMapping("/getAdPlanByFeign")
    public ServerResponse<List<AdPlan>> getAdPlanByFeign(@RequestBody AdPlanGetRequest request){
        log.info("[SearchController] -> getAdPlanByFeign ",JSON.toJSON(request));

        return sponsorClient.getAdPlan(request);
    }


    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlanByRibbon")
    public ServerResponse<List<AdPlan>> getAdPlanByRibbon(@RequestBody AdPlanGetRequest request){
        log.info("[SearchController] -> getAdPlanByRibbon", JSON.toJSONString(request));
        return restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan",request,
                ServerResponse.class
        ).getBody();
    }

}
