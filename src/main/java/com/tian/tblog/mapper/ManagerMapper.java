package com.tian.tblog.mapper;

import com.tian.tblog.bean.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ManagerMapper {
    List<Manager> managerList();

    Manager queryManager(String id);

    Manager queryManagerByAccount(String account);

    Manager verifyManager(Map<String, String> params);

    int updateManager(Map<String, String> params);

    int deleteManager(String id);

    int addManager(Map<String, String> params);

    Manager checkPassword(Map<String, Object> params);
}
