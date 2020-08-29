package cn.hctech2006.livesystem1.controller;


import cn.hctech2006.livesystem1.service.impl.VideoServiceImpl;
import cn.hctech2006.livesystem1.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
@Api(tags = "二维码生成控制器")
@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @Autowired
    private VideoServiceImpl videoService;
    /**
     * 根据 url 生成 普通二维码
     */
    @ApiOperation(value = "生成标注监测视频二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name="videoUrl", value = "视频链接", required = true, defaultValue = "http://121.36.145.230:8888/group1/M00/00/00/eSSR5l9D7vOAFU2kAE0h7JktmxE490.avi")
    })
    @GetMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response,String  videoUrl) throws Exception {
        //这里contentType设置为图片类型，否则可能出现乱码，比如微信小程序接口获取的体验二维码是以文件形式返回，读取对应的输入流后直接输出流会出现乱码
        response.setContentType("image/*");
        String url;
        System.out.println("videoUrl:"+videoUrl);
        url = videoUrl;
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
    @ApiOperation(value = "生成标注监测视频监测结果二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name="videoId", value = "视频编号", required = true, defaultValue = "7333298a-a00f-44a8-862b-54526b8af3ea")
    })
    @GetMapping(value = "/create_video_result_QRCode.do")
    public void createVideoResultQRCode(HttpServletResponse response,String  videoId) throws Exception {
        //这里contentType设置为图片类型，否则可能出现乱码，比如微信小程序接口获取的体验二维码是以文件形式返回，读取对应的输入流后直接输出流会出现乱码
        response.setContentType("image/*");
        String url;
        System.out.println("videoResultUrl: "+" http://lidengyin1999.mynatapp.cc/video/select_one.do?vdieoId="+videoId);
        url = "http://lidengyin.nat300.top/video/select_one.do?videoId="+videoId;
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
