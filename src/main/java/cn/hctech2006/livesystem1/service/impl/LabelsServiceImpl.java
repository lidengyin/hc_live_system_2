package cn.hctech2006.livesystem1.service.impl;


import cn.hctech2006.livesystem1.bean.Labels;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.mapper.LabelsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelsServiceImpl {
    @Autowired
    private LabelsMapper labelsMapper;
    public ServerResponse uploadLabels(Labels params){
        //System.out.println(params);
        Labels labels = labelsMapper.selectByUidAndVideoId(params.getUid(), params.getVideoId());
        //----------初始化开始------------------//

        //------1--------如果标注不存在,则新建监测数据
        if (labels==null){
            //初始化超速状态
            if (params.getSpeed() == 1){
                params.setSppedTimes(0);
                params.setSpeedConfidence("0.00");
            }else params.setSppedTimes(1);
            //初始化变道状态
            if (params.getChangeLine() == 1) {
                params.setChangeLineTimes(0);
                params.setChangeLineConfidence("0.00");
            }
            else params.setChangeLineTimes(1);
            //初始化压线状态
            if (params.getOutLine() == 1) {
                params.setOutLineTimes(0);
                params.setOutLineConfidence("0.00");
            }
            else params.setOutLineTimes(1);
            //初始化不礼让行人状态
            if (params.getMisble() == 1) {
                params.setMisbleTimes(0);
                params.setMisbleConfidence("0.00");
            }
            else params.setMisbleTimes(1);
            //插入新的监控数据
            int reault = labelsMapper.insert(params);
            System.out.println("监测数据初始化成功");
            if (reault > 0) return ServerResponse.createBySuccess("监测数据初始化成功",params);
            return ServerResponse.createByError("监测数据初始化失败");
        }

        //----------初始化结束-------------------//

        //-------2--------修改是否压线,如果压线,则进行修改
        if (params.getOutLine() == 0){
            if (labels.getOutLine() == 1) labels.setOutStartTime(params.getOutStartTime());
            labels.setOutLineTimes(labels.getOutLineTimes()+1);
            labels.setOutLineConfidence(Double.toString(Double.parseDouble(labels.getOutLineConfidence())+Double.parseDouble(params.getOutLineConfidence())));
            labels.setOutEndTime(params.getOutStartTime());
            labels.setOutLine(0);
        }
        //-------3-------修改是否不礼让行人,如果不礼让行人
        if (params.getMisble()==0){
            if (labels.getMisble() == 1) labels.setMisbleStartTime(params.getMisbleStartTime());
            labels.setMisbleEndTime(params.getMisbleStartTime());
            labels.setMisble(0);
            labels.setMisbleConfidence(Double.toString(Double.parseDouble(labels.getMisbleConfidence())+Double.parseDouble(params.getMisbleConfidence())));
            labels.setMisbleTimes(labels.getMisbleTimes()+1);
        }
        //-------4-------修改是否违规变道,如果违规变道
        if (params.getChangeLine() == 0){
            if (labels.getChangeLine() == 1) labels.setChangeLineStartTime(params.getChangeLineStartTime());
            labels.setChangeLine(0);
            labels.setChangeLineConfidence(Double.toString(Double.parseDouble(labels.getChangeLineConfidence())+Double.parseDouble(params.getChangeLineConfidence())));
            labels.setChangeLineEndTime(params.getChangeLineStartTime());
            labels.setChangeLineTimes(labels.getChangeLineTimes()+1);
        }
        //-------5-------修改是否超速, 如果超速
        if (params.getSpeed() == 0){
            if(labels.getSpeed() == 1) {
                labels.setSpeedStartTime(params.getSpeedStartTime());
            }
            labels.setSpeed(0);
            labels.setSpeedConfidence(Double.toString(Double.parseDouble(labels.getSpeedConfidence())+Double.parseDouble(params.getSpeedConfidence())));
            labels.setSppedTimes(labels.getSppedTimes()+1);
            labels.setSpeedEndTime(params.getSpeedStartTime());
            labels.setSpeedNum(labels.getSpeedNum()+params.getSpeedNum());
        }
        if (labels.getLicense().length() < params.getLicense().length()){
            labels.setLicense(params.getLicense());
        }
        int result = labelsMapper.updateByUidAndVideoId(labels);
        if (result > 0) return ServerResponse.createBySuccess("监测数据修改成功",labels);
        return ServerResponse.createByError("监测数据修改失败");
    }
    public ServerResponse listLabels(int pageNum, int pageSize, int speed, int misble, int outLine, int changeLine, String videoId){
        PageHelper.startPage(pageNum,pageSize);
        List<Labels> labels = labelsMapper.selectBySpeedAndMisbleAndOutLineAndChangLineAndVideoId(speed, misble, outLine, changeLine, videoId);
        PageInfo result = new PageInfo(labels);
        return ServerResponse.createBySuccess("监测数据返回成功",result);
    }

    /**
     * 封装监测数据列表
     * @param labelsList
     * @return
     */
//    private List<Labels> encapLabelsList(List<Labels> labelsList){
//
//    }
}
