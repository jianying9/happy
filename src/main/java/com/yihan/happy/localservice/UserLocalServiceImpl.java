package com.yihan.happy.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquirePageContext;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import com.wolf.framework.utils.TimeUtils;
import com.yihan.happy.config.TableNames;
import com.yihan.happy.entity.DuomenPointHistoryEntity;
import com.yihan.happy.entity.SinaUserMapEntity;
import com.yihan.happy.entity.UserEntity;
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
    @InjectLocalService()
    private KeyLocalService keyLocalService;
    //
    @InjectRDao(clazz = DuomenPointHistoryEntity.class)
    private REntityDao<DuomenPointHistoryEntity> duomenPointHistoryEntityDao;

    @Override
    public void init() {
        long maxKeyValue = this.keyLocalService.getMaxKeyValue(TableNames.Y_USER);
        if (maxKeyValue < 100000) {
            this.keyLocalService.updateMaxKeyValue(TableNames.Y_USER, 100000);
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
    public void updateDuomenAndroidPoint(String id, String duomenAndroidPoint) {
        Map<String, String> entityMap = new HashMap<String, String>(2, 1);
        entityMap.put("id", id);
        entityMap.put("duomenAndroidPoint", duomenAndroidPoint);
        this.userEntityDao.update(entityMap);
    }

    /**
     * 获取history dataId.dataId = yyyy-mm-dd + '_' + id
     * @param id
     * @return 
     */
    @Override
    public String getDuomenPointHistoryLastDateId(String id) {
        //计算前一天的日期
        Calendar  currentTime  =  Calendar.getInstance();
        currentTime.add(Calendar.DAY_OF_MONTH, -1);
        Date currentDate = currentTime.getTime();
        String dataStr = TimeUtils.FM_YYMMDD.format(currentDate);
        StringBuilder dataIdBuilder = new StringBuilder(20);
        dataIdBuilder.append(dataStr).append('_').append(id);
        return dataIdBuilder.toString();
    }

    @Override
    public long inquireLastTodayDuomenAndroidPoint(String id) {
        long lastAndroidPoint = 0;
        //计算前一天的日期
        String lastDateId = this.getDuomenPointHistoryLastDateId(id);
        DuomenPointHistoryEntity duomenPointHistoryEntity = this.duomenPointHistoryEntityDao.inquireByKey(lastDateId);
        if(duomenPointHistoryEntity != null) {
            lastAndroidPoint = duomenPointHistoryEntity.getDuomenAndroidPoint();
        }
        return lastAndroidPoint;
    }

    @Override
    public void updateDuomenIosPoint(String id, String duomenIosPoint) {
        Map<String, String> entityMap = new HashMap<String, String>(2, 1);
        entityMap.put("id", id);
        entityMap.put("duomenIosPoint", duomenIosPoint);
        this.userEntityDao.update(entityMap);
    }
    
    @Override
    public long inquireLastTodayDuomenIosPoint(String id) {
        long lastIosPoint = 0;
        //计算前一天的日期
        String lastDateId = this.getDuomenPointHistoryLastDateId(id);
        DuomenPointHistoryEntity duomenPointHistoryEntity = this.duomenPointHistoryEntityDao.inquireByKey(lastDateId);
        if(duomenPointHistoryEntity != null) {
            lastIosPoint = duomenPointHistoryEntity.getDuomenIosPoint();
        }
        return lastIosPoint;
    }
    
    @Override
    public List<DuomenPointHistoryEntity> inquireUserDuomenPointHistory(String id) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertDuomenPointHistory(Map<String, String> historyEntityMap) {
        this.duomenPointHistoryEntityDao.insert(historyEntityMap);
    }
}
