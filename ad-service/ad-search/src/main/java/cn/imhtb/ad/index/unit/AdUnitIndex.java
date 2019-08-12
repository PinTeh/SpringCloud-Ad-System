package cn.imhtb.ad.index.unit;

import cn.imhtb.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
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
