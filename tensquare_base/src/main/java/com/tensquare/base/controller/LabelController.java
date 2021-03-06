package com.tensquare.base.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tensquare.base.pojo.Lable;
import com.tensquare.base.service.LableService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

/**
 * 〈标签控制器〉<br>
 *
 * @className: LableController
 * @package: com.tensquare.base.controller
 * @author: admin
 * @date: 2019/11/15 14:56
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LableService lableService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Lable lable) {
        lableService.save(lable);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    @RequestMapping(value = "/{lableId}", method = RequestMethod.DELETE)
    public Result removeById(@PathVariable("lableId") String lableId) {
        lableService.deleteById(lableId);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Lable lable, @PathVariable String id) {
        lable.setId(id);
        lableService.update(lable);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), lableService.findAll());
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String Id) {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), lableService.findById(Id));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result search(@RequestBody Lable lable) {
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), lableService.search(lable));
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result search(@RequestBody Lable lable, @PathVariable int page, @PathVariable int size) {
        Page<Lable> search = lableService.search(lable, page, size);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), new PageResult<Lable>(search.getTotalElements(), search.getContent()));
    }

}
