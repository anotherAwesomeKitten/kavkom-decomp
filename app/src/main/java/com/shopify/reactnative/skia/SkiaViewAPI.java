package com.shopify.reactnative.skia;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/* JADX INFO: loaded from: classes2.dex */
public interface SkiaViewAPI {
    void onSurfaceChanged(Surface surface, int i, int i2);

    void onSurfaceCreated(Surface surface, int i, int i2);

    void onSurfaceDestroyed();

    void onSurfaceTextureChanged(SurfaceTexture surfaceTexture, int i, int i2);

    void onSurfaceTextureCreated(SurfaceTexture surfaceTexture, int i, int i2);
}
