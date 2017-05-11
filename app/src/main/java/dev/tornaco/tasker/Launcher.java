package dev.tornaco.tasker;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.newstand.logger.Logger;

import java.io.IOException;
import java.util.UUID;

import dev.tornaco.tasker.core.Tasks;
import dev.tornaco.tasker.core.UIDeviceDelegate;
import dev.tornaco.tasker.service.ITask;
import dev.tornaco.tasker.service.ITaskExecutor;
import dev.tornaco.tasker.service.TaskerBridgeServiceProxy;

import static java.lang.Thread.sleep;

/**
 * Created by Nick on 2017/5/9 17:39
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */
@RunWith(AndroidJUnit4.class)
public class Launcher {

    @Test
    public void start() throws IOException, UiObjectNotFoundException, InterruptedException, RemoteException {
        Logger.d("Start called!");

        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        final UIDeviceDelegate deviceDelegate = new UIDeviceDelegate(uiDevice);

        ITaskExecutor executor = new ITaskExecutor.Stub() {
            @Override
            public String execute(ITask task) throws RemoteException {

                Tasks t = Tasks.findByName(task.getName());

                try {
                    if (t != null) {
                        Logger.d("Now executing: %s", t);
                        return t.execute(deviceDelegate, task.getTaskBody());
                    } else {
                        Logger.w("No executor found for: %s", task);
                    }
                } catch (Exception e) {
                    Logger.e(e, "Err execute");
                }
                return null;
            }

            @Override
            public String getSerial() throws RemoteException {
                return UUID.randomUUID().toString();
            }
        };

        TaskerBridgeServiceProxy.create(InstrumentationRegistry.getTargetContext()).onExecutorCreate(executor);

        while (!TaskerBridgeServiceProxy.create(InstrumentationRegistry.getTargetContext()).shouldTerminate(executor)) {
            sleep(1000);
        }

        Logger.d("Bye");
    }
}
