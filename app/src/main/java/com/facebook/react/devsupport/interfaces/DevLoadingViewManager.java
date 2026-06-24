package com.facebook.react.devsupport.interfaces;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* JADX INFO: compiled from: DevLoadingViewManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\tJ+\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;", "", "showMessage", "", "message", "", "color", "", ViewProps.BACKGROUND_COLOR, "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)V", "updateProgress", "status", "done", "", "total", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "hide", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DevLoadingViewManager {
    void hide();

    void showMessage(String message);

    void showMessage(String message, Double color, Double backgroundColor);

    void updateProgress(String status, Integer done, Integer total);
}
