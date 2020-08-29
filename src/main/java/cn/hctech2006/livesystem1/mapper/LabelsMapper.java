package cn.hctech2006.livesystem1.mapper;

import cn.hctech2006.livesystem1.bean.Labels;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LabelsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Labels record);

    Labels selectByPrimaryKey(Integer id);

    List<Labels> selectAll();

    int updateByPrimaryKey(Labels record);

    Labels selectByUidAndVideoId(@Param("uid") String uid, @Param("videoId") String videoId);

    int updateByUidAndVideoId(Labels labels);
    List<Labels> selectBySpeedAndMisbleAndOutLineAndChangLineAndVideoId
            (@Param("speed") int speed, @Param("misble") int misble,
             @Param("outLine") int outLine, @Param("changeLine") int changeLine, @Param("videoId") String videoId);
    List<String> selectLicenseByVideoId(String videoId);
    List<Labels> selectByVideoId(String videoId);


}