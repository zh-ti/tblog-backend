package com.tian.tblog.controller;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.service.ManagerService;
import com.tian.tblog.utils.JsonHandler;
import com.tian.tblog.utils.URLCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tblog/manager")
@CrossOrigin(origins="*")
public class ManagerController {

    @Autowired
    private ManagerService service;


    @GetMapping("/getManagerList")
    public String getManagerList(){
        return JsonHandler.stringify(service.getManagerList());
    }

    @GetMapping("/getManager/{id}")
    public String getManager(@PathVariable("id") String id){
        return JsonHandler.stringify(service.getManager(id));
    }

    @PostMapping("/login")
    public String loginManager(@RequestBody String params) {
        return JsonHandler.stringify(service.loginManager(URLCoder.decode(params)));
    }

    @PostMapping("/update")
    public String updateManager(@RequestBody String params){
        return JsonHandler.stringify(service.updateManager(URLCoder.decode(params)));
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestBody String params) {
        Manager manager = service.verifyManager(URLCoder.decode(params));
        return manager != null ? "1" : "0";
    }

    @PostMapping("/register")
    public String registerManger(@RequestBody String params) {
        return JsonHandler.stringify(service.addManager(URLCoder.decode(params)));
    }

    @GetMapping("/delete/{id}")
    public String deleteManager(@PathVariable String id){
        return JsonHandler.stringify(service.deleteManager(id));
    }

    @PostMapping("/verifyPassword")
    public String checkPassword(@RequestBody String params){
        return JsonHandler.stringify(service.checkPassword(params));
    }
}
