package dev.tornaco.tasker.module;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newstand.logger.Logger;
import org.newstand.logger.Settings;

import java.io.IOException;

import dev.tornaco.lib.test.MiscUtil;

import static dev.tornaco.lib.test.MiscUtil.NEW_WINDOW_WAIT_TIMEOUT;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT_LONG;
import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/6/14 17:42
 */

@RunWith(AndroidJUnit4.class)
public class NetEaseModule {

    @Before
    public void setup() {
        Logger.config(Settings.builder().logLevel(Logger.LogLevel.ALL).tag("Tasker").build());
    }

    @Test
    public void addCommentsInPrivateFM() throws IOException, UiObjectNotFoundException, InterruptedException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        MiscUtil.forceStopPkg(device, "com.netease.cloudmusic");
        MiscUtil.launchPackage(device, "com.netease.cloudmusic/.activity.LoadingActivity");
        device.wait(Until.hasObject(By.pkg("com.netease.cloudmusic")), NEW_WINDOW_WAIT_TIMEOUT);

        UiObject privFM = device.findObject(new UiSelector().resourceId("com.netease.cloudmusic:id/aeu"));
        privFM.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
        privFM.clickAndWaitForNewWindow(NEW_WINDOW_WAIT_TIMEOUT);

        while (true) {
            UiObject comment = device.findObject(new UiSelector().resourceId("com.netease.cloudmusic:id/nm"));
            comment.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            comment.clickAndWaitForNewWindow(NEW_WINDOW_WAIT_TIMEOUT);

            UiObject next = device.findObject(new UiSelector().resourceId("com.netease.cloudmusic:id/nv"));

            UiObject editText = device.findObject(new UiSelector().resourceId("com.netease.cloudmusic:id/a11"));
            editText.waitForExists(UI_OBJECT_WAIT_TIMEOUT);
            editText.setText("网易云真是一个神奇的播放器，各种各样的段子，百看不腻，而且每天都有新的段子手出现，里面个个都是人才，我超喜欢的!");

            try {
                UiObject send = device.findObject(new UiSelector().resourceId("com.netease.cloudmusic:id/a0y"));
                send.waitForExists(UI_OBJECT_WAIT_TIMEOUT);
                send.click();
                sleep(1000);
            } catch (Throwable e) {
                Logger.e(e, "");
            }

            device.pressBack();
            next.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            next.click();
        }
    }
}
