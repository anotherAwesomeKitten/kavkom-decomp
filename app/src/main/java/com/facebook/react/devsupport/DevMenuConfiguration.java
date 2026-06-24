package com.facebook.react.devsupport;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DevMenuConfiguration.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/facebook/react/devsupport/DevMenuConfiguration;", "", "devMenuEnabled", "", "shakeGestureEnabled", "keyboardShortcutsEnabled", "<init>", "(ZZZ)V", "getDevMenuEnabled", "()Z", "getShakeGestureEnabled", "getKeyboardShortcutsEnabled", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DevMenuConfiguration {
    private final boolean devMenuEnabled;
    private final boolean keyboardShortcutsEnabled;
    private final boolean shakeGestureEnabled;

    public DevMenuConfiguration() {
        this(false, false, false, 7, null);
    }

    public DevMenuConfiguration(boolean z, boolean z2, boolean z3) {
        this.devMenuEnabled = z;
        this.shakeGestureEnabled = z2;
        this.keyboardShortcutsEnabled = z3;
    }

    public /* synthetic */ DevMenuConfiguration(boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3);
    }

    public final boolean getDevMenuEnabled() {
        return this.devMenuEnabled;
    }

    public final boolean getShakeGestureEnabled() {
        return this.shakeGestureEnabled;
    }

    public final boolean getKeyboardShortcutsEnabled() {
        return this.keyboardShortcutsEnabled;
    }
}
