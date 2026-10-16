package com.xhzb.nursing.mapper;

import java.util.List;
import com.xhzb.nursing.domain.NursingProjectPlan;

/**
 * 护理计划项目关联 数据层
 *
 * @author xhzb
 */
public interface NursingProjectPlanMapper
{
    /**
     * 根据护理计划ID查询护理项目明细
     *
     * @param planId 护理计划ID
     * @return 护理项目明细集合
     */
    public List<NursingProjectPlan> selectByPlanId(Long planId);

    /**
     * 批量新增护理项目明细
     *
     * @param projectPlans 护理项目明细集合
     * @return 结果
     */
    public int insertBatch(List<NursingProjectPlan> projectPlans);

    /**
     * 根据护理计划ID删除护理项目明细
     *
     * @param planId 护理计划ID
     * @return 结果
     */
    public int deleteByPlanId(Long planId);
}
