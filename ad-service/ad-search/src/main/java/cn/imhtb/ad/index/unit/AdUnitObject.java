package cn.imhtb.ad.index.unit;

import cn.imhtb.ad.index.plan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 82
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


    private static boolean isKaiPing(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.KAIPING) > 0;
    }

    private static boolean isTiePian(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN) > 0;
    }

    private static boolean isTiePianMiddle(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE) > 0;
    }

    private static boolean isTiePianPause(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE) > 0;
    }

    private static boolean isTiePianPost(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_POST) > 0;
    }



    public static boolean isAdSlotTypeOk(int adSlotType, int positionType) {
        switch (adSlotType) {
            case AdUnitConstants.POSITION_TYPE.KAIPING:
                return isKaiPing(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN:
                return isTiePian(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE:
                return isTiePianMiddle(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE:
                return isTiePianPause(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_POST:
                return isTiePianPost(positionType);
            default:
                return false;
        }
    }
}
