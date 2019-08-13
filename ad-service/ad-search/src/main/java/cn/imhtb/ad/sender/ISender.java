package cn.imhtb.ad.sender;

import cn.imhtb.ad.mysql.dto.MySQLRowData;

/**
 * @author PinTeh
 * @date 2019/8/13
 */
public interface ISender {

    void sender(MySQLRowData rowData);
}