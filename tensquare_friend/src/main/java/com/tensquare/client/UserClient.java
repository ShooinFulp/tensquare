package com.tensquare.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈用户客户端〉
 *
 * @author: fuliping
 * @date: 2019/12/15 10:43 下午
 */
@FeignClient("tensquare-user")
@RequestMapping("/user")
public interface UserClient {
    @RequestMapping(value = "/incfollow/{userid}/{x}", method = RequestMethod.POST)
    public void incFollowcount(@PathVariable("userid") String userid, @PathVariable("x") int x);

    @RequestMapping(value = "/incFans/{userid}/{x}", method = RequestMethod.POST)
    public void incFans(@PathVariable("userid") String userid, @PathVariable("x") int x);
}
