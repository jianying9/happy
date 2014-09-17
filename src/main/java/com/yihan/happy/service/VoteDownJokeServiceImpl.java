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
import com.yihan.happy.localservice.JokeLocalService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.VOTE_DOWN_JOKE,
        requestConfigs = {
    @RequestConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "笑话id")
},
        responseConfigs = {
    @ResponseConfig(name = "id", typeEnum = TypeEnum.CHAR_32, desc = "笑话id"),
    @ResponseConfig(name = "voteDown", typeEnum = TypeEnum.LONG, desc = "踩的次数")
},
        responseStates = {
    @ResponseState(state = "SUCCESS", desc = "操作成功")
},
        validateSession = false,
        group = ActionGroupNames.JOKE,
        desc = "笑话点踩")
public class VoteDownJokeServiceImpl implements Service {
    
    @InjectLocalService()
    private JokeLocalService jokeLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        String id = messageContext.getParameter("id");
        long voteDown = this.jokeLocalService.voteDownJoke(id);
        Map<String, String> resultMap = new HashMap<String, String>(2, 1);
        resultMap.put("id", id);
        resultMap.put("voteDown", Long.toString(voteDown));
        messageContext.setMapData(resultMap);
        messageContext.success();
    }
}
