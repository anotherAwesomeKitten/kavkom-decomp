package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YogaNodeFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/yoga/YogaNodeFactory;", "", "<init>", "()V", "create", "Lcom/facebook/yoga/YogaNode;", "config", "Lcom/facebook/yoga/YogaConfig;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaNodeFactory {
    public static final YogaNodeFactory INSTANCE = new YogaNodeFactory();

    private YogaNodeFactory() {
    }

    @JvmStatic
    public static final YogaNode create() {
        return new YogaNodeJNIFinalizer();
    }

    @JvmStatic
    public static final YogaNode create(YogaConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return new YogaNodeJNIFinalizer(config);
    }
}
