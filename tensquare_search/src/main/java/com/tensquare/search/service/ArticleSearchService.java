package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/1 1:22 下午
 */
@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 添加文章
     * @param article
     */
    public  void add(Article article){
        articleSearchDao.save(article);
    }

    public Page<Article> searchArticle(String keywords,int page,int size){
        return  articleSearchDao.findByTitleOrContentLike(keywords,keywords, PageRequest.of(page-1,size));
    }
}
