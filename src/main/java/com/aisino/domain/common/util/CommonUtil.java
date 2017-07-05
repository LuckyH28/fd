package com.aisino.domain.common.util;

import com.aisino.domain.protocol.bean.GlobalInfo;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by LuckyH on 2017-03-07.
 */
public class CommonUtil {

    private static String tempContent;

    public static String getDateByFormat(Date date , String dateFormatStr){
        SimpleDateFormat dateFormat =  new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date);
    }

    /**
     * 对象转为Json
     * @param object
     * @return
     */
    public static String objectToGson(Object object){
        return new GsonBuilder().create().toJson(object);
    }

    /**
     * Json转为对象
     * @param gsonStr
     * @param clazz
     * @return
     */
    public static Object gsonToObject(String gsonStr,Class<?> clazz){
        return new GsonBuilder().create().fromJson(gsonStr,clazz);
    }

    
    
    /**
     * 字符串分割符转换为数组
     * @param srcStr
     * @param separator
     * @return str[] 返回分割后字符组 
     */
    public static String[] arrToStrBySep(String srcStr ,String separator){
    	
    	String[] str = srcStr.split(separator);
    	
    	return str;
    } 
    
    /**
     * 字符串组以分隔符组装字符串
     * @param srcStr
     * @param separator
     * @return
     */
    public static String strToArrBySep(String[] srcStr ,String separator){
    	
    	return StringUtils.join(srcStr,separator); 
    }
    
    
    
    

    /**
     * 加密
     * @param content
     * @param encodeType
     * @return
     */
    public static String encode(String content ,String encodeType){
        //解析数据，并设置为utf8字符编码
//        byte[] tempContent = nullToEmpty(content).getBytes("UTF-8");
        if(encodeType.equals("3des")){
            tempContent =  CryptoUtil.encodeBy3Des(content,"111111");
        }else if (encodeType.equals("base64")){
            tempContent =  CryptoUtil.encodeByBase64(content);
        }else if (encodeType.equals("CA")){
            tempContent =  CryptoUtil.encodeByCA(content,"11111");
        }else{
            //加密类型有误
            return "非法加密方式";
        }
        return tempContent;
    }

    /**
     * 解密
     * @param content
     * @param decodeType
     * @return
     */
    public static String decode(String content,String decodeType){

        if(decodeType.equals("3des")){
            tempContent =  CryptoUtil.decodeBy3Des(content,"111111");
        }else if (decodeType.equals("base64")){
            tempContent =  CryptoUtil.decodeByBase64(content);
        }else if (decodeType.equals("CA")){
            tempContent =  CryptoUtil.decodeByCA(content,"11111");
        }else{
            //加密类型有误
            return "非法加密方式";
        }
        return tempContent;
    }

}
