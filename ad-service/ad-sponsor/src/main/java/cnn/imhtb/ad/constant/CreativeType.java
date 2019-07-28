package cnn.imhtb.ad.constant;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public enum  CreativeType {
    /**
     * 图片
     */
    IMAGE(1, "图片"),
    /**
     * 视频
     */
    VIDEO(2, "视频"),
    /**
     * 文本
     */
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
