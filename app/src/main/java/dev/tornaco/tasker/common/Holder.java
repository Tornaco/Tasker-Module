package dev.tornaco.tasker.common;

/**
 * Created by Nick on 2017/5/9 17:25
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class Holder<DATA> {
    private DATA data;

    public DATA getData() {
        return data;
    }

    public void setData(DATA data) {
        this.data = data;
    }
}
