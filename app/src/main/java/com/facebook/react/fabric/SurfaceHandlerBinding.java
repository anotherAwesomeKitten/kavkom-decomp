package com.facebook.react.fabric;

import com.facebook.jni.HybridClassBase;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SurfaceHandlerBinding.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u0000 *2\u00020\u0001:\u0001*B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0082 J\t\u0010\u0011\u001a\u00020\tH\u0082 J\t\u0010\u0012\u001a\u00020\u0003H\u0082 J\t\u0010\u0013\u001a\u00020\rH\u0082 JF\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cJY\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0082 J\u0013\u0010#\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010%H\u0086 J\u000e\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\rJ\u0011\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\tH\u0082 R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006+"}, d2 = {"Lcom/facebook/react/fabric/SurfaceHandlerBinding;", "Lcom/facebook/jni/HybridClassBase;", "moduleName", "", "<init>", "(Ljava/lang/String;)V", "initHybrid", "", "surfaceId", "", "getSurfaceId", "()I", "isRunning", "", "()Z", "getModuleName", "()Ljava/lang/String;", "_getSurfaceId", "_getModuleName", "_isRunning", "setLayoutConstraints", "widthMeasureSpec", "heightMeasureSpec", "offsetX", "offsetY", "doLeftAndRightSwapInRTL", "isRTL", "pixelDensity", "", "fontScale", "setLayoutConstraintsNative", ViewProps.MIN_WIDTH, ViewProps.MAX_WIDTH, ViewProps.MIN_HEIGHT, ViewProps.MAX_HEIGHT, "setProps", "props", "Lcom/facebook/react/bridge/NativeMap;", "setMountable", "mountable", "setDisplayMode", "mode", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class SurfaceHandlerBinding extends HybridClassBase {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int DISPLAY_MODE_HIDDEN = 2;

    @Deprecated
    public static final int DISPLAY_MODE_SUSPENDED = 1;

    @Deprecated
    public static final int DISPLAY_MODE_VISIBLE = 0;
    private static final int NO_SURFACE_ID = 0;

    private final native String _getModuleName();

    private final native int _getSurfaceId();

    private final native boolean _isRunning();

    private final native void initHybrid(int surfaceId, String moduleName);

    private final native void setDisplayMode(int mode);

    private final native void setLayoutConstraintsNative(float minWidth, float maxWidth, float minHeight, float maxHeight, float offsetX, float offsetY, boolean doLeftAndRightSwapInRTL, boolean isRTL, float pixelDensity, float fontScale);

    public final native void setProps(NativeMap props);

    public SurfaceHandlerBinding(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        initHybrid(0, moduleName);
    }

    public final int getSurfaceId() {
        return _getSurfaceId();
    }

    public final boolean isRunning() {
        return _isRunning();
    }

    public final String getModuleName() {
        return _getModuleName();
    }

    public final void setLayoutConstraints(int widthMeasureSpec, int heightMeasureSpec, int offsetX, int offsetY, boolean doLeftAndRightSwapInRTL, boolean isRTL, float pixelDensity, float fontScale) {
        setLayoutConstraintsNative(LayoutMetricsConversions.INSTANCE.getMinSize(widthMeasureSpec) / pixelDensity, LayoutMetricsConversions.INSTANCE.getMaxSize(widthMeasureSpec) / pixelDensity, LayoutMetricsConversions.INSTANCE.getMinSize(heightMeasureSpec) / pixelDensity, LayoutMetricsConversions.INSTANCE.getMaxSize(heightMeasureSpec) / pixelDensity, offsetX / pixelDensity, offsetY / pixelDensity, doLeftAndRightSwapInRTL, isRTL, pixelDensity, fontScale);
    }

    public final void setMountable(boolean mountable) {
        setDisplayMode(!mountable ? 1 : 0);
    }

    /* JADX INFO: compiled from: SurfaceHandlerBinding.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/react/fabric/SurfaceHandlerBinding$Companion;", "", "<init>", "()V", "NO_SURFACE_ID", "", "DISPLAY_MODE_VISIBLE", "DISPLAY_MODE_SUSPENDED", "DISPLAY_MODE_HIDDEN", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        FabricSoLoader.staticInit();
    }
}
