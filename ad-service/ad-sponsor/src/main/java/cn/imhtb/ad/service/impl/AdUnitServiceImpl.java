package cn.imhtb.ad.service.impl;

import cn.imhtb.ad.constant.Constants;
import cn.imhtb.ad.dao.unit.AdUnitDistrictRepository;
import cn.imhtb.ad.dao.unit.AdUnitItRepository;
import cn.imhtb.ad.dao.unit.AdUnitKeywordRepository;
import cn.imhtb.ad.dao.unit.CreativeUnitRepository;
import cn.imhtb.ad.entity.AdPlan;
import cn.imhtb.ad.entity.AdUnit;
import cn.imhtb.ad.entity.unitCondition.AdUnitDistrict;
import cn.imhtb.ad.entity.unitCondition.AdUnitIt;
import cn.imhtb.ad.entity.unitCondition.AdUnitKeyword;
import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.service.IAdUnitService;
import cn.imhtb.ad.vo.*;
import cn.imhtb.ad.dao.AdPlanRepository;
import cn.imhtb.ad.dao.AdUnitRepository;
import cn.imhtb.ad.entity.unitCondition.CreativeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;

    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if(!request.creativeValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //根据id查找
        Optional<AdPlan> adPlan = adPlanRepository.findById(request.getPlanId());
        //判断是否存在，不存在抛出异常
        if(adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdUnit oldUnit = adUnitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (oldUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUnit newUnit = adUnitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(),
                request.getPositionType(), request.getBudget()));
        return new AdUnitResponse(newUnit.getId(), newUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExits(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> adUnitKeywords = new ArrayList<>();
        if(!CollectionUtils.isEmpty(request.getUnitKeywords())){
            request.getUnitKeywords().forEach(i -> adUnitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(),i.getKeyword())
            ));
            ids = adUnitKeywordRepository.saveAll(adUnitKeywords).stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExits(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(
                new AdUnitIt(i.getUnitId(),i.getItTag())
        ));

        List<Long> ids = adUnitItRepository.saveAll(unitIts).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());

        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExits(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> adUnitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(i -> adUnitDistricts.add(
                new AdUnitDistrict(i.getUnitId(), i.getProvince(), i.getCity())
        ));
        List<Long> ids = adUnitDistrictRepository.saveAll(adUnitDistricts).stream()
                .map(AdUnitDistrict::getId)
                .collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExits(unitIds) && !isRelatedCreativeExist(creativeIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(
                new CreativeUnit(i.getCreativeId(),i.getUnitId())
        ));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());
        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExits(List<Long> unitIds){
        //判断集合是否为空
        if(CollectionUtils.isEmpty(unitIds)){
            return  false;
        }
        //因为unitIds 可能有重复 所以用hashSet比较
        return adUnitRepository.findAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds){
        if(CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }

        return creativeUnitRepository.findAllById(creativeIds).size() ==
                new HashSet<>(creativeIds).size();
    }
}
