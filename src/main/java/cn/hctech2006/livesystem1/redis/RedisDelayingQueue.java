package cn.hctech2006.livesystem1.redis;//package cn.hctech2006.livesystem.redis;
//
//import cn.hctech2006.livesystem.bean.Label;
//import com.alibaba.fastjson.TypeReference;
//import org.springframework.data.redis.core.RedisTemplate;
//
//
//import java.lang.reflect.Type;
//
///**
// * 延时队列
// * @param <T>
// */
//public class RedisDelayingQueue<T> {
//    static class TaskItem<T> {
//        public String id;
//        public T msg;
//    }
//    //如果fastjson序列化对象中存在generic类型时,需要使用TypeReference
//    private Type TaskType = new TypeReference<TaskItem<T>>(){
//
//    }.getType();
//    private RedisTemplate<String, Label> redisTemplate
//    private String queueKey;
//
//    public RedisDelayingQueue(RedisProperties.Jedis jedis, String queueKey) {
//        this.jedis = jedis;
//        this.queueKey = queueKey;
//    }
//}
