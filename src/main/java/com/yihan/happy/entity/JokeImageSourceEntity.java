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
        tableName = TableNames.Y_JOKE_IMAGE_SOURCE)
public final class JokeImageSourceEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "来源地址")
    private String url;
    //
    @RColumnConfig(desc = "来源名称")
    private String name;

    @Override
    public String getKeyValue() {
        return this.url;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(2, 1);
        map.put("url", this.url);
        map.put("name", this.name);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.url = entityMap.get("url");
        this.name = entityMap.get("name");
    }
}
