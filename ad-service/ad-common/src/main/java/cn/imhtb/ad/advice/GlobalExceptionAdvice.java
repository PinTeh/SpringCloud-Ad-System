package cn.imhtb.ad.advice;

import cn.imhtb.ad.exception.AdException;
import cn.imhtb.ad.vo.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
@RestControllerAdvice
public class GlobalExceptionAdvice  {

    @ExceptionHandler(value = AdException.class)
    public ServerResponse<String> handleAdException(HttpServletRequest request, AdException ex){
        ServerResponse<String> serverResponse = new ServerResponse<>();
        serverResponse.setCode(-1);
        serverResponse.setMsg("ad exception");
        serverResponse.setData(ex.getMessage());
        return serverResponse;
    }

}
