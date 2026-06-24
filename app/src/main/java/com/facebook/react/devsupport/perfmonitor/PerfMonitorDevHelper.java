package com.facebook.react.devsupport.perfmonitor;

import android.app.Activity;
import kotlin.Metadata;

/* JADX INFO: compiled from: PerfMonitorDevHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorDevHelper;", "", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "inspectorTarget", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorInspectorTarget;", "getInspectorTarget", "()Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorInspectorTarget;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface PerfMonitorDevHelper {
    Activity getCurrentActivity();

    PerfMonitorInspectorTarget getInspectorTarget();
}
