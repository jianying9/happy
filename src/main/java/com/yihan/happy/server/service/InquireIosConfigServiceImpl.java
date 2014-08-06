package com.yihan.happy.server.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.ResponseConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.server.localservice.ServerLocalService;
import java.util.Map;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_IOS_CONFIG,
        responseConfigs = {
    @ResponseConfig(name = "iosVersion", typeEnum = TypeEnum.CHAR_32, desc = "版本"),
    @ResponseConfig(name = "iosUrl", typeEnum = TypeEnum.CHAR_255, desc = "下载链接"),
    @ResponseConfig(name = "iosPointSwitch", typeEnum = TypeEnum.CHAR_10, desc = "on:开启，off:关闭")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "查询成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.SERVER,
        desc = "获取ios配置")
public class InquireIosConfigServiceImpl implements Service {

    //
    @InjectLocalService()
    private ServerLocalService serverLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> configMap = this.serverLocalService.inquireIosConfig();
        messageContext.setMapData(configMap);
        messageContext.success();
    }
}
