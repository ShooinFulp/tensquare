package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

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
}
