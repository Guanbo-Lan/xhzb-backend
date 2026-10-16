package com.xhzb.nursing.domain;

import java.math.BigDecimal;
import com.xhzb.common.core.domain.BaseEntity;

/**
 * 护理项目对象 nursing_project
 *
 * @author xhzb
 */
public class NursingProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 护理项目ID */
    private Long id;

    /** 护理项目名称 */
    private String name;

    /** 排序号 */
    private Integer orderNo;

    /** 单位 */
    private String unit;

    /** 价格 */
    private BigDecimal price;

    /** 图片地址 */
    private String image;

    /** 护理要求 */
    private String nursingRequirement;

    /** 状态（0：禁用，1：启用） */
    private Integer status;

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

    public Integer getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getNursingRequirement()
    {
        return nursingRequirement;
    }

    public void setNursingRequirement(String nursingRequirement)
    {
        this.nursingRequirement = nursingRequirement;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
