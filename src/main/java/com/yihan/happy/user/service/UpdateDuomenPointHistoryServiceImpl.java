package com.yihan.happy.user.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.session.Session;
import com.wolf.framework.session.SessionImpl;
import com.wolf.framework.task.InjectTaskExecutor;
import com.wolf.framework.task.Task;
import com.wolf.framework.task.TaskExecutor;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.user.entity.UserEntity;
import com.yihan.happy.user.localservice.UserLocalService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.UPDATE_DUOMEN_POINT_HISTORY,
        requestConfigs = {
            @RequestConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码")
        },
        responseStates = {
            @ResponseState(state = "SUCCESS", desc = "更新成功")
        },
        validateSession = false,
        response = true,
        group = ActionGroupNames.USER,
        desc = "更新用户当天的多盟积分信息，每天的凌晨2点到6点之间运行")
public class UpdateDuomenPointHistoryServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;
    //
    //
    @InjectTaskExecutor
    private TaskExecutor taskExecutor;

    @Override
    public void execute(MessageContext messageContext) {
        String password = messageContext.getParameter("password");
        if (password.equals("bigcodebang")) {
            Task updateDuomenPointHistoryTask = new UpdateDuomenPointHistoryTaskImpl();
            this.taskExecutor.submit(updateDuomenPointHistoryTask);
        }
        messageContext.success();
    }

    private class UpdateDuomenPointHistoryTaskImpl extends Task {

        @Override
        public void doWhenRejected() {
        }

        @Override
        protected void execute() {
            //分页查询所有用户的信息
            long pageIndex = 1;
            long pageSize = 500;
            String lastDateId;
            Map<String, String> duomenPointHistoryEntityMap = new HashMap<String, String>(4, 1);
            List<UserEntity> userEntityList = userLocalService.inquireUserEntityList(pageIndex, pageSize);
            while (userEntityList.isEmpty() == false) {
                //
                System.out.println("UPDATE_DUOMEN_POINT_HISTORY-pageIndex:".concat(Long.toString(pageIndex)));
                //记录前一天的用户历史积分记录，如果用户之前从来没有发生积分相关操作，则跳过
                for (UserEntity userEntity : userEntityList) {
                    if (userEntity.getDuomenAndroidPoint() >= 0 || userEntity.getDuomenIosPoint() >= 0) {
                        lastDateId = userLocalService.getDuomenPointHistoryLastDateId(userEntity.getId());
                        duomenPointHistoryEntityMap.put("dateId", lastDateId);
                        duomenPointHistoryEntityMap.put("duomenAndroidPoint", Long.toString(userEntity.getDuomenAndroidPoint()));
                        duomenPointHistoryEntityMap.put("duomenIosPoint", Long.toString(userEntity.getDuomenIosPoint()));
                        userLocalService.insertDuomenPointHistory(duomenPointHistoryEntityMap);
                    }
                }
                //查询下一页用户信息
                pageIndex++;
                userEntityList = userLocalService.inquireUserEntityList(pageIndex, pageSize);
            }
        }
    }
}
