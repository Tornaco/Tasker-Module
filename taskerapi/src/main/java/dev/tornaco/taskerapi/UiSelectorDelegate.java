package dev.tornaco.taskerapi;

import android.support.annotation.NonNull;
import android.support.test.uiautomator.UiSelector;

import com.google.gson.Gson;

import dev.tornaco.taskerapi.utils.Enforcer;

/**
 * Created by Nick on 2017/5/10 12:37
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class UiSelectorDelegate {

    public static String toJson(@NonNull UiSelector selector) {
        return new Gson().toJson(Enforcer.enforceNoNull(selector));
    }

    public static UiSelector fromJson(@NonNull String json) {
        return new Gson().fromJson(Enforcer.enforceNoEmpty(json), UiSelector.class);
    }
}
