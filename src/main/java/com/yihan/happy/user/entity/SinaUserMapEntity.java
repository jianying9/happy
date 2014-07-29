package com.yihan.happy.user.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放sina用户id与bigcodeban用户id的映射
 *
 * @author jianying9
 */
@RDaoConfig(
        tableName = TableNames.Y_SINA_USER_MAP)
public final class SinaUserMapEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "sina用户id")
    private String sinaId;
    //
    @RColumnConfig(desc = "用户id")
    private String id;

    @Override
    public String getKeyValue() {
        return this.sinaId;
    }

    public String getSinaId() {
        return sinaId;
    }

    public String getId() {
        return id;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(2, 1);
        map.put("sinaId", this.sinaId);
        map.put("id", this.id);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.sinaId = entityMap.get("sinaId");
        this.id = entityMap.get("id");
    }
}
