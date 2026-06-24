package com.kavkom.phone;

import android.app.Application;
import android.content.Context;
import androidx.core.view.PointerIconCompat;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeApplicationEntryPoint;
import com.facebook.react.ReactPackage;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.defaults.DefaultReactHost;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainApplication.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/kavkom/phone/MainApplication;", "Landroid/app/Application;", "Lcom/facebook/react/ReactApplication;", "<init>", "()V", "reactHost", "Lcom/facebook/react/ReactHost;", "getReactHost", "()Lcom/facebook/react/ReactHost;", "reactHost$delegate", "Lkotlin/Lazy;", "onCreate", "", "app_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MainApplication extends Application implements ReactApplication {

    /* JADX INFO: renamed from: reactHost$delegate, reason: from kotlin metadata */
    private final Lazy reactHost = LazyKt.lazy(new Function0() { // from class: com.kavkom.phone.MainApplication$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MainApplication.reactHost_delegate$lambda$1(this.f$0);
        }
    });

    @Override // com.facebook.react.ReactApplication
    public ReactHost getReactHost() {
        return (ReactHost) this.reactHost.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ReactHost reactHost_delegate$lambda$1(MainApplication mainApplication) {
        Context applicationContext = mainApplication.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        ArrayList<ReactPackage> packages = new PackageList(mainApplication).getPackages();
        packages.add(new KavkomPackage());
        Intrinsics.checkNotNullExpressionValue(packages, "apply(...)");
        return DefaultReactHost.getDefaultReactHost(applicationContext, packages, (PointerIconCompat.TYPE_GRAB & 4) != 0 ? FirebaseAnalytics.Param.INDEX : null, (PointerIconCompat.TYPE_GRAB & 8) != 0 ? "index.android.bundle" : null, (PointerIconCompat.TYPE_GRAB & 16) != 0 ? null : null, (PointerIconCompat.TYPE_GRAB & 32) != 0 ? null : null, (PointerIconCompat.TYPE_GRAB & 64) != 0 ? ReactBuildConfig.DEBUG : false, (PointerIconCompat.TYPE_GRAB & 128) != 0 ? CollectionsKt.emptyList() : null, (PointerIconCompat.TYPE_GRAB & 256) != 0 ? new Function1() { // from class: com.facebook.react.defaults.DefaultReactHost$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DefaultReactHost.getDefaultReactHost$lambda$0((Exception) obj);
            }
        } : null, (PointerIconCompat.TYPE_GRAB & 512) != 0 ? null : null);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ReactNativeApplicationEntryPoint.loadReactNative(this);
    }
}
