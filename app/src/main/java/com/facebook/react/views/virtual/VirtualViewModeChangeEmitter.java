package com.facebook.react.views.virtual;

import android.graphics.Rect;
import kotlin.Metadata;

/* JADX INFO: compiled from: VirtualViewModeChangeEmitter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/virtual/VirtualViewModeChangeEmitter;", "", "emitModeChange", "", "mode", "Lcom/facebook/react/views/virtual/VirtualViewMode;", "targetRect", "Landroid/graphics/Rect;", "thresholdRect", "synchronous", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface VirtualViewModeChangeEmitter {
    void emitModeChange(VirtualViewMode mode, Rect targetRect, Rect thresholdRect, boolean synchronous);
}
