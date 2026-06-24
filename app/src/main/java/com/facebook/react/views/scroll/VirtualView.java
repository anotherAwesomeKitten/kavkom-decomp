package com.facebook.react.views.scroll;

import android.graphics.Rect;
import com.facebook.react.views.virtual.VirtualViewMode;
import kotlin.Metadata;

/* JADX INFO: compiled from: VirtualViewContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/VirtualView;", "", "virtualViewID", "", "getVirtualViewID", "()Ljava/lang/String;", "containerRelativeRect", "Landroid/graphics/Rect;", "getContainerRelativeRect", "()Landroid/graphics/Rect;", "onModeChange", "", "newMode", "Lcom/facebook/react/views/virtual/VirtualViewMode;", "thresholdRect", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface VirtualView {
    Rect getContainerRelativeRect();

    String getVirtualViewID();

    void onModeChange(VirtualViewMode newMode, Rect thresholdRect);
}
