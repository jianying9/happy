package com.yihan.happy.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import com.yihan.happy.config.TableNames;
import com.yihan.happy.entity.SinaUserMapEntity;
import com.yihan.happy.entity.UserEntity;
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
}
