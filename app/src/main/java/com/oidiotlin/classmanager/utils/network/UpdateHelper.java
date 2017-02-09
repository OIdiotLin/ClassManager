package com.oidiotlin.classmanager.utils.network;

import android.content.Context;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public class UpdateHelper {
    private AppInfo localInfo;
    private Boolean appNeedUpdate;
    private Boolean databaseNeedUpdate;
    private Context context;

    public UpdateHelper(AppInfo localInfo,
                        Boolean appNeedUpdate, Boolean databaseNeedUpdate,
                        Context context) {
        this.localInfo = localInfo;
        this.appNeedUpdate = appNeedUpdate;
        this.databaseNeedUpdate = databaseNeedUpdate;
        this.context = context;
    }


}
