package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.views.view.WindowUtilKt;
import com.facebook.systrace.Systrace;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReactActivityDelegate {
    private final Activity mActivity;
    private final String mMainComponentName;
    private PermissionListener mPermissionListener;
    private Callback mPermissionsCallback;
    private ReactDelegate mReactDelegate;

    protected ReactRootView createRootView() {
        return null;
    }

    protected Bundle getLaunchOptions() {
        return null;
    }

    protected boolean isWideColorGamutEnabled() {
        return false;
    }

    @Deprecated
    public ReactActivityDelegate(Activity activity, String str) {
        this.mActivity = activity;
        this.mMainComponentName = str;
    }

    public ReactActivityDelegate(ReactActivity reactActivity, String str) {
        this.mActivity = reactActivity;
        this.mMainComponentName = str;
    }

    protected Bundle composeLaunchOptions() {
        return getLaunchOptions();
    }

    @Deprecated
    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactNativeHost();
    }

    public ReactHost getReactHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactHost();
    }

    protected ReactDelegate getReactDelegate() {
        return this.mReactDelegate;
    }

    @Deprecated
    public ReactInstanceManager getReactInstanceManager() {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).getReactInstanceManager();
    }

    public String getMainComponentName() {
        return this.mMainComponentName;
    }

    public void onCreate(Bundle bundle) {
        Systrace.traceSection(0L, "ReactActivityDelegate.onCreate::init", new Runnable() { // from class: com.facebook.react.ReactActivityDelegate$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreate$0();
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0() {
        Window window;
        String mainComponentName = getMainComponentName();
        Bundle bundleComposeLaunchOptions = composeLaunchOptions();
        Activity activity = this.mActivity;
        if (activity != null && (window = activity.getWindow()) != null) {
            if (WindowUtilKt.isEdgeToEdgeFeatureFlagOn()) {
                WindowUtilKt.enableEdgeToEdge(window);
            }
            if (Build.VERSION.SDK_INT >= 26 && isWideColorGamutEnabled()) {
                window.setColorMode(1);
            }
        }
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
            this.mReactDelegate = new ReactDelegate(getPlainActivity(), getReactHost(), mainComponentName, bundleComposeLaunchOptions);
        } else {
            this.mReactDelegate = new ReactDelegate(getPlainActivity(), getReactNativeHost(), mainComponentName, bundleComposeLaunchOptions, getFabricEnabled()) { // from class: com.facebook.react.ReactActivityDelegate.1
                AnonymousClass1(Activity activity2, ReactNativeHost reactNativeHost, String mainComponentName2, Bundle bundleComposeLaunchOptions2, boolean z) {
                    super(activity2, reactNativeHost, mainComponentName2, bundleComposeLaunchOptions2, z);
                }

                @Override // com.facebook.react.ReactDelegate
                protected ReactRootView createRootView() {
                    ReactRootView reactRootViewCreateRootView = ReactActivityDelegate.this.createRootView();
                    return reactRootViewCreateRootView == null ? super.createRootView() : reactRootViewCreateRootView;
                }
            };
        }
        if (mainComponentName2 != null) {
            loadApp(mainComponentName2);
        }
    }

    /* JADX INFO: renamed from: com.facebook.react.ReactActivityDelegate$1 */
    class AnonymousClass1 extends ReactDelegate {
        AnonymousClass1(Activity activity2, ReactNativeHost reactNativeHost, String mainComponentName2, Bundle bundleComposeLaunchOptions2, boolean z) {
            super(activity2, reactNativeHost, mainComponentName2, bundleComposeLaunchOptions2, z);
        }

        @Override // com.facebook.react.ReactDelegate
        protected ReactRootView createRootView() {
            ReactRootView reactRootViewCreateRootView = ReactActivityDelegate.this.createRootView();
            return reactRootViewCreateRootView == null ? super.createRootView() : reactRootViewCreateRootView;
        }
    }

    protected void loadApp(String str) {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).loadApp((String) Objects.requireNonNull(str));
        getPlainActivity().setContentView(this.mReactDelegate.getReactRootView());
    }

    public void setReactSurface(ReactSurface reactSurface) {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).setReactSurface(reactSurface);
    }

    public void setReactRootView(ReactRootView reactRootView) {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).setReactRootView(reactRootView);
    }

    public void onUserLeaveHint() {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onUserLeaveHint();
    }

    public void onPause() {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onHostPause();
    }

    public void onResume() {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onHostResume();
        Callback callback = this.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    public void onDestroy() {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onHostDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onActivityResult(i, i2, intent, true);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).shouldShowDevMenuOrReload(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onKeyLongPress(i);
    }

    public boolean onBackPressed() {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onBackPressed();
    }

    public boolean onNewIntent(Intent intent) {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onNewIntent((Intent) Objects.requireNonNull(intent));
    }

    public void onWindowFocusChanged(boolean z) {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onWindowFocusChanged(z);
    }

    public void onConfigurationChanged(Configuration configuration) {
        ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).onConfigurationChanged(configuration);
    }

    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        getPlainActivity().requestPermissions(strArr, i);
    }

    public void onRequestPermissionsResult(final int i, final String[] strArr, final int[] iArr) {
        LifecycleState lifecycleState;
        Callback callback = new Callback() { // from class: com.facebook.react.ReactActivityDelegate$$ExternalSyntheticLambda1
            @Override // com.facebook.react.bridge.Callback
            public final void invoke(Object[] objArr) {
                this.f$0.lambda$onRequestPermissionsResult$1(i, strArr, iArr, objArr);
            }
        };
        if (getFabricEnabled()) {
            ReactHost reactHost = getReactHost();
            lifecycleState = reactHost != null ? reactHost.getLifecycleState() : LifecycleState.BEFORE_CREATE;
        } else {
            ReactNativeHost reactNativeHost = getReactNativeHost();
            if (!reactNativeHost.hasInstance()) {
                lifecycleState = LifecycleState.BEFORE_CREATE;
            } else {
                lifecycleState = reactNativeHost.getReactInstanceManager().getLifecycleState();
            }
        }
        if (lifecycleState == LifecycleState.RESUMED) {
            callback.invoke(new Object[0]);
        } else {
            this.mPermissionsCallback = callback;
        }
    }

    public /* synthetic */ void lambda$onRequestPermissionsResult$1(int i, String[] strArr, int[] iArr, Object[] objArr) {
        PermissionListener permissionListener = this.mPermissionListener;
        if (permissionListener == null || !permissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
            return;
        }
        this.mPermissionListener = null;
    }

    protected Context getContext() {
        return (Context) Assertions.assertNotNull(this.mActivity);
    }

    protected Activity getPlainActivity() {
        return (Activity) getContext();
    }

    protected ReactActivity getReactActivity() {
        return (ReactActivity) getContext();
    }

    public ReactContext getCurrentReactContext() {
        return ((ReactDelegate) Objects.requireNonNull(this.mReactDelegate)).getCurrentReactContext();
    }

    /* JADX INFO: renamed from: isFabricEnabled */
    protected boolean getFabricEnabled() {
        return ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer();
    }
}
