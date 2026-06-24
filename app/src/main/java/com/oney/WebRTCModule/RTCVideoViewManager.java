package com.oney.WebRTCModule;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RTCVideoViewManager extends SimpleViewManager<WebRTCView> {
    private static final String REACT_CLASS = "RTCVideoView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public WebRTCView createViewInstance(ThemedReactContext themedReactContext) {
        return new WebRTCView(themedReactContext);
    }

    @ReactProp(name = "mirror")
    public void setMirror(WebRTCView webRTCView, boolean z) {
        webRTCView.setMirror(z);
    }

    @ReactProp(name = "objectFit")
    public void setObjectFit(WebRTCView webRTCView, String str) {
        webRTCView.setObjectFit(str);
    }

    @ReactProp(name = "streamURL")
    public void setStreamURL(WebRTCView webRTCView, String str) {
        webRTCView.setStreamURL(str);
    }

    @ReactProp(name = "zOrder")
    public void setZOrder(WebRTCView webRTCView, int i) {
        webRTCView.setZOrder(i);
    }

    @ReactProp(name = "onDimensionsChange")
    public void setOnDimensionsChange(WebRTCView webRTCView, boolean z) {
        webRTCView.setOnDimensionsChange(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        map2.put("registrationName", "onDimensionsChange");
        map.put("onDimensionsChange", map2);
        return map;
    }
}
