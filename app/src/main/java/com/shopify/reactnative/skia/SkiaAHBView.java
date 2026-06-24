package com.shopify.reactnative.skia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class SkiaAHBView extends View implements ImageReader.OnImageAvailableListener {
    private long _prevTimestamp;
    SkiaViewAPI mApi;
    private Bitmap mBitmap;
    boolean mDebug;
    private ImageReader mReader;
    private final Matrix matrix;

    public SkiaAHBView(Context context, SkiaViewAPI skiaViewAPI, boolean z) {
        super(context);
        this.mBitmap = null;
        this.matrix = new Matrix();
        this._prevTimestamp = 0L;
        this.mApi = skiaViewAPI;
        this.mDebug = z;
    }

    private ImageReader createReader() {
        ImageReader imageReaderNewInstance = ImageReader.newInstance(getWidth(), getHeight(), 1, 2, 768L);
        imageReaderNewInstance.setOnImageAvailableListener(this, null);
        return imageReaderNewInstance;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int width = getWidth();
        int height = getHeight();
        if (this.mReader == null) {
            ImageReader imageReaderCreateReader = createReader();
            this.mReader = imageReaderCreateReader;
            this.mApi.onSurfaceCreated(imageReaderCreateReader.getSurface(), width, height);
        } else {
            ImageReader imageReaderCreateReader2 = createReader();
            this.mReader = imageReaderCreateReader2;
            this.mApi.onSurfaceChanged(imageReaderCreateReader2.getSurface(), width, height);
        }
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public void onImageAvailable(ImageReader imageReader) {
        Bitmap bitmapWrapHardwareBuffer;
        Image imageAcquireLatestImage = imageReader.acquireLatestImage();
        if (imageAcquireLatestImage != null) {
            try {
                HardwareBuffer hardwareBuffer = imageAcquireLatestImage.getHardwareBuffer();
                if (this.mDebug) {
                    textureUpdated(imageAcquireLatestImage.getTimestamp());
                }
                if (hardwareBuffer != null && (bitmapWrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer, null)) != null) {
                    this.mBitmap = bitmapWrapHardwareBuffer;
                    hardwareBuffer.close();
                    invalidate();
                }
            } catch (Throwable th) {
                if (imageAcquireLatestImage != null) {
                    try {
                        imageAcquireLatestImage.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (imageAcquireLatestImage != null) {
            imageAcquireLatestImage.close();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBitmap != null) {
            float width = getWidth();
            float height = getHeight() / this.mBitmap.getHeight();
            this.matrix.reset();
            this.matrix.setScale(width / this.mBitmap.getWidth(), height);
            canvas.drawBitmap(this.mBitmap, this.matrix, null);
        }
    }

    public void textureUpdated(long j) {
        Log.i("SkiaAHBView", "onSurfaceTextureUpdated " + ((j - this._prevTimestamp) / 1000000) + "ms");
        this._prevTimestamp = j;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mApi.onSurfaceDestroyed();
    }
}
