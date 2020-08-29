package cn.hctech2006.livesystem1.mapper;

import cn.hctech2006.livesystem1.bean.Cause;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CauseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cause record);

    Cause selectByPrimaryKey(Integer id);

    List<Cause> selectAll();

    int updateByPrimaryKey(Cause record);
}