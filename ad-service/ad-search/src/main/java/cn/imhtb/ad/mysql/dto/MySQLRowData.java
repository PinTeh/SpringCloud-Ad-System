package cn.imhtb.ad.mysql.dto;

import cn.imhtb.ad.mysql.constant.OpType;
import javafx.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 71
 * @author PinTeh
 * @date 2019/8/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySQLRowData {

    //表名
    private String tableName;

    //层级业务
    private String level;

    //操作类型
    private OpType opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
