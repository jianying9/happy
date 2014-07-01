package com.yihan.happy.timer;

import com.wolf.framework.timer.AbstractTimer;
import com.yihan.happy.config.ActionNames;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 *
 * @author jianying9
 */
@Stateless
@Startup
public class TimerSessionBean extends AbstractTimer implements TimerSessionBeanLocal {

    @Schedule(minute = "0,10,20,30,40,50", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*", persistent = false)
    @Override
    public void updateImage() {
        System.out.println("timer:updateImage----UPDATE_IMAGE_INFO:抓取图片");
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("page", "3");
        String result = this.executeService(ActionNames.UPDATE_IMAGE_INFO, parameterMap);
        System.out.println(result);
    }
}
