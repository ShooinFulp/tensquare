package com.tensquare.spit.controller;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/11/24 8:15 下午
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询全部数据
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), spitService.findAll());
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), spitService.findById(id));
    }

    /**
     * 增加
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 根据上级ID查询吐槽列表
     */
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> byParentId = spitService.findByParentId(parentId, page, size);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), new PageResult<Spit>(byParentId.getTotalElements(), byParentId.getContent()));
    }

    /**
     * 点赞
     */
    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String id) {
        String userid = "1111";
        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + id) != null) {
            return new Result(true, StatusCode.REPERROR.getCode(), StatusCode.REPERROR.getMsg());
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_" + userid + "_"+ id, 1);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }
}

