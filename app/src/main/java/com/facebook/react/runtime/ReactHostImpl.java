package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.MemoryPressureRouter;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.devsupport.DefaultDevSupportManagerFactory;
import com.facebook.react.devsupport.DevMenuConfiguration;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.InspectorFlags;
import com.facebook.react.devsupport.inspector.InspectorNetworkHelper;
import com.facebook.react.devsupport.inspector.InspectorNetworkRequestListener;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.facebook.react.runtime.BridgelessAtomicRef;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;
import com.facebook.react.runtime.internal.bolts.TaskCompletionSource;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.BlackHoleEventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.clientreport.DiscardedEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: ReactHostImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0003\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 \u0087\u00022\u00020\u0001:\u0004\u0086\u0002\u0087\u0002BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011B1\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0013J\u000e\u0010I\u001a\b\u0012\u0004\u0012\u00020K0JH\u0016J\u001b\u0010L\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010M\u001a\u00020 H\u0000¢\u0006\u0002\bNJ\u001b\u0010O\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010M\u001a\u00020 H\u0000¢\u0006\u0002\bPJ\u001b\u0010Q\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010M\u001a\u00020 H\u0000¢\u0006\u0002\bRJ\u001c\u0010S\u001a\u00020=2\b\u0010*\u001a\u0004\u0018\u00010,2\b\u0010T\u001a\u0004\u0018\u000107H\u0017J\u0012\u0010S\u001a\u00020=2\b\u0010*\u001a\u0004\u0018\u00010,H\u0017J\u0012\u0010U\u001a\u00020=2\b\u0010*\u001a\u0004\u0018\u00010,H\u0017J\u0012\u0010V\u001a\u00020=2\b\u0010*\u001a\u0004\u0018\u00010,H\u0017J\b\u0010V\u001a\u00020=H\u0017J\b\u0010W\u001a\u00020=H\u0017J\u0012\u0010W\u001a\u00020=2\b\u0010*\u001a\u0004\u0018\u00010,H\u0017J\u0010\u0010X\u001a\u00020=2\u0006\u0010Y\u001a\u00020\fH\u0002J\"\u0010^\u001a\u00020_2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010`\u001a\u00020a2\b\u0010b\u001a\u0004\u0018\u00010cH\u0016J\b\u0010g\u001a\u00020\fH\u0017J\u0010\u0010l\u001a\u00020=2\u0006\u0010m\u001a\u00020:H\u0016J\u0010\u0010n\u001a\u00020=2\u0006\u0010m\u001a\u00020:H\u0016J\u0010\u0010o\u001a\u00020=2\u0006\u0010p\u001a\u00020qH\u0016J\u0016\u0010r\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010s\u001a\u00020aH\u0016J\u0012\u0010t\u001a\u00020=2\b\u0010u\u001a\u0004\u0018\u00010aH\u0003J\u0018\u0010z\u001a\u00020=2\u0006\u0010{\u001a\u00020a2\u0006\u0010m\u001a\u00020|H\u0003JO\u0010}\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010s\u001a\u00020a2\u000f\u0010~\u001a\u000b\u0018\u00010\u007fj\u0005\u0018\u0001`\u0080\u00012&\u0010\u0081\u0001\u001a!\u0012\u0016\u0012\u00140\f¢\u0006\u000f\b\u0083\u0001\u0012\n\b\u0084\u0001\u0012\u0005\b\b(\u0085\u0001\u0012\u0004\u0012\u00020=0\u0082\u0001H\u0016J'\u0010}\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010s\u001a\u00020a2\u000f\u0010~\u001a\u000b\u0018\u00010\u007fj\u0005\u0018\u0001`\u0080\u0001H\u0016J\u0011\u0010\u0086\u0001\u001a\u0002052\u0006\u0010%\u001a\u00020$H\u0002J,\u0010\u0096\u0001\u001a\u00020\f\"\n\b\u0000\u0010\u0097\u0001*\u00030\u0098\u00012\u000f\u0010\u0099\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0097\u00010\u009a\u0001H\u0000¢\u0006\u0003\b\u009b\u0001J2\u0010 \u0001\u001a\u0005\u0018\u0001H\u0097\u0001\"\n\b\u0000\u0010\u0097\u0001*\u00030\u0098\u00012\u000f\u0010\u0099\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0097\u00010\u009a\u0001H\u0000¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u001b\u0010 \u0001\u001a\u0005\u0018\u00010\u0098\u00012\u0007\u0010£\u0001\u001a\u00020aH\u0000¢\u0006\u0003\b¡\u0001J/\u0010¬\u0001\u001a\u00020=2\u0006\u0010*\u001a\u00020,2\u0007\u0010\u00ad\u0001\u001a\u00020)2\u0007\u0010®\u0001\u001a\u00020)2\n\u0010¯\u0001\u001a\u0005\u0018\u00010°\u0001H\u0017J\u0012\u0010±\u0001\u001a\u00020=2\u0007\u0010²\u0001\u001a\u00020\fH\u0017J\u0013\u0010³\u0001\u001a\u00020=2\b\u0010´\u0001\u001a\u00030°\u0001H\u0017J\u0012\u0010µ\u0001\u001a\u00020=2\u0007\u0010¶\u0001\u001a\u00020aH\u0017JH\u0010µ\u0001\u001a\u00020=2\u0007\u0010·\u0001\u001a\u00020a2\u0006\u0010`\u001a\u00020a2,\u0010¸\u0001\u001a'\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020a0w\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020a0w0\u0082\u0001H\u0017J\u0011\u0010¹\u0001\u001a\u00020=2\u0006\u0010\u0002\u001a\u00020\u0003H\u0017J\u001f\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00020\f0#2\b\u0010Â\u0001\u001a\u00030Ã\u0001H\u0000¢\u0006\u0003\bÄ\u0001J3\u0010Å\u0001\u001a\b\u0012\u0004\u0012\u00020\f0#2\u0007\u0010Æ\u0001\u001a\u00020)2\u0007\u0010Ç\u0001\u001a\u00020a2\n\u0010È\u0001\u001a\u0005\u0018\u00010É\u0001H\u0000¢\u0006\u0003\bÊ\u0001J\u001d\u0010Ë\u0001\u001a\u00020=2\f\u0010Ì\u0001\u001a\u00070\u007fj\u0003`\u0080\u0001H\u0000¢\u0006\u0003\bÍ\u0001J0\u0010Î\u0001\u001a\b\u0012\u0004\u0012\u00020\f0#2\u0006\u0010`\u001a\u00020a2\u0007\u0010Ï\u0001\u001a\u00020a2\b\u0010Ð\u0001\u001a\u00030Ñ\u0001H\u0000¢\u0006\u0003\bÒ\u0001J\u0017\u0010Ó\u0001\u001a\u00020=2\u0006\u0010M\u001a\u00020 H\u0000¢\u0006\u0003\bÔ\u0001J\u0017\u0010Õ\u0001\u001a\u00020=2\u0006\u0010M\u001a\u00020 H\u0000¢\u0006\u0003\bÖ\u0001J\u0017\u0010×\u0001\u001a\u00020\f2\u0006\u0010M\u001a\u00020 H\u0000¢\u0006\u0003\bØ\u0001J\u0017\u0010Ù\u0001\u001a\u00020\f2\u0006\u0010`\u001a\u00020aH\u0000¢\u0006\u0003\bÚ\u0001J\u0018\u0010Û\u0001\u001a\u00020=2\r\u0010Ü\u0001\u001a\b\u0012\u0004\u0012\u00020=0<H\u0016J\u0018\u0010Ý\u0001\u001a\u00020=2\r\u0010Ü\u0001\u001a\b\u0012\u0004\u0012\u00020=0<H\u0016J\u000f\u0010ß\u0001\u001a\b\u0012\u0004\u0012\u00020K0#H\u0003J\u0014\u0010à\u0001\u001a\u00020=2\t\u0010á\u0001\u001a\u0004\u0018\u00010[H\u0003J(\u0010â\u0001\u001a\u00020=2\u0007\u0010ã\u0001\u001a\u00020a2\u0006\u0010u\u001a\u00020a2\f\b\u0002\u0010ä\u0001\u001a\u0005\u0018\u00010å\u0001H\u0002JJ\u0010æ\u0001\u001a\b\u0012\u0004\u0012\u00020\f0#2\u0007\u0010ã\u0001\u001a\u00020a2\t\b\u0002\u0010ç\u0001\u001a\u00020\t2%\u0010è\u0001\u001a \u0012\u0015\u0012\u00130$¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020=0\u0082\u0001H\u0002JJ\u0010é\u0001\u001a\b\u0012\u0004\u0012\u00020K0#2\u0007\u0010ã\u0001\u001a\u00020a2\t\b\u0002\u0010ç\u0001\u001a\u00020\t2%\u0010è\u0001\u001a \u0012\u0015\u0012\u00130$¢\u0006\u000e\b\u0083\u0001\u0012\t\b\u0084\u0001\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020=0\u0082\u0001H\u0002J\u000f\u0010ê\u0001\u001a\b\u0012\u0004\u0012\u00020$0#H\u0002J\u000f\u0010ë\u0001\u001a\b\u0012\u0004\u0012\u00020$0#H\u0003J!\u0010ì\u0001\u001a\b\u0012\u0004\u0012\u00020$0#2\u0007\u0010í\u0001\u001a\u00020)2\u0007\u0010î\u0001\u001a\u00020)H\u0003J\u000f\u0010ï\u0001\u001a\b\u0012\u0004\u0012\u00020$0#H\u0003J\u0010\u0010ô\u0001\u001a\t\u0012\u0005\u0012\u00030Ã\u00010#H\u0002J\u001a\u0010õ\u0001\u001a\u00020=2\u0007\u0010ö\u0001\u001a\u00020a2\u0006\u0010%\u001a\u00020$H\u0002J\u001a\u0010÷\u0001\u001a\u00020=2\u0007\u0010ö\u0001\u001a\u00020a2\u0006\u0010%\u001a\u00020$H\u0002Jb\u0010ù\u0001\u001aA\u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020$0#¢\u0006\u000f\b\u0083\u0001\u0012\n\b\u0084\u0001\u0012\u0005\b\b(û\u0001\u0012\u0016\u0012\u00140a¢\u0006\u000f\b\u0083\u0001\u0012\n\b\u0084\u0001\u0012\u0005\b\b(ü\u0001\u0012\u0006\u0012\u0004\u0018\u00010$0ú\u00012\u0007\u0010ý\u0001\u001a\u00020a2\u0007\u0010ö\u0001\u001a\u00020a2\u0006\u0010s\u001a\u00020aH\u0002J\u0017\u0010þ\u0001\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010s\u001a\u00020aH\u0003J(\u0010\u0080\u0002\u001a\b\u0012\u0004\u0012\u00020K0#2\u0006\u0010s\u001a\u00020a2\u000f\u0010~\u001a\u000b\u0018\u00010\u007fj\u0005\u0018\u0001`\u0080\u0001H\u0003J\u0011\u0010\u0081\u0002\u001a\u0004\u0018\u00010?H\u0000¢\u0006\u0003\b\u0082\u0002J\u0019\u0010\u0083\u0002\u001a\u00020=2\b\u0010%\u001a\u0004\u0018\u00010$H\u0001¢\u0006\u0003\b\u0084\u0002J\t\u0010\u0085\u0002\u001a\u00020=H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0+X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010-\u001a \u0012\u001c\u0012\u001a\u0012\u0006\u0012\u0004\u0018\u00010, /*\f\u0012\u0006\u0012\u0004\u0018\u00010,\u0018\u00010.0.0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020:09X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010>\u001a\u0004\u0018\u00010?X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u000e\u0010D\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010E\u001a\u00020F8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0016\u0010Z\u001a\u0004\u0018\u00010[8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0014\u0010d\u001a\u00020\f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u0016\u0010h\u001a\u0004\u0018\u00010i8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bj\u0010kR\"\u0010v\u001a\u0010\u0012\u0004\u0012\u00020a\u0012\u0006\u0012\u0004\u0018\u00010a0w8CX\u0082\u0004¢\u0006\u0006\u001a\u0004\bx\u0010yR-\u0010\u0087\u0001\u001a\u0004\u0018\u00010,2\b\u0010*\u001a\u0004\u0018\u00010,8@@BX\u0080\u000e¢\u0006\u0010\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0019\u0010\u008c\u0001\u001a\u0004\u0018\u00010,8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u0089\u0001R\u0018\u0010\u008e\u0001\u001a\u00030\u008f\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u001a\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0093\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u001f\u0010\u009c\u0001\u001a\n\u0012\u0005\u0012\u00030\u0098\u00010\u009d\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u001a\u0010¤\u0001\u001a\u0005\u0018\u00010¥\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b¦\u0001\u0010§\u0001R\u001a\u0010¨\u0001\u001a\u0005\u0018\u00010©\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u001a\u0010º\u0001\u001a\u0005\u0018\u00010»\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0017\u0010¾\u0001\u001a\u0002078@X\u0080\u0004¢\u0006\b\u001a\u0006\b¿\u0001\u0010À\u0001R\u001b\u0010Þ\u0001\u001a\n\u0012\u0004\u0012\u00020K\u0018\u00010#8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001e\u0010ð\u0001\u001a\t\u0012\u0005\u0012\u00030Ã\u00010#8BX\u0082\u0004¢\u0006\b\u001a\u0006\bñ\u0001\u0010ò\u0001R\u001d\u0010ó\u0001\u001a\b\u0012\u0004\u0012\u00020\f0#8BX\u0082\u0004¢\u0006\b\u001a\u0006\bó\u0001\u0010ò\u0001R\u001b\u0010ø\u0001\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001b\u0010ÿ\u0001\u001a\n\u0012\u0004\u0012\u00020K\u0018\u00010#8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\u0088\u0002"}, d2 = {"Lcom/facebook/react/runtime/ReactHostImpl;", "Lcom/facebook/react/ReactHost;", "context", "Landroid/content/Context;", "reactHostDelegate", "Lcom/facebook/react/runtime/ReactHostDelegate;", "componentFactory", "Lcom/facebook/react/fabric/ComponentFactory;", "bgExecutor", "Ljava/util/concurrent/Executor;", "uiExecutor", "allowPackagerServerAccess", "", "useDevSupport", "devSupportManagerFactory", "Lcom/facebook/react/devsupport/DevSupportManagerFactory;", "<init>", "(Landroid/content/Context;Lcom/facebook/react/runtime/ReactHostDelegate;Lcom/facebook/react/fabric/ComponentFactory;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZLcom/facebook/react/devsupport/DevSupportManagerFactory;)V", "delegate", "(Landroid/content/Context;Lcom/facebook/react/runtime/ReactHostDelegate;Lcom/facebook/react/fabric/ComponentFactory;ZZ)V", "reactHostImplDevHelper", "Lcom/facebook/react/runtime/ReactHostImplDevHelper;", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "getDevSupportManager", "()Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "memoryPressureRouter", "Lcom/facebook/react/MemoryPressureRouter;", "getMemoryPressureRouter", "()Lcom/facebook/react/MemoryPressureRouter;", "attachedSurfaces", "", "Lcom/facebook/react/runtime/ReactSurfaceImpl;", "createReactInstanceTaskRef", "Lcom/facebook/react/runtime/BridgelessAtomicRef;", "Lcom/facebook/react/runtime/internal/bolts/Task;", "Lcom/facebook/react/runtime/ReactInstance;", "reactInstance", "bridgelessReactContextRef", "Lcom/facebook/react/runtime/BridgelessReactContext;", "id", "", "activity", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroid/app/Activity;", "lastUsedActivityRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "stateTracker", "Lcom/facebook/react/runtime/ReactHostStateTracker;", "reactLifecycleStateManager", "Lcom/facebook/react/runtime/ReactLifecycleStateManager;", "memoryPressureListener", "Lcom/facebook/react/bridge/MemoryPressureListener;", "defaultHardwareBackBtnHandler", "Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;", "reactInstanceEventListeners", "", "Lcom/facebook/react/ReactInstanceEventListener;", "beforeDestroyListeners", "Lkotlin/Function0;", "", "reactHostInspectorTarget", "Lcom/facebook/react/runtime/ReactHostInspectorTarget;", "getReactHostInspectorTarget$ReactAndroid_release", "()Lcom/facebook/react/runtime/ReactHostInspectorTarget;", "setReactHostInspectorTarget$ReactAndroid_release", "(Lcom/facebook/react/runtime/ReactHostInspectorTarget;)V", "hostInvalidated", "lifecycleState", "Lcom/facebook/react/common/LifecycleState;", "getLifecycleState", "()Lcom/facebook/react/common/LifecycleState;", ViewProps.START, "Lcom/facebook/react/interfaces/TaskInterface;", "Ljava/lang/Void;", "prerenderSurface", "surface", "prerenderSurface$ReactAndroid_release", "startSurface", "startSurface$ReactAndroid_release", "stopSurface", "stopSurface$ReactAndroid_release", "onHostResume", "defaultBackButtonImpl", "onHostLeaveHint", "onHostPause", "onHostDestroy", "maybeEnableDevSupport", ViewProps.ENABLED, "currentReactContext", "Lcom/facebook/react/bridge/ReactContext;", "getCurrentReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "createSurface", "Lcom/facebook/react/interfaces/fabric/ReactSurface;", "moduleName", "", "initialProps", "Landroid/os/Bundle;", "isInstanceInitialized", "isInstanceInitialized$ReactAndroid_release", "()Z", "onBackPressed", "reactQueueConfiguration", "Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "getReactQueueConfiguration", "()Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "addReactInstanceEventListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeReactInstanceEventListener", "setDevMenuConfiguration", "config", "Lcom/facebook/react/devsupport/DevMenuConfiguration;", "reload", DiscardedEvent.JsonKeys.REASON, "setPausedInDebuggerMessage", "message", "hostMetadata", "", "getHostMetadata", "()Ljava/util/Map;", "loadNetworkResource", "url", "Lcom/facebook/react/devsupport/inspector/InspectorNetworkRequestListener;", "destroy", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onDestroyFinished", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "instanceDestroyedSuccessfully", "createMemoryPressureListener", "currentActivity", "getCurrentActivity$ReactAndroid_release", "()Landroid/app/Activity;", "setCurrentActivity", "(Landroid/app/Activity;)V", "lastUsedActivity", "getLastUsedActivity$ReactAndroid_release", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher$ReactAndroid_release", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "uiManager", "Lcom/facebook/react/fabric/FabricUIManager;", "getUiManager$ReactAndroid_release", "()Lcom/facebook/react/fabric/FabricUIManager;", "hasNativeModule", "T", "Lcom/facebook/react/bridge/NativeModule;", "nativeModuleInterface", "Ljava/lang/Class;", "hasNativeModule$ReactAndroid_release", "nativeModules", "", "getNativeModules$ReactAndroid_release", "()Ljava/util/Collection;", "getNativeModule", "getNativeModule$ReactAndroid_release", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/NativeModule;", "nativeModuleName", "runtimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "getRuntimeExecutor$ReactAndroid_release", "()Lcom/facebook/react/bridge/RuntimeExecutor;", "jsCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/interfaces/CallInvokerHolder;", "getJsCallInvokerHolder$ReactAndroid_release", "()Lcom/facebook/react/turbomodule/core/interfaces/CallInvokerHolder;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onWindowFocusChange", "hasFocus", "onNewIntent", "intent", "setBundleSource", "filePath", "debugServerHost", "queryMapper", "onConfigurationChanged", "javaScriptContextHolder", "Lcom/facebook/react/bridge/JavaScriptContextHolder;", "getJavaScriptContextHolder$ReactAndroid_release", "()Lcom/facebook/react/bridge/JavaScriptContextHolder;", "defaultBackButtonHandler", "getDefaultBackButtonHandler$ReactAndroid_release", "()Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;", "loadBundle", "bundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "loadBundle$ReactAndroid_release", "registerSegment", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "path", "callback", "Lcom/facebook/react/bridge/Callback;", "registerSegment$ReactAndroid_release", "handleHostException", "e", "handleHostException$ReactAndroid_release", "callFunctionOnModule", "methodName", "args", "Lcom/facebook/react/bridge/NativeArray;", "callFunctionOnModule$ReactAndroid_release", "attachSurface", "attachSurface$ReactAndroid_release", "detachSurface", "detachSurface$ReactAndroid_release", "isSurfaceAttached", "isSurfaceAttached$ReactAndroid_release", "isSurfaceWithModuleNameAttached", "isSurfaceWithModuleNameAttached$ReactAndroid_release", "addBeforeDestroyListener", "onBeforeDestroy", "removeBeforeDestroyListener", "startTask", "getOrCreateStartTask", "moveToHostDestroy", "currentContext", "raiseSoftException", "callingMethod", "throwable", "", "callWithExistingReactInstance", "executor", "runnable", "callAfterGetOrCreateReactInstance", "getOrCreateReactInstance", "waitThenCallGetOrCreateReactInstanceTask", "waitThenCallGetOrCreateReactInstanceTaskWithRetries", "tryNum", "maxTries", "getOrCreateReactInstanceTask", "jsBundleLoader", "getJsBundleLoader", "()Lcom/facebook/react/runtime/internal/bolts/Task;", "isMetroRunning", "loadJSBundleFromMetro", "stopAttachedSurfaces", "method", "startAttachedSurfaces", "reloadTask", "createReactInstanceUnwrapper", "Lkotlin/Function2;", "task", "stage", "tag", "getOrCreateReloadTask", "destroyTask", "getOrCreateDestroyTask", "getOrCreateReactHostInspectorTarget", "getOrCreateReactHostInspectorTarget$ReactAndroid_release", "unregisterInstanceFromInspector", "unregisterInstanceFromInspector$ReactAndroid_release", "invalidate", "CreationResult", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactHostImpl implements ReactHost {
    private static final int BRIDGELESS_MARKER_INSTANCE_KEY = 1;
    private static final String TAG = "ReactHost";
    private final AtomicReference<Activity> activity;
    private final boolean allowPackagerServerAccess;
    private final Set<ReactSurfaceImpl> attachedSurfaces;
    private final List<Function0<Unit>> beforeDestroyListeners;
    private final Executor bgExecutor;
    private final BridgelessAtomicRef<BridgelessReactContext> bridgelessReactContextRef;
    private final ComponentFactory componentFactory;
    private final Context context;
    private final BridgelessAtomicRef<Task<ReactInstance>> createReactInstanceTaskRef;
    private DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler;
    private Task<Void> destroyTask;
    private final DevSupportManager devSupportManager;
    private volatile boolean hostInvalidated;
    private final int id;
    private final AtomicReference<WeakReference<Activity>> lastUsedActivityRef;
    private MemoryPressureListener memoryPressureListener;
    private final MemoryPressureRouter memoryPressureRouter;
    private final ReactHostDelegate reactHostDelegate;
    private final ReactHostImplDevHelper reactHostImplDevHelper;
    private ReactHostInspectorTarget reactHostInspectorTarget;
    private ReactInstance reactInstance;
    private final List<ReactInstanceEventListener> reactInstanceEventListeners;
    private final ReactLifecycleStateManager reactLifecycleStateManager;
    private Task<ReactInstance> reloadTask;
    private Task<Void> startTask;
    private final ReactHostStateTracker stateTracker;
    private final Executor uiExecutor;
    private final boolean useDevSupport;
    private static final Companion Companion = new Companion(null);
    private static final AtomicInteger counter = new AtomicInteger(0);

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, Executor bgExecutor, Executor uiExecutor, boolean z, boolean z2, DevSupportManagerFactory devSupportManagerFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactHostDelegate, "reactHostDelegate");
        Intrinsics.checkNotNullParameter(componentFactory, "componentFactory");
        Intrinsics.checkNotNullParameter(bgExecutor, "bgExecutor");
        Intrinsics.checkNotNullParameter(uiExecutor, "uiExecutor");
        this.context = context;
        this.reactHostDelegate = reactHostDelegate;
        this.componentFactory = componentFactory;
        this.bgExecutor = bgExecutor;
        this.uiExecutor = uiExecutor;
        this.allowPackagerServerAccess = z;
        this.useDevSupport = z2;
        ReactHostImplDevHelper reactHostImplDevHelper = new ReactHostImplDevHelper(this);
        this.reactHostImplDevHelper = reactHostImplDevHelper;
        DefaultDevSupportManagerFactory defaultDevSupportManagerFactory = devSupportManagerFactory == null ? new DefaultDevSupportManagerFactory() : devSupportManagerFactory;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        DevSupportManager devSupportManagerCreate = defaultDevSupportManagerFactory.create(applicationContext, reactHostImplDevHelper, reactHostDelegate.getJsMainModulePath(), true, null, null, 2, null, null, null, null, z2);
        if (devSupportManagerCreate instanceof DevSupportManagerBase) {
            ((DevSupportManagerBase) devSupportManagerCreate).setTracingStateProvider$ReactAndroid_release(reactHostImplDevHelper);
        }
        this.devSupportManager = devSupportManagerCreate;
        this.memoryPressureRouter = new MemoryPressureRouter(context);
        this.attachedSurfaces = new HashSet();
        this.createReactInstanceTaskRef = new BridgelessAtomicRef<>(Task.INSTANCE.forResult(null));
        this.bridgelessReactContextRef = new BridgelessAtomicRef<>(null, 1, null);
        int andIncrement = counter.getAndIncrement();
        this.id = andIncrement;
        this.activity = new AtomicReference<>();
        this.lastUsedActivityRef = new AtomicReference<>(new WeakReference(null));
        ReactHostStateTracker reactHostStateTracker = new ReactHostStateTracker(andIncrement);
        this.stateTracker = reactHostStateTracker;
        this.reactLifecycleStateManager = new ReactLifecycleStateManager(reactHostStateTracker);
        this.reactInstanceEventListeners = new CopyOnWriteArrayList();
        this.beforeDestroyListeners = new CopyOnWriteArrayList();
    }

    public /* synthetic */ ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, ExecutorService executorService, Executor executor, boolean z, boolean z2, DevSupportManagerFactory devSupportManagerFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, reactHostDelegate, componentFactory, (i & 8) != 0 ? Executors.newSingleThreadExecutor() : executorService, (i & 16) != 0 ? Task.UI_THREAD_EXECUTOR : executor, z, z2, (i & 128) != 0 ? null : devSupportManagerFactory);
    }

    @Override // com.facebook.react.ReactHost
    public DevSupportManager getDevSupportManager() {
        return this.devSupportManager;
    }

    @Override // com.facebook.react.ReactHost
    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.memoryPressureRouter;
    }

    /* JADX INFO: renamed from: getReactHostInspectorTarget$ReactAndroid_release, reason: from getter */
    public final ReactHostInspectorTarget getReactHostInspectorTarget() {
        return this.reactHostInspectorTarget;
    }

    public final void setReactHostInspectorTarget$ReactAndroid_release(ReactHostInspectorTarget reactHostInspectorTarget) {
        this.reactHostInspectorTarget = reactHostInspectorTarget;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ReactHostImpl(Context context, ReactHostDelegate delegate, ComponentFactory componentFactory, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(componentFactory, "componentFactory");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this(context, delegate, componentFactory, executorServiceNewSingleThreadExecutor, Task.UI_THREAD_EXECUTOR, z, z2, null, 128, null);
    }

    @Override // com.facebook.react.ReactHost
    public LifecycleState getLifecycleState() {
        return this.reactLifecycleStateManager.getState();
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> start() {
        return Task.INSTANCE.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda37
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.getOrCreateStartTask();
            }
        }, this.bgExecutor);
    }

    public final TaskInterface<Void> prerenderSurface$ReactAndroid_release(final ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        final String str = "prerenderSurface(surfaceId = " + surface.getSurfaceID() + ")";
        this.stateTracker.enterState(str, "Schedule");
        attachSurface$ReactAndroid_release(surface);
        return callAfterGetOrCreateReactInstance(str, this.bgExecutor, new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.prerenderSurface$lambda$2(this.f$0, str, surface, (ReactInstance) obj);
            }
        });
    }

    public static final Unit prerenderSurface$lambda$2(ReactHostImpl reactHostImpl, String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        Intrinsics.checkNotNullParameter(reactInstance, "reactInstance");
        reactHostImpl.stateTracker.enterState(str, "Execute");
        reactInstance.prerenderSurface(reactSurfaceImpl);
        return Unit.INSTANCE;
    }

    public final TaskInterface<Void> startSurface$ReactAndroid_release(final ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        final String str = "startSurface(surfaceId = " + surface.getSurfaceID() + ")";
        this.stateTracker.enterState(str, "Schedule");
        attachSurface$ReactAndroid_release(surface);
        return callAfterGetOrCreateReactInstance(str, this.bgExecutor, new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.startSurface$lambda$3(this.f$0, str, surface, (ReactInstance) obj);
            }
        });
    }

    public static final Unit startSurface$lambda$3(ReactHostImpl reactHostImpl, String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        Intrinsics.checkNotNullParameter(reactInstance, "reactInstance");
        reactHostImpl.stateTracker.enterState(str, "Execute");
        reactInstance.startSurface(reactSurfaceImpl);
        return Unit.INSTANCE;
    }

    public final TaskInterface<Void> stopSurface$ReactAndroid_release(final ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        final String str = "stopSurface(surfaceId = " + surface.getSurfaceID() + ")";
        this.stateTracker.enterState(str, "Schedule");
        detachSurface$ReactAndroid_release(surface);
        return callWithExistingReactInstance(str, this.bgExecutor, new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.stopSurface$lambda$4(this.f$0, str, surface, (ReactInstance) obj);
            }
        }).makeVoid();
    }

    public static final Unit stopSurface$lambda$4(ReactHostImpl reactHostImpl, String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        Intrinsics.checkNotNullParameter(reactInstance, "reactInstance");
        reactHostImpl.stateTracker.enterState(str, "Execute");
        reactInstance.stopSurface(reactSurfaceImpl);
        return Unit.INSTANCE;
    }

    @Override // com.facebook.react.ReactHost
    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultBackButtonImpl) {
        this.defaultHardwareBackBtnHandler = defaultBackButtonImpl;
        onHostResume(activity);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostResume(Activity activity) {
        ReactHostStateTracker.enterState$default(this.stateTracker, "onHostResume(activity)", null, 2, null);
        setCurrentActivity(activity);
        maybeEnableDevSupport(true);
        this.reactLifecycleStateManager.moveToOnHostResume(getCurrentReactContext(), activity);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostLeaveHint(Activity activity) {
        ReactHostStateTracker.enterState$default(this.stateTracker, "onUserLeaveHint(activity)", null, 2, null);
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onUserLeaveHint(activity);
        }
    }

    @Override // com.facebook.react.ReactHost
    public void onHostPause(Activity activity) {
        ReactHostStateTracker.enterState$default(this.stateTracker, "onHostPause(activity)", null, 2, null);
        Activity currentActivity$ReactAndroid_release = getCurrentActivity$ReactAndroid_release();
        if (currentActivity$ReactAndroid_release != null) {
            boolean z = activity == currentActivity$ReactAndroid_release;
            if (!z) {
                String str = "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + currentActivity$ReactAndroid_release.getClass().getSimpleName() + " Paused activity: " + (activity == null ? "null" : activity.getClass().getSimpleName());
                if (ReactNativeFeatureFlags.skipActivityIdentityAssertionOnHostPause()) {
                    FLog.w(TAG, "onHostPause(activity)", str);
                } else {
                    Assertions.assertCondition(z, str);
                }
            }
        }
        maybeEnableDevSupport(false);
        this.defaultHardwareBackBtnHandler = null;
        this.reactLifecycleStateManager.moveToOnHostPause(getCurrentReactContext(), currentActivity$ReactAndroid_release);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostPause() {
        ReactHostStateTracker.enterState$default(this.stateTracker, "onHostPause()", null, 2, null);
        maybeEnableDevSupport(false);
        this.defaultHardwareBackBtnHandler = null;
        this.reactLifecycleStateManager.moveToOnHostPause(getCurrentReactContext(), getCurrentActivity$ReactAndroid_release());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostDestroy() {
        ReactHostStateTracker.enterState$default(this.stateTracker, "onHostDestroy()", null, 2, null);
        maybeEnableDevSupport(false);
        moveToHostDestroy(getCurrentReactContext());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostDestroy(Activity activity) {
        ReactHostStateTracker.enterState$default(this.stateTracker, "onHostDestroy(activity)", null, 2, null);
        if (getCurrentActivity$ReactAndroid_release() == activity) {
            maybeEnableDevSupport(false);
            moveToHostDestroy(getCurrentReactContext());
        }
    }

    private final void maybeEnableDevSupport(boolean z) {
        if (this.useDevSupport) {
            getDevSupportManager().setDevSupportEnabled(z);
        }
    }

    @Override // com.facebook.react.ReactHost
    public ReactContext getCurrentReactContext() {
        return this.bridgelessReactContextRef.getNullable();
    }

    @Override // com.facebook.react.ReactHost
    public ReactSurface createSurface(Context context, String moduleName, Bundle initialProps) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        ReactSurfaceImpl reactSurfaceImpl = new ReactSurfaceImpl(context, moduleName, initialProps);
        ReactSurfaceView reactSurfaceView = new ReactSurfaceView(context, reactSurfaceImpl);
        reactSurfaceView.setShouldLogContentAppeared(true);
        reactSurfaceImpl.attachView(reactSurfaceView);
        reactSurfaceImpl.attach(this);
        return reactSurfaceImpl;
    }

    public final boolean isInstanceInitialized$ReactAndroid_release() {
        return this.reactInstance != null;
    }

    @Override // com.facebook.react.ReactHost
    public boolean onBackPressed() {
        DeviceEventManagerModule deviceEventManagerModule;
        UiThreadUtil.assertOnUiThread();
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance == null || (deviceEventManagerModule = (DeviceEventManagerModule) reactInstance.getNativeModule(DeviceEventManagerModule.class)) == null) {
            return false;
        }
        deviceEventManagerModule.emitHardwareBackPressed();
        return true;
    }

    @Override // com.facebook.react.ReactHost
    public ReactQueueConfiguration getReactQueueConfiguration() {
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.getReactQueueConfiguration();
        }
        return null;
    }

    @Override // com.facebook.react.ReactHost
    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        Intrinsics.checkNotNullParameter(reactInstanceEventListener, "listener");
        this.reactInstanceEventListeners.add(reactInstanceEventListener);
    }

    @Override // com.facebook.react.ReactHost
    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        Intrinsics.checkNotNullParameter(reactInstanceEventListener, "listener");
        this.reactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    @Override // com.facebook.react.ReactHost
    public void setDevMenuConfiguration(DevMenuConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        getDevSupportManager().setDevMenuEnabled(config.getDevMenuEnabled());
        getDevSupportManager().setShakeGestureEnabled(config.getShakeGestureEnabled());
        getDevSupportManager().setKeyboardShortcutsEnabled(config.getKeyboardShortcutsEnabled());
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> reload(final String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        return Task.INSTANCE.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda22
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReactHostImpl.reload$lambda$8(this.f$0, str);
            }
        }, this.bgExecutor);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.facebook.react.runtime.internal.bolts.Task reload$lambda$8(final com.facebook.react.runtime.ReactHostImpl r4, final java.lang.String r5) {
        /*
            com.facebook.react.runtime.internal.bolts.Task<java.lang.Void> r0 = r4.destroyTask
            if (r0 == 0) goto L1a
            com.facebook.react.runtime.ReactHostStateTracker r1 = r4.stateTracker
            java.lang.String r2 = "reload()"
            java.lang.String r3 = "Waiting for destroy to finish, before reloading React Native."
            r1.enterState(r2, r3)
            com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda14 r1 = new com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda14
            r1.<init>()
            java.util.concurrent.Executor r2 = r4.bgExecutor
            com.facebook.react.runtime.internal.bolts.Task r0 = r0.continueWithTask(r1, r2)
            if (r0 != 0) goto L1e
        L1a:
            com.facebook.react.runtime.internal.bolts.Task r0 = r4.getOrCreateReloadTask(r5)
        L1e:
            com.facebook.react.runtime.internal.bolts.Task r5 = r0.makeVoid()
            com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda15 r0 = new com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda15
            r0.<init>()
            java.util.concurrent.Executor r4 = r4.bgExecutor
            com.facebook.react.runtime.internal.bolts.Task r4 = r5.continueWithTask(r0, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.runtime.ReactHostImpl.reload$lambda$8(com.facebook.react.runtime.ReactHostImpl, java.lang.String):com.facebook.react.runtime.internal.bolts.Task");
    }

    public static final Task reload$lambda$8$lambda$6$lambda$5(ReactHostImpl reactHostImpl, String str, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return reactHostImpl.getOrCreateReloadTask(str);
    }

    public static final Task reload$lambda$8$lambda$7(ReactHostImpl reactHostImpl, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isFaulted()) {
            return task;
        }
        Exception error = task.getError();
        if (error == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (reactHostImpl.useDevSupport) {
            reactHostImpl.getDevSupportManager().handleException(error);
        } else {
            reactHostImpl.reactHostDelegate.handleInstanceException(error);
        }
        return reactHostImpl.getOrCreateDestroyTask("Reload failed", error);
    }

    private final void setPausedInDebuggerMessage(String message) {
        if (message == null) {
            getDevSupportManager().hidePausedInDebuggerOverlay();
        } else {
            getDevSupportManager().showPausedInDebuggerOverlay(message, new DevSupportManager.PausedInDebuggerOverlayCommandListener() { // from class: com.facebook.react.runtime.ReactHostImpl.setPausedInDebuggerMessage.1
                C00991() {
                }

                @Override // com.facebook.react.devsupport.interfaces.DevSupportManager.PausedInDebuggerOverlayCommandListener
                public void onResume() {
                    UiThreadUtil.assertOnUiThread();
                    ReactHostInspectorTarget reactHostInspectorTarget = ReactHostImpl.this.getReactHostInspectorTarget();
                    if (reactHostInspectorTarget != null) {
                        reactHostInspectorTarget.sendDebuggerResumeCommand();
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.facebook.react.runtime.ReactHostImpl$setPausedInDebuggerMessage$1 */
    /* JADX INFO: compiled from: ReactHostImpl.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/facebook/react/runtime/ReactHostImpl$setPausedInDebuggerMessage$1", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PausedInDebuggerOverlayCommandListener;", "onResume", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00991 implements DevSupportManager.PausedInDebuggerOverlayCommandListener {
        C00991() {
        }

        @Override // com.facebook.react.devsupport.interfaces.DevSupportManager.PausedInDebuggerOverlayCommandListener
        public void onResume() {
            UiThreadUtil.assertOnUiThread();
            ReactHostInspectorTarget reactHostInspectorTarget = ReactHostImpl.this.getReactHostInspectorTarget();
            if (reactHostInspectorTarget != null) {
                reactHostInspectorTarget.sendDebuggerResumeCommand();
            }
        }
    }

    private final Map<String, String> getHostMetadata() {
        return AndroidInfoHelpers.getInspectorHostMetadata(this.context);
    }

    private final void loadNetworkResource(String url, InspectorNetworkRequestListener inspectorNetworkRequestListener) {
        InspectorNetworkHelper.loadNetworkResource(url, inspectorNetworkRequestListener);
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> destroy(String str, Exception ex, final Function1<? super Boolean, Unit> onDestroyFinished) {
        Intrinsics.checkNotNullParameter(str, "reason");
        Intrinsics.checkNotNullParameter(onDestroyFinished, "onDestroyFinished");
        TaskInterface<Void> taskInterfaceDestroy = destroy(str, ex);
        Intrinsics.checkNotNull(taskInterfaceDestroy, "null cannot be cast to non-null type com.facebook.react.runtime.internal.bolts.Task<java.lang.Void>");
        return Task.continueWith$default((Task) taskInterfaceDestroy, new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda9
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return ReactHostImpl.destroy$lambda$9(onDestroyFinished, task);
            }
        }, null, 2, null);
    }

    public static final Void destroy$lambda$9(Function1 function1, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        function1.invoke(Boolean.valueOf(task.isCompleted() && !task.isFaulted()));
        return null;
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> destroy(final String str, final Exception ex) {
        Intrinsics.checkNotNullParameter(str, "reason");
        return Task.INSTANCE.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda35
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReactHostImpl.destroy$lambda$11(this.f$0, str, ex);
            }
        }, this.bgExecutor);
    }

    public static final Task destroy$lambda$11(final ReactHostImpl reactHostImpl, final String str, final Exception exc) {
        Task<ReactInstance> task = reactHostImpl.reloadTask;
        if (task != null) {
            reactHostImpl.stateTracker.enterState("destroy()", "Reloading React Native. Waiting for reload to finish before destroying React Native.");
            return task.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda39
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task2) {
                    return ReactHostImpl.destroy$lambda$11$lambda$10(this.f$0, str, exc, task2);
                }
            }, reactHostImpl.bgExecutor);
        }
        return reactHostImpl.getOrCreateDestroyTask(str, exc);
    }

    public static final Task destroy$lambda$11$lambda$10(ReactHostImpl reactHostImpl, String str, Exception exc, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return reactHostImpl.getOrCreateDestroyTask(str, exc);
    }

    private final MemoryPressureListener createMemoryPressureListener(ReactInstance reactInstance) {
        final WeakReference weakReference = new WeakReference(reactInstance);
        return new MemoryPressureListener() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda13
            @Override // com.facebook.react.bridge.MemoryPressureListener
            public final void handleMemoryPressure(int i) {
                ReactHostImpl.createMemoryPressureListener$lambda$13(this.f$0, weakReference, i);
            }
        };
    }

    public static final void createMemoryPressureListener$lambda$13(ReactHostImpl reactHostImpl, final WeakReference weakReference, final int i) {
        reactHostImpl.bgExecutor.execute(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                ReactHostImpl.createMemoryPressureListener$lambda$13$lambda$12(weakReference, i);
            }
        });
    }

    public static final void createMemoryPressureListener$lambda$13$lambda$12(WeakReference weakReference, int i) {
        ReactInstance reactInstance = (ReactInstance) weakReference.get();
        if (reactInstance != null) {
            reactInstance.handleMemoryPressure(i);
        }
    }

    public final Activity getCurrentActivity$ReactAndroid_release() {
        return this.activity.get();
    }

    private final void setCurrentActivity(Activity activity) {
        this.activity.set(activity);
        if (activity != null) {
            this.lastUsedActivityRef.set(new WeakReference<>(activity));
        }
    }

    public final Activity getLastUsedActivity$ReactAndroid_release() {
        WeakReference<Activity> weakReference = this.lastUsedActivityRef.get();
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final EventDispatcher getEventDispatcher$ReactAndroid_release() {
        EventDispatcher eventDispatcher;
        ReactInstance reactInstance = this.reactInstance;
        return (reactInstance == null || (eventDispatcher = reactInstance.getEventDispatcher()) == null) ? BlackHoleEventDispatcher.INSTANCE : eventDispatcher;
    }

    public final FabricUIManager getUiManager$ReactAndroid_release() {
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.getFabricUIManager();
        }
        return null;
    }

    public final <T extends NativeModule> boolean hasNativeModule$ReactAndroid_release(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.hasNativeModule(nativeModuleInterface);
        }
        return false;
    }

    public final Collection<NativeModule> getNativeModules$ReactAndroid_release() {
        Collection<NativeModule> nativeModules;
        ReactInstance reactInstance = this.reactInstance;
        return (reactInstance == null || (nativeModules = reactInstance.getNativeModules()) == null) ? CollectionsKt.emptyList() : nativeModules;
    }

    public final <T extends NativeModule> T getNativeModule$ReactAndroid_release(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        if (!ReactBuildConfig.UNSTABLE_ENABLE_MINIFY_LEGACY_ARCHITECTURE && Intrinsics.areEqual(nativeModuleInterface, UIManagerModule.class)) {
            ReactSoftExceptionLogger.logSoftExceptionVerbose(TAG, new ReactNoCrashSoftException("getNativeModule(UIManagerModule.class) cannot be called when the bridge is disabled"));
        }
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return (T) reactInstance.getNativeModule(nativeModuleInterface);
        }
        return null;
    }

    public final NativeModule getNativeModule$ReactAndroid_release(String nativeModuleName) {
        Intrinsics.checkNotNullParameter(nativeModuleName, "nativeModuleName");
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.getNativeModule(nativeModuleName);
        }
        return null;
    }

    public final RuntimeExecutor getRuntimeExecutor$ReactAndroid_release() {
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.getBufferedRuntimeExecutor();
        }
        raiseSoftException$default(this, "getRuntimeExecutor()", "Tried to get runtime executor while instance is not ready", null, 4, null);
        return null;
    }

    public final CallInvokerHolder getJsCallInvokerHolder$ReactAndroid_release() {
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.getJSCallInvokerHolder();
        }
        raiseSoftException$default(this, "getJSCallInvokerHolder()", "Tried to get JSCallInvokerHolder while instance is not ready", null, 4, null);
        return null;
    }

    @Override // com.facebook.react.ReactHost
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        String str = "onActivityResult(activity = \"" + activity + "\", requestCode = \"" + requestCode + "\", resultCode = \"" + resultCode + "\", data = \"" + data + "\")";
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onActivityResult(activity, requestCode, resultCode, data);
        } else {
            raiseSoftException$default(this, str, "Tried to access onActivityResult while context is not ready", null, 4, null);
        }
    }

    @Override // com.facebook.react.ReactHost
    public void onWindowFocusChange(boolean hasFocus) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onWindowFocusChange(hasFocus);
        } else {
            raiseSoftException$default(this, "onWindowFocusChange(hasFocus = \"" + hasFocus + "\")", "Tried to access onWindowFocusChange while context is not ready", null, 4, null);
        }
    }

    @Override // com.facebook.react.ReactHost
    public void onNewIntent(Intent intent) {
        DeviceEventManagerModule deviceEventManagerModule;
        Intrinsics.checkNotNullParameter(intent, "intent");
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            String action = intent.getAction();
            Uri data = intent.getData();
            if (data != null && ((Intrinsics.areEqual("android.intent.action.VIEW", action) || Intrinsics.areEqual("android.nfc.action.NDEF_DISCOVERED", action)) && (deviceEventManagerModule = (DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)) != null)) {
                deviceEventManagerModule.emitNewIntentReceived(data);
            }
            currentReactContext.onNewIntent(getCurrentActivity$ReactAndroid_release(), intent);
            return;
        }
        raiseSoftException$default(this, "onNewIntent(intent = \"" + intent + "\")", "Tried to access onNewIntent while context is not ready", null, 4, null);
    }

    @Override // com.facebook.react.ReactHost
    public void setBundleSource(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        getDevSupportManager().setBundleFilePath(filePath);
        reload("Change bundle source");
    }

    /* JADX INFO: renamed from: com.facebook.react.runtime.ReactHostImpl$setBundleSource$1 */
    /* JADX INFO: compiled from: ReactHostImpl.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.facebook.react.runtime.ReactHostImpl$setBundleSource$1", f = "ReactHostImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00981 extends SuspendLambda implements Function2<CoroutineScope, kotlin.coroutines.Continuation<? super Unit>, Object> {
        final /* synthetic */ String $debugServerHost;
        final /* synthetic */ String $moduleName;
        final /* synthetic */ Function1<Map<String, String>, Map<String, String>> $queryMapper;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C00981(Function1<? super Map<String, String>, ? extends Map<String, String>> function1, String str, String str2, kotlin.coroutines.Continuation<? super C00981> continuation) {
            super(2, continuation);
            this.$queryMapper = function1;
            this.$debugServerHost = str;
            this.$moduleName = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final kotlin.coroutines.Continuation<Unit> create(Object obj, kotlin.coroutines.Continuation<?> continuation) {
            return ReactHostImpl.this.new C00981(this.$queryMapper, this.$debugServerHost, this.$moduleName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, kotlin.coroutines.Continuation<? super Unit> continuation) {
            return ((C00981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DevSupportManager devSupportManager = ReactHostImpl.this.getDevSupportManager();
            Intrinsics.checkNotNull(devSupportManager, "null cannot be cast to non-null type com.facebook.react.devsupport.DevSupportManagerBase");
            ((DevSupportManagerBase) devSupportManager).getDevServerHelper().closePackagerConnection();
            PackagerConnectionSettings packagerConnectionSettings = ((DevSupportManagerBase) ReactHostImpl.this.getDevSupportManager()).getDevSettings().getPackagerConnectionSettings();
            packagerConnectionSettings.setPackagerOptionsUpdater(this.$queryMapper);
            packagerConnectionSettings.setDebugServerHost(this.$debugServerHost);
            ((DevSupportManagerBase) ReactHostImpl.this.getDevSupportManager()).setJsAppBundleName(this.$moduleName);
            ReactHostImpl.this.reload("Changed bundle source");
            return Unit.INSTANCE;
        }
    }

    @Override // com.facebook.react.ReactHost
    public void setBundleSource(String debugServerHost, String moduleName, Function1<? super Map<String, String>, ? extends Map<String, String>> queryMapper) {
        Intrinsics.checkNotNullParameter(debugServerHost, "debugServerHost");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(queryMapper, "queryMapper");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new C00981(queryMapper, debugServerHost, moduleName, null), 3, null);
    }

    @Override // com.facebook.react.ReactHost
    public void onConfigurationChanged(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            if (ReactNativeFeatureFlags.enableFontScaleChangesUpdatingLayout()) {
                float pixelFromSP = PixelUtil.toPixelFromSP(1.0d);
                DisplayMetricsHolder.initDisplayMetrics(currentReactContext);
                if (pixelFromSP != PixelUtil.toPixelFromSP(1.0d)) {
                    synchronized (this.attachedSurfaces) {
                        Iterator<T> it = this.attachedSurfaces.iterator();
                        while (it.hasNext()) {
                            ReactSurfaceView view = ((ReactSurfaceImpl) it.next()).getView();
                            if (view != null) {
                                view.requestLayout();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            AppearanceModule appearanceModule = (AppearanceModule) currentReactContext.getNativeModule(AppearanceModule.class);
            if (appearanceModule != null) {
                appearanceModule.onConfigurationChanged(context);
            }
        }
    }

    public final JavaScriptContextHolder getJavaScriptContextHolder$ReactAndroid_release() {
        ReactInstance reactInstance = this.reactInstance;
        if (reactInstance != null) {
            return reactInstance.getJavaScriptContextHolder();
        }
        return null;
    }

    public final DefaultHardwareBackBtnHandler getDefaultBackButtonHandler$ReactAndroid_release() {
        return new DefaultHardwareBackBtnHandler() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda31
            @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
            public final void invokeDefaultOnBackPressed() {
                ReactHostImpl._get_defaultBackButtonHandler_$lambda$18(this.f$0);
            }
        };
    }

    public static final void _get_defaultBackButtonHandler_$lambda$18(ReactHostImpl reactHostImpl) {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = reactHostImpl.defaultHardwareBackBtnHandler;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    public final Task<Boolean> loadBundle$ReactAndroid_release(final JSBundleLoader bundleLoader) {
        Intrinsics.checkNotNullParameter(bundleLoader, "bundleLoader");
        final String str = "loadBundle()";
        this.stateTracker.enterState("loadBundle()", "Schedule");
        return callWithExistingReactInstance$default(this, "loadBundle()", null, new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.loadBundle$lambda$19(this.f$0, str, bundleLoader, (ReactInstance) obj);
            }
        }, 2, null);
    }

    public static final Unit loadBundle$lambda$19(ReactHostImpl reactHostImpl, String str, JSBundleLoader jSBundleLoader, ReactInstance reactInstance) {
        Intrinsics.checkNotNullParameter(reactInstance, "reactInstance");
        reactHostImpl.stateTracker.enterState(str, "Execute");
        reactInstance.loadJSBundle(jSBundleLoader);
        return Unit.INSTANCE;
    }

    public final Task<Boolean> registerSegment$ReactAndroid_release(final int i, final String path, final Callback callback) {
        Intrinsics.checkNotNullParameter(path, "path");
        final String str = "registerSegment(segmentId = \"" + i + "\", path = \"" + path + "\")";
        this.stateTracker.enterState(str, "Schedule");
        return callWithExistingReactInstance$default(this, str, null, new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.registerSegment$lambda$20(this.f$0, str, i, path, callback, (ReactInstance) obj);
            }
        }, 2, null);
    }

    public static final Unit registerSegment$lambda$20(ReactHostImpl reactHostImpl, String str, int i, String str2, Callback callback, ReactInstance reactInstance) {
        Intrinsics.checkNotNullParameter(reactInstance, "reactInstance");
        reactHostImpl.stateTracker.enterState(str, "Execute");
        reactInstance.registerSegment(i, str2);
        if (callback != null) {
            callback.invoke(new Object[0]);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final void handleHostException$ReactAndroid_release(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        String str = "handleHostException(message = \"" + e.getMessage() + "\")";
        ReactHostStateTracker.enterState$default(this.stateTracker, str, null, 2, null);
        if (this.useDevSupport) {
            getDevSupportManager().handleException(e);
        } else {
            this.reactHostDelegate.handleInstanceException(e);
        }
        destroy(str, e);
    }

    public final Task<Boolean> callFunctionOnModule$ReactAndroid_release(final String moduleName, final String methodName, final NativeArray args) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(args, "args");
        return callWithExistingReactInstance$default(this, "callFunctionOnModule(\"" + moduleName + "\", \"" + methodName + "\")", null, new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.callFunctionOnModule$lambda$21(moduleName, methodName, args, (ReactInstance) obj);
            }
        }, 2, null);
    }

    public static final Unit callFunctionOnModule$lambda$21(String str, String str2, NativeArray nativeArray, ReactInstance reactInstance) {
        Intrinsics.checkNotNullParameter(reactInstance, "reactInstance");
        reactInstance.callFunctionOnModule(str, str2, nativeArray);
        return Unit.INSTANCE;
    }

    public final void attachSurface$ReactAndroid_release(ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        ReactHostStateTracker.enterState$default(this.stateTracker, "attachSurface(surfaceId = " + surface.getSurfaceID() + ")", null, 2, null);
        synchronized (this.attachedSurfaces) {
            this.attachedSurfaces.add(surface);
        }
    }

    public final void detachSurface$ReactAndroid_release(ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        ReactHostStateTracker.enterState$default(this.stateTracker, "detachSurface(surfaceId = " + surface.getSurfaceID() + ")", null, 2, null);
        synchronized (this.attachedSurfaces) {
            this.attachedSurfaces.remove(surface);
        }
    }

    public final boolean isSurfaceAttached$ReactAndroid_release(ReactSurfaceImpl surface) {
        boolean zContains;
        Intrinsics.checkNotNullParameter(surface, "surface");
        synchronized (this.attachedSurfaces) {
            zContains = this.attachedSurfaces.contains(surface);
        }
        return zContains;
    }

    public final boolean isSurfaceWithModuleNameAttached$ReactAndroid_release(String moduleName) {
        boolean z;
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        synchronized (this.attachedSurfaces) {
            Set<ReactSurfaceImpl> set = this.attachedSurfaces;
            z = false;
            if (!(set instanceof Collection) || !set.isEmpty()) {
                Iterator<T> it = set.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (Intrinsics.areEqual(((ReactSurfaceImpl) it.next()).getModuleName(), moduleName)) {
                        z = true;
                        break;
                    }
                }
            }
        }
        return z;
    }

    @Override // com.facebook.react.ReactHost
    public void addBeforeDestroyListener(Function0<Unit> onBeforeDestroy) {
        Intrinsics.checkNotNullParameter(onBeforeDestroy, "onBeforeDestroy");
        this.beforeDestroyListeners.add(onBeforeDestroy);
    }

    @Override // com.facebook.react.ReactHost
    public void removeBeforeDestroyListener(Function0<Unit> onBeforeDestroy) {
        Intrinsics.checkNotNullParameter(onBeforeDestroy, "onBeforeDestroy");
        this.beforeDestroyListeners.remove(onBeforeDestroy);
    }

    public final Task<Void> getOrCreateStartTask() {
        Task<Void> task = this.startTask;
        if (task != null) {
            return task;
        }
        this.stateTracker.enterState("getOrCreateStartTask()", "Schedule");
        if (ReactBuildConfig.DEBUG) {
            Assertions.assertCondition(ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture(), "enableBridgelessArchitecture FeatureFlag must be set to start ReactNative.");
            Assertions.assertCondition(ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer(), "enableFabricRenderer FeatureFlag must be set to start ReactNative.");
            Assertions.assertCondition(ReactNativeNewArchitectureFeatureFlags.useTurboModules(), "useTurboModules FeatureFlag must be set to start ReactNative.");
        }
        if (ReactBuildConfig.UNSTABLE_ENABLE_MINIFY_LEGACY_ARCHITECTURE) {
            Assertions.assertCondition(!ReactNativeNewArchitectureFeatureFlags.useFabricInterop(), "useFabricInterop FeatureFlag must be false when UNSTABLE_ENABLE_MINIFY_LEGACY_ARCHITECTURE == true.");
            Assertions.assertCondition(!ReactNativeNewArchitectureFeatureFlags.useTurboModuleInterop(), "useTurboModuleInterop FeatureFlag must be false when UNSTABLE_ENABLE_MINIFY_LEGACY_ARCHITECTURE == true.");
        }
        Task taskContinueWithTask = waitThenCallGetOrCreateReactInstanceTask().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda19
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateStartTask$lambda$29(this.f$0, task2);
            }
        }, this.bgExecutor);
        this.startTask = taskContinueWithTask;
        return taskContinueWithTask;
    }

    public static final Task getOrCreateStartTask$lambda$29(ReactHostImpl reactHostImpl, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isFaulted()) {
            final Exception error = task.getError();
            if (error == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (reactHostImpl.useDevSupport) {
                reactHostImpl.getDevSupportManager().handleException(error);
            } else {
                reactHostImpl.reactHostDelegate.handleInstanceException(error);
            }
            return Task.continueWithTask$default(reactHostImpl.getOrCreateDestroyTask("getOrCreateStartTask() failure: " + error.getMessage(), error), new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda1
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task2) {
                    return ReactHostImpl.getOrCreateStartTask$lambda$29$lambda$28(error, task2);
                }
            }, null, 2, null);
        }
        return task.makeVoid();
    }

    public static final Task getOrCreateStartTask$lambda$29$lambda$28(Exception exc, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Task.INSTANCE.forError(exc);
    }

    private final void moveToHostDestroy(ReactContext currentContext) {
        this.reactLifecycleStateManager.moveToOnHostDestroy(currentContext);
        setCurrentActivity(null);
    }

    static /* synthetic */ void raiseSoftException$default(ReactHostImpl reactHostImpl, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        reactHostImpl.raiseSoftException(str, str2, th);
    }

    private final void raiseSoftException(String callingMethod, String message, Throwable throwable) {
        String str = "raiseSoftException(" + callingMethod + ")";
        this.stateTracker.enterState(str, message);
        ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(str + ": " + message, throwable));
    }

    static /* synthetic */ Task callWithExistingReactInstance$default(ReactHostImpl reactHostImpl, String str, Executor executor, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            executor = Task.IMMEDIATE_EXECUTOR;
        }
        return reactHostImpl.callWithExistingReactInstance(str, executor, function1);
    }

    private final Task<Boolean> callWithExistingReactInstance(final String callingMethod, Executor executor, final Function1<? super ReactInstance, Unit> runnable) {
        return this.createReactInstanceTaskRef.get().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda30
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return ReactHostImpl.callWithExistingReactInstance$lambda$31(this.f$0, callingMethod, runnable, task);
            }
        }, executor);
    }

    public static final Boolean callWithExistingReactInstance$lambda$31(ReactHostImpl reactHostImpl, String str, Function1 function1, Task task) {
        boolean z;
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        if (reactInstance == null) {
            raiseSoftException$default(reactHostImpl, "callWithExistingReactInstance(" + str + ")", "Execute: reactInstance is null. Dropping work.", null, 4, null);
            z = false;
        } else {
            function1.invoke(reactInstance);
            z = true;
        }
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Task callAfterGetOrCreateReactInstance$default(ReactHostImpl reactHostImpl, String str, Executor executor, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            executor = Task.IMMEDIATE_EXECUTOR;
        }
        return reactHostImpl.callAfterGetOrCreateReactInstance(str, executor, function1);
    }

    private final Task<Void> callAfterGetOrCreateReactInstance(final String callingMethod, Executor executor, final Function1<? super ReactInstance, Unit> runnable) {
        return getOrCreateReactInstance().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda38
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return ReactHostImpl.callAfterGetOrCreateReactInstance$lambda$32(this.f$0, callingMethod, runnable, task);
            }
        }, executor);
    }

    public static final Void callAfterGetOrCreateReactInstance$lambda$32(ReactHostImpl reactHostImpl, String str, Function1 function1, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        if (reactInstance == null) {
            raiseSoftException$default(reactHostImpl, "callAfterGetOrCreateReactInstance(" + str + ")", "Execute: reactInstance is null. Dropping work.", null, 4, null);
            return null;
        }
        function1.invoke(reactInstance);
        return null;
    }

    private final Task<ReactInstance> getOrCreateReactInstance() {
        return Task.INSTANCE.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda42
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.waitThenCallGetOrCreateReactInstanceTask();
            }
        }, this.bgExecutor);
    }

    public final Task<ReactInstance> waitThenCallGetOrCreateReactInstanceTask() {
        return waitThenCallGetOrCreateReactInstanceTaskWithRetries(0, 4);
    }

    private final Task<ReactInstance> waitThenCallGetOrCreateReactInstanceTaskWithRetries(final int tryNum, final int maxTries) {
        Task<ReactInstance> task = this.reloadTask;
        if (task != null) {
            this.stateTracker.enterState("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is reloading. Return reload task.");
            return task;
        }
        Task<Void> task2 = this.destroyTask;
        if (task2 != null) {
            if (tryNum < maxTries) {
                this.stateTracker.enterState("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down.Wait for teardown to finish, before trying again (try count = " + tryNum + ").");
                return task2.onSuccessTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda43
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public final Object then(Task task3) {
                        return ReactHostImpl.waitThenCallGetOrCreateReactInstanceTaskWithRetries$lambda$36$lambda$35(this.f$0, tryNum, maxTries, task3);
                    }
                }, this.bgExecutor);
            }
            raiseSoftException$default(this, "waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down. Not wait for teardown to finish: reached max retries.", null, 4, null);
        }
        return getOrCreateReactInstanceTask();
    }

    public static final Task waitThenCallGetOrCreateReactInstanceTaskWithRetries$lambda$36$lambda$35(ReactHostImpl reactHostImpl, int i, int i2, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return reactHostImpl.waitThenCallGetOrCreateReactInstanceTaskWithRetries(i + 1, i2);
    }

    /* JADX INFO: compiled from: ReactHostImpl.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/runtime/ReactHostImpl$CreationResult;", "", "instance", "Lcom/facebook/react/runtime/ReactInstance;", "context", "Lcom/facebook/react/bridge/ReactContext;", "isReloading", "", "<init>", "(Lcom/facebook/react/runtime/ReactInstance;Lcom/facebook/react/bridge/ReactContext;Z)V", "getInstance", "()Lcom/facebook/react/runtime/ReactInstance;", "getContext", "()Lcom/facebook/react/bridge/ReactContext;", "()Z", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class CreationResult {
        private final ReactContext context;
        private final ReactInstance instance;
        private final boolean isReloading;

        public CreationResult(ReactInstance instance, ReactContext context, boolean z) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            Intrinsics.checkNotNullParameter(context, "context");
            this.instance = instance;
            this.context = context;
            this.isReloading = z;
        }

        public final ReactInstance getInstance() {
            return this.instance;
        }

        public final ReactContext getContext() {
            return this.context;
        }

        /* JADX INFO: renamed from: isReloading, reason: from getter */
        public final boolean getIsReloading() {
            return this.isReloading;
        }
    }

    private final Task<ReactInstance> getOrCreateReactInstanceTask() {
        final String str = "getOrCreateReactInstanceTask()";
        ReactHostStateTracker.enterState$default(this.stateTracker, "getOrCreateReactInstanceTask()", null, 2, null);
        return this.createReactInstanceTaskRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda12
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                return ReactHostImpl.getOrCreateReactInstanceTask$lambda$44(this.f$0, str);
            }
        });
    }

    public static final Task getOrCreateReactInstanceTask$lambda$44(final ReactHostImpl reactHostImpl, final String str) {
        reactHostImpl.stateTracker.enterState(str, "Start");
        Assertions.assertCondition(!reactHostImpl.hostInvalidated, "Cannot start a new ReactInstance on an invalidated ReactHost");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_START, 1);
        Task<TContinuationResult> taskOnSuccess = reactHostImpl.getJsBundleLoader().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda20
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$40(this.f$0, str, task);
            }
        }, reactHostImpl.bgExecutor);
        taskOnSuccess.continueWith(new ReactHostImpl$sam$com_facebook_react_runtime_internal_bolts_Continuation$0(new Function1() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$42(this.f$0, str, (Task) obj);
            }
        }), reactHostImpl.uiExecutor);
        return Task.onSuccess$default(taskOnSuccess, new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda23
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$43(task);
            }
        }, null, 2, null);
    }

    public static final CreationResult getOrCreateReactInstanceTask$lambda$44$lambda$40(final ReactHostImpl reactHostImpl, final String str, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        Object result = task.getResult();
        if (result == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        BridgelessReactContext orCreate = reactHostImpl.bridgelessReactContextRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda16
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                return ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$40$lambda$37(this.f$0, str);
            }
        });
        orCreate.setJSExceptionHandler(reactHostImpl.getDevSupportManager());
        reactHostImpl.stateTracker.enterState(str, "Creating ReactInstance");
        ReactInstance reactInstance = new ReactInstance(orCreate, reactHostImpl.reactHostDelegate, reactHostImpl.componentFactory, reactHostImpl.getDevSupportManager(), new QueueThreadExceptionHandler() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda17
            @Override // com.facebook.react.bridge.queue.QueueThreadExceptionHandler
            public final void handleException(Exception exc) {
                ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$40$lambda$38(this.f$0, exc);
            }
        }, reactHostImpl.useDevSupport, reactHostImpl.getOrCreateReactHostInspectorTarget$ReactAndroid_release());
        reactHostImpl.reactInstance = reactInstance;
        MemoryPressureListener memoryPressureListenerCreateMemoryPressureListener = reactHostImpl.createMemoryPressureListener(reactInstance);
        reactHostImpl.memoryPressureListener = memoryPressureListenerCreateMemoryPressureListener;
        reactHostImpl.getMemoryPressureRouter().addMemoryPressureListener(memoryPressureListenerCreateMemoryPressureListener);
        reactInstance.initializeEagerTurboModules();
        reactHostImpl.stateTracker.enterState(str, "Loading JS Bundle");
        reactInstance.loadJSBundle((JSBundleLoader) result);
        reactHostImpl.stateTracker.enterState(str, "DevSupportManager.onNewReactContextCreated()");
        BridgelessReactContext bridgelessReactContext = orCreate;
        reactHostImpl.getDevSupportManager().onNewReactContextCreated(bridgelessReactContext);
        orCreate.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$40$lambda$39();
            }
        });
        return new CreationResult(reactInstance, bridgelessReactContext, reactHostImpl.reloadTask != null);
    }

    public static final BridgelessReactContext getOrCreateReactInstanceTask$lambda$44$lambda$40$lambda$37(ReactHostImpl reactHostImpl, String str) {
        reactHostImpl.stateTracker.enterState(str, "Creating BridgelessReactContext");
        return new BridgelessReactContext(reactHostImpl.context, reactHostImpl);
    }

    public static final void getOrCreateReactInstanceTask$lambda$44$lambda$40$lambda$38(ReactHostImpl reactHostImpl, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        reactHostImpl.handleHostException$ReactAndroid_release(e);
    }

    public static final void getOrCreateReactInstanceTask$lambda$44$lambda$40$lambda$39() {
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_END, 1);
    }

    public static final Unit getOrCreateReactInstanceTask$lambda$44$lambda$42(final ReactHostImpl reactHostImpl, String str, final Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isFaulted()) {
            reactHostImpl.uiExecutor.execute(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ReactHostImpl.getOrCreateReactInstanceTask$lambda$44$lambda$42$lambda$41(this.f$0, task);
                }
            });
            return Unit.INSTANCE;
        }
        Object result = task.getResult();
        if (result == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CreationResult creationResult = (CreationResult) result;
        ReactContext context = creationResult.getContext();
        boolean isReloading = creationResult.getIsReloading();
        boolean z = reactHostImpl.reactLifecycleStateManager.getState() == LifecycleState.RESUMED;
        if (isReloading && !z) {
            reactHostImpl.reactLifecycleStateManager.moveToOnHostResume(context, reactHostImpl.getCurrentActivity$ReactAndroid_release());
        } else {
            reactHostImpl.reactLifecycleStateManager.resumeReactContextIfHostResumed(context, reactHostImpl.getCurrentActivity$ReactAndroid_release());
        }
        reactHostImpl.stateTracker.enterState(str, "Executing ReactInstanceEventListeners");
        Iterator<ReactInstanceEventListener> it = reactHostImpl.reactInstanceEventListeners.iterator();
        while (it.hasNext()) {
            it.next().onReactContextInitialized(context);
        }
        return Unit.INSTANCE;
    }

    public static final void getOrCreateReactInstanceTask$lambda$44$lambda$42$lambda$41(ReactHostImpl reactHostImpl, Task task) {
        Exception error = task.getError();
        if (error == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        reactHostImpl.handleHostException$ReactAndroid_release(error);
    }

    public static final ReactInstance getOrCreateReactInstanceTask$lambda$44$lambda$43(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        Object result = task.getResult();
        if (result != null) {
            return ((CreationResult) result).getInstance();
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final Task<JSBundleLoader> getJsBundleLoader() {
        ReactHostStateTracker.enterState$default(this.stateTracker, "getJSBundleLoader()", null, 2, null);
        if (getDevSupportManager().getBundleFilePath() != null) {
            try {
                Task.Companion companion = Task.INSTANCE;
                JSBundleLoader.Companion companion2 = JSBundleLoader.INSTANCE;
                String bundleFilePath = getDevSupportManager().getBundleFilePath();
                if (bundleFilePath != null) {
                    return companion.forResult(companion2.createFileLoader(bundleFilePath));
                }
                throw new IllegalStateException("Required value was null.".toString());
            } catch (Exception e) {
                return Task.INSTANCE.forError(e);
            }
        }
        if (this.useDevSupport && this.allowPackagerServerAccess) {
            return isMetroRunning().onSuccessTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda41
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return ReactHostImpl._get_jsBundleLoader_$lambda$45(this.f$0, task);
                }
            }, this.bgExecutor);
        }
        if (ReactBuildConfig.DEBUG) {
            FLog.d(TAG, "Packager server access is disabled in this environment");
        }
        try {
            return Task.INSTANCE.forResult(this.reactHostDelegate.getJsBundleLoader());
        } catch (Exception e2) {
            return Task.INSTANCE.forError(e2);
        }
    }

    public static final Task _get_jsBundleLoader_$lambda$45(ReactHostImpl reactHostImpl, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        Object result = task.getResult();
        if (result == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (((Boolean) result).booleanValue()) {
            return reactHostImpl.loadJSBundleFromMetro();
        }
        return Task.INSTANCE.forResult(reactHostImpl.reactHostDelegate.getJsBundleLoader());
    }

    private final Task<Boolean> isMetroRunning() {
        final String str = "isMetroRunning()";
        ReactHostStateTracker.enterState$default(this.stateTracker, "isMetroRunning()", null, 2, null);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        getDevSupportManager().isPackagerRunning(new PackagerStatusCallback() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda32
            @Override // com.facebook.react.devsupport.interfaces.PackagerStatusCallback
            public final void onPackagerStatusFetched(boolean z) {
                ReactHostImpl._get_isMetroRunning_$lambda$46(this.f$0, str, taskCompletionSource, z);
            }
        });
        return taskCompletionSource.getTask();
    }

    public static final void _get_isMetroRunning_$lambda$46(ReactHostImpl reactHostImpl, String str, TaskCompletionSource taskCompletionSource, boolean z) {
        reactHostImpl.stateTracker.enterState(str, "Async result = " + z);
        taskCompletionSource.setResult(Boolean.valueOf(z));
    }

    private final Task<JSBundleLoader> loadJSBundleFromMetro() {
        ReactHostStateTracker.enterState$default(this.stateTracker, "loadJSBundleFromMetro()", null, 2, null);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        DevSupportManager devSupportManager = getDevSupportManager();
        Intrinsics.checkNotNull(devSupportManager, "null cannot be cast to non-null type com.facebook.react.devsupport.DevSupportManagerBase");
        DevSupportManagerBase devSupportManagerBase = (DevSupportManagerBase) devSupportManager;
        DevServerHelper devServerHelper = devSupportManagerBase.getDevServerHelper();
        String jsAppBundleName = devSupportManagerBase.getJsAppBundleName();
        if (jsAppBundleName != null) {
            String devServerBundleURL = devServerHelper.getDevServerBundleURL(jsAppBundleName);
            devSupportManagerBase.reloadJSFromServer(devServerBundleURL, new BundleLoadCallback() { // from class: com.facebook.react.runtime.ReactHostImpl.loadJSBundleFromMetro.1
                final /* synthetic */ DevSupportManagerBase $asyncDevSupportManager;
                final /* synthetic */ String $bundleURL;
                final /* synthetic */ String $method;
                final /* synthetic */ TaskCompletionSource<JSBundleLoader> $taskCompletionSource;

                AnonymousClass1(String str, String devServerBundleURL2, DevSupportManagerBase devSupportManagerBase2, TaskCompletionSource<JSBundleLoader> taskCompletionSource2) {
                    str = str;
                    str = devServerBundleURL2;
                    devSupportManagerBase = devSupportManagerBase2;
                    taskCompletionSource = taskCompletionSource2;
                }

                @Override // com.facebook.react.devsupport.interfaces.BundleLoadCallback
                public void onSuccess() {
                    ReactHostImpl.this.stateTracker.enterState(str, "Creating BundleLoader");
                    taskCompletionSource.setResult(JSBundleLoader.INSTANCE.createCachedBundleFromNetworkLoader(str, devSupportManagerBase.getDownloadedJSBundleFile()));
                }

                @Override // com.facebook.react.devsupport.interfaces.BundleLoadCallback
                public void onError(Exception cause) {
                    Intrinsics.checkNotNullParameter(cause, "cause");
                    taskCompletionSource.setError(cause);
                }
            });
            return taskCompletionSource2.getTask();
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    /* JADX INFO: renamed from: com.facebook.react.runtime.ReactHostImpl$loadJSBundleFromMetro$1 */
    /* JADX INFO: compiled from: ReactHostImpl.kt */
    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0014\u0010\u0004\u001a\u00020\u00032\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016¨\u0006\b"}, d2 = {"com/facebook/react/runtime/ReactHostImpl$loadJSBundleFromMetro$1", "Lcom/facebook/react/devsupport/interfaces/BundleLoadCallback;", "onSuccess", "", "onError", "cause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements BundleLoadCallback {
        final /* synthetic */ DevSupportManagerBase $asyncDevSupportManager;
        final /* synthetic */ String $bundleURL;
        final /* synthetic */ String $method;
        final /* synthetic */ TaskCompletionSource<JSBundleLoader> $taskCompletionSource;

        AnonymousClass1(String str, String devServerBundleURL2, DevSupportManagerBase devSupportManagerBase2, TaskCompletionSource<JSBundleLoader> taskCompletionSource2) {
            str = str;
            str = devServerBundleURL2;
            devSupportManagerBase = devSupportManagerBase2;
            taskCompletionSource = taskCompletionSource2;
        }

        @Override // com.facebook.react.devsupport.interfaces.BundleLoadCallback
        public void onSuccess() {
            ReactHostImpl.this.stateTracker.enterState(str, "Creating BundleLoader");
            taskCompletionSource.setResult(JSBundleLoader.INSTANCE.createCachedBundleFromNetworkLoader(str, devSupportManagerBase.getDownloadedJSBundleFile()));
        }

        @Override // com.facebook.react.devsupport.interfaces.BundleLoadCallback
        public void onError(Exception cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            taskCompletionSource.setError(cause);
        }
    }

    private final void stopAttachedSurfaces(String method, ReactInstance reactInstance) {
        this.stateTracker.enterState(method, "Stopping all React Native surfaces");
        synchronized (this.attachedSurfaces) {
            for (ReactSurfaceImpl reactSurfaceImpl : this.attachedSurfaces) {
                reactInstance.stopSurface(reactSurfaceImpl);
                reactSurfaceImpl.clear();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void startAttachedSurfaces(String method, ReactInstance reactInstance) {
        this.stateTracker.enterState(method, "Restarting previously running React Native Surfaces");
        synchronized (this.attachedSurfaces) {
            Iterator<ReactSurfaceImpl> it = this.attachedSurfaces.iterator();
            while (it.hasNext()) {
                reactInstance.startSurface(it.next());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final Function2<Task<ReactInstance>, String, ReactInstance> createReactInstanceUnwrapper(final String tag, final String method, final String str) {
        return new Function2() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ReactHostImpl.createReactInstanceUnwrapper$lambda$49(this.f$0, tag, str, method, (Task) obj, (String) obj2);
            }
        };
    }

    public static final ReactInstance createReactInstanceUnwrapper$lambda$49(ReactHostImpl reactHostImpl, String str, String str2, String str3, Task task, String stage) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(stage, "stage");
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        ReactInstance reactInstance2 = reactHostImpl.reactInstance;
        String str4 = "Stage: " + stage;
        String str5 = str + " reason: " + str2;
        if (task.isFaulted()) {
            Exception error = task.getError();
            if (error == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            raiseSoftException$default(reactHostImpl, str3, str + ": ReactInstance task faulted. " + str4 + ". " + ("Fault reason: " + error.getMessage()) + ". " + str5, null, 4, null);
            return reactInstance2;
        }
        if (task.isCancelled()) {
            raiseSoftException$default(reactHostImpl, str3, str + ": ReactInstance task cancelled. " + str4 + ". " + str5, null, 4, null);
            return reactInstance2;
        }
        if (reactInstance == null) {
            raiseSoftException$default(reactHostImpl, str3, str + ": ReactInstance task returned null. " + str4 + ". " + str5, null, 4, null);
            return reactInstance2;
        }
        if (reactInstance2 != null && !Intrinsics.areEqual(reactInstance, reactInstance2)) {
            raiseSoftException$default(reactHostImpl, str3, str + ": Detected two different ReactInstances. Returning old. " + str4 + ". " + str5, null, 4, null);
        }
        return reactInstance;
    }

    private final Task<ReactInstance> getOrCreateReloadTask(final String str) {
        final String str2 = "getOrCreateReloadTask()";
        ReactHostStateTracker.enterState$default(this.stateTracker, "getOrCreateReloadTask()", null, 2, null);
        raiseSoftException$default(this, "getOrCreateReloadTask()", str, null, 4, null);
        Task<ReactInstance> task = this.reloadTask;
        if (task != null) {
            return task;
        }
        final Function2<Task<ReactInstance>, String, ReactInstance> function2CreateReactInstanceUnwrapper = createReactInstanceUnwrapper("Reload", "getOrCreateReloadTask()", str);
        this.stateTracker.enterState("getOrCreateReloadTask()", "Resetting createReactInstance task ref");
        Task<ReactInstance> taskContinueWithTask = this.createReactInstanceTaskRef.getAndReset().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda2
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateReloadTask$lambda$51(this.f$0, str2, function2CreateReactInstanceUnwrapper, str, task2);
            }
        }, this.uiExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda3
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateReloadTask$lambda$52(function2CreateReactInstanceUnwrapper, this, str2, task2);
            }
        }, this.bgExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda4
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateReloadTask$lambda$54(function2CreateReactInstanceUnwrapper, this, str2, task2);
            }
        }, this.uiExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda5
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateReloadTask$lambda$55(function2CreateReactInstanceUnwrapper, this, str2, task2);
            }
        }, this.bgExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda6
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateReloadTask$lambda$56(function2CreateReactInstanceUnwrapper, this, str2, task2);
            }
        }, this.bgExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda7
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateReloadTask$lambda$57(this.f$0, str2, str, task2);
            }
        }, this.bgExecutor);
        this.reloadTask = taskContinueWithTask;
        return taskContinueWithTask;
    }

    public static final Task getOrCreateReloadTask$lambda$51(ReactHostImpl reactHostImpl, String str, Function2 function2, String str2, Task task) {
        ReactHostImpl reactHostImpl2;
        String str3;
        Intrinsics.checkNotNullParameter(task, "task");
        reactHostImpl.stateTracker.enterState(str, "Starting React Native reload");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "1: Starting reload");
        reactHostImpl.unregisterInstanceFromInspector$ReactAndroid_release(reactInstance);
        BridgelessReactContext nullable = reactHostImpl.bridgelessReactContextRef.getNullable();
        if (nullable == null) {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
            raiseSoftException$default(reactHostImpl2, str3, "ReactContext is null. Reload reason: " + str2, null, 4, null);
        } else {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
        }
        if (nullable != null && reactHostImpl2.reactLifecycleStateManager.getState() == LifecycleState.RESUMED) {
            reactHostImpl2.stateTracker.enterState(str3, "Calling ReactContext.onHostPause()");
            nullable.onHostPause();
        }
        return Task.INSTANCE.forResult(reactInstance);
    }

    public static final Task getOrCreateReloadTask$lambda$52(Function2 function2, ReactHostImpl reactHostImpl, String str, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "2: Surface shutdown");
        if (reactInstance == null) {
            raiseSoftException$default(reactHostImpl, str, "Skipping surface shutdown: ReactInstance null", null, 4, null);
            return task;
        }
        reactHostImpl.stopAttachedSurfaces(str, reactInstance);
        return task;
    }

    public static final Task getOrCreateReloadTask$lambda$54(Function2 function2, ReactHostImpl reactHostImpl, String str, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        function2.invoke(task, "3: Destroying ReactContext");
        Iterator<Function0<Unit>> it = reactHostImpl.beforeDestroyListeners.iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
        MemoryPressureListener memoryPressureListener = reactHostImpl.memoryPressureListener;
        if (memoryPressureListener != null) {
            reactHostImpl.stateTracker.enterState(str, "Removing memory pressure listener");
            reactHostImpl.getMemoryPressureRouter().removeMemoryPressureListener(memoryPressureListener);
        }
        BridgelessReactContext nullable = reactHostImpl.bridgelessReactContextRef.getNullable();
        if (nullable != null) {
            reactHostImpl.stateTracker.enterState(str, "Resetting ReactContext ref");
            reactHostImpl.bridgelessReactContextRef.reset();
            reactHostImpl.stateTracker.enterState(str, "Destroying ReactContext");
            nullable.destroy();
        }
        if (reactHostImpl.useDevSupport && nullable != null) {
            reactHostImpl.stateTracker.enterState(str, "Calling DevSupportManager.onReactInstanceDestroyed(reactContext)");
            reactHostImpl.getDevSupportManager().onReactInstanceDestroyed(nullable);
        }
        return task;
    }

    public static final Task getOrCreateReloadTask$lambda$55(Function2 function2, ReactHostImpl reactHostImpl, String str, Task task) {
        ReactHostImpl reactHostImpl2;
        String str2;
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "4: Destroying ReactInstance");
        if (reactInstance == null) {
            reactHostImpl2 = reactHostImpl;
            str2 = str;
            raiseSoftException$default(reactHostImpl2, str2, "Skipping ReactInstance.destroy(): ReactInstance null", null, 4, null);
        } else {
            reactHostImpl2 = reactHostImpl;
            str2 = str;
            reactHostImpl2.stateTracker.enterState(str2, "Resetting ReactInstance ptr");
            reactHostImpl2.reactInstance = null;
            reactHostImpl2.stateTracker.enterState(str2, "Destroying ReactInstance");
            reactInstance.destroy();
        }
        reactHostImpl2.stateTracker.enterState(str2, "Resetting start task ref");
        reactHostImpl2.startTask = null;
        return reactHostImpl2.getOrCreateReactInstanceTask();
    }

    public static final Task getOrCreateReloadTask$lambda$56(Function2 function2, ReactHostImpl reactHostImpl, String str, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "5: Restarting surfaces");
        if (reactInstance == null) {
            raiseSoftException$default(reactHostImpl, str, "Skipping surface restart: ReactInstance null", null, 4, null);
            return task;
        }
        reactHostImpl.startAttachedSurfaces(str, reactInstance);
        return task;
    }

    public static final Task getOrCreateReloadTask$lambda$57(ReactHostImpl reactHostImpl, String str, String str2, Task task) {
        ReactHostImpl reactHostImpl2;
        String str3;
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isFaulted()) {
            Exception error = task.getError();
            if (error == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            reactHostImpl.raiseSoftException(str, "Error during reload. ReactInstance task faulted. Fault reason: " + error.getMessage() + ". Reload reason: " + str2, task.getError());
        }
        if (task.isCancelled()) {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
            raiseSoftException$default(reactHostImpl2, str3, "Error during reload. ReactInstance task cancelled. Reload reason: " + str2, null, 4, null);
        } else {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
        }
        reactHostImpl2.stateTracker.enterState(str3, "Resetting reload task ref");
        reactHostImpl2.reloadTask = null;
        return task;
    }

    private final Task<Void> getOrCreateDestroyTask(final String str, Exception ex) {
        final String str2 = "getOrCreateDestroyTask()";
        ReactHostStateTracker.enterState$default(this.stateTracker, "getOrCreateDestroyTask()", null, 2, null);
        raiseSoftException("getOrCreateDestroyTask()", str, ex);
        Task<Void> task = this.destroyTask;
        if (task != null) {
            return task;
        }
        final Function2<Task<ReactInstance>, String, ReactInstance> function2CreateReactInstanceUnwrapper = createReactInstanceUnwrapper("Destroy", "getOrCreateDestroyTask()", str);
        this.stateTracker.enterState("getOrCreateDestroyTask()", "Resetting createReactInstance task ref");
        Task<Void> taskContinueWith$default = Task.continueWith$default(this.createReactInstanceTaskRef.getAndReset().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda24
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateDestroyTask$lambda$60(this.f$0, str2, function2CreateReactInstanceUnwrapper, str, task2);
            }
        }, this.uiExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda25
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateDestroyTask$lambda$62(function2CreateReactInstanceUnwrapper, this, str2, task2);
            }
        }, this.bgExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda26
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateDestroyTask$lambda$63(function2CreateReactInstanceUnwrapper, this, str2, str, task2);
            }
        }, this.uiExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda27
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateDestroyTask$lambda$64(function2CreateReactInstanceUnwrapper, this, str2, task2);
            }
        }, this.bgExecutor), new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda28
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task2) {
                return ReactHostImpl.getOrCreateDestroyTask$lambda$65(this.f$0, str2, str, task2);
            }
        }, null, 2, null);
        this.destroyTask = taskContinueWith$default;
        return taskContinueWith$default;
    }

    public static final Task getOrCreateDestroyTask$lambda$60(ReactHostImpl reactHostImpl, String str, Function2 function2, String str2, Task task) {
        ReactHostImpl reactHostImpl2;
        String str3;
        Intrinsics.checkNotNullParameter(task, "task");
        reactHostImpl.stateTracker.enterState(str, "Starting React Native destruction");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "1: Starting destroy");
        reactHostImpl.unregisterInstanceFromInspector$ReactAndroid_release(reactInstance);
        if (reactHostImpl.hostInvalidated) {
            ReactHostInspectorTarget reactHostInspectorTarget = reactHostImpl.reactHostInspectorTarget;
            if (reactHostInspectorTarget != null) {
                reactHostInspectorTarget.close();
            }
            reactHostImpl.reactHostInspectorTarget = null;
        }
        if (reactHostImpl.useDevSupport) {
            reactHostImpl.stateTracker.enterState(str, "DevSupportManager cleanup");
            reactHostImpl.getDevSupportManager().stopInspector();
        }
        BridgelessReactContext nullable = reactHostImpl.bridgelessReactContextRef.getNullable();
        if (nullable == null) {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
            raiseSoftException$default(reactHostImpl2, str3, "ReactContext is null. Destroy reason: " + str2, null, 4, null);
        } else {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
        }
        reactHostImpl2.stateTracker.enterState(str3, "Move ReactHost to onHostDestroy()");
        reactHostImpl2.reactLifecycleStateManager.moveToOnHostDestroy(nullable);
        return Task.INSTANCE.forResult(reactInstance);
    }

    public static final Task getOrCreateDestroyTask$lambda$62(Function2 function2, ReactHostImpl reactHostImpl, String str, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "2: Stopping surfaces");
        if (reactInstance == null) {
            raiseSoftException$default(reactHostImpl, str, "Skipping surface shutdown: ReactInstance null", null, 4, null);
            return task;
        }
        reactHostImpl.stopAttachedSurfaces(str, reactInstance);
        synchronized (reactHostImpl.attachedSurfaces) {
            reactHostImpl.attachedSurfaces.clear();
            Unit unit = Unit.INSTANCE;
        }
        return task;
    }

    public static final Task getOrCreateDestroyTask$lambda$63(Function2 function2, ReactHostImpl reactHostImpl, String str, String str2, Task task) {
        ReactHostImpl reactHostImpl2;
        String str3;
        Intrinsics.checkNotNullParameter(task, "task");
        function2.invoke(task, "3: Destroying ReactContext");
        Iterator<Function0<Unit>> it = reactHostImpl.beforeDestroyListeners.iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
        BridgelessReactContext nullable = reactHostImpl.bridgelessReactContextRef.getNullable();
        if (nullable == null) {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
            raiseSoftException$default(reactHostImpl2, str3, "ReactContext is null. Destroy reason: " + str2, null, 4, null);
        } else {
            reactHostImpl2 = reactHostImpl;
            str3 = str;
        }
        reactHostImpl2.stateTracker.enterState(str3, "Destroying MemoryPressureRouter");
        reactHostImpl2.getMemoryPressureRouter().destroy(reactHostImpl2.context);
        if (nullable != null) {
            reactHostImpl2.stateTracker.enterState(str3, "Resetting ReactContext ref");
            reactHostImpl2.bridgelessReactContextRef.reset();
            reactHostImpl2.stateTracker.enterState(str3, "Destroying ReactContext");
            nullable.destroy();
        }
        reactHostImpl2.setCurrentActivity(null);
        ResourceDrawableIdHelper.clear();
        return task;
    }

    public static final Task getOrCreateDestroyTask$lambda$64(Function2 function2, ReactHostImpl reactHostImpl, String str, Task task) {
        ReactHostImpl reactHostImpl2;
        String str2;
        Intrinsics.checkNotNullParameter(task, "task");
        ReactInstance reactInstance = (ReactInstance) function2.invoke(task, "4: Destroying ReactInstance");
        if (reactInstance == null) {
            reactHostImpl2 = reactHostImpl;
            str2 = str;
            raiseSoftException$default(reactHostImpl2, str2, "Skipping ReactInstance.destroy(): ReactInstance null", null, 4, null);
        } else {
            reactHostImpl2 = reactHostImpl;
            str2 = str;
            reactHostImpl2.stateTracker.enterState(str2, "Resetting ReactInstance ptr");
            reactHostImpl2.reactInstance = null;
            reactHostImpl2.stateTracker.enterState(str2, "Destroying ReactInstance");
            reactInstance.destroy();
        }
        reactHostImpl2.stateTracker.enterState(str2, "Resetting start/destroy task ref");
        reactHostImpl2.startTask = null;
        reactHostImpl2.destroyTask = null;
        return task;
    }

    public static final Void getOrCreateDestroyTask$lambda$65(ReactHostImpl reactHostImpl, String str, String str2, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isFaulted()) {
            Exception error = task.getError();
            if (error == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            reactHostImpl.raiseSoftException(str, "React destruction failed. ReactInstance task faulted. Fault reason: " + error.getMessage() + ". Destroy reason: " + str2, task.getError());
        }
        if (!task.isCancelled()) {
            return null;
        }
        raiseSoftException$default(reactHostImpl, str, "React destruction failed. ReactInstance task cancelled. Destroy reason: " + str2, null, 4, null);
        return null;
    }

    public final ReactHostInspectorTarget getOrCreateReactHostInspectorTarget$ReactAndroid_release() {
        if (this.reactHostInspectorTarget == null && InspectorFlags.getFuseboxEnabled()) {
            this.reactHostInspectorTarget = new ReactHostInspectorTarget(this);
        }
        return this.reactHostInspectorTarget;
    }

    public final void unregisterInstanceFromInspector$ReactAndroid_release(ReactInstance reactInstance) {
        if (reactInstance != null) {
            if (InspectorFlags.getFuseboxEnabled()) {
                ReactHostInspectorTarget reactHostInspectorTarget = this.reactHostInspectorTarget;
                boolean z = false;
                if (reactHostInspectorTarget != null && reactHostInspectorTarget.isValid()) {
                    z = true;
                }
                Assertions.assertCondition(z, "Host inspector target destroyed before instance was unregistered");
            }
            reactInstance.unregisterFromInspector();
        }
    }

    @Override // com.facebook.react.ReactHost
    public void invalidate() {
        FLog.d(TAG, "ReactHostImpl.invalidate()");
        this.hostInvalidated = true;
        destroy("ReactHostImpl.invalidate()", null);
    }

    /* JADX INFO: compiled from: ReactHostImpl.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/react/runtime/ReactHostImpl$Companion;", "", "<init>", "()V", "TAG", "", "BRIDGELESS_MARKER_INSTANCE_KEY", "", "counter", "Ljava/util/concurrent/atomic/AtomicInteger;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
