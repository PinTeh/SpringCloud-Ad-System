package cn.imhtb.ad.mysql.listener;

import cn.imhtb.ad.mysql.dto.BinlogRowData;

/**
 * 68
 * @author PinTeh
 * @date 2019/8/12
 */
public interface IListener {

    //不同的表可以定义不同的更新方法
    void register();

    //实现增量索引的更新
    void onEvent(BinlogRowData eventData);

}
