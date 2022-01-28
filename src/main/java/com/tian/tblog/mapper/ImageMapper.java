package com.tian.tblog.mapper;

import com.tian.tblog.bean.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ImageMapper {
    String getImage(String id);
    int addImage(Image image);
    int delImage(String id);
    Image isExistCover(Map<String, Object> params);
    int updateImage(Image image);
}
