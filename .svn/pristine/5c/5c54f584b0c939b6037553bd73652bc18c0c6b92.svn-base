package com.aisino.domain.busi.service.impl;

import com.aisino.domain.busi.service.IDeviceManagerService;
import com.aisino.domain.busi.service.IRedisService;
import com.aisino.domain.common.util.GlobalVariable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LuckyH on 2017-03-11.
 *
 * 实现设备的状态管理，包括设备是否处于在线状态、是否可用状态
 *
 */
@Service("deviceManagerService")
public class DeviceManagerServiceImpl implements IDeviceManagerService {

    private static final Logger logger = LogManager.getLogger(DeviceManagerServiceImpl.class);

    @Autowired
    private IRedisService redisService;

    /**
     * 判断设备状态是否在线，如在线返回true，否则返回false
     * @return
     */
    @Override
    public boolean isOnline(String deviceNo) throws Exception{


        String online_status =  redisService.getByKey(GlobalVariable.key_pref+deviceNo);

        return Boolean.parseBoolean(online_status);
    }

    /**
     * 更新某设备为在线状态
     *
     * @param DeviceInfo
     */
    @Override
    public void updateToOnline(String DeviceInfo) throws Exception{

        //获取到设备的在线状态
        redisService.add(GlobalVariable.key_pref+DeviceInfo,"true");
        logger.info("--线程(ID:" + Thread.currentThread().getId()+").设备"+DeviceInfo+"上线！");
        //判断设备是否在线，如离线则更新为在线

    }

    /**
     * 更新某设备为离线状态
     *
     * @param DeviceInfo
     */
    @Override
    public void updateToOffline(String DeviceInfo) throws Exception{
        redisService.add(GlobalVariable.key_pref+DeviceInfo,"false");
        logger.info("--线程(ID:" + Thread.currentThread().getId()+").设备"+DeviceInfo+"下线！");
    }

    /**
     * 管理设备功能状态
     *
     * @param DeviceInfo       设备基本信息
     * @param DeviceStatusInfo 设备状态信息
     */
    @Override
    public void managerDeviceFunctionStatus(String DeviceInfo, String DeviceStatusInfo) throws Exception{

    }


}
