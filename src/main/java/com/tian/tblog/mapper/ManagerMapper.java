package com.tian.tblog.mapper;

import com.tian.tblog.bean.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ManagerMapper {
    Manager queryManager(String id);
    Manager verifyManager(Map<String, String> params);
    int updateManager(Map<String, Object> params);
    int deleteManager(String id);
    int addManager(Map<String, Object> params);
}
