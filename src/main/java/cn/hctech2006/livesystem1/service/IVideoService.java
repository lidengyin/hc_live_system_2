//package cn.hctech2006.livesystem1.service;
//
//import cn.hctech2006.livesystem1.bean.Video;
//import cn.hctech2006.livesystem1.bean.vb.VideoVO;
//import cn.hctech2006.livesystem1.common.ServerResponse;
//import com.github.pagehelper.PageInfo;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileNotFoundException;
//
//public interface IVideoService {
//    public String handleRequest(MultipartFile multipartFile, String videoId);
//    public String insertVideo(MultipartFile uploadFile, String videoId) throws FileNotFoundException;
//    public PageInfo selectVideoByTimeAndName(VideoVO videoVO);
//    public Video selectByVideoId(String videoId);
//    public String insertVideoBefore(String videoUrl, String message) throws FileNotFoundException;
//    public ServerResponse insertVideoAndPicture(MultipartFile uploadFile) throws FileNotFoundException;
//}
