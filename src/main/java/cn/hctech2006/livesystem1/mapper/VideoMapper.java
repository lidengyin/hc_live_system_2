package cn.hctech2006.livesystem1.mapper;

import cn.hctech2006.livesystem1.bean.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    Video selectByPrimaryKey(Integer id);

    List<Video> selectAll();

    int updateByPrimaryKey(Video record);

    List<Video> selectByName(@Param("name") String name);
    Video selectByVideoId(String videoId);

    int updateByVideoId(Video video);

}