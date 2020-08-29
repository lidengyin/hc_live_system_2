package cn.hctech2006.livesystem1.server;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/recorded")
@Component
public class RecordedWebSocketServer {
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private Logger logger = LoggerFactory.getLogger(RealTimeWebSocketServer.class);

    /**
     * 新建客户端
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        logger.info("有新的客户端连接了:{}",session.getId());
        //新用户存入在线组
        clients.put(session.getId(),session);
    }

    /**
     * 客户端关闭
     * @param session
     */
    @OnClose
    public void onClose(Session session){

        logger.info("有用户断开了，id为:{}",session.getId());
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable){
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来的消息
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        logger.info("服务端收到客户端发来的信息:{}",message);
        //Map<String,String> messageMap = new HashMap<>();
        //messageMap.put("message")
        //this.sendAll(message);
    }

    public void sendAll(Map<String, Object> message){
        for (Map.Entry<String,Session>sessionEntry:clients.entrySet()){
//            JSONArray jsonArray = JSONObject.
            sessionEntry.getValue().getAsyncRemote().sendText(JSON.toJSONString(message));
            //sessionEntry.getValue().getAsyncRemote().sendObject(message);
        }
    }
}
