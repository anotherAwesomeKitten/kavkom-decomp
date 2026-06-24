package io.sentry.react;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import com.facebook.react.bridge.Promise;
import io.sentry.SentryDateProvider;

/* JADX INFO: loaded from: classes3.dex */
public class RNSentryTimeToDisplay {
    public static void GetTimeToDisplay(final Promise promise, final SentryDateProvider sentryDateProvider) {
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper == null) {
            promise.reject("GetTimeToDisplay is not able to measure the time to display: Main looper not available.");
        } else {
            new Handler(mainLooper).post(new Runnable() { // from class: io.sentry.react.RNSentryTimeToDisplay$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RNSentryTimeToDisplay.lambda$GetTimeToDisplay$1(sentryDateProvider, promise);
                }
            });
        }
    }

    static /* synthetic */ void lambda$GetTimeToDisplay$1(final SentryDateProvider sentryDateProvider, final Promise promise) {
        try {
            Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: io.sentry.react.RNSentryTimeToDisplay$$ExternalSyntheticLambda0
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j) {
                    SentryDateProvider sentryDateProvider2 = sentryDateProvider;
                    promise.resolve(Double.valueOf(sentryDateProvider2.now().nanoTimestamp() / 1.0E9d));
                }
            });
        } catch (Exception e) {
            promise.reject("Failed to receive the instance of Choreographer", e);
        }
    }
}
