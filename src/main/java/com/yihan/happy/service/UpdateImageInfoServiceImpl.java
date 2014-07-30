package com.yihan.happy.service;

import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseState;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.RequestConfig;
import com.wolf.framework.task.InjectTaskExecutor;
import com.wolf.framework.task.Task;
import com.wolf.framework.task.TaskExecutor;
import com.wolf.framework.worker.context.MessageContext;
import com.yihan.happy.config.ActionGroupNames;
import com.yihan.happy.config.ActionNames;
import com.yihan.happy.localservice.ImageInfo;
import com.yihan.happy.localservice.JokeImageLocalService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.UPDATE_IMAGE_INFO,
        requestConfigs = {
            @RequestConfig(name = "page", typeEnum = TypeEnum.LONG, desc = "抓取图片翻页次数,[1-50]"),
            @RequestConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码")
        },
        responseStates = {
            @ResponseState(state = "SUCCESS", desc = "更新成功")
        },
        validateSession = false,
        response = true,
        group = ActionGroupNames.IMAGE,
        desc = "抓取图片")
public class UpdateImageInfoServiceImpl implements Service {

    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;
    //
    @InjectTaskExecutor
    private TaskExecutor taskExecutor;

    @Override
    public void execute(MessageContext messageContext) {
        String password = messageContext.getParameter("password");
        if (password.equals("bigcodebang")) {
            long page = Long.parseLong(messageContext.getParameter("page"));
            if (page <= 0) {
                page = 1;
            } else if (page > 50) {
                page = 50;
            }
            Task updateImageTask = new updateImageTaskImpl(page);
            this.taskExecutor.submit(updateImageTask);
        }
        messageContext.success();
    }

    private class updateImageTaskImpl extends Task {

        private final long page;

        public updateImageTaskImpl(long page) {
            if (page <= 0) {
                this.page = 1;
            } else if (page > 50) {
                this.page = 50;
            } else {
                this.page = page;
            }
        }

        @Override
        public void doWhenRejected() {
        }

        @Override
        protected void execute() {
            List<ImageInfo> imageInfoList;
            Map<String, String> entityMap = new HashMap<String, String>(32, 1);
            for (int index = 1; index < page; index++) {
                imageInfoList = jokeImageLocalService.getPage(index);
                if (imageInfoList.isEmpty() == false) {
                    for (ImageInfo imageInfo : imageInfoList) {
//                    System.out.println(imageInfo);
                        //判断来源是否存在
                        if (jokeImageLocalService.existImageSource(imageInfo.getFromUrl()) == false) {
                            jokeImageLocalService.insertImageSource(imageInfo.getFromUrl(), imageInfo.getFromName());
                        }
                        //判断该图片是否存在
                        if (jokeImageLocalService.existImage(imageInfo.getId()) == false) {
                            entityMap.clear();
                            entityMap.put("id", imageInfo.getId());
                            entityMap.put("title", imageInfo.getTitle());
                            entityMap.put("voteUp", Long.toString(imageInfo.getVoteUp()));
                            entityMap.put("voteDown", Long.toString(imageInfo.getVoteDown()));
                            entityMap.put("url", "");
                            entityMap.put("height", Long.toString(imageInfo.getHeight()));
                            entityMap.put("width", Long.toString(imageInfo.getWidth()));
                            entityMap.put("picurl", imageInfo.getPicurl());
                            jokeImageLocalService.insertImage(entityMap);
                        }
                    }
                }
            }
        }
    }
}
