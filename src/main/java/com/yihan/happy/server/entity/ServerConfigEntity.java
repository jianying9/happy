package com.yihan.happy.server.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统服务配置
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.Y_SERVER_CONFIG)
public final class ServerConfigEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "属性名")
    private String name;
    //
    @RColumnConfig(desc = "属性值")
    private String value;

    @Override
    public String getKeyValue() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(2, 1);
        map.put("value", this.value);
        map.put("name", this.name);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.value = entityMap.get("value");
        this.name = entityMap.get("name");
    }
}
