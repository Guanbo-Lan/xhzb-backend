package com.xhzb.nursing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xhzb.common.constant.UserConstants;
import com.xhzb.common.exception.ServiceException;
import com.xhzb.common.utils.StringUtils;
import com.xhzb.nursing.domain.NursingProject;
import com.xhzb.nursing.domain.vo.SelectOptionVo;
import com.xhzb.nursing.mapper.NursingProjectMapper;
import com.xhzb.nursing.mapper.NursingProjectPlanMapper;
import com.xhzb.nursing.service.INursingProjectService;

/**
 * 护理项目 服务层实现
 *
 * @author xhzb
 */
@Service
public class NursingProjectServiceImpl implements INursingProjectService
{
    @Autowired
    private NursingProjectMapper nursingProjectMapper;

    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    /**
     * 查询护理项目列表
     *
     * @param nursingProject 护理项目查询条件
     * @return 护理项目集合
     */
    @Override
    public List<NursingProject> selectNursingProjectList(NursingProject nursingProject)
    {
        return nursingProjectMapper.selectNursingProjectList(nursingProject);
    }

    /**
     * 查询所有护理项目下拉选项
     *
     * @return 下拉选项集合
     */
    @Override
    public List<SelectOptionVo> selectNursingProjectAll()
    {
        return nursingProjectMapper.selectNursingProjectAll();
    }

    /**
     * 根据护理项目ID查询护理项目
     *
     * @param id 护理项目ID
     * @return 护理项目
     */
    @Override
    public NursingProject selectNursingProjectById(Long id)
    {
        return nursingProjectMapper.selectNursingProjectById(id);
    }

    /**
     * 校验护理项目名称是否唯一
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public boolean checkNameUnique(NursingProject nursingProject)
    {
        Long id = StringUtils.isNull(nursingProject.getId()) ? -1L : nursingProject.getId();
        NursingProject info = nursingProjectMapper.checkNameUnique(nursingProject.getName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增护理项目
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int insertNursingProject(NursingProject nursingProject)
    {
        return nursingProjectMapper.insertNursingProject(nursingProject);
    }

    /**
     * 修改护理项目
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int updateNursingProject(NursingProject nursingProject)
    {
        return nursingProjectMapper.updateNursingProject(nursingProject);
    }

    /**
     * 修改护理项目状态
     *
     * @param nursingProject 护理项目（id、status）
     * @return 结果
     */
    @Override
    public int updateNursingProjectStatus(NursingProject nursingProject)
    {
        return nursingProjectMapper.updateNursingProjectStatus(nursingProject);
    }

    /**
     * 批量删除护理项目
     * 已被护理计划引用的项目不允许删除，避免产生无效的护理计划明细
     *
     * @param ids 需要删除的护理项目ID数组
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteNursingProjectByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            NursingProject nursingProject = nursingProjectMapper.selectNursingProjectById(id);
            if (StringUtils.isNotNull(nursingProject) && nursingProjectPlanMapper.countByProjectId(id) > 0)
            {
                throw new ServiceException(String.format("%1$s已关联护理计划，不能删除", nursingProject.getName()));
            }
        }
        return nursingProjectMapper.deleteNursingProjectByIds(ids);
    }
}
