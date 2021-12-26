package com.tian.tblog.controller;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.service.ManagerService;
import com.tian.tblog.utils.EncryptorUtils;
import com.tian.tblog.utils.JsonHandler;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tblog/manager")
@CrossOrigin(origins="*")
public class ManagerController {

    @Autowired
    private ManagerService service;

    private static String key = "986132";

    @PostMapping("/login")
    public String loginManager(@RequestBody String params) throws UnsupportedEncodingException {
        params = URLDecoder.decode(params, "utf-8");
        Map<String, String> paramsMap = JsonHandler.parse(params, Map.class);
        Manager manager = service.verifyManager(paramsMap);
        if(manager == null) return JsonHandler.stringify(null);
        Map<String, Object> accountMap = new HashMap<>();
        String account = EncryptorUtils.encodeText(manager.getAccount(), key);
        String password = EncryptorUtils.encodeText(manager.getPassword(), key);
        accountMap.put("account", account);
        accountMap.put("password", password);
        System.out.println(account);
        System.out.println(password);
        Cookie cookie = new Cookie("account", JsonHandler.stringify(accountMap));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        resultMap.put("cookie", cookie);
        return JsonHandler.stringify(resultMap);
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestBody String params) throws UnsupportedEncodingException {
        params = URLDecoder.decode(params, "utf-8");
        Map<String, String> accountMap = JsonHandler.parse(params, Map.class);
        String account = EncryptorUtils.decodeText(accountMap.get("account"), key);
        String password = EncryptorUtils.decodeText(accountMap.get("password"), key);
        accountMap.put("account", account);
        accountMap.put("password", password);
        Manager manager = service.verifyManager(accountMap);
        return manager != null ? "1" : "0";
    }

    @PostMapping("/register")
    public String registerManger(@RequestBody String params) throws UnsupportedEncodingException {
        params = URLDecoder.decode(params, "utf-8");
        Map<String, Object> paramsMap = JsonHandler.parse(params, Map.class);
        paramsMap.put("id", UUIDGenerator.getShortUUID());
        return JsonHandler.stringify(service.addManager(paramsMap));
    }

}
