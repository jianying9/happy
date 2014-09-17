package com.yihan.happy.config;

/**
 *
 * @author aladdin
 */
public class ActionNames {

    //-----------------------图片------------------------//
    //新增图片
    public final static String INSERT_IMAGE = "INSERT_IMAGE";
    //删除图片
    public final static String DELETE_IMAGE = "DELETE_IMAGE";
    //分页查询最新的图片
    public final static String INQUIRE_IMAGE_PAGE = "INQUIRE_IMAGE_PAGE";
    //图片点赞
    public final static String VOTE_UP_IMAGE = "VOTE_UP_IMAGE";
    //图片点踩
    public final static String VOTE_DOWN_IMAGE = "VOTE_DOWN_IMAGE";
    //-----------------------笑话段子------------------------//
    //新增笑话
    public final static String INSERT_JOKE = "INSERT_JOKE";
    //删除笑话
    public final static String DELETE_JOKE = "DELETE_JOKE";
    //分页查询最新的笑话
    public final static String INQUIRE_JOKE_PAGE = "INQUIRE_JOKE_PAGE";
    //笑话点赞
    public final static String VOTE_UP_JOKE = "VOTE_UP_JOKE";
    //笑话点踩
    public final static String VOTE_DOWN_JOKE = "VOTE_DOWN_JOKE";
    //------------------------用户---------------------------//
    //sina用户登录
    public final static String SINA_USER_LOGIN = "SINA_USER_LOGIN";
    //保存当前用户的android积分
    public final static String UPDATE_ANDROID_POINT = "UPDATE_ANDROID_POINT";
    //保存当前用户的ios积分
    public final static String UPDATE_IOS_POINT = "UPDATE_IOS_POINT";
    //定时记录用户的当天的积分情况
    public final static String UPDATE_POINT_HISTORY = "UPDATE_POINT_HISTORY";
    //android积分提现
    public final static String ORDER_FOR_MONEY_FROM_ANDROID = "ORDER_FOR_MONEY_FROM_ANDROID";
    //ios积分提现
    public final static String ORDER_FOR_MONEY_FROM_IOS = "ORDER_FOR_MONEY_FROM_IOS";
    //android积分充值话费
    public final static String ORDER_FOR_PHONE_BILL_FROM_ANDROID = "ORDER_FOR_PHONE_BILL_FROM_ANDROID";
    //IOS积分充值话费
    public final static String ORDER_FOR_PHONE_BILL_FROM_IOS = "ORDER_FOR_PHONE_BILL_FROM_IOS";
    //完成订单
    public final static String FINISH_ORDER = "FINISH_ORDER";
    //订单信息错误，提示客户修改补充相关信息
    public final static String INVALID_ORDER = "INVALID_ORDER";
    //收藏图片
    public final static String ADD_FAVORITE_IMAGE = "ADD_FAVORITE_IMAGE";
    //收藏图片
    public final static String DELETE_FAVORITE_IMAGE = "DELETE_FAVORITE_IMAGE";
    //查看我的收藏图片
    public final static String INQUIRE_FAVORITE_IMAGE = "INQUIRE_FAVORITE_IMAGE";
    //------------------------系统配置---------------------------//
    //获取ios配置
    public final static String INQUIRE_IOS_CONFIG = "INQUIRE_IOS_CONFIG";
    //修改ios配置
    public final static String UPDATE_IOS_CONFIG = "UPDATE_IOS_CONFIG";
    //开启ios积分服务
    public final static String OPEN_IOS_POINT = "OPEN_IOS_POINT";
    //关闭ios积分服务
    public final static String CLOSE_IOS_POINT = "CLOSE_IOS_POINT";
    //获取android配置
    public final static String INQUIRE_ANDROID_CONFIG = "INQUIRE_ANDROID_CONFIG";
    //修改android的配置
    public final static String UPDATE_ANDROID_CONFIG = "UPDATE_ANDROID_CONFIG";
}
