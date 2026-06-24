package com.swmansion.rnscreens;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenStackHeaderHeightUpdateProxy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStackHeaderHeightUpdateProxy;", "", "<init>", "()V", "previousHeaderHeightInPx", "", "getPreviousHeaderHeightInPx", "()Ljava/lang/Integer;", "setPreviousHeaderHeightInPx", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "updateHeaderHeightIfNeeded", "", "config", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "screen", "Lcom/swmansion/rnscreens/Screen;", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ScreenStackHeaderHeightUpdateProxy {
    private Integer previousHeaderHeightInPx;

    public final Integer getPreviousHeaderHeightInPx() {
        return this.previousHeaderHeightInPx;
    }

    public final void setPreviousHeaderHeightInPx(Integer num) {
        this.previousHeaderHeightInPx = num;
    }

    public final void updateHeaderHeightIfNeeded(ScreenStackHeaderConfig config, Screen screen) {
        Intrinsics.checkNotNullParameter(config, "config");
        int height = config.getIsHeaderHidden() ? 0 : config.getToolbar().getHeight();
        Integer num = this.previousHeaderHeightInPx;
        if (num != null && height == num.intValue()) {
            return;
        }
        this.previousHeaderHeightInPx = Integer.valueOf(height);
        if (screen != null) {
            screen.notifyHeaderHeightChange$react_native_screens_release(height);
        }
    }
}
