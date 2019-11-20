package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @className: LableService
 * @package: com.tensquare.base.service
 * @author: admin
 * @date: 2019/11/15 14:49
 */
@Service
public class LableService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LableDao lableDao;

    public void save(Lable lable) {
        lable.setId(idWorker.nextId() + "");
        lableDao.save(lable);
    }

    public void deleteById(String id) {
        lableDao.deleteById(id);
    }

    public void update(Lable lable) {
        lableDao.save(lable);
    }

    public List<Lable> findAll() {
        return lableDao.findAll();
    }

    public Lable findById(String id) {
        return lableDao.findById(id).get();
    }

    public List<Lable> search(Lable lable) {
        return lableDao.findAll(createSpecification(lable));
    }

    public Page<Lable> search(Lable lable, int page, int size) {
        PageRequest of = PageRequest.of(page-1, size);
        return lableDao.findAll(createSpecification(lable), of);
    }

    private Specification<Lable> createSpecification(Lable lable) {
        return new Specification<Lable>() {
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname())) {
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(labelname);
                }
                if (lable.getRecommend() != null && !"".equals(lable.getRecommend())) {
                    Predicate recommend = criteriaBuilder.equal(root.get("recommend").as(String.class), lable.getRecommend());
                    list.add(recommend);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }
}
