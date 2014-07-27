package com.yihan.happy.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.localservice.UserLocalService;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.UPDATE_DUOMEN_ANDORID_POINT,
        requestConfigs = {
            @RequestConfig(name = "duomenAndroidPoint", typeEnum = TypeEnum.LONG, desc = "当前用户多盟android积分")
        },
        responseStates = {
            @ResponseState(state = "SUCCESS", desc = "更新成功，且积分变化正常"),
            @ResponseState(state = "MAX_DAY_POINT", desc = "一天之内积分增长超过3000分，一天为当天的早上6点到次日凌晨2点")
        },
        validateSession = true,
        response = true,
        group = ActionGroupNames.USER,
        desc = "更新用户多盟android积分")
public class UpdateDuomenAndroidPointServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String duomenAndroidPoint = messageContext.getParameter("duomenAndroidPoint");
        String id = messageContext.getSession().getSid();
        //更新多盟当前积分
        this.userLocalService.updateDuomenAndroidPoint(id, duomenAndroidPoint);
        //获取用户当天多盟的用户积分，与当前提交的积分比较，判断积分增长是否达到5000分。如果大等于5000分，则提示客户端禁止用户赚取积分
        long lastDuomenAndroidPoint = this.userLocalService.inquireLastTodayDuomenAndroidPoint(id);
        if(Long.parseLong(duomenAndroidPoint) - lastDuomenAndroidPoint >= 3000) {
            messageContext.setState("MAX_DAY_POINT");
        } else {
            messageContext.success();
        }
    }
}
