package com.shopify.reactnative.skia;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import com.facebook.react.views.view.ReactViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SkiaBaseView extends ReactViewGroup implements SkiaViewAPI {
    private final boolean debug;
    private View mView;
    private final String tag;

    protected abstract int[] getBitmap(int i, int i2);

    protected abstract void registerView(int i);

    protected abstract void setDebugMode(boolean z);

    protected abstract void surfaceAvailable(Object obj, int i, int i2, boolean z);

    protected abstract void surfaceDestroyed();

    protected abstract void surfaceSizeChanged(Object obj, int i, int i2, boolean z);

    protected abstract void unregisterView();

    public SkiaBaseView(Context context) {
        super(context);
        this.debug = false;
        this.tag = "SkiaView";
        SkiaTextureView skiaTextureView = new SkiaTextureView(context, this, false);
        this.mView = skiaTextureView;
        addView(skiaTextureView);
    }

    public void setOpaque(boolean z) {
        if (z) {
            View view = this.mView;
            if (view instanceof SkiaTextureView) {
                removeView(view);
                SkiaSurfaceView skiaSurfaceView = new SkiaSurfaceView(getContext(), this, false);
                this.mView = skiaSurfaceView;
                addView(skiaSurfaceView);
                return;
            }
        }
        if (z) {
            return;
        }
        View view2 = this.mView;
        if (view2 instanceof SkiaSurfaceView) {
            removeView(view2);
            SkiaTextureView skiaTextureView = new SkiaTextureView(getContext(), this, false);
            this.mView = skiaTextureView;
            addView(skiaTextureView);
        }
    }

    void dropInstance() {
        unregisterView();
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mView.layout(0, 0, i3 - i, i4 - i2);
    }

    @Override // com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceCreated(Surface surface, int i, int i2) {
        surfaceAvailable(surface, i, i2, true);
    }

    @Override // com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceChanged(Surface surface, int i, int i2) {
        Log.i("SkiaView", "onSurfaceTextureSizeChanged " + i + "/" + i2);
        surfaceSizeChanged(surface, i, i2, true);
    }

    @Override // com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceTextureCreated(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceAvailable(surfaceTexture, i, i2, false);
    }

    @Override // com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceTextureChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.i("SkiaView", "onSurfaceTextureSizeChanged " + i + "/" + i2);
        surfaceSizeChanged(surfaceTexture, i, i2, false);
    }

    @Override // com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceDestroyed() {
        surfaceDestroyed();
    }
}
