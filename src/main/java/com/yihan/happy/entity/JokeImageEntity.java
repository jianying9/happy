package com.yihan.happy.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.Y_JOKE_IMAGE)
public final class JokeImageEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "主键id")
    private long id;
    //
    @RColumnConfig(desc = "标题")
    private String title;
    //
    @RColumnConfig(desc = "正文")
    private String content;
    //
    @RColumnConfig(desc = "标签")
    private String tag;
    //
    @RColumnConfig(desc = "顶的次数")
    private long voteUp;
    //
    @RColumnConfig(desc = "踩的次数")
    private long voteDown;
    //
    @RColumnConfig(desc = "分享次数")
    private long share;
    //
    @RColumnConfig(desc = "评价次数")
    private long comment;
    //
    @RColumnConfig(desc = "创建时间")
    private long createTime;
    //
    @RColumnConfig(desc = "本地原始图片链接")
    private String url;
    //
    @RColumnConfig(desc = "原始图片高")
    private long height;
    //
    @RColumnConfig(desc = "原始图片宽")
    private long width;
    //
    @RColumnConfig(desc = "原始图片链接")
    private String picurl;
    //
    @RColumnConfig(desc = "小图片链接")
    private String sPicurl;
    //
    @RColumnConfig(desc = "小始图片高")
    private long sHeight;
    //
    @RColumnConfig(desc = "小始图片宽")
    private long sWidth;
    //
    @RColumnConfig(desc = "中图片链接")
    private String mPicurl;
    //
    @RColumnConfig(desc = "中始图片高")
    private long mHeight;
    //
    @RColumnConfig(desc = "中始图片宽")
    private long mWidth;

    @Override
    public String getKeyValue() {
        return Long.toString(this.id);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(32, 1);
        map.put("id", Long.toString(this.id));
        map.put("title", this.title);
        map.put("content", this.content);
        map.put("tag", this.tag);
        map.put("voteUp", Long.toString(this.voteUp));
        map.put("voteDown", Long.toString(this.voteDown));
        map.put("share", Long.toString(this.share));
        map.put("comment", Long.toString(this.comment));
        map.put("createTime", Long.toString(this.createTime));
        map.put("url", this.url);
        map.put("height", Long.toString(this.height));
        map.put("width", Long.toString(this.width));
        map.put("picurl", this.picurl);
        map.put("sPicurl", this.sPicurl);
        map.put("sHeight", Long.toString(this.sHeight));
        map.put("sWidth", Long.toString(this.sWidth));
        map.put("mPicurl", this.mPicurl);
        map.put("mHeight", Long.toString(this.mHeight));
        map.put("mWidth", Long.toString(this.mWidth));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = Long.parseLong(entityMap.get("id"));
        this.title = entityMap.get("title");
        this.content = entityMap.get("content");
        this.tag = entityMap.get("tag");
        this.voteUp = Long.parseLong(entityMap.get("voteUp"));
        this.voteDown = Long.parseLong(entityMap.get("voteDown"));
        this.share = Long.parseLong(entityMap.get("share"));
        this.comment = Long.parseLong(entityMap.get("comment"));
        this.createTime = Long.parseLong(entityMap.get("createTime"));
        this.url = entityMap.get("url");
        this.height = Long.parseLong(entityMap.get("height"));
        this.width = Long.parseLong(entityMap.get("width"));
        this.picurl = entityMap.get("picurl");
        this.sPicurl = entityMap.get("sPicurl");
        this.sHeight = Long.parseLong(entityMap.get("sHeight"));
        this.sWidth = Long.parseLong(entityMap.get("sWidth"));
        this.mPicurl = entityMap.get("mPicurl");
        this.mHeight = Long.parseLong(entityMap.get("mHeight"));
        this.mWidth = Long.parseLong(entityMap.get("mWidth"));
    }
}
