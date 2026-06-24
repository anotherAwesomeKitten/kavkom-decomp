package com.facebook.react.bridge;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Arguments.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\tH\u0007J\u0018\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\n2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0007J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0002J \u0010\u0011\u001a\u00020\u000e2\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0012H\u0007J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0007J\u001d\u0010\u0019\u001a\u00020\u00072\u000e\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001bH\u0007¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0001H\u0007J\u0014\u0010\u001f\u001a\u00020\u00162\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\tH\u0007J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J*\u0010\"\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010#j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u0001`$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0007J\u0014\u0010'\u001a\u0004\u0018\u00010\u00142\b\u0010(\u001a\u0004\u0018\u00010)H\u0007¨\u0006*"}, d2 = {"Lcom/facebook/react/bridge/Arguments;", "", "<init>", "()V", "makeNativeObject", "value", "makeNativeArray", "Lcom/facebook/react/bridge/WritableNativeArray;", "objects", "", "T", "addEntry", "", "nativeMap", "Lcom/facebook/react/bridge/WritableNativeMap;", "key", "", "makeNativeMap", "", "bundle", "Landroid/os/Bundle;", "createArray", "Lcom/facebook/react/bridge/WritableArray;", "createMap", "Lcom/facebook/react/bridge/WritableMap;", "fromJavaArgs", "args", "", "([Ljava/lang/Object;)Lcom/facebook/react/bridge/WritableNativeArray;", "fromArray", "array", "fromList", "list", "fromBundle", "toList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "readableArray", "Lcom/facebook/react/bridge/ReadableArray;", "toBundle", "readableMap", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Arguments {
    public static final Arguments INSTANCE = new Arguments();

    /* JADX INFO: compiled from: Arguments.kt */
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

    private Arguments() {
    }

    private final Object makeNativeObject(Object value) {
        if (value == null) {
            return null;
        }
        if ((value instanceof Float) || (value instanceof Long) || (value instanceof Byte) || (value instanceof Short)) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        return value.getClass().isArray() ? makeNativeArray(value) : value instanceof List ? makeNativeArray((List<?>) value) : value instanceof Map ? makeNativeMap((Map<String, ? extends Object>) value) : value instanceof Bundle ? makeNativeMap((Bundle) value) : value instanceof JavaOnlyMap ? makeNativeMap(((JavaOnlyMap) value).toHashMap()) : value instanceof JavaOnlyArray ? makeNativeArray((List<?>) ((JavaOnlyArray) value).toArrayList()) : value;
    }

    @JvmStatic
    public static final WritableNativeArray makeNativeArray(List<?> objects) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        if (objects != null) {
            Iterator<?> it = objects.iterator();
            while (it.hasNext()) {
                Object objMakeNativeObject = INSTANCE.makeNativeObject(it.next());
                if (objMakeNativeObject == null) {
                    writableNativeArray.pushNull();
                } else if (objMakeNativeObject instanceof Boolean) {
                    writableNativeArray.pushBoolean(((Boolean) objMakeNativeObject).booleanValue());
                } else if (objMakeNativeObject instanceof Integer) {
                    writableNativeArray.pushInt(((Number) objMakeNativeObject).intValue());
                } else if (objMakeNativeObject instanceof Double) {
                    writableNativeArray.pushDouble(((Number) objMakeNativeObject).doubleValue());
                } else if (objMakeNativeObject instanceof String) {
                    writableNativeArray.pushString((String) objMakeNativeObject);
                } else if (objMakeNativeObject instanceof WritableNativeArray) {
                    writableNativeArray.pushArray((ReadableArray) objMakeNativeObject);
                } else {
                    if (!(objMakeNativeObject instanceof WritableNativeMap)) {
                        throw new IllegalArgumentException("Could not convert " + objMakeNativeObject.getClass());
                    }
                    writableNativeArray.pushMap((ReadableMap) objMakeNativeObject);
                }
            }
        }
        return writableNativeArray;
    }

    @JvmStatic
    public static final <T> WritableNativeArray makeNativeArray(Object objects) {
        if (objects == null) {
            return new WritableNativeArray();
        }
        return makeNativeArray((List<?>) new AbstractList<Object>() { // from class: com.facebook.react.bridge.Arguments.makeNativeArray.1
            final /* synthetic */ Object $objects;

            AnonymousClass1(Object objects2) {
                obj = objects2;
            }

            @Override // java.util.AbstractList, java.util.List
            public final /* bridge */ Object remove(int i) {
                return removeAt(i);
            }

            public /* bridge */ Object removeAt(int i) {
                return super.remove(i);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public final /* bridge */ int size() {
                return getSize();
            }

            public int getSize() {
                return Array.getLength(obj);
            }

            @Override // java.util.AbstractList, java.util.List
            public Object get(int i) {
                return Array.get(obj, i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.bridge.Arguments$makeNativeArray$1 */
    /* JADX INFO: compiled from: Arguments.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0096\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"com/facebook/react/bridge/Arguments$makeNativeArray$1", "Ljava/util/AbstractList;", "", RRWebVideoEvent.JsonKeys.SIZE, "", "getSize", "()I", "get", FirebaseAnalytics.Param.INDEX, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 extends AbstractList<Object> {
        final /* synthetic */ Object $objects;

        AnonymousClass1(Object objects2) {
            obj = objects2;
        }

        @Override // java.util.AbstractList, java.util.List
        public final /* bridge */ Object remove(int i) {
            return removeAt(i);
        }

        public /* bridge */ Object removeAt(int i) {
            return super.remove(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ int size() {
            return getSize();
        }

        public int getSize() {
            return Array.getLength(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            return Array.get(obj, i);
        }
    }

    private final void addEntry(WritableNativeMap nativeMap, String key, Object value) {
        Object objMakeNativeObject = makeNativeObject(value);
        if (objMakeNativeObject == null) {
            nativeMap.putNull(key);
            return;
        }
        if (objMakeNativeObject instanceof Boolean) {
            nativeMap.putBoolean(key, ((Boolean) objMakeNativeObject).booleanValue());
            return;
        }
        if (objMakeNativeObject instanceof Integer) {
            nativeMap.putInt(key, ((Number) objMakeNativeObject).intValue());
            return;
        }
        if (objMakeNativeObject instanceof Number) {
            nativeMap.putDouble(key, ((Number) objMakeNativeObject).doubleValue());
            return;
        }
        if (objMakeNativeObject instanceof String) {
            nativeMap.putString(key, (String) objMakeNativeObject);
            return;
        }
        if (objMakeNativeObject instanceof WritableNativeArray) {
            nativeMap.putArray(key, (ReadableArray) objMakeNativeObject);
        } else if (objMakeNativeObject instanceof WritableNativeMap) {
            nativeMap.putMap(key, (ReadableMap) objMakeNativeObject);
        } else {
            throw new IllegalArgumentException("Could not convert " + objMakeNativeObject.getClass());
        }
    }

    @JvmStatic
    public static final WritableNativeMap makeNativeMap(Map<String, ? extends Object> objects) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (objects != null) {
            for (Map.Entry<String, ? extends Object> entry : objects.entrySet()) {
                INSTANCE.addEntry(writableNativeMap, entry.getKey(), entry.getValue());
            }
        }
        return writableNativeMap;
    }

    @JvmStatic
    public static final WritableNativeMap makeNativeMap(Bundle bundle) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Arguments arguments = INSTANCE;
                Intrinsics.checkNotNull(str);
                arguments.addEntry(writableNativeMap, str, bundle.get(str));
            }
        }
        return writableNativeMap;
    }

    @JvmStatic
    public static final WritableArray createArray() {
        return new WritableNativeArray();
    }

    @JvmStatic
    public static final WritableMap createMap() {
        return new WritableNativeMap();
    }

    @Deprecated(message = "Use fromJavaArgs(Array<Any?>) instead. This method is added only to retain compatibility with Java consumers.")
    @JvmStatic
    public static final WritableNativeArray fromJavaArgs(Object args) {
        Intrinsics.checkNotNull(args, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        return fromJavaArgs((Object[]) args);
    }

    @JvmStatic
    public static final WritableNativeArray fromJavaArgs(Object[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        int length = args.length;
        for (int i = 0; i < length; i++) {
            Object obj = args[i];
            Class<?> cls = obj != null ? obj.getClass() : null;
            if (cls == null) {
                writableNativeArray.pushNull();
            } else if (Intrinsics.areEqual(cls, Boolean.TYPE) || Intrinsics.areEqual(cls, Boolean.class)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
                writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
            } else if (Intrinsics.areEqual(cls, Integer.TYPE) || Intrinsics.areEqual(cls, Integer.class)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Number");
                writableNativeArray.pushDouble(((Number) obj).doubleValue());
            } else if (Intrinsics.areEqual(cls, Double.TYPE) || Intrinsics.areEqual(cls, Double.class)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
                writableNativeArray.pushDouble(((Double) obj).doubleValue());
            } else if (Intrinsics.areEqual(cls, Float.TYPE)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
                writableNativeArray.pushDouble(((Float) obj).floatValue());
            } else if (Intrinsics.areEqual(cls, Float.class)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
                writableNativeArray.pushDouble(((Float) obj).floatValue());
            } else if (Intrinsics.areEqual(cls, String.class)) {
                writableNativeArray.pushString(obj.toString());
            } else if (Intrinsics.areEqual(cls, WritableNativeMap.class)) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.bridge.WritableNativeMap");
                writableNativeArray.pushMap((WritableNativeMap) obj);
            } else {
                if (!Intrinsics.areEqual(cls, WritableNativeArray.class)) {
                    throw new RuntimeException("Cannot convert argument of type " + cls);
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.bridge.WritableNativeArray");
                writableNativeArray.pushArray((WritableNativeArray) obj);
            }
        }
        return writableNativeArray;
    }

    @JvmStatic
    public static final WritableArray fromArray(Object array) {
        Intrinsics.checkNotNullParameter(array, "array");
        WritableArray writableArrayCreateArray = createArray();
        boolean z = array instanceof Object[];
        int i = 0;
        if (z && (((Object[]) array) instanceof String[])) {
            String[] strArr = (String[]) array;
            int length = strArr.length;
            while (i < length) {
                writableArrayCreateArray.pushString(strArr[i]);
                i++;
            }
        } else if (z && (((Object[]) array) instanceof Bundle[])) {
            Bundle[] bundleArr = (Bundle[]) array;
            int length2 = bundleArr.length;
            while (i < length2) {
                writableArrayCreateArray.pushMap(fromBundle(bundleArr[i]));
                i++;
            }
        } else if (array instanceof int[]) {
            int[] iArr = (int[]) array;
            int length3 = iArr.length;
            while (i < length3) {
                writableArrayCreateArray.pushInt(iArr[i]);
                i++;
            }
        } else if (array instanceof float[]) {
            int length4 = ((float[]) array).length;
            while (i < length4) {
                writableArrayCreateArray.pushDouble(r5[i]);
                i++;
            }
        } else if (array instanceof double[]) {
            double[] dArr = (double[]) array;
            int length5 = dArr.length;
            while (i < length5) {
                writableArrayCreateArray.pushDouble(dArr[i]);
                i++;
            }
        } else if (array instanceof boolean[]) {
            boolean[] zArr = (boolean[]) array;
            int length6 = zArr.length;
            while (i < length6) {
                writableArrayCreateArray.pushBoolean(zArr[i]);
                i++;
            }
        } else if (z && (((Object[]) array) instanceof Parcelable[])) {
            Parcelable[] parcelableArr = (Parcelable[]) array;
            int length7 = parcelableArr.length;
            while (i < length7) {
                Parcelable parcelable = parcelableArr[i];
                if (parcelable instanceof Bundle) {
                    writableArrayCreateArray.pushMap(fromBundle((Bundle) parcelable));
                    i++;
                } else {
                    throw new IllegalArgumentException("Unexpected array member type " + parcelable.getClass());
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown array type " + array.getClass());
        }
        return writableArrayCreateArray;
    }

    @JvmStatic
    public static final WritableArray fromList(List<?> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        WritableArray writableArrayCreateArray = createArray();
        for (Object obj : list) {
            if (obj == null) {
                writableArrayCreateArray.pushNull();
            } else if (obj.getClass().isArray()) {
                writableArrayCreateArray.pushArray(fromArray(obj));
            } else if (obj instanceof Bundle) {
                writableArrayCreateArray.pushMap(fromBundle((Bundle) obj));
            } else if (obj instanceof List) {
                writableArrayCreateArray.pushArray(fromList((List) obj));
            } else if (obj instanceof String) {
                writableArrayCreateArray.pushString((String) obj);
            } else if (obj instanceof Integer) {
                writableArrayCreateArray.pushInt(((Number) obj).intValue());
            } else if (obj instanceof Number) {
                writableArrayCreateArray.pushDouble(((Number) obj).doubleValue());
            } else {
                if (!(obj instanceof Boolean)) {
                    throw new IllegalArgumentException("Unknown value type " + obj.getClass());
                }
                writableArrayCreateArray.pushBoolean(((Boolean) obj).booleanValue());
            }
        }
        return writableArrayCreateArray;
    }

    @JvmStatic
    public static final WritableMap fromBundle(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        WritableMap writableMapCreateMap = createMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj == null) {
                Intrinsics.checkNotNull(str);
                writableMapCreateMap.putNull(str);
            } else if (obj.getClass().isArray()) {
                Intrinsics.checkNotNull(str);
                writableMapCreateMap.putArray(str, fromArray(obj));
            } else if (obj instanceof String) {
                Intrinsics.checkNotNull(str);
                writableMapCreateMap.putString(str, (String) obj);
            } else if (obj instanceof Number) {
                if (obj instanceof Integer) {
                    Intrinsics.checkNotNull(str);
                    writableMapCreateMap.putInt(str, ((Number) obj).intValue());
                } else {
                    Intrinsics.checkNotNull(str);
                    writableMapCreateMap.putDouble(str, ((Number) obj).doubleValue());
                }
            } else if (obj instanceof Boolean) {
                Intrinsics.checkNotNull(str);
                writableMapCreateMap.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Bundle) {
                Intrinsics.checkNotNull(str);
                writableMapCreateMap.putMap(str, fromBundle((Bundle) obj));
            } else {
                if (!(obj instanceof List)) {
                    throw new IllegalArgumentException("Could not convert " + obj.getClass());
                }
                Intrinsics.checkNotNull(str);
                writableMapCreateMap.putArray(str, fromList((List) obj));
            }
        }
        return writableMapCreateMap;
    }

    @JvmStatic
    public static final ArrayList<Object> toList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        ArrayList<Object> arrayList = new ArrayList<>();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            switch (WhenMappings.$EnumSwitchMapping$0[readableArray.getType(i).ordinal()]) {
                case 1:
                    arrayList.add(null);
                    break;
                case 2:
                    arrayList.add(Boolean.valueOf(readableArray.getBoolean(i)));
                    break;
                case 3:
                    double d = readableArray.getDouble(i);
                    if (d == Math.rint(d)) {
                        arrayList.add(Integer.valueOf((int) d));
                    } else {
                        arrayList.add(Double.valueOf(d));
                    }
                    break;
                case 4:
                    arrayList.add(readableArray.getString(i));
                    break;
                case 5:
                    arrayList.add(toBundle(readableArray.getMap(i)));
                    break;
                case 6:
                    arrayList.add(toList(readableArray.getArray(i)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object in array.");
            }
        }
        return arrayList;
    }

    @JvmStatic
    public static final Bundle toBundle(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        Bundle bundle = new Bundle();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            switch (WhenMappings.$EnumSwitchMapping$0[readableMap.getType(strNextKey).ordinal()]) {
                case 1:
                    bundle.putString(strNextKey, null);
                    break;
                case 2:
                    bundle.putBoolean(strNextKey, readableMap.getBoolean(strNextKey));
                    break;
                case 3:
                    bundle.putDouble(strNextKey, readableMap.getDouble(strNextKey));
                    break;
                case 4:
                    bundle.putString(strNextKey, readableMap.getString(strNextKey));
                    break;
                case 5:
                    bundle.putBundle(strNextKey, toBundle(readableMap.getMap(strNextKey)));
                    break;
                case 6:
                    bundle.putSerializable(strNextKey, toList(readableMap.getArray(strNextKey)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + strNextKey + ".");
            }
        }
        return bundle;
    }
}
