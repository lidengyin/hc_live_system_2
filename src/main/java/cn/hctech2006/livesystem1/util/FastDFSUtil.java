package cn.hctech2006.livesystem1.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * FastDFS文件上传工具
 */
public class FastDFSUtil {
    public static String upload(MultipartFile file){
        String conf_filename="fdfs_client.conf";

        try {
            ClientGlobal.init(conf_filename);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            String fileName = ResourceUtils.getURL("").getPath()+ UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File direct = new File(fileName);
            if (!direct.exists()) direct.mkdirs();
            System.out.println("file: "+fileName);
            File upload_temp_file;
            upload_temp_file = new File(fileName);
            System.out.println(upload_temp_file.getAbsolutePath());

            file.transferTo(upload_temp_file);
            System.out.println("absolutePath: "+upload_temp_file.getAbsolutePath());
            String fileIds[] = storageClient.upload_file(upload_temp_file.getAbsolutePath() ,null, null);
            upload_temp_file.delete();
            String path = "http://121.36.145.230:8888/"+fileIds[0]+"/"+fileIds[1];
            return path;
        } catch (IOException | MyException e) {
            e.printStackTrace();
            return "上传失败";
        }


    }
    public static String upload(File upload_temp_file){
        String conf_filename="fdfs_client.conf";

        try {
            ClientGlobal.init(conf_filename);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            String fileIds[] = storageClient.upload_file(upload_temp_file.getAbsolutePath() ,null, null);
            upload_temp_file.delete();
            String path = "http://121.36.145.230:8888/"+fileIds[0]+"/"+fileIds[1];
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        } catch (MyException e) {
            e.printStackTrace();
            return "上传失败";
        }


    }
}

