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
public class AdCreativeTable {

    private Long adId;
    private String name;
    private Integer type;
    //类型
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;
}