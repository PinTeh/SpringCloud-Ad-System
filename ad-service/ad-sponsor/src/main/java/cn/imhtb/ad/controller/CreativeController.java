package cn.imhtb.ad.controller;

import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.ICreativeService;
import cn.imhtb.ad.vo.CreativeRequest;
import cn.imhtb.ad.vo.CreativeResponse;
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
public class CreativeController {

    @Autowired
    private ICreativeService creativeService;

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(
            @RequestBody CreativeRequest request
    ) throws AdException {
        log.info("ad-sponsor: createCreative -> {}",
                JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }

}
