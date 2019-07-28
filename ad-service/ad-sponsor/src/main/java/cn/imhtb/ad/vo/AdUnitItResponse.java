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
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitItResponse {
    private List<Long> ids;
}
