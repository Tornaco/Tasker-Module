package dev.tornaco.tasker;

import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.newstand.logger.Logger;

import java.io.IOException;

import dev.tornaco.tasker.common.Consumer;
import dev.tornaco.tasker.service.TaskerBridgeServiceProxy;

import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/5/9 17:39
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */
@RunWith(AndroidJUnit4.class)
public class Launcher {

    @Test
    public void start() throws IOException, UiObjectNotFoundException, InterruptedException {
        Logger.d("Start called!");

        TaskerBridgeServiceProxy.version(InstrumentationRegistry.getTargetContext(),
                new Consumer<String>() {
                    @Override
                    public void accept(@Nullable String s) {
                        Logger.d("TaskerBridgeService version: %s", s);
                    }
                });

        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        Logger.d("UIDevice: %s", uiDevice);

        // Test
        uiDevice.pressHome();
        // Launch contacts app.
        String kill = uiDevice.executeShellCommand("am force-stop com.android.contacts");
        String res = uiDevice.executeShellCommand("am start -n com.android.contacts/.activities.PeopleActivity");

        UiObject addButton = uiDevice.findObject(
                new UiSelector()
                        .resourceId("com.android.contacts:id/floating_action_button"));

        addButton.clickAndWaitForNewWindow();

        sleep(10 * 1000);
    }
}
