package cn.hctech2006.livesystem1.service;

import cn.hctech2006.livesystem1.bean.Label;

public interface ILabelService {
    public String insertLabel(Label label);
    public void selectByVideoId(String videoId) throws InterruptedException;
}
