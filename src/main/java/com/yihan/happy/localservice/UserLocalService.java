package com.yihan.happy.localservice;

import com.wolf.framework.local.Local;

/**
 *
 * @author aladdin
 */
public interface UserLocalService extends Local {

    public String loginBySinaUser(String sinaId);
}
