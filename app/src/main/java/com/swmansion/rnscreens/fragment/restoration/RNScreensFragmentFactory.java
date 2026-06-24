package com.swmansion.rnscreens.fragment.restoration;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import com.swmansion.rnscreens.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RNScreensFragmentFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/swmansion/rnscreens/fragment/restoration/RNScreensFragmentFactory;", "Landroidx/fragment/app/FragmentFactory;", "<init>", "()V", "instantiate", "Landroidx/fragment/app/Fragment;", "classLoader", "Ljava/lang/ClassLoader;", "className", "", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RNScreensFragmentFactory extends FragmentFactory {
    @Override // androidx.fragment.app.FragmentFactory
    public Fragment instantiate(ClassLoader classLoader, String className) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(className, "className");
        if (StringsKt.startsWith$default(className, BuildConfig.LIBRARY_PACKAGE_NAME, false, 2, (Object) null)) {
            return new AutoRemovingFragment();
        }
        Fragment fragmentInstantiate = super.instantiate(classLoader, className);
        Intrinsics.checkNotNull(fragmentInstantiate);
        return fragmentInstantiate;
    }
}
