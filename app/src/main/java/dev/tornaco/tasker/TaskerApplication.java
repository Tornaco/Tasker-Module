package dev.tornaco.tasker;

import android.app.Application;
import android.support.annotation.Nullable;

import org.newstand.logger.Logger;
import org.newstand.logger.Settings;

import dev.tornaco.tasker.common.Consumer;
import dev.tornaco.tasker.service.TaskerBridgeServiceProxy;

/**
 * Created by Nick on 2017/5/9 16:48
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class TaskerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.config(Settings.builder().tag(getClass().getSimpleName()).logLevel(Logger.LogLevel.ALL).build());

        TaskerBridgeServiceProxy.start(this);

        TaskerBridgeServiceProxy.version(this, new Consumer<String>() {
            @Override
            public void accept(@Nullable String s) {
                Logger.d("TaskerBridgeService version: %s", s);
            }
        });
    }
}
