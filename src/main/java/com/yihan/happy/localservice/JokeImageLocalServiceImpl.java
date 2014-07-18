package com.yihan.happy.localservice;

import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquirePageContext;
import com.wolf.framework.local.LocalServiceConfig;
import com.yihan.happy.entity.JokeImageEntity;
import com.yihan.happy.entity.JokeImageSourceEntity;
import com.yihan.happy.spider.HttpClientManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

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
    private volatile HttpClientManager httpClientManager;
    private final String imageUrl = "http://www.hao123.com/gaoxiao/screen/all/";

    @Override
    public void init() {
        PoolingClientConnectionManager cm;
        DefaultHttpClient httpClient;
        HttpClientManager hcm = new HttpClientManager();
        for (int index = 0; index < 20; index++) {
            cm = new PoolingClientConnectionManager();
            cm.setMaxTotal(10);
            httpClient = new DefaultHttpClient(cm);
            hcm.add(httpClient);
        }
        this.httpClientManager = hcm;
    }

    private String getUrl(String url) {
        System.out.println(url);
        String responseBody = "";
        DefaultHttpClient client = this.httpClientManager.getClient();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpGet httpGet = new HttpGet(url);
        HttpConnectionParams.setConnectionTimeout(httpGet.getParams(), 20000);
        HttpConnectionParams.setSoTimeout(httpGet.getParams(), 20000);
        try {
            responseBody = client.execute(httpGet, responseHandler);
        } catch (IOException ex) {
        }
        return responseBody;
    }

    @Override
    public List<ImageInfo> getPage(long pageIndex) {
        List<ImageInfo> resultList;
        String url = this.imageUrl.concat(Long.toString(pageIndex));
        String responseText = this.getUrl(url);
        if (responseText.isEmpty()) {
            resultList = new ArrayList<ImageInfo>(0);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode rootNode = null;
            try {
                rootNode = mapper.readValue(responseText, ArrayNode.class);
            } catch (IOException e) {
            }
            if (rootNode == null) {
                resultList = new ArrayList<ImageInfo>(0);
            } else {
                resultList = new ArrayList<ImageInfo>();
                JsonNode jsonNode;
                String id;
                String title;
                String content;
                JsonNode urlNode;
                String picurl;
                long height;
                long width;
                String sPicurl;
                long sHeight;
                long sWidth;
                String mPicurl;
                long mHeight;
                long mWidth;
                String tag;
                String createTime;
                long voteUp;
                long voteDown;
                long share;
                JsonNode fromNode;
                String fromUrl;
                String fromName;
                String linkUrl;
                long comment;
                ImageInfo imageInfo;
                Iterator<JsonNode> jsonNodes = rootNode.iterator();
                while (jsonNodes.hasNext()) {
                    jsonNode = jsonNodes.next();
//                    System.out.println(jsonNode);
                    id = jsonNode.get("id").getTextValue();
                    title = jsonNode.get("title").getTextValue();
                    content = jsonNode.get("content").getTextValue();
                    linkUrl = jsonNode.get("linkurl").getTextValue();
                    urlNode = jsonNode.get("picurl");
                    picurl = urlNode.get("0").get("picurl").getTextValue();
                    height = urlNode.get("0").get("height").getLongValue();
                    width = urlNode.get("0").get("width").getLongValue();
                    sPicurl = urlNode.get("212").get("picurl").getTextValue();
                    sHeight = urlNode.get("212").get("height").getLongValue();
                    sWidth = urlNode.get("212").get("width").getLongValue();
                    mPicurl = urlNode.get("430").get("picurl").getTextValue();
                    mHeight = urlNode.get("430").get("height").getLongValue();
                    mWidth = urlNode.get("430").get("width").getLongValue();
                    tag = jsonNode.get("ctg").getTextValue();
                    createTime = jsonNode.get("create_at").getTextValue();
                    voteUp = jsonNode.get("vote_up").getLongValue();
                    voteDown = jsonNode.get("vote_down").getLongValue();
                    share = jsonNode.get("share").getLongValue();
                    fromNode = jsonNode.get("from");
                    fromUrl = fromNode.get("link").getTextValue();
                    if (fromUrl == null) {
                        fromUrl = "";
                    }
                    fromName = fromNode.get("title").getTextValue();
                    if (fromName == null) {
                        fromName = "";
                    }
                    if (jsonNode.get("comment_count") == null) {
                        comment = 0;
                    } else {
                        comment = jsonNode.get("comment_count").getLongValue();
                    }
                    imageInfo = new ImageInfo();
                    imageInfo.setId(id);
                    imageInfo.setTitle(title);
                    imageInfo.setContent(content);
                    imageInfo.setTag(tag);
                    imageInfo.setVoteUp(voteUp);
                    imageInfo.setVoteDown(voteDown);
                    imageInfo.setShare(share);
                    imageInfo.setComment(comment);
                    createTime = createTime.concat("000");
                    imageInfo.setCreateTime(Long.parseLong(createTime));
                    imageInfo.setHeight(height);
                    imageInfo.setWidth(width);
                    imageInfo.setPicurl(picurl);
                    imageInfo.setsPicurl(sPicurl);
                    imageInfo.setsHeight(sHeight);
                    imageInfo.setsWidth(sWidth);
                    imageInfo.setmPicurl(mPicurl);
                    imageInfo.setmHeight(mHeight);
                    imageInfo.setmWidth(mWidth);
                    imageInfo.setFromUrl(fromUrl);
                    imageInfo.setFromName(fromName);
                    imageInfo.setLinkUrl(linkUrl);
                    resultList.add(imageInfo);
                }
            }
        }
        return resultList;
    }

    @Override
    public boolean existImage(String id) {
        return this.jokeImageEntityDao.exist(id);
    }

    @Override
    public void insertImage(Map<String, String> entityMap) {
        long source = Long.parseLong(entityMap.get("createTime"));
        this.jokeImageEntityDao.setKeySorce(entityMap, source);
        this.jokeImageEntityDao.insert(entityMap);
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
