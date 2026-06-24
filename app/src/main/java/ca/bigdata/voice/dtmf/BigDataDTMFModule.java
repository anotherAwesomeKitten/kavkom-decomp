package ca.bigdata.voice.dtmf;

import android.media.ToneGenerator;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BigDataDTMFModule extends ReactContextBaseJavaModule {
    private ToneGenerator mToneGenerator;
    private final ReactApplicationContext reactContext;

    public BigDataDTMFModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mToneGenerator = new ToneGenerator(8, 50);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNDtmf";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("DTMF_0", 0);
        map.put("DTMF_1", 1);
        map.put("DTMF_2", 2);
        map.put("DTMF_3", 3);
        map.put("DTMF_4", 4);
        map.put("DTMF_5", 5);
        map.put("DTMF_6", 6);
        map.put("DTMF_7", 7);
        map.put("DTMF_8", 8);
        map.put("DTMF_9", 9);
        map.put("DTMF_A", 12);
        map.put("DTMF_B", 13);
        map.put("DTMF_C", 14);
        map.put("DTMF_D", 15);
        map.put("DTMF_STAR", 10);
        map.put("DTMF_POUND", 11);
        map.put("DTMF_S", 10);
        map.put("DTMF_P", 11);
        return map;
    }

    @ReactMethod
    public void playTone(int i, int i2) {
        this.mToneGenerator.startTone(i, i2);
    }

    @ReactMethod
    public void startTone(int i) {
        this.mToneGenerator.startTone(i, 5000);
    }

    @ReactMethod
    public void stopTone() {
        this.mToneGenerator.stopTone();
    }
}
