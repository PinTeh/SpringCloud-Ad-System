package cn.imhtb.ad.service;

import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.vo.*;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface IAdUnitService {

    /**
     * 创建广告单元
     */
    AdUnitResponse createUnit (AdUnitRequest request ) throws AdException;

    /**
     * 创建广告单元关键字
     */
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    /**
     *  创建广告单元兴趣制度
     */
    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    /**
     *  创建广告单元地域维度
     */
    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)throws  AdException;

    /**
     *  创建创意单元
     */
    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request)
            throws AdException;
}
