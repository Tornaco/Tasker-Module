package dev.tornaco.tasker.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.WorkerThread;

import dev.tornaco.tasker.common.Consumer;

/**
 * Created by Nick on 2017/5/9 17:23
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class TaskerBridgeServiceProxy extends ServiceProxy {

    private ITaskerBridgeService mService;

    private TaskerBridgeServiceProxy(Context context) {
        super(context, new Intent(context, TaskerBridgeService.class));
    }

    public static void start(Context context) {
        context.startService(new Intent(context, TaskerBridgeService.class));
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, TaskerBridgeService.class));
    }

    @WorkerThread
    public static void version(Context context, Consumer<String> stringConsumer) {
        try {
            new TaskerBridgeServiceProxy(context).version(stringConsumer);
        } catch (RemoteException ignored) {
            stringConsumer.accept(null);
        }
    }

    @WorkerThread
    public void version(final Consumer<String> consumer) throws RemoteException {


        setTask(new ProxyTask() {
            @Override
            public void run() throws RemoteException {
                String version = mService.version();
                consumer.accept(version);
            }
        });
    }


    @Override
    public void onConnected(IBinder binder) {
        mService = ITaskerBridgeService.Stub.asInterface(binder);
    }
}
