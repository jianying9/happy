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
    //保存当前用户的多盟的android积分
    public final static String UPDATE_DUOMEN_ANDORID_POINT = "UPDATE_DUOMEN_ANDORID_POINT";
    //保存当前用户的多盟的ios积分
    public final static String UPDATE_DUOMEN_IOS_POINT = "UPDATE_DUOMEN_IOS_POINT";
    //定时记录用户的当天的积分情况
    public final static String UPDATE_DUOMEN_POINT_HISTORY = "UPDATE_DUOMEN_POINT_HISTORY";
    //android积分提现
    public final static String ORDER_FOR_MONEY_FROM_ANDROID= "ORDER_FOR_MONEY_FROM_ANDROID";
    //ios积分提现
    public final static String ORDER_FOR_MONEY_FROM_IOS= "ORDER_FOR_MONEY_FROM_IOS";
    //android积分充值话费
    public final static String ORDER_FOR_PHONE_BILL_FROM_ANDROID= "ORDER_FOR_PHONE_BILL_FROM_ANDROID";
    //IOS积分充值话费
    public final static String ORDER_FOR_PHONE_BILL_FROM_IOS= "ORDER_FOR_PHONE_BILL_FROM_IOS";
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
}
