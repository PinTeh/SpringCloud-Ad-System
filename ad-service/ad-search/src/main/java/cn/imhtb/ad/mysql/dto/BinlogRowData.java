package cn.imhtb.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 68 Event类实体对象
 * @author PinTeh
 * @date 2019/8/12
 */
@Data
public class BinlogRowData {

    private TableTemplate table;
    private EventType eventType;
    //List中包含的是Map，map的k 是操作的名字，value是操作列的值
    private List<Map<String,String>> after;
    private List<Map<String,String>> before;
}
