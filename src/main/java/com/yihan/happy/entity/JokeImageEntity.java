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

    @Override
    public String getKeyValue() {
        return this.id;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(8, 1);
        map.put("id", this.id);
        map.put("title", this.title);
        map.put("voteUp", Long.toString(this.voteUp));
        map.put("voteDown", Long.toString(this.voteDown));
        map.put("url", this.url);
        map.put("height", Long.toString(this.height));
        map.put("width", Long.toString(this.width));
        map.put("picurl", this.picurl);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = entityMap.get("id");
        this.title = entityMap.get("title");
        this.voteUp = Long.parseLong(entityMap.get("voteUp"));
        this.voteDown = Long.parseLong(entityMap.get("voteDown"));
        this.url = entityMap.get("url");
        this.height = Long.parseLong(entityMap.get("height"));
        this.width = Long.parseLong(entityMap.get("width"));
        this.picurl = entityMap.get("picurl");
    }
}
