package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Collection;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThemedReactContext.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\fB\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J%\u0010\u001d\u001a\u0002H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!H\u0016¢\u0006\u0002\u0010\"J \u0010#\u001a\u00020\u001a\"\b\b\u0000\u0010\u001e*\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001e0!H\u0016J\u0012\u0010&\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010'H\u0016J'\u0010(\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001e0!H\u0016¢\u0006\u0002\u0010)J\u0012\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\n\u0010*\u001a\u0004\u0018\u00010+H\u0017J\b\u0010,\u001a\u00020\u001aH\u0017J\b\u0010-\u001a\u00020\u001aH\u0016J\b\u0010.\u001a\u00020\u001aH\u0017J\b\u0010/\u001a\u00020\u001aH\u0016J\b\u00100\u001a\u00020\u0015H\u0016J\n\u00101\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u00102\u001a\u00020\u00152\u000e\u00103\u001a\n\u0018\u000104j\u0004\u0018\u0001`5H\u0016J\b\u00106\u001a\u00020\u001aH\u0017J\n\u00107\u001a\u0004\u0018\u000108H\u0016J\n\u00109\u001a\u0004\u0018\u00010:H\u0016J\n\u0010;\u001a\u0004\u0018\u00010<H\u0017J\n\u0010=\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010>\u001a\u00020\u00152\u0006\u0010?\u001a\u00020\t2\b\u0010@\u001a\u0004\u0018\u00010\u00072\b\u0010A\u001a\u0004\u0018\u00010BH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006C"}, d2 = {"Lcom/facebook/react/uimanager/ThemedReactContext;", "Lcom/facebook/react/bridge/ReactContext;", "reactApplicationContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", Constants.SENSITIVITY_BASE, "Landroid/content/Context;", "moduleName", "", "surfaceId", "", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;Landroid/content/Context;Ljava/lang/String;I)V", "(Lcom/facebook/react/bridge/ReactApplicationContext;Landroid/content/Context;Ljava/lang/String;)V", "(Lcom/facebook/react/bridge/ReactApplicationContext;Landroid/content/Context;)V", "getReactApplicationContext", "()Lcom/facebook/react/bridge/ReactApplicationContext;", "getModuleName", "()Ljava/lang/String;", "getSurfaceId", "()I", "addLifecycleEventListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/bridge/LifecycleEventListener;", "removeLifecycleEventListener", "hasCurrentActivity", "", "getCurrentActivity", "Landroid/app/Activity;", "getJSModule", "T", "Lcom/facebook/react/bridge/JavaScriptModule;", "jsInterface", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/JavaScriptModule;", "hasNativeModule", "Lcom/facebook/react/bridge/NativeModule;", "nativeModuleInterface", "getNativeModules", "", "getNativeModule", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/NativeModule;", "getCatalystInstance", "Lcom/facebook/react/bridge/CatalystInstance;", "hasActiveCatalystInstance", "hasActiveReactInstance", "hasCatalystInstance", "hasReactInstance", "destroy", "getSurfaceID", "handleException", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "isBridgeless", "getJavaScriptContextHolder", "Lcom/facebook/react/bridge/JavaScriptContextHolder;", "getJSCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/interfaces/CallInvokerHolder;", "getFabricUIManager", "Lcom/facebook/react/bridge/UIManager;", "getSourceURL", "registerSegment", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "path", "callback", "Lcom/facebook/react/bridge/Callback;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ThemedReactContext extends ReactContext {
    private final String moduleName;
    private final ReactApplicationContext reactApplicationContext;
    private final int surfaceId;

    public final ReactApplicationContext getReactApplicationContext() {
        return this.reactApplicationContext;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final int getSurfaceId() {
        return this.surfaceId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context base, String str, int i) {
        super(base);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(base, "base");
        this.reactApplicationContext = reactApplicationContext;
        this.moduleName = str;
        this.surfaceId = i;
        initializeFromOther(reactApplicationContext);
    }

    public /* synthetic */ ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactApplicationContext, context, (i & 4) != 0 ? null : str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "This constructor is deprecated and you should not be using it.")
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context base, String str) {
        this(reactApplicationContext, base, str, -1);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(base, "base");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "This constructor is deprecated and you should not be using it.")
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context base) {
        this(reactApplicationContext, base, null, -1);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(base, "base");
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void addLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        Intrinsics.checkNotNullParameter(lifecycleEventListener, "listener");
        this.reactApplicationContext.addLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        Intrinsics.checkNotNullParameter(lifecycleEventListener, "listener");
        this.reactApplicationContext.removeLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasCurrentActivity() {
        return this.reactApplicationContext.hasCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public Activity getCurrentActivity() {
        return this.reactApplicationContext.getCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface) {
        Intrinsics.checkNotNullParameter(jsInterface, "jsInterface");
        T t = (T) this.reactApplicationContext.getJSModule(jsInterface);
        Intrinsics.checkNotNullExpressionValue(t, "getJSModule(...)");
        return t;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        return this.reactApplicationContext.hasNativeModule(nativeModuleInterface);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public Collection<NativeModule> getNativeModules() {
        return this.reactApplicationContext.getNativeModules();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        return (T) this.reactApplicationContext.getNativeModule(nativeModuleInterface);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public NativeModule getNativeModule(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        return this.reactApplicationContext.getNativeModule(moduleName);
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Deprecated(message = "This method is deprecated and will be removed once the Legacy Architecture is removed")
    public CatalystInstance getCatalystInstance() {
        return this.reactApplicationContext.getCatalystInstance();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Deprecated(message = "This API has been deprecated due to naming consideration, please use hasActiveReactInstance() instead", replaceWith = @ReplaceWith(expression = "hasActiveReactInstance()", imports = {}))
    public boolean hasActiveCatalystInstance() {
        return this.reactApplicationContext.hasActiveCatalystInstance();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasActiveReactInstance() {
        return this.reactApplicationContext.hasActiveCatalystInstance();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Deprecated(message = "This API has been deprecated due to naming consideration, please use hasReactInstance() instead", replaceWith = @ReplaceWith(expression = "hasReactInstance()", imports = {}))
    public boolean hasCatalystInstance() {
        return this.reactApplicationContext.hasCatalystInstance();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasReactInstance() {
        return this.reactApplicationContext.hasReactInstance();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void destroy() {
        this.reactApplicationContext.destroy();
    }

    @Deprecated(message = "Do not depend on this method. It will be removed in a future release of React Native.")
    public final String getSurfaceID() {
        return this.moduleName;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void handleException(Exception e) {
        this.reactApplicationContext.handleException(e);
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Deprecated(message = "You should not invoke isBridgeless and let your code depend on this check. This function will be removed in the future.")
    public boolean isBridgeless() {
        return this.reactApplicationContext.isBridgeless();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.reactApplicationContext.getJavaScriptContextHolder();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public CallInvokerHolder getJSCallInvokerHolder() {
        return this.reactApplicationContext.getJSCallInvokerHolder();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Deprecated(message = "This method is deprecated, please use UIManagerHelper.getUIManager() instead.", replaceWith = @ReplaceWith(expression = "UIManagerHelper.getUIManager()", imports = {}))
    public UIManager getFabricUIManager() {
        return this.reactApplicationContext.getFabricUIManager();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public String getSourceURL() {
        return this.reactApplicationContext.getSourceURL();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void registerSegment(int i, String path, Callback callback) {
        this.reactApplicationContext.registerSegment(i, path, callback);
    }
}
