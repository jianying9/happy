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
public class JokeImageJUnitTest extends AbstractHappyTest {

    public JokeImageJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

    @Test
    public void testInquireImagePage() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("pageSize", "2");
        Response response = this.testHandler.execute(ActionNames.INQUIRE_IMAGE_PAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testVoteUpImage() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("id", "170793");
        Response response = this.testHandler.execute(ActionNames.VOTE_UP_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testVoteDownImage() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("id", "170793");
        Response response = this.testHandler.execute(ActionNames.VOTE_DOWN_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testInsertImage() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("title", "test");
        parameterMap.put("voteUp", "1");
        parameterMap.put("voteDown", "2");
        parameterMap.put("sinaUrl", "http://ww1.sina.com/text/2222.jpg");
        parameterMap.put("length", "1222");
        parameterMap.put("lWidth", "600");
        parameterMap.put("lHeight", "800");
        parameterMap.put("mWidth", "440");
        parameterMap.put("mHeight", "640");
        parameterMap.put("sWidth", "67");
        parameterMap.put("sHeight", "88");
        parameterMap.put("password", "bigcodebang");
        Response response = this.testHandler.execute(ActionNames.INSERT_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }
    
//    @Test
    public void testDeleteImage() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("id", "300004");
        parameterMap.put("password", "bigcodebang");
        Response response = this.testHandler.execute(ActionNames.DELETE_IMAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }
}