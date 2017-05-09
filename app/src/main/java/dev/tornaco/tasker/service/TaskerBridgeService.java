package dev.tornaco.tasker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import dev.tornaco.tasker.provider.Settings;
import dev.tornaco.tasker.provider.SettingStore;

/**
 * Created by Nick on 2017/5/9 17:07
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class TaskerBridgeService extends Service implements ITaskerBridgeService {

    private Settings mSettings;

    @Override
    public void onCreate() {
        super.onCreate();
        mSettings = new SettingStore("TaskerBridgeService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return asBinder();
    }

    @Override
    public String version() throws RemoteException {
        return "1.0.0-ALPHA";
    }

    @Override
    public IBinder asBinder() {
        return new Bridge();
    }

    private class Bridge extends ITaskerBridgeService.Stub {

        @Override
        public String version() throws RemoteException {
            return TaskerBridgeService.this.version();
        }
    }
}
