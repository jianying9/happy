package com.yihan.happy.user.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.user.localservice.UserLocalService;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.UPDATE_IOS_POINT,
        requestConfigs = {
            @RequestConfig(name = "iosPoint", typeEnum = TypeEnum.LONG, desc = "当前用户ios积分")
        },
        responseStates = {
            @ResponseState(state = "SUCCESS", desc = "更新成功，且积分变化正常"),
            @ResponseState(state = "MAX_DAY_POINT", desc = "一天之内积分增长超过3000分，一天为当天的早上6点到次日凌晨2点")
        },
        validateSession = true,
        response = true,
        group = ActionGroupNames.USER,
        desc = "更新用户ios积分")
public class UpdateIosPointServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String iosPoint = messageContext.getParameter("iosPoint");
        String id = messageContext.getSession().getSid();
        //更新当前积分
        this.userLocalService.updateIosPoint(id, iosPoint);
        //获取用户昨天用户积分，与当前提交的积分比较，判断积分增长是否达到5000分。如果大等于5000分，则提示客户端禁止用户赚取积分
        long lastIosPoint = this.userLocalService.inquireLastdayIosPoint(id);
        if(Long.parseLong(iosPoint) - lastIosPoint >= 3000) {
            messageContext.setState("MAX_DAY_POINT");
        } else {
            messageContext.success();
        }
    }
}
