package com.xhzb.nursing.mapper;

import java.util.List;
import com.xhzb.nursing.domain.NursingLevel;

/**
 * 护理等级 数据层
 *
 * @author xhzb
 */
public interface NursingLevelMapper
{
    /**
     * 查询护理等级列表
     *
     * @param nursingLevel 护理等级查询条件
     * @return 护理等级集合
     */
    public List<NursingLevel> selectNursingLevelList(NursingLevel nursingLevel);

    /**
     * 查询所有护理等级
     *
     * @return 护理等级集合
     */
    public List<NursingLevel> selectNursingLevelAll();

    /**
     * 根据护理等级ID查询护理等级
     *
     * @param id 护理等级ID
     * @return 护理等级
     */
    public NursingLevel selectNursingLevelById(Long id);

    /**
     * 校验护理等级名称是否唯一
     *
     * @param name 护理等级名称
     * @return 护理等级
     */
    public NursingLevel checkNameUnique(String name);

    /**
     * 新增护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    public int insertNursingLevel(NursingLevel nursingLevel);

    /**
     * 修改护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    public int updateNursingLevel(NursingLevel nursingLevel);

    /**
     * 统计引用指定护理计划的护理等级数量
     *
     * @param planId 护理计划ID
     * @return 数量
     */
    public int countByPlanId(Long planId);

    /**
     * 修改护理等级状态
     *
     * @param nursingLevel 护理等级（id、status）
     * @return 结果
     */
    public int updateNursingLevelStatus(NursingLevel nursingLevel);

    /**
     * 批量删除护理等级
     *
     * @param ids 需要删除的护理等级ID数组
     * @return 结果
     */
    public int deleteNursingLevelByIds(Long[] ids);
}
