package cnn.imhtb.ad.dao;


import cnn.imhtb.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
public interface UserRepostory extends JpaRepository<AdUser,Long> {

    AdUser findByUsername(String username);

}
