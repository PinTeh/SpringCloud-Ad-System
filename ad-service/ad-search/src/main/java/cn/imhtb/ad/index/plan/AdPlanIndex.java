package cn.imhtb.ad.index.plan;

import cn.imhtb.ad.index.IndexAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author PinTeh
 * @date 2019/7/29
 */
@Component
public class AdPlanIndex implements IndexAware<Long,AdPlanObject> {

    private static Map<Long,AdPlanObject> map;
    static {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return map.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        map.put(key,value);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        AdPlanObject adPlanObject = map.get(key);
        if(adPlanObject == null){
            map.put(key, value);
        }else {
            // ?
            adPlanObject.update(value);
        }
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        map.remove(key);
    }
}
