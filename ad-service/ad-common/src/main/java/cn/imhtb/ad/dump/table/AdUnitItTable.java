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
public class AdUnitItTable {
    private Long unitId;
    private String itTag;
}
