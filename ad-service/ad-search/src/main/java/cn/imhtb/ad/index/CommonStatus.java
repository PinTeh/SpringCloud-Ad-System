package cn.imhtb.ad.index;

import lombok.Getter;

/**
 * @author PinTeh
 * @date 2019/8/15
 */
@Getter
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
