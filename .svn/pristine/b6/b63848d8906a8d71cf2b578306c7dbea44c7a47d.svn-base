package com.aisino.domain.common.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Administrator
 * 西部CA配置文件路径和参数
 */
public final class CaConstant {

	private static final Logger LOGGER = LogManager.getLogger(CaConstant.class);

	public final static String DEFAULT_CHARSET = "UTF-8";
	private static Properties properties;
	
	 public static  String SL_CA_PRIVATE_KEY="";
	    public static  String SL_CA_PUBLIC_KEY="";
	    public static  String SL_CA_TRUST="";
	    public static  String SL_CA_PASSWORD="";
	    public static  String PTS_CA_PUBLIC_KEY="";

	static {
		try {
			properties = new Properties();
			properties.load(CaConstant.class.getResourceAsStream("/pkcs7.properties"));
			
			//添加CA加密解密配置参数
			SL_CA_PRIVATE_KEY = properties.getProperty("SL_CA_PRIVATE_KEY") ;
			SL_CA_PUBLIC_KEY = properties.getProperty("SL_CA_PUBLIC_KEY") ;
			SL_CA_TRUST = properties.getProperty("SL_CA_TRUST") ;
			SL_CA_PASSWORD = properties.getProperty("SL_CA_PASSWORD") ;
			PTS_CA_PUBLIC_KEY = properties.getProperty("PTS_CA_PUBLIC_KEY");
		} catch (Exception e) {
			LOGGER.error("pkcs7接口初始化系统参数失败!", e.fillInStackTrace());
		}
	}

	/**
	 * 读取配置文件里key的值
	 * 
	 * @param key
	 *            配置文件里的key
	 * @return
	 * @throws java.io.IOException
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
    public static String getCAPassword() throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(SL_CA_PASSWORD.replace("\\","/"))));
		String str = bis.readLine();
		return str;
	}

}
