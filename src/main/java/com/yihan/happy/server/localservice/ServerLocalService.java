package com.yihan.happy.server.localservice;

import com.wolf.framework.local.Local;
import java.util.Map;

/**
 *
 * @author aladdin
 */
public interface ServerLocalService extends Local {

    //android的版本名称
    public String ANDROID_VERSION = "androidVersion";
    //android的版本号
    public String ANDROID_CODE = "androidCode";
    //android的下载地址
    public String ANDROID_URL = "androidUrl";
    
    //ios积分墙开关on,off
    public String IOS_POINT_SWICH = "iosPointSwitch";
    //ios的版本号
    public String IOS_VERSION = "iosVersion";
    //ios的下载地址
    public String IOS_URL = "iosUrl";
    
    public Map<String, String> inquireIosConfig();
    
    public void updateIosConfig(String version, String url);
    
    public void openIosPointSwitch();
    
    public void clodeIosPointSwitch();
    
    public Map<String, String> inquireAndroidConfig();
    
    public void updateAndroidConfig(String version, String url, String code);
}
