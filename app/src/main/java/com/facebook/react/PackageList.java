package com.facebook.react;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import ca.bigdata.voice.dtmf.BigDataDTMFPackage;
import com.BV.LinearGradient.LinearGradientPackage;
import com.asterinet.react.bgactions.BackgroundActionsPackage;
import com.dieam.reactnativepushnotification.ReactNativePushNotificationPackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.horcrux.svg.SvgPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.lugg.RNCConfig.RNCConfigPackage;
import com.mkuczera.RNReactNativeHapticFeedbackPackage;
import com.oblador.keychain.KeychainPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.oney.WebRTCModule.WebRTCModulePackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativepagerview.PagerViewPackage;
import com.shopify.reactnative.skia.RNSkiaPackage;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.swmansion.worklets.WorkletsPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.zmxv.RNSound.RNSoundPackage;
import com.zoontek.rnlocalize.RNLocalizePackage;
import com.zoontek.rnpermissions.RNPermissionsPackage;
import com.zxcpoiu.incallmanager.InCallManagerPackage;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingPackage;
import io.sentry.react.RNSentryPackage;
import io.wazo.callkeep.RNCallKeepPackage;
import java.util.ArrayList;
import java.util.Arrays;
import org.linusu.RNGetRandomValuesPackage;

/* JADX INFO: loaded from: classes.dex */
public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost) {
        this(reactNativeHost, (MainPackageConfig) null);
    }

    public PackageList(Application application) {
        this(application, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application;
        this.mConfig = mainPackageConfig;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        return reactNativeHost == null ? this.application : reactNativeHost.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new MainReactPackage(this.mConfig), new AsyncStoragePackage(), new ReactNativeFirebaseAppPackage(), new ReactNativeFirebaseMessagingPackage(), new RNSentryPackage(), new RNSkiaPackage(), new BackgroundActionsPackage(), new BackgroundTimerPackage(), new RNCallKeepPackage(), new RNCConfigPackage(), new RNDeviceInfo(), new BigDataDTMFPackage(), new RNGestureHandlerPackage(), new RNGetRandomValuesPackage(), new RNReactNativeHapticFeedbackPackage(), new InCallManagerPackage(), new KeychainPackage(), new LinearGradientPackage(), new RNLocalizePackage(), new PagerViewPackage(), new RNPermissionsPackage(), new ReactNativePushNotificationPackage(), new ReanimatedPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new RNSoundPackage(), new SvgPackage(), new WebRTCModulePackage(), new WorkletsPackage()));
    }
}
