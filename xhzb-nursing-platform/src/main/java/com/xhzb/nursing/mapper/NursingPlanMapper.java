package com.xhzb.nursing.mapper;

import java.util.List;
import com.xhzb.nursing.domain.NursingPlan;

/**
 * 护理计划 数据层
 *
 * @author xhzb
 */
public interface NursingPlanMapper
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
     * @param planName 护理计划名称
     * @return 护理计划
     */
    public NursingPlan checkNameUnique(String planName);

    /**
     * 新增护理计划
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int insertNursingPlan(NursingPlan nursingPlan);

    /**
     * 修改护理计划
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int updateNursingPlan(NursingPlan nursingPlan);

    /**
     * 删除护理计划
     *
     * @param id 护理计划ID
     * @return 结果
     */
    public int deleteNursingPlanById(Long id);
}
