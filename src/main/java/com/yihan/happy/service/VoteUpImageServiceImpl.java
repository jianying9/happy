package com.yihan.happy.service;

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
import com.yihan.happy.localservice.JokeImageLocalService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.VOTE_UP_IMAGE,
        requestConfigs = {
    @RequestConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "图片id"),
    @ResponseConfig(name = "voteUp", typeEnum = TypeEnum.LONG, desc = "赞的次数")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "操作成功")
},
        validateSession = false,
        response = true,
        group = ActionGroupNames.IMAGE,
        desc = "图片点赞")
public class VoteUpImageServiceImpl implements Service {
    
    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        String id = messageContext.getParameter("id");
        long voteUp = this.jokeImageLocalService.voteUpImage(id);
        Map<String, String> resultMap = new HashMap<String, String>(2, 1);
        resultMap.put("id", id);
        resultMap.put("voteUp", Long.toString(voteUp));
        messageContext.setMapData(resultMap);
        messageContext.success();
    }
}
