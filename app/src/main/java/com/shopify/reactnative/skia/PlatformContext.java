package com.shopify.reactnative.skia;

import android.os.Handler;
import android.os.Looper;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactContext;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class PlatformContext {
    private final ReactContext mContext;
    private final HybridData mHybridData;
    private final String TAG = "PlatformContext";
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private native HybridData initHybrid(float f);

    /* JADX INFO: Access modifiers changed from: private */
    public native void notifyTaskReadyNative();

    public PlatformContext(ReactContext reactContext) {
        this.mContext = reactContext;
        this.mHybridData = initHybrid(reactContext.getResources().getDisplayMetrics().density);
    }

    public Object createVideo(String str) {
        return new RNSkVideo(this.mContext, str);
    }

    private byte[] getStreamAsBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr, 0, 4096);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    Object takeScreenshotFromViewTag(int i) {
        return ViewScreenshotService.makeViewScreenshotFromTag(this.mContext, i);
    }

    public byte[] getJniStreamFromSource(String str) throws IOException {
        int identifier = this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName());
        if (identifier == 0) {
            identifier = this.mContext.getResources().getIdentifier(str, "raw", this.mContext.getPackageName());
        }
        if (identifier != 0) {
            return getStreamAsBytes(this.mContext.getResources().openRawResource(identifier));
        }
        try {
            URI uri = new URI(str);
            if (uri.getScheme() == null) {
                throw new Exception("Invalid URI scheme");
            }
            URL url = uri.toURL();
            url.openConnection().connect();
            return getStreamAsBytes(new BufferedInputStream(url.openStream(), 8192));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (URISyntaxException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    protected void finalize() throws Throwable {
        this.mHybridData.resetNative();
        super.finalize();
    }

    public void raise(final String str) {
        this.mainHandler.post(new Runnable() { // from class: com.shopify.reactnative.skia.PlatformContext.1
            @Override // java.lang.Runnable
            public void run() {
                PlatformContext.this.mContext.handleException(new Exception(str));
            }
        });
    }

    public void notifyTaskReady() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            notifyTaskReadyNative();
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.shopify.reactnative.skia.PlatformContext$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.notifyTaskReadyNative();
                }
            });
        }
    }
}
