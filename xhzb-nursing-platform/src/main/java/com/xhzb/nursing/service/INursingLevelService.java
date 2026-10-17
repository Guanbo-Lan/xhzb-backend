package com.xhzb.nursing.service;

import java.util.List;
import com.xhzb.nursing.domain.NursingLevel;

/**
 * 护理等级 服务层
 *
 * @author xhzb
 */
public interface INursingLevelService
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
     * @param nursingLevel 护理等级
     * @return 结果
     */
    public boolean checkNameUnique(NursingLevel nursingLevel);

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
     * 修改护理等级状态（启用/禁用）
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
