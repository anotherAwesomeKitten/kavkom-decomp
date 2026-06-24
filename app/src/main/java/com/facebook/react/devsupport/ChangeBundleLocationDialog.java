package com.facebook.react.devsupport;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.react.R;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChangeBundleLocationDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\r"}, d2 = {"Lcom/facebook/react/devsupport/ChangeBundleLocationDialog;", "", "<init>", "()V", "show", "", "context", "Landroid/content/Context;", "devSettings", "Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "onClickListener", "Lcom/facebook/react/devsupport/ChangeBundleLocationDialog$ChangeBundleLocationDialogListener;", "ChangeBundleLocationDialogListener", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ChangeBundleLocationDialog {
    public static final ChangeBundleLocationDialog INSTANCE = new ChangeBundleLocationDialog();

    /* JADX INFO: compiled from: ChangeBundleLocationDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/ChangeBundleLocationDialog$ChangeBundleLocationDialogListener;", "", ViewProps.ON_CLICK, "", "newHostAndPort", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface ChangeBundleLocationDialogListener {
        void onClick(String newHostAndPort);
    }

    private ChangeBundleLocationDialog() {
    }

    public final void show(Context context, DeveloperSettings devSettings, final ChangeBundleLocationDialogListener onClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(devSettings, "devSettings");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        PackagerConnectionSettings packagerConnectionSettings = devSettings.getPackagerConnectionSettings();
        String debugServerHost = packagerConnectionSettings.getDebugServerHost();
        packagerConnectionSettings.setDebugServerHost("");
        final String debugServerHost2 = packagerConnectionSettings.getDebugServerHost();
        packagerConnectionSettings.setDebugServerHost(debugServerHost);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        int i = (int) (4 * context.getResources().getDisplayMetrics().density);
        int i2 = (int) (16 * context.getResources().getDisplayMetrics().density);
        linearLayout.setPadding(i2, i2, i2, i2);
        TextView textView = new TextView(context);
        textView.setText(context.getString(R.string.catalyst_change_bundle_location_input_label));
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        final EditText editText = new EditText(context);
        editText.setInputType(1);
        editText.setHint(context.getString(R.string.catalyst_change_bundle_location_input_hint));
        editText.setBackgroundResource(android.R.drawable.edit_text);
        editText.setHintTextColor(-3355444);
        editText.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        editText.setText(debugServerHost);
        Button button = new Button(context);
        button.setText(debugServerHost2);
        button.setTextSize(12.0f);
        button.setAllCaps(false);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.ChangeBundleLocationDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeBundleLocationDialog.show$lambda$0(editText, debugServerHost2, view);
            }
        });
        final String devServerNetworkIpAndPort$ReactAndroid_release = AndroidInfoHelpers.INSTANCE.getDevServerNetworkIpAndPort$ReactAndroid_release(context);
        Button button2 = new Button(context);
        button2.setText(devServerNetworkIpAndPort$ReactAndroid_release);
        button2.setTextSize(12.0f);
        button2.setAllCaps(false);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.ChangeBundleLocationDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeBundleLocationDialog.show$lambda$1(editText, devServerNetworkIpAndPort$ReactAndroid_release, view);
            }
        });
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout2.addView(button);
        linearLayout2.addView(button2);
        TextView textView2 = new TextView(context);
        textView2.setText(context.getString(R.string.catalyst_change_bundle_location_instructions, AndroidInfoHelpers.getAdbReverseTcpCommand(context)));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, i, 0, i2);
        textView2.setLayoutParams(layoutParams);
        Button button3 = new Button(context);
        button3.setText(context.getString(R.string.catalyst_change_bundle_location_apply));
        Button button4 = new Button(context);
        button4.setText(context.getString(R.string.catalyst_change_bundle_location_cancel));
        linearLayout.addView(textView);
        linearLayout.addView(editText);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(textView2);
        linearLayout.addView(button3);
        linearLayout.addView(button4);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setTitle(context.getString(R.string.catalyst_change_bundle_location)).setView(linearLayout).create();
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.ChangeBundleLocationDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeBundleLocationDialog.show$lambda$2(onClickListener, editText, alertDialogCreate, view);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.ChangeBundleLocationDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0(EditText editText, String str, View view) {
        editText.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$1(EditText editText, String str, View view) {
        editText.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$2(ChangeBundleLocationDialogListener changeBundleLocationDialogListener, EditText editText, AlertDialog alertDialog, View view) {
        changeBundleLocationDialogListener.onClick(editText.getText().toString());
        alertDialog.dismiss();
    }
}
