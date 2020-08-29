package cn.hctech2006.livesystem1.util;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

public class ImageAndVideoUtil {

    public static  void findImgName(String imgUrl, MultipartFile multipartFile)throws IOException {

        //获得文件所在目录
        File folder = new File(imgUrl);
        //添加目录
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        try{
            //转移加载文件
            multipartFile.transferTo(folder);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String showImage(String imageUrl, HttpServletResponse response
    )throws IOException{
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        File file = new File(imageUrl);
        BufferedImage bi = ImageIO.read(new FileInputStream(file));
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi,"jpg",out);
        try{
            out.flush();
        }finally {
            out.close();
        }
        return null;
    }
    /**
     * 捕捉图片第一帧
     * @throws Exception
     */
    public static void fetchFrame(String videoUrl,String imgUrl)throws Exception{
//

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoUrl);
        Java2DFrameConverter converter = new Java2DFrameConverter();

        frameGrabber.start();
        //解码长度
        int length = frameGrabber.getLengthInFrames();
        //时间
        int i = 0;
        Frame frame = null;
        while(i < length){
            //过滤前五帧，避免出现全黑的图片
            frame = frameGrabber.grabFrame();
            if(i > 10 && (frame.image != null)){
                break;
            }
            i ++;
        }
        // Frame frame = frameGrabber.grabFrame();
        BufferedImage bufferedImage = converter.convert(frame);
        //照片保存文件夹
        File targetFile = new File(imgUrl);
        //文件夹不存在就新建
        if(!targetFile.isDirectory()){
            targetFile.mkdirs();
        }
        //写入文件夹,以jpg图片格式
        ImageIO.write(bufferedImage, "jpg", targetFile);
        //停止转化为帧
        frameGrabber.stop();
    }
    /**
     * 获取视频所有帧
     * @throws Exception
     */
    public static void fetchALLFrame(String videoUrl,String imgUrl)throws Exception{
//

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoUrl);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        frameGrabber.start();
        //解码长度
        int length = frameGrabber.getLengthInFrames();
        //时间
        int i = 0;
        Frame frame = null;
        while(i < length){
            //过滤前五帧，避免出现全黑的图片
            frame = frameGrabber.grabFrame();
            if((frame.image != null)){
                // Frame frame = frameGrabber.grabFrame();
                BufferedImage bufferedImage = converter.convert(frame);
                //照片保存文件夹
                File targetFile = new File(imgUrl+i+".jpg");
                //文件夹不存在就新建
                if(!targetFile.isDirectory()){
                    targetFile.mkdirs();
                }
                //写入文件夹,以jpg图片格式
                ImageIO.write(bufferedImage, "jpg", targetFile);
            }
            i ++;
        }

        //停止转化为帧
        frameGrabber.stop();
    }

    public static String showVideo(String videoUrl, HttpServletResponse response)throws IOException{
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("video/mp4");
        FileInputStream fis = null;
        OutputStream os = null ;
        File file = new File(videoUrl);
        fis = new FileInputStream(file);
        int size = fis.available(); // 得到文件大小
        byte data[] = new byte[size];
        fis.read(data); // 读数据
        fis.close();
        fis = null;
        response.setContentType("video/mp4"); // 设置返回的文件类型
        os = response.getOutputStream();
        os.write(data);
        try{
            os.flush();
        }finally {
            os.close();
        }
        return null;
    }

    public static void main(String[]args) throws Exception {
        String url = ResourceUtils.getURL("").getPath()+"image/"+"视频三";
        System.out.println(url);
        fetchALLFrame("https://public-ldy.oss-cn-beijing.aliyuncs.com/viedo-03.avi", url);
    }
}

