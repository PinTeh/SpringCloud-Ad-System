package cnn.imhtb.ad.dao;

import cnn.imhtb.ad.entity.Creative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public interface CreativeRepository extends JpaRepository<Creative,Long> {
}
