package com.yihan.happy;

import com.wolf.framework.config.FrameworkConfig;
import com.wolf.framework.context.ApplicationContext;
import com.wolf.framework.session.Session;
import com.wolf.framework.session.SessionImpl;
import com.wolf.framework.test.TestHandler;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;

/**
 *
 * @author aladdin
 */
public abstract class AbstractHappyTest {

    protected final TestHandler testHandler;

    public AbstractHappyTest() {
        Map<String, String> parameterMap = new HashMap<String, String>(8, 1);
        parameterMap.put(FrameworkConfig.ANNOTATION_SCAN_PACKAGES, "com.yihan.happy");
        parameterMap.put(FrameworkConfig.TASK_CORE_POOL_SIZE, "1");
        parameterMap.put(FrameworkConfig.TASK_MAX_POOL_SIZE, "2");
        //
        parameterMap.put(FrameworkConfig.REDIS_SERVER_HOST, "jianying9");
        parameterMap.put(FrameworkConfig.REDIS_SERVER_PORT, "6379");
        parameterMap.put(FrameworkConfig.REDIS_MAX_POOL_SIZE, "1");
        parameterMap.put(FrameworkConfig.REDIS_MIN_POOL_SIZE, "2");
        parameterMap.put("file.path", "/data/file");
        this.testHandler = new TestHandler(parameterMap);
        Session session = new SessionImpl("100001");
        this.testHandler.setSession(session);
    }

    @AfterClass
    public static void tearDownClass() {
        ApplicationContext.CONTEXT.contextDestroyed();
    }
}
