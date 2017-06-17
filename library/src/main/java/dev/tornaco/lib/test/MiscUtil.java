package dev.tornaco.lib.test;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.telephony.TelephonyManager;

import org.junit.Assert;
import org.newstand.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.Math.sqrt;
import static java.lang.Thread.sleep;

public abstract class MiscUtil {

    public static final long NEW_WINDOW_WAIT_TIMEOUT = 5 * 1000;
    public static final long UI_OBJECT_WAIT_TIMEOUT = 5 * 1000;
    public static final long UI_OBJECT_WAIT_TIMEOUT_LONG = 15 * 1000;
    public static final long UI_OBJECT_WAIT_TIMEOUT_INFINITE = 60 * 60 * 1000;
    public static final long UI_OBJECT_WAIT_TIMEOUT_SHORT = 1000;

    public static void log(String format, Object... args) {
        Logger.d(String.format(format, args));
    }


    public static boolean forceStopPkg(UiDevice device, String pkgName) throws IOException {
        Assert.assertNotNull(device);
        String res = device.executeShellCommand(String.format("am force-stop %s", pkgName));
        return !res.contains("Error");
    }

    public static boolean launchPackage(UiDevice device, String packageActivity) throws IOException {
        Assert.assertNotNull(device);
        String res = device.executeShellCommand("am start -n " + packageActivity);
        return !res.contains("Error");
    }

    public static void grantPermissionInPackageInstallerUI(UiDevice device, int steps) throws UiObjectNotFoundException {
        for (int i = 0; i < steps; i++) {
            UiObject grant = device.findObject(new UiSelector()
                    .resourceId("com.android.packageinstaller:id/permission_allow_button"));
            if (grant.waitForExists(UI_OBJECT_WAIT_TIMEOUT_SHORT)) {
                grant.click();
            }
        }
    }

    public static boolean isSIMInsert() {
        TelephonyManager manager = (TelephonyManager) InstrumentationRegistry
                .getTargetContext().getSystemService(Context.TELEPHONY_SERVICE);
        int state = manager.getSimState();
        return state == TelephonyManager.SIM_STATE_READY;
    }

    public static boolean isBTOn() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    public static void setBTOn(boolean on) throws InterruptedException {
        if (on) BluetoothAdapter.getDefaultAdapter().enable();
        else BluetoothAdapter.getDefaultAdapter().disable();
        sleep(3 * 1000);
    }

    public static boolean isSDCardInsert() {
        StorageManager sm = (StorageManager) InstrumentationRegistry.getTargetContext()
                .getSystemService(Context.STORAGE_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<StorageVolume> volumes = sm.getStorageVolumes();
            for (StorageVolume sv : volumes) {
                log("Removable %s", sv.isRemovable());
                if (sv.isRemovable()) return true;
            }

        }
        return false;
    }

    @Nullable
    public static String getRemovableStorageUUID() {
        StorageManager s = (StorageManager) InstrumentationRegistry.getTargetContext()
                .getSystemService(Context.STORAGE_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<StorageVolume> volumes = s.getStorageVolumes();
            for (StorageVolume sv : volumes) {
                log("Removable %s, %s", sv.isRemovable(), sv.getUuid());
                if (sv.isRemovable()) {
                    return sv.getUuid();
                }
            }
        }
        return null;
    }

    public static boolean hasGoogleAccount() {
        return hasAccount("google");
    }

    public static boolean hasAccount(String acc) {
        AccountManager accountManager = (AccountManager) InstrumentationRegistry.getTargetContext()
                .getSystemService(Context.ACCOUNT_SERVICE);
        Account[] accouns = accountManager.getAccounts();
        if (ContextCompat.checkSelfPermission(InstrumentationRegistry.getTargetContext(), Manifest.permission
                .GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            log("Permission not grant:" + Manifest.permission
                    .GET_ACCOUNTS);
            return false;
        }
        log("Current accounts: %s", Arrays.toString(accouns));
        for (Account a : accouns) {
            if (a.type.contains(acc)) return true;
        }
        return false;
    }

    private static String cropImage(Rect rect, String filePath) throws IOException {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inTempStorage = new byte[12 * 1024];
        bfOptions.inJustDecodeBounds = true;
        Bitmap m = BitmapFactory.decodeFile(filePath);
        m = Bitmap.createBitmap(m, rect.left, rect.top, rect.width(), rect.height());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        m.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        return saveImage(m);
    }

    private static String saveImage(Bitmap bitmap) throws IOException {
        String path = InstrumentationRegistry.getTargetContext().getExternalCacheDir()
                + File.separator + "CROPPED_"
                + formatDateForFileName(System.currentTimeMillis())
                + ".png";
        File file = new File(path);
        int index = 1;
        while (file.exists()) {
            path = InstrumentationRegistry.getTargetContext().getExternalCacheDir()
                    + File.separator + "CROPPED_"
                    + formatDateForFileName(System.currentTimeMillis())
                    + "-" + index
                    + ".png";
            index++;
            file = new File(path);
        }
        FileOutputStream out = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        out.close();
        return path;
    }

    public static String saveScreenshot(UiDevice device) {
        String path = InstrumentationRegistry.getTargetContext().getExternalCacheDir()
                + File.separator + "SCREENSHOT_"
                + formatDateForFileName(System.currentTimeMillis())
                + ".png";
        File file = new File(path);
        Assert.assertTrue("Fail create parent dir for tmp dir to save screenshot",
                file.getParentFile().exists() || file.getParentFile().mkdirs());
        device.takeScreenshot(file);
        return file.getPath();
    }

    public static String getImageWithUIObject(UiDevice device, UiObject uiObject)
            throws UiObjectNotFoundException, IOException {
        Assert.assertTrue(uiObject.waitForExists(UI_OBJECT_WAIT_TIMEOUT));
        Rect rect = uiObject.getBounds();
        String path = InstrumentationRegistry.getTargetContext().getExternalCacheDir()
                + File.separator + "SCREENSHOT_"
                + formatDateForFileName(System.currentTimeMillis())
                + ".png";
        File file = new File(path);
        Assert.assertTrue("Fail create parent dir for tmp dir to save screenshot",
                file.getParentFile().exists() || file.getParentFile().mkdirs());
        device.takeScreenshot(file);
        return cropImage(rect, path);
    }

    public static String formatDateForFileName(long l) {
        String time;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-HH:mm:ss");
            Date d1 = new Date(l);
            time = format.format(d1);
        } else {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-HH:mm:ss");
            Date d1 = new Date(l);
            time = format.format(d1);
        }
        return time;
    }

    /**
     * 获取指定图像的取色板。
     *
     * @param bitmap Target {@link Bitmap}
     * @return 目标图片的取色板。
     */
    public static Palette.Swatch getDomainSwatch(Bitmap bitmap) {
        return Palette.from(bitmap)
                .generate().getDominantSwatch();
    }

    /**
     * 获取两个RGB颜色差异程度
     *
     * @param rgb1 RGB to compare.
     * @param rgb2 RGB to compare with.
     * @return RGB颜色差异程度，数值越大，差异越大。
     */
    public static double getColorComp(int rgb1, int rgb2) {
        int ar = Color.red(rgb1);
        int ag = Color.green(rgb1);
        int ab = Color.blue(rgb1);

        int br = Color.red(rgb2);
        int bg = Color.green(rgb2);
        int bb = Color.blue(rgb2);

        int absR = ar - br;
        int absG = ag - bg;
        int absB = ab - bb;

        return sqrt(absR * absR + absG * absG + absB * absB);
    }
}
