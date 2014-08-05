package com.yihan.happy.user.localservice;

import com.wolf.framework.local.Local;
import com.yihan.happy.user.entity.PointHistoryEntity;
import com.yihan.happy.user.entity.UserEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
public interface UserLocalService extends Local {
    
    //订单类型
    public String MONEY_ORDER_TYPE = "1";
    
    public String PHONE_BILL_ORDER_TYPE = "2";
    //订单状态
    public String UN_FINISH_ORDER_STATE = "0";
    
    public String FINISH_ORDER_STATE = "1";
    
    public String INVALID_ORDER_STATE = "-1";
    
    /**
     * 使用sinaId登陆
     * @param sinaId
     * @return 
     */
    public String loginBySinaUser(String sinaId);
    
    /**
     * 分页查询所有用户的信息
     * @param pageIndex
     * @param pageSize
     * @return 
     */
    public List<UserEntity> inquireUserEntityList(long pageIndex, long pageSize);
    
    /**
     * 增加收藏图片
     * @param id
     * @param imageId 
     */
    public void addFavoriteImage(String id, String imageId);
    
    /**
     * 删除收藏的图片
     * @param id
     * @param imageId 
     */
    public void deleteFavoriteImage(String id, String imageId);
    
    /**
     * 查询我的收藏图片
     * @param id
     * @return 
     */
    public List<String> inquireFavorite(String id);
    
    /**
     * 更新用户的android积分
     * @param id
     * @param androidPoint 
     */
    public void updateAndroidPoint(String id, String androidPoint);
    
    /**
     * 更新用户的ios积分
     * @param id
     * @param iosPoint 
     */
    public void updateIosPoint(String id, String iosPoint);
    
    /**
     * 获取history dataId.dataId = yyyy-mm-dd + '_' + id
     * @param id
     * @return 
     */
    public String getPointHistoryLastDateId(String id);
    
    /**
     * 查询用户前一天的android的积分
     * @param id
     * @return 
     */
    public long inquireLastdayAndroidPoint(String id);
    
    /**
     * 查询用户前一天的ios的积分
     * @param id
     * @return 
     */
    public long inquireLastdayIosPoint(String id);
    
    /**
     * 查询用户的积分变化记录
     * @param id
     * @return 
     */
    public List<PointHistoryEntity> inquireUserPointHistory(String id);
    
    /**
     * 插入某个用户积分历史记录
     * @param historyEntityMap 
     */
    public void insertPointHistory(Map<String, String> historyEntityMap);
    
    /**
     * 新增android提现订单
     * @param userId
     * @param zfb
     * @param androidPoint 
     */
    public String insertMoneyOrderFromAndroid(String userId, String zfb, String androidPoint);
    
    /**
     * 新增ios提现订单
     * @param userId
     * @param zfb
     * @param iosPoint
     * @return 
     */
    public String insertMoneyOrderFromIos(String userId, String zfb, String iosPoint);
    
    /**
     * 新增android充话费订单
     * @param userId
     * @param cellPhone
     * @param androidPoint
     * @return 
     */
    public String insertPhoneBillOrderFromAndroid(String userId, String cellPhone, String androidPoint);
    
    /**
     * 新增ios充话费订单
     * @param userId
     * @param cellPhone
     * @param iosPoint
     * @return 
     */
    public String insertPhoneBillOrderFromIos(String userId, String cellPhone, String iosPoint);
    
    /**
     * 完成订单
     * @param orderId
     * @param note 
     */
    public void finishOrder(String orderId, String note);
    
    /**
     * 无效的订单
     * @param orderId
     * @param note
     */
    public void invalidOrder(String orderId, String note);
}
