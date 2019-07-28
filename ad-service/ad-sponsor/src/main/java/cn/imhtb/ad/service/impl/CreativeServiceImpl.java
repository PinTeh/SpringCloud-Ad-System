package cn.imhtb.ad.service.impl;

import cn.imhtb.ad.dao.CreativeRepository;
import cn.imhtb.ad.entity.Creative;
import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.ICreativeService;
import cn.imhtb.ad.vo.CreativeRequest;
import cn.imhtb.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepository creativeRepository;


    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        //其他方法
        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
