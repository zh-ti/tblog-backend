package com.tian.tblog.service.impl;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.mapper.ManagerMapper;
import com.tian.tblog.service.ManagerService;
import com.tian.tblog.utils.DateUtils;
import com.tian.tblog.utils.EncryptorUtils;
import com.tian.tblog.utils.JsonHandler;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper mapper;

    private static String key = "986132";

    @Override
    public List<Manager> getManagerList() {
        return mapper.managerList();
    }

    @Override
    public Manager getManager(String id) {
        return mapper.queryManager(id);
    }

    @Override
    public Map<String, Object> loginManager(String params){
        Map<String, String> paramsMap = JsonHandler.parse(params, Map.class);
        Manager manager = mapper.verifyManager(paramsMap);
        if(manager == null) return null;
        String account = EncryptorUtils.encodeText(manager.getAccount(), key);
        String password = EncryptorUtils.encodeText(manager.getPassword(), key);
        manager.setAccount(account);
        manager.setPassword(password);
        Cookie cookie = new Cookie("account", JsonHandler.stringify(manager));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        resultMap.put("cookie", cookie);
        System.out.println(resultMap);
        return resultMap;
    }

    @Override
    public boolean checkPassword(String params) {
        Map<String, Object> maramsMap = JsonHandler.parse(params, Map.class);
        return mapper.checkPassword(maramsMap) != null;
    }

    @Override
    public Manager verifyManager(String params) {
        Map<String, String> accountMap = JsonHandler.parse(params, Map.class);
        String account = EncryptorUtils.decodeText(accountMap.get("account"), key);
        String password = EncryptorUtils.decodeText(accountMap.get("password"), key);
        accountMap.put("account", account);
        accountMap.put("password", password);
        return mapper.verifyManager(accountMap);
    }

    @Override
    public int updateManager(String params) {
        Map<String, String> accountMap = JsonHandler.parse(params, Map.class);
        Manager manager = mapper.queryManagerByAccount(accountMap.get("account"));
        if(manager != null && !manager.getId().equals(accountMap.get("id"))) return -1;
        return mapper.updateManager(accountMap);
    }

    @Override
    public int deleteManager(String id) {
        return mapper.deleteManager(id);
    }

    @Override
    public int addManager(String params) {
        Map<String, String> paramsMap = JsonHandler.parse(params, Map.class);
        if(mapper.queryManagerByAccount(paramsMap.get("account")) != null) return -1;
        paramsMap.put("id", UUIDGenerator.getShortUUID());
        paramsMap.put("registDatetime", DateUtils.getFormatDate());
        return mapper.addManager(paramsMap);
    }
}
