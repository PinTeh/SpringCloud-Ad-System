package cn.imhtb.ad.client;

import cn.imhtb.ad.client.vo.AdPlan;
import cn.imhtb.ad.client.vo.AdPlanGetRequest;
import cn.imhtb.ad.vo.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@FeignClient(name = "eureka-client-ad-sponsor",fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping("/ad-sponsor/get/AdPlan")
    ServerResponse<List<AdPlan>> getAdPlan(@RequestBody AdPlanGetRequest request);

}
