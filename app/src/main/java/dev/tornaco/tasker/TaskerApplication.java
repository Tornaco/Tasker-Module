package dev.tornaco.tasker;

import android.app.Application;

import org.newstand.logger.Logger;
import org.newstand.logger.Settings;

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
    }
}
