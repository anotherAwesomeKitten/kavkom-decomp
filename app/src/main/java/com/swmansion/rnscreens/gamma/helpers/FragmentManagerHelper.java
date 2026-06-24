package com.swmansion.rnscreens.gamma.helpers;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.react.ReactRootView;
import com.swmansion.rnscreens.gamma.common.FragmentProviding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentManagerHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/gamma/helpers/FragmentManagerHelper;", "", "<init>", "()V", "findFragmentManagerForView", "Landroidx/fragment/app/FragmentManager;", "view", "Landroid/view/ViewGroup;", "resolveFragmentManagerForReactRootView", "rootView", "Lcom/facebook/react/ReactRootView;", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FragmentManagerHelper {
    public static final FragmentManagerHelper INSTANCE = new FragmentManagerHelper();

    private FragmentManagerHelper() {
    }

    public final FragmentManager findFragmentManagerForView(ViewGroup view) {
        boolean z;
        Intrinsics.checkNotNullParameter(view, "view");
        ViewParent viewParent = view;
        while (true) {
            z = viewParent instanceof ReactRootView;
            if (!z && !(viewParent instanceof FragmentProviding)) {
                ViewParent viewParent2 = viewParent;
                if (viewParent2.getParent() == null) {
                    break;
                }
                ViewParent parent = viewParent2.getParent();
                Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
                viewParent = parent;
            } else {
                break;
            }
        }
        if (viewParent instanceof FragmentProviding) {
            Fragment associatedFragment = ((FragmentProviding) viewParent).getAssociatedFragment();
            if (associatedFragment == null) {
                throw new IllegalStateException(("[RNScreens] Parent fragment providing view " + viewParent + " returned nullish fragment").toString());
            }
            return associatedFragment.getChildFragmentManager();
        }
        if (!z) {
            throw new IllegalStateException(("[RNScreens] Expected parent to be a ReactRootView, instead found: " + viewParent.getClass().getName()).toString());
        }
        return resolveFragmentManagerForReactRootView((ReactRootView) viewParent);
    }

    private final FragmentManager resolveFragmentManagerForReactRootView(ReactRootView rootView) {
        boolean z;
        Context context = rootView.getContext();
        while (true) {
            z = context instanceof FragmentActivity;
            if (z || !(context instanceof ContextWrapper)) {
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (!z) {
            throw new IllegalStateException("[RNScreens] In order to use react-native-screens components your app's activity need to extend ReactActivity".toString());
        }
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        if (fragmentActivity.getSupportFragmentManager().getFragments().isEmpty()) {
            return fragmentActivity.getSupportFragmentManager();
        }
        try {
            return FragmentManager.findFragment(rootView).getChildFragmentManager();
        } catch (IllegalStateException unused) {
            return fragmentActivity.getSupportFragmentManager();
        }
    }
}
