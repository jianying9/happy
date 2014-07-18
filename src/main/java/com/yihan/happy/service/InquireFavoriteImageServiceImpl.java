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
import com.yihan.happy.localservice.UserLocalService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_FAVORITE_IMAGE,
        page = true,
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id"),
    @ResponseConfig(name = "title", typeEnum = TypeEnum.CHAR_32, desc = "标题"),
    @ResponseConfig(name = "tag", typeEnum = TypeEnum.CHAR_120, desc = "标签"),
    @ResponseConfig(name = "voteUp", typeEnum = TypeEnum.LONG, desc = "赞的次数"),
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
    @ResponseConfig(name = "mPicurl", typeEnum = TypeEnum.CHAR_255, desc = "中图片链接")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "查询成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.USER,
        desc = "查询我的收藏图片")
public class InquireFavoriteImageServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;
    //
    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        long pageIndex = messageContext.getPageIndex();
        long pageSize = messageContext.getPageSize();
        String id = messageContext.getSession().getSid();
        List<String> imageIdList = this.userLocalService.inquireFavorite(id);
        long start = (pageIndex - 1) * pageSize;
        long end = pageIndex * pageSize;
        List<String> pageImageIdList = new ArrayList<String>((int) pageSize);
        for (int index = (int) start; index < end && index < imageIdList.size(); index++) {
            pageImageIdList.add(imageIdList.get(index));
        }
        messageContext.setPageTotal(imageIdList.size());
        //
        List<JokeImageEntity> entityList = this.jokeImageLocalService.inquireJokeImageEntityListByIdList(pageImageIdList);
        messageContext.setEntityListData(entityList);
        messageContext.success();
    }
}
