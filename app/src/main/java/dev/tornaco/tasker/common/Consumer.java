package dev.tornaco.tasker.common;

import android.support.annotation.Nullable;

/**
 * Created by Nick on 2017/5/9 17:32
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public interface Consumer<T> {
    void accept(@Nullable T t);
}
