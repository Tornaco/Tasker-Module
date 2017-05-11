package dev.tornaco.tasker.core.data;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import dev.tornaco.tasker.core.utils.Enforcer;

/**
 * Created by Nick on 2017/5/10 16:08
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class PressKeyCodeParam {

    private int keyCode;
    private int metaState;

    public PressKeyCodeParam(int keyCode, int metaState) {
        this.keyCode = keyCode;
        this.metaState = metaState;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getMetaState() {
        return metaState;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static PressKeyCodeParam fromJson(@NonNull String json) {
        return new Gson().fromJson(Enforcer.enforceNoEmpty(json), PressKeyCodeParam.class);
    }
}
