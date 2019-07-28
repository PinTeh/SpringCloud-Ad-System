package cn.imhtb.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    /**
     * 位置类型
     */
    private Integer positionType;

    /**
     *  预算
     */
    private Long budget;

    public boolean creativeValidate(){
        return planId != null && !StringUtils.isEmpty(unitName) && positionType != null && budget != null;
    }
}
