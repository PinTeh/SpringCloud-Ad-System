package cn.imhtb.ad.dao;


import cn.imhtb.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
public interface AdUserRepository extends JpaRepository<AdUser,Long> {

    AdUser findByUsername(String username);

}
