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
import com.yihan.happy.entity.JokeImageEntity;
import com.yihan.happy.localservice.JokeImageLocalService;
import java.util.List;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_IMAGE_PAGE,
        page = true,
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id"),
    @ResponseConfig(name = "title", typeEnum = TypeEnum.CHAR_60, desc = "标题"),
    @ResponseConfig(name = "voteUp", typeEnum = TypeEnum.LONG, desc = "赞的次数"),
    @ResponseConfig(name = "voteDown", typeEnum = TypeEnum.LONG, desc = "踩的次数"),
    @ResponseConfig(name = "url", typeEnum = TypeEnum.CHAR_255, desc = "本地原始图片url,还没有下载本地时为空"),
    @ResponseConfig(name = "height", typeEnum = TypeEnum.LONG, desc = "图片高"),
    @ResponseConfig(name = "width", typeEnum = TypeEnum.LONG, desc = "图片宽"),
    @ResponseConfig(name = "picurl", typeEnum = TypeEnum.CHAR_255, desc = "图片链接")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "查询成功")
},
        validateSession = false,
        response = true,
        group = ActionGroupNames.IMAGE,
        desc = "分页查询图片，按照更新时间倒序排列.不返回总数和总页数")
public class InquireImagePageServiceImpl implements Service {

    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        long pageIndex = messageContext.getPageIndex();
        long pageSize = messageContext.getPageSize();
        List<JokeImageEntity> entityList = this.jokeImageLocalService.inquireJokeImageEntityListDESC(pageIndex, pageSize);
        messageContext.setEntityListData(entityList);
        messageContext.success();
    }
}
