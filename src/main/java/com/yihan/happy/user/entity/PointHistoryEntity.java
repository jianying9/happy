package com.yihan.happy.user.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放积分的变化历史记录
 *
 * @author jianying9
 */
@RDaoConfig(
        tableName = TableNames.Y_POINT_HISTORY)
public final class PointHistoryEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "yyyy-mm-dd + '_' + 用户id")
    private String dateId;
    //
    @RColumnConfig(desc = "android的积分", defaultValue = "0")
    private long androidPoint;
    //
    @RColumnConfig(desc = "ios的积分", defaultValue = "0")
    private long iosPoint;
    //
    @Override
    public String getKeyValue() {
        return this.dateId;
    }

    public String getDateId() {
        return dateId;
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
        map.put("dateId", this.dateId);
        map.put("androidPoint", Long.toString(this.androidPoint));
        map.put("iosPoint", Long.toString(this.iosPoint));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.dateId = entityMap.get("dateId");
        this.androidPoint = Long.parseLong(entityMap.get("duomenAndroidPoint"));
        this.iosPoint = Long.parseLong(entityMap.get("duomenIosPoint"));
    }
}
