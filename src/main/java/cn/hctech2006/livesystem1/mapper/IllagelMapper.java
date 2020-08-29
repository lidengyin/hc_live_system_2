package cn.hctech2006.livesystem1.mapper;


import cn.hctech2006.livesystem1.bean.Illagel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IllagelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Illagel record);

    Illagel selectByPrimaryKey(Long id);

    List<Illagel> selectAll();

    int updateByPrimaryKey(Illagel record);
}