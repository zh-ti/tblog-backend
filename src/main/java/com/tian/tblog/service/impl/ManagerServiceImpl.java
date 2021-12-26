package com.tian.tblog.service.impl;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.mapper.ManagerMapper;
import com.tian.tblog.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper mapper;

    @Override
    public Manager getManager(String id) {
        return mapper.queryManager(id);
    }

    @Override
    public Manager verifyManager(Map<String, String> params) {
        return mapper.verifyManager(params);
    }

    @Override
    public int updateManager(Map<String, Object> params) {
        return mapper.updateManager(params);
    }

    @Override
    public int deleteManager(String id) {
        return mapper.deleteManager(id);
    }

    @Override
    public int addManager(Map<String, Object> params) {
        return mapper.addManager(params);
    }
}
