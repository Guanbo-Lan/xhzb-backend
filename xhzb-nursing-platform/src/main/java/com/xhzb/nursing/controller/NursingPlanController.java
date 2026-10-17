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
import com.xhzb.nursing.domain.NursingPlan;
import com.xhzb.nursing.service.INursingPlanService;

/**
 * 护理计划 操作处理
 *
 * @author xhzb
 */
@RestController
@RequestMapping("/nursing/plan")
public class NursingPlanController extends BaseController
{
    @Autowired
    private INursingPlanService nursingPlanService;

    /**
     * 条件分页查询护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(NursingPlan nursingPlan)
    {
        startPage();
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        return getDataTable(list);
    }

    /**
     * 查询所有护理计划
     */
    @GetMapping("/all")
    public AjaxResult all()
    {
        return success(nursingPlanService.selectNursingPlanAll());
    }

    /**
     * 根据id查询护理计划（含护理项目明细）
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(nursingPlanService.selectNursingPlanById(id));
    }

    /**
     * 新增护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:add')")
    @Log(title = "护理计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NursingPlan nursingPlan)
    {
        if (!nursingPlanService.checkNameUnique(nursingPlan))
        {
            return error("新增护理计划'" + nursingPlan.getPlanName() + "'失败，护理计划名称已存在");
        }
        nursingPlan.setCreateBy(getUserId().toString());
        return toAjax(nursingPlanService.insertNursingPlan(nursingPlan));
    }

    /**
     * 修改护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:edit')")
    @Log(title = "护理计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NursingPlan nursingPlan)
    {
        if (!nursingPlanService.checkNameUnique(nursingPlan))
        {
            return error("修改护理计划'" + nursingPlan.getPlanName() + "'失败，护理计划名称已存在");
        }
        nursingPlan.setUpdateBy(getUserId().toString());
        return toAjax(nursingPlanService.updateNursingPlan(nursingPlan));
    }

    /**
     * 修改护理计划状态（启用/禁用）
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:edit')")
    @Log(title = "护理计划", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody NursingPlan nursingPlan)
    {
        if (nursingPlan.getId() == null || nursingPlan.getStatus() == null)
        {
            return error("护理计划ID和状态不能为空");
        }
        nursingPlan.setUpdateBy(getUserId().toString());
        return toAjax(nursingPlanService.updateNursingPlanStatus(nursingPlan));
    }

    /**
     * 删除护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:remove')")
    @Log(title = "护理计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nursingPlanService.deleteNursingPlanByIds(ids));
    }
}
