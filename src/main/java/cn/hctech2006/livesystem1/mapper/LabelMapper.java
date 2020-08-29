package cn.hctech2006.livesystem1.mapper;

import cn.hctech2006.livesystem1.bean.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Label record);

    Label selectByPrimaryKey(Long id);

    List<Label> selectAll();

    int updateByPrimaryKey(Label record);

    List<Label> selectByVideoId(String videoId);
}