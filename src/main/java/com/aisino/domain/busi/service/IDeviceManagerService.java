package com.aisino.domain.busi.service;

/**
 * Created by LuckyH on 2017-03-11.
 *
 * 实现设备的状态管理，包括设备是否处于在线状态、是否可用状态
 */
public interface IDeviceManagerService {

    /**
     * 判断设备状态是否在线，如在线返回true，否则返回false
     * @param deviceNo
     * @return
     */
    boolean isOnline(String deviceNo)throws Exception;

    /**
     * 根据设备是否为离线状态，进而更新某设备为在线状态
     * @param DeviceInfo
     */
    void updateToOnline(String DeviceInfo)throws Exception;

    /**
     * 根据获取到遗言信息，更新某设备为离线状态
     * @param DeviceInfo
     */
    void updateToOffline(String DeviceInfo)throws Exception;


    /**
     * 管理设备功能状态，包括设备停用启用
     * @param DeviceInfo 设备基本信息
     * @param DeviceStatusInfo 设备状态信息
     */
    void managerDeviceFunctionStatus(String DeviceInfo, String DeviceStatusInfo)throws Exception;
}
