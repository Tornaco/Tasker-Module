package dev.tornaco.taskerapi;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dev.tornaco.taskerapi.data.PressKeyCodeParam;
import dev.tornaco.taskerapi.utils.Enforcer;

/**
 * Created by Nick on 2017/5/10 15:30
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public enum Tasks implements TaskExecutor {

    pressMenu {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate,
                              @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressMenu());
        }
    },

    pressBack {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressBack());
        }
    },

    pressHome {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressHome());
        }
    },

    pressSearch {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressSearch());
        }
    },

    pressDPadCenter {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressDPadCenter());
        }
    },

    pressDPadDown {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressDPadDown());
        }
    },

    pressDPadUp {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressDPadUp());
        }
    },

    pressDPadLeft {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressDPadLeft());
        }
    },

    pressDPadRight {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressDPadRight());
        }
    },

    pressDelete {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressDelete());
        }
    },

    pressEnter {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressEnter());
        }
    },

    pressKeyCode {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) {
            PressKeyCodeParam pressKeyCodeParam = PressKeyCodeParam.fromJson(Enforcer.enforceNoEmpty(taskData));
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressKeyCode(pressKeyCodeParam.getKeyCode(), pressKeyCodeParam.getMetaState()));
        }
    },

    pressRecentApps {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) throws RemoteException {
            return String.valueOf(Enforcer.enforceNoNull(delegate).pressRecentApps());
        }
    },

    openNotification {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) throws Exception {
            return String.valueOf(Enforcer.enforceNoNull(delegate).openNotification());
        }
    },

    openQuickSettings {
        @Override
        public String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) throws Exception {
            return String.valueOf(Enforcer.enforceNoNull(delegate).openQuickSettings());
        }
    },

    // Block.
    ;


    public static
    @Nullable
    Tasks findByName(@NonNull String name) {
        for (Tasks t : values()) {
            if (t.name().equals(Enforcer.enforceNoEmpty(name))) {
                return t;
            }
        }
        return null;
    }
}
