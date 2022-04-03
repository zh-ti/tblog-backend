package com.tian.tblog.mapper;

import com.tian.tblog.bean.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ManagerMapper {
    List<Manager> queryManagerList();

    Manager queryManager(String name);

    Manager queryManagerById(String id);

    int updateManager(Manager Manager);

    int deleteManager(String id);

    int insertManager(Manager Manager);

}
