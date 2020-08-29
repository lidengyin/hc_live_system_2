package cn.hctech2006.livesystem1.service.impl;

import cn.hctech2006.livesystem1.bean.Cause;
import cn.hctech2006.livesystem1.bean.Labels;
import cn.hctech2006.livesystem1.bean.Video;
import cn.hctech2006.livesystem1.bean.vb.VideoVO;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.mapper.CauseMapper;
import cn.hctech2006.livesystem1.mapper.LabelsMapper;
import cn.hctech2006.livesystem1.mapper.VideoMapper;
import cn.hctech2006.livesystem1.server.RealTimeDeepinWebSocketServer;

import cn.hctech2006.livesystem1.server.RealTimeWebSocketServer;
import cn.hctech2006.livesystem1.util.FastDFSUtil;
import cn.hctech2006.livesystem1.util.ImageAndVideoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class VideoServiceImpl  {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private LabelsMapper labelsMapper;
    @Autowired
    private CauseMapper causeMapper;
    private static Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);
    //基本线程池连接数为4,最大连接数6,空闲等待最长时间5秒,使用链表阻塞队列
//    private ThreadPoolExecutor executor = new ThreadPoolExecutor(4,6,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
//    public String handleRequest(MultipartFile multipartFile, String videoId){
//        executor.execute(new Thread(new MyTask(this,multipartFile,videoId)));
//        return "后台正在上传";
//    }

    /**
     * 深度学习视频标注后上传接口
     * @param uploadFile
     * @param videoId
     * @return
     * @throws FileNotFoundException
     */
    public ServerResponse insertVideo(MultipartFile uploadFile, String videoId) throws FileNotFoundException {
//        String videoUrlName = UUID.randomUUID().toString()+uploadFile.getOriginalFilename()
//                .substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String imgUrlName = UUID.randomUUID().toString()+".jpg";
        //String url = ResourceUtils.getURL("").getPath()+ videoUrlName;
        String imgUrl = ResourceUtils.getURL("").getPath()+imgUrlName;
        //String videoName = uploadFile.getOriginalFilename();
        //File file = new File(url);
        File imgFile = new File(imgUrl);

        try {




            String video_path = FastDFSUtil.upload(uploadFile);
            System.out.println("videoPath: "+video_path);
            ImageAndVideoUtil.fetchFrame(video_path,imgUrl);
            String img_path = FastDFSUtil.upload(imgFile);
            Video video = new Video();
            //video.setName(videoName);
            video.setVideoUrl(video_path);
            video.setVideoId(videoId);
            video.setImgUrl(img_path);
            int result = videoMapper.updateByVideoId(video);
            Map<String, String> map = new HashMap<>();

            if (result > 0) {

                map.put("videoId", videoId);
                map.put("status", "success");

            }else{
                map.put("videoId", videoId);
                map.put("status", "fail");
            }
            RealTimeWebSocketServer.sendAll(map);


            logger.info("视频URL:"+video_path);
            logger.info("图片URL:"+img_path);
            return ServerResponse.createBySuccess(video_path);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    public String insertVideoBefore(String videoUrl,String message, String videoId) throws FileNotFoundException {

        try {

            Map<String,String> map = new HashMap<>();
            map.put("flag","true");
            map.put("message",message);
            map.put("videoUrl",videoUrl);
            map.put("videoId", videoId);
            RealTimeDeepinWebSocketServer.sendAll(map);
            return videoUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "传输失败";
        }
    }

    /**
     * 前段视频上传接口
     * @param uploadFile
     * @return
     * @throws FileNotFoundException
     */
    public ServerResponse insertVideoAndPicture(MultipartFile uploadFile) throws FileNotFoundException {
        String videoUrlName = UUID.randomUUID().toString()+uploadFile.getOriginalFilename()
                .substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String imgUrlName = UUID.randomUUID().toString()+".jpg";
        String url = ResourceUtils.getURL("").getPath()+ videoUrlName;
        String imgUrl = ResourceUtils.getURL("").getPath()+imgUrlName;
        String videoName = uploadFile.getOriginalFilename();
        File file = new File(url);
        File imgFile = new File(imgUrl);

        try {
            //uploadFile.transferTo(file);
//            FTPUtil.uploadFile(Lists.newArrayList(file));
//            file.delete();
            url = FastDFSUtil.upload(uploadFile);
            ImageAndVideoUtil.fetchFrame(url,imgUrl);
            //FTPUtil.uploadFile(Lists.newArrayList(imgFile));
            //imgFile.delete();
            imgUrl =FastDFSUtil.upload(imgFile);
            Video video = new Video();
            video.setName(videoName);
            video.setVideoUrl(url);
            video.setVideoId(UUID.randomUUID().toString());
            //video.setVideoId(videoId);
            video.setImgUrl(imgUrl);
            videoMapper.insert(video);
            logger.info("视频URL:"+url);
            logger.info("图片URL:"+imgUrl);
            Map<String,String> map = new HashMap<>();
            //map.put("flag","true");
            map.put("imageUrl",imgUrl);
            map.put("videoUrl",url);
            //RealTimeDeepinWebSocketServer.sendAll(map);
            return ServerResponse.createBySuccess(video);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }

    /**
     * 视频列表查询接口
     * @param
     * @return
     */
    public ServerResponse selectVideoByName(String name, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Video> videos = videoMapper.selectByName(name);
        PageInfo pageInfo = new PageInfo(videos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 单个视频查询
     * @param videoId
     * @return
     */
    public ServerResponse selectByVideoId(String videoId){

        Video video = videoMapper.selectByVideoId(videoId);
        List<Labels> labelsList = labelsMapper.selectByVideoId(videoId);
        List<Cause> causeList = causeMapper.selectAll();
        List<String> causeLicenseList = new ArrayList<>();
        VideoVO videoVO = new VideoVO(video, labelsList);

        return ServerResponse.createBySuccess(videoVO);
    }


}
