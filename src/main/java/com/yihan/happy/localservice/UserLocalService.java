package com.yihan.happy.localservice;

import com.wolf.framework.local.Local;
import java.util.List;

/**
 *
 * @author aladdin
 */
public interface UserLocalService extends Local {

    public String loginBySinaUser(String sinaId);
    
    public void addFavoriteImage(String id, String imageId);
    
    public void deleteFavoriteImage(String id, String imageId);
    
    public List<String> inquireFavorite(String id);
}
