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
public class UpdateImageInfoJUnitTest extends AbstractHappyTest {

    public UpdateImageInfoJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

    @Test
    public void test() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("page", "2");
        Response response = this.testHandler.execute(ActionNames.UPDATE_IMAGE_INFO, parameterMap);
        System.out.println(response.getResponseMessage());
    }
}