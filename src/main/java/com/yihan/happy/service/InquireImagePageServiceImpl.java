package com.yihan.happy.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
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
    @ResponseConfig(name = "title", typeEnum = TypeEnum.CHAR_32, desc = "标题"),
    @ResponseConfig(name = "tag", typeEnum = TypeEnum.CHAR_120, desc = "标签"),
    @ResponseConfig(name = "voteUp", typeEnum = TypeEnum.LONG, desc = "顶的次数"),
    @ResponseConfig(name = "voteDown", typeEnum = TypeEnum.LONG, desc = "踩的次数"),
    @ResponseConfig(name = "createTime", typeEnum = TypeEnum.DATE_TIME, desc = "更新时间"),
    @ResponseConfig(name = "url", typeEnum = TypeEnum.CHAR_255, desc = "本地原始图片url,还没有下载本地时为空"),
    @ResponseConfig(name = "height", typeEnum = TypeEnum.LONG, desc = "大图片高"),
    @ResponseConfig(name = "width", typeEnum = TypeEnum.LONG, desc = "大图片宽"),
    @ResponseConfig(name = "picurl", typeEnum = TypeEnum.CHAR_255, desc = "大图片链接"),
    @ResponseConfig(name = "sHeight", typeEnum = TypeEnum.LONG, desc = "小图片高"),
    @ResponseConfig(name = "sWidth", typeEnum = TypeEnum.LONG, desc = "小图片宽"),
    @ResponseConfig(name = "sPicurl", typeEnum = TypeEnum.CHAR_255, desc = "小图片链接"),
    @ResponseConfig(name = "mHeight", typeEnum = TypeEnum.LONG, desc = "中始图片高"),
    @ResponseConfig(name = "mWidth", typeEnum = TypeEnum.LONG, desc = "中始图片宽"),
    @ResponseConfig(name = "mPicurl", typeEnum = TypeEnum.CHAR_255, desc = "中图片链接"),
    @ResponseConfig(name = "linkUrl", typeEnum = TypeEnum.CHAR_255, desc = "图片来源网页链接")
},
        validateSession = false,
        response = true,
        group = ActionGroupNames.IMAGE,
        description = "分页查询图片，按照更新时间倒序排列.不返回总数和总页数")
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
