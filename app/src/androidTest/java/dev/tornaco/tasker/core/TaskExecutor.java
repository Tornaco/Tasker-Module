package dev.tornaco.tasker.core;

import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.newstand.logger.Logger;

import dev.tornaco.tasker.common.Consumer;
import dev.tornaco.tasker.provider.SettingStore;
import dev.tornaco.tasker.service.TaskerBridgeServiceProxy;

import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/5/9 17:21
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */
@RunWith(AndroidJUnit4.class)
public class TaskExecutor {

    @Test
    public void execute() throws InterruptedException {

        final SettingStore settingStore = new SettingStore(getClass().getSimpleName());

        TaskerBridgeServiceProxy.version(InstrumentationRegistry.getTargetContext(), new Consumer<String>() {
            @Override
            public void accept(@Nullable String s) {
                Logger.w("Version@Test %s", s);
                settingStore.write(InstrumentationRegistry.getTargetContext(), "service_version", s != null ? s : "NULL");
            }
        });

        // Blocked.
        sleep(100 * 1000);
    }
}
