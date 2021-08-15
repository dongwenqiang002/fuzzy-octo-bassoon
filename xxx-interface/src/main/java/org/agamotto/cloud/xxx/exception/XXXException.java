package org.agamotto.cloud.xxx.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author
 * @Time 2019/6/2 21:24
 */
public class XXXException extends RuntimeException {



    protected HttpStatus code;

    public XXXException(){

    }
    public XXXException(String message,Integer code){
        super(message);
        this.code = HttpStatus.valueOf(code);
    }

    public XXXException(String message,HttpStatus code){
        super(message);
        this.code = code;
    }


    public XXXException(String message){
        super(message);
    }

    public Integer getCode(){
        return code.value();
    }

}
