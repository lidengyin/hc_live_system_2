package cn.hctech2006.livesystem1.controller;


import cn.hctech2006.livesystem1.server.RealTimeDeepinWebSocketServer;
import cn.hctech2006.livesystem1.server.RealTimeWebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "深度学习交互接口")
@RestController
@RequestMapping("/deepin")
public class DeepinController {
    @Autowired
    private RealTimeWebSocketServer realTimeWebSocketServer;

    @ApiOperation(value = "实时标注信息上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "time", value ="当前视频时间", required = true),
            @ApiImplicitParam(type = "query", name = "line_ball", value ="压线情况", required = true),
            @ApiImplicitParam(type = "query", name = "car", value ="车辆数量", required = true),
            @ApiImplicitParam(type = "query", name = "car_speed", value ="车辆速度", required = true),
            @ApiImplicitParam(type = "query", name = "misbe", value ="违规情况", required = true)

    })

    @RequestMapping(value = "/uploadMessage.do",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public String uploadMessage(String time, String line_ball, String car, String car_speed,String misbe){
        try {
            Map<String,String> map = new HashMap<>();
            map.put("time",time);
            map.put("line_ball",line_ball);
            map.put("car",car);
            map.put("car_speed",car_speed);
            map.put("misbe",misbe);

            realTimeWebSocketServer.sendAll(map);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return "发送失败";
        }
    }
    @ApiOperation(value = "发送信息通知深度学习开始处理发送视频以及发送标志接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "flag", value ="开始标志,如果是true就代表开始", required = true),
    })
    @RequestMapping(value = "/sendMessageToDeepin.do",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public String sendMessageToDeepIn(String flag,String videoUrl){
        try{
            Map<String, String> map = new HashMap<>();
            map.put("flag",flag);
            map.put("videoUrl",videoUrl);
            RealTimeDeepinWebSocketServer.sendAll(map);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return "发送失败";
        }

    }
}
