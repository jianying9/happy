package com.yihan.happy.server.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.local.LocalServiceConfig;
import com.yihan.happy.server.entity.ServerConfigEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jianying9
 */
@LocalServiceConfig(
        interfaceInfo = ServerLocalService.class,
        description = "系统服务相关接口")
public class ServerLocalServiceImpl implements ServerLocalService {

    @InjectRDao(clazz = ServerConfigEntity.class)
    private REntityDao<ServerConfigEntity> serverConfigEntityDao;

    @Override
    public void init() {
        //初始化
        Map<String, String> configMap = new HashMap<String, String>(2, 1);
        boolean exist = this.serverConfigEntityDao.exist(ServerLocalService.ANDROID_VERSION);
        if (exist == false) {
            configMap.put("name", ServerLocalService.ANDROID_URL);
            configMap.put("value", "http://www.bigcodebang.com/download.html");
            this.serverConfigEntityDao.insert(configMap);
            //
            configMap.put("name", ServerLocalService.ANDROID_VERSION);
            configMap.put("value", "1.0");
            this.serverConfigEntityDao.insert(configMap);
            //
            configMap.put("name", ServerLocalService.ANDROID_CODE);
            configMap.put("value", "1");
            this.serverConfigEntityDao.insert(configMap);
        }
        //
        exist = this.serverConfigEntityDao.exist(ServerLocalService.IOS_VERSION);
        if (exist == false) {
            configMap.put("name", ServerLocalService.IOS_URL);
            configMap.put("value", "http://www.bigcodebang.com/download.html");
            this.serverConfigEntityDao.insert(configMap);
            //
            configMap.put("name", ServerLocalService.IOS_VERSION);
            configMap.put("value", "1.0");
            this.serverConfigEntityDao.insert(configMap);
            //
            configMap.put("name", ServerLocalService.IOS_POINT_SWICH);
            configMap.put("value", "off");
            this.serverConfigEntityDao.insert(configMap);
        }
    }

    @Override
    public Map<String, String> inquireIosConfig() {
        List<String> keyList = new ArrayList<String>(3);
        keyList.add(ServerLocalService.IOS_URL);
        keyList.add(ServerLocalService.IOS_VERSION);
        keyList.add(ServerLocalService.IOS_POINT_SWICH);
        List<ServerConfigEntity> entityList = this.serverConfigEntityDao.inquireByKeys(keyList);
        Map<String, String> configMap = new HashMap<String, String>(4, 1);
        for (ServerConfigEntity serverConfigEntity : entityList) {
            configMap.put(serverConfigEntity.getName(), serverConfigEntity.getValue());
        }
        return configMap;
    }

    @Override
    public void openIosPointSwitch() {
        Map<String, String> configMap = new HashMap<String, String>(2, 1);
        configMap.put("name", ServerLocalService.IOS_POINT_SWICH);
        configMap.put("value", "on");
        this.serverConfigEntityDao.update(configMap);
    }

    @Override
    public void clodeIosPointSwitch() {
        Map<String, String> configMap = new HashMap<String, String>(2, 1);
        configMap.put("name", ServerLocalService.IOS_POINT_SWICH);
        configMap.put("value", "off");
        this.serverConfigEntityDao.update(configMap);
    }

    @Override
    public void updateIosConfig(String version, String url) {
        Map<String, String> configMap = new HashMap<String, String>(2, 1);
        configMap.put("name", ServerLocalService.IOS_URL);
        configMap.put("value", url);
        this.serverConfigEntityDao.update(configMap);
        //
        configMap.put("name", ServerLocalService.IOS_VERSION);
        configMap.put("value", version);
        this.serverConfigEntityDao.update(configMap);
    }

    @Override
    public Map<String, String> inquireAndroidConfig() {
        List<String> keyList = new ArrayList<String>(3);
        keyList.add(ServerLocalService.ANDROID_URL);
        keyList.add(ServerLocalService.ANDROID_VERSION);
        keyList.add(ServerLocalService.ANDROID_CODE);
        List<ServerConfigEntity> entityList = this.serverConfigEntityDao.inquireByKeys(keyList);
        Map<String, String> configMap = new HashMap<String, String>(4, 1);
        for (ServerConfigEntity serverConfigEntity : entityList) {
            configMap.put(serverConfigEntity.getName(), serverConfigEntity.getValue());
        }
        return configMap;
    }

    @Override
    public void updateAndroidConfig(String version, String url, String code) {
        Map<String, String> configMap = new HashMap<String, String>(2, 1);
        configMap.put("name", ServerLocalService.ANDROID_URL);
        configMap.put("value", url);
        this.serverConfigEntityDao.update(configMap);
        //
        configMap.put("name", ServerLocalService.ANDROID_VERSION);
        configMap.put("value", version);
        this.serverConfigEntityDao.update(configMap);
        //
        configMap.put("name", ServerLocalService.ANDROID_CODE);
        configMap.put("value", code);
        this.serverConfigEntityDao.update(configMap);
    }
}