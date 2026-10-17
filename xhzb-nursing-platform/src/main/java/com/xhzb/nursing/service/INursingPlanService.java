package com.xhzb.nursing.service;

import java.util.List;
import com.xhzb.nursing.domain.NursingPlan;

/**
 * 护理计划 服务层
 *
 * @author xhzb
 */
public interface INursingPlanService
{
    /**
     * 查询护理计划列表
     *
     * @param nursingPlan 护理计划查询条件
     * @return 护理计划集合
     */
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan);

    /**
     * 查询所有护理计划
     *
     * @return 护理计划集合
     */
    public List<NursingPlan> selectNursingPlanAll();

    /**
     * 根据护理计划ID查询护理计划（含护理项目明细）
     *
     * @param id 护理计划ID
     * @return 护理计划
     */
    public NursingPlan selectNursingPlanById(Long id);

    /**
     * 校验护理计划名称是否唯一
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public boolean checkNameUnique(NursingPlan nursingPlan);

    /**
     * 新增护理计划（含护理项目明细）
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int insertNursingPlan(NursingPlan nursingPlan);

    /**
     * 修改护理计划（护理项目明细全量替换）
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int updateNursingPlan(NursingPlan nursingPlan);

    /**
     * 修改护理计划状态（启用/禁用）
     *
     * @param nursingPlan 护理计划（id、status）
     * @return 结果
     */
    public int updateNursingPlanStatus(NursingPlan nursingPlan);

    /**
     * 批量删除护理计划（级联删除护理项目明细）
     *
     * @param ids 需要删除的护理计划ID数组
     * @return 结果
     */
    public int deleteNursingPlanByIds(Long[] ids);
}
