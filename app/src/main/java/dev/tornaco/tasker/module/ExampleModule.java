package dev.tornaco.tasker.module;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import dev.tornaco.lib.test.MiscUtil;

import static dev.tornaco.lib.test.MiscUtil.NEW_WINDOW_WAIT_TIMEOUT;
import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/6/7 14:23
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleModule {

    private void showToast(final String message) {
        MainThreadHandler.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InstrumentationRegistry.getTargetContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showToast(final String format, Object... args) {
        showToast(String.format(format, args));
    }

    @Before
    public void setup() {
        showToast("@Before");
    }

    @After
    public void teardown() throws InterruptedException {
        showToast("@After");
        // Wait for dismiss.
        sleep(3 * 1000);
    }

    @Test
    public void pressHome() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();
    }

    @Test
    public void pressBack() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressBack();
    }

    @Test
    public void openNotification() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.openNotification();
    }

    @Test
    public void getProductName() throws InterruptedException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        final String name = device.getProductName();
        MainThreadHandler.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InstrumentationRegistry.getTargetContext(), name, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Test
    public void showToast() throws InterruptedException {
        showToast("Hello world!");
    }

    @Test
    public void launch3rdApp() {
        // Launch 3-rd app.
        PackageManager pm = InstrumentationRegistry.getTargetContext().getPackageManager();
        List<PackageInfo> packageInfos;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            packageInfos = pm.getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);
        } else {
            packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        }
        for (PackageInfo packageInfo : packageInfos) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
            }

            Intent intent = pm.getLaunchIntentForPackage(packageInfo.packageName);
            if (intent == null) continue;
            InstrumentationRegistry.getTargetContext().startActivity(intent);
        }
    }

    @Test
    public void quanMinKTVComments() throws IOException, UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        MiscUtil.forceStopPkg(device, "com.tencent.karaoke");
        MiscUtil.launchPackage(device, "com.tencent.karaoke/.module.splash.ui.SplashBaseActivity");
        device.wait(Until.hasObject(By.pkg("com.tencent.karaoke")), NEW_WINDOW_WAIT_TIMEOUT);

        UiObject myTab = device.findObject(new UiSelector().resourceId("com.tencent.karaoke:id/aoe"));
        myTab.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT_LONG);
        showToast("Click my-tab:%s", myTab.click());

        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.tencent.karaoke:id/at2"));
        scrollable.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT_LONG);
        showToast("Scroll up:%s", scrollable.scrollForward());

        UiObject item = device.findObject(new UiSelector().resourceId("com.tencent.karaoke:id/st"));
        item.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT);
        showToast("Click item:%s", item.clickAndWaitForNewWindow());

        UiObject comment = device.findObject(new UiSelector().resourceId("com.tencent.karaoke:id/a16"));
        comment.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT);
        comment.clickAndWaitForNewWindow();

        UiObject editText = device.findObject(new UiSelector().resourceId("com.tencent.karaoke:id/kt"));
        editText.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT);
        editText.setText("This is a test, auto generated by Tasker-App@"
                + MiscUtil.formatDateForFileName(System.currentTimeMillis()));

        UiObject send = device.findObject(new UiSelector().resourceId("com.tencent.karaoke:id/kw"));
        send.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT);
        send.click();

        showToast("One cycle complete");
    }

    @Test
    public void quanMinKTVComments50() throws IOException, UiObjectNotFoundException, InterruptedException {
        for (int i = 0; i < 50; i++) {
            quanMinKTVComments();
            sleep(3 * 1000);
        }
    }

    @Test
    public void sendQQMessage() throws IOException, UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        MiscUtil.forceStopPkg(device, "com.tencent.mobileqq");
        MiscUtil.launchPackage(device, "com.tencent.mobileqq/.activity.SplashActivity");
        device.wait(Until.hasObject(By.pkg("com.tencent.mobileqq")), NEW_WINDOW_WAIT_TIMEOUT);

        UiObject searchView = device.findObject(new UiSelector().resourceId("com.tencent.mobileqq:id/et_search_keyword"));
        searchView.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT_LONG);
        showToast("Click search:%s", searchView.click());
        String target = new SettingsProvider(InstrumentationRegistry.getTargetContext()).getTarget();
        showToast("Target:%s", target);
        showToast("Set text:%s", searchView.setText(target));

        UiObject item = device.findObject(new UiSelector().resourceId("com.tencent.mobileqq:id/image"));
        showToast("Item exists:%s", item.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT));
        showToast("Click item:%s", item.click());

        UiObject input = device.findObject(new UiSelector().resourceId("com.tencent.mobileqq:id/input"));
        input.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT);
        input.setText("Hi, This is a test, you will receive this message in about every 10s ---auto generated by Tasker-App@"
                + MiscUtil.formatDateForFileName(System.currentTimeMillis()));

        UiObject send = device.findObject(new UiSelector().resourceId("com.tencent.mobileqq:id/fun_btn"));
        send.waitForExists(MiscUtil.UI_OBJECT_WAIT_TIMEOUT);
        showToast("Send:%s", send.click());
    }

    @Test
    public void sendQQMessage50() throws IOException, UiObjectNotFoundException, InterruptedException {
        for (int i = 0; i < 50; i++) {
            sendQQMessage();
            sleep(3 * 1000);
        }
    }

    @Test
    public void badBehaviour() {
        throw new IllegalStateException("Bad behaviour");
    }
}
