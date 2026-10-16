package com.xhzb.nursing.domain;

import java.math.BigDecimal;
import com.xhzb.common.core.domain.BaseEntity;

/**
 * 护理等级对象 nursing_level
 *
 * @author xhzb
 */
public class NursingLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 护理等级ID */
    private Long id;

    /** 护理等级名称 */
    private String name;

    /** 关联护理计划ID */
    private Long lplanId;

    /** 护理费用 */
    private BigDecimal fee;

    /** 护理等级说明 */
    private String description;

    /** 状态（0：禁用，1：启用） */
    private Integer status;

    /** 关联的护理计划名称（列表查询时关联查询得到） */
    private String nursingPlanName;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getLplanId()
    {
        return lplanId;
    }

    public void setLplanId(Long lplanId)
    {
        this.lplanId = lplanId;
    }

    public BigDecimal getFee()
    {
        return fee;
    }

    public void setFee(BigDecimal fee)
    {
        this.fee = fee;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getNursingPlanName()
    {
        return nursingPlanName;
    }

    public void setNursingPlanName(String nursingPlanName)
    {
        this.nursingPlanName = nursingPlanName;
    }
}
