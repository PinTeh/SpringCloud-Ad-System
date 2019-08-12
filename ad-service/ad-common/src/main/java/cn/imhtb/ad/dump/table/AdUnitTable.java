package cn.imhtb.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PinTeh
 * @date 2019/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitTable {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

}
