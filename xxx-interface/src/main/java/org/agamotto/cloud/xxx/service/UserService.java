package org.agamotto.cloud.xxx.service;

import reactor.core.publisher.Mono;

/**
 * 〈〉
 *
 * @author wenqiangdong
 * @date 2019-05-30
 */
public interface UserService {

    /**
     * 返回token
     * @param username a
     * @param password a
     * @return a
     */
    Mono<String> login(String username, String password);

    /**
     * 添加注册用户，应该是一个繁琐的流程
     * @param userPO a
     * @return a
     */
    Mono<Boolean> register(Object userPO);

    /**
     * 根据用户名获取用户
     * @param username a
     * @return a
     */
    Mono getUser(String username);

    /**
     * 获取列表
     * @return a
     */
    Mono getList();

    /**
     * 退出登录
     * @param token
     * @return
     */
    Mono<Boolean> logout(String token);

    /**
     * 停用用户
     * @param id
     * @return
     */
    Mono stopUser(Integer id);
}
