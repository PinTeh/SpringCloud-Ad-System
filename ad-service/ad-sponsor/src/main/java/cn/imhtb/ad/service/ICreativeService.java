package cn.imhtb.ad.service;

import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.vo.CreativeRequest;
import cn.imhtb.ad.vo.CreativeResponse;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request)throws AdException;
}
