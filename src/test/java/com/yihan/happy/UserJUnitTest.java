package com.yihan.happy;

import com.wolf.framework.utils.SecurityUtils;
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

    @Test
    public void testSinaLogin() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("sinaId", "261455");
        Response response = this.testHandler.execute(ActionNames.SINA_USER_LOGIN, parameterMap);
        System.out.println(response.getResponseMessage());
    }
}