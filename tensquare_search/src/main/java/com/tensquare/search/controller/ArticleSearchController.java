package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/1 1:27 下午
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @RequestMapping(method = RequestMethod.POST)
    public Result addArticleSearch(@RequestBody Article article) {
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    @RequestMapping(value = "/search/{keywords}/{page}/{size}", method = RequestMethod.GET)
    public Result search(@PathVariable String keywords, @PathVariable int page,@PathVariable  int size) {
        Page<Article> articles = articleSearchService.searchArticle(keywords, page, size);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), new PageResult<Article>(articles.getTotalElements(), articles.getContent()));
    }
}
