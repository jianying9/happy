package com.yihan.happy.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放图片信息
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.Y_JOKE_IMAGE)
public final class JokeImageEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "主键id")
    private String id;
    //
    @RColumnConfig(desc = "标题")
    private String title;
    //
    @RColumnConfig(desc = "赞的次数")
    private long voteUp;
    //
    @RColumnConfig(desc = "踩的次数")
    private long voteDown;
    //
    @RColumnConfig(desc = "sina图片名称")
    private String fileName;
    //
    @RColumnConfig(desc = "大图大小", defaultValue = "0")
    private long lLength;
    //
    @RColumnConfig(desc = "大图高")
    private long lHeight;
    //
    @RColumnConfig(desc = "大图宽")
    private long lWidth;
    //
    @RColumnConfig(desc = "中图大小", defaultValue = "0")
    private long mLength;
    //
    @RColumnConfig(desc = "中图高")
    private long mHeight;
    //
    @RColumnConfig(desc = "中图宽")
    private long mWidth;
    //
    @RColumnConfig(desc = "小图大小", defaultValue = "0")
    private long sLength;
    //
    @RColumnConfig(desc = "小图高")
    private long sHeight;
    //
    @RColumnConfig(desc = "小图宽")
    private long sWidth;

    @Override
    public String getKeyValue() {
        return this.id;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(16, 1);
        map.put("id", this.id);
        map.put("title", this.title);
        map.put("voteUp", Long.toString(this.voteUp));
        map.put("voteDown", Long.toString(this.voteDown));
        map.put("fileName", this.fileName);
        map.put("lLength", Long.toString(this.lLength));
        map.put("lWidth", Long.toString(this.lWidth));
        map.put("lHeight", Long.toString(this.lHeight));
        map.put("mLength", Long.toString(this.mLength));
        map.put("mWidth", Long.toString(this.mWidth));
        map.put("mHeight", Long.toString(this.mHeight));
        map.put("sLength", Long.toString(this.sLength));
        map.put("sWidth", Long.toString(this.sWidth));
        map.put("sHeight", Long.toString(this.sHeight));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = entityMap.get("id");
        this.title = entityMap.get("title");
        this.voteUp = Long.parseLong(entityMap.get("voteUp"));
        this.voteDown = Long.parseLong(entityMap.get("voteDown"));
        this.fileName = entityMap.get("fileName");
        this.lLength = Long.parseLong(entityMap.get("lLength"));
        this.lWidth = Long.parseLong(entityMap.get("lWidth"));
        this.lHeight = Long.parseLong(entityMap.get("lHeight"));
        this.mLength = Long.parseLong(entityMap.get("mLength"));
        this.mWidth = Long.parseLong(entityMap.get("mWidth"));
        this.mHeight = Long.parseLong(entityMap.get("mHeight"));
        this.sLength = Long.parseLong(entityMap.get("sLength"));
        this.sWidth = Long.parseLong(entityMap.get("sWidth"));
        this.sHeight = Long.parseLong(entityMap.get("sHeight"));
    }
}