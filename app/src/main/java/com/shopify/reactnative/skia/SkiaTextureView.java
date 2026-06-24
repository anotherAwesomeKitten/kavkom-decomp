package com.shopify.reactnative.skia;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.TextureView;

/* JADX INFO: loaded from: classes2.dex */
public class SkiaTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    private long _prevTimestamp;
    SkiaViewAPI mApi;
    boolean mDebug;
    private String tag;

    public SkiaTextureView(Context context, SkiaViewAPI skiaViewAPI, boolean z) {
        super(context);
        this.tag = "SkiaTextureView";
        this._prevTimestamp = 0L;
        this.mApi = skiaViewAPI;
        this.mDebug = z;
        setOpaque(false);
        setSurfaceTextureListener(this);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.i(this.tag, "onSurfaceTextureAvailable:  " + i + "x" + i2);
        this.mApi.onSurfaceTextureCreated(surfaceTexture, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.i(this.tag, "onSurfaceTextureSizeChanged:  " + i + "x" + i2);
        this.mApi.onSurfaceTextureChanged(surfaceTexture, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mApi.onSurfaceDestroyed();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        if (this.mDebug) {
            long timestamp = surfaceTexture.getTimestamp();
            Log.i("SkiaTextureView", "onSurfaceTextureUpdated " + ((timestamp - this._prevTimestamp) / 1000000) + "ms");
            this._prevTimestamp = timestamp;
        }
    }
}
