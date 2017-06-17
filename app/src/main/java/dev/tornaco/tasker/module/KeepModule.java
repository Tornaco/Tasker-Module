package dev.tornaco.tasker.module;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newstand.logger.Logger;
import org.newstand.logger.Settings;

import java.io.IOException;
import java.util.List;

import dev.tornaco.lib.test.MiscUtil;

import static dev.tornaco.lib.test.MiscUtil.NEW_WINDOW_WAIT_TIMEOUT;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT_INFINITE;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT_LONG;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT_SHORT;

/**
 * Created by Nick on 2017/6/14 13:50
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class KeepModule {

    @Before
    public void setup() {
        Logger.config(Settings.builder().logLevel(Logger.LogLevel.ALL).tag("Tasker").build());
    }

    @Test
    public void getImages() throws IOException, UiObjectNotFoundException, InterruptedException {

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        MiscUtil.forceStopPkg(device, "com.gotokeep.keep");
        MiscUtil.launchPackage(device, "com.gotokeep.keep/.activity.SplashActivity");
        device.wait(Until.hasObject(By.pkg("com.gotokeep.keep")), NEW_WINDOW_WAIT_TIMEOUT);

        UiObject dongtaiTab = device.findObject(new UiSelector().className(TextView.class).text("动态"));
        dongtaiTab.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
        dongtaiTab.click();
        dongtaiTab.click();

        UiObject progress = device.findObject(new UiSelector().text("加载中"));
        if (progress.waitForExists(UI_OBJECT_WAIT_TIMEOUT_SHORT)) {
            progress.waitUntilGone(UI_OBJECT_WAIT_TIMEOUT_INFINITE);
        }

        UiObject remen = device.findObject(new UiSelector().text("热门").className(TextView.class));
        remen.waitForExists(UI_OBJECT_WAIT_TIMEOUT);
        remen.click();

        UiScrollable scrollable = new UiScrollable(new UiSelector().className(RecyclerView.class));

        while (true) {
            scrollable.waitForExists(UI_OBJECT_WAIT_TIMEOUT);
            scrollable.scrollBackward();
            saveAllInCurrentList(device);
        }
    }

    private void saveAllInCurrentList(UiDevice device) {
        List<UiObject2> images = device.findObjects(By.res("com.gotokeep.keep:id/img_content"));

        for (UiObject2 img : images) {

            img.click();

            UiObject imageView = device.findObject(new UiSelector().resourceId("com.gotokeep.keep:id/item_community_pic"));
            imageView.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);

            String path = MiscUtil.saveScreenshot(device);
            Logger.d("Saving to %s", path);

            imageView.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            device.pressBack();
        }
    }
}
