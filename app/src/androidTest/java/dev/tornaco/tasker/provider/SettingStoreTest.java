package dev.tornaco.tasker.provider;

import android.support.test.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Nick on 2017/5/9 17:16
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */
@SuppressWarnings("ConstantConditions")
public class SettingStoreTest {
    @Test
    public void read() throws Exception {
        SettingStore provider = new SettingStore("test_1");
        provider.write(InstrumentationRegistry.getTargetContext(), "key_1", "value_1");
        Assert.assertTrue(provider.read(InstrumentationRegistry.getTargetContext(), "key_1").equals("value_1"));
    }
}