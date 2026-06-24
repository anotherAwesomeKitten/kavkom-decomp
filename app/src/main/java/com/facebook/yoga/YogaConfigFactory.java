package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: YogaConfigFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Lcom/facebook/yoga/YogaConfigFactory;", "", "<init>", "()V", "create", "Lcom/facebook/yoga/YogaConfig;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaConfigFactory {
    public static final YogaConfigFactory INSTANCE = new YogaConfigFactory();

    private YogaConfigFactory() {
    }

    @JvmStatic
    public static final YogaConfig create() {
        return new YogaConfigJNIFinalizer();
    }
}
