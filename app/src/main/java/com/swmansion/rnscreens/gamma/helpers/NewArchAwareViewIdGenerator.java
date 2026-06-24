package com.swmansion.rnscreens.gamma.helpers;

import kotlin.Metadata;

/* JADX INFO: compiled from: ViewIdHelpers.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/swmansion/rnscreens/gamma/helpers/NewArchAwareViewIdGenerator;", "Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;", "<init>", "()V", "nextId", "", "generateViewId", "progressViewId", "", "isValidReactRootTag", "", "tag", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class NewArchAwareViewIdGenerator implements ViewIdProviding {
    private int nextId = 3;

    @Override // com.swmansion.rnscreens.gamma.helpers.ViewIdProviding
    public int generateViewId() {
        int i = this.nextId;
        progressViewId();
        return i;
    }

    private final void progressViewId() {
        int i = this.nextId + 2;
        this.nextId = i;
        if (isValidReactRootTag(i)) {
            this.nextId += 2;
        }
    }

    private final boolean isValidReactRootTag(int tag) {
        return tag % 10 == 1;
    }
}
