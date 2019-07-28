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
public class AdPlanRequest {

    private Long id;
    private Long userId;
    private String planName;
    private String starDate;
    private String endDate;

    /**如果不为空就会返回 ->true,,否则返回false*/
    public boolean createValidate(){
        return userId != null && !StringUtils.isEmpty(planName)
                &&  !StringUtils.isEmpty(starDate) && !StringUtils.isEmpty(endDate);
    }

    public  boolean updateValidate(){
        return id != null && userId !=null;
    }

    public boolean deleteValidate(){
        return id != null && userId !=null;
    }
}
