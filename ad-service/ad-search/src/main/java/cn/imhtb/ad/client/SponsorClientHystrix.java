package cn.imhtb.ad.client;

import cn.imhtb.ad.client.vo.AdPlan;
import cn.imhtb.ad.client.vo.AdPlanGetRequest;
import cn.imhtb.ad.vo.ServerResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Component
public class SponsorClientHystrix implements SponsorClient{
    @Override
    public ServerResponse<List<AdPlan>> getAdPlan(AdPlanGetRequest request) {
        return new ServerResponse<>(-1,"eureka-client-ad-sponsor error");
    }
}
