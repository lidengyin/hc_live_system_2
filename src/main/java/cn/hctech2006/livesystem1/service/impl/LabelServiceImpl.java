package cn.hctech2006.livesystem1.service.impl;

import cn.hctech2006.livesystem1.bean.Label;
import cn.hctech2006.livesystem1.mapper.LabelMapper;
import cn.hctech2006.livesystem1.server.RecordedWebSocketServer;
import cn.hctech2006.livesystem1.service.ILabelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class LabelServiceImpl implements ILabelService {
    @Autowired
    private RecordedWebSocketServer recordedWebSocketServer;
    @Autowired
    private LabelMapper labelMapper;
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public String insertLabel(Label label) {
//            String addAll = null;
//            Map<String, String> map = new HashMap<>();
//                String []speed = label.getSpeed().split(",");
//                String []line = label.getLine().split(",");
//                String
//            addAll += "/"+speed[1]+"/"+line[1];


            try {
                labelMapper.insert(label);
                return "上传成功";
            }catch (Exception e){
                e.printStackTrace();
                return "上传失败";
            }

    }

    public void selectByVideoId(String videoId) throws InterruptedException {
        //PageHelper.startPage(pageNum,pageSize);
//        System.out.println(System.currentTimeMillis());
//        List<Label> labels = labelMapper.selectByVideoId(videoId);
//        //PageInfo pageInfo = new PageInfo(labels);
//        for (Label label : labels) {
//            Map<String, Object> map = new HashMap<>();
//            //System.out.println("label:"+label.);
//            map.put("label", label);
//            recordedWebSocketServer.sendAll(map);
//            Thread.sleep(42);
//        }
        executor.execute(new Thread(new MyTask(videoId)));
//        return labels;
    }

    class MyTask implements Runnable {
        private String videoId;

        public MyTask(String videoId) {
            this.videoId = videoId;
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
            List<Label> labels = labelMapper.selectByVideoId(videoId);
            //PageInfo pageInfo = new PageInfo(labels);
            for (Label label : labels) {
                Map<String, Object> map = new HashMap<>();
                //System.out.println("label:"+label.);
                map.put("label", label);
                recordedWebSocketServer.sendAll(map);
                try {
                    Thread.sleep(42);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
