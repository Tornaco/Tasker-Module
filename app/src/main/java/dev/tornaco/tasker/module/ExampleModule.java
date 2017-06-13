package dev.tornaco.tasker.module;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/6/7 14:23
 */
@RunWith(AndroidJUnit4.class)
public class ExampleModule {

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
}
