package dev.tornaco.tasker.module;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/6/7 14:23
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleModule {

    @Before
    public void setup() {
        MainThreadHandler.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InstrumentationRegistry.getTargetContext(), "@Before", Toast.LENGTH_LONG).show();
            }
        });
    }

    @After
    public void teardown() {
        MainThreadHandler.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InstrumentationRegistry.getTargetContext(), "@After", Toast.LENGTH_LONG).show();
            }
        });
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

        // Wait for dismiss.
        sleep(3 * 1000);
    }

    @Test
    public void showToast() throws InterruptedException {
        MainThreadHandler.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InstrumentationRegistry.getTargetContext(), "Hello world!", Toast.LENGTH_LONG).show();
            }
        });

        // Wait for dismiss.
        sleep(3 * 1000);
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
}
