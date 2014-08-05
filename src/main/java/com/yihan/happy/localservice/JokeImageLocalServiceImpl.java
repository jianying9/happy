package com.yihan.happy.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquirePageContext;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import com.yihan.happy.config.TableNames;
import com.yihan.happy.entity.JokeImageEntity;
import com.yihan.happy.entity.JokeImageSourceEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@LocalServiceConfig(
        interfaceInfo = JokeImageLocalService.class,
        description = "图片相关接口")
public class JokeImageLocalServiceImpl implements JokeImageLocalService {

    @InjectRDao(clazz = JokeImageEntity.class)
    private REntityDao<JokeImageEntity> jokeImageEntityDao;
    //
    @InjectRDao(clazz = JokeImageSourceEntity.class)
    private REntityDao<JokeImageSourceEntity> jokeImageSourceEntityDao;
    //
    @InjectLocalService()
    private KeyLocalService keyLocalService;

    @Override
    public void init() {
        //初始化Y_USER主键
        long maxKeyValue = this.keyLocalService.getMaxKeyValue(TableNames.Y_JOKE_IMAGE);
        if (maxKeyValue < 300000) {
            this.keyLocalService.updateMaxKeyValue(TableNames.Y_JOKE_IMAGE, 300000);
        }
    }

    @Override
    public boolean existImage(String id) {
        return this.jokeImageEntityDao.exist(id);
    }

    @Override
    public String insertImage(Map<String, String> entityMap) {
        long newId = this.keyLocalService.nextKeyValue(TableNames.Y_JOKE_IMAGE);
        String id = Long.toString(newId);
        entityMap.put("id", id);
        long source = System.currentTimeMillis();
        this.jokeImageEntityDao.setKeySorce(entityMap, source);
        this.jokeImageEntityDao.insert(entityMap);
        return id;
    }

    @Override
    public void deleteImage(String id) {
        this.jokeImageEntityDao.delete(id);
    }

    @Override
    public List<JokeImageEntity> inquireJokeImageEntityListDESC(long pageIndex, long pageSize) {
        InquirePageContext inquirePageContext = new InquirePageContext();
        inquirePageContext.setPageSize(pageSize);
        inquirePageContext.setPageIndex(pageIndex);
        return this.jokeImageEntityDao.inquireDESC(inquirePageContext);
    }

    @Override
    public List<JokeImageEntity> inquireJokeImageEntityListByIdList(List<String> imageIdList) {
        return this.jokeImageEntityDao.inquireByKeys(imageIdList);
    }

    @Override
    public boolean existImageSource(String url) {
        boolean result = true;
        if (url.isEmpty() == false) {
            result = this.jokeImageSourceEntityDao.exist(url);
        }
        return result;
    }

    @Override
    public void insertImageSource(String url, String name) {
        Map<String, String> entityMap = new HashMap<String, String>(2, 1);
        entityMap.put("url", url);
        entityMap.put("name", name);
        this.jokeImageSourceEntityDao.insert(entityMap);
    }

    @Override
    public void deleteImageSource(String url) {
        this.jokeImageSourceEntityDao.delete(url);
    }

    @Override
    public List<JokeImageSourceEntity> inquireJokeImageSourceEntityList(long pageIndex, long pageSize) {
        InquirePageContext inquirePageContext = new InquirePageContext();
        inquirePageContext.setPageSize(pageSize);
        inquirePageContext.setPageIndex(pageIndex);
        return this.jokeImageSourceEntityDao.inquire(inquirePageContext);
    }

    @Override
    public long voteUpImage(String id) {
        return this.jokeImageEntityDao.increase(id, "voteUp", 1);
    }

    @Override
    public long voteDownImage(String id) {
        return this.jokeImageEntityDao.increase(id, "voteDown", 1);
    }
}
