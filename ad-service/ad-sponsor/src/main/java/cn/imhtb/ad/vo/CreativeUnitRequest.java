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
public class CreativeUnitRequest {

    private List<CreativeUnitItem> unitItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreativeUnitItem {

        private Long creativeId;
        private Long unitId;
    }
}
