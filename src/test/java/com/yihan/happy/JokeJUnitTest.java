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
public class JokeJUnitTest extends AbstractHappyTest {

    public JokeJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

    @Test
    public void testInquireJokePage() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("pageSize", "2");
        Response response = this.testHandler.execute(ActionNames.INQUIRE_JOKE_PAGE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testVoteUpJoke() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("id", "300001");
        Response response = this.testHandler.execute(ActionNames.VOTE_UP_JOKE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testVoteDownIJoke() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("id", "300001");
        Response response = this.testHandler.execute(ActionNames.VOTE_DOWN_JOKE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testInsertJoke() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("content", "test");
        parameterMap.put("voteUp", "1");
        parameterMap.put("voteDown", "2");
        parameterMap.put("password", "bigcodebang");
        Response response = this.testHandler.execute(ActionNames.INSERT_JOKE, parameterMap);
        System.out.println(response.getResponseMessage());
    }

//    @Test
    public void testDeleteJoke() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("id", "300001");
        parameterMap.put("password", "bigcodebang");
        Response response = this.testHandler.execute(ActionNames.DELETE_JOKE, parameterMap);
        System.out.println(response.getResponseMessage());
    }
}