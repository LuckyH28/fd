package com.aisino.domain.protocol.bean.busi;

import com.aisino.domain.common.util.CommonUtil;

/**
 * Created by LuckyH on 2017-03-15.
 *
 * 业务对象实体
 *
 */
public final class RequestBusiEntity {

    /**
     * 协议版本号
     */
    private String version;
    /**
     * 加密方式
     */
    private String encrypt;

    /**
     * 业务类型，主要分为设备状态查询、消息发布等
     */
    private String busiType;
    /**
     * 业务内容
     */
    private String content;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public static void main(String[] args) {
    	QueueInfoEntity queueInfoEntity = new QueueInfoEntity();
    	queueInfoEntity.setQueueName("");
    	queueInfoEntity.setQueueConsumer("51IMFP");
    	queueInfoEntity.setQueueNO("PTSBM");
    	queueInfoEntity.setQueueBusiType("IM");
    	queueInfoEntity.setQueueService("51SAAS");
    	queueInfoEntity.setQueueLevel("1");
    	
		RequestBusiEntity requestBusiEntity = new RequestBusiEntity();
		requestBusiEntity.setBusiType("busi_init_queue");
		requestBusiEntity.setContent(CommonUtil.objectToGson(queueInfoEntity));
		
		String requestStr = CommonUtil.objectToGson(requestBusiEntity);
		System.out.println(requestStr);
	}
}
