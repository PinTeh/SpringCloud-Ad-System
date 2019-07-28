package cnn.imhtb.ad.dao.unit;

import cnn.imhtb.ad.entity.unitCondition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface AdUnitKeywordRepository extends JpaRepository<AdUnitKeyword,Long> {
}
