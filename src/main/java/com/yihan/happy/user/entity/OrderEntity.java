package com.yihan.happy.user.entity;

import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.yihan.happy.config.TableNames;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author jianying9
 */
@RDaoConfig(
        tableName = TableNames.Y_ORDER)
public final class OrderEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "订单id")
    private String orderId;
    //
    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.INDEX, desc = "用户id")
    private String userId;
    //
    @RColumnConfig(desc = "订单类型:1-提现，2-话费，3-阿里妈妈购物")
    private String type;
    //
    @RColumnConfig(desc = "支付宝帐号")
    private String zfb;
    //
    @RColumnConfig(desc = "手机号")
    private String cellPhone;
    //
    @RColumnConfig(desc = "消费android的积分", defaultValue = "0")
    private long androidPoint;
    //
    @RColumnConfig(desc = "消费ios的积分", defaultValue = "0")
    private long iosPoint;
    //
    @RColumnConfig(desc = "状态:0-未处理,1-已处理")
    private String state;
    //
    @RColumnConfig(desc = "备注")
    private String note;
    //
    @RColumnConfig(desc = "创建时间")
    private String createTime;
    //
    @RColumnConfig(desc = "完成时间")
    private String finishTime;
    //
    @Override
    public String getKeyValue() {
        return this.orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getZfb() {
        return zfb;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public long getAndroidPoint() {
        return androidPoint;
    }

    public long getIosPoint() {
        return iosPoint;
    }

    public String getState() {
        return state;
    }

    public String getNote() {
        return note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }
    
    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(16, 1);
        map.put("orderId", this.orderId);
        map.put("userId", this.userId);
        map.put("type", this.type);
        map.put("zfb", this.zfb);
        map.put("cellPhone", this.cellPhone);
        map.put("androidPoint", Long.toString(this.androidPoint));
        map.put("iosPoint", Long.toString(this.iosPoint));
        map.put("state", this.state);
        map.put("note", this.note);
        map.put("createTime", this.createTime);
        map.put("finishTime", this.finishTime);
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.orderId = entityMap.get("orderId");
        this.userId = entityMap.get("userId");
        this.type = entityMap.get("type");
        this.zfb = entityMap.get("zfb");
        this.cellPhone = entityMap.get("cellPhone");
        this.androidPoint = Long.parseLong(entityMap.get("androidPoint"));
        this.iosPoint = Long.parseLong(entityMap.get("iosPoint"));
        this.state = entityMap.get("state");
        this.note = entityMap.get("note");
        this.createTime = entityMap.get("createTime");
        this.finishTime = entityMap.get("finishTime");
    }
}
