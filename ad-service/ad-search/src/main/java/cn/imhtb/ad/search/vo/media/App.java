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
public class App {

    /**
     * App 编码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用的包名
     */
    private String packageName;

    /**
     * 应用请求页面名
     */
    private String activityName;


}