package cn.imhtb.ad.search.vo;

import cn.imhtb.ad.search.feature.DistrictFeature;
import cn.imhtb.ad.search.feature.FeatureRelation;
import cn.imhtb.ad.search.feature.ItFeature;
import cn.imhtb.ad.search.feature.KeywordFeature;
import cn.imhtb.ad.search.vo.media.AdSlot;
import cn.imhtb.ad.search.vo.media.App;
import cn.imhtb.ad.search.vo.media.Device;
import cn.imhtb.ad.search.vo.media.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 80
 * @author PinTeh
 * @date 2019/8/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    /**
     * 媒体方请求标识
     */
    private String mediaId;

    /**
     * 请求基本信息
     */
    private RequestInfo requestInfo;

    /**
     * 请求匹配信息
     */
    private FeatureInfo featureInfo;

    /**
     * 一次请求 携带的基本信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo {
        /**
         * 请求的唯一 id
         */
        private String requestId;

        /**
         * 广告位, 可以一次请求多个广告位
         */
        private List<AdSlot> adSlots;

        /**
         * app 信息
         */
        private App app;

        /**
         * 地理位置信息
         */
        private Geo geo;

        /**
         * 设备信息
         */
        private Device device;
    }

    /**
     * 一次请求 携带的匹配信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeatureInfo {
        private KeywordFeature keywordFeature;
        private ItFeature itFeature;
        private DistrictFeature districtFeature;

        /**
         * 默认为 AND
         */
        private FeatureRelation featureRelation = FeatureRelation.AND;
    }
}
