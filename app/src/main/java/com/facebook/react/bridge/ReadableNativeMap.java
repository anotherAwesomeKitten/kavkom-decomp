package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReadableNativeMap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082 ¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006H\u0082 ¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006H\u0082 ¢\u0006\u0002\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J6\u0010\u001e\u001a\u0002H\u001f\"\u0006\b\u0000\u0010\u001f\u0018\u00012\u0006\u0010\u001c\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u000e2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001f0\"H\u0083\b¢\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J,\u0010$\u001a\u0002H\u001f\"\u0006\b\u0000\u0010\u001f\u0018\u00012\u0006\u0010\u001c\u001a\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001f0\"H\u0082\b¢\u0006\u0002\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J.\u0010&\u001a\u0004\u0018\u0001H\u001f\"\u0006\b\u0000\u0010\u001f\u0018\u00012\u0006\u0010\u001c\u001a\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001f0\"H\u0082\b¢\u0006\u0002\u0010%J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010,\u001a\u00020-2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0012\u0010.\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0012\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0012\u00101\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u00102\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u00103\u001a\u0002042\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\b\u0010:\u001a\u00020;H\u0016J\b\u0010<\u001a\u00020+H\u0016J\u0013\u0010=\u001a\u00020\u001b2\b\u0010>\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J(\u0010?\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000e0@j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000e`AH\u0016R\u0018\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00130\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R&\u00105\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e07068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109¨\u0006C"}, d2 = {"Lcom/facebook/react/bridge/ReadableNativeMap;", "Lcom/facebook/react/bridge/NativeMap;", "Lcom/facebook/react/bridge/ReadableMap;", "<init>", "()V", "keysStorage", "", "", "[Ljava/lang/String;", "keys", "getKeys", "()[Ljava/lang/String;", "localMapStorage", "", "", "localMap", "getLocalMap", "()Ljava/util/Map;", "localTypeMapStorage", "Lcom/facebook/react/bridge/ReadableType;", "localTypeMap", "getLocalTypeMap", "importKeys", "importValues", "()[Ljava/lang/Object;", "importTypes", "hasKey", "", "name", "isNull", "checkInstance", "T", "instance", "type", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "getValue", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "getNullableValue", "getBoolean", "getDouble", "", "getInt", "", "getLong", "", "getString", "getArray", "Lcom/facebook/react/bridge/ReadableArray;", "getMap", "getType", "getDynamic", "Lcom/facebook/react/bridge/Dynamic;", "entryIterator", "", "", "getEntryIterator", "()Ljava/util/Iterator;", "keySetIterator", "Lcom/facebook/react/bridge/ReadableMapKeySetIterator;", "hashCode", "equals", "other", "toHashMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static final Companion Companion = new Companion(null);
    private static int jniPassCounter;
    private String[] keysStorage;
    private Map<String, ? extends Object> localMapStorage;
    private Map<String, ? extends ReadableType> localTypeMapStorage;

    /* JADX INFO: compiled from: ReadableNativeMap.kt */
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

    private final native String[] importKeys();

    private final native Object[] importTypes();

    private final native Object[] importValues();

    protected ReadableNativeMap() {
    }

    private final String[] getKeys() {
        String[] strArrImportKeys;
        String[] strArr = this.keysStorage;
        if (strArr != null) {
            return strArr;
        }
        synchronized (this) {
            strArrImportKeys = this.keysStorage;
            if (strArrImportKeys == null) {
                strArrImportKeys = importKeys();
                this.keysStorage = strArrImportKeys;
                jniPassCounter++;
            }
        }
        return strArrImportKeys;
    }

    private final Map<String, Object> getLocalMap() {
        Map<String, ? extends Object> map;
        Map<String, ? extends Object> map2 = this.localMapStorage;
        if (map2 != null) {
            return map2;
        }
        synchronized (this) {
            Map<String, ? extends Object> map3 = this.localMapStorage;
            if (map3 == null) {
                String[] keys = getKeys();
                int length = keys.length;
                HashMap map4 = new HashMap();
                Object[] objArrImportValues = importValues();
                for (int i = 0; i < length; i++) {
                    map4.put(keys[i], objArrImportValues[i]);
                }
                this.localMapStorage = map4;
                jniPassCounter++;
                map3 = map4;
            }
            map = map3;
        }
        return map;
    }

    private final Map<String, ReadableType> getLocalTypeMap() {
        Map<String, ReadableType> map;
        Map map2 = this.localTypeMapStorage;
        if (map2 != null) {
            return map2;
        }
        synchronized (this) {
            Object obj = this.localTypeMapStorage;
            if (obj == null) {
                String[] keys = getKeys();
                Object map3 = new HashMap();
                Object[] objArrImportTypes = importTypes();
                int length = keys.length;
                for (int i = 0; i < length; i++) {
                    String str = keys[i];
                    Object obj2 = objArrImportTypes[i];
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.facebook.react.bridge.ReadableType");
                    ((Map) map3).put(str, (ReadableType) obj2);
                }
                this.localTypeMapStorage = (Map) map3;
                jniPassCounter++;
                obj = map3;
            }
            map = (Map) obj;
        }
        return map;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getLocalMap().containsKey(name);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (getLocalMap().containsKey(name)) {
            return getLocalMap().get(name) == null;
        }
        throw new NoSuchKeyException(name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T> T checkInstance(String name, Object instance, Class<T> type) {
        String simpleName;
        Class<?> cls;
        Intrinsics.reifiedOperationMarker(2, "T");
        if (instance != 0) {
            return instance;
        }
        if (instance == 0 || (cls = instance.getClass()) == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + type.getSimpleName());
    }

    private final Object getValue(String name) {
        if (hasKey(name)) {
            Object objAssertNotNull = Assertions.assertNotNull(getLocalMap().get(name));
            Intrinsics.checkNotNullExpressionValue(objAssertNotNull, "assertNotNull(...)");
            return objAssertNotNull;
        }
        throw new NoSuchKeyException(name);
    }

    private final /* synthetic */ <T> T getValue(String name, Class<T> type) {
        String simpleName;
        Class<?> cls;
        T t = (T) getValue(name);
        Intrinsics.reifiedOperationMarker(2, "T");
        if (t != null) {
            return t;
        }
        if (t == null || (cls = t.getClass()) == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + type.getSimpleName());
    }

    private final Object getNullableValue(String name) {
        return getLocalMap().get(name);
    }

    private final /* synthetic */ <T> T getNullableValue(String name, Class<T> type) {
        String simpleName;
        T t = (T) getNullableValue(name);
        if (t == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        if (t != null) {
            return t;
        }
        Class<?> cls = t.getClass();
        if (cls == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + type.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(String name) {
        String simpleName;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Boolean.TYPE;
        Object value = getValue(name);
        Boolean bool = (Boolean) (!(value instanceof Boolean) ? null : value);
        if (bool != null) {
            return bool.booleanValue();
        }
        if (value == null || (cls = value.getClass()) == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(String name) {
        String simpleName;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Double.TYPE;
        Object value = getValue(name);
        Double d = (Double) (!(value instanceof Double) ? null : value);
        if (d != null) {
            return d.doubleValue();
        }
        if (value == null || (cls = value.getClass()) == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(String name) {
        String simpleName;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Double.TYPE;
        Object value = getValue(name);
        Double d = (Double) (!(value instanceof Double) ? null : value);
        if (d != null) {
            return (int) d.doubleValue();
        }
        if (value == null || (cls = value.getClass()) == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public long getLong(String name) {
        String simpleName;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Long.TYPE;
        Object value = getValue(name);
        Long l = (Long) (!(value instanceof Long) ? null : value);
        if (l != null) {
            return l.longValue();
        }
        if (value == null || (cls = value.getClass()) == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public String getString(String name) {
        String simpleName;
        Intrinsics.checkNotNullParameter(name, "name");
        Object nullableValue = getNullableValue(name);
        if (nullableValue == null) {
            return null;
        }
        String str = (String) (nullableValue instanceof String ? nullableValue : null);
        if (str != null) {
            return str;
        }
        Class<?> cls = nullableValue.getClass();
        if (cls == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + String.class.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableArray getArray(String name) {
        String simpleName;
        Intrinsics.checkNotNullParameter(name, "name");
        Object nullableValue = getNullableValue(name);
        if (nullableValue == null) {
            return null;
        }
        ReadableArray readableArray = (ReadableArray) (nullableValue instanceof ReadableArray ? nullableValue : null);
        if (readableArray != null) {
            return readableArray;
        }
        Class<?> cls = nullableValue.getClass();
        if (cls == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to ReadableArray");
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableType getType(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ReadableType readableType = getLocalTypeMap().get(name);
        if (readableType != null) {
            return readableType;
        }
        throw new NoSuchKeyException(name);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Dynamic getDynamic(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return DynamicFromMap.INSTANCE.create(this, name);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        String[] keys = getKeys();
        Object[] objArrImportValues = importValues();
        jniPassCounter++;
        return new ReadableNativeMap$entryIterator$1(keys, objArrImportValues);
    }

    /* JADX INFO: renamed from: com.facebook.react.bridge.ReadableNativeMap$keySetIterator$1 */
    /* JADX INFO: compiled from: ReadableNativeMap.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"com/facebook/react/bridge/ReadableNativeMap$keySetIterator$1", "Lcom/facebook/react/bridge/ReadableMapKeySetIterator;", "currentIndex", "", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "hasNextKey", "", "nextKey", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements ReadableMapKeySetIterator {
        final /* synthetic */ String[] $iteratorKeys;
        private int currentIndex;

        AnonymousClass1(String[] strArr) {
            strArr = strArr;
        }

        public final int getCurrentIndex() {
            return this.currentIndex;
        }

        public final void setCurrentIndex(int i) {
            this.currentIndex = i;
        }

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public boolean hasNextKey() {
            return this.currentIndex < strArr.length;
        }

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public String nextKey() {
            String[] strArr = strArr;
            int i = this.currentIndex;
            this.currentIndex = i + 1;
            return strArr[i];
        }
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableMapKeySetIterator() { // from class: com.facebook.react.bridge.ReadableNativeMap.keySetIterator.1
            final /* synthetic */ String[] $iteratorKeys;
            private int currentIndex;

            AnonymousClass1(String[] strArr) {
                strArr = strArr;
            }

            public final int getCurrentIndex() {
                return this.currentIndex;
            }

            public final void setCurrentIndex(int i) {
                this.currentIndex = i;
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public boolean hasNextKey() {
                return this.currentIndex < strArr.length;
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public String nextKey() {
                String[] strArr = strArr;
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                return strArr[i];
            }
        };
    }

    public int hashCode() {
        return getLocalMap().hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof ReadableNativeMap) {
            return Intrinsics.areEqual(getLocalMap(), ((ReadableNativeMap) other).getLocalMap());
        }
        return false;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>(getLocalMap());
        for (String str : map.keySet()) {
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            String str2 = str;
            switch (WhenMappings.$EnumSwitchMapping$0[getType(str2).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    map.put(str2, ((ReadableNativeMap) Assertions.assertNotNull(getMap(str2))).toHashMap());
                    break;
                case 6:
                    map.put(str2, ((ReadableArray) Assertions.assertNotNull(getArray(str2))).toArrayList());
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return map;
    }

    /* JADX INFO: compiled from: ReadableNativeMap.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058G@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/facebook/react/bridge/ReadableNativeMap$Companion;", "", "<init>", "()V", "value", "", "jniPassCounter", "getJNIPassCounter", "()I", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int getJNIPassCounter() {
            return ReadableNativeMap.jniPassCounter;
        }
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableNativeMap getMap(String name) {
        String simpleName;
        Intrinsics.checkNotNullParameter(name, "name");
        Object nullableValue = getNullableValue(name);
        if (nullableValue == null) {
            return null;
        }
        ReadableNativeMap readableNativeMap = (ReadableNativeMap) (nullableValue instanceof ReadableNativeMap ? nullableValue : null);
        if (readableNativeMap != null) {
            return readableNativeMap;
        }
        Class<?> cls = nullableValue.getClass();
        if (cls == null || (simpleName = cls.getSimpleName()) == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to ReadableNativeMap");
    }
}
