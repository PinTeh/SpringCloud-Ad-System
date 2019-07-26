package cn.imhtb.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerResponse<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public ServerResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
