package dev.tornaco.tasker;

import android.content.Context;

import dev.tornaco.tasker.utils.Enforcer;

/**
 * Created by Nick on 2017/5/9 17:39
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class Launcher {

    public static void launch(Context context) {
        Enforcer.enforceTestAppInstalled(context);
        String cmd = launcherCommand(context);
        Enforcer.enforceRoot().runCommand(cmd);
    }

    private static String launcherCommand(Context context) {
        return testsCmd("dev.tornaco.tasker.core.TaskExecutor", "execute", context.getPackageName() + ".test");
    }

    private static String testsCmd(String clz, String method, String testPkg) {
        return String.format("am instrument -w -r -e debug false -e class %s#%s %s/android.support.test.runner.AndroidJUnitRunner", clz, method, testPkg);
    }
}
