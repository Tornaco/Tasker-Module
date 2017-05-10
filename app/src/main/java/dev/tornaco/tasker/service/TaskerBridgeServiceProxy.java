package dev.tornaco.tasker.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import java.util.concurrent.CountDownLatch;

import dev.tornaco.tasker.common.Consumer;
import dev.tornaco.tasker.common.Holder;

/**
 * Created by Nick on 2017/5/9 17:23
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class TaskerBridgeServiceProxy extends ServiceProxy {

    private ITaskerBridgeService mService;

    private TaskerBridgeServiceProxy(Context context, Intent intent) {
        super(context, intent);
    }

    private static TaskerBridgeServiceProxy create(Context c) {
        Intent i = new Intent("dev.tornaco.tasker.ACTION_START_SERVICE");
        i.setClassName("dev.tornaco.tasker.app", "dev.tornaco.tasker.app.service.TaskerBridgeService");
        return new TaskerBridgeServiceProxy(c, i);
    }

    @WorkerThread
    public static String version(Context context) {
        try {
            final Holder<String> stringHolder = new Holder<>();
            final CountDownLatch latch = new CountDownLatch(1);
            create(context).version(new Consumer<String>() {
                @Override
                public void accept(@Nullable String s) {
                    stringHolder.setData(s);
                    latch.countDown();
                }
            });
            latch.await();
            return stringHolder.getData();
        } catch (RemoteException ignored) {
            return null;
        } catch (InterruptedException e) {
            return null;
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

    public static ITask nextTask(Context context) {
        try {
            final Holder<ITask> taskHolder = new Holder<>();
            final CountDownLatch latch = new CountDownLatch(1);
            create(context).nextTask(new Consumer<ITask>() {
                @Override
                public void accept(@Nullable ITask iTask) {
                    taskHolder.setData(iTask);
                    latch.countDown();
                }
            });
            latch.await();
            return taskHolder.getData();
        } catch (RemoteException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }

    public void nextTask(final Consumer<ITask> taskConsumer) throws RemoteException {
        setTask(new ProxyTask() {
            @Override
            public void run() throws RemoteException {
                ITask next = mService.nextTask();
                taskConsumer.accept(next);
            }
        });
    }

    public static boolean hasNext(Context context) {
        try {
            final Holder<Boolean> holder = new Holder<>();
            final CountDownLatch latch = new CountDownLatch(1);
            create(context).hasNextTask(new Consumer<Boolean>() {
                @Override
                public void accept(@Nullable Boolean aBoolean) {
                    holder.setData(aBoolean);
                    latch.countDown();
                }
            });
            latch.await();
            return holder.getData();
        } catch (RemoteException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void hasNextTask(final Consumer<Boolean> booleanConsumer) throws RemoteException {
        setTask(new ProxyTask() {
            @Override
            public void run() throws RemoteException {
                booleanConsumer.accept(mService.hasNextTask());
            }
        });
    }

    public static void setTaskListener(Context context, ITaskListener listener) {
        try {
            create(context).setTaskListener(listener);
        } catch (RemoteException ignored) {

        }
    }

    public void setTaskListener(final ITaskListener listener) throws RemoteException {
        setTask(new ProxyTask() {
            @Override
            public void run() throws RemoteException {
                mService.setTaskListener(listener);
            }
        });
    }
}
