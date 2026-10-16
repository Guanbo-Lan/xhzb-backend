package com.xhzb.nursing.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xhzb.common.core.domain.BaseEntity;

/**
 * 护理计划对象 nursing_plan
 *
 * @author xhzb
 */
public class NursingPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 护理计划ID */
    private Long id;

    /** 护理计划名称 */
    private String planName;

    /** 排序号 */
    private Integer sortNo;

    /** 状态（0：禁用，1：启用） */
    private Integer status;

    /** 计划下的护理项目明细（仅详情接口返回，列表接口不序列化） */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<NursingProjectPlan> projectPlans;

    /**
     * 分页查询条件：护理计划名称。
     * 仅用于接口入参（接口文档中条件查询的参数名为 name），不参与响应序列化。
     */
    @JsonIgnore
    private String name;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPlanName()
    {
        return planName;
    }

    public void setPlanName(String planName)
    {
        this.planName = planName;
    }

    public Integer getSortNo()
    {
        return sortNo;
    }

    public void setSortNo(Integer sortNo)
    {
        this.sortNo = sortNo;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public List<NursingProjectPlan> getProjectPlans()
    {
        return projectPlans;
    }

    public void setProjectPlans(List<NursingProjectPlan> projectPlans)
    {
        this.projectPlans = projectPlans;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
