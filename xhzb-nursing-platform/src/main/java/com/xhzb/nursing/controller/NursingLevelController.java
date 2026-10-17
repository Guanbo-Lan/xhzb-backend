package com.xhzb.nursing.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xhzb.common.annotation.Log;
import com.xhzb.common.core.controller.BaseController;
import com.xhzb.common.core.domain.AjaxResult;
import com.xhzb.common.core.page.TableDataInfo;
import com.xhzb.common.enums.BusinessType;
import com.xhzb.nursing.domain.NursingLevel;
import com.xhzb.nursing.service.INursingLevelService;

/**
 * 护理等级 操作处理
 *
 * @author xhzb
 */
@RestController
@RequestMapping("/nursing/level")
public class NursingLevelController extends BaseController
{
    @Autowired
    private INursingLevelService nursingLevelService;

    /**
     * 条件分页查询护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(NursingLevel nursingLevel)
    {
        startPage();
        List<NursingLevel> list = nursingLevelService.selectNursingLevelList(nursingLevel);
        return getDataTable(list);
    }

    /**
     * 查询所有护理等级
     */
    @GetMapping("/all")
    public AjaxResult all()
    {
        return success(nursingLevelService.selectNursingLevelAll());
    }

    /**
     * 根据id查询护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(nursingLevelService.selectNursingLevelById(id));
    }

    /**
     * 新增护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:add')")
    @Log(title = "护理等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NursingLevel nursingLevel)
    {
        if (!nursingLevelService.checkNameUnique(nursingLevel))
        {
            return error("新增护理等级'" + nursingLevel.getName() + "'失败，护理等级名称已存在");
        }
        nursingLevel.setCreateBy(getUserId().toString());
        return toAjax(nursingLevelService.insertNursingLevel(nursingLevel));
    }

    /**
     * 修改护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:edit')")
    @Log(title = "护理等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NursingLevel nursingLevel)
    {
        if (!nursingLevelService.checkNameUnique(nursingLevel))
        {
            return error("修改护理等级'" + nursingLevel.getName() + "'失败，护理等级名称已存在");
        }
        nursingLevel.setUpdateBy(getUserId().toString());
        return toAjax(nursingLevelService.updateNursingLevel(nursingLevel));
    }

    /**
     * 修改护理等级状态（启用/禁用）
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:edit')")
    @Log(title = "护理等级", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody NursingLevel nursingLevel)
    {
        if (nursingLevel.getId() == null || nursingLevel.getStatus() == null)
        {
            return error("护理等级ID和状态不能为空");
        }
        nursingLevel.setUpdateBy(getUserId().toString());
        return toAjax(nursingLevelService.updateNursingLevelStatus(nursingLevel));
    }

    /**
     * 删除护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:remove')")
    @Log(title = "护理等级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nursingLevelService.deleteNursingLevelByIds(ids));
    }
}
