package org.agamotto.cloud.xxx.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.impl.Base64Codec;
import org.agamotto.cloud.xxx.exception.XXXException;
import org.springframework.http.HttpStatus;

/**
 * 〈〉
 *
 * @author wenqiangdong
 * @date 2019-08-12
 */
public interface TokenService {

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    Boolean checkToken(String token);


    /**
     * 获取客户信息
     *
     * @param token
     * @return
     * @throws
     */
    default Object getInfoByToken(String token) {
        try {
            if ((token == null) || (token.length() < 6)) {
                return null;
            }
            String context = Base64Codec.BASE64URL.decodeToString(token.split("\\.")[1]);
            return JSONObject.parseObject(context, Object.class);
        } catch (JSONException jsonException) {
            throw new XXXException("token无效", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * 创建token
     * @param authObject
     * @return
     */
    String createJWTByObj(Object authObject);

    /**
     * 注销token
     * @param token
     * @return
     */
    Boolean removeToken(String token);

    }
