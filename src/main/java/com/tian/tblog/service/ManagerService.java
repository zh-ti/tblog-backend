package com.tian.tblog.service;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.bean.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface ManagerService {
    ResponseEntity getManagerList();

    ResponseEntity getManager(String id);

    ResponseEntity verifyManager(String managerToken, HttpSession session);

    ResponseEntity updateManager(Manager Manager);

    ResponseEntity deleteManager(String id);

    ResponseEntity register(Manager manager);

    ResponseEntity login(Manager manager, HttpSession session);

    ResponseEntity checkPassword(Manager manager);

    ResponseEntity getManagerInfo(String sessionId);
}
