package com.aisino.domain.protocol.bean.busi;

/**
 * Created by LuckyH on 2017-03-15.
 */
public final class ResponseInfoEntity {

    private String code;
    private String message;
    private String content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
