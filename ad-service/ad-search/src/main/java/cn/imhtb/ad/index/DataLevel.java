package cn.imhtb.ad.index;

import lombok.Getter;

/**
 * 74
 * @author PinTeh
 * @date 2019/8/13
 */
@Getter
public enum DataLevel {

    LEVEL2("2","level2"),
    LEVEL3("3","level3"),
    LEVEL4("4","level4");

    private String level;
    private String desc;

    DataLevel(String level,String desc){
        this.level = level;
        this.desc = desc;
    }
}
