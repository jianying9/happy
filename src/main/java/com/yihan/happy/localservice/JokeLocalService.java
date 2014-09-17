package com.yihan.happy.localservice;

import com.wolf.framework.local.Local;
import com.yihan.happy.entity.JokeEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
public interface JokeLocalService extends Local {

    public boolean existJoke(String id);

    public String insertJoke(Map<String, String> entityMap);

    public void deleteJoke(String id);

    public long voteUpJoke(String id);

    public long voteDownJoke(String id);

    public List<JokeEntity> inquireJokeEntityListDESC(long pageIndex, long pageSize);

    public List<JokeEntity> inquireJokeEntityListByIdList(List<String> imageIdList);
}
