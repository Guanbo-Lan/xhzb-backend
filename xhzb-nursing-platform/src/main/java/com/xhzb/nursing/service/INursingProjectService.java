package com.xhzb.nursing.service;

import java.util.List;
import com.xhzb.nursing.domain.NursingProject;
import com.xhzb.nursing.domain.vo.SelectOptionVo;

/**
 * 护理项目 服务层
 *
 * @author xhzb
 */
public interface INursingProjectService
{
    /**
     * 查询护理项目列表
     *
     * @param nursingProject 护理项目查询条件
     * @return 护理项目集合
     */
    public List<NursingProject> selectNursingProjectList(NursingProject nursingProject);

    /**
     * 查询所有护理项目下拉选项
     *
     * @return 下拉选项集合
     */
    public List<SelectOptionVo> selectNursingProjectAll();

    /**
     * 根据护理项目ID查询护理项目
     *
     * @param id 护理项目ID
     * @return 护理项目
     */
    public NursingProject selectNursingProjectById(Long id);

    /**
     * 校验护理项目名称是否唯一
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    public boolean checkNameUnique(NursingProject nursingProject);

    /**
     * 新增护理项目
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    public int insertNursingProject(NursingProject nursingProject);

    /**
     * 修改护理项目
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    public int updateNursingProject(NursingProject nursingProject);

    /**
     * 删除护理项目
     *
     * @param id 护理项目ID
     * @return 结果
     */
    public int deleteNursingProjectById(Long id);
}
