package org.agamotto.cloud.xxx.core.config;

import lombok.extern.slf4j.Slf4j;
import org.agamotto.cloud.xxx.exception.xxxException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 〈统一异常处理〉
 *
 * @author ajianzheng
 * @date 2019/1/18
 */

@Slf4j
@Component
public class GlobalExceptionHandle extends DefaultErrorWebExceptionHandler {


    /**
     *
     */
    public GlobalExceptionHandle(ErrorAttributes errorAttributes,
                                 ResourceProperties resourceProperties,
                                 ServerProperties serverProperties,
                                 ApplicationContext applicationContext,
                                 ServerCodecConfigurer serverCodecConfigurer,
                                 ObjectProvider<ViewResolver> viewResolversProvider) {
        super(errorAttributes, resourceProperties, serverProperties.getError(), applicationContext);

        this.setViewResolvers(viewResolversProvider.orderedStream()
                .collect(Collectors.toList()));
        this.setMessageWriters(serverCodecConfigurer.getWriters());
        this.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    /**
     * 获取异常属性
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

        Throwable error = super.getError(request);
        if (error instanceof xxxException) {
            return response(((xxxException) error).getCode(), this.buildMessage(request, error.getMessage()));
        }
        return response(500, this.buildMessage(request, "服务器内部异常"));
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     * @param errorAttributes a
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus
     * @param errorAttributes a
     */
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.remove("code");
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 构建异常信息
     * @param request
     * @param msg
     * @return
     */
    private String buildMessage(ServerRequest request, String msg) {
        //StringBuilder message = new StringBuilder(msg+" [");
       // message.append(request.methodName());
       // message.append(" ");
       // message.append(request.uri());
       // message.append(" ]");
        return msg;
    }

    /**
     * 构建返回的JSON数据格式
     * @param status        状态码
     * @param errorMessage  异常信息
     * @return a
     */
    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", status);
        map.put("message", errorMessage);
        return map;
    }


}