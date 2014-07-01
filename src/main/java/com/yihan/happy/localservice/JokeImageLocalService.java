package com.yihan.happy.localservice;

import com.wolf.framework.local.Local;
import com.yihan.happy.entity.JokeImageEntity;
import com.yihan.happy.entity.JokeImageSourceEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
public interface JokeImageLocalService extends Local {

    public List<ImageInfo> getPage(long pageIndex);
    
    public boolean existImage(String id);
    
    public void insertImage(Map<String, String> entityMap);
    
    public void deleteImage(String id);
    
    public long voteUpImage(String id);
    
    public long voteDownImage(String id);
    
    public List<JokeImageEntity> inquireJokeImageEntityListDESC(long pageIndex, long pageSize);
    
    public boolean existImageSource(String url);
    
    public void insertImageSource(String url, String name);
    
    public void deleteImageSource(String url);
    
    public List<JokeImageSourceEntity> inquireJokeImageSourceEntityList(long pageIndex, long pageSize);
}
