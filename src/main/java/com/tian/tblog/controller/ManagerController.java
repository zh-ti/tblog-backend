package com.tian.tblog.controller;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.bean.ResponseEntity;
import com.tian.tblog.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService service;


    @GetMapping("/getManagerList")
    public ResponseEntity getManagerList() {
        return service.getManagerList();
    }

    @GetMapping("/getManager/{id}")
    public ResponseEntity getManager(@PathVariable("id") String id) {
        return service.getManager(id);
    }

    @GetMapping("/login")
    public ResponseEntity loginView() {
        return new ResponseEntity("Hello This is login view");
    }

    @PostMapping("/login")
    public ResponseEntity loginManager(@RequestBody Manager manager,
                                       HttpSession session) {
        return service.login(manager, session);
    }

    @PostMapping("/update")
    public ResponseEntity updateManager(@RequestBody Manager manager) {
        return service.updateManager(manager);
    }

    @PostMapping("/checkLogin")
    public ResponseEntity checkLogin(@RequestBody Map<String, String> params,
                                     HttpSession session) {
        String managerToken = params.get("managerToken");
        return service.verifyManager(managerToken, session);
    }

    @PostMapping("/register")
    public ResponseEntity registerManger(@RequestBody Manager manager) {
        return service.register(manager);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteManager(@PathVariable String id) {
        return service.deleteManager(id);
    }

    @PostMapping("/verifyPassword")
    public ResponseEntity checkPassword(@RequestBody Manager manager) {
        System.out.println(manager);
        return service.checkPassword(manager);
    }

    @PostMapping("/getManagerInfo/{sessionId}")
    public ResponseEntity getManagerInfo(@PathVariable("sessionId") String sessionId){
        return service.getManagerInfo(sessionId);
    }
}
