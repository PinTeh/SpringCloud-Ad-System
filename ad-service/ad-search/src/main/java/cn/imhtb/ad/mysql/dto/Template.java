package cn.imhtb.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 65
 * @author PinTeh
 * @date 2019/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {

    private String databases;
    private List<JsonTable> tableList;
}
