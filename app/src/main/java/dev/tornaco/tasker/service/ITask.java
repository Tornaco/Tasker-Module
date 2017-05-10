package dev.tornaco.tasker.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nick on 2017/5/10 13:37
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class ITask implements Parcelable {

    private String name;
    private String taskId;
    private String taskBody;

    public ITask() {
    }

    public ITask(String name, String taskId, String taskBody) {
        this.name = name;
        this.taskId = taskId;
        this.taskBody = taskBody;
    }

    protected ITask(Parcel in) {
        name = in.readString();
        taskId = in.readString();
        taskBody = in.readString();
    }

    public static final Creator<ITask> CREATOR = new Creator<ITask>() {
        @Override
        public ITask createFromParcel(Parcel in) {
            return new ITask(in);
        }

        @Override
        public ITask[] newArray(int size) {
            return new ITask[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskBody() {
        return taskBody;
    }

    public void setTaskBody(String taskBody) {
        this.taskBody = taskBody;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(taskId);
        dest.writeString(taskBody);
    }

    @Override
    public String toString() {
        return "ITask{" +
                "name='" + name + '\'' +
                ", taskId='" + taskId + '\'' +
                ", taskBody='" + taskBody + '\'' +
                '}';
    }
}
