package com.yihan.happy.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放多盟积分的变化历史记录
 *
 * @author jianying9
 */
@RDaoConfig(
        tableName = TableNames.Y_DUOMEN_POINT_HISTORY)
public final class DuomenPointHistoryEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "yyyy-mm-dd + '_' + 用户id")
    private String dateId;
    //
    @RColumnConfig(desc = "duomen的android的积分", defaultValue = "0")
    private long duomenAndroidPoint;
    //
    @RColumnConfig(desc = "duomen的ios的积分", defaultValue = "0")
    private long duomenIosPoint;
    //
    @Override
    public String getKeyValue() {
        return this.dateId;
    }

    public String getDateId() {
        return dateId;
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
        map.put("dateId", this.dateId);
        map.put("duomenAndroidPoint", Long.toString(this.duomenAndroidPoint));
        map.put("duomenIosPoint", Long.toString(this.duomenIosPoint));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.dateId = entityMap.get("dateId");
        this.duomenAndroidPoint = Long.parseLong(entityMap.get("duomenAndroidPoint"));
        this.duomenIosPoint = Long.parseLong(entityMap.get("duomenIosPoint"));
    }
}
