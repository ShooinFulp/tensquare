package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/1 1:21 下午
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
