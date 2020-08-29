package cn.hctech2006.livesystem1.conf;

import cn.hctech2006.livesystem1.server.RealTimeDeepinWebSocketServer;
import cn.hctech2006.livesystem1.server.RealTimeWebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 定时发送消息信息
 * 以<60s的频率发送给webSocket链接的对象,以防止反向代理的60s超时限制
 */
@Configuration
//开启定时任务
@EnableScheduling
public class WebSocketConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
    //指定间隔时间55秒
    @Scheduled(fixedRate = 55*1000)
    public void configureTasks() throws Exception{
        RealTimeWebSocketServer.send("overTimeFlag");
        RealTimeDeepinWebSocketServer.send("overTimeFlag");
    }
    @Bean
    @Nullable
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolScheduler = new ThreadPoolTaskScheduler();
        threadPoolScheduler.setThreadNamePrefix("SockJS-");
        threadPoolScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        threadPoolScheduler.setRemoveOnCancelPolicy(true);
        return threadPoolScheduler;
    }
}
