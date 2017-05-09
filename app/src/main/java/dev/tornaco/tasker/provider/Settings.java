package dev.tornaco.tasker.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Nick on 2017/5/9 17:09
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public interface Settings {
    @Nullable
    String read(@NonNull Context context, @NonNull String key);

    void write(@NonNull Context context, @NonNull String key, @NonNull String value);
}
