package com.facebook.react.fabric;

import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridClassBase;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.uimanager.ReferenceStateWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StateWrapperImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0006H\u0082 J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0082 J\u000b\u0010\t\u001a\u0004\u0018\u00010\nH\u0082 J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0082 J\u0011\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0086 J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcom/facebook/react/fabric/StateWrapperImpl;", "Lcom/facebook/jni/HybridClassBase;", "Lcom/facebook/react/uimanager/ReferenceStateWrapper;", "<init>", "()V", "initHybrid", "", "getStateDataImpl", "Lcom/facebook/react/bridge/ReadableNativeMap;", "getStateMapBufferDataImpl", "Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;", "getStateDataReferenceImpl", "", "updateStateImpl", "map", "Lcom/facebook/react/bridge/NativeMap;", "stateDataMapBuffer", "getStateDataMapBuffer", "()Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;", "stateData", "getStateData", "()Lcom/facebook/react/bridge/ReadableNativeMap;", "stateDataReference", "getStateDataReference", "()Ljava/lang/Object;", "updateState", "Lcom/facebook/react/bridge/WritableMap;", "destroyState", "toString", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class StateWrapperImpl extends HybridClassBase implements ReferenceStateWrapper {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "StateWrapperImpl";

    private final native ReadableNativeMap getStateDataImpl();

    private final native Object getStateDataReferenceImpl();

    private final native ReadableMapBuffer getStateMapBufferDataImpl();

    private final native void initHybrid();

    public final native void updateStateImpl(NativeMap map);

    private StateWrapperImpl() {
        initHybrid();
    }

    @Override // com.facebook.react.uimanager.StateWrapper
    public ReadableMapBuffer getStateDataMapBuffer() {
        if (!isValid()) {
            FLog.e(TAG, "Race between StateWrapperImpl destruction and getState");
            return null;
        }
        return getStateMapBufferDataImpl();
    }

    @Override // com.facebook.react.uimanager.StateWrapper
    public ReadableNativeMap getStateData() {
        if (!isValid()) {
            FLog.e(TAG, "Race between StateWrapperImpl destruction and getState");
            return null;
        }
        return getStateDataImpl();
    }

    @Override // com.facebook.react.uimanager.ReferenceStateWrapper
    public Object getStateDataReference() {
        if (!isValid()) {
            FLog.e(TAG, "Race between StateWrapperImpl destruction and getState");
            return null;
        }
        return getStateDataReferenceImpl();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.StateWrapper
    public void updateState(WritableMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        if (!isValid()) {
            FLog.e(TAG, "Race between StateWrapperImpl destruction and updateState");
        } else {
            updateStateImpl((NativeMap) map);
        }
    }

    @Override // com.facebook.react.uimanager.StateWrapper
    public void destroyState() {
        if (isValid()) {
            resetNative();
        }
    }

    public String toString() {
        String string;
        if (!isValid()) {
            return "<destroyed>";
        }
        ReadableMapBuffer stateMapBufferDataImpl = getStateMapBufferDataImpl();
        if (stateMapBufferDataImpl != null) {
            return stateMapBufferDataImpl.toString();
        }
        ReadableNativeMap stateDataImpl = getStateDataImpl();
        return (stateDataImpl == null || (string = stateDataImpl.toString()) == null) ? "<unexpected null: stateDataImpl>" : string;
    }

    /* JADX INFO: compiled from: StateWrapperImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/fabric/StateWrapperImpl$Companion;", "", "<init>", "()V", "TAG", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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
