package com.yihan.happy.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquirePageContext;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import com.yihan.happy.config.TableNames;
import com.yihan.happy.entity.JokeEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@LocalServiceConfig(
        interfaceInfo = JokeLocalService.class,
        description = "JOKE相关接口")
public class JokeLocalServiceImpl implements JokeLocalService {

    @InjectRDao(clazz = JokeEntity.class)
    private REntityDao<JokeEntity> jokeEntityDao;
    //
    @InjectLocalService()
    private KeyLocalService keyLocalService;

    @Override
    public void init() {
        //初始化Y_JOKE主键
        long maxKeyValue = this.keyLocalService.getMaxKeyValue(TableNames.Y_JOKE);
        if (maxKeyValue < 300000) {
            this.keyLocalService.updateMaxKeyValue(TableNames.Y_JOKE, 300000);
        }
    }

    @Override
    public boolean existJoke(String id) {
        return this.jokeEntityDao.exist(id);
    }

    @Override
    public String insertJoke(Map<String, String> entityMap) {
        long newId = this.keyLocalService.nextKeyValue(TableNames.Y_JOKE);
        String id = Long.toString(newId);
        entityMap.put("id", id);
        long source = System.currentTimeMillis();
        this.jokeEntityDao.setKeySorce(entityMap, source);
        this.jokeEntityDao.insert(entityMap);
        return id;
    }

    @Override
    public void deleteJoke(String id) {
        this.jokeEntityDao.delete(id);
    }

    @Override
    public List<JokeEntity> inquireJokeEntityListDESC(long pageIndex, long pageSize) {
        InquirePageContext inquirePageContext = new InquirePageContext();
        inquirePageContext.setPageSize(pageSize);
        inquirePageContext.setPageIndex(pageIndex);
        return this.jokeEntityDao.inquireDESC(inquirePageContext);
    }

    @Override
    public List<JokeEntity> inquireJokeEntityListByIdList(List<String> imageIdList) {
        return this.jokeEntityDao.inquireByKeys(imageIdList);
    }

    @Override
    public long voteUpJoke(String id) {
        return this.jokeEntityDao.increase(id, "voteUp", 1);
    }

    @Override
    public long voteDownJoke(String id) {
        return this.jokeEntityDao.increase(id, "voteDown", 1);
    }
}
