package com.kavkom.phone;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import io.sentry.protocol.DebugImage;

/* JADX INFO: loaded from: classes2.dex */
public class IncomingCallActivity extends Activity {
    private static IncomingCallActivity currentInstance;
    private String currentUuid = null;
    private boolean wasKeyguardDismissed = false;

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String string = getIntent().getExtras().getString("name");
        int i = getIntent().getExtras().getInt("notificationId", 1);
        String string2 = getIntent().getExtras().getString(DebugImage.JsonKeys.UUID);
        String string3 = getIntent().getExtras().getString("eventName");
        if (string3 == null) {
            finish();
            return;
        }
        if (string3.equals("accepted")) {
            acceptCall(string2, string, i);
            return;
        }
        if (string3.equals("rejected")) {
            rejectCall(string2, string, i);
        } else if (string3.equals("fullScreenCall")) {
            currentInstance = this;
            this.currentUuid = string2;
            fullScreenCall(string2, string, i);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (currentInstance == this) {
            currentInstance = null;
            this.currentUuid = null;
        }
    }

    public static void finishIfShowing(String str) {
        IncomingCallActivity incomingCallActivity = currentInstance;
        if (incomingCallActivity != null) {
            if (str == null || str.equals(incomingCallActivity.currentUuid)) {
                IncomingCallActivity incomingCallActivity2 = currentInstance;
                incomingCallActivity2.runOnUiThread(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.1
                    AnonymousClass1() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            IncomingCallActivity incomingCallActivity3 = IncomingCallActivity.this;
                            if (incomingCallActivity3 != null && !incomingCallActivity3.isFinishing()) {
                                IncomingCallActivity.this.finish();
                                IncomingCallActivity.this.stopService();
                                return;
                            }
                            IncomingCallActivity incomingCallActivity4 = IncomingCallActivity.this;
                            if (incomingCallActivity4 == null || !incomingCallActivity4.isFinishing()) {
                                return;
                            }
                            Log.d("IncomingCallActivity", "Activity is already finishing, skipping finish() call");
                            IncomingCallActivity.this.stopService();
                        } catch (Exception e) {
                            Log.e("IncomingCallActivity", "Exception when finishing activity: " + e.getMessage(), e);
                            try {
                                IncomingCallActivity incomingCallActivity5 = IncomingCallActivity.this;
                                if (incomingCallActivity5 != null) {
                                    incomingCallActivity5.stopService();
                                }
                            } catch (Exception e2) {
                                Log.w("IncomingCallActivity", "Failed to stop service after exception: " + e2.getMessage());
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$1 */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                IncomingCallActivity incomingCallActivity3 = IncomingCallActivity.this;
                if (incomingCallActivity3 != null && !incomingCallActivity3.isFinishing()) {
                    IncomingCallActivity.this.finish();
                    IncomingCallActivity.this.stopService();
                    return;
                }
                IncomingCallActivity incomingCallActivity4 = IncomingCallActivity.this;
                if (incomingCallActivity4 == null || !incomingCallActivity4.isFinishing()) {
                    return;
                }
                Log.d("IncomingCallActivity", "Activity is already finishing, skipping finish() call");
                IncomingCallActivity.this.stopService();
            } catch (Exception e) {
                Log.e("IncomingCallActivity", "Exception when finishing activity: " + e.getMessage(), e);
                try {
                    IncomingCallActivity incomingCallActivity5 = IncomingCallActivity.this;
                    if (incomingCallActivity5 != null) {
                        incomingCallActivity5.stopService();
                    }
                } catch (Exception e2) {
                    Log.w("IncomingCallActivity", "Failed to stop service after exception: " + e2.getMessage());
                }
            }
        }
    }

    public void acceptCall(String str, String str2, int i) {
        CallkeepHelperModule.setAcceptedFromLockedState(str, this.wasKeyguardDismissed);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("eventName", "accepted");
        writableMapCreateMap.putString("name", str2);
        writableMapCreateMap.putString(DebugImage.JsonKeys.UUID, str);
        writableMapCreateMap.putInt("notificationId", i);
        writableMapCreateMap.putBoolean("acceptedFromLockedState", this.wasKeyguardDismissed);
        sendEvent("answeredCallAndroid", writableMapCreateMap);
        finish();
        stopService();
    }

    public void rejectCall(String str, String str2, int i) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("eventName", "rejected");
        writableMapCreateMap.putString("name", str2);
        writableMapCreateMap.putString(DebugImage.JsonKeys.UUID, str);
        writableMapCreateMap.putInt("notificationId", i);
        sendEvent("rejectedCallAndroid", writableMapCreateMap);
        finish();
        stopService();
    }

    private void fullScreenCall(String str, String str2, int i) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("eventName", "fullScreenCall");
        writableMapCreateMap.putString("name", str2);
        writableMapCreateMap.putString(DebugImage.JsonKeys.UUID, str);
        writableMapCreateMap.putInt("notificationId", i);
        sendEvent("fullScreenCall", writableMapCreateMap);
        setContentView(R.layout.activity_call_incoming);
        TextView textView = (TextView) findViewById(R.id.tvName);
        textView.setText(str2);
        boolean z = false;
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        if (keyguardManager != null && keyguardManager.isKeyguardLocked()) {
            z = true;
        }
        this.wasKeyguardDismissed = z;
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        }
        getWindow().addFlags(6816896);
        View viewFindViewById = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(viewFindViewById, new OnApplyWindowInsetsListener() { // from class: com.kavkom.phone.IncomingCallActivity.2
            AnonymousClass2() {
            }

            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                int i2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
                LinearLayout linearLayout = (LinearLayout) IncomingCallActivity.this.findViewById(R.id.buttonContainer);
                if (linearLayout != null) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
                    layoutParams.bottomMargin = ((int) (40 * IncomingCallActivity.this.getResources().getDisplayMetrics().density)) + i2;
                    linearLayout.setLayoutParams(layoutParams);
                }
                return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
            }
        });
        ViewCompat.requestApplyInsets(viewFindViewById);
        View viewFindViewById2 = findViewById(R.id.nameContainer);
        View viewFindViewById3 = findViewById(R.id.pulsingCircleView);
        if (viewFindViewById2 != null && viewFindViewById3 != null) {
            viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.kavkom.phone.IncomingCallActivity.3
                final /* synthetic */ View val$rootView;

                AnonymousClass3(View viewFindViewById4) {
                    view = viewFindViewById4;
                }

                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    view.post(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.3.1
                        AnonymousClass1() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            IncomingCallActivity.this.centerCircleOnText(view);
                        }
                    });
                }

                /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$3$1 */
                class AnonymousClass1 implements Runnable {
                    AnonymousClass1() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        IncomingCallActivity.this.centerCircleOnText(view);
                    }
                }
            });
        }
        ((AnimateButton) findViewById(R.id.ivAcceptCall)).setOnClickListener(new View.OnClickListener() { // from class: com.kavkom.phone.IncomingCallActivity.4
            final /* synthetic */ String val$name;
            final /* synthetic */ int val$notificationId;
            final /* synthetic */ String val$uuid;

            AnonymousClass4(String str3, String str22, int i2) {
                str = str3;
                str = str22;
                i = i2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IncomingCallActivity.this.acceptCall(str, str, i);
            }
        });
        ((AnimateButton) findViewById(R.id.ivDeclineCall)).setOnClickListener(new View.OnClickListener() { // from class: com.kavkom.phone.IncomingCallActivity.5
            final /* synthetic */ String val$name;
            final /* synthetic */ int val$notificationId;
            final /* synthetic */ String val$uuid;

            AnonymousClass5(String str3, String str22, int i2) {
                str = str3;
                str = str22;
                i = i2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IncomingCallActivity.this.rejectCall(str, str, i);
            }
        });
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$2 */
    class AnonymousClass2 implements OnApplyWindowInsetsListener {
        AnonymousClass2() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            int i2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            LinearLayout linearLayout = (LinearLayout) IncomingCallActivity.this.findViewById(R.id.buttonContainer);
            if (linearLayout != null) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = ((int) (40 * IncomingCallActivity.this.getResources().getDisplayMetrics().density)) + i2;
                linearLayout.setLayoutParams(layoutParams);
            }
            return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$3 */
    class AnonymousClass3 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ View val$rootView;

        AnonymousClass3(View viewFindViewById4) {
            view = viewFindViewById4;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            view.post(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.3.1
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    IncomingCallActivity.this.centerCircleOnText(view);
                }
            });
        }

        /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$3$1 */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                IncomingCallActivity.this.centerCircleOnText(view);
            }
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$4 */
    class AnonymousClass4 implements View.OnClickListener {
        final /* synthetic */ String val$name;
        final /* synthetic */ int val$notificationId;
        final /* synthetic */ String val$uuid;

        AnonymousClass4(String str3, String str22, int i2) {
            str = str3;
            str = str22;
            i = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            IncomingCallActivity.this.acceptCall(str, str, i);
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$5 */
    class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ String val$name;
        final /* synthetic */ int val$notificationId;
        final /* synthetic */ String val$uuid;

        AnonymousClass5(String str3, String str22, int i2) {
            str = str3;
            str = str22;
            i = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            IncomingCallActivity.this.rejectCall(str, str, i);
        }
    }

    public void centerCircleOnText(View view) {
        View viewFindViewById = findViewById(R.id.nameContainer);
        View viewFindViewById2 = findViewById(R.id.pulsingCircleView);
        TextView textView = (TextView) findViewById(R.id.tvName);
        TextView textView2 = (TextView) findViewById(R.id.tvInfo);
        if (viewFindViewById == null || viewFindViewById2 == null) {
            return;
        }
        view.post(new AnonymousClass6(viewFindViewById, viewFindViewById2, view, textView, textView2));
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$6 */
    class AnonymousClass6 implements Runnable {
        final /* synthetic */ View val$nameContainer;
        final /* synthetic */ View val$pulsingCircleView;
        final /* synthetic */ View val$rootView;
        final /* synthetic */ TextView val$tvInfo;
        final /* synthetic */ TextView val$tvName;

        AnonymousClass6(View view, View view2, View view3, TextView textView, TextView textView2) {
            this.val$nameContainer = view;
            this.val$pulsingCircleView = view2;
            this.val$rootView = view3;
            this.val$tvName = textView;
            this.val$tvInfo = textView2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$nameContainer.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.val$pulsingCircleView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = this.val$nameContainer.getMeasuredHeight();
            int measuredHeight2 = this.val$pulsingCircleView.getMeasuredHeight();
            if (measuredHeight == 0 || measuredHeight2 == 0) {
                this.val$rootView.postDelayed(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.6.1
                    AnonymousClass1() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        IncomingCallActivity.this.centerCircleOnText(AnonymousClass6.this.val$rootView);
                    }
                }, 50L);
            } else {
                this.val$rootView.post(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.6.2
                    final /* synthetic */ int val$circleHeight;

                    AnonymousClass2(int measuredHeight22) {
                        i = measuredHeight22;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        int i;
                        int top = AnonymousClass6.this.val$nameContainer.getTop();
                        int bottom = AnonymousClass6.this.val$nameContainer.getBottom();
                        if (top == 0 && bottom == 0) {
                            AnonymousClass6.this.val$rootView.postDelayed(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.6.2.1
                                AnonymousClass1() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    IncomingCallActivity.this.centerCircleOnText(AnonymousClass6.this.val$rootView);
                                }
                            }, 50L);
                            return;
                        }
                        if (AnonymousClass6.this.val$tvName != null && AnonymousClass6.this.val$tvInfo != null) {
                            int top2 = AnonymousClass6.this.val$tvName.getTop();
                            int bottom2 = AnonymousClass6.this.val$tvInfo.getBottom();
                            if (top2 > 0 && bottom2 > 0) {
                                i = ((top2 + top) + (bottom2 + top)) / 2;
                            } else {
                                i = (top + bottom) / 2;
                            }
                        } else {
                            i = (top + bottom) / 2;
                        }
                        int i2 = (((int) (IncomingCallActivity.this.getResources().getDisplayMetrics().density * (-21.0f))) + i) - (i / 2);
                        Log.d("IncomingCallActivity", "Alignment: nameTop=" + top + ", nameBottom=" + bottom + ", textCenterY=" + i + ", circleHeight=" + i + ", circleTop=" + i2);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) AnonymousClass6.this.val$pulsingCircleView.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                        }
                        layoutParams.addRule(14);
                        layoutParams.removeRule(3);
                        layoutParams.removeRule(6);
                        layoutParams.removeRule(8);
                        layoutParams.topMargin = i2;
                        AnonymousClass6.this.val$pulsingCircleView.setLayoutParams(layoutParams);
                        AnonymousClass6.this.val$pulsingCircleView.requestLayout();
                    }

                    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$6$2$1 */
                    class AnonymousClass1 implements Runnable {
                        AnonymousClass1() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            IncomingCallActivity.this.centerCircleOnText(AnonymousClass6.this.val$rootView);
                        }
                    }
                });
            }
        }

        /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$6$1 */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                IncomingCallActivity.this.centerCircleOnText(AnonymousClass6.this.val$rootView);
            }
        }

        /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$6$2 */
        class AnonymousClass2 implements Runnable {
            final /* synthetic */ int val$circleHeight;

            AnonymousClass2(int measuredHeight22) {
                i = measuredHeight22;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i;
                int top = AnonymousClass6.this.val$nameContainer.getTop();
                int bottom = AnonymousClass6.this.val$nameContainer.getBottom();
                if (top == 0 && bottom == 0) {
                    AnonymousClass6.this.val$rootView.postDelayed(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.6.2.1
                        AnonymousClass1() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            IncomingCallActivity.this.centerCircleOnText(AnonymousClass6.this.val$rootView);
                        }
                    }, 50L);
                    return;
                }
                if (AnonymousClass6.this.val$tvName != null && AnonymousClass6.this.val$tvInfo != null) {
                    int top2 = AnonymousClass6.this.val$tvName.getTop();
                    int bottom2 = AnonymousClass6.this.val$tvInfo.getBottom();
                    if (top2 > 0 && bottom2 > 0) {
                        i = ((top2 + top) + (bottom2 + top)) / 2;
                    } else {
                        i = (top + bottom) / 2;
                    }
                } else {
                    i = (top + bottom) / 2;
                }
                int i2 = (((int) (IncomingCallActivity.this.getResources().getDisplayMetrics().density * (-21.0f))) + i) - (i / 2);
                Log.d("IncomingCallActivity", "Alignment: nameTop=" + top + ", nameBottom=" + bottom + ", textCenterY=" + i + ", circleHeight=" + i + ", circleTop=" + i2);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) AnonymousClass6.this.val$pulsingCircleView.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                }
                layoutParams.addRule(14);
                layoutParams.removeRule(3);
                layoutParams.removeRule(6);
                layoutParams.removeRule(8);
                layoutParams.topMargin = i2;
                AnonymousClass6.this.val$pulsingCircleView.setLayoutParams(layoutParams);
                AnonymousClass6.this.val$pulsingCircleView.requestLayout();
            }

            /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$6$2$1 */
            class AnonymousClass1 implements Runnable {
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    IncomingCallActivity.this.centerCircleOnText(AnonymousClass6.this.val$rootView);
                }
            }
        }
    }

    public void stopService() {
        try {
            if (CallkeepHelperModule.reactContext == null) {
                Log.w("IncomingCallActivity", "Cannot stop service - React context is null");
            } else {
                CallkeepHelperModule.reactContext.stopService(new Intent(CallkeepHelperModule.reactContext, (Class<?>) IncomingCallService.class));
            }
        } catch (Exception e) {
            Log.e("IncomingCallActivity", "Error stopping service: " + e.getMessage(), e);
        }
    }

    private void sendEvent(String str, WritableMap writableMap) {
        try {
            if (CallkeepHelperModule.reactContext != null && CallkeepHelperModule.reactContext.hasActiveCatalystInstance()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) CallkeepHelperModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.kavkom.phone.IncomingCallActivity.7
                    final /* synthetic */ String val$eventName;
                    final /* synthetic */ WritableMap val$params;

                    AnonymousClass7(String str2, WritableMap writableMap2) {
                        str = str2;
                        writableMap = writableMap2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (CallkeepHelperModule.reactContext == null || !CallkeepHelperModule.reactContext.hasActiveCatalystInstance()) {
                                Log.w("IncomingCallActivity", "Failed to send event " + str + " after retry - React context still not available");
                            } else {
                                ((DeviceEventManagerModule.RCTDeviceEventEmitter) CallkeepHelperModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
                            }
                        } catch (Exception e) {
                            Log.e("IncomingCallActivity", "Error sending event " + str + " on retry: " + e.getMessage(), e);
                        }
                    }
                }, 1000L);
            }
        } catch (Exception e) {
            Log.e("IncomingCallActivity", "Error sending event " + str2 + ": " + e.getMessage(), e);
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallActivity$7 */
    class AnonymousClass7 implements Runnable {
        final /* synthetic */ String val$eventName;
        final /* synthetic */ WritableMap val$params;

        AnonymousClass7(String str2, WritableMap writableMap2) {
            str = str2;
            writableMap = writableMap2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (CallkeepHelperModule.reactContext == null || !CallkeepHelperModule.reactContext.hasActiveCatalystInstance()) {
                    Log.w("IncomingCallActivity", "Failed to send event " + str + " after retry - React context still not available");
                } else {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) CallkeepHelperModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
                }
            } catch (Exception e) {
                Log.e("IncomingCallActivity", "Error sending event " + str + " on retry: " + e.getMessage(), e);
            }
        }
    }
}
