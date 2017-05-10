// TaskListener.aidl
package dev.tornaco.tasker.service;

import dev.tornaco.tasker.service.ITask;

interface ITaskListener {
    void onTaskStart(in ITask task);
    void onTaskFail(in ITask task, String err);
    void onTaskSuccess(in ITask task);
    void onTaskComplete(in ITask task);
}
