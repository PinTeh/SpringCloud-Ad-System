package cnn.imhtb.ad.constant;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public enum CreativeMaterialType {
    /**
     * jpg图片
     */
    JPG(1, "jpg"),
    /**
     * bmp图片
     */
    BMP(2, "bmp"),

    /**
     * mp4视频
     */
    MP4(3, "mp4"),
    /**
     * avi视频
     */
    AVI(4, "avi"),

    /**
     * txt文本
     */
    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
