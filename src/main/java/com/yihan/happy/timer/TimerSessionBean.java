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
        parameterMap.put("password", "bigcodebang");
        String result = this.executeService(ActionNames.UPDATE_IMAGE_INFO, parameterMap);
        System.out.println(result);
    }

    @Schedule(minute = "30", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "2", dayOfWeek = "*", persistent = false)
    @Override
    public void updateDuomenPointHistory() {
        System.out.println("timer:updateDuomenPointHistory----UPDATE_DUOMEN_POINT_HISTORY:更新用户当天的多盟积分信息");
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("password", "bigcodebang");
        String result = this.executeService(ActionNames.UPDATE_DUOMEN_POINT_HISTORY, parameterMap);
        System.out.println(result);
    }
}
