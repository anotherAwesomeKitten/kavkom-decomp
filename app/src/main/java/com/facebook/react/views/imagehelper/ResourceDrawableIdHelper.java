package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.res.ResourcesCompat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ResourceDrawableIdHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0007J\u001a\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007J\r\u0010\u0016\u001a\u00020\u0000H\u0007¢\u0006\u0002\b\u0019R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/react/views/imagehelper/ResourceDrawableIdHelper;", "", "<init>", "()V", "resourceDrawableIdMap", "", "", "", "clear", "", "getResourceDrawableId", "context", "Landroid/content/Context;", "name", "addDrawableId", "normalizedName", "getResourceDrawable", "Landroid/graphics/drawable/Drawable;", "getResourceDrawableUri", "Landroid/net/Uri;", "instance", "getInstance$annotations", "getInstance", "()Lcom/facebook/react/views/imagehelper/ResourceDrawableIdHelper;", "LOCAL_RESOURCE_SCHEME", "DEPRECATED$getInstance", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ResourceDrawableIdHelper {
    private static final String LOCAL_RESOURCE_SCHEME = "res";
    public static final ResourceDrawableIdHelper INSTANCE = new ResourceDrawableIdHelper();
    private static final Map<String, Integer> resourceDrawableIdMap = new HashMap();

    @Deprecated(message = "Use object methods instead, this API is for backward compat")
    @JvmStatic
    public static /* synthetic */ void getInstance$annotations() {
    }

    @Deprecated(message = "Use .instance instead, this API is for backward compat", replaceWith = @ReplaceWith(expression = "instance", imports = {}))
    public final ResourceDrawableIdHelper DEPRECATED$getInstance() {
        return this;
    }

    private ResourceDrawableIdHelper() {
    }

    @JvmStatic
    public static final synchronized void clear() {
        resourceDrawableIdMap.clear();
    }

    @JvmStatic
    public static final int getResourceDrawableId(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = name;
        if (str == null || str.length() == 0) {
            return 0;
        }
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String strReplace$default = StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null);
        try {
            return Integer.parseInt(strReplace$default);
        } catch (NumberFormatException unused) {
            synchronized (INSTANCE) {
                Integer num = resourceDrawableIdMap.get(strReplace$default);
                return num != null ? num.intValue() : INSTANCE.addDrawableId(context, strReplace$default);
            }
        }
    }

    private final int addDrawableId(Context context, String normalizedName) {
        int identifier = context.getResources().getIdentifier(normalizedName, "drawable", context.getPackageName());
        resourceDrawableIdMap.put(normalizedName, Integer.valueOf(identifier));
        return identifier;
    }

    @JvmStatic
    public static final Drawable getResourceDrawable(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        int resourceDrawableId = getResourceDrawableId(context, name);
        if (resourceDrawableId > 0) {
            return ResourcesCompat.getDrawable(context.getResources(), resourceDrawableId, null);
        }
        return null;
    }

    @JvmStatic
    public static final Uri getResourceDrawableUri(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        int resourceDrawableId = getResourceDrawableId(context, name);
        if (resourceDrawableId > 0) {
            Uri uriBuild = new Uri.Builder().scheme("res").path(String.valueOf(resourceDrawableId)).build();
            Intrinsics.checkNotNull(uriBuild);
            return uriBuild;
        }
        Uri uri = Uri.EMPTY;
        Intrinsics.checkNotNull(uri);
        return uri;
    }

    public static final ResourceDrawableIdHelper getInstance() {
        return INSTANCE;
    }
}
