package com.facebook.react.bridge;

import android.content.Context;
import android.content.res.AssetManager;
import com.facebook.react.common.DebugServerException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JSBundleLoader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/facebook/react/bridge/JSBundleLoader;", "", "<init>", "()V", "loadScript", "", "delegate", "Lcom/facebook/react/bridge/JSBundleLoaderDelegate;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class JSBundleLoader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final JSBundleLoader createAssetLoader(Context context, String str, boolean z) {
        return INSTANCE.createAssetLoader(context, str, z);
    }

    @JvmStatic
    public static final JSBundleLoader createCachedBundleFromNetworkLoader(String str, String str2) {
        return INSTANCE.createCachedBundleFromNetworkLoader(str, str2);
    }

    @JvmStatic
    public static final JSBundleLoader createCachedSplitBundleFromNetworkLoader(String str, String str2) {
        return INSTANCE.createCachedSplitBundleFromNetworkLoader(str, str2);
    }

    @JvmStatic
    public static final JSBundleLoader createFileLoader(String str) {
        return INSTANCE.createFileLoader(str);
    }

    @JvmStatic
    public static final JSBundleLoader createFileLoader(String str, String str2, boolean z) {
        return INSTANCE.createFileLoader(str, str2, z);
    }

    public abstract String loadScript(JSBundleLoaderDelegate delegate);

    /* JADX INFO: compiled from: JSBundleLoader.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\tH\u0007J \u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0007J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0007¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/bridge/JSBundleLoader$Companion;", "", "<init>", "()V", "createAssetLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "context", "Landroid/content/Context;", "assetUrl", "", "loadSynchronously", "", "createFileLoader", "fileName", "createCachedBundleFromNetworkLoader", "sourceURL", "cachedFileLocation", "createCachedSplitBundleFromNetworkLoader", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final JSBundleLoader createAssetLoader(final Context context, final String assetUrl, final boolean loadSynchronously) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(assetUrl, "assetUrl");
            return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader$Companion$createAssetLoader$1
                @Override // com.facebook.react.bridge.JSBundleLoader
                public String loadScript(JSBundleLoaderDelegate delegate) {
                    Intrinsics.checkNotNullParameter(delegate, "delegate");
                    AssetManager assets = context.getAssets();
                    Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
                    delegate.loadScriptFromAssets(assets, assetUrl, loadSynchronously);
                    return assetUrl;
                }
            };
        }

        @JvmStatic
        public final JSBundleLoader createFileLoader(String fileName) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return createFileLoader(fileName, fileName, false);
        }

        @JvmStatic
        public final JSBundleLoader createFileLoader(final String fileName, final String assetUrl, final boolean loadSynchronously) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(assetUrl, "assetUrl");
            return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader$Companion$createFileLoader$1
                @Override // com.facebook.react.bridge.JSBundleLoader
                public String loadScript(JSBundleLoaderDelegate delegate) {
                    Intrinsics.checkNotNullParameter(delegate, "delegate");
                    delegate.loadScriptFromFile(fileName, assetUrl, loadSynchronously);
                    return fileName;
                }
            };
        }

        @JvmStatic
        public final JSBundleLoader createCachedBundleFromNetworkLoader(final String sourceURL, final String cachedFileLocation) {
            Intrinsics.checkNotNullParameter(sourceURL, "sourceURL");
            Intrinsics.checkNotNullParameter(cachedFileLocation, "cachedFileLocation");
            return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader$Companion$createCachedBundleFromNetworkLoader$1
                @Override // com.facebook.react.bridge.JSBundleLoader
                public String loadScript(JSBundleLoaderDelegate delegate) {
                    Intrinsics.checkNotNullParameter(delegate, "delegate");
                    try {
                        delegate.loadScriptFromFile(cachedFileLocation, sourceURL, false);
                        return sourceURL;
                    } catch (Exception e) {
                        DebugServerException.Companion companion = DebugServerException.Companion;
                        String str = sourceURL;
                        String message = e.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        throw companion.makeGeneric(str, message, e);
                    }
                }
            };
        }

        @JvmStatic
        public final JSBundleLoader createCachedSplitBundleFromNetworkLoader(final String sourceURL, final String cachedFileLocation) {
            Intrinsics.checkNotNullParameter(sourceURL, "sourceURL");
            Intrinsics.checkNotNullParameter(cachedFileLocation, "cachedFileLocation");
            return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader$Companion$createCachedSplitBundleFromNetworkLoader$1
                @Override // com.facebook.react.bridge.JSBundleLoader
                public String loadScript(JSBundleLoaderDelegate delegate) {
                    Intrinsics.checkNotNullParameter(delegate, "delegate");
                    try {
                        delegate.loadSplitBundleFromFile(cachedFileLocation, sourceURL);
                        return sourceURL;
                    } catch (Exception e) {
                        DebugServerException.Companion companion = DebugServerException.Companion;
                        String str = sourceURL;
                        String message = e.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        throw companion.makeGeneric(str, message, e);
                    }
                }
            };
        }
    }
}
