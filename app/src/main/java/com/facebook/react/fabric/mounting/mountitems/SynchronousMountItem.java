package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.MountingManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SynchronousMountItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/fabric/mounting/mountitems/SynchronousMountItem;", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "reactTag", "", "props", "Lcom/facebook/react/bridge/ReadableMap;", "<init>", "(ILcom/facebook/react/bridge/ReadableMap;)V", "execute", "", "mountingManager", "Lcom/facebook/react/fabric/mounting/MountingManager;", "toString", "", "getSurfaceId", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SynchronousMountItem implements MountItem {
    private final ReadableMap props;
    private final int reactTag;

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return -1;
    }

    public SynchronousMountItem(int i, ReadableMap props) {
        Intrinsics.checkNotNullParameter(props, "props");
        this.reactTag = i;
        this.props = props;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        try {
            mountingManager.storeSynchronousMountPropsOverride(this.reactTag, this.props);
            mountingManager.updatePropsSynchronously(this.reactTag, this.props);
        } catch (Exception unused) {
        }
    }

    public String toString() {
        String string = FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT ? this.props.toHashMap().toString() : "<hidden>";
        Intrinsics.checkNotNull(string);
        return "SYNC UPDATE PROPS [" + this.reactTag + "]: " + string;
    }
}
