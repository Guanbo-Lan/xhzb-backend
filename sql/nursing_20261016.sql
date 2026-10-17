-- ----------------------------
-- 服务管理模块：菜单 + 按钮权限 + 字典 + 演示数据
--
-- 说明（重要）：
--   1. 本脚本【不含建表语句】。库中的 nursing_project / nursing_plan /
--      nursing_project_plan / nursing_level 四张表已存在，脚本按现有结构适配：
--        - nursing_plan / nursing_project_plan / nursing_level 的 create_by、update_by 为 bigint（用户ID）
--        - nursing_project_plan.execute_time 为 varchar(50) NOT NULL
--        - nursing_project_plan.execute_cycle / execute_frequency 为 NOT NULL
--        - nursing_project.name、nursing_plan.plan_name、nursing_level.name 均有唯一约束
--   2. 上级菜单「服务管理」已存在（menu_id = 2000，path = serve），本脚本只补充其下子菜单，不修改该行。
--   3. 字典 nursing_project_status（1启用/0禁用）已存在，本脚本不重复插入；
--      护理计划、护理等级各用一个独立字典（nursing_plan_status / nursing_level_status）。
--   4. 脚本可重复执行：先删除本脚本自己写入的 id 段，再重新插入。
-- ----------------------------

-- ----------------------------
-- 1、字典类型
-- ----------------------------
delete from sys_dict_type where dict_id in (101, 102, 103);

insert into sys_dict_type values(101, '护理计划状态', 'nursing_plan_status',   '0', 'admin', sysdate(), '', null, '护理计划状态列表');
insert into sys_dict_type values(102, '护理等级状态', 'nursing_level_status',  '0', 'admin', sysdate(), '', null, '护理等级状态列表');
insert into sys_dict_type values(103, '护理执行周期', 'nursing_execute_cycle', '0', 'admin', sysdate(), '', null, '护理计划项目执行周期列表');

-- ----------------------------
-- 2、字典数据
-- 已占用：100、101（nursing_project_status）
-- ----------------------------
delete from sys_dict_data where dict_code between 102 and 108;

