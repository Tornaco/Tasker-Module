// ITaskerBridgeService.aidl
package dev.tornaco.tasker.service;

import dev.tornaco.tasker.service.ITask;
import dev.tornaco.tasker.service.ITaskListener;

interface ITaskerBridgeService {
    String version();

    ITask nextTask();

    boolean hasNextTask();

    void setTaskListener(ITaskListener listener);
}
