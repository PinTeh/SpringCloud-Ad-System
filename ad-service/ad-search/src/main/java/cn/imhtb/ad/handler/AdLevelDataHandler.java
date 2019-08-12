package cn.imhtb.ad.handler;


import cn.imhtb.ad.dump.table.*;
import cn.imhtb.ad.index.DataTable;
import cn.imhtb.ad.index.IndexAware;
import cn.imhtb.ad.index.creative.CreativeIndex;
import cn.imhtb.ad.index.creative.CreativeObject;
import cn.imhtb.ad.index.creativeunit.CreativeUnitIndex;
import cn.imhtb.ad.index.creativeunit.CreativeUnitObject;
import cn.imhtb.ad.index.district.UnitDistrictIndex;
import cn.imhtb.ad.index.interest.UnitItIndex;
import cn.imhtb.ad.index.keyword.UnitKeywordIndex;
import cn.imhtb.ad.index.plan.AdPlanIndex;
import cn.imhtb.ad.index.plan.AdPlanObject;
import cn.imhtb.ad.index.unit.AdUnitIndex;
import cn.imhtb.ad.index.unit.AdUnitObject;
import cn.imhtb.ad.mysql.constant.OpType;
import cn.imhtb.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 57 58 59 60
 *  * 1 索引之间存在着层级的划分，也就是以来关系的划分
 *  * 2 加载全量索引 其实就是增量索引  ”添加“ 的一种特殊关系
 * @author PinTeh
 * @date 2019/8/9
 */
@Slf4j
public class AdLevelDataHandler {

    public static void handleLevel2(AdPlanTable adPlanTable, OpType type) {
        AdPlanObject adPlanObject = new AdPlanObject(
                adPlanTable.getId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate()
        );
        handleBinLogEvent(
                DataTable.of(AdPlanIndex.class),
                adPlanObject.getPlanId(),
                adPlanObject,
                type
        );
    }

    public static void handleLevel2(AdCreativeTable adCreativeTable, OpType type){
        CreativeObject creativeObject = new CreativeObject(
                adCreativeTable.getAdId(),
                adCreativeTable.getName(),
                adCreativeTable.getType(),
                adCreativeTable.getMaterialType(),
                adCreativeTable.getHeight(),
                adCreativeTable.getWidth(),
                adCreativeTable.getAuditStatus(),
                adCreativeTable.getAdUrl()
        );
        handleBinLogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    public static void handleLevel3(AdUnitTable adUnitTable, OpType type){
        //在AdUnitIndex中有PlanId
        AdPlanObject adPlanObject = DataTable.of(
                AdPlanIndex.class
        ).get(adUnitTable.getPlanId());
        if(null == adPlanObject){
            log.error("handleLevel3 found AdPlanObject error");
            return;
        }
        AdUnitObject unitObject = new AdUnitObject(
                adUnitTable.getUnitId(),
                adUnitTable.getUnitStatus(),
                adUnitTable.getPositionType(),
                adUnitTable.getPlanId(),
                adPlanObject
        );
        handleBinLogEvent(
                DataTable.of(AdUnitIndex.class),
                unitObject.getUnitId(),
                unitObject,
                type
        );
    }

    public static void handleLevel3(AdCreativeUnitTable adCreativeUnitTable, OpType type){
        //这个不支持更新
        if(type == OpType.UPDATE){
            log.error("CreativeUnitIndex not support update");
            return;
        }

        //在AdUnitIndex中有PlanId
        AdUnitObject adUnitObject = DataTable.of(
                AdUnitIndex.class
        ).get(adCreativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(
                CreativeIndex.class
        ).get(adCreativeUnitTable.getAdId());
        if(null == adUnitObject || null == creativeObject){
            log.error("AdCreativeUnitTable index error");
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                adCreativeUnitTable.getAdId(),
                adCreativeUnitTable.getUnitId()
        );
        handleBinLogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(
                        creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()
                ),
                creativeUnitObject,
                type
        );
    }

    public static void handleLevel4(AdUnitDistrictTable adUnitDistrictTable, OpType type){

        //首先地域纬度不支持更新
        if(type == OpType.UPDATE){
            log.error("district index can not support update");
            return;
        }

        //获取当前系统的索引对象
        AdUnitObject adUnitObject = DataTable.of(
                AdUnitIndex.class
        ).get(adUnitDistrictTable.getUnitId());
        //如果索引不存在，不需要创建
        if(adUnitObject == null){
            log.error("AdUnitDistrictTable index error: {}",adUnitDistrictTable.getUnitId());
            return;
        }

        //构造Key  省和市用了连字符
        String key = CommonUtils.stringConcat(
                adUnitDistrictTable.getProvince(),
                adUnitDistrictTable.getCity()
        );
        Set<Long> value = new HashSet<>(
                Collections.singleton(adUnitDistrictTable.getUnitId())
        );
        handleBinLogEvent(
                DataTable.of(UnitDistrictIndex.class),
                key, value,
                type);

    }

    public static void handleLevel4(AdUnitItTable adUnitItTable,OpType type){

        //兴趣纬度不支持更新
        if(type  == OpType.UPDATE){
            log.error("it index can not support update");
            return;
        }

        //获取当前所有对象
        AdUnitObject adUnitObject = DataTable.of(
                AdUnitIndex.class
        ).get(adUnitItTable.getUnitId());
        //如果所有不存在，不需要创建
        if(adUnitObject == null){
            log.error("adUnitTable index error: {}",adUnitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(adUnitItTable.getUnitId())
        );
        handleBinLogEvent(
                DataTable.of(UnitItIndex.class),
                adUnitItTable.getItTag(),
                value,
                type
        );

    }

    public static void handleLevel4(AdUnitKeywordTable adUnitKeywordTable, OpType type){

        if(type == OpType.UPDATE){
            log.error("keyword index can not support update");
            return;
        }

        //获取当前对象
        AdUnitObject adUnitObject = DataTable.of(
                AdUnitIndex.class
        ).get(adUnitKeywordTable.getUnitId());
        //如果不存在，不需要创造
        if(adUnitObject.getUnitId() == null){
            log.error("AdUnitKeywordTable index error: {}",adUnitKeywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(adUnitKeywordTable.getUnitId())
        );

        handleBinLogEvent(
                DataTable.of(UnitKeywordIndex.class),
                adUnitKeywordTable.getKeyword(),
                value,
                type
        );

    }

    //通用方法处理全量索引，和增量索引，监听binlog
    private static <K, V> void handleBinLogEvent(
            IndexAware<K, V> index,
            K key,
            V value,
            OpType type) {
        switch (type) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }
}
