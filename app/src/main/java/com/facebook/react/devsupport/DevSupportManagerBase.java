package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.util.Supplier;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeRedBoxSpec;
import com.facebook.react.R;
import com.facebook.react.bridge.DefaultJSExceptionHandler;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.ShakeDetector;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.ChangeBundleLocationDialog;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DebuggerFrontendPanelName;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.ErrorType;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.devsupport.interfaces.TracingState;
import com.facebook.react.devsupport.interfaces.TracingStateProvider;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorDevHelper;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTarget;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorOverlayManager;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.common.UiModeUtils;
import com.facebook.react.views.text.DefaultStyleValuesUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;

/* JADX INFO: compiled from: DevSupportManagerBase.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 ì\u00012\u00020\u0001:\u0004ë\u0001ì\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u008e\u0001\u001a\u00030\u008f\u00012\r\u0010\u0090\u0001\u001a\b0\u0091\u0001j\u0003`\u0092\u0001H\u0016J\u001f\u0010\u0093\u0001\u001a\u00030\u008f\u00012\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u00072\b\u0010\u0090\u0001\u001a\u00030\u0095\u0001H\u0016J\u001c\u0010\u0096\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00072\u0007\u0010\u0098\u0001\u001a\u00020jH\u0016J*\u0010\u0099\u0001\u001a\u00030\u008f\u00012\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u00072\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u00012\u0007\u0010\u009c\u0001\u001a\u00020\u000fH\u0016J\u0013\u0010\u009d\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u009e\u0001\u001a\u00020~H\u0016J8\u0010\u009f\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0R0 \u00012\u001a\u0010¡\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0R0 \u0001H\u0016J\n\u0010¢\u0001\u001a\u00030\u008f\u0001H\u0016J\u0015\u0010£\u0001\u001a\u0005\u0018\u00010¤\u00012\u0007\u0010¥\u0001\u001a\u00020\u0007H\u0016J\u0016\u0010¦\u0001\u001a\u00030\u008f\u00012\n\u0010§\u0001\u001a\u0005\u0018\u00010¤\u0001H\u0016J\u0019\u0010¨\u0001\u001a\u00030\u008f\u00012\r\u0010\u0090\u0001\u001a\b0\u0091\u0001j\u0003`\u0092\u0001H\u0002J\n\u0010©\u0001\u001a\u00030\u008f\u0001H\u0002J<\u0010ª\u0001\u001a\u00030\u008f\u00012\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u00072\r\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020S0R2\u0007\u0010\u009c\u0001\u001a\u00020\u000f2\u0007\u0010¬\u0001\u001a\u00020ZH\u0002¢\u0006\u0003\u0010\u00ad\u0001J\n\u0010®\u0001\u001a\u00030\u008f\u0001H\u0016J\u0013\u0010¯\u0001\u001a\u00030\u008f\u00012\u0007\u0010°\u0001\u001a\u00020,H\u0016J\u0013\u0010±\u0001\u001a\u00030\u008f\u00012\u0007\u0010°\u0001\u001a\u00020,H\u0016J\t\u0010²\u0001\u001a\u00020\tH\u0016J\u0015\u0010³\u0001\u001a\u00030\u008f\u00012\t\u0010°\u0001\u001a\u0004\u0018\u00010,H\u0002J\n\u0010´\u0001\u001a\u00030\u008f\u0001H\u0016J\u0013\u0010µ\u0001\u001a\u00030\u008f\u00012\u0007\u0010¶\u0001\u001a\u00020\u0007H\u0003J\n\u0010·\u0001\u001a\u00030\u008f\u0001H\u0005J\n\u0010¸\u0001\u001a\u00030\u008f\u0001H\u0005J\u001b\u0010¹\u0001\u001a\u00030\u008f\u00012\u0007\u0010º\u0001\u001a\u00020\u00072\b\u0010»\u0001\u001a\u00030¼\u0001J\u0013\u0010½\u0001\u001a\u00030\u008f\u00012\u0007\u0010¶\u0001\u001a\u00020\u0007H\u0003J\n\u0010¾\u0001\u001a\u00030\u008f\u0001H\u0003J\u0014\u0010¿\u0001\u001a\u00030\u008f\u00012\b\u0010»\u0001\u001a\u00030À\u0001H\u0016J\u001d\u0010Á\u0001\u001a\u0004\u0018\u00010m2\u0007\u0010Â\u0001\u001a\u00020\u00072\u0007\u0010Ã\u0001\u001a\u00020mH\u0016J<\u0010Ä\u0001\u001a\u00030\u008f\u00012\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u00072\r\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020S0R2\u0007\u0010\u009c\u0001\u001a\u00020\u000f2\u0007\u0010¬\u0001\u001a\u00020ZH\u0002¢\u0006\u0003\u0010\u00ad\u0001J\u001d\u0010Å\u0001\u001a\u00030\u008f\u00012\u0007\u0010Æ\u0001\u001a\u00020\u00072\b\u0010»\u0001\u001a\u00030Ç\u0001H\u0016J\u0019\u0010È\u0001\u001a\u00030\u008f\u00012\r\u0010É\u0001\u001a\b0\u0091\u0001j\u0003`\u0092\u0001H\u0002J\n\u0010Ê\u0001\u001a\u00030\u008f\u0001H\u0016J\n\u0010Ë\u0001\u001a\u00030\u008f\u0001H\u0016J\u0013\u0010Ì\u0001\u001a\u00030\u008f\u00012\u0007\u0010Í\u0001\u001a\u00020\tH\u0016J\u0013\u0010Î\u0001\u001a\u00030\u008f\u00012\u0007\u0010Ï\u0001\u001a\u00020\tH\u0016J\n\u0010Ð\u0001\u001a\u00030\u008f\u0001H\u0016J\n\u0010Ñ\u0001\u001a\u00030\u008f\u0001H\u0002J\n\u0010Ò\u0001\u001a\u00030\u008f\u0001H\u0002J\n\u0010Ó\u0001\u001a\u00030\u008f\u0001H\u0002J\u0013\u0010Ô\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0016J\u0014\u0010Õ\u0001\u001a\u0004\u0018\u00010r2\u0007\u0010Ö\u0001\u001a\u00020\u0007H\u0016J/\u0010×\u0001\u001a\u00030\u008f\u00012\u0007\u0010Ø\u0001\u001a\u00020\u00032\u0007\u0010Ù\u0001\u001a\u00020g2\b\u0010Ú\u0001\u001a\u00030Û\u00012\u0007\u0010Ü\u0001\u001a\u00020\tH\u0002J\u0013\u0010Ý\u0001\u001a\u00030Þ\u00012\u0007\u0010Ø\u0001\u001a\u00020\u0003H\u0002J\u0013\u0010ß\u0001\u001a\u00030Þ\u00012\u0007\u0010Ø\u0001\u001a\u00020\u0003H\u0002J\u0015\u0010à\u0001\u001a\u00030\u008f\u00012\t\u0010á\u0001\u001a\u0004\u0018\u00010\u0007H\u0016J\u001d\u0010â\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u00072\b\u0010ã\u0001\u001a\u00030ä\u0001H\u0016J\n\u0010å\u0001\u001a\u00030\u008f\u0001H\u0016J\u001b\u0010æ\u0001\u001a\u00030\u008f\u00012\u0007\u0010ç\u0001\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0016J\u001c\u0010è\u0001\u001a\u00030\u008f\u00012\n\u0010é\u0001\u001a\u0005\u0018\u00010\u0087\u0001H\u0000¢\u0006\u0003\bê\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010)\u001a\u00020\u0007X¤\u0004¢\u0006\u0006\u001a\u0004\b*\u0010 R\"\u0010-\u001a\u0004\u0018\u00010,2\b\u0010+\u001a\u0004\u0018\u00010,@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u000201¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0016\u00104\u001a\u0004\u0018\u0001058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R$\u00109\u001a\u00020\t2\u0006\u00108\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R&\u0010>\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=R*\u0010A\u001a\u0004\u0018\u00010\u00072\b\u0010+\u001a\u0004\u0018\u00010\u00078V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010 \"\u0004\bC\u0010\"R\u0014\u0010D\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010 R\u0014\u0010F\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010 R\u0014\u0010H\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010 R\u0011\u0010J\u001a\u00020K¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u001c\u0010N\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010 \"\u0004\bP\u0010\"R$\u0010Q\u001a\n\u0012\u0004\u0012\u00020S\u0018\u00010RX\u0086\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001c\u0010Y\u001a\u0004\u0018\u00010ZX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001a\u0010_\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u000e\u0010d\u001a\u00020eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020gX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010h\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020j0ij\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020j`kX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020mX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020mX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020pX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010q\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010s\u001a\u0004\u0018\u00010tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010u\u001a\u0004\u0018\u00010vX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010|\u001a\b\u0012\u0004\u0012\u00020~0}X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00078BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010 R\u0012\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0085\u0001\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0088\u0001\u001a\u00020\tX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0001\u0010;\"\u0005\b\u008a\u0001\u0010=R\u001d\u0010\u008b\u0001\u001a\u00020\tX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010;\"\u0005\b\u008d\u0001\u0010=¨\u0006í\u0001"}, d2 = {"Lcom/facebook/react/devsupport/DevSupportManagerBase;", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "applicationContext", "Landroid/content/Context;", "reactInstanceDevHelper", "Lcom/facebook/react/devsupport/ReactInstanceDevHelper;", "jsAppBundleName", "", "enableOnCreate", "", "redBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "devBundleDownloadListener", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "minNumShakes", "", "customPackagerCommandHandlers", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "surfaceDelegateFactory", "Lcom/facebook/react/common/SurfaceDelegateFactory;", "devLoadingViewManager", "Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;", "pausedInDebuggerOverlayManager", "Lcom/facebook/react/devsupport/interfaces/PausedInDebuggerOverlayManager;", "<init>", "(Landroid/content/Context;Lcom/facebook/react/devsupport/ReactInstanceDevHelper;Ljava/lang/String;ZLcom/facebook/react/devsupport/interfaces/RedBoxHandler;Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;ILjava/util/Map;Lcom/facebook/react/common/SurfaceDelegateFactory;Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;Lcom/facebook/react/devsupport/interfaces/PausedInDebuggerOverlayManager;)V", "getApplicationContext", "()Landroid/content/Context;", "getReactInstanceDevHelper", "()Lcom/facebook/react/devsupport/ReactInstanceDevHelper;", "getJSAppBundleName", "()Ljava/lang/String;", "setJsAppBundleName", "(Ljava/lang/String;)V", "getRedBoxHandler", "()Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "getDevLoadingViewManager", "()Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;", "setDevLoadingViewManager", "(Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;)V", "uniqueTag", "getUniqueTag", "value", "Lcom/facebook/react/bridge/ReactContext;", "currentReactContext", "getCurrentReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "devSettings", "Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "getDevSettings", "()Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "isDevSupportEnabled", "devSupportEnabled", "getDevSupportEnabled", "()Z", "setDevSupportEnabled", "(Z)V", "shakeGestureEnabled", "getShakeGestureEnabled", "setShakeGestureEnabled", "bundleFilePath", "getBundleFilePath", "setBundleFilePath", "sourceMapUrl", "getSourceMapUrl", "sourceUrl", "getSourceUrl", "downloadedJSBundleFile", "getDownloadedJSBundleFile", "devServerHelper", "Lcom/facebook/react/devsupport/DevServerHelper;", "getDevServerHelper", "()Lcom/facebook/react/devsupport/DevServerHelper;", "lastErrorTitle", "getLastErrorTitle", "setLastErrorTitle", "lastErrorStack", "", "Lcom/facebook/react/devsupport/interfaces/StackFrame;", "getLastErrorStack", "()[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "setLastErrorStack", "([Lcom/facebook/react/devsupport/interfaces/StackFrame;)V", "[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "lastErrorType", "Lcom/facebook/react/devsupport/interfaces/ErrorType;", "getLastErrorType", "()Lcom/facebook/react/devsupport/interfaces/ErrorType;", "setLastErrorType", "(Lcom/facebook/react/devsupport/interfaces/ErrorType;)V", "lastErrorCookie", "getLastErrorCookie", "()I", "setLastErrorCookie", "(I)V", "shakeDetector", "Lcom/facebook/react/common/ShakeDetector;", "reloadAppBroadcastReceiver", "Landroid/content/BroadcastReceiver;", "customDevOptions", "Ljava/util/LinkedHashMap;", "Lcom/facebook/react/devsupport/interfaces/DevOptionHandler;", "Lkotlin/collections/LinkedHashMap;", "jsBundleDownloadedFile", "Ljava/io/File;", "jsSplitBundlesDir", "defaultJSExceptionHandler", "Lcom/facebook/react/bridge/DefaultJSExceptionHandler;", "redBoxSurfaceDelegate", "Lcom/facebook/react/common/SurfaceDelegate;", "devOptionsDialog", "Landroid/app/AlertDialog;", "debugOverlayController", "Lcom/facebook/react/devsupport/DebugOverlayController;", "devLoadingViewVisible", "pendingJSSplitBundleRequests", "isReceiverRegistered", "isShakeDetectorStarted", "isPackagerConnected", "errorCustomizers", "", "Lcom/facebook/react/devsupport/interfaces/ErrorCustomizer;", "packagerLocationCustomizer", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PackagerLocationCustomizer;", "jSExecutorDescription", "getJSExecutorDescription", "perfMonitorOverlayManager", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorOverlayManager;", "perfMonitorInitialized", "tracingStateProvider", "Lcom/facebook/react/devsupport/interfaces/TracingStateProvider;", "keyboardShortcutsEnabled", "getKeyboardShortcutsEnabled", "setKeyboardShortcutsEnabled", "devMenuEnabled", "getDevMenuEnabled", "setDevMenuEnabled", "handleException", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "showNewJavaError", "message", "", "addCustomDevOption", "optionName", "optionHandler", "showNewJSError", "details", "Lcom/facebook/react/bridge/ReadableArray;", "errorCookie", "registerErrorCustomizer", "errorCustomizer", "processErrorCustomizers", "Landroid/util/Pair;", "errorInfo", "hideRedboxDialog", "createRootView", "Landroid/view/View;", "appKey", "destroyRootView", "rootView", "logJSException", "hideDevOptionsDialog", "showNewError", StackTraceHelper.STACK_KEY, "errorType", "(Ljava/lang/String;[Lcom/facebook/react/devsupport/interfaces/StackFrame;ILcom/facebook/react/devsupport/interfaces/ErrorType;)V", "showDevOptionsDialog", "onNewReactContextCreated", "reactContext", "onReactInstanceDestroyed", "hasUpToDateJSBundleInCache", "resetCurrentContext", "reloadSettings", "showDevLoadingViewForUrl", "bundleUrl", "showDevLoadingViewForRemoteJSEnabled", "hideDevLoadingView", "fetchSplitBundleAndCreateBundleLoader", "bundlePath", "callback", "Lcom/facebook/react/devsupport/DevSupportManagerBase$CallbackWithBundleLoader;", "showSplitBundleDevLoadingView", "hideSplitBundleDevLoadingView", "isPackagerRunning", "Lcom/facebook/react/devsupport/interfaces/PackagerStatusCallback;", "downloadBundleResourceFromUrlSync", "resourceURL", "outputFile", "updateLastErrorInfo", "reloadJSFromServer", "bundleURL", "Lcom/facebook/react/devsupport/interfaces/BundleLoadCallback;", "reportBundleLoadingFailure", "cause", "startInspector", "stopInspector", "setHotModuleReplacementEnabled", "isHotModuleReplacementEnabled", "setFpsDebugEnabled", "isFpsDebugEnabled", "toggleElementInspector", "startShakeDetector", "stopShakeDetector", "reload", "setPackagerLocationCustomizer", "createSurfaceDelegate", "moduleName", "compatRegisterReceiver", "context", "receiver", ViewProps.FILTER, "Landroid/content/IntentFilter;", "exported", "safeGetDefaultTextColor", "Landroid/content/res/ColorStateList;", "safeGetTextColorSecondary", "openDebugger", "panel", "showPausedInDebuggerOverlay", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PausedInDebuggerOverlayCommandListener;", "hidePausedInDebuggerOverlay", "setAdditionalOptionForPackager", "name", "setTracingStateProvider", "provider", "setTracingStateProvider$ReactAndroid_release", "CallbackWithBundleLoader", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class DevSupportManagerBase implements DevSupportManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EXOPACKAGE_LOCATION_FORMAT = "/data/local/tmp/exopackage/%s//secondary-dex";
    private static final int JAVA_ERROR_COOKIE = -1;
    private static final int JSEXCEPTION_ERROR_COOKIE = -1;
    private static final String RELOAD_APP_ACTION_SUFFIX = ".RELOAD_APP_ACTION";
    private final Context applicationContext;
    private String bundleFilePath;
    private ReactContext currentReactContext;
    private final LinkedHashMap<String, DevOptionHandler> customDevOptions;
    private final Map<String, RequestHandler> customPackagerCommandHandlers;
    private DebugOverlayController debugOverlayController;
    private final DefaultJSExceptionHandler defaultJSExceptionHandler;
    private final DevBundleDownloadListener devBundleDownloadListener;
    private DevLoadingViewManager devLoadingViewManager;
    private boolean devLoadingViewVisible;
    private boolean devMenuEnabled;
    private AlertDialog devOptionsDialog;
    private final DevServerHelper devServerHelper;
    private final DeveloperSettings devSettings;
    private final List<ErrorCustomizer> errorCustomizers;
    private boolean isDevSupportEnabled;
    private boolean isPackagerConnected;
    private boolean isReceiverRegistered;
    private boolean isShakeDetectorStarted;
    private String jsAppBundleName;
    private final File jsBundleDownloadedFile;
    private final File jsSplitBundlesDir;
    private boolean keyboardShortcutsEnabled;
    private int lastErrorCookie;
    private StackFrame[] lastErrorStack;
    private String lastErrorTitle;
    private ErrorType lastErrorType;
    private DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer;
    private PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager;
    private int pendingJSSplitBundleRequests;
    private boolean perfMonitorInitialized;
    private PerfMonitorOverlayManager perfMonitorOverlayManager;
    private final ReactInstanceDevHelper reactInstanceDevHelper;
    private final RedBoxHandler redBoxHandler;
    private SurfaceDelegate redBoxSurfaceDelegate;
    private final BroadcastReceiver reloadAppBroadcastReceiver;
    private final ShakeDetector shakeDetector;
    private boolean shakeGestureEnabled;
    private final SurfaceDelegateFactory surfaceDelegateFactory;
    private TracingStateProvider tracingStateProvider;

    /* JADX INFO: compiled from: DevSupportManagerBase.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/DevSupportManagerBase$CallbackWithBundleLoader;", "", "onSuccess", "", "bundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "onError", "url", "", "cause", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface CallbackWithBundleLoader {
        void onError(String url, Throwable cause);

        void onSuccess(JSBundleLoader bundleLoader);
    }

    /* JADX INFO: compiled from: DevSupportManagerBase.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TracingState.values().length];
            try {
                iArr[TracingState.ENABLEDINBACKGROUNDMODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TracingState.ENABLEDINCDPMODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TracingState.DISABLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void showDevOptionsDialog$lambda$14() {
    }

    protected abstract String getUniqueTag();

    /* JADX WARN: Multi-variable type inference failed */
    public DevSupportManagerBase(Context applicationContext, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, ? extends RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(reactInstanceDevHelper, "reactInstanceDevHelper");
        this.applicationContext = applicationContext;
        this.reactInstanceDevHelper = reactInstanceDevHelper;
        this.jsAppBundleName = str;
        this.redBoxHandler = redBoxHandler;
        this.devBundleDownloadListener = devBundleDownloadListener;
        this.customPackagerCommandHandlers = map;
        this.surfaceDelegateFactory = surfaceDelegateFactory;
        this.devLoadingViewManager = devLoadingViewManager;
        this.pausedInDebuggerOverlayManager = pausedInDebuggerOverlayManager;
        DevInternalSettings devInternalSettings = new DevInternalSettings(applicationContext, new DevInternalSettings.Listener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$devSettings$1
            @Override // com.facebook.react.devsupport.DevInternalSettings.Listener
            public void onInternalSettingsChanged() {
                this.this$0.reloadSettings();
            }
        });
        this.devSettings = devInternalSettings;
        this.shakeGestureEnabled = true;
        this.devServerHelper = new DevServerHelper(devInternalSettings, applicationContext, devInternalSettings.getPackagerConnectionSettings());
        this.shakeDetector = new ShakeDetector(new ShakeDetector.ShakeListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda13
            @Override // com.facebook.react.common.ShakeDetector.ShakeListener
            public final void onShake() {
                this.f$0.showDevOptionsDialog();
            }
        }, i);
        this.reloadAppBroadcastReceiver = new BroadcastReceiver() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$reloadAppBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (Intrinsics.areEqual(DevSupportManagerBase.INSTANCE.getReloadAppAction(context), intent.getAction())) {
                    this.this$0.handleReloadJS();
                }
            }
        };
        this.customDevOptions = new LinkedHashMap<>();
        this.defaultJSExceptionHandler = new DefaultJSExceptionHandler();
        this.errorCustomizers = new ArrayList();
        this.keyboardShortcutsEnabled = true;
        this.devMenuEnabled = true;
        String uniqueTag = getUniqueTag();
        this.jsBundleDownloadedFile = new File(applicationContext.getFilesDir(), uniqueTag + "ReactNativeDevBundle.js");
        String lowerCase = uniqueTag.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        File dir = applicationContext.getDir(lowerCase + "_dev_js_split_bundles", 0);
        Intrinsics.checkNotNullExpressionValue(dir, "getDir(...)");
        this.jsSplitBundlesDir = dir;
        setDevSupportEnabled(z);
        if (this.devLoadingViewManager == null) {
            this.devLoadingViewManager = new DefaultDevLoadingViewImplementation(reactInstanceDevHelper);
        }
        if (this.pausedInDebuggerOverlayManager == null) {
            this.pausedInDebuggerOverlayManager = new PausedInDebuggerOverlayDialogManager(new Supplier() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda14
                @Override // androidx.core.util.Supplier
                public final Object get() {
                    return DevSupportManagerBase._init_$lambda$3(this.f$0);
                }
            });
        }
        if (ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && ReactNativeFeatureFlags.perfMonitorV2Enabled() && (reactInstanceDevHelper instanceof PerfMonitorDevHelper)) {
            this.perfMonitorOverlayManager = new PerfMonitorOverlayManager((PerfMonitorDevHelper) reactInstanceDevHelper, new Function0() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DevSupportManagerBase._init_$lambda$4(this.f$0);
                }
            });
        }
    }

    public static final /* synthetic */ void access$hideSplitBundleDevLoadingView(DevSupportManagerBase devSupportManagerBase) {
        devSupportManagerBase.hideSplitBundleDevLoadingView();
    }

    protected final Context getApplicationContext() {
        return this.applicationContext;
    }

    public final ReactInstanceDevHelper getReactInstanceDevHelper() {
        return this.reactInstanceDevHelper;
    }

    /* JADX INFO: renamed from: getJSAppBundleName, reason: from getter */
    public final String getJsAppBundleName() {
        return this.jsAppBundleName;
    }

    public final void setJsAppBundleName(String str) {
        this.jsAppBundleName = str;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public RedBoxHandler getRedBoxHandler() {
        return this.redBoxHandler;
    }

    public final DevLoadingViewManager getDevLoadingViewManager() {
        return this.devLoadingViewManager;
    }

    public final void setDevLoadingViewManager(DevLoadingViewManager devLoadingViewManager) {
        this.devLoadingViewManager = devLoadingViewManager;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final ReactContext getCurrentReactContext() {
        return this.currentReactContext;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final DeveloperSettings getDevSettings() {
        return this.devSettings;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public Activity getCurrentActivity() {
        return this.reactInstanceDevHelper.getCurrentActivity();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    /* JADX INFO: renamed from: getDevSupportEnabled, reason: from getter */
    public final boolean getIsDevSupportEnabled() {
        return this.isDevSupportEnabled;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final void setDevSupportEnabled(boolean z) {
        this.isDevSupportEnabled = z;
        reloadSettings();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final boolean getShakeGestureEnabled() {
        return this.shakeGestureEnabled;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final void setShakeGestureEnabled(boolean z) {
        if (this.shakeGestureEnabled == z) {
            return;
        }
        if (z) {
            startShakeDetector();
        } else {
            stopShakeDetector();
        }
        this.shakeGestureEnabled = z;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getBundleFilePath() {
        return this.bundleFilePath;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setBundleFilePath(String str) {
        this.bundleFilePath = str;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceMapUrl() {
        String sourceMapUrl;
        String str = this.jsAppBundleName;
        return (str == null || (sourceMapUrl = this.devServerHelper.getSourceMapUrl(str)) == null) ? "" : sourceMapUrl;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceUrl() {
        String sourceUrl;
        String str = this.jsAppBundleName;
        return (str == null || (sourceUrl = this.devServerHelper.getSourceUrl(str)) == null) ? "" : sourceUrl;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getDownloadedJSBundleFile() {
        String absolutePath = this.jsBundleDownloadedFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    public final DevServerHelper getDevServerHelper() {
        return this.devServerHelper;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final String getLastErrorTitle() {
        return this.lastErrorTitle;
    }

    public final void setLastErrorTitle(String str) {
        this.lastErrorTitle = str;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final StackFrame[] getLastErrorStack() {
        return this.lastErrorStack;
    }

    public final void setLastErrorStack(StackFrame[] stackFrameArr) {
        this.lastErrorStack = stackFrameArr;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final ErrorType getLastErrorType() {
        return this.lastErrorType;
    }

    public final void setLastErrorType(ErrorType errorType) {
        this.lastErrorType = errorType;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public final int getLastErrorCookie() {
        return this.lastErrorCookie;
    }

    public final void setLastErrorCookie(int i) {
        this.lastErrorCookie = i;
    }

    private final String getJSExecutorDescription() {
        try {
            return this.reactInstanceDevHelper.getJavaScriptExecutorFactory().toString();
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean getKeyboardShortcutsEnabled() {
        return this.keyboardShortcutsEnabled;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setKeyboardShortcutsEnabled(boolean z) {
        this.keyboardShortcutsEnabled = z;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean getDevMenuEnabled() {
        return this.devMenuEnabled;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setDevMenuEnabled(boolean z) {
        this.devMenuEnabled = z;
    }

    public static final Context _init_$lambda$3(DevSupportManagerBase devSupportManagerBase) {
        Activity currentActivity = devSupportManagerBase.reactInstanceDevHelper.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            return null;
        }
        return currentActivity;
    }

    public static final Unit _init_$lambda$4(DevSupportManagerBase devSupportManagerBase) {
        devSupportManagerBase.openDebugger(DebuggerFrontendPanelName.PERFORMANCE.toString());
        return Unit.INSTANCE;
    }

    @Override // com.facebook.react.bridge.JSExceptionHandler
    public void handleException(Exception e) throws Exception {
        Intrinsics.checkNotNullParameter(e, "e");
        if (this.isDevSupportEnabled) {
            logJSException(e);
        } else {
            this.defaultJSExceptionHandler.handleException(e);
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJavaError(String message, Throwable e) {
        Intrinsics.checkNotNullParameter(e, "e");
        FLog.e(ReactConstants.TAG, "Exception in native call", e);
        showNewError(message, StackTraceHelper.convertJavaStackTrace(e), -1, ErrorType.NATIVE);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void addCustomDevOption(String optionName, DevOptionHandler optionHandler) {
        Intrinsics.checkNotNullParameter(optionName, "optionName");
        Intrinsics.checkNotNullParameter(optionHandler, "optionHandler");
        this.customDevOptions.put(optionName, optionHandler);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJSError(String message, ReadableArray details, int errorCookie) {
        showNewError(message, StackTraceHelper.convertJsStackTrace(details), errorCookie, ErrorType.JS);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
        Intrinsics.checkNotNullParameter(errorCustomizer, "errorCustomizer");
        this.errorCustomizers.add(errorCustomizer);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> errorInfo) {
        Intrinsics.checkNotNullParameter(errorInfo, "errorInfo");
        Iterator<ErrorCustomizer> it = this.errorCustomizers.iterator();
        Pair<String, StackFrame[]> pairCustomizeErrorInfo = errorInfo;
        while (it.hasNext()) {
            pairCustomizeErrorInfo = it.next().customizeErrorInfo(errorInfo);
        }
        return pairCustomizeErrorInfo;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hideRedboxDialog() {
        SurfaceDelegate surfaceDelegate = this.redBoxSurfaceDelegate;
        if (surfaceDelegate != null) {
            surfaceDelegate.hide();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public View createRootView(String appKey) {
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        return this.reactInstanceDevHelper.createRootView(appKey);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void destroyRootView(View rootView) {
        if (rootView != null) {
            this.reactInstanceDevHelper.destroyRootView(rootView);
        }
    }

    private final void logJSException(Exception e) {
        String message = e.getMessage();
        if (message == null) {
            message = "Exception in native call from JS";
        }
        StringBuilder sb = new StringBuilder(message);
        for (Throwable cause = e.getCause(); cause != null; cause = cause.getCause()) {
            sb.append("\n\n").append(cause.getMessage());
        }
        if (e instanceof JavascriptException) {
            FLog.e(ReactConstants.TAG, "Exception in native call from JS", e);
            showNewError(e.getMessage(), new StackFrame[0], -1, ErrorType.JS);
        } else {
            showNewJavaError(sb.toString(), e);
        }
    }

    private final void hideDevOptionsDialog() {
        AlertDialog alertDialog = this.devOptionsDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.devOptionsDialog = null;
    }

    private final void showNewError(final String message, final StackFrame[] stackFrameArr, final int errorCookie, final ErrorType errorType) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.showNewError$lambda$7(this.f$0, message, stackFrameArr, errorCookie, errorType);
            }
        });
    }

    public static final void showNewError$lambda$7(DevSupportManagerBase devSupportManagerBase, String str, StackFrame[] stackFrameArr, int i, ErrorType errorType) {
        SurfaceDelegate surfaceDelegate;
        devSupportManagerBase.updateLastErrorInfo(str, stackFrameArr, i, errorType);
        if (devSupportManagerBase.redBoxSurfaceDelegate == null) {
            RedBoxDialogSurfaceDelegate redBoxDialogSurfaceDelegateCreateSurfaceDelegate = devSupportManagerBase.createSurfaceDelegate(NativeRedBoxSpec.NAME);
            if (redBoxDialogSurfaceDelegateCreateSurfaceDelegate == null) {
                RedBoxDialogSurfaceDelegate redBoxDialogSurfaceDelegate = new RedBoxDialogSurfaceDelegate(devSupportManagerBase);
                redBoxDialogSurfaceDelegate.createContentView(NativeRedBoxSpec.NAME);
                redBoxDialogSurfaceDelegateCreateSurfaceDelegate = redBoxDialogSurfaceDelegate;
            }
            devSupportManagerBase.redBoxSurfaceDelegate = redBoxDialogSurfaceDelegateCreateSurfaceDelegate;
        }
        SurfaceDelegate surfaceDelegate2 = devSupportManagerBase.redBoxSurfaceDelegate;
        if ((surfaceDelegate2 == null || !surfaceDelegate2.isShowing()) && (surfaceDelegate = devSupportManagerBase.redBoxSurfaceDelegate) != null) {
            surfaceDelegate.show();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showDevOptionsDialog() {
        String string;
        RCTNativeAppEventEmitter rCTNativeAppEventEmitter;
        AlertDialog alertDialog;
        ListView listView;
        String string2;
        String string3;
        TracingState tracingState;
        String string4;
        DevOptionHandler devOptionHandler;
        int i;
        if (this.devOptionsDialog == null && this.isDevSupportEnabled && getDevMenuEnabled() && !ActivityManager.isUserAMonkey()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            final HashSet hashSet = new HashSet();
            LinkedHashMap linkedHashMap2 = linkedHashMap;
            linkedHashMap2.put(this.applicationContext.getString(R.string.catalyst_reload), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda22
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public final void onOptionSelected() {
                    DevSupportManagerBase.showDevOptionsDialog$lambda$8(this.f$0);
                }
            });
            if (this.devSettings.getIsDeviceDebugEnabled()) {
                boolean z = this.isPackagerConnected;
                Context context = this.applicationContext;
                if (z) {
                    i = R.string.catalyst_debug_open;
                } else {
                    i = R.string.catalyst_debug_open_disabled;
                }
                String string5 = context.getString(i);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                if (!z) {
                    hashSet.add(string5);
                }
                linkedHashMap2.put(string5, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda1
                    @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                    public final void onOptionSelected() {
                        DevSupportManagerBase.showDevOptionsDialog$lambda$9(this.f$0);
                    }
                });
            }
            if (ReactNativeFeatureFlags.perfMonitorV2Enabled()) {
                boolean z2 = this.isPackagerConnected;
                TracingStateProvider tracingStateProvider = this.tracingStateProvider;
                if (tracingStateProvider == null || (tracingState = tracingStateProvider.getTracingState()) == null) {
                    tracingState = TracingState.DISABLED;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[tracingState.ordinal()];
                if (i2 == 1) {
                    string4 = this.applicationContext.getString(R.string.catalyst_performance_background);
                } else if (i2 == 2) {
                    string4 = this.applicationContext.getString(R.string.catalyst_performance_cdp);
                } else {
                    if (i2 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    string4 = this.applicationContext.getString(R.string.catalyst_performance_disabled);
                }
                Intrinsics.checkNotNull(string4);
                if (!z2 || tracingState == TracingState.ENABLEDINCDPMODE) {
                    hashSet.add(string4);
                }
                int i3 = WhenMappings.$EnumSwitchMapping$0[tracingState.ordinal()];
                if (i3 == 1) {
                    devOptionHandler = new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda2
                        @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                        public final void onOptionSelected() {
                            DevSupportManagerBase.showDevOptionsDialog$lambda$12(this.f$0);
                        }
                    };
                } else if (i3 == 2) {
                    devOptionHandler = new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda4
                        @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                        public final void onOptionSelected() {
                            DevSupportManagerBase.showDevOptionsDialog$lambda$14();
                        }
                    };
                } else if (i3 == 3) {
                    devOptionHandler = new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda3
                        @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                        public final void onOptionSelected() {
                            DevSupportManagerBase.showDevOptionsDialog$lambda$13(this.f$0);
                        }
                    };
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                linkedHashMap2.put(string4, devOptionHandler);
            }
            if (ReactNativeFeatureFlags.perfMonitorV2Enabled()) {
                boolean z3 = this.isPackagerConnected;
                PerfMonitorOverlayManager perfMonitorOverlayManager = this.perfMonitorOverlayManager;
                if (perfMonitorOverlayManager != null && perfMonitorOverlayManager.getEnabled()) {
                    string3 = this.applicationContext.getString(R.string.catalyst_performance_disable);
                } else {
                    string3 = this.applicationContext.getString(R.string.catalyst_performance_enable);
                }
                Intrinsics.checkNotNull(string3);
                if (!z3) {
                    hashSet.add(string3);
                }
                PerfMonitorOverlayManager perfMonitorOverlayManager2 = this.perfMonitorOverlayManager;
                linkedHashMap2.put(string3, (perfMonitorOverlayManager2 == null || !perfMonitorOverlayManager2.getEnabled()) ? new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda6
                    @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                    public final void onOptionSelected() {
                        DevSupportManagerBase.showDevOptionsDialog$lambda$16(this.f$0);
                    }
                } : new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda5
                    @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                    public final void onOptionSelected() {
                        DevSupportManagerBase.showDevOptionsDialog$lambda$15(this.f$0);
                    }
                });
            }
            linkedHashMap2.put(this.applicationContext.getString(R.string.catalyst_change_bundle_location), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda7
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public final void onOptionSelected() {
                    DevSupportManagerBase.showDevOptionsDialog$lambda$18(this.f$0);
                }
            });
            linkedHashMap2.put(this.applicationContext.getString(R.string.catalyst_inspector_toggle), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda8
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public final void onOptionSelected() {
                    DevSupportManagerBase.showDevOptionsDialog$lambda$19(this.f$0);
                }
            });
            if (this.devSettings.isHotModuleReplacementEnabled()) {
                string = this.applicationContext.getString(R.string.catalyst_hot_reloading_stop);
            } else {
                string = this.applicationContext.getString(R.string.catalyst_hot_reloading);
            }
            Intrinsics.checkNotNull(string);
            linkedHashMap2.put(string, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda9
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public final void onOptionSelected() {
                    DevSupportManagerBase.showDevOptionsDialog$lambda$20(this.f$0);
                }
            });
            if (!ReactNativeFeatureFlags.perfMonitorV2Enabled()) {
                if (this.devSettings.isFpsDebugEnabled()) {
                    string2 = this.applicationContext.getString(R.string.catalyst_perf_monitor_stop);
                } else {
                    string2 = this.applicationContext.getString(R.string.catalyst_perf_monitor);
                }
                Intrinsics.checkNotNull(string2);
                linkedHashMap2.put(string2, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda23
                    @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                    public final void onOptionSelected() {
                        DevSupportManagerBase.showDevOptionsDialog$lambda$21(this.f$0);
                    }
                });
                linkedHashMap2.put(this.applicationContext.getString(R.string.catalyst_settings), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda24
                    @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                    public final void onOptionSelected() {
                        DevSupportManagerBase.showDevOptionsDialog$lambda$22(this.f$0);
                    }
                });
            }
            if (!this.customDevOptions.isEmpty()) {
                linkedHashMap.putAll(this.customDevOptions);
            }
            Collection collectionValues = linkedHashMap.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
            final DevOptionHandler[] devOptionHandlerArr = (DevOptionHandler[]) collectionValues.toArray(new DevOptionHandler[0]);
            final Activity currentActivity = this.reactInstanceDevHelper.getCurrentActivity();
            if (currentActivity == null || currentActivity.isFinishing()) {
                FLog.e(ReactConstants.TAG, "Unable to launch dev options menu because react activity isn't available");
                return;
            }
            Activity activity = currentActivity;
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(activity);
            textView.setText(currentActivity.getString(R.string.catalyst_dev_menu_header));
            textView.setPadding(0, 50, 0, 0);
            textView.setGravity(17);
            textView.setTextSize(16.0f);
            textView.setTypeface(textView.getTypeface(), 1);
            linearLayout.addView(textView);
            String jSExecutorDescription = getJSExecutorDescription();
            if (jSExecutorDescription != null) {
                TextView textView2 = new TextView(activity);
                textView2.setText(currentActivity.getString(R.string.catalyst_dev_menu_sub_header, new Object[]{jSExecutorDescription}));
                textView2.setPadding(0, 20, 0, 0);
                textView2.setGravity(17);
                textView2.setTextSize(14.0f);
                linearLayout.addView(textView2);
            }
            Set setKeySet = linkedHashMap.keySet();
            Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
            final String[] strArr = (String[]) setKeySet.toArray(new String[0]);
            AlertDialog alertDialogCreate = new AlertDialog.Builder(activity).setCustomTitle(linearLayout).setAdapter(new ArrayAdapter<String>(currentActivity, hashSet, this, strArr) { // from class: com.facebook.react.devsupport.DevSupportManagerBase$showDevOptionsDialog$adapter$1
                final /* synthetic */ Activity $context;
                final /* synthetic */ Set<String> $disabledItemKeys;
                final /* synthetic */ DevSupportManagerBase this$0;

                @Override // android.widget.BaseAdapter, android.widget.ListAdapter
                public boolean areAllItemsEnabled() {
                    return false;
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(currentActivity, android.R.layout.simple_list_item_1, strArr);
                    this.$context = currentActivity;
                    this.$disabledItemKeys = hashSet;
                    this.this$0 = this;
                }

                @Override // android.widget.BaseAdapter, android.widget.ListAdapter
                public boolean isEnabled(int position) {
                    return !this.$disabledItemKeys.contains(getItem(position));
                }

                @Override // android.widget.ArrayAdapter, android.widget.Adapter
                public View getView(int position, View convertView, ViewGroup parent) {
                    Intrinsics.checkNotNullParameter(parent, "parent");
                    View view = super.getView(position, convertView, parent);
                    Intrinsics.checkNotNullExpressionValue(view, "getView(...)");
                    DevSupportManagerBase devSupportManagerBase = this.this$0;
                    Activity activity2 = this.$context;
                    view.setEnabled(isEnabled(position));
                    if (view instanceof TextView) {
                        TextView textView3 = (TextView) view;
                        textView3.setTextColor(textView3.isEnabled() ? devSupportManagerBase.safeGetDefaultTextColor(activity2) : devSupportManagerBase.safeGetTextColorSecondary(activity2));
                    }
                    return view;
                }
            }, new DialogInterface.OnClickListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda25
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i4) {
                    DevSupportManagerBase.showDevOptionsDialog$lambda$25(devOptionHandlerArr, this, dialogInterface, i4);
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda26
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    this.f$0.devOptionsDialog = null;
                }
            }).create();
            this.devOptionsDialog = alertDialogCreate;
            if (alertDialogCreate != null) {
                alertDialogCreate.show();
            }
            if (Build.VERSION.SDK_INT <= 30 && (alertDialog = this.devOptionsDialog) != null && (listView = alertDialog.getListView()) != null) {
                int i4 = (int) (((double) currentActivity.getResources().getDisplayMetrics().heightPixels) * 0.7d);
                ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new ViewGroup.LayoutParams(-1, -2);
                }
                layoutParams.height = i4;
                listView.setLayoutParams(layoutParams);
            }
            ReactContext reactContext = this.currentReactContext;
            if (reactContext == null || (rCTNativeAppEventEmitter = (RCTNativeAppEventEmitter) reactContext.getJSModule(RCTNativeAppEventEmitter.class)) == null) {
                return;
            }
            rCTNativeAppEventEmitter.emit("RCTDevMenuShown", null);
        }
    }

    public static final void showDevOptionsDialog$lambda$8(DevSupportManagerBase devSupportManagerBase) {
        if (!devSupportManagerBase.devSettings.isJSDevModeEnabled() && devSupportManagerBase.devSettings.isHotModuleReplacementEnabled()) {
            Context context = devSupportManagerBase.applicationContext;
            Toast.makeText(context, context.getString(R.string.catalyst_hot_reloading_auto_disable), 1).show();
            devSupportManagerBase.devSettings.setHotModuleReplacementEnabled(false);
        }
        devSupportManagerBase.handleReloadJS();
    }

    public static final void showDevOptionsDialog$lambda$9(DevSupportManagerBase devSupportManagerBase) {
        DevSupportManager.openDebugger$default(devSupportManagerBase, null, 1, null);
    }

    public static final void showDevOptionsDialog$lambda$12(final DevSupportManagerBase devSupportManagerBase) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.showDevOptionsDialog$lambda$12$lambda$11(this.f$0);
            }
        });
    }

    public static final void showDevOptionsDialog$lambda$12$lambda$11(DevSupportManagerBase devSupportManagerBase) {
        PerfMonitorInspectorTarget inspectorTarget;
        ReactInstanceDevHelper reactInstanceDevHelper = devSupportManagerBase.reactInstanceDevHelper;
        if (!(reactInstanceDevHelper instanceof PerfMonitorDevHelper) || (inspectorTarget = ((PerfMonitorDevHelper) reactInstanceDevHelper).getInspectorTarget()) == null || inspectorTarget.pauseAndAnalyzeBackgroundTrace()) {
            return;
        }
        devSupportManagerBase.openDebugger(DebuggerFrontendPanelName.PERFORMANCE.toString());
    }

    public static final void showDevOptionsDialog$lambda$13(DevSupportManagerBase devSupportManagerBase) {
        PerfMonitorInspectorTarget inspectorTarget;
        ReactInstanceDevHelper reactInstanceDevHelper = devSupportManagerBase.reactInstanceDevHelper;
        if (!(reactInstanceDevHelper instanceof PerfMonitorDevHelper) || (inspectorTarget = ((PerfMonitorDevHelper) reactInstanceDevHelper).getInspectorTarget()) == null) {
            return;
        }
        inspectorTarget.resumeBackgroundTrace();
    }

    public static final void showDevOptionsDialog$lambda$15(DevSupportManagerBase devSupportManagerBase) {
        PerfMonitorOverlayManager perfMonitorOverlayManager = devSupportManagerBase.perfMonitorOverlayManager;
        if (perfMonitorOverlayManager != null) {
            perfMonitorOverlayManager.disable();
        }
    }

    public static final void showDevOptionsDialog$lambda$16(DevSupportManagerBase devSupportManagerBase) {
        PerfMonitorOverlayManager perfMonitorOverlayManager = devSupportManagerBase.perfMonitorOverlayManager;
        if (perfMonitorOverlayManager != null) {
            perfMonitorOverlayManager.enable();
        }
    }

    public static final void showDevOptionsDialog$lambda$18(final DevSupportManagerBase devSupportManagerBase) {
        Activity currentActivity = devSupportManagerBase.reactInstanceDevHelper.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            FLog.e(ReactConstants.TAG, "Unable to launch change bundle location because react activity is not available");
        } else {
            ChangeBundleLocationDialog.INSTANCE.show(currentActivity, devSupportManagerBase.devSettings, new ChangeBundleLocationDialog.ChangeBundleLocationDialogListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda21
                @Override // com.facebook.react.devsupport.ChangeBundleLocationDialog.ChangeBundleLocationDialogListener
                public final void onClick(String str) {
                    DevSupportManagerBase.showDevOptionsDialog$lambda$18$lambda$17(this.f$0, str);
                }
            });
        }
    }

    public static final void showDevOptionsDialog$lambda$18$lambda$17(DevSupportManagerBase devSupportManagerBase, String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        devSupportManagerBase.devSettings.getPackagerConnectionSettings().setDebugServerHost(host);
        devSupportManagerBase.handleReloadJS();
    }

    public static final void showDevOptionsDialog$lambda$19(DevSupportManagerBase devSupportManagerBase) {
        devSupportManagerBase.devSettings.setElementInspectorEnabled(!r0.isElementInspectorEnabled());
        devSupportManagerBase.reactInstanceDevHelper.toggleElementInspector();
    }

    public static final void showDevOptionsDialog$lambda$20(DevSupportManagerBase devSupportManagerBase) {
        boolean zIsHotModuleReplacementEnabled = devSupportManagerBase.devSettings.isHotModuleReplacementEnabled();
        devSupportManagerBase.devSettings.setHotModuleReplacementEnabled(!zIsHotModuleReplacementEnabled);
        ReactContext reactContext = devSupportManagerBase.currentReactContext;
        if (reactContext != null) {
            if (!zIsHotModuleReplacementEnabled) {
                HMRClient hMRClient = (HMRClient) reactContext.getJSModule(HMRClient.class);
                if (hMRClient != null) {
                    hMRClient.enable();
                }
            } else {
                HMRClient hMRClient2 = (HMRClient) reactContext.getJSModule(HMRClient.class);
                if (hMRClient2 != null) {
                    hMRClient2.disable();
                }
            }
        }
        if (zIsHotModuleReplacementEnabled || devSupportManagerBase.devSettings.isJSDevModeEnabled()) {
            return;
        }
        Context context = devSupportManagerBase.applicationContext;
        Toast.makeText(context, context.getString(R.string.catalyst_hot_reloading_auto_enable), 1).show();
        devSupportManagerBase.devSettings.setJSDevModeEnabled(true);
        devSupportManagerBase.handleReloadJS();
    }

    public static final void showDevOptionsDialog$lambda$21(DevSupportManagerBase devSupportManagerBase) {
        if (!devSupportManagerBase.devSettings.isFpsDebugEnabled()) {
            Activity currentActivity = devSupportManagerBase.reactInstanceDevHelper.getCurrentActivity();
            if (currentActivity == null) {
                FLog.e(ReactConstants.TAG, "Unable to get reference to react activity");
            } else {
                DebugOverlayController.INSTANCE.requestPermission(currentActivity);
            }
        }
        devSupportManagerBase.devSettings.setFpsDebugEnabled(!r2.isFpsDebugEnabled());
    }

    public static final void showDevOptionsDialog$lambda$22(DevSupportManagerBase devSupportManagerBase) {
        Intent intent = new Intent(devSupportManagerBase.applicationContext, (Class<?>) DevSettingsActivity.class);
        intent.setFlags(268435456);
        devSupportManagerBase.applicationContext.startActivity(intent);
    }

    public static final void showDevOptionsDialog$lambda$25(DevOptionHandler[] devOptionHandlerArr, DevSupportManagerBase devSupportManagerBase, DialogInterface dialogInterface, int i) {
        devOptionHandlerArr[i].onOptionSelected();
        devSupportManagerBase.devOptionsDialog = null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onNewReactContextCreated(ReactContext reactContext) {
        PerfMonitorInspectorTarget inspectorTarget;
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        if (!this.perfMonitorInitialized) {
            ReactInstanceDevHelper reactInstanceDevHelper = this.reactInstanceDevHelper;
            if (reactInstanceDevHelper instanceof PerfMonitorDevHelper) {
                PerfMonitorOverlayManager perfMonitorOverlayManager = this.perfMonitorOverlayManager;
                if (perfMonitorOverlayManager != null && (inspectorTarget = ((PerfMonitorDevHelper) reactInstanceDevHelper).getInspectorTarget()) != null) {
                    inspectorTarget.addPerfMonitorListener(perfMonitorOverlayManager);
                }
                if (this.isPackagerConnected) {
                    PerfMonitorOverlayManager perfMonitorOverlayManager2 = this.perfMonitorOverlayManager;
                    if (perfMonitorOverlayManager2 != null) {
                        perfMonitorOverlayManager2.enable();
                    }
                    PerfMonitorOverlayManager perfMonitorOverlayManager3 = this.perfMonitorOverlayManager;
                    if (perfMonitorOverlayManager3 != null) {
                        perfMonitorOverlayManager3.startBackgroundTrace();
                    }
                }
                this.perfMonitorInitialized = true;
            }
        }
        resetCurrentContext(reactContext);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onReactInstanceDestroyed(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        if (reactContext == this.currentReactContext) {
            resetCurrentContext(null);
        }
        System.gc();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean hasUpToDateJSBundleInCache() {
        if (this.isDevSupportEnabled && this.jsBundleDownloadedFile.exists()) {
            try {
                String packageName = this.applicationContext.getPackageName();
                PackageManager packageManager = this.applicationContext.getPackageManager();
                if (packageManager != null) {
                    if (this.jsBundleDownloadedFile.lastModified() > packageManager.getPackageInfo(packageName, 0).lastUpdateTime) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str = String.format(Locale.US, EXOPACKAGE_LOCATION_FORMAT, Arrays.copyOf(new Object[]{packageName}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        File file = new File(str);
                        if (file.exists()) {
                            return this.jsBundleDownloadedFile.lastModified() > file.lastModified();
                        }
                        return true;
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
                FLog.e(ReactConstants.TAG, "DevSupport is unable to get current app info");
            }
        }
        return false;
    }

    private final void resetCurrentContext(ReactContext reactContext) {
        if (this.currentReactContext == reactContext) {
            return;
        }
        this.currentReactContext = reactContext;
        DebugOverlayController debugOverlayController = this.debugOverlayController;
        if (debugOverlayController != null) {
            debugOverlayController.setFpsDebugViewVisible(false);
        }
        if (reactContext != null) {
            this.debugOverlayController = new DebugOverlayController(reactContext);
        }
        if (reactContext != null) {
            try {
                URL url = new URL(getSourceUrl());
                String path = url.getPath();
                if (path != null) {
                    path = path.substring(1);
                    Intrinsics.checkNotNullExpressionValue(path, "substring(...)");
                }
                ((HMRClient) reactContext.getJSModule(HMRClient.class)).setup("android", path, url.getHost(), url.getPort() != -1 ? url.getPort() : url.getDefaultPort(), this.devSettings.isHotModuleReplacementEnabled(), url.getProtocol());
            } catch (MalformedURLException e) {
                showNewJavaError(e.getMessage(), e);
            }
        }
        reloadSettings();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadSettings() {
        if (UiThreadUtil.isOnUiThread()) {
            reload();
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda19
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.reload();
                }
            });
        }
    }

    private final void showDevLoadingViewForUrl(String bundleUrl) {
        try {
            URL url = new URL(bundleUrl);
            int port = url.getPort() != -1 ? url.getPort() : url.getDefaultPort();
            DevLoadingViewManager devLoadingViewManager = this.devLoadingViewManager;
            if (devLoadingViewManager != null) {
                String string = this.applicationContext.getString(R.string.catalyst_loading_from_url, url.getHost() + ":" + port);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                devLoadingViewManager.showMessage(string);
            }
            this.devLoadingViewVisible = true;
        } catch (MalformedURLException e) {
            FLog.e(ReactConstants.TAG, "Bundle url format is invalid. \n\n" + e);
        }
    }

    protected final void showDevLoadingViewForRemoteJSEnabled() {
        DevLoadingViewManager devLoadingViewManager = this.devLoadingViewManager;
        if (devLoadingViewManager != null) {
            String string = this.applicationContext.getString(R.string.catalyst_debug_connecting);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            devLoadingViewManager.showMessage(string);
        }
        this.devLoadingViewVisible = true;
    }

    protected final void hideDevLoadingView() {
        DevLoadingViewManager devLoadingViewManager = this.devLoadingViewManager;
        if (devLoadingViewManager != null) {
            devLoadingViewManager.hide();
        }
        this.devLoadingViewVisible = false;
    }

    public final void fetchSplitBundleAndCreateBundleLoader(String bundlePath, final CallbackWithBundleLoader callback) {
        Intrinsics.checkNotNullParameter(bundlePath, "bundlePath");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final String devServerSplitBundleURL = this.devServerHelper.getDevServerSplitBundleURL(bundlePath);
        final File file = new File(this.jsSplitBundlesDir, new Regex("/").replace(bundlePath, "_") + ".jsbundle");
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.fetchSplitBundleAndCreateBundleLoader$lambda$31(this.f$0, devServerSplitBundleURL, file, callback);
            }
        });
    }

    public static final void fetchSplitBundleAndCreateBundleLoader$lambda$31(DevSupportManagerBase devSupportManagerBase, String str, File file, CallbackWithBundleLoader callbackWithBundleLoader) {
        devSupportManagerBase.showSplitBundleDevLoadingView(str);
        DevServerHelper.downloadBundleFromURL$default(devSupportManagerBase.devServerHelper, new DevSupportManagerBase$fetchSplitBundleAndCreateBundleLoader$1$1(devSupportManagerBase, str, file, callbackWithBundleLoader), file, str, null, null, 16, null);
    }

    private final void showSplitBundleDevLoadingView(String bundleUrl) {
        showDevLoadingViewForUrl(bundleUrl);
        this.pendingJSSplitBundleRequests++;
    }

    public final void hideSplitBundleDevLoadingView() {
        int i = this.pendingJSSplitBundleRequests - 1;
        this.pendingJSSplitBundleRequests = i;
        if (i == 0) {
            hideDevLoadingView();
        }
    }

    public static final void isPackagerRunning$lambda$32(DevSupportManagerBase devSupportManagerBase, PackagerStatusCallback packagerStatusCallback) {
        devSupportManagerBase.devServerHelper.isPackagerRunning(packagerStatusCallback);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void isPackagerRunning(final PackagerStatusCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Runnable runnable = new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.isPackagerRunning$lambda$32(this.f$0, callback);
            }
        };
        DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer = this.packagerLocationCustomizer;
        if (packagerLocationCustomizer != null) {
            packagerLocationCustomizer.run(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile) {
        Intrinsics.checkNotNullParameter(resourceURL, "resourceURL");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        return this.devServerHelper.downloadBundleResourceFromUrlSync(resourceURL, outputFile);
    }

    private final void updateLastErrorInfo(String message, StackFrame[] stackFrameArr, int errorCookie, ErrorType errorType) {
        this.lastErrorTitle = message;
        this.lastErrorStack = stackFrameArr;
        this.lastErrorCookie = errorCookie;
        this.lastErrorType = errorType;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadJSFromServer(String bundleURL, BundleLoadCallback callback) {
        Intrinsics.checkNotNullParameter(bundleURL, "bundleURL");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_START);
        showDevLoadingViewForUrl(bundleURL);
        BundleDownloader.BundleInfo bundleInfo = new BundleDownloader.BundleInfo();
        DevServerHelper.downloadBundleFromURL$default(this.devServerHelper, new DevBundleDownloadListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.reloadJSFromServer.1
            final /* synthetic */ BundleDownloader.BundleInfo $bundleInfo;
            final /* synthetic */ BundleLoadCallback $callback;

            C00951(BundleDownloader.BundleInfo bundleInfo2, BundleLoadCallback callback2) {
                bundleInfo = bundleInfo2;
                bundleLoadCallback = callback2;
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onSuccess() {
                DevSupportManagerBase.this.hideDevLoadingView();
                DevBundleDownloadListener devBundleDownloadListener = DevSupportManagerBase.this.devBundleDownloadListener;
                if (devBundleDownloadListener != null) {
                    devBundleDownloadListener.onSuccess();
                }
                ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_END, bundleInfo.toJSONString());
                bundleLoadCallback.onSuccess();
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onProgress(String status, Integer done, Integer total) {
                DevLoadingViewManager devLoadingViewManager = DevSupportManagerBase.this.getDevLoadingViewManager();
                if (devLoadingViewManager != null) {
                    devLoadingViewManager.updateProgress(status, done, total);
                }
                DevBundleDownloadListener devBundleDownloadListener = DevSupportManagerBase.this.devBundleDownloadListener;
                if (devBundleDownloadListener != null) {
                    devBundleDownloadListener.onProgress(status, done, total);
                }
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onFailure(Exception cause) {
                Intrinsics.checkNotNullParameter(cause, "cause");
                DevSupportManagerBase.this.hideDevLoadingView();
                DevBundleDownloadListener devBundleDownloadListener = DevSupportManagerBase.this.devBundleDownloadListener;
                if (devBundleDownloadListener != null) {
                    devBundleDownloadListener.onFailure(cause);
                }
                FLog.e(ReactConstants.TAG, "Unable to download JS bundle", cause);
                DevSupportManagerBase.this.reportBundleLoadingFailure(cause);
                bundleLoadCallback.onError(cause);
            }
        }, this.jsBundleDownloadedFile, bundleURL, bundleInfo2, null, 16, null);
    }

    /* JADX INFO: renamed from: com.facebook.react.devsupport.DevSupportManagerBase$reloadJSFromServer$1 */
    /* JADX INFO: compiled from: DevSupportManagerBase.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J+\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u00020\u00032\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0016¨\u0006\u000f"}, d2 = {"com/facebook/react/devsupport/DevSupportManagerBase$reloadJSFromServer$1", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "onSuccess", "", "onProgress", "status", "", "done", "", "total", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "onFailure", "cause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00951 implements DevBundleDownloadListener {
        final /* synthetic */ BundleDownloader.BundleInfo $bundleInfo;
        final /* synthetic */ BundleLoadCallback $callback;

        C00951(BundleDownloader.BundleInfo bundleInfo2, BundleLoadCallback callback2) {
            bundleInfo = bundleInfo2;
            bundleLoadCallback = callback2;
        }

        @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
        public void onSuccess() {
            DevSupportManagerBase.this.hideDevLoadingView();
            DevBundleDownloadListener devBundleDownloadListener = DevSupportManagerBase.this.devBundleDownloadListener;
            if (devBundleDownloadListener != null) {
                devBundleDownloadListener.onSuccess();
            }
            ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_END, bundleInfo.toJSONString());
            bundleLoadCallback.onSuccess();
        }

        @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
        public void onProgress(String status, Integer done, Integer total) {
            DevLoadingViewManager devLoadingViewManager = DevSupportManagerBase.this.getDevLoadingViewManager();
            if (devLoadingViewManager != null) {
                devLoadingViewManager.updateProgress(status, done, total);
            }
            DevBundleDownloadListener devBundleDownloadListener = DevSupportManagerBase.this.devBundleDownloadListener;
            if (devBundleDownloadListener != null) {
                devBundleDownloadListener.onProgress(status, done, total);
            }
        }

        @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
        public void onFailure(Exception cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            DevSupportManagerBase.this.hideDevLoadingView();
            DevBundleDownloadListener devBundleDownloadListener = DevSupportManagerBase.this.devBundleDownloadListener;
            if (devBundleDownloadListener != null) {
                devBundleDownloadListener.onFailure(cause);
            }
            FLog.e(ReactConstants.TAG, "Unable to download JS bundle", cause);
            DevSupportManagerBase.this.reportBundleLoadingFailure(cause);
            bundleLoadCallback.onError(cause);
        }
    }

    public final void reportBundleLoadingFailure(final Exception cause) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                DevSupportManagerBase.reportBundleLoadingFailure$lambda$33(cause, this);
            }
        });
    }

    public static final void reportBundleLoadingFailure$lambda$33(Exception exc, DevSupportManagerBase devSupportManagerBase) {
        if (exc instanceof DebugServerException) {
            devSupportManagerBase.showNewJavaError(exc.getMessage(), exc);
        } else {
            devSupportManagerBase.showNewJavaError(devSupportManagerBase.applicationContext.getString(R.string.catalyst_reload_error), exc);
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
        if (this.isDevSupportEnabled) {
            this.devServerHelper.openInspectorConnection();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void stopInspector() {
        this.devServerHelper.closeInspectorConnection();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setHotModuleReplacementEnabled(final boolean isHotModuleReplacementEnabled) {
        if (this.isDevSupportEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    DevSupportManagerBase.setHotModuleReplacementEnabled$lambda$34(this.f$0, isHotModuleReplacementEnabled);
                }
            });
        }
    }

    public static final void setHotModuleReplacementEnabled$lambda$34(DevSupportManagerBase devSupportManagerBase, boolean z) {
        devSupportManagerBase.devSettings.setHotModuleReplacementEnabled(z);
        devSupportManagerBase.handleReloadJS();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setFpsDebugEnabled(final boolean isFpsDebugEnabled) {
        if (this.isDevSupportEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    DevSupportManagerBase.setFpsDebugEnabled$lambda$35(this.f$0, isFpsDebugEnabled);
                }
            });
        }
    }

    public static final void setFpsDebugEnabled$lambda$35(DevSupportManagerBase devSupportManagerBase, boolean z) {
        devSupportManagerBase.devSettings.setFpsDebugEnabled(z);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void toggleElementInspector() {
        if (this.isDevSupportEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    DevSupportManagerBase.toggleElementInspector$lambda$36(this.f$0);
                }
            });
        }
    }

    public static final void toggleElementInspector$lambda$36(DevSupportManagerBase devSupportManagerBase) {
        devSupportManagerBase.devSettings.setElementInspectorEnabled(!r0.isElementInspectorEnabled());
        devSupportManagerBase.reactInstanceDevHelper.toggleElementInspector();
    }

    private final void startShakeDetector() {
        Object systemService = this.applicationContext.getSystemService("sensor");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        this.shakeDetector.start((SensorManager) systemService);
        this.isShakeDetectorStarted = true;
    }

    private final void stopShakeDetector() {
        this.shakeDetector.stop();
        this.isShakeDetectorStarted = false;
    }

    public final void reload() {
        DevLoadingViewManager devLoadingViewManager;
        UiThreadUtil.assertOnUiThread();
        if (this.isDevSupportEnabled) {
            DebugOverlayController debugOverlayController = this.debugOverlayController;
            if (debugOverlayController != null) {
                debugOverlayController.setFpsDebugViewVisible(this.devSettings.isFpsDebugEnabled());
            }
            if (!this.isShakeDetectorStarted && getShakeGestureEnabled()) {
                startShakeDetector();
            }
            if (!this.isReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(INSTANCE.getReloadAppAction(this.applicationContext));
                compatRegisterReceiver(this.applicationContext, this.reloadAppBroadcastReceiver, intentFilter, true);
                this.isReceiverRegistered = true;
            }
            if (this.devLoadingViewVisible && (devLoadingViewManager = this.devLoadingViewManager) != null) {
                devLoadingViewManager.showMessage("Reloading...");
            }
            this.devServerHelper.openPackagerConnection(getClass().getSimpleName(), new AnonymousClass1());
            return;
        }
        DebugOverlayController debugOverlayController2 = this.debugOverlayController;
        if (debugOverlayController2 != null) {
            debugOverlayController2.setFpsDebugViewVisible(false);
        }
        if (this.isShakeDetectorStarted) {
            stopShakeDetector();
        }
        if (this.isReceiverRegistered) {
            this.applicationContext.unregisterReceiver(this.reloadAppBroadcastReceiver);
            this.isReceiverRegistered = false;
        }
        hideRedboxDialog();
        hideDevOptionsDialog();
        DevLoadingViewManager devLoadingViewManager2 = this.devLoadingViewManager;
        if (devLoadingViewManager2 != null) {
            devLoadingViewManager2.hide();
        }
        PerfMonitorOverlayManager perfMonitorOverlayManager = this.perfMonitorOverlayManager;
        if (perfMonitorOverlayManager != null) {
            perfMonitorOverlayManager.disable();
        }
        this.devServerHelper.closePackagerConnection();
    }

    /* JADX INFO: renamed from: com.facebook.react.devsupport.DevSupportManagerBase$reload$1 */
    /* JADX INFO: compiled from: DevSupportManagerBase.kt */
    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016¨\u0006\u000b"}, d2 = {"com/facebook/react/devsupport/DevSupportManagerBase$reload$1", "Lcom/facebook/react/devsupport/DevServerHelper$PackagerCommandListener;", "onPackagerConnected", "", "onPackagerDisconnected", "onPackagerReloadCommand", "onPackagerDevMenuCommand", "customCommandHandlers", "", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements DevServerHelper.PackagerCommandListener {
        AnonymousClass1() {
        }

        @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
        public void onPackagerConnected() {
            DevSupportManagerBase.this.isPackagerConnected = true;
            PerfMonitorOverlayManager perfMonitorOverlayManager = DevSupportManagerBase.this.perfMonitorOverlayManager;
            if (perfMonitorOverlayManager != null) {
                perfMonitorOverlayManager.enable();
            }
            PerfMonitorOverlayManager perfMonitorOverlayManager2 = DevSupportManagerBase.this.perfMonitorOverlayManager;
            if (perfMonitorOverlayManager2 != null) {
                perfMonitorOverlayManager2.startBackgroundTrace();
            }
        }

        @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
        public void onPackagerDisconnected() {
            DevSupportManagerBase.this.isPackagerConnected = false;
            PerfMonitorOverlayManager perfMonitorOverlayManager = DevSupportManagerBase.this.perfMonitorOverlayManager;
            if (perfMonitorOverlayManager != null) {
                perfMonitorOverlayManager.disable();
            }
            PerfMonitorOverlayManager perfMonitorOverlayManager2 = DevSupportManagerBase.this.perfMonitorOverlayManager;
            if (perfMonitorOverlayManager2 != null) {
                perfMonitorOverlayManager2.stopBackgroundTrace();
            }
        }

        @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
        public void onPackagerReloadCommand() {
            if (!InspectorFlags.getFuseboxEnabled()) {
                DevSupportManagerBase.this.getDevServerHelper().disableDebugger();
            }
            final DevSupportManagerBase devSupportManagerBase = DevSupportManagerBase.this;
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$reload$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    devSupportManagerBase.handleReloadJS();
                }
            });
        }

        @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
        public void onPackagerDevMenuCommand() {
            final DevSupportManagerBase devSupportManagerBase = DevSupportManagerBase.this;
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase$reload$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    devSupportManagerBase.showDevOptionsDialog();
                }
            });
        }

        @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
        public Map<String, RequestHandler> customCommandHandlers() {
            return DevSupportManagerBase.this.customPackagerCommandHandlers;
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setPackagerLocationCustomizer(DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer) {
        Intrinsics.checkNotNullParameter(packagerLocationCustomizer, "packagerLocationCustomizer");
        this.packagerLocationCustomizer = packagerLocationCustomizer;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public SurfaceDelegate createSurfaceDelegate(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        SurfaceDelegateFactory surfaceDelegateFactory = this.surfaceDelegateFactory;
        if (surfaceDelegateFactory != null) {
            return surfaceDelegateFactory.createSurfaceDelegate(moduleName);
        }
        return null;
    }

    private final void compatRegisterReceiver(Context context, BroadcastReceiver receiver, IntentFilter intentFilter, boolean exported) {
        if (Build.VERSION.SDK_INT >= 34 && context.getApplicationInfo().targetSdkVersion >= 34) {
            context.registerReceiver(receiver, intentFilter, exported ? 2 : 4);
        } else {
            context.registerReceiver(receiver, intentFilter);
        }
    }

    public final ColorStateList safeGetDefaultTextColor(Context context) {
        ColorStateList defaultTextColor = DefaultStyleValuesUtil.getDefaultTextColor(context);
        if (defaultTextColor != null) {
            return defaultTextColor;
        }
        if (UiModeUtils.isDarkMode(context)) {
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(-1);
            Intrinsics.checkNotNullExpressionValue(colorStateListValueOf, "valueOf(...)");
            return colorStateListValueOf;
        }
        ColorStateList colorStateListValueOf2 = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        Intrinsics.checkNotNullExpressionValue(colorStateListValueOf2, "valueOf(...)");
        return colorStateListValueOf2;
    }

    public final ColorStateList safeGetTextColorSecondary(Context context) {
        ColorStateList textColorSecondary = DefaultStyleValuesUtil.getTextColorSecondary(context);
        if (textColorSecondary != null) {
            return textColorSecondary;
        }
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(-7829368);
        Intrinsics.checkNotNullExpressionValue(colorStateListValueOf, "valueOf(...)");
        return colorStateListValueOf;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void openDebugger(String panel) {
        this.devServerHelper.openDebugger(this.currentReactContext, this.applicationContext.getString(R.string.catalyst_open_debugger_error), panel);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showPausedInDebuggerOverlay(String message, DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(pausedInDebuggerOverlayCommandListener, "listener");
        PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager = this.pausedInDebuggerOverlayManager;
        if (pausedInDebuggerOverlayManager != null) {
            pausedInDebuggerOverlayManager.showPausedInDebuggerOverlay(message, pausedInDebuggerOverlayCommandListener);
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hidePausedInDebuggerOverlay() {
        PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager = this.pausedInDebuggerOverlayManager;
        if (pausedInDebuggerOverlayManager != null) {
            pausedInDebuggerOverlayManager.hidePausedInDebuggerOverlay();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setAdditionalOptionForPackager(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.devSettings.getPackagerConnectionSettings().setAdditionalOptionForPackager(name, value);
    }

    public final void setTracingStateProvider$ReactAndroid_release(TracingStateProvider provider) {
        this.tracingStateProvider = provider;
    }

    /* JADX INFO: compiled from: DevSupportManagerBase.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/react/devsupport/DevSupportManagerBase$Companion;", "", "<init>", "()V", "JAVA_ERROR_COOKIE", "", "JSEXCEPTION_ERROR_COOKIE", "RELOAD_APP_ACTION_SUFFIX", "", "EXOPACKAGE_LOCATION_FORMAT", "getReloadAppAction", "context", "Landroid/content/Context;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getReloadAppAction(Context context) {
            return context.getPackageName() + DevSupportManagerBase.RELOAD_APP_ACTION_SUFFIX;
        }
    }
}
