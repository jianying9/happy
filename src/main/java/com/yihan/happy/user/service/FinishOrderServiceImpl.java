package com.yihan.happy.user.service;

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
import com.yihan.happy.user.localservice.UserLocalService;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.FINISH_ORDER,
        requestConfigs = {
    @RequestConfig(name = "orderId", typeEnum = TypeEnum.CHAR_60, desc = "订单id"),
    @RequestConfig(name = "note", typeEnum = TypeEnum.CHAR_255, desc = "备注")
},
        responseConfigs = {
    @ResponseConfig(name = "orderId", typeEnum = TypeEnum.LONG, desc = "订单id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "完成订单成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.USER,
        desc = "完成订单")
public class FinishOrderServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        String orderId = messageContext.getParameter("orderId");
        String note = messageContext.getParameter("note");
        this.userLocalService.finishOrder(orderId, note);
        messageContext.setMapData(messageContext.getParameterMap());
        messageContext.success();
    }
}
