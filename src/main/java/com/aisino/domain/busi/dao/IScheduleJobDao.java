package com.aisino.domain.busi.dao;

import com.aisino.domain.protocol.bean.busi.ScheduleJobEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：schedule job dao
 *
 * @author LuckyH
 * @create 2017-03-28 16:45
 **/
public interface IScheduleJobDao {

    List<ScheduleJobEntity> queryAllScheduleJobs() throws SQLException;

    ScheduleJobEntity queryScheduleJob(ScheduleJobEntity scheduleJobEntity)throws SQLException ;

    void saveScheduleJob(ScheduleJobEntity scheduleJobEntity) throws SQLException;
    
    void updateScheduleJob(ScheduleJobEntity scheduleJobEntity) throws SQLException;
    
    void deleteScheduleJob(ScheduleJobEntity scheduleJobEntity) throws SQLException;
}
