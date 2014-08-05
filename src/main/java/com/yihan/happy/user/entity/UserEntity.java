package com.yihan.happy.user.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * bigcodebang用户的信息
 *
 * @author jianying9
 */
@RDaoConfig(
        tableName = TableNames.Y_USER,
        sortedSets = {"favoriteImages"})
public final class UserEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "用户id")
    private String id;
    //
    @RColumnConfig(desc = "sina用户id")
    private String sinaId;
    //
    @RColumnConfig(desc = "android的积分", defaultValue = "0")
    private long androidPoint;
    //
    @RColumnConfig(desc = "ios的积分", defaultValue = "0")
    private long iosPoint;
    //
    @Override
    public String getKeyValue() {
        return this.id;
    }

    public String getId() {
        return id;
    }

    public String getSinaId() {
        return sinaId;
    }

    public long getAndroidPoint() {
        return androidPoint;
    }

    public long getIosPoint() {
        return iosPoint;
    }
    
    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(4, 1);
        map.put("id", this.id);
        map.put("sinaId", this.sinaId);
        map.put("androidPoint", Long.toString(this.androidPoint));
        map.put("iosPoint", Long.toString(this.iosPoint));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = entityMap.get("id");
        this.sinaId = entityMap.get("sinaId");
        this.androidPoint = Long.parseLong(entityMap.get("androidPoint"));
        this.iosPoint = Long.parseLong(entityMap.get("iosPoint"));
    }
}
