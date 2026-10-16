package com.xhzb.nursing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xhzb.common.constant.UserConstants;
import com.xhzb.common.utils.StringUtils;
import com.xhzb.nursing.domain.NursingLevel;
import com.xhzb.nursing.mapper.NursingLevelMapper;
import com.xhzb.nursing.service.INursingLevelService;

/**
 * 护理等级 服务层实现
 *
 * @author xhzb
 */
@Service
public class NursingLevelServiceImpl implements INursingLevelService
{
    @Autowired
    private NursingLevelMapper nursingLevelMapper;

    /**
     * 查询护理等级列表
     *
     * @param nursingLevel 护理等级查询条件
     * @return 护理等级集合
     */
    @Override
    public List<NursingLevel> selectNursingLevelList(NursingLevel nursingLevel)
    {
        return nursingLevelMapper.selectNursingLevelList(nursingLevel);
    }

    /**
     * 查询所有护理等级
     *
     * @return 护理等级集合
     */
    @Override
    public List<NursingLevel> selectNursingLevelAll()
    {
        return nursingLevelMapper.selectNursingLevelAll();
    }

    /**
     * 根据护理等级ID查询护理等级
     *
     * @param id 护理等级ID
     * @return 护理等级
     */
    @Override
    public NursingLevel selectNursingLevelById(Long id)
    {
        return nursingLevelMapper.selectNursingLevelById(id);
    }

    /**
     * 校验护理等级名称是否唯一
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public boolean checkNameUnique(NursingLevel nursingLevel)
    {
        Long id = StringUtils.isNull(nursingLevel.getId()) ? -1L : nursingLevel.getId();
        NursingLevel info = nursingLevelMapper.checkNameUnique(nursingLevel.getName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public int insertNursingLevel(NursingLevel nursingLevel)
    {
        return nursingLevelMapper.insertNursingLevel(nursingLevel);
    }

    /**
     * 修改护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public int updateNursingLevel(NursingLevel nursingLevel)
    {
        return nursingLevelMapper.updateNursingLevel(nursingLevel);
    }

    /**
     * 删除护理等级
     *
     * @param id 护理等级ID
     * @return 结果
     */
    @Override
    public int deleteNursingLevelById(Long id)
    {
        return nursingLevelMapper.deleteNursingLevelById(id);
    }
}
