package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
class FontRequestWorker {
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    static final Object LOCK = new Object();
    static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();

    private FontRequestWorker() {
    }

    static void resetTypefaceCache() {
        sTypefaceCache.evictAll();
    }

    static Typeface requestFontSync(Context context, FontRequest fontRequest, CallbackWithHandler callbackWithHandler, int i, int i2) {
        String strCreateCacheId = createCacheId(fontRequest, i);
        Typeface typeface = sTypefaceCache.get(strCreateCacheId);
        if (typeface != null) {
            callbackWithHandler.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        }
        if (i2 == -1) {
            TypefaceResult fontSync = getFontSync(strCreateCacheId, context, fontRequest, i);
            callbackWithHandler.onTypefaceResult(fontSync);
            return fontSync.mTypeface;
        }
        try {
            TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.1
                final /* synthetic */ Context val$context;
                final /* synthetic */ String val$id;
                final /* synthetic */ FontRequest val$request;
                final /* synthetic */ int val$style;

                AnonymousClass1(String strCreateCacheId2, Context context2, FontRequest fontRequest2, int i3) {
                    str = strCreateCacheId2;
                    context = context2;
                    fontRequest = fontRequest2;
                    i = i3;
                }

                @Override // java.util.concurrent.Callable
                public TypefaceResult call() {
                    return FontRequestWorker.getFontSync(str, context, fontRequest, i);
                }
            }, i2);
            callbackWithHandler.onTypefaceResult(typefaceResult);
            return typefaceResult.mTypeface;
        } catch (InterruptedException unused) {
            callbackWithHandler.onTypefaceResult(new TypefaceResult(-3));
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.core.provider.FontRequestWorker$1 */
    class AnonymousClass1 implements Callable<TypefaceResult> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        AnonymousClass1(String strCreateCacheId2, Context context2, FontRequest fontRequest2, int i3) {
            str = strCreateCacheId2;
            context = context2;
            fontRequest = fontRequest2;
            i = i3;
        }

        @Override // java.util.concurrent.Callable
        public TypefaceResult call() {
            return FontRequestWorker.getFontSync(str, context, fontRequest, i);
        }
    }

    static Typeface requestFontAsync(Context context, FontRequest fontRequest, int i, Executor executor, CallbackWithHandler callbackWithHandler) {
        String strCreateCacheId = createCacheId(fontRequest, i);
        Typeface typeface = sTypefaceCache.get(strCreateCacheId);
        if (typeface != null) {
            callbackWithHandler.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        }
        AnonymousClass2 anonymousClass2 = new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.2
            AnonymousClass2() {
            }

            @Override // androidx.core.util.Consumer
            public void accept(TypefaceResult typefaceResult) {
                if (typefaceResult == null) {
                    typefaceResult = new TypefaceResult(-3);
                }
                callbackWithHandler.onTypefaceResult(typefaceResult);
            }
        };
        synchronized (LOCK) {
            SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> simpleArrayMap = PENDING_REPLIES;
            ArrayList<Consumer<TypefaceResult>> arrayList = simpleArrayMap.get(strCreateCacheId);
            if (arrayList != null) {
                arrayList.add(anonymousClass2);
                return null;
            }
            ArrayList<Consumer<TypefaceResult>> arrayList2 = new ArrayList<>();
            arrayList2.add(anonymousClass2);
            simpleArrayMap.put(strCreateCacheId, arrayList2);
            AnonymousClass3 anonymousClass3 = new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.3
                final /* synthetic */ Context val$context;
                final /* synthetic */ String val$id;
                final /* synthetic */ FontRequest val$request;
                final /* synthetic */ int val$style;

                AnonymousClass3(String strCreateCacheId2, Context context2, FontRequest fontRequest2, int i2) {
                    str = strCreateCacheId2;
                    context = context2;
                    fontRequest = fontRequest2;
                    i = i2;
                }

                @Override // java.util.concurrent.Callable
                public TypefaceResult call() {
                    try {
                        return FontRequestWorker.getFontSync(str, context, fontRequest, i);
                    } catch (Throwable unused) {
                        return new TypefaceResult(-3);
                    }
                }
            };
            if (executor == null) {
                executor = DEFAULT_EXECUTOR_SERVICE;
            }
            RequestExecutor.execute(executor, anonymousClass3, new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.4
                final /* synthetic */ String val$id;

                AnonymousClass4(String strCreateCacheId2) {
                    str = strCreateCacheId2;
                }

                @Override // androidx.core.util.Consumer
                public void accept(TypefaceResult typefaceResult) {
                    synchronized (FontRequestWorker.LOCK) {
                        ArrayList<Consumer<TypefaceResult>> arrayList3 = FontRequestWorker.PENDING_REPLIES.get(str);
                        if (arrayList3 == null) {
                            return;
                        }
                        FontRequestWorker.PENDING_REPLIES.remove(str);
                        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                            arrayList3.get(i2).accept(typefaceResult);
                        }
                    }
                }
            });
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.core.provider.FontRequestWorker$2 */
    class AnonymousClass2 implements Consumer<TypefaceResult> {
        AnonymousClass2() {
        }

        @Override // androidx.core.util.Consumer
        public void accept(TypefaceResult typefaceResult) {
            if (typefaceResult == null) {
                typefaceResult = new TypefaceResult(-3);
            }
            callbackWithHandler.onTypefaceResult(typefaceResult);
        }
    }

    /* JADX INFO: renamed from: androidx.core.provider.FontRequestWorker$3 */
    class AnonymousClass3 implements Callable<TypefaceResult> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        AnonymousClass3(String strCreateCacheId2, Context context2, FontRequest fontRequest2, int i2) {
            str = strCreateCacheId2;
            context = context2;
            fontRequest = fontRequest2;
            i = i2;
        }

        @Override // java.util.concurrent.Callable
        public TypefaceResult call() {
            try {
                return FontRequestWorker.getFontSync(str, context, fontRequest, i);
            } catch (Throwable unused) {
                return new TypefaceResult(-3);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.core.provider.FontRequestWorker$4 */
    class AnonymousClass4 implements Consumer<TypefaceResult> {
        final /* synthetic */ String val$id;

        AnonymousClass4(String strCreateCacheId2) {
            str = strCreateCacheId2;
        }

        @Override // androidx.core.util.Consumer
        public void accept(TypefaceResult typefaceResult) {
            synchronized (FontRequestWorker.LOCK) {
                ArrayList<Consumer<TypefaceResult>> arrayList3 = FontRequestWorker.PENDING_REPLIES.get(str);
                if (arrayList3 == null) {
                    return;
                }
                FontRequestWorker.PENDING_REPLIES.remove(str);
                for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                    arrayList3.get(i2).accept(typefaceResult);
                }
            }
        }
    }

    private static String createCacheId(FontRequest fontRequest, int i) {
        return fontRequest.getId() + "-" + i;
    }

    static TypefaceResult getFontSync(String str, Context context, FontRequest fontRequest, int i) {
        LruCache<String, Typeface> lruCache = sTypefaceCache;
        Typeface typeface = lruCache.get(str);
        if (typeface != null) {
            return new TypefaceResult(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult fontFamilyResult = FontProvider.getFontFamilyResult(context, fontRequest, null);
            int fontFamilyResultStatus = getFontFamilyResultStatus(fontFamilyResult);
            if (fontFamilyResultStatus != 0) {
                return new TypefaceResult(fontFamilyResultStatus);
            }
            Typeface typefaceCreateFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fontFamilyResult.getFonts(), i);
            if (typefaceCreateFromFontInfo != null) {
                lruCache.put(str, typefaceCreateFromFontInfo);
                return new TypefaceResult(typefaceCreateFromFontInfo);
            }
            return new TypefaceResult(-3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(-1);
        }
    }

    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i = 1;
        if (fontFamilyResult.getStatusCode() != 0) {
            return fontFamilyResult.getStatusCode() != 1 ? -3 : -2;
        }
        FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
        if (fonts != null && fonts.length != 0) {
            i = 0;
            for (FontsContractCompat.FontInfo fontInfo : fonts) {
                int resultCode = fontInfo.getResultCode();
                if (resultCode != 0) {
                    if (resultCode < 0) {
                        return -3;
                    }
                    return resultCode;
                }
            }
        }
        return i;
    }

    static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(int i) {
            this.mTypeface = null;
            this.mResult = i;
        }

        TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }

        boolean isSuccess() {
            return this.mResult == 0;
        }
    }
}
