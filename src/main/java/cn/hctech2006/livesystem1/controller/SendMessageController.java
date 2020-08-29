package cn.hctech2006.livesystem1.controller;//package cn.hctech2006.livesystem.controller;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/rabbit")
//public class SendMessageController {
//    //提供了接受和发送等消息
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @RequestMapping(value = "/sendMessage",method = RequestMethod.GET)
//    public String sendDirectMessage(){
//        String messageId = UUID.randomUUID().toString();
//        String messageData = "test message, hello!";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageData", messageData);
//        map.put("messageTime", createTime);
//        //将消息携带绑定键值:TestDirectRouting发送到交换机TestDirectExchane
//        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
//        return "ok";
//
//    }
//
//}
