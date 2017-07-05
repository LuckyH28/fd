package com.aisino.domain.protocol.bean.busi;

import java.util.Date;


/**
 * DESCRIPTION：task timer object
 *
 * @author LuckyH
 * @create 2017-03-23 16:35
 **/
public class ScheduleJobEntity {

    /**
     *
     */
    private String id;

    /**
     * scheduleJob编号
     */
    private String jobNo;

    /**
     * scheduleJob名称
     */
    private String jobName;

    /**
     * scheduleJob分组
     */
    private String jobGroup;

    /**
     * scheduleJob运行状态
     */
    private String jobStatus;

    /**
     * scheduleJob状态
     */
    private String isConcurrent;

    /**
     * scheduleJob策略
     */
    private String cronExpression;

    /**
     * scheduleJob功能描述
     */
    private String description;

    /**
     * scheduleJob业务类型
     */
    private String jobBusiType;
    
    /**
     * scheduleJob业务类型对应的执行Bean对象
     */
    private String springID;
    
    /**
     *  scheduleJob更新时间
     */
    private Date updateTime;
    
    /**
     * scheduleJob操作类型，增删改
     */
    private String czlx;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobBusiType() {
        return jobBusiType;
    }

    public void setJobBusiType(String jobBusiType) {
        this.jobBusiType = jobBusiType;
    }

    public String getSpringID() {
        return springID;
    }

    public void setSpringID(String springID) {
        this.springID = springID;
    }

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

}
