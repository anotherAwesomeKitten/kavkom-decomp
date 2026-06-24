package com.facebook.react.modules.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.protocol.Request;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlertFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B!\b\u0011\u0012\f\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0003\u0010\nJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/modules/dialog/AlertFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/content/DialogInterface$OnClickListener;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/modules/dialog/DialogModule$AlertFragmentListener;", "Lcom/facebook/react/modules/dialog/DialogModule;", "arguments", "Landroid/os/Bundle;", "(Lcom/facebook/react/modules/dialog/DialogModule$AlertFragmentListener;Landroid/os/Bundle;)V", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", ViewProps.ON_CLICK, "", "dialog", "Landroid/content/DialogInterface;", "which", "", "onDismiss", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AlertFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String ARG_BUTTON_NEGATIVE = "button_negative";
    public static final String ARG_BUTTON_NEUTRAL = "button_neutral";
    public static final String ARG_BUTTON_POSITIVE = "button_positive";
    public static final String ARG_ITEMS = "items";
    public static final String ARG_MESSAGE = "message";
    public static final String ARG_TITLE = "title";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DialogModule.AlertFragmentListener listener;

    @JvmStatic
    public static final Dialog createDialog(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        return INSTANCE.createDialog(context, bundle, onClickListener);
    }

    public AlertFragment() {
        this.listener = null;
    }

    public AlertFragment(DialogModule.AlertFragmentListener alertFragmentListener, Bundle bundle) {
        this.listener = alertFragmentListener;
        setArguments(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Companion companion = INSTANCE;
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        Bundle bundleRequireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(bundleRequireArguments, "requireArguments(...)");
        return companion.createDialog(fragmentActivityRequireActivity, bundleRequireArguments, this);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogModule.AlertFragmentListener alertFragmentListener = this.listener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onClick(dialog, i);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        DialogModule.AlertFragmentListener alertFragmentListener = this.listener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onDismiss(dialog);
        }
    }

    /* JADX INFO: compiled from: AlertFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J \u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/react/modules/dialog/AlertFragment$Companion;", "", "<init>", "()V", "ARG_TITLE", "", "ARG_MESSAGE", "ARG_BUTTON_POSITIVE", "ARG_BUTTON_NEGATIVE", "ARG_BUTTON_NEUTRAL", "ARG_ITEMS", "createDialog", "Landroid/app/Dialog;", "activityContext", "Landroid/content/Context;", "arguments", "Landroid/os/Bundle;", Request.JsonKeys.FRAGMENT, "Landroid/content/DialogInterface$OnClickListener;", "isAppCompatTheme", "", "getAccessibleTitle", "Landroid/view/View;", "titleText", "createAppCompatDialog", "createAppDialog", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Dialog createDialog(Context activityContext, Bundle arguments, DialogInterface.OnClickListener onClickListener) {
            Intrinsics.checkNotNullParameter(activityContext, "activityContext");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            Intrinsics.checkNotNullParameter(onClickListener, "fragment");
            if (isAppCompatTheme(activityContext)) {
                return createAppCompatDialog(activityContext, arguments, onClickListener);
            }
            return createAppDialog(activityContext, arguments, onClickListener);
        }

        private final boolean isAppCompatTheme(Context activityContext) {
            TypedArray typedArrayObtainStyledAttributes = activityContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
            Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowActionBar);
            typedArrayObtainStyledAttributes.recycle();
            return zHasValue;
        }

        private final View getAccessibleTitle(Context activityContext, String titleText) {
            View viewInflate = LayoutInflater.from(activityContext).inflate(com.facebook.react.R.layout.alert_title_layout, (ViewGroup) null);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
            Object objAssertNotNull = Assertions.assertNotNull(viewInflate.findViewById(com.facebook.react.R.id.alert_title));
            Intrinsics.checkNotNullExpressionValue(objAssertNotNull, "assertNotNull(...)");
            final TextView textView = (TextView) objAssertNotNull;
            textView.setText(titleText);
            textView.setFocusable(true);
            if (Build.VERSION.SDK_INT >= 28) {
                textView.setAccessibilityHeading(true);
                return viewInflate;
            }
            ViewCompat.setAccessibilityDelegate(textView, new AccessibilityDelegateCompat() { // from class: com.facebook.react.modules.dialog.AlertFragment$Companion$getAccessibleTitle$1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat info) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(info, "info");
                    super.onInitializeAccessibilityNodeInfo(textView, info);
                    info.setHeading(true);
                }
            });
            return viewInflate;
        }

        private final Dialog createAppCompatDialog(Context activityContext, Bundle arguments, DialogInterface.OnClickListener onClickListener) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
            if (arguments.containsKey("title")) {
                String str = (String) Assertions.assertNotNull(arguments.getString("title"));
                Intrinsics.checkNotNull(str);
                builder.setCustomTitle(getAccessibleTitle(activityContext, str));
            }
            if (arguments.containsKey(AlertFragment.ARG_BUTTON_POSITIVE)) {
                builder.setPositiveButton(arguments.getString(AlertFragment.ARG_BUTTON_POSITIVE), onClickListener);
            }
            if (arguments.containsKey(AlertFragment.ARG_BUTTON_NEGATIVE)) {
                builder.setNegativeButton(arguments.getString(AlertFragment.ARG_BUTTON_NEGATIVE), onClickListener);
            }
            if (arguments.containsKey(AlertFragment.ARG_BUTTON_NEUTRAL)) {
                builder.setNeutralButton(arguments.getString(AlertFragment.ARG_BUTTON_NEUTRAL), onClickListener);
            }
            if (arguments.containsKey("message")) {
                builder.setMessage(arguments.getString("message"));
            }
            if (arguments.containsKey("items")) {
                builder.setItems(arguments.getCharSequenceArray("items"), onClickListener);
            }
            AlertDialog alertDialogCreate = builder.create();
            Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
            return alertDialogCreate;
        }

        @Deprecated(message = "non-AppCompat dialogs are deprecated and will be removed in a future version.", replaceWith = @ReplaceWith(expression = "createAppCompatDialog(activityContext, arguments, fragment)", imports = {}))
        private final Dialog createAppDialog(Context activityContext, Bundle arguments, DialogInterface.OnClickListener onClickListener) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
            if (arguments.containsKey("title")) {
                String str = (String) Assertions.assertNotNull(arguments.getString("title"));
                Intrinsics.checkNotNull(str);
                builder.setCustomTitle(getAccessibleTitle(activityContext, str));
            }
            if (arguments.containsKey(AlertFragment.ARG_BUTTON_POSITIVE)) {
                builder.setPositiveButton(arguments.getString(AlertFragment.ARG_BUTTON_POSITIVE), onClickListener);
            }
            if (arguments.containsKey(AlertFragment.ARG_BUTTON_NEGATIVE)) {
                builder.setNegativeButton(arguments.getString(AlertFragment.ARG_BUTTON_NEGATIVE), onClickListener);
            }
            if (arguments.containsKey(AlertFragment.ARG_BUTTON_NEUTRAL)) {
                builder.setNeutralButton(arguments.getString(AlertFragment.ARG_BUTTON_NEUTRAL), onClickListener);
            }
            if (arguments.containsKey("message")) {
                builder.setMessage(arguments.getString("message"));
            }
            if (arguments.containsKey("items")) {
                builder.setItems(arguments.getCharSequenceArray("items"), onClickListener);
            }
            android.app.AlertDialog alertDialogCreate = builder.create();
            Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
            return alertDialogCreate;
        }
    }
}
