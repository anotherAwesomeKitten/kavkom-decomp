package com.swmansion.rnscreens.gamma.tabs.image;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TabsImageLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u0003J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/image/ImageSource;", "", "context", "Landroid/content/Context;", "uriString", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "isLocalResourceUri", "", "uri", "Landroid/net/Uri;", "getUri", "computeUri", "computeLocalUri", "name", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ImageSource {
    private final Context context;
    private final String uriString;

    public ImageSource(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.uriString = str;
    }

    private final boolean isLocalResourceUri(Uri uri) {
        String scheme;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return StringsKt.startsWith$default(scheme, UriUtil.LOCAL_RESOURCE_SCHEME, false, 2, (Object) null);
    }

    public final Uri getUri(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Uri uriComputeUri = computeUri(context);
        if (!isLocalResourceUri(uriComputeUri)) {
            return uriComputeUri;
        }
        Intrinsics.checkNotNull(uriComputeUri);
        String string = uriComputeUri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return Uri.parse(StringsKt.replace$default(string, "res:/", "android.resource://" + context.getPackageName() + "/", false, 4, (Object) null));
    }

    private final Uri computeUri(Context context) {
        String str = this.uriString;
        if (str == null) {
            return null;
        }
        try {
            Uri uri = Uri.parse(str);
            return uri.getScheme() == null ? computeLocalUri(str, context) : uri;
        } catch (Exception unused) {
            return computeLocalUri(str, context);
        }
    }

    private final Uri computeLocalUri(String name, Context context) {
        return ResourceIdHelper.INSTANCE.getResourceUri(context, name);
    }
}
