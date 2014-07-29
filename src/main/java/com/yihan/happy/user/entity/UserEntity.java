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
    @RColumnConfig(desc = "duomen的android的积分", defaultValue = "0")
    private long duomenAndroidPoint;
    //
    @RColumnConfig(desc = "duomen的ios的积分", defaultValue = "0")
    private long duomenIosPoint;
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

    public long getDuomenAndroidPoint() {
        return duomenAndroidPoint;
    }

    public long getDuomenIosPoint() {
        return duomenIosPoint;
    }
    
    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(4, 1);
        map.put("id", this.id);
        map.put("sinaId", this.sinaId);
        map.put("duomenAndroidPoint", Long.toString(this.duomenAndroidPoint));
        map.put("duomenIosPoint", Long.toString(this.duomenIosPoint));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.id = entityMap.get("id");
        this.sinaId = entityMap.get("sinaId");
        this.duomenAndroidPoint = Long.parseLong(entityMap.get("duomenAndroidPoint"));
        this.duomenIosPoint = Long.parseLong(entityMap.get("duomenIosPoint"));
    }
}
