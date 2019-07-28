package cn.imhtb.ad.dao;

import cn.imhtb.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface AdUnitRepository extends JpaRepository<AdUnit,Long> {

    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findByUnitStatus(Integer unitStatus);
}
