package cn.imhtb.ad.advice;

import cn.imhtb.ad.annotation.IgnoreResponseAdvice;
import cn.imhtb.ad.vo.ServerResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
@RestControllerAdvice
public class ServerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 类上注解
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        // 方法上注解
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        ServerResponse<Object> serverResponse = new ServerResponse<>(0,"ex");
        if (null==o){
            return serverResponse;
        }else if (o instanceof ServerResponse){
            serverResponse = (ServerResponse<Object>) o;
        }else{
            serverResponse.setData(o);
        }
        return serverResponse;
    }
}
