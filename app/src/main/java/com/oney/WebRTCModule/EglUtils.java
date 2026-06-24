package com.oney.WebRTCModule;

import android.util.Log;
import org.webrtc.EglBase;

/* JADX INFO: loaded from: classes2.dex */
public class EglUtils {
    private static EglBase rootEglBase;

    public static synchronized EglBase getRootEglBase() {
        if (rootEglBase == null) {
            int[] iArr = EglBase.CONFIG_PLAIN;
            EglBase eglBaseCreateEgl10 = null;
            try {
                e = null;
                eglBaseCreateEgl10 = EglBase.createEgl14(iArr);
            } catch (RuntimeException e) {
                e = e;
            }
            if (eglBaseCreateEgl10 == null) {
                try {
                    eglBaseCreateEgl10 = EglBase.createEgl10(iArr);
                } catch (RuntimeException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                Log.e(EglUtils.class.getName(), "Failed to create EglBase", e);
            } else {
                rootEglBase = eglBaseCreateEgl10;
            }
        }
        return rootEglBase;
    }

    public static EglBase.Context getRootEglBaseContext() {
        EglBase rootEglBase2 = getRootEglBase();
        if (rootEglBase2 == null) {
            return null;
        }
        return rootEglBase2.getEglBaseContext();
    }
}
