package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: YogaMeasureOutput.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0007J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0005H\u0007J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0005H\u0007¨\u0006\r"}, d2 = {"Lcom/facebook/yoga/YogaMeasureOutput;", "", "<init>", "()V", "make", "", "width", "", "height", "", "getWidth", "measureOutput", "getHeight", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaMeasureOutput {
    public static final YogaMeasureOutput INSTANCE = new YogaMeasureOutput();

    private YogaMeasureOutput() {
    }

    @JvmStatic
    public static final long make(float width, float height) {
        return ((long) Float.floatToRawIntBits(height)) | (((long) Float.floatToRawIntBits(width)) << 32);
    }

    @JvmStatic
    public static final long make(int width, int height) {
        return make(width, height);
    }

    @JvmStatic
    public static final float getWidth(long measureOutput) {
        return Float.intBitsToFloat((int) ((measureOutput >> 32) & 4294967295L));
    }

    @JvmStatic
    public static final float getHeight(long measureOutput) {
        return Float.intBitsToFloat((int) (measureOutput & 4294967295L));
    }
}
