package com.xhzb.nursing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xhzb.common.constant.UserConstants;
import com.xhzb.common.utils.StringUtils;
import com.xhzb.nursing.domain.NursingPlan;
import com.xhzb.nursing.domain.NursingProjectPlan;
import com.xhzb.nursing.mapper.NursingPlanMapper;
import com.xhzb.nursing.mapper.NursingProjectPlanMapper;
import com.xhzb.nursing.service.INursingPlanService;

/**
 * 护理计划 服务层实现
 *
 * @author xhzb
 */
@Service
public class NursingPlanServiceImpl implements INursingPlanService
{
    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    /**
     * 查询护理计划列表
     *
     * @param nursingPlan 护理计划查询条件
     * @return 护理计划集合
     */
    @Override
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan)
    {
        return nursingPlanMapper.selectNursingPlanList(nursingPlan);
    }

    /**
     * 查询所有护理计划
     *
     * @return 护理计划集合
     */
    @Override
    public List<NursingPlan> selectNursingPlanAll()
    {
        return nursingPlanMapper.selectNursingPlanAll();
    }

    /**
     * 根据护理计划ID查询护理计划（含护理项目明细）
     *
     * @param id 护理计划ID
     * @return 护理计划
     */
    @Override
    public NursingPlan selectNursingPlanById(Long id)
    {
        return nursingPlanMapper.selectNursingPlanById(id);
    }

    /**
     * 校验护理计划名称是否唯一
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Override
    public boolean checkNameUnique(NursingPlan nursingPlan)
    {
        Long id = StringUtils.isNull(nursingPlan.getId()) ? -1L : nursingPlan.getId();
        NursingPlan info = nursingPlanMapper.checkNameUnique(nursingPlan.getPlanName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增护理计划（含护理项目明细）
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Override
    @Transactional
    public int insertNursingPlan(NursingPlan nursingPlan)
    {
        int rows = nursingPlanMapper.insertNursingPlan(nursingPlan);
        insertNursingProjectPlan(nursingPlan);
        return rows;
    }

    /**
     * 修改护理计划（护理项目明细全量替换）
     *
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Override
    @Transactional
    public int updateNursingPlan(NursingPlan nursingPlan)
    {
        Long planId = nursingPlan.getId();
        // 明细为全量提交，先清空旧的再按新列表写入
        nursingProjectPlanMapper.deleteByPlanId(planId);
        int rows = nursingPlanMapper.updateNursingPlan(nursingPlan);
        insertNursingProjectPlan(nursingPlan);
        return rows;
    }

    /**
     * 删除护理计划（级联删除护理项目明细）
     *
     * @param id 护理计划ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteNursingPlanById(Long id)
    {
        nursingProjectPlanMapper.deleteByPlanId(id);
        return nursingPlanMapper.deleteNursingPlanById(id);
    }

    /**
     * 新增护理计划项目明细
     *
     * @param nursingPlan 护理计划
     */
    private void insertNursingProjectPlan(NursingPlan nursingPlan)
    {
        List<NursingProjectPlan> projectPlans = nursingPlan.getProjectPlans();
        if (StringUtils.isEmpty(projectPlans))
        {
            return;
        }
        for (NursingProjectPlan projectPlan : projectPlans)
        {
            projectPlan.setPlanId(nursingPlan.getId());
            projectPlan.setCreateBy(nursingPlan.getCreateBy());
            // execute_time / execute_cycle / execute_frequency 在库中均为 NOT NULL，缺省时补齐默认值
            if (projectPlan.getExecuteTime() == null)
            {
                projectPlan.setExecuteTime("");
            }
            if (projectPlan.getExecuteCycle() == null)
            {
                projectPlan.setExecuteCycle(0);
            }
            if (projectPlan.getExecuteFrequency() == null)
            {
                projectPlan.setExecuteFrequency(1);
            }
        }
        nursingProjectPlanMapper.insertBatch(projectPlans);
    }
}
