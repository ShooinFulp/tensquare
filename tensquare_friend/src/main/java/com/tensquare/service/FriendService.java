package com.tensquare.service;

import com.tensquare.client.UserClient;
import com.tensquare.dao.FriendDao;
import com.tensquare.dao.NoFriendDao;
import com.tensquare.pojo.Friend;
import com.tensquare.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/15 9:20 下午
 */
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    @Transactional
    public void deleteFriend(String userid, String friendid) {
        friendDao.deleteFriend(userid, friendid);
        friendDao.updateIslike(friendid, userid, "0");
        addNoFriend(userid, friendid);
        userClient.incFans(friendid, -1);
        userClient.incFollowcount(userid, -1);
    }

    /**
     * 添加不喜欢记录
     *
     * @param userid
     * @param friendid
     */
    public void addNoFriend(String userid, String friendid) {
        NoFriend noFriend = new NoFriend();
        noFriend.setFriendid(friendid);
        noFriend.setUserid(userid);
        noFriendDao.save(noFriend);
    }


    @Transactional
    public int addFriend(Friend friend) {
        if (friendDao.selectCount(friend.getUserid(), friend.getFriendid()) > 0) {
            return 0;
        }
        friend = new Friend();
        friend.setFriendid(friend.getFriendid());
        friend.setUserid(friend.getUserid());
        friend.setIslike(friend.getIslike());
        friendDao.save(friend);
        userClient.incFans(friend.getFriendid(), 1);
        userClient.incFollowcount(friend.getUserid(), 1);
        if (friendDao.selectCount(friend.getFriendid(), friend.getUserid()) > 0) {
            friendDao.updateIslike(friend.getFriendid(), friend.getUserid(), "1");
            friendDao.updateIslike(friend.getUserid(), friend.getFriendid(), "1");
        }
        return 1;
    }
}
