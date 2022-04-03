package com.tian.tblog.service.impl;

import com.tian.tblog.bean.Manager;
import com.tian.tblog.bean.ResponseEntity;
import com.tian.tblog.bean.ResultStatus;
import com.tian.tblog.mapper.ManagerMapper;
import com.tian.tblog.service.ManagerService;
import com.tian.tblog.utils.DateUtils;
import com.tian.tblog.utils.UUIDGenerator;
import org.apache.catalina.session.StandardSessionFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {
    // 可能存在 session 超时过期但此 map 仍保存它的问题
    private final Map<String, HttpSession> sessions = new HashMap<>();

    @Autowired
    private ManagerMapper mapper;

    @Override
    public ResponseEntity getManagerList() {
        return new ResponseEntity(mapper.queryManagerList());
    }

    @Override
    public ResponseEntity getManager(String id) {
        return new ResponseEntity(mapper.queryManagerById(id));
    }

    @Override
    public ResponseEntity verifyManager(String managerToken, HttpSession session) {
        ResponseEntity responseEntity = new ResponseEntity();
        UsernamePasswordToken usernamePasswordToken;
        if (managerToken == null || !sessions.containsKey(managerToken)) {
            responseEntity.setResultStatus(new ResultStatus("请重新登录"));
        } else {
            try {
                Subject subject = SecurityUtils.getSubject();
                usernamePasswordToken = (UsernamePasswordToken) sessions.get(managerToken).getAttribute("managerToken");
                subject.login(usernamePasswordToken);
                session.setAttribute("managerToken", usernamePasswordToken);
                sessions.put(session.getId(), session);
                responseEntity.setData(session.getId());
            } catch (AuthenticationException e) {
                responseEntity.setResultStatus(new ResultStatus("账号或密码错误"));
            } catch (IllegalStateException e) {
                // 当 session 失效以后使用 getAttribute() 就会报这个错误
                System.out.println("before" + sessions);
                sessions.remove(managerToken);
                System.out.println("after" + sessions);
                responseEntity.setResultStatus(new ResultStatus("请重新登录"));
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity login(Manager manager, HttpSession session) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (manager.isEmpty()) {
            responseEntity.setResultStatus(new ResultStatus("请输入账号和密码"));
        } else {
            UsernamePasswordToken usernamePasswordToken;
            Subject subject = SecurityUtils.getSubject();
            usernamePasswordToken = new UsernamePasswordToken(manager.getName(), manager.getPassword());
            try {
                subject.login(usernamePasswordToken);
                session.setAttribute("managerToken", usernamePasswordToken);
                sessions.put(session.getId(), session);
                responseEntity.setData(session.getId());
            } catch (AuthenticationException e) {
                responseEntity.setResultStatus(new ResultStatus("账号或密码错误"));
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity checkPassword(Manager manager) {
        ResponseEntity responseEntity = new ResponseEntity();
        Manager resultManager;
        if ((resultManager = mapper.queryManagerById(manager.getId())) == null) {
            responseEntity.setResultStatus(new ResultStatus("该管理员不存在"));
        } else {
            System.out.println(resultManager.getPassword());
            if (resultManager.getPassword().equals(manager.getPassword())) {
                responseEntity.setData(true);
            } else {
                responseEntity.setResultStatus(new ResultStatus("密码错误"));
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getManagerInfo(String sessionId) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (sessionId.length() > 0 && sessions.containsKey(sessionId)) {
            UsernamePasswordToken usernamePasswordToken;
            try {
                usernamePasswordToken = (UsernamePasswordToken) sessions.get(sessionId).getAttribute("managerToken");
                Manager manager = managerTokenParse(usernamePasswordToken);
                Manager resultManager = mapper.queryManager(manager.getName());
                if (resultManager != null) {
                    responseEntity.setData(resultManager);
                } else {
                    responseEntity.setResultStatus(new ResultStatus("请重新登录"));
                }
            } catch (IllegalStateException e) {
                responseEntity.setResultStatus(new ResultStatus("请重新登录"));
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateManager(Manager manager) {
        ResponseEntity responseEntity = new ResponseEntity();
        Manager resultManager = mapper.queryManagerById(manager.getId());
        if (resultManager == null) {
            responseEntity.setResultStatus(new ResultStatus("未找到账号 " + manager.getName()));
        } else {
            Manager otherManager = mapper.queryManager(manager.getName());
            // 判断是否名字相同，且不是同一个账号
            if(manager.getName().equals(otherManager.getName()) && !otherManager.equals(manager)){
                responseEntity.setResultStatus(new ResultStatus("账号名已经存在"));
            }else{
                // 管理员的盐和注册时间不能进行修改
                manager.setSalt(resultManager.getSalt());
                manager.setRegistrationDatetime(resultManager.getRegistrationDatetime());
                if (resultManager.getPosition().equals(manager.getPosition()) // 目标等级和当前等级是否相等
                        || !resultManager.getPosition().equals("0")) { // 判断是否有能力修改等级
                    manager.setPosition(resultManager.getPosition());
                }
                // 对密码进行加密
                Md5Hash md5 = new Md5Hash(manager.getPassword(), manager.getSalt(), 1024);
                manager.setPassword(md5.toHex());
                mapper.updateManager(manager);
                responseEntity.setData(manager);
            }
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity deleteManager(String id) {
        return new ResponseEntity(mapper.deleteManager(id) > 0);
    }

    @Override
    public ResponseEntity register(Manager manager) {
        ResponseEntity responseEntity = new ResponseEntity();
        // 先判断该管理员名字是否被使用
        if (manager.isEmpty()) {
            responseEntity.setResultStatus(new ResultStatus("请填写账号和密码"));
        } else {
            if (mapper.queryManager(manager.getName()) != null) {
                responseEntity.setResultStatus(new ResultStatus("用户名已被使用"));
            } else {
                String salt = UUIDGenerator.getShortUUID();
                Md5Hash md5 = new Md5Hash(manager.getPassword(), salt, 1024);
                manager.setId(UUIDGenerator.getShortUUID());
                manager.setPassword(md5.toHex());
                manager.setSalt(salt);
                manager.setRegistrationDatetime(DateUtils.getNowFormatDate());
                mapper.insertManager(manager);
            }
        }

        return responseEntity;
    }

    private Manager managerTokenParse(UsernamePasswordToken managerToken) {
        Manager manager = new Manager();
        manager.setName(managerToken.getUsername());
        manager.setPassword(String.valueOf(managerToken.getPassword()));
        return manager;
    }

}
