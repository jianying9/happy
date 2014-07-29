package com.yihan.happy.user.localservice;

import com.wolf.framework.local.Local;
import com.yihan.happy.user.entity.DuomenPointHistoryEntity;
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
     * 更新用户的多盟android积分
     * @param id
     * @param duomenAndroidPoint 
     */
    public void updateDuomenAndroidPoint(String id, String duomenAndroidPoint);
    
    /**
     * 更新用户的多盟ios积分
     * @param id
     * @param duomenIosPoint 
     */
    public void updateDuomenIosPoint(String id, String duomenIosPoint);
    
    /**
     * 获取history dataId.dataId = yyyy-mm-dd + '_' + id
     * @param id
     * @return 
     */
    public String getDuomenPointHistoryLastDateId(String id);
    
    /**
     * 查询用户前一天的多盟android的积分
     * @param id
     * @return 
     */
    public long inquireLastTodayDuomenAndroidPoint(String id);
    
    /**
     * 查询用户前一天的多盟ios的积分
     * @param id
     * @return 
     */
    public long inquireLastTodayDuomenIosPoint(String id);
    
    /**
     * 查询用户的多盟积分变化记录
     * @param id
     * @return 
     */
    public List<DuomenPointHistoryEntity> inquireUserDuomenPointHistory(String id);
    
    /**
     * 插入某个用户多盟积分历史记录
     * @param historyEntityMap 
     */
    public void insertDuomenPointHistory(Map<String, String> historyEntityMap);
    
    /**
     * 新增android提现订单
     * @param userId
     * @param zfb
     * @param duomenAndroidPoint 
     */
    public String insertMoneyOrderFromAndroid(String userId, String zfb, String duomenAndroidPoint);
    
    /**
     * 新增ios提现订单
     * @param userId
     * @param zfb
     * @param duomenIosPoint
     * @return 
     */
    public String insertMoneyOrderFromIos(String userId, String zfb, String duomenIosPoint);
    
    /**
     * 新增android充话费订单
     * @param userId
     * @param cellPhone
     * @param duomenAndroidPoint
     * @return 
     */
    public String insertPhoneBillOrderFromAndroid(String userId, String cellPhone, String duomenAndroidPoint);
    
    /**
     * 新增ios充话费订单
     * @param userId
     * @param cellPhone
     * @param duomenIosPoint
     * @return 
     */
    public String insertPhoneBillOrderFromIos(String userId, String cellPhone, String duomenIosPoint);
    
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
