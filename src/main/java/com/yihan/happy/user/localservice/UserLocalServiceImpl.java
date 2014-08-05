package com.yihan.happy.user.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquirePageContext;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import com.wolf.framework.utils.TimeUtils;
import com.yihan.happy.config.TableNames;
import com.yihan.happy.localservice.KeyLocalService;
import com.yihan.happy.user.entity.PointHistoryEntity;
import com.yihan.happy.user.entity.OrderEntity;
import com.yihan.happy.user.entity.SinaUserMapEntity;
import com.yihan.happy.user.entity.UserEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@LocalServiceConfig(
        interfaceInfo = UserLocalService.class,
        description = "用户接口")
public class UserLocalServiceImpl implements UserLocalService {

    @InjectRDao(clazz = UserEntity.class)
    private REntityDao<UserEntity> userEntityDao;
    //
    @InjectRDao(clazz = SinaUserMapEntity.class)
    private REntityDao<SinaUserMapEntity> sinaUserMapEntityDao;
    //
    @InjectRDao(clazz = OrderEntity.class)
    private REntityDao<OrderEntity> orderEntityDao;
    //
    @InjectLocalService()
    private KeyLocalService keyLocalService;
    //
    @InjectRDao(clazz = PointHistoryEntity.class)
    private REntityDao<PointHistoryEntity> pointHistoryEntityDao;

    @Override
    public void init() {
        //初始化Y_USER主键
        long maxKeyValue = this.keyLocalService.getMaxKeyValue(TableNames.Y_USER);
        if (maxKeyValue < 100000) {
            this.keyLocalService.updateMaxKeyValue(TableNames.Y_USER, 100000);
        }
        //初始化Y_ORDER主键
        maxKeyValue = this.keyLocalService.getMaxKeyValue(TableNames.Y_ORDER);
        if (maxKeyValue < 100000) {
            this.keyLocalService.updateMaxKeyValue(TableNames.Y_ORDER, 100000);
        }
    }

    @Override
    public String loginBySinaUser(String sinaId) {
        String id;
        SinaUserMapEntity sinaUserMapEntity = this.sinaUserMapEntityDao.inquireByKey(sinaId);
        synchronized (this) {
            if (sinaUserMapEntity == null) {
                long newId = this.keyLocalService.nextKeyValue(TableNames.Y_USER);
                id = Long.toString(newId);
                Map<String, String> newUserMap = new HashMap<String, String>(2, 1);
                newUserMap.put("id", id);
                newUserMap.put("sinaId", sinaId);
                this.userEntityDao.insert(newUserMap);
                this.sinaUserMapEntityDao.insert(newUserMap);
            } else {
                id = sinaUserMapEntity.getId();
            }
        }
        return id;
    }

    @Override
    public List<UserEntity> inquireUserEntityList(long pageIndex, long pageSize) {
        InquirePageContext inquirePageContext = new InquirePageContext();
        inquirePageContext.setPageIndex(pageIndex);
        inquirePageContext.setPageSize(pageSize);
        return this.userEntityDao.inquire(inquirePageContext);
    }

    @Override
    public void addFavoriteImage(String id, String imageId) {
        this.userEntityDao.sortedSetAdd(id, "favoriteImages", imageId, System.currentTimeMillis());
    }

    @Override
    public void deleteFavoriteImage(String id, String imageId) {
        this.userEntityDao.sortedSetRemove(id, "favoriteImages", imageId);
    }

    @Override
    public List<String> inquireFavorite(String id) {
        return this.userEntityDao.sortedSetDESC(id, "favoriteImages");
    }

    @Override
    public void updateAndroidPoint(String id, String androidPoint) {
        Map<String, String> entityMap = new HashMap<String, String>(2, 1);
        entityMap.put("id", id);
        entityMap.put("androidPoint", androidPoint);
        this.userEntityDao.update(entityMap);
    }

