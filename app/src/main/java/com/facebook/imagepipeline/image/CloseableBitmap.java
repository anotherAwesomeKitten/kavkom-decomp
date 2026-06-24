package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public interface CloseableBitmap extends CloseableImage {
    Bitmap getUnderlyingBitmap();
}
