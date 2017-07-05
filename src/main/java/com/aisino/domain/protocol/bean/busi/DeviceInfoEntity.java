package com.aisino.domain.protocol.bean.busi;

/**
 * Created by LuckyH on 2017-03-15.
 *
 * 设备信息实体对象
 *
 */
public final class DeviceInfoEntity {

    /**
     * 纳税人识别号
     */
    private String nsrsbh;
    /**
     * 分机号
     */
    private String fjh;
    /**
     * 设备编号
     */
    private String sbbh;

    /**
     * 设备在线状态
     */
    private String status;

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