insert into sys_dict_data values(102, 1, '启用', '1', 'nursing_plan_status',   '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '启用状态');
insert into sys_dict_data values(103, 2, '禁用', '0', 'nursing_plan_status',   '', 'danger',  'N', '0', 'admin', sysdate(), '', null, '禁用状态');
insert into sys_dict_data values(104, 1, '启用', '1', 'nursing_level_status',  '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '启用状态');
insert into sys_dict_data values(105, 2, '禁用', '0', 'nursing_level_status',  '', 'danger',  'N', '0', 'admin', sysdate(), '', null, '禁用状态');
insert into sys_dict_data values(106, 1, '天',   '0', 'nursing_execute_cycle', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '每天执行');
insert into sys_dict_data values(107, 2, '周',   '1', 'nursing_execute_cycle', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '每周执行');
insert into sys_dict_data values(108, 3, '月',   '2', 'nursing_execute_cycle', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '每月执行');

-- 护理项目状态字典配色：启用为绿色（success），禁用为红色（danger），与页面原型保持一致
update sys_dict_data set list_class = 'success' where dict_type = 'nursing_project_status' and dict_value = '1';
update sys_dict_data set list_class = 'danger'  where dict_type = 'nursing_project_status' and dict_value = '0';
update sys_dict_data set list_class = 'success' where dict_type = 'nursing_plan_status'    and dict_value = '1';
update sys_dict_data set list_class = 'danger'  where dict_type = 'nursing_plan_status'    and dict_value = '0';
update sys_dict_data set list_class = 'success' where dict_type = 'nursing_level_status'   and dict_value = '1';
update sys_dict_data set list_class = 'danger'  where dict_type = 'nursing_level_status'   and dict_value = '0';

-- ----------------------------
-- 3、菜单与按钮权限
-- 上级：menu_id = 2000「服务管理」（path = serve，已存在）
-- 前端页面路径对应 src/views/serve/{project,plan,level}/index.vue
-- 超管（role_id = 1）走 selectMenuTreeAll() 与 *:*:*，无需写 sys_role_menu；
-- 普通角色请在「系统管理 - 角色管理」中勾选「服务管理」下的菜单与按钮。
-- ----------------------------
delete from sys_role_menu where menu_id between 2001 and 2021;
delete from sys_menu where menu_id between 2001 and 2021;

insert into sys_menu values('2001', '护理项目', '2000', '1', 'project', 'serve/project/index', '', '', 1, 0, 'C', '0', '0', 'nursing:project:list',   'list', 'admin', sysdate(), '', null, '护理项目菜单');
insert into sys_menu values('2010', '护理项目查询', '2001', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:project:query',  '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2011', '护理项目新增', '2001', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:project:add',    '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2012', '护理项目修改', '2001', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:project:edit',   '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2013', '护理项目删除', '2001', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:project:remove', '#',    'admin', sysdate(), '', null, '');

insert into sys_menu values('2002', '护理计划', '2000', '2', 'plan', 'serve/plan/index', '', '', 1, 0, 'C', '0', '0', 'nursing:plan:list',   'date', 'admin', sysdate(), '', null, '护理计划菜单');
insert into sys_menu values('2014', '护理计划查询', '2002', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:plan:query',  '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2015', '护理计划新增', '2002', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:plan:add',    '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2016', '护理计划修改', '2002', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:plan:edit',   '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2017', '护理计划删除', '2002', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:plan:remove', '#',    'admin', sysdate(), '', null, '');

insert into sys_menu values('2003', '护理等级', '2000', '3', 'level', 'serve/level/index', '', '', 1, 0, 'C', '0', '0', 'nursing:level:list',   'star', 'admin', sysdate(), '', null, '护理等级菜单');
insert into sys_menu values('2018', '护理等级查询', '2003', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:level:query',  '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2019', '护理等级新增', '2003', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:level:add',    '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2020', '护理等级修改', '2003', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:level:edit',   '#',    'admin', sysdate(), '', null, '');
insert into sys_menu values('2021', '护理等级删除', '2003', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'nursing:level:remove', '#',    'admin', sysdate(), '', null, '');

-- ============================
-- 4、演示数据（可选，正式环境请勿执行）
-- id 统一取 1000+ 段，可重复执行
-- ============================
delete from nursing_project_plan where id between 1000 and 1099;
delete from nursing_level        where id between 1000 and 1099;
delete from nursing_plan         where id between 1000 and 1099;
delete from nursing_project      where id between 1000 and 1099;

-- 护理项目
insert into nursing_project (id, name, order_no, unit, price, image, nursing_requirement, status, create_by, update_by, remark, create_time, update_time) values
(1000, '助浴',     1, '元', 20.00, 'https://itheim.oss-cn-beijing.aliyuncs.com/7cbe6478-2499-4162-89f1-e642893aa2ce.png', '将老人带到助浴间，协助完成全身清洁、擦干并更换衣物，注意防滑和保暖', 1, '1', '1', null, sysdate(), sysdate()),
(1001, '助餐',     2, '餐', 15.00, 'https://itheim.oss-cn-beijing.aliyuncs.com/60f89ec5-a53b-4a53-8b6a-499a32dcc421.png', '协助老人进食，注意食物温度，对吞咽困难者需缓慢喂食',                    1, '1', '1', null, sysdate(), sysdate()),
(1002, '整理床铺', 3, '次', 10.00, null, '整理床铺，更换床单被罩，保持床铺平整干燥',                                                    1, '1', '1', null, sysdate(), sysdate()),
(1003, '洗头',     4, '次', 20.00, 'https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/a2a56275-51e6-43a0-8ad6-b0600bfb01c2.jpg', '将患者带到洗发间进行洗发，并提供按摩和护理，保持头皮健康', 0, '1', '1', null, sysdate(), sysdate());

-- 护理计划
insert into nursing_plan (id, sort_no, plan_name, status, create_time, update_time, create_by, update_by, remark) values
(1000, 1, '一级护理计划', 1, sysdate(), sysdate(), 1, 1, '面向自理能力较好的老人'),
(1001, 2, '二级护理计划', 1, sysdate(), sysdate(), 1, 1, '面向需要一定辅助的老人');

-- 护理计划项目明细
insert into nursing_project_plan (id, plan_id, project_id, execute_time, execute_cycle, execute_frequency, create_time, update_time, create_by, update_by, remark) values
(1000, 1000, 1000, '09:00:00', 1, 2, sysdate(), sysdate(), 1, 1, '每周助浴两次'),
(1001, 1000, 1001, '11:30:00', 0, 3, sysdate(), sysdate(), 1, 1, '每天助餐三次'),
(1002, 1001, 1002, '15:08:00', 1, 1, sysdate(), sysdate(), 1, 1, '每周整理床铺一次'),
(1003, 1001, 1003, '16:00:00', 2, 1, sysdate(), sysdate(), 1, 1, '每月洗头一次');

-- 护理等级
insert into nursing_level (id, name, lplan_id, fee, status, description, create_time, create_by, update_by, remark, update_time) values
(1000, '一级护理等级', 1000, 2000.00, 1, '一级护理等级适用于完全自理的老年人，该等级仅提供少量基础护理项目', sysdate(), 1, 1, null, sysdate()),
(1001, '二级护理等级', 1001, 1500.00, 1, '二级护理等级适用于能够自理但需要一定辅助护理的老年人，该等级提供了中等程度的照护项目', sysdate(), 1, 1, null, sysdate());
