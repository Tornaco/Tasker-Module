package dev.tornaco.tasker.module;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by Nick on 2017/6/7 14:36
 */

public class MainThreadHandler {

    static Handler h;

    static synchronized Handler getMainThreadHandler() {
        if (h == null) {
            HandlerThread ht = new HandlerThread("MainThreadHandler");
            ht.start();
            h = new Handler(ht.getLooper());
        }
        return h;
    }
}
