package com.facebook.react.devsupport;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DebugOverlayController.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/devsupport/DebugOverlayController;", "", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "<init>", "(Lcom/facebook/react/bridge/ReactContext;)V", "windowManager", "Landroid/view/WindowManager;", "fpsDebugViewContainer", "Landroid/widget/FrameLayout;", "setFpsDebugViewVisible", "", "fpsDebugViewVisible", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DebugOverlayController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FrameLayout fpsDebugViewContainer;
    private final ReactContext reactContext;
    private final WindowManager windowManager;

    @JvmStatic
    public static final void requestPermission(Context context) {
        INSTANCE.requestPermission(context);
    }

    public DebugOverlayController(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        Object systemService = reactContext.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.windowManager = (WindowManager) systemService;
    }

    public final void setFpsDebugViewVisible(final boolean fpsDebugViewVisible) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DebugOverlayController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DebugOverlayController.setFpsDebugViewVisible$lambda$0(fpsDebugViewVisible, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setFpsDebugViewVisible$lambda$0(boolean z, DebugOverlayController debugOverlayController) {
        FrameLayout frameLayout;
        if (z && debugOverlayController.fpsDebugViewContainer == null) {
            if (!INSTANCE.permissionCheck(debugOverlayController.reactContext)) {
                FLog.d(ReactConstants.TAG, "Wait for overlay permission to be set");
                return;
            } else {
                debugOverlayController.fpsDebugViewContainer = new FpsView(debugOverlayController.reactContext);
                debugOverlayController.windowManager.addView(debugOverlayController.fpsDebugViewContainer, new WindowManager.LayoutParams(-1, -1, WindowOverlayCompat.TYPE_SYSTEM_OVERLAY, 24, -3));
                return;
            }
        }
        if (z || (frameLayout = debugOverlayController.fpsDebugViewContainer) == null) {
            return;
        }
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        debugOverlayController.windowManager.removeView(debugOverlayController.fpsDebugViewContainer);
        debugOverlayController.fpsDebugViewContainer = null;
    }

    /* JADX INFO: compiled from: DebugOverlayController.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, d2 = {"Lcom/facebook/react/devsupport/DebugOverlayController$Companion;", "", "<init>", "()V", "requestPermission", "", "context", "Landroid/content/Context;", "permissionCheck", "", "canHandleIntent", "intent", "Landroid/content/Intent;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void requestPermission(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (Settings.canDrawOverlays(context)) {
                return;
            }
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + context.getPackageName()));
            intent.setFlags(268435456);
            FLog.w(ReactConstants.TAG, "Overlay permissions needs to be granted in order for react native apps to run in dev mode");
            if (canHandleIntent(context, intent)) {
                context.startActivity(intent);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean permissionCheck(Context context) {
            return Settings.canDrawOverlays(context);
        }

        private final boolean canHandleIntent(Context context, Intent intent) {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || intent.resolveActivity(packageManager) == null) ? false : true;
        }
    }
}
