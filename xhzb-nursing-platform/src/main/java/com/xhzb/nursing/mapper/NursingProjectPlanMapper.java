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
     * 根据护理项目ID统计被护理计划引用的数量
     *
     * @param projectId 护理项目ID
     * @return 引用数量
     */
    public int countByProjectId(Long projectId);

    /**
     * 根据护理计划ID删除护理项目明细
     *
     * @param planId 护理计划ID
     * @return 结果
     */
    public int deleteByPlanId(Long planId);
}
