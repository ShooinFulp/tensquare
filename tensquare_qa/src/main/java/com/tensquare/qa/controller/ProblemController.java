package com.tensquare.qa.controller;

import java.util.List;
import java.util.Map;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.tensquare.qa.client.LabelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * problem控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private LabelClient labelClient;

    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
    public Result findLabelById(@PathVariable("id") String id) {
        return labelClient.findById(id);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), problemService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), problemService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param problem
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result newList(@PathVariable String labelid, @PathVariable int page, int size) {
        Page<Problem> newListByLabelId = problemService.findNewListByLabelId(labelid, page, size);
        PageResult<Problem> pageResult = new PageResult<>(newListByLabelId.getTotalElements(), newListByLabelId.getContent());
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), pageResult);
    }

    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result hotlist(@PathVariable String labelid, @PathVariable int page, int size) {
        Page<Problem> hotListByLabelId = problemService.findHotListByLabelId(labelid, page, size);
        PageResult<Problem> pageResult = new PageResult<>(hotListByLabelId.getTotalElements(), hotListByLabelId.getContent());
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), pageResult);
    }

    @RequestMapping(value = "/awitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result awitlist(@PathVariable String labelid, @PathVariable int page, int size) {
        Page<Problem> waitListByLabelId = problemService.findWaitListByLabelId(labelid, page, size);
        PageResult<Problem> pageResult = new PageResult<>(waitListByLabelId.getTotalElements(), waitListByLabelId.getContent());
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), pageResult);
    }
}
