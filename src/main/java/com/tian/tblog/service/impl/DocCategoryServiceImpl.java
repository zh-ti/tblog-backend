package com.tian.tblog.service.impl;

import com.tian.tblog.bean.DocCategory;
import com.tian.tblog.mapper.DocCategoryMapper;
import com.tian.tblog.service.DocCategoryService;
import com.tian.tblog.utils.DateUtils;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocCategoryServiceImpl implements DocCategoryService {

    @Autowired
    private DocCategoryMapper mapper;

    @Override
    public List<DocCategory> docCategoryList(){
        return mapper.getDocCateGoryList();
    }

    @Override
    public DocCategory queryDocCategory(String id) {
        return mapper.queryDocCategory(id);
    }

    @Override
    public int addDocCategory(String name) {
        if(mapper.queryDocCategoryByName(name) != null) return -1;
        String id = UUIDGenerator.getShortUUID();
        String createTime = DateUtils.getFormatDate();
        return mapper.addDocCategory(id, name, createTime);
    }

    @Override
    public int updateDocCategory(String id, String newName) {
        if(mapper.queryDocCategoryByName(newName) != null) return -1;
        return mapper.updateDocCategory(id, newName);
    }

    @Override
    public int deleteDocCategory(String id) {
        if(mapper.queryDocAmount(id) > 0) return -1;
        return mapper.deleteDocCategory(id);
    }

}
