package cn.hctech2006.livesystem1.queue;//package cn.hctech2006.livesystem.queue;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * 直连型交换机
// */
//@Configuration
//public class DirectRabbitConfig {
//    /**
//     * 队列
//     * @return
//     */
//    @Bean
//    public Queue TestDirectQueue(){
//        return new Queue("TestDirectQueue",true);
//    }
//
//    /**
//     * 交换机
//     * @return
//     */
//    @Bean
//    DirectExchange TestDirectExchange(){
//        return new DirectExchange("TestDirectExchange",true,false);
//
//    }
//
//    /**
//     * 队列与交换机绑定,并且设置用于匹配健
//     * @return
//     */
//    @Bean
//    Binding bindingDirect(){
//        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
//    }
//    @Bean
//    DirectExchange lonelyDirectExchange(){
//        return new DirectExchange("lovelyDirectExchange");
//    }
//
//}
