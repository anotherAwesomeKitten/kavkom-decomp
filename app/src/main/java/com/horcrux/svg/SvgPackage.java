package com.horcrux.svg;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.horcrux.svg.RenderableViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class SvgPackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private Map<String, ModuleSpec> mViewManagers;

    private Map<String, ModuleSpec> getViewManagersMap(ReactApplicationContext reactApplicationContext) {
        if (this.mViewManagers == null) {
            HashMap mapNewHashMap = MapBuilder.newHashMap();
            mapNewHashMap.put(RenderableViewManager.GroupViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.1
                AnonymousClass1() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.GroupViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.PathViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.2
                AnonymousClass2() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.PathViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.CircleViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.3
                AnonymousClass3() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.CircleViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.EllipseViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.4
                AnonymousClass4() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.EllipseViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.LineViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.5
                AnonymousClass5() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.LineViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.RectViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.6
                AnonymousClass6() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.RectViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.TextViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.7
                AnonymousClass7() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.TextViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.TSpanViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.8
                AnonymousClass8() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.TSpanViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.TextPathViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.9
                AnonymousClass9() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.TextPathViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.ImageViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.10
                AnonymousClass10() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.ImageViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.ClipPathViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.11
                AnonymousClass11() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.ClipPathViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.DefsViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.12
                AnonymousClass12() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.DefsViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.UseViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.13
                AnonymousClass13() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.UseViewManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.SymbolManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.14
                AnonymousClass14() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.SymbolManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.LinearGradientManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.15
                AnonymousClass15() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.LinearGradientManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.RadialGradientManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.16
                AnonymousClass16() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.RadialGradientManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.PatternManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.17
                AnonymousClass17() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.PatternManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.MaskManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.18
                AnonymousClass18() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.MaskManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FilterManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.19
                AnonymousClass19() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FilterManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeBlendManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.20
                AnonymousClass20() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeBlendManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeColorMatrixManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.21
                AnonymousClass21() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeColorMatrixManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeCompositeManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.22
                AnonymousClass22() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeCompositeManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeFloodManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.23
                AnonymousClass23() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeFloodManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeGaussianBlurManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.24
                AnonymousClass24() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeGaussianBlurManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeMergeManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.25
                AnonymousClass25() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeMergeManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.FeOffsetManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.26
                AnonymousClass26() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.FeOffsetManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.ForeignObjectManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.27
                AnonymousClass27() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.ForeignObjectManager();
                }
            }));
            mapNewHashMap.put(RenderableViewManager.MarkerManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.28
                AnonymousClass28() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new RenderableViewManager.MarkerManager();
                }
            }));
            mapNewHashMap.put(SvgViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider<NativeModule>() { // from class: com.horcrux.svg.SvgPackage.29
                AnonymousClass29() {
                }

                @Override // javax.inject.Provider
                public NativeModule get() {
                    return new SvgViewManager();
                }
            }));
            this.mViewManagers = mapNewHashMap;
        }
        return this.mViewManagers;
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$1 */
    class AnonymousClass1 implements Provider<NativeModule> {
        AnonymousClass1() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.GroupViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$2 */
    class AnonymousClass2 implements Provider<NativeModule> {
        AnonymousClass2() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.PathViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$3 */
    class AnonymousClass3 implements Provider<NativeModule> {
        AnonymousClass3() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.CircleViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$4 */
    class AnonymousClass4 implements Provider<NativeModule> {
        AnonymousClass4() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.EllipseViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$5 */
    class AnonymousClass5 implements Provider<NativeModule> {
        AnonymousClass5() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.LineViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$6 */
    class AnonymousClass6 implements Provider<NativeModule> {
        AnonymousClass6() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.RectViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$7 */
    class AnonymousClass7 implements Provider<NativeModule> {
        AnonymousClass7() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.TextViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$8 */
    class AnonymousClass8 implements Provider<NativeModule> {
        AnonymousClass8() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.TSpanViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$9 */
    class AnonymousClass9 implements Provider<NativeModule> {
        AnonymousClass9() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.TextPathViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$10 */
    class AnonymousClass10 implements Provider<NativeModule> {
        AnonymousClass10() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.ImageViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$11 */
    class AnonymousClass11 implements Provider<NativeModule> {
        AnonymousClass11() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.ClipPathViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$12 */
    class AnonymousClass12 implements Provider<NativeModule> {
        AnonymousClass12() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.DefsViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$13 */
    class AnonymousClass13 implements Provider<NativeModule> {
        AnonymousClass13() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.UseViewManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$14 */
    class AnonymousClass14 implements Provider<NativeModule> {
        AnonymousClass14() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.SymbolManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$15 */
    class AnonymousClass15 implements Provider<NativeModule> {
        AnonymousClass15() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.LinearGradientManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$16 */
    class AnonymousClass16 implements Provider<NativeModule> {
        AnonymousClass16() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.RadialGradientManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$17 */
    class AnonymousClass17 implements Provider<NativeModule> {
        AnonymousClass17() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.PatternManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$18 */
    class AnonymousClass18 implements Provider<NativeModule> {
        AnonymousClass18() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.MaskManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$19 */
    class AnonymousClass19 implements Provider<NativeModule> {
        AnonymousClass19() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FilterManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$20 */
    class AnonymousClass20 implements Provider<NativeModule> {
        AnonymousClass20() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeBlendManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$21 */
    class AnonymousClass21 implements Provider<NativeModule> {
        AnonymousClass21() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeColorMatrixManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$22 */
    class AnonymousClass22 implements Provider<NativeModule> {
        AnonymousClass22() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeCompositeManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$23 */
    class AnonymousClass23 implements Provider<NativeModule> {
        AnonymousClass23() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeFloodManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$24 */
    class AnonymousClass24 implements Provider<NativeModule> {
        AnonymousClass24() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeGaussianBlurManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$25 */
    class AnonymousClass25 implements Provider<NativeModule> {
        AnonymousClass25() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeMergeManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$26 */
    class AnonymousClass26 implements Provider<NativeModule> {
        AnonymousClass26() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.FeOffsetManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$27 */
    class AnonymousClass27 implements Provider<NativeModule> {
        AnonymousClass27() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.ForeignObjectManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$28 */
    class AnonymousClass28 implements Provider<NativeModule> {
        AnonymousClass28() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new RenderableViewManager.MarkerManager();
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$29 */
    class AnonymousClass29 implements Provider<NativeModule> {
        AnonymousClass29() {
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return new SvgViewManager();
        }
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public List<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap(reactApplicationContext).keySet());
    }

    @Override // com.facebook.react.BaseReactPackage
    protected List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap(reactApplicationContext).values());
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ModuleSpec moduleSpec = getViewManagersMap(reactApplicationContext).get(str);
        if (moduleSpec != null) {
            return (ViewManager) moduleSpec.getProvider().get();
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String str, @Nonnull ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        if (str.equals("RNSVGRenderableModule")) {
            return new RNSVGRenderableManager(reactApplicationContext);
        }
        if (str.equals("RNSVGSvgViewModule")) {
            return new SvgViewModule(reactApplicationContext);
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.horcrux.svg.SvgPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() { // from class: com.horcrux.svg.SvgPackage.30
                AnonymousClass30() {
                }

                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public Map<String, ReactModuleInfo> getReactModuleInfos() {
                    HashMap map = new HashMap();
                    Class[] clsArr = {SvgViewModule.class, RNSVGRenderableManager.class};
                    for (int i = 0; i < 2; i++) {
                        Class cls = clsArr[i];
                        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                        map.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), true));
                    }
                    return map;
                }
            };
        } catch (IllegalAccessException e) {
            e = e;
            throw new RuntimeException("No ReactModuleInfoProvider for MyPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            e = e2;
            throw new RuntimeException("No ReactModuleInfoProvider for MyPackage$$ReactModuleInfoProvider", e);
        }
    }

    /* JADX INFO: renamed from: com.horcrux.svg.SvgPackage$30 */
    class AnonymousClass30 implements ReactModuleInfoProvider {
        AnonymousClass30() {
        }

        @Override // com.facebook.react.module.model.ReactModuleInfoProvider
        public Map<String, ReactModuleInfo> getReactModuleInfos() {
            HashMap map = new HashMap();
            Class[] clsArr = {SvgViewModule.class, RNSVGRenderableManager.class};
            for (int i = 0; i < 2; i++) {
                Class cls = clsArr[i];
                ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                map.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), true));
            }
            return map;
        }
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
}
