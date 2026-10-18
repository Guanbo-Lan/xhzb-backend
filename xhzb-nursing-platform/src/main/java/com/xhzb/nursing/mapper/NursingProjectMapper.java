package com.xhzb.nursing.mapper;

import java.util.List;
import com.xhzb.nursing.domain.NursingProject;
import com.xhzb.nursing.domain.vo.SelectOptionVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 护理项目 数据层
 *
 * @author xhzb
 */
@Mapper
public interface NursingProjectMapper
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
     * @param name 护理项目名称
     * @return 护理项目
     */
    public NursingProject checkNameUnique(String name);

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
     * 修改护理项目状态
     *
     * @param nursingProject 护理项目（id、status）
     * @return 结果
     */
    public int updateNursingProjectStatus(NursingProject nursingProject);

    /**
     * 批量删除护理项目
     *
     * @param ids 需要删除的护理项目ID数组
     * @return 结果
     */
    public int deleteNursingProjectByIds(Long[] ids);
}
