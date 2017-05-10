package dev.tornaco.taskerapi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Nick on 2017/5/10 15:32
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

interface TaskExecutor {
    String execute(@NonNull UIDeviceDelegate delegate, @Nullable String taskData) throws Exception;
}
