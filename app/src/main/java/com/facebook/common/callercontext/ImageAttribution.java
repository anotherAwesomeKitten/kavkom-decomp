package com.facebook.common.callercontext;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface ImageAttribution {
    String getCallingClassName();

    @Nullable
    ContextChain getContextChain();
}
