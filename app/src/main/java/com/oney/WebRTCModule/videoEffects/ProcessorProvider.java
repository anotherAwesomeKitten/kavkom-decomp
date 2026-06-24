package com.oney.WebRTCModule.videoEffects;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessorProvider {
    private static Map<String, VideoFrameProcessorFactoryInterface> methodMap = new HashMap();

    public static VideoFrameProcessor getProcessor(String str) {
        if (methodMap.containsKey(str)) {
            return methodMap.get(str).build();
        }
        return null;
    }

    public static void addProcessor(String str, VideoFrameProcessorFactoryInterface videoFrameProcessorFactoryInterface) {
        if (str != null && videoFrameProcessorFactoryInterface != null) {
            methodMap.put(str, videoFrameProcessorFactoryInterface);
            return;
        }
        throw new NullPointerException("Name or VideoFrameProcessorFactry can not be null");
    }

    public static void removeProcessor(String str) {
        if (str != null && methodMap.containsKey(str)) {
            methodMap.remove(str);
            return;
        }
        throw new RuntimeException("VideoFrameProcessorFactry with " + str + " does not exist");
    }
}
