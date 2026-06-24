package com.zoontek.rnpermissions;

import android.Manifest;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseArray;
import androidx.core.app.NotificationManagerCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import io.sentry.protocol.SentryStackFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RNPermissionsModuleImpl {
    private static final String BLOCKED = "blocked";
    private static final String DENIED = "denied";
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private static final String GRANTED = "granted";
    public static final String NAME = "RNPermissions";
    private static final String UNAVAILABLE = "unavailable";
    private static int mRequestCode;

    public static Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("available", Arguments.createArray());
        return map;
    }

    private static boolean isPermissionUnavailable(String str) {
        try {
            Manifest.permission.class.getField(str.replace("android.permission.", "").replace("com.android.voicemail.permission.", ""));
            return false;
        } catch (NoSuchFieldException unused) {
            return true;
        }
    }

    private static WritableMap getLegacyNotificationsResponse(ReactApplicationContext reactApplicationContext, String str) {
        boolean zAreNotificationsEnabled = NotificationManagerCompat.from(reactApplicationContext).areNotificationsEnabled();
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        if (zAreNotificationsEnabled) {
            str = GRANTED;
        }
        writableMapCreateMap.putString("status", str);
        writableMapCreateMap.putMap("settings", writableMapCreateMap2);
        return writableMapCreateMap;
    }

    public static void openSettings(ReactApplicationContext reactApplicationContext, Promise promise) {
        try {
            Intent intent = new Intent();
            String packageName = reactApplicationContext.getPackageName();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addFlags(268435456);
            intent.setData(Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, packageName, null));
            reactApplicationContext.startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    public static void check(ReactApplicationContext reactApplicationContext, String str, Promise promise) {
        if (str == null || isPermissionUnavailable(str)) {
            promise.resolve(UNAVAILABLE);
        } else if (reactApplicationContext.getBaseContext().checkSelfPermission(str) == 0) {
            promise.resolve(GRANTED);
        } else {
            promise.resolve(DENIED);
        }
    }

    public static void checkNotifications(ReactApplicationContext reactApplicationContext, Promise promise) {
        promise.resolve(getLegacyNotificationsResponse(reactApplicationContext, DENIED));
    }

    public static void checkMultiple(ReactApplicationContext reactApplicationContext, ReadableArray readableArray, Promise promise) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Context baseContext = reactApplicationContext.getBaseContext();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (isPermissionUnavailable(string)) {
                writableNativeMap.putString(string, UNAVAILABLE);
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, GRANTED);
            } else {
                writableNativeMap.putString(string, DENIED);
            }
        }
        promise.resolve(writableNativeMap);
    }

    public static void request(ReactApplicationContext reactApplicationContext, PermissionListener permissionListener, SparseArray<Callback> sparseArray, String str, Promise promise) {
        if (str == null || isPermissionUnavailable(str)) {
            promise.resolve(UNAVAILABLE);
            return;
        }
        if (reactApplicationContext.getBaseContext().checkSelfPermission(str) == 0) {
            promise.resolve(GRANTED);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactApplicationContext);
            sparseArray.put(mRequestCode, new Callback() { // from class: com.zoontek.rnpermissions.RNPermissionsModuleImpl.1
                final /* synthetic */ String val$permission;

                AnonymousClass1(String str2) {
                    str = str2;
                }

                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    if (iArr.length > 0 && iArr[0] == 0) {
                        promise.resolve(RNPermissionsModuleImpl.GRANTED);
                    } else if (((PermissionAwareActivity) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                        promise.resolve(RNPermissionsModuleImpl.DENIED);
                    } else {
                        promise.resolve(RNPermissionsModuleImpl.BLOCKED);
                    }
                }
            });
            permissionAwareActivity.requestPermissions(new String[]{str2}, mRequestCode, permissionListener);
            mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    /* JADX INFO: renamed from: com.zoontek.rnpermissions.RNPermissionsModuleImpl$1 */
    class AnonymousClass1 implements Callback {
        final /* synthetic */ String val$permission;

        AnonymousClass1(String str2) {
            str = str2;
        }

        @Override // com.facebook.react.bridge.Callback
        public void invoke(Object... objArr) {
            int[] iArr = (int[]) objArr[0];
            if (iArr.length > 0 && iArr[0] == 0) {
                promise.resolve(RNPermissionsModuleImpl.GRANTED);
            } else if (((PermissionAwareActivity) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                promise.resolve(RNPermissionsModuleImpl.DENIED);
            } else {
                promise.resolve(RNPermissionsModuleImpl.BLOCKED);
            }
        }
    }

    public static void requestNotifications(ReactApplicationContext reactApplicationContext, Promise promise) {
        promise.resolve(getLegacyNotificationsResponse(reactApplicationContext, BLOCKED));
    }

    public static void requestMultiple(ReactApplicationContext reactApplicationContext, PermissionListener permissionListener, SparseArray<Callback> sparseArray, ReadableArray readableArray, Promise promise) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        ArrayList arrayList = new ArrayList();
        Context baseContext = reactApplicationContext.getBaseContext();
        int i = 0;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            String string = readableArray.getString(i2);
            if (isPermissionUnavailable(string)) {
                writableNativeMap.putString(string, UNAVAILABLE);
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, GRANTED);
            } else {
                arrayList.add(string);
            }
            i++;
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactApplicationContext);
            sparseArray.put(mRequestCode, new Callback() { // from class: com.zoontek.rnpermissions.RNPermissionsModuleImpl.2
                final /* synthetic */ WritableMap val$output;
                final /* synthetic */ ArrayList val$permissionsToCheck;
                final /* synthetic */ Promise val$promise;

                AnonymousClass2(ArrayList arrayList2, WritableMap writableNativeMap2, Promise promise2) {
                    arrayList = arrayList2;
                    writableMap = writableNativeMap2;
                    promise = promise2;
                }

                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    PermissionAwareActivity permissionAwareActivity2 = (PermissionAwareActivity) objArr[1];
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        String str = (String) arrayList.get(i3);
                        if (iArr.length > 0 && iArr[i3] == 0) {
                            writableMap.putString(str, RNPermissionsModuleImpl.GRANTED);
                        } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str)) {
                            writableMap.putString(str, RNPermissionsModuleImpl.DENIED);
                        } else {
                            writableMap.putString(str, RNPermissionsModuleImpl.BLOCKED);
                        }
                    }
                    promise.resolve(writableMap);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList2.toArray(new String[0]), mRequestCode, permissionListener);
            mRequestCode++;
        } catch (IllegalStateException e) {
            promise2.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    /* JADX INFO: renamed from: com.zoontek.rnpermissions.RNPermissionsModuleImpl$2 */
    class AnonymousClass2 implements Callback {
        final /* synthetic */ WritableMap val$output;
        final /* synthetic */ ArrayList val$permissionsToCheck;
        final /* synthetic */ Promise val$promise;

        AnonymousClass2(ArrayList arrayList2, WritableMap writableNativeMap2, Promise promise2) {
            arrayList = arrayList2;
            writableMap = writableNativeMap2;
            promise = promise2;
        }

        @Override // com.facebook.react.bridge.Callback
        public void invoke(Object... objArr) {
            int[] iArr = (int[]) objArr[0];
            PermissionAwareActivity permissionAwareActivity2 = (PermissionAwareActivity) objArr[1];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                String str = (String) arrayList.get(i3);
                if (iArr.length > 0 && iArr[i3] == 0) {
                    writableMap.putString(str, RNPermissionsModuleImpl.GRANTED);
                } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str)) {
                    writableMap.putString(str, RNPermissionsModuleImpl.DENIED);
                } else {
                    writableMap.putString(str, RNPermissionsModuleImpl.BLOCKED);
                }
            }
            promise.resolve(writableMap);
        }
    }

    public static void shouldShowRequestRationale(ReactApplicationContext reactApplicationContext, String str, Promise promise) {
        if (str == null) {
            promise.resolve(false);
            return;
        }
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity(reactApplicationContext).shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    private static PermissionAwareActivity getPermissionAwareActivity(ReactApplicationContext reactApplicationContext) {
        ComponentCallbacks2 currentActivity = reactApplicationContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        return (PermissionAwareActivity) currentActivity;
    }

    public static void openPhotoPicker(Promise promise) {
        promise.reject("Permissions:openPhotoPicker", "openPhotoPicker is not supported on Android");
    }

    public static void checkLocationAccuracy(Promise promise) {
        promise.reject("Permissions:checkLocationAccuracy", "checkLocationAccuracy is not supported on Android");
    }

    public static void requestLocationAccuracy(Promise promise) {
        promise.reject("Permissions:requestLocationAccuracy", "requestLocationAccuracy is not supported on Android");
    }

    public static boolean onRequestPermissionsResult(ReactApplicationContext reactApplicationContext, SparseArray<Callback> sparseArray, int i, int[] iArr) {
        try {
            sparseArray.get(i).invoke(iArr, getPermissionAwareActivity(reactApplicationContext));
            sparseArray.remove(i);
            return sparseArray.size() == 0;
        } catch (Exception e) {
            FLog.e("PermissionsModule", e, "Unexpected invocation of `onRequestPermissionsResult`", new Object[0]);
            return false;
        }
    }
}
