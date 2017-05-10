// ITask.aidl
package dev.tornaco.tasker.service;

import dev.tornaco.tasker.service.ITask;

interface ITaskExecutor {
    String execute(in ITask task);
    String getSerial();
}