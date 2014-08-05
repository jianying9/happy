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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.ORDER_FOR_MONEY_FROM_IOS,
        requestConfigs = {
    @RequestConfig(name = "zfb", typeEnum = TypeEnum.CHAR_60, desc = "支付宝帐号"),
    @RequestConfig(name = "iosPoint", typeEnum = TypeEnum.LONG, desc = "兑换的ios积分数量")
},
        responseConfigs = {
    @ResponseConfig(name = "orderId", typeEnum = TypeEnum.LONG, desc = "订单id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "订单创建成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.USER,
        desc = "ios创建提现类型的订单")
public class OrderForMoneyFromIosServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String zfb = messageContext.getParameter("zfb");
        String iosPoint = messageContext.getParameter("iosPoint");
        String id = messageContext.getSession().getSid();
        String orderId = this.userLocalService.insertMoneyOrderFromIos(id, zfb, iosPoint);
        Map<String, String> resultMap = new HashMap<String, String>(2, 1);
        resultMap.put("orderId", orderId);
        messageContext.setMapData(resultMap);
        messageContext.success();
    }
}
