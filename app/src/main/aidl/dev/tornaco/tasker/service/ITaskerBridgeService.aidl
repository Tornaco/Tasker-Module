// ITaskerBridgeService.aidl
package dev.tornaco.tasker.service;

import dev.tornaco.tasker.service.ITaskExecutor;

interface ITaskerBridgeService {
    void onExecutorCreate(in ITaskExecutor executor);

    boolean shouldTerminate(in ITaskExecutor executor);
}
