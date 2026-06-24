package com.shopify.reactnative.skia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactContext;

/* JADX INFO: loaded from: classes2.dex */
public class SkiaPictureView extends SkiaBaseView {
    private boolean androidWarmup;
    private HybridData mHybridData;
    private Paint paint;

    private native HybridData initHybrid(SkiaManager skiaManager);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native int[] getBitmap(int i, int i2);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void registerView(int i);

    protected native void setBgColor(int i);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void setDebugMode(boolean z);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void surfaceAvailable(Object obj, int i, int i2, boolean z);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void surfaceDestroyed();

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void surfaceSizeChanged(Object obj, int i, int i2, boolean z);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void unregisterView();

    public SkiaPictureView(Context context) {
        super(context);
        this.paint = new Paint();
        this.androidWarmup = false;
        this.mHybridData = initHybrid(((RNSkiaModule) ((ReactContext) context).getNativeModule(RNSkiaModule.class)).getSkiaManager());
    }

    public void setAndroidWarmup(boolean z) {
        this.androidWarmup = z;
        setWillNotDraw(!z);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.mHybridData.resetNative();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int[] bitmap;
        super.onDraw(canvas);
        if (this.androidWarmup) {
            int width = getWidth();
            int height = getHeight();
            if (width <= 0 || height <= 0 || (bitmap = getBitmap(width, height)) == null || bitmap.length != width * height) {
                return;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, width, height, Bitmap.Config.ARGB_8888);
            this.paint.setFilterBitmap(true);
            canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, this.paint);
        }
    }

    @Override // com.shopify.reactnative.skia.SkiaBaseView, com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceTextureCreated(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureCreated(surfaceTexture, i, i2);
    }

    @Override // com.shopify.reactnative.skia.SkiaBaseView, com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceTextureChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureChanged(surfaceTexture, i, i2);
    }

    @Override // com.shopify.reactnative.skia.SkiaBaseView, com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceCreated(Surface surface, int i, int i2) {
        super.onSurfaceCreated(surface, i, i2);
    }

    @Override // com.shopify.reactnative.skia.SkiaBaseView, com.shopify.reactnative.skia.SkiaViewAPI
    public void onSurfaceChanged(Surface surface, int i, int i2) {
        super.onSurfaceChanged(surface, i, i2);
    }
}
