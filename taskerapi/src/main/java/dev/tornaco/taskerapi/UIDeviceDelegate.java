package dev.tornaco.taskerapi;

import android.app.Instrumentation;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.EventCondition;
import android.support.test.uiautomator.InstrumentationUiAutomatorBridge;
import android.support.test.uiautomator.SearchCondition;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Nick on 2017/5/10 12:14
 * E-Mail: Tornaco@163.com
 * All right reserved.
 */

public class UIDeviceDelegate {

    private UiDevice device;

    public UIDeviceDelegate(UiDevice device) {
        this.device = device;
    }

    @Deprecated
    public void initialize(InstrumentationUiAutomatorBridge uiAutomatorBridge) {
        device.initialize(uiAutomatorBridge);
    }

    public UiObject findObject(UiSelector selector) {
        return device.findObject(selector);
    }

    public boolean hasObject(BySelector selector) {
        return device.hasObject(selector);
    }

    public UiObject2 findObject(BySelector selector) {
        return device.findObject(selector);
    }

    public List<UiObject2> findObjects(BySelector selector) {
        return device.findObjects(selector);
    }

    public <T> T wait(SearchCondition<T> condition, long timeout) {
        return device.wait(condition, timeout);
    }

    public <T> T performActionAndWait(Runnable action, EventCondition<T> condition, long timeout) {
        return device.performActionAndWait(action, condition, timeout);
    }

    public void setCompressedLayoutHeirarchy(boolean compressed) {
        device.setCompressedLayoutHeirarchy(compressed);
    }

    @Deprecated
    public static UiDevice getInstance() {
        return UiDevice.getInstance();
    }

    public static UiDevice getInstance(Instrumentation instrumentation) {
        return UiDevice.getInstance(instrumentation);
    }

    public Point getDisplaySizeDp() {
        return device.getDisplaySizeDp();
    }

    public String getProductName() {
        return device.getProductName();
    }

    public String getLastTraversedText() {
        return device.getLastTraversedText();
    }

    public void clearLastTraversedText() {
        device.clearLastTraversedText();
    }

    public boolean pressMenu() {
        return device.pressMenu();
    }

    public boolean pressBack() {
        return device.pressBack();
    }

    public boolean pressHome() {
        return device.pressHome();
    }

    public boolean pressSearch() {
        return device.pressSearch();
    }

    public boolean pressDPadCenter() {
        return device.pressDPadCenter();
    }

    public boolean pressDPadDown() {
        return device.pressDPadDown();
    }

    public boolean pressDPadUp() {
        return device.pressDPadUp();
    }

    public boolean pressDPadLeft() {
        return device.pressDPadLeft();
    }

    public boolean pressDPadRight() {
        return device.pressDPadRight();
    }

    public boolean pressDelete() {
        return device.pressDelete();
    }

    public boolean pressEnter() {
        return device.pressEnter();
    }

    public boolean pressKeyCode(int keyCode) {
        return device.pressKeyCode(keyCode);
    }

    public boolean pressKeyCode(int keyCode, int metaState) {
        return device.pressKeyCode(keyCode, metaState);
    }

    public boolean pressRecentApps() throws RemoteException {
        return device.pressRecentApps();
    }

    public boolean openNotification() {
        return device.openNotification();
    }

    public boolean openQuickSettings() {
        return device.openQuickSettings();
    }

    public int getDisplayWidth() {
        return device.getDisplayWidth();
    }

    public int getDisplayHeight() {
        return device.getDisplayHeight();
    }

    public boolean click(int x, int y) {
        return device.click(x, y);
    }

    public boolean swipe(int startX, int startY, int endX, int endY, int steps) {
        return device.swipe(startX, startY, endX, endY, steps);
    }

    public boolean drag(int startX, int startY, int endX, int endY, int steps) {
        return device.drag(startX, startY, endX, endY, steps);
    }

    public boolean swipe(Point[] segments, int segmentSteps) {
        return device.swipe(segments, segmentSteps);
    }

    public void waitForIdle() {
        device.waitForIdle();
    }

    public void waitForIdle(long timeout) {
        device.waitForIdle(timeout);
    }

    @Deprecated
    public String getCurrentActivityName() {
        return device.getCurrentActivityName();
    }

    public String getCurrentPackageName() {
        return device.getCurrentPackageName();
    }

    public void registerWatcher(String name, UiWatcher watcher) {
        device.registerWatcher(name, watcher);
    }

    public void removeWatcher(String name) {
        device.removeWatcher(name);
    }

    public void runWatchers() {
        device.runWatchers();
    }

    public void resetWatcherTriggers() {
        device.resetWatcherTriggers();
    }

    public boolean hasWatcherTriggered(String watcherName) {
        return device.hasWatcherTriggered(watcherName);
    }

    public boolean hasAnyWatcherTriggered() {
        return device.hasAnyWatcherTriggered();
    }

    public boolean isNaturalOrientation() {
        return device.isNaturalOrientation();
    }

    public int getDisplayRotation() {
        return device.getDisplayRotation();
    }

    public void freezeRotation() throws RemoteException {
        device.freezeRotation();
    }

    public void unfreezeRotation() throws RemoteException {
        device.unfreezeRotation();
    }

    public void setOrientationLeft() throws RemoteException {
        device.setOrientationLeft();
    }

    public void setOrientationRight() throws RemoteException {
        device.setOrientationRight();
    }

    public void setOrientationNatural() throws RemoteException {
        device.setOrientationNatural();
    }

    public void wakeUp() throws RemoteException {
        device.wakeUp();
    }

    public boolean isScreenOn() throws RemoteException {
        return device.isScreenOn();
    }

    public void sleep() throws RemoteException {
        device.sleep();
    }

    @Deprecated
    public void dumpWindowHierarchy(String fileName) {
        device.dumpWindowHierarchy(fileName);
    }

    public void dumpWindowHierarchy(File dest) throws IOException {
        device.dumpWindowHierarchy(dest);
    }

    public void dumpWindowHierarchy(OutputStream out) throws IOException {
        device.dumpWindowHierarchy(out);
    }

    public boolean waitForWindowUpdate(String packageName, long timeout) {
        return device.waitForWindowUpdate(packageName, timeout);
    }

    public boolean takeScreenshot(File storePath) {
        return device.takeScreenshot(storePath);
    }

    public boolean takeScreenshot(File storePath, float scale, int quality) {
        return device.takeScreenshot(storePath, scale, quality);
    }

    public String getLauncherPackageName() {
        return device.getLauncherPackageName();
    }

    public String executeShellCommand(String cmd) throws IOException {
        return device.executeShellCommand(cmd);
    }
}
