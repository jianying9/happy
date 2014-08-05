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
    @ResponseConfig(name = "fileName", typeEnum = TypeEnum.CHAR_255, desc = "sina图片文件名称"),
    @ResponseConfig(name = "length", typeEnum = TypeEnum.LONG, desc = "大图片字节大小"),
    @ResponseConfig(name = "lHeight", typeEnum = TypeEnum.LONG, desc = "大图高"),
    @ResponseConfig(name = "lWidth", typeEnum = TypeEnum.LONG, desc = "大图宽"),
    @ResponseConfig(name = "mHeight", typeEnum = TypeEnum.LONG, desc = "中图高"),
    @ResponseConfig(name = "mWidth", typeEnum = TypeEnum.LONG, desc = "中图宽"),
    @ResponseConfig(name = "sHeight", typeEnum = TypeEnum.LONG, desc = "小图高"),
    @ResponseConfig(name = "sWidth", typeEnum = TypeEnum.LONG, desc = "小图宽")
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
