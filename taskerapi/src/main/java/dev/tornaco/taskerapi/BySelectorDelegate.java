package dev.tornaco.taskerapi;

import android.support.annotation.NonNull;
import android.support.test.uiautomator.BySelector;

import com.google.gson.Gson;

import dev.tornaco.taskerapi.utils.Enforcer;

/**
 * Created by Nick on 2017/5/10 12:37
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class BySelectorDelegate {

    public static String toJson(@NonNull BySelector selector) {
        return new Gson().toJson(Enforcer.enforceNoNull(selector));
    }

    public static BySelector fromJson(@NonNull String json) {
        return new Gson().fromJson(Enforcer.enforceNoEmpty(json), BySelector.class);
    }
}
