package com.tensquare.controller;

import com.tensquare.pojo.Friend;
import com.tensquare.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/15 9:31 下午
 */
@Controller
@RequestMapping(value = "/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String friendid) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (ObjectUtils.isEmpty(claims)) {
            return new Result(false, StatusCode.ACCESSERROR.getCode(), "权限不足");
        }
        friendService.deleteFriend(claims.getId(), friendid);
        return new Result(true, StatusCode.OK.getCode(), "删除成功！");
    }

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (ObjectUtils.isEmpty(claims)) {
            throw new RuntimeException("权限不足");
        }
        if (type.equals("1")) {
            Friend friend = new Friend();
            friend.setUserid(claims.getId());
            friend.setFriendid(friendid);
            if (friendService.addFriend(friend) == 0) {
                return new Result(false, StatusCode.REPERROR.getCode(), "已经添加过好友了");
            }
        } else {
            //不喜欢
            friendService.addNoFriend(claims.getId(), friendid);
        }
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

}
