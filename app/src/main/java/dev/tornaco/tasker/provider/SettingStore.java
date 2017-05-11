package dev.tornaco.tasker.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Observable;

import dev.tornaco.tasker.core.utils.Enforcer;

/**
 * Created by Nick on 2017/5/9 17:09
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class SettingStore extends Observable implements Settings {

    private String mName;

    public SettingStore(String name) {
        this.mName = Enforcer.enforceNoNull(name);
    }

    @Nullable
    @Override
    public String read(@NonNull Context context, @NonNull String key) {
        SharedPreferences s = Enforcer.enforceNoNull(context)
                .getSharedPreferences(mName, Context.MODE_APPEND);
        return s.getString(key, null);
    }

    @Override
    public void write(@NonNull Context context, @NonNull String key, @NonNull String value) {
        SharedPreferences s = Enforcer.enforceNoNull(context)
                .getSharedPreferences(mName, Context.MODE_APPEND);
        s.edit().putString(key, value).apply();
    }
}
