package cn.imhtb.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 53
 * @author PinTeh
 * @date 2019/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanTable {

    //和索引字段一一对应
    private Long id;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;

}
