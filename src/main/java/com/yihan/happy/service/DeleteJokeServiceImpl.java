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
import com.yihan.happy.localservice.JokeLocalService;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.DELETE_JOKE,
        requestConfigs = {
    @RequestConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "笑话id"),
    @RequestConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "笑话id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "删除成功"),
    @ResponseState(state = "FAILURE", desc = "密码错误")
},
        validateSession = false,
        validateSecurity = false,
        group = ActionGroupNames.JOKE,
        desc = "删除笑话")
public class DeleteJokeServiceImpl implements Service {
    
    @InjectLocalService()
    private JokeLocalService jokeLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> parameterMap = messageContext.getParameterMap();
        String password = parameterMap.get("password");
        if (password.equals("bigcodebang")) {
            String id = parameterMap.get("id");
            this.jokeLocalService.deleteJoke(id);
            messageContext.setMapData(parameterMap);
            messageContext.success();
        }
    }
}
