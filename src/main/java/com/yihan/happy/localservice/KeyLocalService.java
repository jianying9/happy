package com.yihan.happy.localservice;

import com.wolf.framework.local.Local;

/**
 *
 * @author jianying9
 */
public interface KeyLocalService extends Local {

    public long getMaxKeyValue(String tableName);

    public long nextKeyValue(String tableName);

    public void updateMaxKeyValue(String tableName, long value);
}
