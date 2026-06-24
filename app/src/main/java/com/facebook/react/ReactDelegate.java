package com.facebook.react;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.devsupport.ReleaseDevSupportManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bB/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000eB7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\n\u0010\u0011J\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020%J\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020%J\u0006\u0010)\u001a\u00020\u0010J\u000e\u0010*\u001a\u00020\u00102\u0006\u0010+\u001a\u00020,J(\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u00010,2\u0006\u00102\u001a\u00020\u0010J\u000e\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\u0010J\u0010\u00105\u001a\u00020%2\b\u00106\u001a\u0004\u0018\u000107J\u0016\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020\u00102\u0006\u00109\u001a\u00020/J\u0006\u0010=\u001a\u00020%J\u0006\u0010>\u001a\u00020%J\u000e\u0010>\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010?\u001a\u00020%J\u0010\u0010@\u001a\u00020%2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\n\u0010F\u001a\u0004\u0018\u00010\u0013H\u0014J\u0018\u0010G\u001a\u00020\u00102\u0006\u00109\u001a\u00020/2\b\u0010:\u001a\u0004\u0018\u00010;J\b\u0010H\u001a\u00020IH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0018R\"\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u0004\u0018\u00010!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R(\u0010A\u001a\u0004\u0018\u00010\u00132\b\u0010A\u001a\u0004\u0018\u00010\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0013\u0010J\u001a\u0004\u0018\u00010K8F¢\u0006\u0006\u001a\u0004\bL\u0010M¨\u0006N"}, d2 = {"Lcom/facebook/react/ReactDelegate;", "", "activity", "Landroid/app/Activity;", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "appKey", "", "launchOptions", "Landroid/os/Bundle;", "<init>", "(Landroid/app/Activity;Lcom/facebook/react/ReactNativeHost;Ljava/lang/String;Landroid/os/Bundle;)V", "reactHost", "Lcom/facebook/react/ReactHost;", "(Landroid/app/Activity;Lcom/facebook/react/ReactHost;Ljava/lang/String;Landroid/os/Bundle;)V", "fabricEnabled", "", "(Landroid/app/Activity;Lcom/facebook/react/ReactNativeHost;Ljava/lang/String;Landroid/os/Bundle;Z)V", "internalReactRootView", "Lcom/facebook/react/ReactRootView;", "mainComponentName", "doubleTapReloadRecognizer", "Lcom/facebook/react/devsupport/DoubleTapReloadRecognizer;", "getReactNativeHost$annotations", "()V", "value", "getReactHost", "()Lcom/facebook/react/ReactHost;", "reactSurface", "Lcom/facebook/react/interfaces/fabric/ReactSurface;", "isFabricEnabled", "()Z", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "getDevSupportManager", "()Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "onHostResume", "", "onUserLeaveHint", "onHostPause", "onHostDestroy", "onBackPressed", "onNewIntent", "intent", "Landroid/content/Intent;", "onActivityResult", "requestCode", "", "resultCode", "data", "shouldForwardToReactInstance", "onWindowFocusChanged", "hasFocus", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyLongPress", "reload", "loadApp", "unloadApp", "setReactSurface", "reactRootView", "getReactRootView", "()Lcom/facebook/react/ReactRootView;", "setReactRootView", "(Lcom/facebook/react/ReactRootView;)V", "createRootView", "shouldShowDevMenuOrReload", "getReactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "currentReactContext", "Lcom/facebook/react/bridge/ReactContext;", "getCurrentReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ReactDelegate {
    private final Activity activity;
    private DoubleTapReloadRecognizer doubleTapReloadRecognizer;
    private ReactRootView internalReactRootView;
    private boolean isFabricEnabled;
    private Bundle launchOptions;
    private final String mainComponentName;
    private ReactHost reactHost;
    private ReactNativeHost reactNativeHost;
    private ReactSurface reactSurface;

    @Deprecated(message = "You should not use ReactNativeHost directly in the New Architecture. Use ReactHost instead.", replaceWith = @ReplaceWith(expression = "reactHost", imports = {}))
    private static /* synthetic */ void getReactNativeHost$annotations() {
    }

    public final ReactHost getReactHost() {
        return this.reactHost;
    }

    /* JADX INFO: renamed from: isFabricEnabled, reason: from getter */
    protected final boolean getIsFabricEnabled() {
        return this.isFabricEnabled;
    }

    @Deprecated(message = "Use one of the other constructors instead to account for New Architecture. Deprecated since 0.75.0")
    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.isFabricEnabled = ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer();
        this.activity = activity;
        this.mainComponentName = str;
        this.launchOptions = bundle;
        this.doubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.reactNativeHost = reactNativeHost;
    }

    public ReactDelegate(Activity activity, ReactHost reactHost, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.isFabricEnabled = ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer();
        this.activity = activity;
        this.mainComponentName = str;
        this.launchOptions = bundle;
        this.doubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.reactHost = reactHost;
    }

    @Deprecated(message = "Deprecated since 0.81.0, use one of the other constructors instead.")
    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer();
        this.isFabricEnabled = z;
        this.activity = activity;
        this.mainComponentName = str;
        this.launchOptions = bundle;
        this.doubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.reactNativeHost = reactNativeHost;
    }

    private final DevSupportManager getDevSupportManager() {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
            ReactHost reactHost = this.reactHost;
            if ((reactHost != null ? reactHost.getDevSupportManager() : null) != null) {
                ReactHost reactHost2 = this.reactHost;
                if (reactHost2 != null) {
                    return reactHost2.getDevSupportManager();
                }
                return null;
            }
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 != null && reactNativeHost2.hasInstance()) {
            ReactNativeHost reactNativeHost3 = this.reactNativeHost;
            if ((reactNativeHost3 != null ? reactNativeHost3.getReactInstanceManager() : null) != null && (reactNativeHost = this.reactNativeHost) != null && (reactInstanceManager = reactNativeHost.getReactInstanceManager()) != null) {
                return reactInstanceManager.getDevSupportManager();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void onHostResume() {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (!(this.activity instanceof DefaultHardwareBackBtnHandler)) {
            throw new ClassCastException("Host Activity `" + this.activity.getClass().getSimpleName() + "` does not implement DefaultHardwareBackBtnHandler");
        }
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                Activity activity = this.activity;
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.facebook.react.modules.core.DefaultHardwareBackBtnHandler");
                reactHost.onHostResume(activity, (DefaultHardwareBackBtnHandler) activity);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || (reactNativeHost = this.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        Activity activity2 = this.activity;
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.facebook.react.modules.core.DefaultHardwareBackBtnHandler");
        reactInstanceManager.onHostResume(activity2, (DefaultHardwareBackBtnHandler) activity2);
    }

    public final void onUserLeaveHint() {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                reactHost.onHostLeaveHint(this.activity);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || (reactNativeHost = this.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        reactInstanceManager.onUserLeaveHint(this.activity);
    }

    public final void onHostPause() {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                reactHost.onHostPause(this.activity);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || (reactNativeHost = this.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        reactInstanceManager.onHostPause(this.activity);
    }

    public final void onHostDestroy() {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        unloadApp();
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                reactHost.onHostDestroy(this.activity);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || (reactNativeHost = this.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        reactInstanceManager.onHostDestroy(this.activity);
    }

    public final boolean onBackPressed() {
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                reactHost.onBackPressed();
            }
            return true;
        }
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        if (reactNativeHost == null || !reactNativeHost.hasInstance()) {
            return false;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 != null && (reactInstanceManager = reactNativeHost2.getReactInstanceManager()) != null) {
            reactInstanceManager.onBackPressed();
        }
        return true;
    }

    public final boolean onNewIntent(Intent intent) {
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                reactHost.onNewIntent(intent);
            }
            return true;
        }
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        if (reactNativeHost == null || !reactNativeHost.hasInstance()) {
            return false;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 != null && (reactInstanceManager = reactNativeHost2.getReactInstanceManager()) != null) {
            reactInstanceManager.onNewIntent(intent);
        }
        return true;
    }

    public final void onActivityResult(int requestCode, int resultCode, Intent data, boolean shouldForwardToReactInstance) {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null && shouldForwardToReactInstance) {
            if (reactHost != null) {
                reactHost.onActivityResult(this.activity, requestCode, resultCode, data);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || !shouldForwardToReactInstance || (reactNativeHost = this.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        reactInstanceManager.onActivityResult(this.activity, requestCode, resultCode, data);
    }

    public final void onWindowFocusChanged(boolean hasFocus) {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                reactHost.onWindowFocusChange(hasFocus);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || (reactNativeHost = this.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        reactInstanceManager.onWindowFocusChange(hasFocus);
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        ReactHost reactHost;
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            if (reactHost != null) {
                Activity activity = this.activity;
                if (activity == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                reactHost.onConfigurationChanged(activity);
                return;
            }
            return;
        }
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        if (reactNativeHost == null || !reactNativeHost.hasInstance()) {
            return;
        }
        ReactInstanceManager reactInstanceManager = getReactInstanceManager();
        Activity activity2 = this.activity;
        if (activity2 == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        reactInstanceManager.onConfigurationChanged(activity2, newConfig);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onKeyDown(int r2, android.view.KeyEvent r3) {
        /*
            r1 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r0 = 90
            if (r2 != r0) goto L34
            boolean r2 = com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()
            r0 = 1
            if (r2 == 0) goto L1c
            com.facebook.react.ReactHost r2 = r1.reactHost
            if (r2 == 0) goto L19
            com.facebook.react.devsupport.interfaces.DevSupportManager r2 = r2.getDevSupportManager()
            goto L1a
        L19:
            r2 = 0
        L1a:
            if (r2 != 0) goto L30
        L1c:
            com.facebook.react.ReactNativeHost r2 = r1.reactNativeHost
            if (r2 == 0) goto L34
            boolean r2 = r2.hasInstance()
            if (r2 != r0) goto L34
            com.facebook.react.ReactNativeHost r2 = r1.reactNativeHost
            if (r2 == 0) goto L34
            boolean r2 = r2.getUseDeveloperSupport()
            if (r2 != r0) goto L34
        L30:
            r3.startTracking()
            return r0
        L34:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactDelegate.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    public final boolean onKeyLongPress(int keyCode) {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactHost reactHost;
        if (keyCode != 4 && keyCode != 90) {
            return false;
        }
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && (reactHost = this.reactHost) != null) {
            DevSupportManager devSupportManager = reactHost != null ? reactHost.getDevSupportManager() : null;
            if (devSupportManager == null || (devSupportManager instanceof ReleaseDevSupportManager)) {
                return false;
            }
            devSupportManager.showDevOptionsDialog();
            return true;
        }
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance() || (reactNativeHost = this.reactNativeHost) == null || !reactNativeHost.getUseDeveloperSupport()) {
            return false;
        }
        ReactNativeHost reactNativeHost3 = this.reactNativeHost;
        if (reactNativeHost3 != null && (reactInstanceManager = reactNativeHost3.getReactInstanceManager()) != null) {
            reactInstanceManager.showDevOptionsDialog();
        }
        return true;
    }

    public final void reload() {
        DevSupportManager devSupportManager = getDevSupportManager();
        if (devSupportManager == null) {
            return;
        }
        if (devSupportManager instanceof ReleaseDevSupportManager) {
            if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
                ReactHost reactHost = this.reactHost;
                if (reactHost != null) {
                    reactHost.reload("ReactDelegate.reload()");
                    return;
                }
                return;
            }
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactDelegate$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ReactDelegate.reload$lambda$0(this.f$0);
                }
            });
            return;
        }
        devSupportManager.handleReloadJS();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reload$lambda$0(ReactDelegate reactDelegate) {
        ReactNativeHost reactNativeHost;
        ReactInstanceManager reactInstanceManager;
        ReactNativeHost reactNativeHost2 = reactDelegate.reactNativeHost;
        if (reactNativeHost2 == null || !reactNativeHost2.hasInstance()) {
            return;
        }
        ReactNativeHost reactNativeHost3 = reactDelegate.reactNativeHost;
        if ((reactNativeHost3 != null ? reactNativeHost3.getReactInstanceManager() : null) == null || (reactNativeHost = reactDelegate.reactNativeHost) == null || (reactInstanceManager = reactNativeHost.getReactInstanceManager()) == null) {
            return;
        }
        reactInstanceManager.recreateReactContextInBackground();
    }

    public final void loadApp() {
        String str = this.mainComponentName;
        if (str == null) {
            throw new IllegalArgumentException("Cannot loadApp without a main component name.".toString());
        }
        loadApp(str);
    }

    public final void loadApp(String appKey) {
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
            ReactHost reactHost = this.reactHost;
            if (this.reactSurface == null && reactHost != null) {
                this.reactSurface = reactHost.createSurface(this.activity, appKey, this.launchOptions);
            }
            ReactSurface reactSurface = this.reactSurface;
            if (reactSurface != null) {
                reactSurface.start();
                return;
            }
            return;
        }
        if (this.internalReactRootView != null) {
            throw new IllegalStateException("Cannot loadApp while app is already running.".toString());
        }
        ReactRootView reactRootViewCreateRootView = createRootView();
        this.internalReactRootView = reactRootViewCreateRootView;
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        if (reactNativeHost == null || reactRootViewCreateRootView == null) {
            return;
        }
        reactRootViewCreateRootView.startReactApplication(reactNativeHost != null ? reactNativeHost.getReactInstanceManager() : null, appKey, this.launchOptions);
    }

    public final void unloadApp() {
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
            ReactSurface reactSurface = this.reactSurface;
            if (reactSurface != null) {
                reactSurface.stop();
            }
            this.reactSurface = null;
            return;
        }
        ReactRootView reactRootView = this.internalReactRootView;
        if (reactRootView != null) {
            if (reactRootView != null) {
                reactRootView.unmountReactApplication();
            }
            this.internalReactRootView = null;
        }
    }

    public final void setReactSurface(ReactSurface reactSurface) {
        this.reactSurface = reactSurface;
    }

    public final ReactRootView getReactRootView() {
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
            ReactSurface reactSurface = this.reactSurface;
            if (reactSurface != null) {
                return (ReactRootView) (reactSurface != null ? reactSurface.getView() : null);
            }
            return null;
        }
        return this.internalReactRootView;
    }

    public final void setReactRootView(ReactRootView reactRootView) {
        this.internalReactRootView = reactRootView;
    }

    protected ReactRootView createRootView() {
        ReactRootView reactRootView = new ReactRootView(this.activity);
        reactRootView.setIsFabric(this.isFabricEnabled);
        return reactRootView;
    }

    public final boolean shouldShowDevMenuOrReload(int keyCode, KeyEvent event) {
        DevSupportManager devSupportManager = getDevSupportManager();
        if (devSupportManager != null && devSupportManager.getKeyboardShortcutsEnabled() && !(devSupportManager instanceof ReleaseDevSupportManager)) {
            if (keyCode == 82) {
                devSupportManager.showDevOptionsDialog();
                return true;
            }
            DoubleTapReloadRecognizer doubleTapReloadRecognizer = this.doubleTapReloadRecognizer;
            if (Intrinsics.areEqual((Object) (doubleTapReloadRecognizer != null ? Boolean.valueOf(doubleTapReloadRecognizer.didDoubleTapR(keyCode, this.activity.getCurrentFocus())) : null), (Object) true)) {
                devSupportManager.handleReloadJS();
                return true;
            }
        }
        return false;
    }

    @Deprecated(message = "Do not access [ReactInstanceManager] directly. This class is going away in the New Architecture. You should use [ReactHost] instead.")
    public final ReactInstanceManager getReactInstanceManager() {
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        if (reactNativeHost == null) {
            throw new IllegalStateException("Cannot get ReactInstanceManager without a ReactNativeHost.".toString());
        }
        ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();
        Intrinsics.checkNotNullExpressionValue(reactInstanceManager, "getReactInstanceManager(...)");
        return reactInstanceManager;
    }

    public final ReactContext getCurrentReactContext() {
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture()) {
            ReactHost reactHost = this.reactHost;
            if (reactHost == null || reactHost == null) {
                return null;
            }
            return reactHost.getCurrentReactContext();
        }
        return getReactInstanceManager().getCurrentReactContext();
    }
}
