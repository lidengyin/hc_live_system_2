package cn.hctech2006.livesystem1.bean.vb;

import cn.hctech2006.livesystem1.bean.Labels;
import cn.hctech2006.livesystem1.bean.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class VideoVO {
    private Video video;
    private List<Labels> labelsList;

    public VideoVO(Video video, List<Labels> labelsList) {
        this.video = video;
        this.labelsList = labelsList;

    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<Labels> getLabelsList() {
        return labelsList;
    }

    public void setLabelsList(List<Labels> labelsList) {
        this.labelsList = labelsList;
    }

    }
