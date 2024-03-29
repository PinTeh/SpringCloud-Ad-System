package cn.imhtb.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PinTeh
 * @date 2019/8/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    /**
     * 设备id
     */
    private String deviceCode;

    /**
     * 设备 mac 地址
     */
    private String mac;

    /**
     * 设备 ip
     */
    private String ip;

    /**
     * 机型编码
     */
    private String model;

    /**
     * 屏幕分辨率
     */
    private String displaySize;

    /**
     * 屏幕尺寸
     */
    private String screenSize;

    /**
     * 设备序列号
     */
    private String serialName;
}
