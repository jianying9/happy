package com.yihan.happy.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.SessionHandleTypeEnum;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.service.parameter.ResponseConfig;
import com.wolf.framework.session.Session;
import com.wolf.framework.session.SessionImpl;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.localservice.UserLocalService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.SINA_USER_LOGIN,
        requestConfigs = {
    @RequestConfig(name = "sinaId", typeEnum = TypeEnum.CHAR_32, desc = "sina用户id")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "用户id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "登录成功")
},
        validateSession = false,
        sessionHandleTypeEnum = SessionHandleTypeEnum.SAVE,
        response = true,
        group = ActionGroupNames.USER,
        desc = "sina用户登录")
public class SinaLoginServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String sinaId = messageContext.getParameter("sinaId");
        String id = this.userLocalService.loginBySinaUser(sinaId);
        Map<String, String> resultMap = new HashMap<String, String>(2, 1);
        resultMap.put("id", id);
        Session session = new SessionImpl(id);
        messageContext.setNewSession(session);
        messageContext.setMapData(resultMap);
        messageContext.success();
    }
}
