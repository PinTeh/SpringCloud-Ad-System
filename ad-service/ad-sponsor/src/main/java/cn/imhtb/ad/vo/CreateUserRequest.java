package cn.imhtb.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String username;

    /**如果不为空就会返回 ->true,,否则返回false*/
    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }
}
