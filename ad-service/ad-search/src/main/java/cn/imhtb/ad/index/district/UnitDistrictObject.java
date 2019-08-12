package cn.imhtb.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 48
 * @author PinTeh
 * @date 2019/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;

    //<String, Set<Long>>
    // province-city 用连字符 作为key
    //Set<Long> 是unitId
}
