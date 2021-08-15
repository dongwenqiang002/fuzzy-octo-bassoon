/**
  * Copyright (C), 2005-2019, Juphoon Corporation
  *
  * FileName   : RoleService
  * Author     : wenqiangdong
  * Date       : 2019-08-09 14:53
  * Description: 
  * 
  *
  * History:
  * <author>          <time>          <version>          <desc>
  * 作者姓名           修改时间           版本号              描述
  */
    package org.agamotto.cloud.xxx.service;

import reactor.core.publisher.Mono;

/**
 * 〈〉
 *
 * @author wenqiangdong
 * @date   2019-08-09
 */
public interface RoleService {

    /**
     * 获取列表
     * //TODO 缺少查询条件
     * @return
     */
    Mono getList();

    /**
     * 添加角色类型
     * @param role
     * @return
     */
    Mono add(Object role);

    /**
     * 更新
     * @param role
     * @param id
     * @return
     */
    Mono update(Object role,Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    Mono delete(Long id);

}
