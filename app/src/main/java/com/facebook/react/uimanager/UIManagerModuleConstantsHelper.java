package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UIManagerModuleConstantsHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0010\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\fJ&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015H\u0002Jc\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\b\u0000\u0012\u00020\u0019\u0012\u0006\b\u0000\u0012\u00020\u00190\u00180\u00172\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015H\u0001¢\u0006\u0002\b\fJ\u0089\u0001\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00152\u0016\u0010\u001d\u001a\u0012\u0012\u0006\b\u0000\u0012\u00020\u0019\u0012\u0006\b\u0000\u0012\u00020\u00190\u00182\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015H\u0001¢\u0006\u0002\b\"J-\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00152\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0015H\u0001¢\u0006\u0002\b%J4\u0010&\u001a\u00020\u00122\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010¨\u0006)"}, d2 = {"Lcom/facebook/react/uimanager/UIManagerModuleConstantsHelper;", "", "<init>", "()V", "TAG", "", "BUBBLING_EVENTS_KEY", "DIRECT_EVENTS_KEY", "createConstants", "", "resolver", "Lcom/facebook/react/uimanager/ViewManagerResolver;", "internal_createConstants", "defaultExportableEventTypes", "getDefaultExportableEventTypes$annotations", "getDefaultExportableEventTypes", "()Ljava/util/Map;", "validateDirectEventNames", "", "viewManagerName", "directEvents", "", "viewManagers", "", "Lcom/facebook/react/uimanager/ViewManager;", "", "allBubblingEventTypes", "allDirectEventTypes", "createConstantsForViewManager", "viewManager", "defaultBubblingEvents", "defaultDirectEvents", "cumulativeBubblingEventTypes", "cumulativeDirectEventTypes", "internal_createConstantsForViewManager", "normalizeEventTypes", "eventsToNormalize", "normalizeEventTypes$ReactAndroid_release", "recursiveMerge", "dest", "source", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class UIManagerModuleConstantsHelper {
    private static final String BUBBLING_EVENTS_KEY = "bubblingEventTypes";
    private static final String DIRECT_EVENTS_KEY = "directEventTypes";
    public static final UIManagerModuleConstantsHelper INSTANCE = new UIManagerModuleConstantsHelper();
    private static final String TAG = "UIManagerModuleConstantsHelper";

    @JvmStatic
    public static /* synthetic */ void getDefaultExportableEventTypes$annotations() {
    }

    private UIManagerModuleConstantsHelper() {
    }

    @JvmStatic
    public static final Map<String, Object> internal_createConstants(ViewManagerResolver resolver) {
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        return MapsKt.plus(UIManagerModuleConstants.constants, MapsKt.mapOf(TuplesKt.to("ViewManagerNames", new ArrayList(resolver.getViewManagerNames())), TuplesKt.to("LazyViewManagersEnabled", true)));
    }

    public static final Map<String, Object> getDefaultExportableEventTypes() {
        return MapsKt.mapOf(TuplesKt.to(BUBBLING_EVENTS_KEY, UIManagerModuleConstants.bubblingEventTypeConstants), TuplesKt.to(DIRECT_EVENTS_KEY, UIManagerModuleConstants.directEventTypeConstants));
    }

    private final void validateDirectEventNames(String viewManagerName, Map<String, Object> directEvents) {
        String str;
        if (!ReactBuildConfig.DEBUG || directEvents == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : directEvents.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (TypeIntrinsics.isMutableMap(value) && (str = (String) ((Map) value).get("registrationName")) != null && StringsKt.startsWith$default(key, "top", false, 2, (Object) null) && StringsKt.startsWith$default(str, "on", false, 2, (Object) null)) {
                String strSubstring = key.substring(3);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String strSubstring2 = str.substring(2);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                if (!Intrinsics.areEqual(strSubstring, strSubstring2)) {
                    FLog.e(TAG, "Direct event name for '" + viewManagerName + "' doesn't correspond to the naming convention, expected 'topEventName'->'onEventName', got '" + key + "'->'" + str + "'");
                }
            }
        }
    }

    @JvmStatic
    public static final Map<String, Object> internal_createConstants(List<? extends ViewManager> viewManagers, Map<String, Object> allBubblingEventTypes, Map<String, Object> allDirectEventTypes) {
        Intrinsics.checkNotNullParameter(viewManagers, "viewManagers");
        Map<String, Object> mutableMap = MapsKt.toMutableMap(UIManagerModuleConstants.constants);
        Map<String, Object> map = UIManagerModuleConstants.bubblingEventTypeConstants;
        Map<String, Object> map2 = UIManagerModuleConstants.directEventTypeConstants;
        if (allBubblingEventTypes != null) {
            allBubblingEventTypes.putAll(map);
        }
        if (allDirectEventTypes != null) {
            allDirectEventTypes.putAll(map2);
        }
        for (ViewManager viewManager : viewManagers) {
            String name = viewManager.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            Map<String, Object> mapInternal_createConstantsForViewManager = internal_createConstantsForViewManager(viewManager, null, null, allBubblingEventTypes, allDirectEventTypes);
            if (!mapInternal_createConstantsForViewManager.isEmpty()) {
                mutableMap.put(name, mapInternal_createConstantsForViewManager);
            }
        }
        mutableMap.put("genericBubblingEventTypes", map);
        mutableMap.put("genericDirectEventTypes", map2);
        return mutableMap;
    }

    @JvmStatic
    public static final Map<String, Object> internal_createConstantsForViewManager(ViewManager viewManager, Map<String, Object> defaultBubblingEvents, Map<String, Object> defaultDirectEvents, Map<String, Object> cumulativeBubblingEventTypes, Map<String, Object> cumulativeDirectEventTypes) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Map<String, Object> exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants != null) {
            if (ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer() && ReactNativeNewArchitectureFeatureFlags.useFabricInterop()) {
                exportedCustomBubblingEventTypeConstants = INSTANCE.normalizeEventTypes$ReactAndroid_release(exportedCustomBubblingEventTypeConstants);
            }
            UIManagerModuleConstantsHelper uIManagerModuleConstantsHelper = INSTANCE;
            uIManagerModuleConstantsHelper.recursiveMerge(cumulativeBubblingEventTypes, exportedCustomBubblingEventTypeConstants);
            uIManagerModuleConstantsHelper.recursiveMerge(exportedCustomBubblingEventTypeConstants, defaultBubblingEvents);
            linkedHashMap.put(BUBBLING_EVENTS_KEY, exportedCustomBubblingEventTypeConstants);
        } else if (defaultBubblingEvents != null) {
            linkedHashMap.put(BUBBLING_EVENTS_KEY, defaultBubblingEvents);
        }
        Map<String, Object> exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
        UIManagerModuleConstantsHelper uIManagerModuleConstantsHelper2 = INSTANCE;
        String name = viewManager.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        uIManagerModuleConstantsHelper2.validateDirectEventNames(name, exportedCustomDirectEventTypeConstants);
        if (exportedCustomDirectEventTypeConstants != null) {
            if (ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer() && ReactNativeNewArchitectureFeatureFlags.useFabricInterop()) {
                exportedCustomDirectEventTypeConstants = uIManagerModuleConstantsHelper2.normalizeEventTypes$ReactAndroid_release(exportedCustomDirectEventTypeConstants);
            }
            uIManagerModuleConstantsHelper2.recursiveMerge(cumulativeDirectEventTypes, exportedCustomDirectEventTypeConstants);
            uIManagerModuleConstantsHelper2.recursiveMerge(exportedCustomDirectEventTypeConstants, defaultDirectEvents);
            linkedHashMap.put(DIRECT_EVENTS_KEY, exportedCustomDirectEventTypeConstants);
        } else if (defaultDirectEvents != null) {
            linkedHashMap.put(DIRECT_EVENTS_KEY, defaultDirectEvents);
        }
        Map<String, Object> exportedViewConstants = viewManager.getExportedViewConstants();
        if (exportedViewConstants != null) {
            linkedHashMap.put("Constants", exportedViewConstants);
        }
        Map<String, Integer> commandsMap = viewManager.getCommandsMap();
        if (commandsMap != null) {
            linkedHashMap.put("Commands", commandsMap);
        }
        Map<String, String> nativeProps = viewManager.getNativeProps();
        if (!nativeProps.isEmpty()) {
            Intrinsics.checkNotNull(nativeProps);
            linkedHashMap.put("NativeProps", nativeProps);
        }
        return linkedHashMap;
    }

    public final Map<String, Object> normalizeEventTypes$ReactAndroid_release(Map<String, Object> eventsToNormalize) {
        String strSubstring;
        Intrinsics.checkNotNullParameter(eventsToNormalize, "eventsToNormalize");
        HashSet<String> hashSet = new HashSet();
        for (String str : eventsToNormalize.keySet()) {
            if (!StringsKt.startsWith$default(str, "top", false, 2, (Object) null)) {
                hashSet.add(str);
            }
        }
        if (!(eventsToNormalize instanceof HashMap)) {
            eventsToNormalize = new HashMap(eventsToNormalize);
        }
        for (String str2 : hashSet) {
            Object obj = eventsToNormalize.get(str2);
            if (obj == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (StringsKt.startsWith$default(str2, "on", false, 2, (Object) null)) {
                strSubstring = str2.substring(2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            } else {
                String strSubstring2 = str2.substring(0, 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String upperCase = strSubstring2.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                String strSubstring3 = str2.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                strSubstring = upperCase + strSubstring3;
            }
            eventsToNormalize.put("top" + strSubstring, obj);
        }
        return eventsToNormalize;
    }

    private final void recursiveMerge(Map<String, Object> dest, Map<String, Object> source) {
        if (dest == null || source == null || source.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object obj = dest.get(key);
            if (obj != null && TypeIntrinsics.isMutableMap(value) && TypeIntrinsics.isMutableMap(obj)) {
                if (!(obj instanceof HashMap)) {
                    HashMap map = new HashMap((Map) obj);
                    dest.replace(key, TypeIntrinsics.asMutableMap(map));
                    obj = map;
                }
                Map<String, Object> mapAsMutableMap = TypeIntrinsics.asMutableMap(obj);
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any>");
                recursiveMerge(mapAsMutableMap, TypeIntrinsics.asMutableMap(value));
            } else {
                dest.put(key, value);
            }
        }
    }
}
