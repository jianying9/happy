package com.yihan.happy.server.service;

import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.server.localservice.ServerLocalService;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.CLOSE_IOS_POINT,
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "关闭成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.SERVER,
        desc = "关闭ios积分服务")
public class CloseIosPointServiceImpl implements Service {

    //
    @InjectLocalService()
    private ServerLocalService serverLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        this.serverLocalService.clodeIosPointSwitch();
        messageContext.success();
    }
}
