package cn.hctech2006.livesystem1.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 高复用返回封装对象
 * @param <T>
 */
//JSON序列化忽略是NULL的字段，主要是失败时的data不显示
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    //返回状态
    private int status;
    //返回信息
    private String msg;
    //返回对象
    private T data;

    /**
     * 私有构造器，在调用公共方法时，使用
     * @param status
     */
    private ServerResponse(int status){
        this.status = status;
    }

    /**
     * 私有构造器，在调用公共方法时，使用
     * @param status
     * @param msg
     */
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 私有构造器，在调用公共方法时，使用
     * @param status
     * @param data
     */
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 私有构造器，在调用公共方法时，使用
     * @param status
     * @param msg
     * @param data
     */
    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 公共开放方法
     * @return
     */
    //序列化不会显示在JSON里面
    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }
    /**
     * 公共开放方法
     */
    public int getStatus() {
        return status;
    }
    /**
     * 公共开放方法
     */
    public String getMsg() {
        return msg;
    }
    /**
     * 公共开放方法
     */
    public T getData() {
        return data;
    }

    /**
     * 公共静态成功返回方法
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 公共静态成功返回方法
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 公共静态成功返回方法
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    /**
     * 公共静态成功返回方法
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    //解决String与data同时传输的问题
    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 公共静态失败返回方法
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    /**
     * 公共静态失败返回方法，返回默认失败信息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(String msg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg);
    }

    /**
     * 公共静态失败返回方法,根据状态码自定义失败信息
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(int code,String msg){
        return new ServerResponse<T>(code, msg);
    }
}
