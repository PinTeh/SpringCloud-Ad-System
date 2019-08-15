package cn.imhtb.ad.search;

import cn.imhtb.ad.search.vo.SearchRequest;
import cn.imhtb.ad.search.vo.SearchResponse;

/**
 * 80
 * @author PinTeh
 * @date 2019/8/15
 */
public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);

}
