package com.facebook.crypto.util;

import com.facebook.crypto.exception.CryptoInitializationException;

/* JADX INFO: loaded from: classes.dex */
public interface NativeCryptoLibrary {
    void ensureCryptoLoaded() throws CryptoInitializationException;
}
