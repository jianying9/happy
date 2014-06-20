package com.yihan.happy.service;

import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
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
        validateSession = false,
        response = true,
        group = ActionGroupNames.IMAGE_SPIDER,
        description = "抓取图片")
public class UpdateImageInfoServiceImpl implements Service {

    @InjectLocalService()
    private JokeImageLocalService jokeImageLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        List<ImageInfo> imageInfoList;
        Map<String, String> entityMap = new HashMap<String, String>(32, 1);
        for (int index = 1; index < 100; index++) {
            imageInfoList = this.jokeImageLocalService.getPage(index);
            if(imageInfoList.isEmpty() == false) {
                for (ImageInfo imageInfo : imageInfoList) {
                    System.out.println(imageInfo);
                    //判断来源是否存在
                    if(this.jokeImageLocalService.existImageSource(imageInfo.getFromUrl()) == false) {
                        this.jokeImageLocalService.insertImageSource(imageInfo.getFromUrl(), imageInfo.getFromName());
                    }
                    //判断该图片是否存在
//                    if(this.jokeImageLocalService.existImage(imageInfo.getId()) == false) {
                        entityMap.clear();
                        entityMap.put("id", Long.toString(imageInfo.getId()));
                        entityMap.put("title", imageInfo.getTitle());
                        entityMap.put("content", imageInfo.getContent());
                        entityMap.put("tag", imageInfo.getTag());
                        entityMap.put("voteUp", Long.toString(imageInfo.getVoteUp()));
                        entityMap.put("voteDown", Long.toString(imageInfo.getVoteDown()));
                        entityMap.put("share", Long.toString(imageInfo.getShare()));
                        entityMap.put("comment", Long.toString(imageInfo.getComment()));
                        entityMap.put("createTime", Long.toString(imageInfo.getCreateTime()));
                        entityMap.put("url", "");
                        entityMap.put("height", Long.toString(imageInfo.getHeight()));
                        entityMap.put("width", Long.toString(imageInfo.getWidth()));
                        entityMap.put("picurl", imageInfo.getPicurl());
                        entityMap.put("sHeight", Long.toString(imageInfo.getsHeight()));
                        entityMap.put("sWidth", Long.toString(imageInfo.getsWidth()));
                        entityMap.put("sPicurl", imageInfo.getsPicurl());
                        entityMap.put("mHeight", Long.toString(imageInfo.getmHeight()));
                        entityMap.put("mWidth", Long.toString(imageInfo.getmWidth()));
                        entityMap.put("mPicurl", imageInfo.getmPicurl());
                        this.jokeImageLocalService.insertImage(entityMap);
//                    }
                }
            }
        }
        messageContext.success();
    }
}
