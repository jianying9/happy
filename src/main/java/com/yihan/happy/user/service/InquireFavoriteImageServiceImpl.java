package com.yihan.happy.user.service;

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
import com.yihan.happy.user.localservice.UserLocalService;
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
