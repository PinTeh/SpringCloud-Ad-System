package cn.imhtb.ad.index.unit;

import cn.imhtb.ad.index.plan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PinTeh
 * @date 2019/7/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {
    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    /**
     *   为了方便获取索引对象
     */
    private AdPlanObject adPlanObject;

    public void update(AdUnitObject newUnitObject){
        if(null != newUnitObject.getUnitId()){
            this.unitId = newUnitObject.getUnitId();
        }
        if(null != newUnitObject.getPlanId()){
            this.planId = newUnitObject.getPlanId();
        }
        if(null != newUnitObject.getPositionType()){
            this.positionType = newUnitObject.getPositionType();
        }
        if(null != newUnitObject.getUnitStatus()){
            this.unitStatus = newUnitObject.getUnitStatus();
        }
        if(null != newUnitObject.getAdPlanObject()){
            this.adPlanObject = newUnitObject.getAdPlanObject();
        }
    }
}
