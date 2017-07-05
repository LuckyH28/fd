package com.aisino.domain.common.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * DESCRIPTION：密钥相关功能
 *
 * @author LuckyH
 * @create 2017-03-31 9:35
 **/
public class CryptoUtil {

    private static final Logger logger = LogManager.getLogger(CryptoUtil.class);

//     private static String tempStr = new String();
        private static String Algorithm = "DESede";
    /**
     * 3des解密
     * @param content
     * @param password
     * @return
     */
    public static String decodeBy3Des(String content,String password){
        //定义 加密算法,可用 DES,DESede,Blowfish
        Cipher c1 = null;
        String str = null;
        //生成密钥
        SecretKey deskey = new SecretKeySpec(password.getBytes(), Algorithm);
        //加密
        try {
            c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            str = decodeByBase64(new String (c1.doFinal(content.getBytes())));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            return str;
        }

    }

    /**
     * 3des加密
     * @param content
     * @param password
     * @return
     */
    public static String encodeBy3Des(String content,String password){

         //定义 加密算法,可用 DES,DESede,Blowfish
        Cipher c1 = null;
        String str = null;
        //生成密钥
        SecretKey deskey = new SecretKeySpec(password.getBytes(), Algorithm);

//        content = encodeByBase64(content);
        //加密
        try {
            byte[] src = content.getBytes("UTF-8");
            c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
//            System.out.println(c1.doFinal(content.getBytes()));
            str =  new String (c1.doFinal(src),"UTF-8");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            return str;
        }
       
    }

    /**
     * CA解密
     * @param content
     * @param key
     * @return
     */
    public static String decodeByCA(String content,String key){

        return content;
    }

    /**
     * CA加密
     * @param content
     * @param key
     * @return
     */
    public static String encodeByCA(String content,String key){

        return content;
    }

    /**
     * base64解密
     * @param content
     * @return
     */
    public static String decodeByBase64(String content){
        return new String(new Base64().decode(content));
    }

    /**
     * base64加密
     * @param content
     * @return
     */
    public static String encodeByBase64(String content){

        return new Base64().encodeAsString(content.getBytes());
    }

    public static void main(String[] args) {
        String str = "12345678";
        String str1="TXkgbmFtZSBpcyBodWxpYW5nICzog6Hkuq4=";
        System.out.println(encodeBy3Des(str,"111111111111111111111111"));
//        System.out.println(decodeBy3Des(str,"111111111111111111111111"));



    }
}
