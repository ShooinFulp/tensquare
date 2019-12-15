package com.tensquare.dao;

import com.tensquare.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/15 9:09 下午
 */
public interface FriendDao extends JpaRepository<Friend, String> {
    /**
     * 根据用户ID与被关注用户id查询记录个数
     *
     * @param userid 用户id
     * @param friend 被关注人ID
     * @return 数量
     */
    @Query("select count(f) from Friend  f where  f.userid = ?1 and f.friendid = ?2")
    public int selectCount(String userid, String friend);

    /**
     * 更新为互相喜欢
     *
     * @param userid
     * @param friend
     * @param islike
     * @return
     */
    @Modifying
    @Query("update  Friend  set  islike = ?3 where userid = ?1 and friendid =?2")
    public int updateIslike(String userid, String friend, String islike);

    /**
     * 删除喜欢
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query("delete from Friend where userid = ?1 and friendid = ?2")
    public void deleteFriend(String userid, String friendid);
}
