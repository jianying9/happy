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
        actionName = ActionNames.INSERT_IMAGE,
        requestConfigs = {
    @RequestConfig(name = "title", typeEnum = TypeEnum.CHAR_60, desc = "标题"),
    @RequestConfig(name = "voteUp", typeEnum = TypeEnum.LONG, desc = "赞的次数"),
    @RequestConfig(name = "voteDown", typeEnum = TypeEnum.LONG, desc = "踩的次数"),
    @RequestConfig(name = "height", typeEnum = TypeEnum.LONG, desc = "图片高"),
    @RequestConfig(name = "width", typeEnum = TypeEnum.LONG, desc = "图片宽"),
    @RequestConfig(name = "picurl", typeEnum = TypeEnum.CHAR_255, desc = "图片链接"),
    @RequestConfig(name = "picurl", typeEnum = TypeEnum.CHAR_255, desc = "图片链接"),
    @RequestConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "更新成功"),
    @ResponseState(state = "FAILURE", desc = "密码错误")
},
        validateSession = false,
        validateSecurity = false,
        response = true,
        group = ActionGroupNames.IMAGE,
        desc = "新增图片")
public class InsertImageServiceImpl implements Service {

    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> parameterMap = messageContext.getParameterMap();
        String password = parameterMap.get("password");
        if (password.equals("bigcodebang")) {
            String id = this.jokeImageLocalService.insertImage(parameterMap);
            parameterMap.put("id", id);
            messageContext.setMapData(parameterMap);
            messageContext.success();
        }
    }
}
