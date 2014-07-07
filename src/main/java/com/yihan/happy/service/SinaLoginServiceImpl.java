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
import com.wolf.framework.utils.SecurityUtils;
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
    @RequestConfig(name = "name", typeEnum = TypeEnum.CHAR_32, desc = "用户名称"),
    @RequestConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码，md5加密")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "用户id"),
    @ResponseConfig(name = "name", typeEnum = TypeEnum.LONG, desc = "用户名称")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "登录成功"),
    @ResponseState(state = UserResponseState.PASSWORD_ERROR, desc = "密码错误")
},
        validateSession = false,
        sessionHandleTypeEnum = SessionHandleTypeEnum.SAVE,
        response = true,
        group = ActionGroupNames.USER,
        desc = "sina用户登录，name随意，密码:000000")
public class SinaLoginServiceImpl implements Service {

    private final String SINA_PREFIX = "S_";
    //
    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String name = messageContext.getParameter("name");
        String password = messageContext.getParameter("password");
        String sPassword = SecurityUtils.encryptByMd5("000000");
        if (password.equals(sPassword)) {
            String id = this.SINA_PREFIX.concat(name);
            Map<String, String> resultMap = new HashMap<String, String>(2, 1);
            resultMap.put("id", id);
            resultMap.put("name", name);
            messageContext.setMapData(resultMap);
            Session session = new SessionImpl(id);
            messageContext.setNewSession(session);
            messageContext.success();
        } else {
            messageContext.setState(UserResponseState.PASSWORD_ERROR);
        }
    }
}
