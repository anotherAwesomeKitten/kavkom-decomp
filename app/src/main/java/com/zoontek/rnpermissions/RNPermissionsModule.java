package com.zoontek.rnpermissions;

import android.util.SparseArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionListener;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@ReactModule(name = "RNPermissions")
public class RNPermissionsModule extends NativeRNPermissionsSpec implements PermissionListener {
    private final SparseArray<Callback> mCallbacks;

    public RNPermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCallbacks = new SparseArray<>();
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNPermissions";
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    protected Map<String, Object> getTypedExportedConstants() {
        return RNPermissionsModuleImpl.getConstants();
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void openSettings(Promise promise) {
        RNPermissionsModuleImpl.openSettings(getReactApplicationContext(), promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void check(String str, Promise promise) {
        RNPermissionsModuleImpl.check(getReactApplicationContext(), str, promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void checkNotifications(Promise promise) {
        RNPermissionsModuleImpl.checkNotifications(getReactApplicationContext(), promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void checkMultiple(ReadableArray readableArray, Promise promise) {
        RNPermissionsModuleImpl.checkMultiple(getReactApplicationContext(), readableArray, promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void request(String str, Promise promise) {
        RNPermissionsModuleImpl.request(getReactApplicationContext(), this, this.mCallbacks, str, promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void requestNotifications(ReadableArray readableArray, Promise promise) {
        RNPermissionsModuleImpl.requestNotifications(getReactApplicationContext(), promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void requestMultiple(ReadableArray readableArray, Promise promise) {
        RNPermissionsModuleImpl.requestMultiple(getReactApplicationContext(), this, this.mCallbacks, readableArray, promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void shouldShowRequestRationale(String str, Promise promise) {
        RNPermissionsModuleImpl.shouldShowRequestRationale(getReactApplicationContext(), str, promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void checkLocationAccuracy(Promise promise) {
        RNPermissionsModuleImpl.checkLocationAccuracy(promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void requestLocationAccuracy(String str, Promise promise) {
        RNPermissionsModuleImpl.requestLocationAccuracy(promise);
    }

    @Override // com.zoontek.rnpermissions.NativeRNPermissionsSpec
    public void openPhotoPicker(Promise promise) {
        RNPermissionsModuleImpl.openPhotoPicker(promise);
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        return RNPermissionsModuleImpl.onRequestPermissionsResult(getReactApplicationContext(), this.mCallbacks, i, iArr);
    }
}
