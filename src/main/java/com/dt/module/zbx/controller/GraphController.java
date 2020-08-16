package com.dt.module.zbx.controller;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.zbx.service.impl.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zbx/graph")
public class GraphController extends BaseController {

    @Autowired
    GraphService graphService;

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/getGraphByHostId.do")
    public R getGraphByHostId(String hostid) {
        return graphService.getGraphByHostId(hostid);
    }


}
