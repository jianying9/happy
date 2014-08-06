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
        actionName = ActionNames.UPDATE_ANDROID_CONFIG,
        requestConfigs = {
    @RequestConfig(name = "androidVersion", typeEnum = TypeEnum.CHAR_32, desc = "版本"),
    @RequestConfig(name = "androidUrl", typeEnum = TypeEnum.CHAR_255, desc = "下载链接")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "修改成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.SERVER,
        desc = "修改android配置")
public class UpdateAndroidConfigServiceImpl implements Service {

    //
    @InjectLocalService()
    private ServerLocalService serverLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String version = messageContext.getParameter("androidVersion");
        String url = messageContext.getParameter("androidUrl");
        this.serverLocalService.updateAndroidVersionAndUrl(version, url);
        messageContext.success();
    }
}
