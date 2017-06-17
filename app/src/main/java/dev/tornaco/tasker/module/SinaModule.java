package dev.tornaco.tasker.module;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import dev.tornaco.lib.test.MiscUtil;

import static dev.tornaco.lib.test.MiscUtil.NEW_WINDOW_WAIT_TIMEOUT;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT;
import static dev.tornaco.lib.test.MiscUtil.UI_OBJECT_WAIT_TIMEOUT_LONG;

/**
 * Created by Nick on 2017/6/14 11:00
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SinaModule {

    @Test
    public void redirectReMen() throws IOException, UiObjectNotFoundException {
        redirect("热门");
    }

    @Test
    public void redirectBangDan() throws IOException, UiObjectNotFoundException {
        redirect("榜单");
    }

    @Test
    public void redirectShiPin() throws IOException, UiObjectNotFoundException {
        redirect("视频");
    }

    @Test
    public void redirectTouTiao() throws IOException, UiObjectNotFoundException {
        redirect("头条");
    }

    private void redirect(String tab) throws IOException, UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        MiscUtil.forceStopPkg(device, "com.sina.weibo");
        MiscUtil.launchPackage(device, "com.sina.weibo/.SplashActivity");
        device.wait(Until.hasObject(By.pkg("com.sina.weibo")), NEW_WINDOW_WAIT_TIMEOUT);

        UiObject discover = device.findObject(new UiSelector().index(3).descriptionContains("发现"));
        discover.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
        discover.click();
        discover.click();

        UiObject bangdan = device.findObject(new UiSelector().className(TextView.class).text(tab));
        bangdan.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
        bangdan.click();
        bangdan.click();

        UiScrollable listView = new UiScrollable(new UiSelector().resourceId("com.sina.weibo:id/lv_content"));
        listView.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);

        for (int i = 0; i < 100; i++) {
            UiObject redirect = device.findObject(new UiSelector().resourceId("com.sina.weibo:id/tweet_redirect"));
            redirect.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            redirect.clickAndWaitForNewWindow(NEW_WINDOW_WAIT_TIMEOUT);

            UiObject editView = device.findObject(new UiSelector().resourceId("com.sina.weibo:id/edit_view"));
            editView.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            editView.setText("Auto redirected by TORNA@" + MiscUtil.formatDateForFileName(System.currentTimeMillis()));

            UiObject checkbox = device.findObject(new UiSelector().resourceId("com.sina.weibo:id/checkbox"));
            checkbox.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            checkbox.click();

            UiObject save = device.findObject(new UiSelector().resourceId("com.sina.weibo:id/titleSave"));
            save.waitForExists(UI_OBJECT_WAIT_TIMEOUT);
            save.click();

            listView.waitForExists(UI_OBJECT_WAIT_TIMEOUT_LONG);
            listView.scrollForward();
        }
    }
}
