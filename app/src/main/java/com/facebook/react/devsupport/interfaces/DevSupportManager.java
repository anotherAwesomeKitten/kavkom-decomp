package com.facebook.react.devsupport.interfaces;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: compiled from: DevSupportManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0002|}J\u001a\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u000b2\u0006\u0010@\u001a\u00020AH&J\u0018\u0010B\u001a\u00020>2\u0006\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020EH&J\u0012\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010H\u001a\u00020\u000bH&J\u0012\u0010I\u001a\u00020>2\b\u0010J\u001a\u0004\u0018\u00010GH&J$\u0010K\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u000b2\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020\u001eH&J\b\u0010O\u001a\u00020>H&J\b\u0010P\u001a\u00020>H&J\b\u0010Q\u001a\u00020>H&J\b\u0010R\u001a\u00020>H&J\u0010\u0010S\u001a\u00020>2\u0006\u0010T\u001a\u00020&H&J\u0010\u0010U\u001a\u00020>2\u0006\u0010T\u001a\u00020&H&J\b\u0010V\u001a\u00020*H&J\b\u0010W\u001a\u00020>H&J\b\u0010X\u001a\u00020>H&J\u0018\u0010Y\u001a\u00020>2\u0006\u0010Z\u001a\u00020\u000b2\u0006\u0010[\u001a\u00020\\H&J\u0010\u0010]\u001a\u00020>2\u0006\u0010[\u001a\u00020^H&J\u0010\u0010_\u001a\u00020>2\u0006\u0010`\u001a\u00020*H&J\u0010\u0010a\u001a\u00020>2\u0006\u0010b\u001a\u00020*H&J\b\u0010c\u001a\u00020>H&J\u001a\u0010d\u001a\u0004\u0018\u00010e2\u0006\u0010f\u001a\u00020\u000b2\u0006\u0010g\u001a\u00020eH&J\u0010\u0010h\u001a\u00020>2\u0006\u0010i\u001a\u00020jH&J4\u0010k\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150l2\u0018\u0010m\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150lH&J\u0010\u0010n\u001a\u00020>2\u0006\u0010o\u001a\u00020pH&J\u0012\u0010q\u001a\u0004\u0018\u00010r2\u0006\u0010s\u001a\u00020\u000bH&J\u0014\u0010t\u001a\u00020>2\n\b\u0002\u0010u\u001a\u0004\u0018\u00010\u000bH&J\u0018\u0010v\u001a\u00020>2\u0006\u0010?\u001a\u00020\u000b2\u0006\u0010w\u001a\u00020xH&J\b\u0010y\u001a\u00020>H&J\u0018\u0010z\u001a\u00020>2\u0006\u0010{\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000bH&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u0004\u0018\u00010&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R$\u0010+\u001a\u00020*2\u0006\u0010)\u001a\u00020*8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00100\u001a\u00020*2\u0006\u0010)\u001a\u00020*8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R$\u00103\u001a\u00020*2\u0006\u0010)\u001a\u00020*8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/R(\u00106\u001a\u0004\u0018\u00010\u000b2\b\u0010)\u001a\u0004\u0018\u00010\u000b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u0010\r\"\u0004\b8\u00109R\u0018\u0010:\u001a\u00020*X¦\u000e¢\u0006\f\u001a\u0004\b;\u0010-\"\u0004\b<\u0010/ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006~À\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "Lcom/facebook/react/bridge/JSExceptionHandler;", "devSettings", "Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "getDevSettings", "()Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "redBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "getRedBoxHandler", "()Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "sourceMapUrl", "", "getSourceMapUrl", "()Ljava/lang/String;", "sourceUrl", "getSourceUrl", "downloadedJSBundleFile", "getDownloadedJSBundleFile", "lastErrorTitle", "getLastErrorTitle", "lastErrorStack", "", "Lcom/facebook/react/devsupport/interfaces/StackFrame;", "getLastErrorStack", "()[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "lastErrorType", "Lcom/facebook/react/devsupport/interfaces/ErrorType;", "getLastErrorType", "()Lcom/facebook/react/devsupport/interfaces/ErrorType;", "lastErrorCookie", "", "getLastErrorCookie", "()I", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "currentReactContext", "Lcom/facebook/react/bridge/ReactContext;", "getCurrentReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "value", "", "devMenuEnabled", "getDevMenuEnabled", "()Z", "setDevMenuEnabled", "(Z)V", "shakeGestureEnabled", "getShakeGestureEnabled", "setShakeGestureEnabled", "keyboardShortcutsEnabled", "getKeyboardShortcutsEnabled", "setKeyboardShortcutsEnabled", "bundleFilePath", "getBundleFilePath", "setBundleFilePath", "(Ljava/lang/String;)V", "devSupportEnabled", "getDevSupportEnabled", "setDevSupportEnabled", "showNewJavaError", "", "message", "e", "", "addCustomDevOption", "optionName", "optionHandler", "Lcom/facebook/react/devsupport/interfaces/DevOptionHandler;", "createRootView", "Landroid/view/View;", "appKey", "destroyRootView", "rootView", "showNewJSError", "details", "Lcom/facebook/react/bridge/ReadableArray;", "errorCookie", "hideRedboxDialog", "showDevOptionsDialog", "startInspector", "stopInspector", "onNewReactContextCreated", "reactContext", "onReactInstanceDestroyed", "hasUpToDateJSBundleInCache", "reloadSettings", "handleReloadJS", "reloadJSFromServer", "bundleURL", "callback", "Lcom/facebook/react/devsupport/interfaces/BundleLoadCallback;", "isPackagerRunning", "Lcom/facebook/react/devsupport/interfaces/PackagerStatusCallback;", "setHotModuleReplacementEnabled", "isHotModuleReplacementEnabled", "setFpsDebugEnabled", "isFpsDebugEnabled", "toggleElementInspector", "downloadBundleResourceFromUrlSync", "Ljava/io/File;", "resourceURL", "outputFile", "registerErrorCustomizer", "errorCustomizer", "Lcom/facebook/react/devsupport/interfaces/ErrorCustomizer;", "processErrorCustomizers", "Landroid/util/Pair;", "errorInfo", "setPackagerLocationCustomizer", "packagerLocationCustomizer", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PackagerLocationCustomizer;", "createSurfaceDelegate", "Lcom/facebook/react/common/SurfaceDelegate;", "moduleName", "openDebugger", "panel", "showPausedInDebuggerOverlay", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PausedInDebuggerOverlayCommandListener;", "hidePausedInDebuggerOverlay", "setAdditionalOptionForPackager", "name", "PackagerLocationCustomizer", "PausedInDebuggerOverlayCommandListener", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DevSupportManager extends JSExceptionHandler {

    /* JADX INFO: compiled from: DevSupportManager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PackagerLocationCustomizer;", "", "run", "", "callback", "Ljava/lang/Runnable;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface PackagerLocationCustomizer {
        void run(Runnable callback);
    }

    /* JADX INFO: compiled from: DevSupportManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PausedInDebuggerOverlayCommandListener;", "", "onResume", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface PausedInDebuggerOverlayCommandListener {
        void onResume();
    }

    void addCustomDevOption(String optionName, DevOptionHandler optionHandler);

    View createRootView(String appKey);

    SurfaceDelegate createSurfaceDelegate(String moduleName);

    void destroyRootView(View rootView);

    File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile);

    default String getBundleFilePath() {
        return null;
    }

    Activity getCurrentActivity();

    ReactContext getCurrentReactContext();

    default boolean getDevMenuEnabled() {
        return true;
    }

    DeveloperSettings getDevSettings();

    boolean getDevSupportEnabled();

    String getDownloadedJSBundleFile();

    default boolean getKeyboardShortcutsEnabled() {
        return true;
    }

    int getLastErrorCookie();

    StackFrame[] getLastErrorStack();

    String getLastErrorTitle();

    ErrorType getLastErrorType();

    RedBoxHandler getRedBoxHandler();

    default boolean getShakeGestureEnabled() {
        return true;
    }

    String getSourceMapUrl();

    String getSourceUrl();

    void handleReloadJS();

    boolean hasUpToDateJSBundleInCache();

    void hidePausedInDebuggerOverlay();

    void hideRedboxDialog();

    void isPackagerRunning(PackagerStatusCallback callback);

    void onNewReactContextCreated(ReactContext reactContext);

    void onReactInstanceDestroyed(ReactContext reactContext);

    void openDebugger(String panel);

    Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> errorInfo);

    void registerErrorCustomizer(ErrorCustomizer errorCustomizer);

    void reloadJSFromServer(String bundleURL, BundleLoadCallback callback);

    void reloadSettings();

    void setAdditionalOptionForPackager(String name, String value);

    default void setBundleFilePath(String str) {
    }

    default void setDevMenuEnabled(boolean z) {
    }

    void setDevSupportEnabled(boolean z);

    void setFpsDebugEnabled(boolean isFpsDebugEnabled);

    void setHotModuleReplacementEnabled(boolean isHotModuleReplacementEnabled);

    default void setKeyboardShortcutsEnabled(boolean z) {
    }

    void setPackagerLocationCustomizer(PackagerLocationCustomizer packagerLocationCustomizer);

    default void setShakeGestureEnabled(boolean z) {
    }

    void showDevOptionsDialog();

    void showNewJSError(String message, ReadableArray details, int errorCookie);

    void showNewJavaError(String message, Throwable e);

    void showPausedInDebuggerOverlay(String message, PausedInDebuggerOverlayCommandListener listener);

    void startInspector();

    void stopInspector();

    void toggleElementInspector();

    static /* synthetic */ void openDebugger$default(DevSupportManager devSupportManager, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openDebugger");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        devSupportManager.openDebugger(str);
    }
}
