package com.yihan.happy.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放笑话信息
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.Y_JOKE)
public final class JokeEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "主键id")
    private String id;
    //
    @RColumnConfig(desc = "赞的次数")
    private long voteUp;
    //
    @RColumnConfig(desc = "踩的次数")
    private long voteDown;
    //
    @RColumnConfig(desc = "内容")
    private String content;
    //

    @Override
    public String getKeyValue() {
        return this.id;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(4, 1);
        map.put("id", this.id);
        map.put("voteUp", Long.toString(this.voteUp));
        map.put("voteDown", Long.toString(this.voteDown));
        map.put("content", this.content);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = entityMap.get("id");
        this.voteUp = Long.parseLong(entityMap.get("voteUp"));
        this.voteDown = Long.parseLong(entityMap.get("voteDown"));
        this.content = entityMap.get("content");
    }
}