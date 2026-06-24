package com.facebook.react.bridge;

import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReadableNativeArray.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 -2\u00020\u00012\u00020\u0002:\u0001-B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0082 ¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082 ¢\u0006\u0002\u0010\u000bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010'\u001a\u00020\u0015H\u0016J\u0013\u0010(\u001a\u00020\u00172\b\u0010)\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J\u0011\u0010*\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u0000H\u0082 J\u0010\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070,H\u0016R\u001a\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u001c\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006."}, d2 = {"Lcom/facebook/react/bridge/ReadableNativeArray;", "Lcom/facebook/react/bridge/NativeArray;", "Lcom/facebook/react/bridge/ReadableArray;", "<init>", "()V", "localArrayStorage", "", "", "[Ljava/lang/Object;", "localArray", "getLocalArray", "()[Ljava/lang/Object;", "importArray", "localTypeArrayStorage", "Lcom/facebook/react/bridge/ReadableType;", "[Lcom/facebook/react/bridge/ReadableType;", "localTypeArray", "getLocalTypeArray", "()[Lcom/facebook/react/bridge/ReadableType;", "importTypeArray", RRWebVideoEvent.JsonKeys.SIZE, "", "isNull", "", FirebaseAnalytics.Param.INDEX, "getBoolean", "getDouble", "", "getInt", "getLong", "", "getString", "", "getArray", "getMap", "Lcom/facebook/react/bridge/ReadableNativeMap;", "getType", "getDynamic", "Lcom/facebook/react/bridge/Dynamic;", "hashCode", "equals", "other", "nativeEquals", "toArrayList", "Ljava/util/ArrayList;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    private static final Companion Companion = new Companion(null);
    private static int jniPassCounter;
    private Object[] localArrayStorage;
    private ReadableType[] localTypeArrayStorage;

    /* JADX INFO: compiled from: ReadableNativeArray.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final int getJNIPassCounter() {
        return Companion.getJNIPassCounter();
    }

    private final native Object[] importArray();

    private final native Object[] importTypeArray();

    private final native boolean nativeEquals(ReadableNativeArray other);

    protected ReadableNativeArray() {
    }

    private final Object[] getLocalArray() {
        Object[] objArrImportArray;
        Object[] objArr = this.localArrayStorage;
        if (objArr != null) {
            return objArr;
        }
        synchronized (this) {
            objArrImportArray = this.localArrayStorage;
            if (objArrImportArray == null) {
                objArrImportArray = importArray();
                this.localArrayStorage = objArrImportArray;
                jniPassCounter++;
            }
        }
        return objArrImportArray;
    }

    private final ReadableType[] getLocalTypeArray() {
        ReadableType[] readableTypeArr;
        ReadableType[] readableTypeArr2 = this.localTypeArrayStorage;
        if (readableTypeArr2 != null) {
            return readableTypeArr2;
        }
        synchronized (this) {
            Object element = this.localTypeArrayStorage;
            if (element == null) {
                Object[] objArrImportTypeArray = importTypeArray();
                element = Arrays.copyOf(objArrImportTypeArray, objArrImportTypeArray.length, ReadableType[].class);
                this.localTypeArrayStorage = (ReadableType[]) element;
                jniPassCounter++;
            }
            Intrinsics.checkNotNullExpressionValue(element, "element");
            readableTypeArr = (ReadableType[]) element;
        }
        return readableTypeArr;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        return getLocalArray().length;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int i) {
        return getLocalArray()[i] == null;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) obj).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) obj).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
        return (int) ((Double) obj).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public long getLong(int i) {
        Object obj = getLocalArray()[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        return ((Long) obj).longValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public String getString(int i) {
        return (String) getLocalArray()[i];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeArray getArray(int i) {
        return (ReadableNativeArray) getLocalArray()[i];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeMap getMap(int i) {
        return (ReadableNativeMap) getLocalArray()[i];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableType getType(int i) {
        return getLocalTypeArray()[i];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public Dynamic getDynamic(int i) {
        return DynamicFromArray.INSTANCE.create(this, i);
    }

    public int hashCode() {
        return getLocalArray().hashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ReadableNativeArray)) {
            return false;
        }
        if (ReactNativeFeatureFlags.useNativeEqualsInNativeReadableArrayAndroid()) {
            return nativeEquals((ReadableNativeArray) other);
        }
        return ArraysKt.contentDeepEquals(getLocalArray(), ((ReadableNativeArray) other).getLocalArray());
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        int size = size();
        for (int i = 0; i < size; i++) {
            switch (WhenMappings.$EnumSwitchMapping$0[getType(i).ordinal()]) {
                case 1:
                    arrayList.add(null);
                    break;
                case 2:
                    arrayList.add(Boolean.valueOf(getBoolean(i)));
                    break;
                case 3:
                    arrayList.add(Double.valueOf(getDouble(i)));
                    break;
                case 4:
                    arrayList.add(getString(i));
                    break;
                case 5:
                    ReadableNativeMap map = getMap(i);
                    arrayList.add(map != null ? map.toHashMap() : null);
                    break;
                case 6:
                    ReadableNativeArray array = getArray(i);
                    arrayList.add(array != null ? array.toArrayList() : null);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: ReadableNativeArray.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058G@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/facebook/react/bridge/ReadableNativeArray$Companion;", "", "<init>", "()V", "value", "", "jniPassCounter", "getJNIPassCounter", "()I", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int getJNIPassCounter() {
            return ReadableNativeArray.jniPassCounter;
        }
    }
}
