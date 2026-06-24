package com.swmansion.gesturehandler.react;

import com.swmansion.gesturehandler.core.FlingGestureHandler;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.HoverGestureHandler;
import com.swmansion.gesturehandler.core.LongPressGestureHandler;
import com.swmansion.gesturehandler.core.ManualGestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import com.swmansion.gesturehandler.core.PinchGestureHandler;
import com.swmansion.gesturehandler.core.RotationGestureHandler;
import com.swmansion.gesturehandler.core.TapGestureHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RNGestureHandlerFactoryUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006\"\b\b\u0000\u0010\n*\u00020\t2\u0006\u0010\u000b\u001a\u00020\tJ \u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006\"\b\b\u0000\u0010\n*\u00020\t2\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerFactoryUtil;", "", "<init>", "()V", "handlerFactories", "", "Lcom/swmansion/gesturehandler/core/GestureHandler$Factory;", "[Lcom/swmansion/gesturehandler/core/GestureHandler$Factory;", "findFactoryForHandler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "T", "handler", "findFactoryForName", "handlerName", "", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RNGestureHandlerFactoryUtil {
    public static final RNGestureHandlerFactoryUtil INSTANCE = new RNGestureHandlerFactoryUtil();
    private static final GestureHandler.Factory<?>[] handlerFactories = {new NativeViewGestureHandler.Factory(), new TapGestureHandler.Factory(), new LongPressGestureHandler.Factory(), new PanGestureHandler.Factory(), new PinchGestureHandler.Factory(), new RotationGestureHandler.Factory(), new FlingGestureHandler.Factory(), new ManualGestureHandler.Factory(), new HoverGestureHandler.Factory()};

    private RNGestureHandlerFactoryUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends GestureHandler> GestureHandler.Factory<GestureHandler> findFactoryForHandler(GestureHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        for (FlingGestureHandler.Factory factory : handlerFactories) {
            if (Intrinsics.areEqual(factory.getType(), handler.getClass())) {
                return factory;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends GestureHandler> GestureHandler.Factory<GestureHandler> findFactoryForName(String handlerName) {
        Intrinsics.checkNotNullParameter(handlerName, "handlerName");
        for (FlingGestureHandler.Factory factory : handlerFactories) {
            if (Intrinsics.areEqual(factory.getName(), handlerName)) {
                return factory;
            }
        }
        return null;
    }
}
