package dev.tornaco.tasker.module;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Nick on 2017/6/13 17:14
 */

public class SettingsProvider {

    private Context context;

    public SettingsProvider(Context context) {
        this.context = context;
    }

    public void setTarget(String target) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString("target", target).apply();
    }

    public String getTarget() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString("target", "MLOV");
    }
}
