package com.swmansion.rnscreens.gamma.tabs.image;

import android.content.Context;
import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TabsImageLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0003J\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/image/ResourceIdHelper;", "", "<init>", "()V", "idMap", "", "", "", "getIdForResourceType", "context", "Landroid/content/Context;", "name", "type", "getResourceUri", "Landroid/net/Uri;", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ResourceIdHelper {
    public static final ResourceIdHelper INSTANCE = new ResourceIdHelper();
    private static final Map<String, Integer> idMap = new LinkedHashMap();

    private ResourceIdHelper() {
    }

    private final int getIdForResourceType(Context context, String name, String type) {
        if (name.length() == 0) {
            return -1;
        }
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = name.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String strReplace$default = StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null);
        String str = type + "/" + strReplace$default;
        synchronized (this) {
            Map<String, Integer> map = idMap;
            Integer num = map.get(str);
            if (num != null) {
                return num.intValue();
            }
            int identifier = context.getResources().getIdentifier(strReplace$default, type, context.getPackageName());
            map.put(str, Integer.valueOf(identifier));
            return identifier;
        }
    }

    public final Uri getResourceUri(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = name.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null);
        int idForResourceType = getIdForResourceType(context, name, "drawable");
        if (idForResourceType != 0) {
            return Uri.parse("res:/" + idForResourceType);
        }
        int idForResourceType2 = getIdForResourceType(context, name, "raw");
        if (idForResourceType2 != 0) {
            return Uri.parse("res:/" + idForResourceType2);
        }
        if (StringsKt.startsWith$default(name, "asset:/", false, 2, (Object) null)) {
            return Uri.parse("file:///android_asset/" + StringsKt.removePrefix(name, (CharSequence) "asset:/"));
        }
        return Uri.parse("file:///android_asset/" + name);
    }
}
