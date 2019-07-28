package cn.imhtb.ad.constant;

import lombok.Getter;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Getter
public enum CommonStatus {
    /**
     * 用户、推广计划有效状态
     */
    VALID(1, "有效状态"),
    /**
     * 用户、推广计划无效状态
     */
    INVALID(0, "无效状态");

    private int status;
    private String msg;

    CommonStatus(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
}
