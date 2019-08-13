package cn.imhtb.ad.mysql.dto;

import cn.imhtb.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 65 方便读取表的一些信息
 * @author PinTeh
 * @date 2019/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    /**
     * 字段索引[0,1,2,3...] -> 字段名
     */
    private Map<Integer,String> posMap = new HashMap<>();
}
