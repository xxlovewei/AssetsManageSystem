#clear all
#clear sys table
truncate table sys_log_access;
truncate table sys_log_login;
truncate table sys_card_addr;
truncate table sys_dbbackup_rec;
truncate table sys_feedback;
truncate table sys_files;
truncate table sys_notice;
truncate table sys_session;

#clear zc table
truncate table res;
truncate table res_action_item;
truncate table res_allocate;
truncate table res_allocate_item
truncate table res_attr_value;
truncate table res_change_item;
truncate table res_class;
truncate table res_class_attrs;
truncate table res_history;
truncate table res_inventory;
truncate table res_inventory_item;
truncate table res_inventory_item_s;
truncate table res_inventory_user;
truncate table res_repair;
truncate table res_repair_file;
truncate table res_repair_item;
truncate table sys_feedback;
truncate table res_scrape;
truncate table res_scrape_item;
truncate table res_inout;
truncate table res_inout_item;
truncate table sys_process_data;
truncate table sys_process_data_kv;
truncate table res_residual_item;
truncate table res_residual;
truncate table res_c_basicinformation;
truncate table res_c_basicinformation_item;
truncate table res_c_finance;
truncate table res_c_finance_item;
truncate table res_c_maintenance;
truncate table res_c_maintenance_item;

#clear ops table
truncate table ops_node;
truncate table ops_node_infosys;
truncate table ops_node_item;

#clear others
delete from sys_user_info where dr='1'
delete from sys_user_group where dr='1';
delete from sys_form where dr='1';
delete from sys_menus_node where dr='1';
delete from sys_role_info where dr='1';
delete from res_attrs where dr='1';
delete from sys_dict where dr='1';
delete from sys_dict_item where dr='1';
delete from sys_params where dr='1';
delete from ct_category where dr='1';
delete from ct_category_root where dr='1';
delete from hrm_org_employee where dr='1';
delete from hrm_org_info where dr='1';
delete from hrm_org_part where dr='1';
delete from res_label_tpl where dr='1';
delete from sys_process_def where dr='1';
delete from sys_user_role where user_id not in (select user_id from sys_user_info);
delete from sys_user_role where role_id not in (select role_id from sys_role_info);
delete from sys_role_module where role_id not in  (select role_id from sys_role_info)
delete from sys_role_module where module_id not in  (select node_id from sys_menus_node)
truncate table sys_process_form;
delete from sys_process_setting where dr='1';
update sys_user_info set pwd='oracle';



truncate table uflo_task_participator;
truncate table uflo_task_appointor;
truncate table uflo_task_reminder;
delete from uflo_task;
truncate table uflo_his_task


