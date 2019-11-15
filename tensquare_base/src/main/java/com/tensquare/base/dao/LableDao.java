package com.tensquare.base.dao;

import com.tensquare.base.pojo.Lable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 〈功能概述〉<br>
 *
 * @className: LableDao
 * @package: com.tensquare.base.dao
 * @author: admin
 * @date: 2019/11/15 14:46
 */
public interface LableDao extends JpaRepository<Lable, String>, JpaSpecificationExecutor<Lable> {
}
