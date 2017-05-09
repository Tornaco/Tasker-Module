package dev.tornaco.tasker;

import android.support.test.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import dev.tornaco.tasker.utils.Enforcer;

/**
 * Created by Nick on 2017/5/9 16:58
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */
public class EnforcerTest {
    @Test
    public void enforceTestAppInstalled() throws Exception {
        Enforcer.enforceTestAppInstalled(InstrumentationRegistry.getTargetContext());

        try {
            Enforcer.enforceAppInstalled("com.a.b.c");
            Assert.fail();
        } catch (Throwable ignored) {

        }
    }

}