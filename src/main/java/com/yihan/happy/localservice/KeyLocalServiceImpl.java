package com.yihan.happy.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.local.LocalServiceConfig;
import com.yihan.happy.entity.KeyEntity;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jianying9
 */
@LocalServiceConfig(
        interfaceInfo = KeyLocalService.class,
        description = "redis table主键值管理")
public class KeyLocalServiceImpl implements KeyLocalService {

    @InjectRDao(clazz = KeyEntity.class)
    private REntityDao<KeyEntity> keyEntityDao;

    @Override
    public void init() {
    }

    @Override
    public long getMaxKeyValue(String tableName) {
        long value = 0;
        KeyEntity keyEntity = this.keyEntityDao.inquireByKey(tableName);
        if (keyEntity != null) {
            value = keyEntity.getIndexValue();
        }
        return value;
    }

    @Override
    public void updateMaxKeyValue(String tableName, long value) {
        Map<String, String> updateMap = new HashMap<String, String>(2, 1);
        updateMap.put("tableName", tableName);
        updateMap.put("indexValue", Long.toString(value));
        this.keyEntityDao.insert(updateMap);
    }

    @Override
    public long nextKeyValue(String tableName) {
        return this.keyEntityDao.increase(tableName, "indexValue", 1);
    }
}
