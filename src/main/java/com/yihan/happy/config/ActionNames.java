package com.yihan.happy.config;

/**
 *
 * @author aladdin
 */
public class ActionNames {
    
    //-----------------------图片爬虫------------------------//
    //抓取图片
    public final static String UPDATE_IMAGE_INFO = "UPDATE_IMAGE_INFO";
    //分页查询最新的图片
    public final static String INQUIRE_IMAGE_PAGE = "INQUIRE_IMAGE_PAGE";
    //图片点赞
    public final static String VOTE_UP_IMAGE = "VOTE_UP_IMAGE";
    //图片点踩
    public final static String VOTE_DOWN_IMAGE = "VOTE_DOWN_IMAGE";
    //------------------------用户---------------------------//
    //sina用户登录
    public final static String SINA_USER_LOGIN = "SINA_USER_LOGIN";
    //收藏图片
    public final static String ADD_FAVORITE_IMAGE = "ADD_FAVORITE_IMAGE";
    //收藏图片
    public final static String DELETE_FAVORITE_IMAGE = "DELETE_FAVORITE_IMAGE";
    //查看我的收藏图片
    public final static String INQUIRE_FAVORITE_IMAGE = "INQUIRE_FAVORITE_IMAGE";
}
