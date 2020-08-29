package cn.hctech2006.livesystem1.bean;

import java.io.Serializable;

public class Illagel implements Serializable {
    private Long id;

    private String videoId;

    private String illegalName;

    private String illagelId;

    private String carId;

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

    public String getIllegalName() {
        return illegalName;
    }

    public void setIllegalName(String illegalName) {
        this.illegalName = illegalName;
    }

    public String getIllagelId() {
        return illagelId;
    }

    public void setIllagelId(String illagelId) {
        this.illagelId = illagelId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoId=").append(videoId);
        sb.append(", illegalName=").append(illegalName);
        sb.append(", illagelId=").append(illagelId);
        sb.append(", carId=").append(carId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}