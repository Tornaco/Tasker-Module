package dev.tornaco.tasker.module;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Test;
import org.junit.runner.RunWith;

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
}
