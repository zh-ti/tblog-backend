package com.tian.tblog.service;

import com.tian.tblog.bean.Manager;

import java.util.Map;

public interface ManagerService {
    Manager getManager(String id);
    Manager verifyManager(Map<String, String> params);
    int updateManager(Map<String, Object> params);
    int deleteManager(String id);
    int addManager(Map<String, Object> params);
}
