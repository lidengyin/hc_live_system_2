package cn.hctech2006.livesystem1.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 文件服务器工具类
 */
public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser=PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");


    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    /**
     * 是否上传成功
     * @param fileList
     * @return
     */
    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("img", fileList);
        logger.info("结束上传，上传结果:{}",result);
        return result;
    }

    /**
     * 上传具体逻辑
     *
     * @param remotePath    remotePath使得在ftp根目录下继续划分目录
     * @param fileList
     * @return
     */
    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        //设置文件输入流
        FileInputStream fis = null;

        boolean uploaded = true;
        //如果ftp服务器连接成功
        if (connectServer(ip,port,user,pwd)){
            try {
                //修改当前存储目录
                ftpClient.changeWorkingDirectory(remotePath);
                //修改缓存大小
                ftpClient.setBufferSize(1024);
                //修改字符编码
                ftpClient.setControlEncoding("UTF-8");
                //设置文件类型为二进制文件类型
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //打开本地被动模式
                ftpClient.enterLocalPassiveMode();
                //开始上传文件
                for(File file : fileList){
                    fis = new FileInputStream(file);
                    //流文件存储
                    ftpClient.storeFile(file.getName(), fis);
                }
            } catch (IOException e) {
                logger.error("上传文件异常(目录切换)",e);
                uploaded = false;
            }finally {
                //关闭流
                fis.close();
                //关闭服务器连接
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    /**
     * 服务器连接具体逻辑封装
     * @param ip
     * @param port
     * @param user
     * @param pwd
     * @return
     */
    private boolean connectServer(String ip, int port, String user, String pwd){
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);
        }catch (IOException e){
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
