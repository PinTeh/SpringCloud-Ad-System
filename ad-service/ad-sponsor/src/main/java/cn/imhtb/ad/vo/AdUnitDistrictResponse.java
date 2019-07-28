package cn.imhtb.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictResponse {
    private List<Long> ids;
}
