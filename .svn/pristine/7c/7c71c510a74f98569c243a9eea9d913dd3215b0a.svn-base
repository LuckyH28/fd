package com.aisino.domain.common.util;

import com.aisino.domain.protocol.bean.InterfaceInfo;
import com.aisino.domain.protocol.bean.busi.ResponseInfoEntity;

public class ProtocolUtil {
	
	 private static byte[] tempContent;
	
//	private static InterfaceInfo interfaceInfo = new InterfaceInfo();
	
	public static InterfaceInfo assemblyInterfaceInfo(InterfaceInfo interfaceInfo){
		
		/*interfaceInfo.setData(data);
		interfaceInfo.setGlobalInfo(globalInfo);
		interfaceInfo.setReturnStateInfo(returnStateInfo);*/
		
		return interfaceInfo;
		
	} 
	
	public static String resolveContent(InterfaceInfo interfaceInfo){
		
		switch (interfaceInfo.getData().getDataDescription().getZipCode()) {
		case GlobalVariable.ZIPCODE_COMPRESSED:
			break;
		case GlobalVariable.ZIPCODE_UNCOMPRESSED:
			break;

		default:
			break;
		}
		return null;
	}
	
	public static String compressData(String content){
		
		
		return content;
	}
	
	public static String decompressData(String content){
		
		return content;
	}
}
