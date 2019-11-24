package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/11/24 8:06 下午
 */
public interface SpitDao extends MongoRepository<Spit, String> {
    /**
     * 根据上级ID查询吐槽列表（分页）
     */
    Page<Spit> findByParentid(String parentid, PageRequest pageRequest);
}
