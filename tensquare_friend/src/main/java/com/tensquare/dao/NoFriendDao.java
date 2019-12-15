package com.tensquare.dao;

import com.tensquare.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/15 9:46 下午
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {


}
