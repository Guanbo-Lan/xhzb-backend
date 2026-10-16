package com.xhzb.nursing.domain;

import com.xhzb.common.core.domain.BaseEntity;

/**
 * 护理计划项目关联对象 nursing_project_plan
 *
 * @author xhzb
 */
public class NursingProjectPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 护理计划ID */
    private Long planId;

    /** 护理项目ID */
    private Long projectId;

    /** 执行周期（0：天，1：周，2：月） */
    private Integer executeCycle;

    /** 执行频次 */
    private Integer executeFrequency;

    /** 计划执行时间，形如 15:08:00 */
    private String executeTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Integer getExecuteCycle()
    {
        return executeCycle;
    }

    public void setExecuteCycle(Integer executeCycle)
    {
        this.executeCycle = executeCycle;
    }

    public Integer getExecuteFrequency()
    {
        return executeFrequency;
    }

    public void setExecuteFrequency(Integer executeFrequency)
    {
        this.executeFrequency = executeFrequency;
    }

    public String getExecuteTime()
    {
        return executeTime;
    }

    public void setExecuteTime(String executeTime)
    {
        this.executeTime = executeTime;
    }
}
