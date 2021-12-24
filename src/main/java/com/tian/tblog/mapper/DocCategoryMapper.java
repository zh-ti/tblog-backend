package com.tian.tblog.mapper;

import com.tian.tblog.bean.DocCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocCategoryMapper {

    List<DocCategory> getDocCateGoryList();

    DocCategory queryDocCategory(String id);

    DocCategory queryDocCategoryByName(String name);

    int addDocCategory(String id, String name, String createDatetime);

    int updateDocCategory(String id, String newName);

    int deleteDocCategory(String id);

    int queryDocAmount(String id);
}