    /**
     * 获取history dateId = yyyy-mm-dd + '_' + id
     *
     * @param id
     * @return
     */
    @Override
    public String getPointHistoryLastDateId(String id) {
        //计算前一天的日期
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.DAY_OF_MONTH, -1);
        Date currentDate = currentTime.getTime();
        String dataStr = TimeUtils.FM_YYMMDD.format(currentDate);
        StringBuilder dataIdBuilder = new StringBuilder(20);
        dataIdBuilder.append(dataStr).append('_').append(id);
        return dataIdBuilder.toString();
    }

    @Override
    public long inquireLastdayAndroidPoint(String id) {
        long lastAndroidPoint = 0;
        //计算前一天的日期
        String lastDateId = this.getPointHistoryLastDateId(id);
        PointHistoryEntity pointHistoryEntity = this.pointHistoryEntityDao.inquireByKey(lastDateId);
        if (pointHistoryEntity != null) {
            lastAndroidPoint = pointHistoryEntity.getAndroidPoint();
        }
        return lastAndroidPoint;
    }

    @Override
    public void updateIosPoint(String id, String iosPoint) {
        Map<String, String> entityMap = new HashMap<String, String>(2, 1);
        entityMap.put("id", id);
        entityMap.put("iosPoint", iosPoint);
        this.userEntityDao.update(entityMap);
    }

    @Override
    public long inquireLastdayIosPoint(String id) {
        long lastIosPoint = 0;
        //计算前一天的日期
        String lastDateId = this.getPointHistoryLastDateId(id);
        PointHistoryEntity pointHistoryEntity = this.pointHistoryEntityDao.inquireByKey(lastDateId);
        if (pointHistoryEntity != null) {
            lastIosPoint = pointHistoryEntity.getIosPoint();
        }
        return lastIosPoint;
    }

    @Override
    public List<PointHistoryEntity> inquireUserPointHistory(String id) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertPointHistory(Map<String, String> historyEntityMap) {
        this.pointHistoryEntityDao.insert(historyEntityMap);
    }

    @Override
    public String insertMoneyOrderFromAndroid(String userId, String zfb, String androidPoint) {
        long newOrderId = this.keyLocalService.nextKeyValue(TableNames.Y_ORDER);
        String orderId = Long.toString(newOrderId);
        Map<String, String> entityMap = new HashMap<String, String>(8, 1);
        entityMap.put("orderId", orderId);
        entityMap.put("userId", userId);
        entityMap.put("type", UserLocalService.MONEY_ORDER_TYPE);
        entityMap.put("zfb", zfb);
        entityMap.put("androidPoint", androidPoint);
        entityMap.put("state", UserLocalService.UN_FINISH_ORDER_STATE);
        String createTime = Long.toString(System.currentTimeMillis());
        entityMap.put("createTime", createTime);
        this.orderEntityDao.insert(entityMap);
        return orderId;
    }

    @Override
    public String insertMoneyOrderFromIos(String userId, String zfb, String iosPoint) {
        long newOrderId = this.keyLocalService.nextKeyValue(TableNames.Y_ORDER);
        String orderId = Long.toString(newOrderId);
        Map<String, String> entityMap = new HashMap<String, String>(8, 1);
        entityMap.put("orderId", orderId);
        entityMap.put("userId", userId);
        entityMap.put("type", UserLocalService.MONEY_ORDER_TYPE);
        entityMap.put("zfb", zfb);
        entityMap.put("iosPoint", iosPoint);
        entityMap.put("state", UserLocalService.UN_FINISH_ORDER_STATE);
        String createTime = Long.toString(System.currentTimeMillis());
        entityMap.put("createTime", createTime);
        this.orderEntityDao.insert(entityMap);
        return orderId;
    }

    @Override
    public String insertPhoneBillOrderFromAndroid(String userId, String cellPhone, String androidPoint) {
        long newOrderId = this.keyLocalService.nextKeyValue(TableNames.Y_ORDER);
        String orderId = Long.toString(newOrderId);
        Map<String, String> entityMap = new HashMap<String, String>(8, 1);
        entityMap.put("orderId", orderId);
        entityMap.put("userId", userId);
        entityMap.put("type", UserLocalService.PHONE_BILL_ORDER_TYPE);
        entityMap.put("cellPhone", cellPhone);
        entityMap.put("androidPoint", androidPoint);
        entityMap.put("state", UserLocalService.UN_FINISH_ORDER_STATE);
        String createTime = Long.toString(System.currentTimeMillis());
        entityMap.put("createTime", createTime);
        this.orderEntityDao.insert(entityMap);
        return orderId;
    }

    @Override
    public String insertPhoneBillOrderFromIos(String userId, String cellPhone, String iosPoint) {
        long newOrderId = this.keyLocalService.nextKeyValue(TableNames.Y_ORDER);
        String orderId = Long.toString(newOrderId);
        Map<String, String> entityMap = new HashMap<String, String>(8, 1);
        entityMap.put("orderId", orderId);
        entityMap.put("userId", userId);
        entityMap.put("type", UserLocalService.PHONE_BILL_ORDER_TYPE);
        entityMap.put("cellPhone", cellPhone);
        entityMap.put("iosPoint", iosPoint);
        entityMap.put("state", UserLocalService.UN_FINISH_ORDER_STATE);
        String createTime = Long.toString(System.currentTimeMillis());
        entityMap.put("createTime", createTime);
        this.orderEntityDao.insert(entityMap);
        return orderId;
    }

    @Override
    public void finishOrder(String orderId, String note) {
        Map<String, String> entityMap = new HashMap<String, String>(4, 1);
        entityMap.put("state", UserLocalService.FINISH_ORDER_STATE);
        entityMap.put("note", note);
        entityMap.put("orderId", orderId);
        String finishTime = Long.toString(System.currentTimeMillis());
        entityMap.put("finishTime", finishTime);
        this.orderEntityDao.update(entityMap);
    }

    @Override
    public void invalidOrder(String orderId, String note) {
        Map<String, String> entityMap = new HashMap<String, String>(4, 1);
        entityMap.put("state", UserLocalService.INVALID_ORDER_STATE);
        entityMap.put("note", note);
        entityMap.put("orderId", orderId);
        this.orderEntityDao.update(entityMap);
    }
}