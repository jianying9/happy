package com.yihan.happy.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.ResponseConfig;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.entity.JokeEntity;
import com.yihan.happy.localservice.JokeLocalService;
import java.util.List;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_JOKE_PAGE,
        page = true,
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "笑话id"),
    @ResponseConfig(name = "content", typeEnum = TypeEnum.CHAR_4000, desc = "内容"),
    @ResponseConfig(name = "voteUp", typeEnum = TypeEnum.LONG, desc = "赞的次数"),
    @ResponseConfig(name = "voteDown", typeEnum = TypeEnum.LONG, desc = "踩的次数")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "查询成功")
},
        validateSession = false,
        group = ActionGroupNames.JOKE,
        desc = "分页查询笑话，按照更新时间倒序排列.不返回总数和总页数")
public class InquireJokePageServiceImpl implements Service {

    @InjectLocalService()
    private JokeLocalService jokeLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        long pageIndex = messageContext.getPageIndex();
        long pageSize = messageContext.getPageSize();
        List<JokeEntity> entityList = this.jokeLocalService.inquireJokeEntityListDESC(pageIndex, pageSize);
        messageContext.setEntityListData(entityList);
        messageContext.success();
    }
}
