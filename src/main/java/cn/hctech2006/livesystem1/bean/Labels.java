package cn.hctech2006.livesystem1.bean;

import java.io.Serializable;

public class Labels implements Serializable {
    private Integer id;

    private String license;

    private String uid;

    private String videoId;

    private Integer outLine;

    private Integer outLineTimes;

    private String outLineConfidence;

    private String outStartTime;

    private String outEndTime;

    private Integer misble;

    private Integer misbleTimes;

    private String misbleConfidence;

    private String misbleStartTime;

    private String misbleEndTime;

    private Integer speed;

    private Integer sppedTimes;

    private String speedConfidence;

    private String speedStartTime;

    private String speedEndTime;

    private Double speedNum;

    private Integer changeLine;

    private String changeLineConfidence;

    private Integer changeLineTimes;

    private String changeLineStartTime;

    private String changeLineEndTime;

    public Labels() {
    }

    public Labels(String license, String uid, String videoId, Integer outLine, String outLineConfidence, String outStartTime, Integer misble, String misbleConfidence, String misbleStartTime, Integer speed, String speedConfidence, String speedStartTime, Double speedNum, Integer changeLine, String changeLineConfidence, String changeLineStartTime) {
        this.license = license;
        this.uid = uid;
        this.videoId = videoId;
        this.outLine = outLine;
        this.outLineConfidence = outLineConfidence;
        this.outStartTime = outStartTime;
        this.misble = misble;
        this.misbleConfidence = misbleConfidence;
        this.misbleStartTime = misbleStartTime;
        this.speed = speed;
        this.speedConfidence = speedConfidence;
        this.speedStartTime = speedStartTime;
        this.speedNum = speedNum;
        this.changeLine = changeLine;
        this.changeLineConfidence = changeLineConfidence;
        this.changeLineStartTime = changeLineStartTime;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Integer getOutLine() {
        return outLine;
    }

    public void setOutLine(Integer outLine) {
        this.outLine = outLine;
    }

    public Integer getOutLineTimes() {
        return outLineTimes;
    }

    public void setOutLineTimes(Integer outLineTimes) {
        this.outLineTimes = outLineTimes;
    }

    public String getOutLineConfidence() {
        return outLineConfidence;
    }

    public void setOutLineConfidence(String outLineConfidence) {
        this.outLineConfidence = outLineConfidence;
    }

    public String getOutStartTime() {
        return outStartTime;
    }

    public void setOutStartTime(String outStartTime) {
        this.outStartTime = outStartTime;
    }

    public String getOutEndTime() {
        return outEndTime;
    }

    public void setOutEndTime(String outEndTime) {
        this.outEndTime = outEndTime;
    }

    public Integer getMisble() {
        return misble;
    }

    public void setMisble(Integer misble) {
        this.misble = misble;
    }

    public Integer getMisbleTimes() {
        return misbleTimes;
    }

    public void setMisbleTimes(Integer misbleTimes) {
        this.misbleTimes = misbleTimes;
    }

    public String getMisbleConfidence() {
        return misbleConfidence;
    }

    public void setMisbleConfidence(String misbleConfidence) {
        this.misbleConfidence = misbleConfidence;
    }

    public String getMisbleStartTime() {
        return misbleStartTime;
    }

    public void setMisbleStartTime(String misbleStartTime) {
        this.misbleStartTime = misbleStartTime;
    }

    public String getMisbleEndTime() {
        return misbleEndTime;
    }

    public void setMisbleEndTime(String misbleEndTime) {
        this.misbleEndTime = misbleEndTime;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSppedTimes() {
        return sppedTimes;
    }

    public void setSppedTimes(Integer sppedTimes) {
        this.sppedTimes = sppedTimes;
    }

    public String getSpeedConfidence() {
        return speedConfidence;
    }

    public void setSpeedConfidence(String speedConfidence) {
        this.speedConfidence = speedConfidence;
    }

    public String getSpeedStartTime() {
        return speedStartTime;
    }

    public void setSpeedStartTime(String speedStartTime) {
        this.speedStartTime = speedStartTime;
    }

    public String getSpeedEndTime() {
        return speedEndTime;
    }

    public void setSpeedEndTime(String speedEndTime) {
        this.speedEndTime = speedEndTime;
    }

    public Double getSpeedNum() {
        return speedNum;
    }

    public void setSpeedNum(Double speedNum) {
        this.speedNum = speedNum;
    }

    public Integer getChangeLine() {
        return changeLine;
    }

    public void setChangeLine(Integer changeLine) {
        this.changeLine = changeLine;
    }

    public String getChangeLineConfidence() {
        return changeLineConfidence;
    }

    public void setChangeLineConfidence(String changeLineConfidence) {
        this.changeLineConfidence = changeLineConfidence;
    }

    public Integer getChangeLineTimes() {
        return changeLineTimes;
    }

    public void setChangeLineTimes(Integer changeLineTimes) {
        this.changeLineTimes = changeLineTimes;
    }

    public String getChangeLineStartTime() {
        return changeLineStartTime;
    }

    public void setChangeLineStartTime(String changeLineStartTime) {
        this.changeLineStartTime = changeLineStartTime;
    }

    public String getChangeLineEndTime() {
        return changeLineEndTime;
    }

    public void setChangeLineEndTime(String changeLineEndTime) {
        this.changeLineEndTime = changeLineEndTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", license=").append(license);
        sb.append(", uid=").append(uid);
        sb.append(", videoId=").append(videoId);
        sb.append(", outLine=").append(outLine);
        sb.append(", outLineTimes=").append(outLineTimes);
        sb.append(", outLineConfidence=").append(outLineConfidence);
        sb.append(", outStartTime=").append(outStartTime);
        sb.append(", outEndTime=").append(outEndTime);
        sb.append(", misble=").append(misble);
        sb.append(", misbleTimes=").append(misbleTimes);
        sb.append(", misbleConfidence=").append(misbleConfidence);
        sb.append(", misbleStartTime=").append(misbleStartTime);
        sb.append(", misbleEndTime=").append(misbleEndTime);
        sb.append(", speed=").append(speed);
        sb.append(", sppedTimes=").append(sppedTimes);
        sb.append(", speedConfidence=").append(speedConfidence);
        sb.append(", speedStartTime=").append(speedStartTime);
        sb.append(", speedEndTime=").append(speedEndTime);
        sb.append(", speedNum=").append(speedNum);
        sb.append(", changeLine=").append(changeLine);
        sb.append(", changeLineConfidence=").append(changeLineConfidence);
        sb.append(", changeLineTimes=").append(changeLineTimes);
        sb.append(", changeLineStartTime=").append(changeLineStartTime);
        sb.append(", changeLineEndTime=").append(changeLineEndTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}