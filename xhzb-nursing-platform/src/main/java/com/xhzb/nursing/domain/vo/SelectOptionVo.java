package com.xhzb.nursing.domain.vo;

import java.io.Serializable;

/**
 * 下拉选项对象
 *
 * @author xhzb
 */
public class SelectOptionVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 显示名称 */
    private String label;

    /** 选项值 */
    private Long value;

    public SelectOptionVo()
    {
    }

    public SelectOptionVo(String label, Long value)
    {
        this.label = label;
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Long getValue()
    {
        return value;
    }

    public void setValue(Long value)
    {
        this.value = value;
    }
}
