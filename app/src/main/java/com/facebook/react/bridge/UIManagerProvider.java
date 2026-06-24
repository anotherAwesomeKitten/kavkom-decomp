package com.facebook.react.bridge;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: compiled from: UIManagerProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "This class is part of Legacy Architecture and will be removed in a future release")
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bç\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/bridge/UIManagerProvider;", "", "createUIManager", "Lcom/facebook/react/bridge/UIManager;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface UIManagerProvider {
    UIManager createUIManager(ReactApplicationContext context);
}
