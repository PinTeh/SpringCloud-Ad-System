package cn.imhtb.ad.index.unit;

import cn.imhtb.ad.index.IndexAware;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author PinTeh
 * @date 2019/7/29
 */
@Slf4j
@Component
public class AdUnitIndex implements IndexAware<Long,AdUnitObject> {

    private static Map<Long,AdUnitObject> map;
    static {
        map = new ConcurrentHashMap<>();
    }

    /**
     * 82
     * 匹配某一流量类型的 广告单元, 返回单元id 集合
     * @param positionType
     * @return
     */
    public Set<Long> match(int positionType) {
        Set<Long> adUnitIds = Sets.newHashSet();

        map.forEach((k,v)->{
            if (AdUnitObject.isAdSlotTypeOk(positionType,v.getPositionType())){
                adUnitIds.add(k);
            }
        });

        return adUnitIds;
    }

    public List<AdUnitObject> fetch(Collection<Long> adUnitIds) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return Collections.emptyList();
        }
        List<AdUnitObject> unitObjects = Lists.newArrayList();
        adUnitIds.forEach(unitId -> {
            AdUnitObject unitObject = get(unitId);
            if (unitObject == null) {
                log.error("adUnitObject not found: {}", unitId);
                return;
            }
            unitObjects.add(unitObject);
        });
        return unitObjects;
    }

    @Override
    public AdUnitObject get(Long key) {
        return map.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        log.info("[AdUnitIndex]-> before add {}",map );
        map.put(key,value);
        log.info("[AdUnitIndex]-> after add {}",map );
    }

    @Override
    public void update(Long key, AdUnitObject value) {

        log.info("[AdUnitIndex] -> before update {}",map);
        AdUnitObject oldObject = map.get(key);
        if(null == oldObject){
            map.put(key,value);
        }else {
            oldObject.update(value);
        }
        log.info("[AdUnitIndex] -> after update {}",map);
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        log.info("[AdUnitIndex] -> before delete {}",map);
        map.remove(key);
        log.info("[AdUnitIndex] -> after delete {}",map);
    }
}
