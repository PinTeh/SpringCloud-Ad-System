package cn.imhtb.ad.index.district;

import cn.imhtb.ad.index.IndexAware;
import cn.imhtb.ad.search.feature.DistrictFeature;
import cn.imhtb.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * @author PinTeh
 * @date 2019/8/9
 */
@Slf4j
@Component
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {

    private static Map<String,Set<Long>> districtUnitMap;
    private static Map<Long, Set<String>> unitDistrictMap;
    static{
        districtUnitMap = new ConcurrentHashMap<>();
        unitDistrictMap = new ConcurrentHashMap<>();
    }

    @Override
    //这个key 是province-city 用连字符 作为key
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("[UnitDistrictIndex] -> before add {}",districtUnitMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key,districtUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIds.addAll(value);
        for(Long unitId: value){
            Set<String> districtSet = CommonUtils.getOrCreate(
                    unitId,unitDistrictMap,
                    ConcurrentSkipListSet::new
            );
            districtSet.add(key);
        }
        log.info("[UnitDistrictIndex] -> after add {}",districtUnitMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        //更新成本比较高
        log.error("district index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("[UnitDistrictIndex] -> before delete {}",districtUnitMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key,districtUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIds.removeAll(value);
        for(Long unitId : unitIds){
            Set<String> districts = CommonUtils.getOrCreate(
                    unitId,unitDistrictMap,
                    ConcurrentSkipListSet::new
            );
            districts.remove(key);
        }
        log.info("[UnitDistrictIndex] -> after delete {}",districtUnitMap);
    }

    public boolean match(Long adUnitId,
                         List<DistrictFeature.ProvinceAndCity> districts) {

        if (unitDistrictMap.containsKey(adUnitId) &&
                CollectionUtils.isNotEmpty(unitDistrictMap.get(adUnitId))) {

            Set<String> unitDistricts = unitDistrictMap.get(adUnitId);

            List<String> targetDistricts = districts.stream()
                    .map(
                            d -> CommonUtils.stringConcat(
                                    d.getProvince(), d.getCity()
                            )
                    ).collect(Collectors.toList());

            return CollectionUtils.isSubCollection(targetDistricts, unitDistricts);
        }

        return false;
    }

}
