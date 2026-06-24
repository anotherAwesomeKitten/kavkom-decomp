package com.facebook.react.modules.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DialogModule.kt */
/* JADX INFO: loaded from: classes.dex */
@ReactModule(name = "DialogManagerAndroid")
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u001f2\u00020\u00012\u00020\u0002:\u0003\u001d\u001e\u001fB\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J \u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\b\u0018\u00010\u0019R\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/facebook/react/modules/dialog/DialogModule;", "Lcom/facebook/fbreact/specs/NativeDialogManagerAndroidSpec;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "isInForeground", "", "getTypedExportedConstants", "", "", "", "initialize", "", "onHostPause", "onHostDestroy", "onHostResume", "showAlert", "options", "Lcom/facebook/react/bridge/ReadableMap;", "errorCallback", "Lcom/facebook/react/bridge/Callback;", "actionCallback", "fragmentManagerHelper", "Lcom/facebook/react/modules/dialog/DialogModule$FragmentManagerHelper;", "getFragmentManagerHelper", "()Lcom/facebook/react/modules/dialog/DialogModule$FragmentManagerHelper;", "invalidate", "FragmentManagerHelper", "AlertFragmentListener", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DialogModule extends NativeDialogManagerAndroidSpec implements LifecycleEventListener {
    public static final String FRAGMENT_TAG = "com.facebook.catalyst.react.dialog.DialogModule";
    private static final String KEY_CANCELABLE = "cancelable";
    private static final String KEY_ITEMS = "items";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TITLE = "title";
    public static final String NAME = "DialogManagerAndroid";
    private boolean isInForeground;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    public static final String ACTION_DISMISSED = "dismissed";
    private static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    private static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    private static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    private static final Map<String, Object> CONSTANTS = MapsKt.mapOf(TuplesKt.to(ACTION_BUTTON_CLICKED, ACTION_BUTTON_CLICKED), TuplesKt.to(ACTION_DISMISSED, ACTION_DISMISSED), TuplesKt.to(KEY_BUTTON_POSITIVE, -1), TuplesKt.to(KEY_BUTTON_NEGATIVE, -2), TuplesKt.to(KEY_BUTTON_NEUTRAL, -3));

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    public DialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DialogModule.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u0018\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/modules/dialog/DialogModule$FragmentManagerHelper;", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "<init>", "(Lcom/facebook/react/modules/dialog/DialogModule;Landroidx/fragment/app/FragmentManager;)V", "fragmentToShow", "Lcom/facebook/react/modules/dialog/AlertFragment;", "showPendingAlert", "", "dismissExisting", "showNewAlert", "arguments", "Landroid/os/Bundle;", "actionCallback", "Lcom/facebook/react/bridge/Callback;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    final class FragmentManagerHelper {
        private final FragmentManager fragmentManager;
        private AlertFragment fragmentToShow;
        final /* synthetic */ DialogModule this$0;

        public FragmentManagerHelper(DialogModule dialogModule, FragmentManager fragmentManager) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            this.this$0 = dialogModule;
            this.fragmentManager = fragmentManager;
        }

        public final void showPendingAlert() {
            UiThreadUtil.assertOnUiThread();
            SoftAssertions.assertCondition(this.this$0.isInForeground, "showPendingAlert() called in background");
            AlertFragment alertFragment = this.fragmentToShow;
            if (alertFragment == null) {
                return;
            }
            dismissExisting();
            alertFragment.show(this.fragmentManager, DialogModule.FRAGMENT_TAG);
            this.fragmentToShow = null;
        }

        public final void dismissExisting() {
            AlertFragment alertFragment;
            if (this.this$0.isInForeground && (alertFragment = (AlertFragment) this.fragmentManager.findFragmentByTag(DialogModule.FRAGMENT_TAG)) != null && alertFragment.isResumed()) {
                alertFragment.dismiss();
            }
        }

        public final void showNewAlert(Bundle arguments, Callback actionCallback) {
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            UiThreadUtil.assertOnUiThread();
            dismissExisting();
            AlertFragment alertFragment = new AlertFragment(actionCallback != null ? new AlertFragmentListener(this.this$0, actionCallback) : null, arguments);
            if (this.this$0.isInForeground && !this.fragmentManager.isStateSaved()) {
                if (arguments.containsKey(DialogModule.KEY_CANCELABLE)) {
                    alertFragment.setCancelable(arguments.getBoolean(DialogModule.KEY_CANCELABLE));
                }
                alertFragment.show(this.fragmentManager, DialogModule.FRAGMENT_TAG);
                return;
            }
            this.fragmentToShow = alertFragment;
        }
    }

    /* JADX INFO: compiled from: DialogModule.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/modules/dialog/DialogModule$AlertFragmentListener;", "Landroid/content/DialogInterface$OnClickListener;", "Landroid/content/DialogInterface$OnDismissListener;", "callback", "Lcom/facebook/react/bridge/Callback;", "<init>", "(Lcom/facebook/react/modules/dialog/DialogModule;Lcom/facebook/react/bridge/Callback;)V", "callbackConsumed", "", ViewProps.ON_CLICK, "", "dialog", "Landroid/content/DialogInterface;", "which", "", "onDismiss", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class AlertFragmentListener implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
        private final Callback callback;
        private boolean callbackConsumed;
        final /* synthetic */ DialogModule this$0;

        public AlertFragmentListener(DialogModule dialogModule, Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.this$0 = dialogModule;
            this.callback = callback;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            Intrinsics.checkNotNullParameter(dialog, "dialog");
            if (this.callbackConsumed || !this.this$0.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            this.callback.invoke(DialogModule.ACTION_BUTTON_CLICKED, Integer.valueOf(which));
            this.callbackConsumed = true;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialog) {
            if (this.callbackConsumed || !this.this$0.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            this.callback.invoke(DialogModule.ACTION_DISMISSED);
            this.callbackConsumed = true;
        }
    }

    @Override // com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec
    public Map<String, Object> getTypedExportedConstants() {
        return CONSTANTS;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.isInForeground = false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.isInForeground = true;
        FragmentManagerHelper fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper != null) {
            fragmentManagerHelper.showPendingAlert();
        } else {
            FLog.w((Class<?>) DialogModule.class, "onHostResume called but no FragmentManager found");
        }
    }

    @Override // com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec
    public void showAlert(ReadableMap options, Callback errorCallback, final Callback actionCallback) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Intrinsics.checkNotNullParameter(actionCallback, "actionCallback");
        final FragmentManagerHelper fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            errorCallback.invoke("Tried to show an alert while not attached to an Activity");
            return;
        }
        final Bundle bundle = new Bundle();
        if (options.hasKey("title")) {
            bundle.putString("title", options.getString("title"));
        }
        if (options.hasKey("message")) {
            bundle.putString("message", options.getString("message"));
        }
        if (options.hasKey(KEY_BUTTON_POSITIVE)) {
            bundle.putString(AlertFragment.ARG_BUTTON_POSITIVE, options.getString(KEY_BUTTON_POSITIVE));
        }
        if (options.hasKey(KEY_BUTTON_NEGATIVE)) {
            bundle.putString(AlertFragment.ARG_BUTTON_NEGATIVE, options.getString(KEY_BUTTON_NEGATIVE));
        }
        if (options.hasKey(KEY_BUTTON_NEUTRAL)) {
            bundle.putString(AlertFragment.ARG_BUTTON_NEUTRAL, options.getString(KEY_BUTTON_NEUTRAL));
        }
        if (options.hasKey("items")) {
            ReadableArray array = options.getArray("items");
            if (array == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            CharSequence[] charSequenceArr = new CharSequence[array.size()];
            int size = array.size();
            for (int i = 0; i < size; i++) {
                charSequenceArr[i] = array.getString(i);
            }
            bundle.putCharSequenceArray("items", charSequenceArr);
        }
        if (options.hasKey(KEY_CANCELABLE)) {
            bundle.putBoolean(KEY_CANCELABLE, options.getBoolean(KEY_CANCELABLE));
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.dialog.DialogModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                fragmentManagerHelper.showNewAlert(bundle, actionCallback);
            }
        });
    }

    private final FragmentManagerHelper getFragmentManagerHelper() {
        Activity currentActivity = getReactApplicationContext().getCurrentActivity();
        if (!(currentActivity instanceof FragmentActivity)) {
            return null;
        }
        FragmentManager supportFragmentManager = ((FragmentActivity) currentActivity).getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        return new FragmentManagerHelper(this, supportFragmentManager);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        getReactApplicationContext().removeLifecycleEventListener(this);
        super.invalidate();
    }

    /* JADX INFO: compiled from: DialogModule.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003R\u0016\u0010\b\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0003R\u0016\u0010\n\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0003R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/modules/dialog/DialogModule$Companion;", "", "<init>", "()V", "NAME", "", "FRAGMENT_TAG", "getFRAGMENT_TAG$ReactAndroid_release$annotations", "ACTION_BUTTON_CLICKED", "getACTION_BUTTON_CLICKED$ReactAndroid_release$annotations", "ACTION_DISMISSED", "getACTION_DISMISSED$ReactAndroid_release$annotations", "KEY_TITLE", "KEY_MESSAGE", "KEY_BUTTON_POSITIVE", "KEY_BUTTON_NEGATIVE", "KEY_BUTTON_NEUTRAL", "KEY_ITEMS", "KEY_CANCELABLE", "CONSTANTS", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @VisibleForTesting
        public static /* synthetic */ void getACTION_BUTTON_CLICKED$ReactAndroid_release$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void getACTION_DISMISSED$ReactAndroid_release$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void getFRAGMENT_TAG$ReactAndroid_release$annotations() {
        }

        private Companion() {
        }
    }
}
