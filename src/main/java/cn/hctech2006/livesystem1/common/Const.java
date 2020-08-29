package cn.hctech2006.livesystem1.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 常量类
 */
public class Const {
    public static final String CURRENT_USER = "current_user";

    //邮箱常量
    public static final String EMAIL = "email";

    //用户名常量
    public static final String USERNAME = "username";

    //商品列表排序
    public interface ProductListOrderBy{

        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }
    public enum  Illegal{
        LINE_BALL("压线",1),
        SPEED("速度",0),
        LINE_CHAGE("违规变道",2),
        COMIDY_PEOPLE("不礼让行人",3)
        ;

        Illegal(String value, int code) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

    }

    /**
     * 购物车常量
     */
    public interface Cart{
        int CHECKED=1;
        int UNCHECKED=0;
        //g购买数量超出限制
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        //购买数量未超出限制
        String LIMIT_NUM_SUCCESS= "LIMIT_NUM_SUCCESS";
    }


    //设置角色分类接口
    public interface Role{
        //普通用户
        int ROLE_CUSTOMER=0;
        //管理员
        int ROLE_ADMIN=1;
    }

    /**
     * 商品状态
     */
    public enum ProductStatusEnum{
        ON_SALE("在售",1);
        private String value;
        private int code;


        ProductStatusEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static ProductStatusEnum codeof(int code){
            for (ProductStatusEnum productStatusEnum : values()){
                if (productStatusEnum.getCode() == code){
                    return productStatusEnum;
                }

            }
            throw new RuntimeException("没有找到对应的枚举");
        }

    }

    /**
     * 订单状态
     */
    public enum OrderStatusEnum{
        CABCALED(0,"已取消"),
        NO_PAY(10,"未支付"),
        PATD(20,"已付款"),
        SHIPPED(40, "已发货") ,
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭");

        private int code;
        private String value;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public static OrderStatusEnum codeOf(int code){
            for (OrderStatusEnum orderStatusEnum : values()){
                if (orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }

            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    /**
     * 支付宝异步通知回调状态
     */
    public interface AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";
        String TRADE_STATUS_TRADE_FINISHED = "TRADE_FINISHED";
        String TRADE_STATUS_TRADE_CLOSED = "TRADE_CLOSED";
        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";

    }
    public enum PayPlatformEnum{
            ALIPAY(1, "支付宝")
            ;
        private int code;
        private String value;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        PayPlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }
    }

    /**
     * 支付类型
     */
    public enum PaymentType{
        ONLINE_PAY(1, "在线支付");

        private int code;

        private String value;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        PaymentType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public static PaymentType codeOf(int code){
            for (PaymentType paymentType : values()){
                if (paymentType.getCode() == code){
                    return paymentType;
                }

            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }



}
