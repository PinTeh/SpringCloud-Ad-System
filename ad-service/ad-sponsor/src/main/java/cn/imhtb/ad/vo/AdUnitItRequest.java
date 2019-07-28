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
public class AdUnitItRequest {

    private List<UnitIt> unitIts;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitIt{
        private Long unitId;
        private String itTag;
    }
}
