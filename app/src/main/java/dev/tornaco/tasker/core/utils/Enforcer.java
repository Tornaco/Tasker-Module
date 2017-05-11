package dev.tornaco.tasker.core.utils;

import android.text.TextUtils;

import com.chrisplus.rootmanager.RootManager;
import com.chrisplus.rootmanager.container.Result;

import org.newstand.logger.Logger;

/**
 * Created by Nick on 2017/5/9 16:51
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class Enforcer {

    public static void enforce(boolean expression, String message) {
        if (!expression) throw new IllegalArgumentException(message);
    }

    public static <T> T enforceNoNull(T t) {
        if (t == null) throw new NullPointerException();
        return t;
    }

    public static String enforceNoEmpty(String text) {
        enforceNoNull(text);
        enforce(text.length() > 0, "Text is empty.");
        return text;
    }

    public static RootManager enforceRoot() {
        enforce(RootManager.getInstance().obtainPermission(), "No root permission~");
        return RootManager.getInstance();
    }

    public static void enforceAppInstalled(String pkgName) {
        String command = String.format("pm path %s", enforceNoNull(pkgName));
        Result r = enforceRoot().runCommand(command);

        Logger.v("enforceTestAppInstalled result %s, %s, %s", r.getResult(), r.getStatusCode(), r.getMessage());

        enforce(r.getResult() && !TextUtils.isEmpty(r.getMessage()),
                String.format("App: %s not installed~", pkgName));
    }
}
