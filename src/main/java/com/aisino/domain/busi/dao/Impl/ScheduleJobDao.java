package com.aisino.domain.busi.dao.Impl;

import com.aisino.domain.busi.dao.IScheduleJobDao;
import com.aisino.domain.protocol.bean.busi.ScheduleJobEntity;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：schedule job dao impl
 *
 * @author LuckyH
 * @create 2017-03-28 16:40
 **/

@Repository("scheduleJobDao")
public class ScheduleJobDao extends BaseDao implements IScheduleJobDao{
	
//	private  ScheduleJobEntity scheduleJobEntity;
    @Override
    public List<ScheduleJobEntity> queryAllScheduleJobs() throws SQLException {
//    	scheduleJobEntity.setJobBusiType("01");
//    	scheduleJobEntity.setSpringID("republishTask");
        return getSqlSessionTemplate().selectList("selectScheduleJobList");
    }

    @Override
    public ScheduleJobEntity queryScheduleJob(ScheduleJobEntity scheduleJobEntity) throws SQLException {
        return getSqlSessionTemplate().selectOne("selectScheduleJob",scheduleJobEntity);
    }

    @Override
    public void saveScheduleJob(ScheduleJobEntity scheduleJobEntity) throws SQLException {

        getSqlSessionTemplate().insert("insertScheduleJob",scheduleJobEntity);

    }

	@Override
	public void updateScheduleJob(ScheduleJobEntity scheduleJobEntity)
			throws SQLException {
		 getSqlSessionTemplate().update("updateScheduleJob",scheduleJobEntity);
		
	}

	@Override
	public void deleteScheduleJob(ScheduleJobEntity scheduleJobEntity)
			throws SQLException {
		 getSqlSessionTemplate().delete("deleteScheduleJob",scheduleJobEntity);
		
	}
    
    
}
