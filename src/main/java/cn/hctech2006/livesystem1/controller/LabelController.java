package cn.hctech2006.livesystem1.controller;

import cn.hctech2006.livesystem1.bean.Label;
import cn.hctech2006.livesystem1.service.ILabelService;

import cn.hctech2006.livesystem1.bean.Label;
import cn.hctech2006.livesystem1.server.RecordedWebSocketServer;
import cn.hctech2006.livesystem1.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "标注信息列表")
@RestController
@RequestMapping("/label")
@CacheConfig
public class LabelController {
    @Autowired
    private RecordedWebSocketServer recordedWebSocketServer;
    @Autowired

    private ILabelService labelService;
    @ApiOperation(value = "标注信息上传")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "videoId",value = "所属视频ID",required = true),
            @ApiImplicitParam(type = "query", name = "time",value = "当前视频时间",required = true,defaultValue = "02:26:34"),
            @ApiImplicitParam(type = "query", name = "number",value = "违章车数量",required = true),
            @ApiImplicitParam(type = "query", name = "misble",value = "违规车牌",required = true),
            @ApiImplicitParam(type = "query", name = "speed",value = "违规车以及车速",required = true),
            @ApiImplicitParam(type = "query", name = "line",value = "压线车牌",required = true)
    })
    @RequestMapping(value = "/insert.do",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})

    public String insert(Label label){
        try {
            labelService.insertLabel(label);
            return "上传成功";
        }catch (Exception e){
            e.printStackTrace();
            return "上传失败";
        }
    }
    @ApiOperation(value = "标注信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "videoId",value = "所属视频ID",required = true)
    })
    @RequestMapping(value = "/select_one.do",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})

    public void selectByVideoId(String videoId) throws InterruptedException {

        labelService.selectByVideoId(videoId);
    }
    @RequestMapping(value = "redis_get.do",method = RequestMethod.GET)
    @Cacheable(cacheNames = "user",key = "'text'")
    public Label redisText(){
        Label label = new Label();
        label.setLabelId("3222");
        return label;
    }
    @RequestMapping(value = "redis_put.do",method = RequestMethod.PUT)
    @CachePut(cacheNames = "user",key = "'text'")
    public Label redisPut(){
        Label label = new Label();
        label.setLabelId("2222");
        return label;
    }

    public void process(String message){
        System.out.println("Message is : "+message);
    }

}
