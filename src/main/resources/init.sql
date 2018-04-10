--sql 文件
--获取mn_service_node_metric中不是模版添加的metric
select 'metric' source,a.service_id,a.node_id ,a.metric_id,b.v_a,b.v_a_m,
decode(a.v_a_v,null,b.v_a_v,a.v_a_v) v_a_v,
decode(a.data_interval,null,b.data_interval,a.data_interval) dl
from mn_service_node_metric a,mn_metric_define b
where mtype<>'templ' and a.metric_id=b.id
union all
--模版
select 'templ' source, t1.service_id,t1.node_id,t1.metric_id,t1.v_a,t1.v_a_m,
decode(t2.v_a_v,null,t1.v_a_v,t2.v_a_v ) v_a_v,
decode(t2.data_interval,null,t1.data_interval,t2.data_interval) dl
from(
select b.id service_id,a.id node_id, a.name nodename,c.metric_id,d.*
from om_node a,mn_metric_group c ,mn_service b,mn_metric_define d
where a.templid=c.id and a.deleted='N' and d.id=c.metric_id and d.is_delete='N'
and b.node_id=a.id and b.is_delete='N') t1 left join mn_service_node_metric t2
on (t1.service_id=t2.service_id and t1.metric_id=t2.metric_id and t1.node_id=t2.node_id)