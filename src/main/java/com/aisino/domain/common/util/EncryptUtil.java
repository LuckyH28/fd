package com.aisino.domain.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aisino.PKCS7;
import com.aisino.domain.common.enums.CaConstant;

public class EncryptUtil {
	
	private static final Logger logger = LogManager.getLogger(EncryptUtil.class);
	
	private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
	
	/**
     * 缓存证书信任链
     */
    public static Map<String, PKCS7>  PKCS7_LIST = new ConcurrentHashMap<String, PKCS7>();
    
    private static byte[] tempContent;
	
	
	/*********************************BASE64*Beginning********************************************/
    /**
     * 加密
     * @param res 字节数组
     * @return 编码后字节数组
     */
    public static byte[] encode(byte[] res) {
        try {
            final Base64 base = new Base64();
            return base.encode(res);
        } catch (Exception e) {
        	logger.error("未知异常:{}", e);
            return new byte[0];
        }
    }

    /**
     * 编码
     *
     * @param res 字节数组
     * @return 编码后字节数组
     */
    public static String encodeToString(byte[] res) {
        try {
            final Base64 base = new Base64();
            return base.encodeToString(res);
        } catch (Exception e) {
        	logger.error("未知异常:{}", e);
            return null;
        }
    }

    /**
     * 编码
     *
     * @param str 需要编码的字符串
     * @return 编码后的字符串
     */
    public static String encode(String str) {
        try {
            return new String(encode(str.getBytes(GlobalVariable.CHARSET)), GlobalVariable.CHARSET);
        } catch (UnsupportedEncodingException e) {
        	logger.error("不支持的编码:{}, 发生异常:{}", GlobalVariable.CHARSET, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 编码(json)
     *
     * @param str 需要编码的字符串
     * @return 编码后的字符串
     */
    public static String encodeJson(String str) {
        try {
        	
        	//原字符编码是GBK
            return new String(encode(str.getBytes(GlobalVariable.CHARSET)), GlobalVariable.CHARSET);
        } catch (UnsupportedEncodingException e) {
        	logger.error("不支持的编码:{}, 发生异常:{}", GlobalVariable.CHARSET, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 解码
     *
     * @param str 需要解码的字符串
     * @return 解码后的字符串
     */
    private static String decode(String str) {
        try {
            return new String(new Base64().decode(str.getBytes(GlobalVariable.CHARSET)), GlobalVariable.CHARSET);
        } catch (UnsupportedEncodingException e) {
        	logger.error("不支持的编码:{}, 发生异常:{}", GlobalVariable.CHARSET, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 解码
     *
     * @param str 需要解码的byte 数组
     * @return 解码后的byte 数组
     */
    private static byte[] decode(byte[] str) {
        return new Base64().decode(str);
    }
    /*********************************BASE64*Ending********************************************/
    
    /*********************************CA加密*Beginning
     * @throws IOException ********************************************/
    public static byte[] encodeByCA(String content ,String certNo) throws IOException {
    	
    	String pfxurl = CaConstant.PTS_CA_PUBLIC_KEY.replace("~", certNo);
    	byte[] publicPFXBytes = FileUtils.readFileToByteArray(new File(pfxurl));
//    	PKCS7.setpfx(nsrsbh);
		//从map取pkcs
		PKCS7 pkcs = PKCS7_LIST.get("51SAASCA");
		if(pkcs == null){
			pkcs = PKCS7.getPkcs();
			//放入map
			PKCS7_LIST.put("51SAASCA", pkcs);
		}
		tempContent = pkcs.pkcs7Encrypt(content, publicPFXBytes);
    	
    	return tempContent;
    }
    
    
    public static byte[] decodeByCA(String content ,String certNo){

//    	String pfxurl = SL_CA_PRIVATE_KEY.replace("~", code);
//		String pfxurl2 = SL_CA_PUBLIC_KEY.replace("~", code);
//		String pfxurl3 = SL_CA_TRUST.replace("~", code);
//		String pfxurl4 = SL_CA_PASSWORD.replace("~", code);
		
		if(!new File(CaConstant.SL_CA_PRIVATE_KEY).exists() || !new File(CaConstant.SL_CA_PUBLIC_KEY).exists() || !new File(CaConstant.SL_CA_TRUST).exists() || !new File(CaConstant.SL_CA_PASSWORD).exists()){
			return null;
		}else{
			//CA解密
			//从map取pkcs
			PKCS7 pkcs = PKCS7_LIST.get("51SAASCA");
			if(pkcs == null){
				pkcs = PKCS7.getPkcs();
				//放入map
				PKCS7_LIST.put("51SAASCA", pkcs);
			}
			tempContent = pkcs.pkcs7Decrypt(tempContent);
			if(tempContent == null){
				return null;
			}
		}
    
    	
      	return tempContent;
    	
    }
    /*********************************CA加密*Ending********************************************/
    
	
	/*********************************3DES加密*Beginning********************************************/
	
	/**
	 * 加密算法
	 * password为加密密钥，长度为24字节
	 * src为被加密的数据缓冲区（源）
	 */
    public static byte[] encryptMode(String password, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(password.getBytes(), Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
        	logger.error("未知",e1);
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
        	logger.error("未知",e2);
            e2.printStackTrace();
        } catch (Exception e3) {
        	logger.error("未知",e3);
            e3.printStackTrace();
        }
        return null;
    }

    
    /**
	 * 解密算法
	 * keybyte为加密密钥，长度为24字节
	 * src为被加密的数据缓冲区（源）
	 */
    public static byte[] decryptMode(String password, byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(password.getBytes(), Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
        	logger.error("未知",e1);
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
        	logger.error("未知",e2);
            e2.printStackTrace();
        } catch (Exception e3) {
        	logger.error("未知",e3);
            e3.printStackTrace();
        }
        return null;
    }

    //转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        StringBuffer hs=new StringBuffer("");
        String stmp="";

        for (int n=0;n<b.length;n++) {
            stmp=(Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs.append("0").append(stmp);
            else hs.append(stmp);
            if (n<b.length-1)  hs.append(":");
        }
        return hs.toString().toUpperCase();
    }
    
    /*********************************3DES加密*Ending********************************************/
}
