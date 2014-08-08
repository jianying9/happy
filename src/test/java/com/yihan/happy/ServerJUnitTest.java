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
public class ServerJUnitTest extends AbstractHappyTest {

    public ServerJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

    @Test
    public void testInquireIosConfig() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        Response response = this.testHandler.execute(ActionNames.INQUIRE_IOS_CONFIG, parameterMap);
        System.out.println(response.getResponseMessage());
    }

    @Test
    public void testOpenIosPoint() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        Response response = this.testHandler.execute(ActionNames.OPEN_IOS_POINT, parameterMap);
        System.out.println(response.getResponseMessage());
    }

    @Test
    public void testCloseIosPoint() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        Response response = this.testHandler.execute(ActionNames.CLOSE_IOS_POINT, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
    @Test
    public void testUpdateIosConfig() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("iosUrl", "testIos");
        parameterMap.put("iosVersion", "3.0");
        Response response = this.testHandler.execute(ActionNames.UPDATE_IOS_CONFIG, parameterMap);
        System.out.println(response.getResponseMessage());
    }

    @Test
    public void testInquireAndroidConfig() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        Response response = this.testHandler.execute(ActionNames.INQUIRE_ANDROID_CONFIG, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
    @Test
    public void testUpdateAndroidConfig() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("androidUrl", "test");
        parameterMap.put("androidVersion", "2.0");
        parameterMap.put("androidCode", "3");
        Response response = this.testHandler.execute(ActionNames.UPDATE_ANDROID_CONFIG, parameterMap);
        System.out.println(response.getResponseMessage());
    }
}