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
 * @author jianying9
 */
@RDaoConfig(
        tableName = TableNames.Y_USER)
public final class UserEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "用户id")
    private String id;
    //
    @RColumnConfig(desc = "来源名称")
    private String name;

    @Override
    public String getKeyValue() {
        return this.id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(2, 1);
        map.put("id", this.id);
        map.put("name", this.name);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = entityMap.get("url");
        this.name = entityMap.get("name");
    }
}
