package cnn.imhtb.ad.dao.unit;

import cnn.imhtb.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface AdUnitItRepository extends JpaRepository<AdUnit,Long> {
}
