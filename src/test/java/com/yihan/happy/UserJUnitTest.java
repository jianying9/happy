package com.yihan.happy;

import com.wolf.framework.worker.context.Response;
import com.yihan.happy.config.ActionNames;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aladdin
 */
public class UserJUnitTest extends AbstractHappyTest {

    public UserJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

//    @Test
    public void testSinaLogin() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("sinaId", "261455");
        Response response = this.testHandler.execute(ActionNames.SINA_USER_LOGIN, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testAddFavoriteIamge() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("imageId", "300007");
        Response response = this.testHandler.execute(ActionNames.ADD_FAVORITE_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testDeleteFavoriteIamge() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("imageId", "261455");
        Response response = this.testHandler.execute(ActionNames.DELETE_FAVORITE_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
    @Test
    public void testInquireFavoriteIamge() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        Response response = this.testHandler.execute(ActionNames.INQUIRE_FAVORITE_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testUpdateAndroidPoint() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("androidPoint", "600000");
        Response response = this.testHandler.execute(ActionNames.UPDATE_ANDROID_POINT, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testUpdateIosPoint() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("iosPoint", "100");
        Response response = this.testHandler.execute(ActionNames.UPDATE_IOS_POINT, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testUpdatePointHistory() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("password", "bigcodebang");
        Response response = this.testHandler.execute(ActionNames.UPDATE_POINT_HISTORY, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testOrderForMoneyFormAndroid() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("zfb", "bigcodebang@163.com");
        parameterMap.put("androidPoint", "100");
        Response response = this.testHandler.execute(ActionNames.ORDER_FOR_MONEY_FROM_ANDROID, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testOrderForMoneyFormIos() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("zfb", "bigcodebang@163.com");
        parameterMap.put("iosPoint", "100");
        Response response = this.testHandler.execute(ActionNames.ORDER_FOR_MONEY_FROM_IOS, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testOrderForPhoneBillFormAndroid() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("cellPhone", "13459118551");
        parameterMap.put("androidPoint", "100");
        Response response = this.testHandler.execute(ActionNames.ORDER_FOR_PHONE_BILL_FROM_ANDROID, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testOrderForPhoneBillFormIos() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("cellPhone", "13459118551");
        parameterMap.put("iosPoint", "100");
        Response response = this.testHandler.execute(ActionNames.ORDER_FOR_PHONE_BILL_FROM_IOS, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testFinishOrder() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("orderId", "100001");
        parameterMap.put("note", "兑换100");
        Response response = this.testHandler.execute(ActionNames.FINISH_ORDER, parameterMap);
        System.out.println(response.getResponseMessage());
    }
}