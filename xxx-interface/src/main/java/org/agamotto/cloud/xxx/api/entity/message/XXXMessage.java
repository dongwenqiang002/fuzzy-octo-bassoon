package org.agamotto.cloud.xxx.api.entity.message;

import lombok.Getter;

/**
 * 获取消息列表
 */
@Getter
public enum XXXMessage {

    logoutOk("logout_ok"),
    logoutError("logout_error"),;


    private String message;


    XXXMessage(String msg){
        this.message = msg;
    }
}
