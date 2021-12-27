package com.tian.tblog.service;

import com.tian.tblog.bean.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Manager> getManagerList();
    Manager getManager(String id);
    Manager verifyManager(String params);
    int updateManager(String params);
    int deleteManager(String id);
    int addManager(String params);
    Map loginManager(String params);
    boolean checkPassword(String params);
}
