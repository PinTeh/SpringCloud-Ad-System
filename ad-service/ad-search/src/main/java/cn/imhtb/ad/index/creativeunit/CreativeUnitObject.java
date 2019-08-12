package cn.imhtb.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 50
 * @author PinTeh
 * @date 2019/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUnitObject {

    private Long adId;
    private Long unitId;

    //key 作为adId-unitId

}
