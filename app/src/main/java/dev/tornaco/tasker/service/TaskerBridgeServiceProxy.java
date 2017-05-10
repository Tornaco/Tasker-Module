package dev.tornaco.tasker.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;

import dev.tornaco.tasker.common.Holder;

/**
 * Created by Nick on 2017/5/9 17:23
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class TaskerBridgeServiceProxy extends ServiceProxy implements ITaskerBridgeService {

    private ITaskerBridgeService mService;

    private TaskerBridgeServiceProxy(Context context, Intent intent) {
        super(context, intent);
    }

    public static TaskerBridgeServiceProxy create(Context c) {
        Intent i = new Intent("dev.tornaco.tasker.ACTION_START_SERVICE");
        i.setClassName("dev.tornaco.tasker.app", "dev.tornaco.tasker.app.service.TaskerBridgeService");
        return new TaskerBridgeServiceProxy(c, i);
    }

    @Override
    public void onConnected(IBinder binder) {
        mService = ITaskerBridgeService.Stub.asInterface(binder);
    }

    @Override
    public void onExecutorCreate(final ITaskExecutor executor) throws RemoteException {
        setTask(new ProxyTask() {
            @Override
            public void run() throws RemoteException {
                mService.onExecutorCreate(executor);
            }
        });
    }

    @Override
    public boolean shouldTerminate(final ITaskExecutor executor) throws RemoteException {
        final Holder<Boolean> booleanHolder = new Holder<>();
        final CountDownLatch latch = new CountDownLatch(1);
        setTask(new ProxyTask() {
            @Override
            public void run() throws RemoteException {
                booleanHolder.setData(mService.shouldTerminate(executor));
                latch.countDown();
            }
        });
        try {
            latch.await();
            return booleanHolder.getData();
        } catch (InterruptedException e) {
            return true;
        }
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
