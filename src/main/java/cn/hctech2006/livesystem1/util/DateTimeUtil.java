package cn.hctech2006.livesystem1.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 时间字符串转换工具类
 */
public class DateTimeUtil {


    /**
     * 标准时间格式
     */
    public static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";
    //使用joda－time完成时间字符串转换
    //str->date
    //date-str

    /**
     * 字符串转换为时间参工具方法
     * @param dateTimeStr
     * @return
     */
    public static Date strToDate(String dateTimeStr){
        //使用joda设置时间格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        //转换成DateTime
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     *　时间转换为字符串方法
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        //转换时间为DateTime
        DateTime dateTime = new DateTime(date);
        //使用joda设置时间格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        //返回自定义格式时间字符串
        return dateTime.toString(STANDARD_FORMAT);
    }

    //测试
//    public static void main(String[] args) {
//        System.out.println(dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(strToDate(dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
//    }
}
