package cn.hctech2006.livesystem1.controller;

import cn.hctech2006.livesystem1.bean.MmallUser;
import cn.hctech2006.livesystem1.bean.Video;
import cn.hctech2006.livesystem1.common.Const;
import cn.hctech2006.livesystem1.common.ResponseCode;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.service.impl.VideoServiceImpl;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;


@Api(tags = "监控视频解析控制器")
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoServiceImpl videoService;

    @ApiOperation(value = "深度学习视频上传接口")
    @PostMapping(value = "/upload_deep.do")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ServerResponse insertVideo(@ApiIgnore HttpSession session, @RequestBody MultipartFile uploadFile, @ApiParam("视频唯一标识") @RequestParam String videoId) throws FileNotFoundException {

        return videoService.insertVideo(uploadFile,videoId);
    }
    @ApiOperation(value = "视频列表查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "name", value = "视频名"),
            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize", value = "每页大小",required = true),
    })
    @RequestMapping(value = "/select_all.do",method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ServerResponse selectAll(@ApiIgnore HttpSession session,  String name, int pageNum, int pageSize){
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录后访问");
        }
        return videoService.selectVideoByName(name, pageNum, pageSize);
    }
    @ApiOperation(value = "单个视频查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "videoId", value = "视频唯一标识ID",required = true)
    })
    @RequestMapping(value = "/select_one.do",method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ServerResponse selectByVideoID(@ApiIgnore HttpSession session,  String videoId){
//        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
//        if (user == null){
//            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录后访问");
//        }
        return videoService.selectByVideoId(videoId);
    }

        @ApiOperation(value = "前段信息推送接口,包括标注信息，视频URL")
    @RequestMapping(value = "/pull",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ServerResponse upload(@ApiIgnore HttpSession session,  String  videoUrl,String message, String videoId) throws IOException {
            MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
            if (user == null){
                return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录后访问");
            }
        try{
            System.out.println(message);
                videoService.insertVideoBefore(videoUrl, message, videoId);
            //System.out.println(file.getName());
            return ServerResponse.createBySuccess("深度学习已经开始解析");
        }catch (Exception e){
            return ServerResponse.createByError("解析失败,请重新发送");
        }

    }
    @ApiOperation(value = "前段视频文件上传接口,返回视频URL和图片URL")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ServerResponse  upload(MultipartFile file) throws IOException {
            return videoService.insertVideoAndPicture(file);

    }
}
