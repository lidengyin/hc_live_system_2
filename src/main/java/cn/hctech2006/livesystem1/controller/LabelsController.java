package cn.hctech2006.livesystem1.controller;

import cn.hctech2006.livesystem1.bean.Labels;
import cn.hctech2006.livesystem1.bean.MmallUser;
import cn.hctech2006.livesystem1.common.Const;
import cn.hctech2006.livesystem1.common.ResponseCode;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.service.impl.LabelsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;

@Api(tags = "监测数据控制器")
@RestController
@RequestMapping("/labels")
public class LabelsController {
    @Autowired
    private LabelsServiceImpl labelsService;
    @ApiOperation(value = "监测数据上传接口", notes = "本接口保存所有车辆, 以及记录每个车辆的违规行为以及相关违规的起止时间")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "license", value = "车牌号", required = true, defaultValue = "鲁ACW666"),
            @ApiImplicitParam(type = "query", name = "uid", value = "车辆唯一编号,用来识别车辆", required = true, defaultValue = "1"),
            @ApiImplicitParam(type = "query", name = "videoId", value = "视频编号", required = true, defaultValue = "xxxxxxx"),
            @ApiImplicitParam(type = "query", name = "outLine", value = "是否压线,0压线, 1不压线", required = true, defaultValue = "1"),
            //@ApiImplicitParam(type = "query", name = "outLineTimes", value = "压线次数", required = true, defaultValue = "12"),
            @ApiImplicitParam(type = "query", name = "outLineConfidence", value = "压线置信度", required = true, defaultValue = "0.22"),
            @ApiImplicitParam(type = "query", name = "outStartTime", value = "压线开始时间,这里只是一个字符串", required = true, defaultValue = "00:23"),
            //@ApiImplicitParam(type = "query", name = "outEndTime", value = "压线下一次更新时间", required = true, defaultValue = "00:56"),
            @ApiImplicitParam(type = "query", name = "misble", value = "是否礼让行人,0不礼让,1礼让", required = true, defaultValue = "1"),
            //@ApiImplicitParam(type = "query", name = "misbleTimes", value = "不礼让行人次数", required = true, defaultValue = "23"),
            @ApiImplicitParam(type = "query", name = "misbleConfidence", value = "不礼让行人置信度", required = true, defaultValue = "0.33"),
            @ApiImplicitParam(type = "query", name = "misbleStartTime", value = "不礼让行人开始时间", required = true, defaultValue = "00:34"),
            //@ApiImplicitParam(type = "query", name = "misbleEndTime", value = "不礼让行人下一次更新时间", required = true, defaultValue = "00:45"),
            @ApiImplicitParam(type = "query", name = "speed", value = "是否超速,0超速,1不超速", required = true, defaultValue = "0"),
            //@ApiImplicitParam(type = "query", name = "speedTimes", value = "超速次数", required = true, defaultValue = "鲁ACW666"),
            @ApiImplicitParam(type = "query", name = "speedConfidence", value = "超速置信度", required = true, defaultValue = "0.33"),
            @ApiImplicitParam(type = "query", name = "speedStartTime", value = "超速开始时间", required = true, defaultValue = "00:32"),
           // @ApiImplicitParam(type = "query", name = "speedEndTime", value = "超速下一次更新时间", required = true, defaultValue = "00:34"),
            @ApiImplicitParam(type = "query", name = "speedNum", value = "超速速度", required = true, defaultValue = "19"),
            @ApiImplicitParam(type = "query", name = "changeLine", value = "是否违规变道,0变道, 1不变道", required = true, defaultValue = "1"),
            @ApiImplicitParam(type = "query", name = "changeLineConfidence", value = "变道置信度", required = true, defaultValue = "0.44"),
            //@ApiImplicitParam(type = "query", name = "changeLineTimes", value = "违规变道总次数", required = true, defaultValue = "5"),
            @ApiImplicitParam(type = "query", name = "changeLineStartTime", value = "违规变道开始时间", required = true, defaultValue = "00:23"),
            //@ApiImplicitParam(type = "query", name = "changeLineEndTime", value = "违规变道结束时间", required = true, defaultValue = "00:45")

    })
    @CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true", methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.OPTIONS,RequestMethod.PATCH})
    @RequestMapping(value = "/upload_labels.do", method = RequestMethod.POST)
    public ServerResponse uploadLabels(
         String license,
         @RequestParam(required = false) String uid,
         @RequestParam(required = false) String videoId,
         @RequestParam(required = false) String outLine,
         @RequestParam(required = false) String outLineConfidence,
         @RequestParam(required = false) String outStartTime,
         @RequestParam(required = false) String misble,
         @RequestParam(required = false) String misbleConfidence,
         @RequestParam(required = false) String misbleStartTime,
         @RequestParam(required = false) String changeLine,
         @RequestParam(required = false) String changeLineConfidence,
         @RequestParam(required = false) String changeLineStartTime,
         @RequestParam(required = false) String speed,
         @RequestParam(required = false) String speedConfidence,
         @RequestParam(required = false) String speedStartTime,
         @RequestParam(required = false) String speedNum){
        System.out.println(videoId+" "+outLine);
        DecimalFormat df = new DecimalFormat("0.00");
        Labels labels = new Labels(license,uid,videoId,
                outLine == null ? 1: Integer.parseInt(outLine),
                outLineConfidence == null ? null : String.format("%.2f", Double.parseDouble(outLineConfidence)),
                outStartTime == null ? null:outStartTime,
                misble==null ? 1:Integer.parseInt(misble),
                misbleConfidence == null ? "0.0" : String.format("%.2f", Double.parseDouble(misbleConfidence)),
                misbleStartTime == null? "0.00":String.format("%.2f", Double.parseDouble(misbleStartTime)),
                speed==null?1:Integer.parseInt(speed),
                speedConfidence==null?"0.0" : String.format("%.2f", Double.parseDouble(speedConfidence)),
                speedStartTime==null ? "0.00":String.format("%.2f", Double.parseDouble(speedStartTime)),
                speedNum==null?0.0:Double.parseDouble(String.format("%.2f", Double.parseDouble(speedNum))),
                changeLine==null?1:Integer.parseInt(changeLine),
                changeLineConfidence==null?"0.0":String.format("%.2f", Double.parseDouble(changeLineConfidence)),
                changeLineStartTime==null?"0.00":String.format("%.2f", Double.parseDouble(changeLineStartTime)));
//        public ServerResponse uploadLabels(Labels labels){
        //String license, String uid, String videoId, int outLine, double outLineConfidence
        System.out.println(labels+" "+license+" "+videoId+" "+misbleConfidence);
        return labelsService.uploadLabels(labels);
    }
    @ApiOperation(value = "前段返回监测数据封装接口", notes = "本接口支持分页查找,支持关键词查找,一共分为五种简单模式" +
            "1. 只是超速(0111)" +
            "2. 只是不遵守礼貌(1011)" +
            "3. 只是压线(1101)" +
            "4. 只是变道(1110)" +
            "5. 全违规模式(0000)" +
            "还有其他组合模式,可以自己开发")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码", required = true, defaultValue = "1"),
            @ApiImplicitParam(type = "query", name = "pageSize", value = "页行", required = true, defaultValue = "1"),
            @ApiImplicitParam(type = "query", name = "speed", value = "是否超速,0超速,1不超速", required = true, defaultValue = "1"),
            @ApiImplicitParam(type = "query", name = "videoId", value = "视频编号", required = true, defaultValue = "xxx"),
            @ApiImplicitParam(type = "query", name = "misble", value = "是否不遵守礼貌, 0不遵守礼貌, 1遵守礼貌", required = true, defaultValue = "10"),
            @ApiImplicitParam(type = "query", name = "outLine", value = "是否压线, 0压线, 1不压线", required = true, defaultValue = "1"),
            @ApiImplicitParam(type = "query", name = "changeLine", value = "是否变道, 0变道, 1不变道", required = true, defaultValue = "1"),
    })
    @CrossOrigin(origins = "*",allowedHeaders = "*", allowCredentials = "true", methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.OPTIONS,RequestMethod.PATCH})
    @GetMapping("/list_page.do")
    public ServerResponse listLabels(@ApiIgnore HttpSession session,  int pageNum, int pageSize, int speed, int misble, int outLine, int changeLine, String videoId){
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null){

            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录后访问");
        }
        return labelsService.listLabels(pageNum, pageSize, speed, misble, outLine, changeLine,videoId);
    }


    public static void main(String[] args) {
        String a = "7.7777777";
        System.out.println(String.format("%.2f", Double.parseDouble(a)));
    }
}
