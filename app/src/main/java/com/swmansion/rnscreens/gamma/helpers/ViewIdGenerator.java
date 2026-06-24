package com.swmansion.rnscreens.gamma.helpers;

import kotlin.Metadata;

/* JADX INFO: compiled from: ViewIdHelpers.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/swmansion/rnscreens/gamma/helpers/ViewIdGenerator;", "Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;", "<init>", "()V", "externalGenerator", "getExternalGenerator", "()Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;", "setExternalGenerator", "(Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;)V", "defaultGenerator", "generateViewId", "", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ViewIdGenerator implements ViewIdProviding {
    public static final ViewIdGenerator INSTANCE = new ViewIdGenerator();
    private static final ViewIdProviding defaultGenerator = new NewArchAwareViewIdGenerator();
    private static ViewIdProviding externalGenerator;

    private ViewIdGenerator() {
    }

    public final ViewIdProviding getExternalGenerator() {
        return externalGenerator;
    }

    public final void setExternalGenerator(ViewIdProviding viewIdProviding) {
        externalGenerator = viewIdProviding;
    }

    @Override // com.swmansion.rnscreens.gamma.helpers.ViewIdProviding
    public int generateViewId() {
        ViewIdProviding viewIdProviding = externalGenerator;
        return viewIdProviding != null ? viewIdProviding.generateViewId() : defaultGenerator.generateViewId();
    }
}
