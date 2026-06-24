package com.facebook.react;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: ReactApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u00020\u00038VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/ReactApplication;", "", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "getReactNativeHost$annotations", "()V", "getReactNativeHost", "()Lcom/facebook/react/ReactNativeHost;", "reactHost", "Lcom/facebook/react/ReactHost;", "getReactHost", "()Lcom/facebook/react/ReactHost;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ReactApplication {
    @Deprecated(message = "You should not use ReactNativeHost directly in the New Architecture. Use ReactHost instead.", replaceWith = @ReplaceWith(expression = "reactHost", imports = {}))
    static /* synthetic */ void getReactNativeHost$annotations() {
    }

    default ReactHost getReactHost() {
        return null;
    }

    default ReactNativeHost getReactNativeHost() {
        throw new RuntimeException("You should not use ReactNativeHost directly in the New Architecture");
    }
}
