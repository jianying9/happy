package com.yihan.happy.server.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.server.localservice.ServerLocalService;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.UPDATE_IOS_CONFIG,
        requestConfigs = {
    @RequestConfig(name = "iosVersion", typeEnum = TypeEnum.CHAR_32, desc = "版本"),
    @RequestConfig(name = "iosUrl", typeEnum = TypeEnum.CHAR_255, desc = "下载链接")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "修改成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.SERVER,
        desc = "修改ios配置")
public class UpdateIosConfigServiceImpl implements Service {

    //
    @InjectLocalService()
    private ServerLocalService serverLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String version = messageContext.getParameter("iosVersion");
        String url = messageContext.getParameter("iosUrl");
        this.serverLocalService.updateIosConfig(version, url);
        messageContext.success();
    }
}
