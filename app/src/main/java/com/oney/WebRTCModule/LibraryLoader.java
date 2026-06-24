package com.oney.WebRTCModule;

import org.webrtc.Logging;
import org.webrtc.NativeLibraryLoader;

/* JADX INFO: loaded from: classes2.dex */
public class LibraryLoader implements NativeLibraryLoader {
    private static String TAG = "LibraryLoader";

    @Override // org.webrtc.NativeLibraryLoader
    public boolean load(String str) {
        Logging.d(TAG, "Loading library: " + str);
        System.loadLibrary(str);
        return true;
    }
}
