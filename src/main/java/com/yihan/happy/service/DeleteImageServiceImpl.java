package com.yihan.happy.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.service.parameter.ResponseConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.localservice.JokeImageLocalService;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.DELETE_IMAGE,
        requestConfigs = {
    @RequestConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id"),
    @RequestConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "删除成功"),
    @ResponseState(state = "FAILURE", desc = "密码错误")
},
        validateSession = false,
        validateSecurity = false,
        response = true,
        group = ActionGroupNames.IMAGE,
        desc = "删除图片")
public class DeleteImageServiceImpl implements Service {
    
    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> parameterMap = messageContext.getParameterMap();
        String password = parameterMap.get("password");
        if (password.equals("bigcodebang")) {
            String id = parameterMap.get("id");
            this.jokeImageLocalService.deleteImage(id);
            messageContext.setMapData(parameterMap);
            messageContext.success();
        }
    }
}
