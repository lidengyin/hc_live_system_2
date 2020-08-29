package cn.hctech2006.livesystem1.bean;

import java.io.Serializable;

public class Label implements Serializable {
    private Long id;

    private String videoId;

    private String labelId;


    private String time;

    private String number;

    private String misble;

    private String speed;

    private String line;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMisble() {
        return misble;
    }

    public void setMisble(String misble) {
        this.misble = misble;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoId=").append(videoId);
        sb.append(", labelId=").append(labelId);
        sb.append(", time=").append(time);
        sb.append(", number=").append(number);
        sb.append(", misble=").append(misble);
        sb.append(", speed=").append(speed);
        sb.append(", line=").append(line);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}