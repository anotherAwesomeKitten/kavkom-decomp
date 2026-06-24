package com.oney.WebRTCModule;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

/* JADX INFO: loaded from: classes2.dex */
public class ReactBridgeUtil {
    public static String getMapStrValue(ReadableMap readableMap, String str) {
        if (!readableMap.hasKey(str)) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(str).ordinal()];
        if (i == 1) {
            return String.valueOf(readableMap.getBoolean(str));
        }
        if (i == 2) {
            return String.valueOf(readableMap.getDouble(str));
        }
        if (i != 3) {
            return null;
        }
        return readableMap.getString(str);
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.ReactBridgeUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Boolean.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
