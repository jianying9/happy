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

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.ADD_FAVORITE_IMAGE,
        requestConfigs = {
    @RequestConfig(name = "imageId", typeEnum = TypeEnum.CHAR_32, desc = "图片id")
},
        responseConfigs = {
    @ResponseConfig(name = "imageId", typeEnum = TypeEnum.CHAR_32, desc = "图片id")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "收藏成功")
},
        validateSession = true,
        response = true,
        group = ActionGroupNames.USER,
        desc = "收藏图片")
public class AddFavoriteImageServiceImpl implements Service {

    //
    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String imageId = messageContext.getParameter("imageId");
        String id = messageContext.getSession().getSid();
        this.userLocalService.addFavoriteImage(id, imageId);
        messageContext.setMapData(messageContext.getParameterMap());
        messageContext.success();
    }
}
