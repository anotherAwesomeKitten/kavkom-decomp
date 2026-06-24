package com.facebook.react.uimanager;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.google.firebase.messaging.Constants;
import io.sentry.SentryReplayOptions;
import io.sentry.protocol.MetricSummary;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 42\u00020\u0001:\u00041234B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\"\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0018\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0014J\u0016\u0010&\u001a\u00020\u00162\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(H\u0014J\u0018\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0019H\u0014J\"\u0010,\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\b\u0010-\u001a\u0004\u0018\u00010 H\u0014J\u0012\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\u0012\u00100\u001a\u0004\u0018\u00010/2\u0006\u0010\u0017\u001a\u00020\u0003H\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fR.\u0010\u0010\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate;", "Landroidx/customview/widget/ExploreByTouchHelper;", "hostView", "Landroid/view/View;", "originalFocus", "", "originalImportantForAccessibility", "", "<init>", "(Landroid/view/View;ZI)V", "getHostView", "()Landroid/view/View;", "accessibilityEventHandler", "Landroid/os/Handler;", "getAccessibilityEventHandler$annotations", "()V", "accessibilityActionsMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", ViewProps.ACCESSIBILITY_LABELLED_BY, "onInitializeAccessibilityNodeInfo", "", "host", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "onInitializeAccessibilityEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/accessibility/AccessibilityEvent;", "performAccessibilityAction", "action", "args", "Landroid/os/Bundle;", "scheduleAccessibilityEventSender", "getVirtualViewAt", "x", "", "y", "getVisibleVirtualViews", "virtualViewIds", "", "onPopulateNodeForVirtualView", "virtualViewId", "node", "onPerformActionForVirtualView", "arguments", "getAccessibilityNodeProvider", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "superGetAccessibilityNodeProvider", "Role", "AccessibilityActionEvent", "AccessibilityRole", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ReactAccessibilityDelegate extends ExploreByTouchHelper {
    private static final int SEND_EVENT = 1;
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_DISABLED = "disabled";
    private static final String STATE_SELECTED = "selected";
    private static final String TAG = "ReactAccessibilityDelegate";
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    public static final String TOP_ACCESSIBILITY_ACTION_EVENT = "topAccessibilityAction";
    private static final String delimiter = ", ";
    private static final int delimiterLength = 2;
    private final HashMap<Integer, String> accessibilityActionsMap;
    private final Handler accessibilityEventHandler;
    private View accessibilityLabelledBy;
    private final View hostView;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<String, Integer> actionIdMap = MapsKt.mapOf(TuplesKt.to("activate", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId())), TuplesKt.to("longpress", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK.getId())), TuplesKt.to("increment", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId())), TuplesKt.to("decrement", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId())), TuplesKt.to("expand", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND.getId())), TuplesKt.to(StackTraceHelper.COLLAPSE_KEY, Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE.getId())));
    private static int customActionCounter = 1056964608;
    private static final Map<String, Integer> customActionIdMap = new HashMap();

    @JvmStatic
    public static final AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        return INSTANCE.createNodeInfoFromView(view);
    }

    private static /* synthetic */ void getAccessibilityEventHandler$annotations() {
    }

    @JvmStatic
    public static final CharSequence getTalkbackDescription(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return INSTANCE.getTalkbackDescription(view, accessibilityNodeInfoCompat);
    }

    @JvmStatic
    public static final boolean hasNonActionableSpeakingDescendants(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        return INSTANCE.hasNonActionableSpeakingDescendants(accessibilityNodeInfoCompat, view);
    }

    @JvmStatic
    public static final boolean hasText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return INSTANCE.hasText(accessibilityNodeInfoCompat);
    }

    @JvmStatic
    public static final boolean hasValidRangeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return INSTANCE.hasValidRangeInfo(accessibilityNodeInfoCompat);
    }

    @JvmStatic
    public static final boolean isAccessibilityFocusable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        return INSTANCE.isAccessibilityFocusable(accessibilityNodeInfoCompat, view);
    }

    @JvmStatic
    public static final boolean isActionableForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return INSTANCE.isActionableForAccessibility(accessibilityNodeInfoCompat);
    }

    @JvmStatic
    public static final boolean isSpeakingNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        return INSTANCE.isSpeakingNode(accessibilityNodeInfoCompat, view);
    }

    @JvmStatic
    public static final void resetDelegate(View view, boolean z, int i) {
        INSTANCE.resetDelegate(view, z, i);
    }

    @JvmStatic
    public static final void setDelegate(View view, boolean z, int i) {
        INSTANCE.setDelegate(view, z, i);
    }

    @JvmStatic
    public static final void setRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityRole accessibilityRole, Context context) {
        INSTANCE.setRole(accessibilityNodeInfoCompat, accessibilityRole, context);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        Intrinsics.checkNotNullParameter(host, "host");
        return null;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected int getVirtualViewAt(float x, float y) {
        return Integer.MIN_VALUE;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected void getVisibleVirtualViews(List<Integer> virtualViewIds) {
        Intrinsics.checkNotNullParameter(virtualViewIds, "virtualViewIds");
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
        return false;
    }

    protected final View getHostView() {
        return this.hostView;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactAccessibilityDelegate(View hostView, boolean z, int i) {
        super(hostView);
        Intrinsics.checkNotNullParameter(hostView, "hostView");
        this.hostView = hostView;
        this.accessibilityEventHandler = new Handler() { // from class: com.facebook.react.uimanager.ReactAccessibilityDelegate$accessibilityEventHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                View view = (View) msg.obj;
                if (view != null) {
                    view.sendAccessibilityEvent(4);
                }
            }
        };
        this.accessibilityActionsMap = new HashMap<>();
        hostView.setFocusable(z);
        hostView.setImportantForAccessibility(i);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
        int iIntValue;
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(info, "info");
        super.onInitializeAccessibilityNodeInfo(host, info);
        if (host.getTag(R.id.accessibility_state_expanded) != null) {
            Object tag = host.getTag(R.id.accessibility_state_expanded);
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Boolean");
            info.addAction(((Boolean) tag).booleanValue() ? 524288 : 262144);
        }
        AccessibilityRole accessibilityRoleFromViewTag = AccessibilityRole.INSTANCE.fromViewTag(host);
        String str = (String) host.getTag(R.id.accessibility_hint);
        if (accessibilityRoleFromViewTag != null) {
            Companion companion = INSTANCE;
            Context context = host.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            companion.setRole(info, accessibilityRoleFromViewTag, context);
        }
        if (str != null) {
            info.setTooltipText(str);
        }
        Object tag2 = host.getTag(R.id.labelled_by);
        if (tag2 != null) {
            View rootView = host.getRootView();
            Intrinsics.checkNotNullExpressionValue(rootView, "getRootView(...)");
            View viewFindView = ReactFindViewUtil.findView(rootView, (String) tag2);
            this.accessibilityLabelledBy = viewFindView;
            if (viewFindView != null) {
                info.setLabeledBy(viewFindView);
            }
        }
        ReadableMap readableMap = (ReadableMap) host.getTag(R.id.accessibility_state);
        if (readableMap != null) {
            INSTANCE.setState(info, readableMap);
        }
        ReadableArray readableArray = (ReadableArray) host.getTag(R.id.accessibility_actions);
        ReadableMap readableMap2 = (ReadableMap) host.getTag(R.id.accessibility_collection_item);
        if (readableMap2 != null) {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(readableMap2.getInt("rowIndex"), readableMap2.getInt("rowSpan"), readableMap2.getInt("columnIndex"), readableMap2.getInt("columnSpan"), readableMap2.getBoolean("heading")));
        }
        if (readableArray != null) {
            int size = readableArray.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = readableArray.getMap(i);
                if (map == null || !map.hasKey("name")) {
                    throw new IllegalArgumentException("Unknown accessibility action.".toString());
                }
                String string = map.getString("name");
                String str2 = map.hasKey(Constants.ScionAnalytics.PARAM_LABEL) ? (String) Assertions.assertNotNull(map.getString(Constants.ScionAnalytics.PARAM_LABEL)) : "";
                Integer num = actionIdMap.get(string);
                if (num != null) {
                    iIntValue = num.intValue();
                } else {
                    Map<String, Integer> map2 = customActionIdMap;
                    Integer numValueOf = map2.get(string);
                    if (numValueOf == null) {
                        int i2 = customActionCounter;
                        customActionCounter = i2 + 1;
                        numValueOf = Integer.valueOf(i2);
                        map2.put(string, numValueOf);
                    }
                    iIntValue = numValueOf.intValue();
                }
                this.accessibilityActionsMap.put(Integer.valueOf(iIntValue), string);
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(iIntValue, str2));
            }
        }
        ReadableMap readableMap3 = (ReadableMap) host.getTag(R.id.accessibility_value);
        if (readableMap3 != null && readableMap3.hasKey(MetricSummary.JsonKeys.MIN) && readableMap3.hasKey("now") && readableMap3.hasKey(MetricSummary.JsonKeys.MAX)) {
            Dynamic dynamic = readableMap3.getDynamic(MetricSummary.JsonKeys.MIN);
            Dynamic dynamic2 = readableMap3.getDynamic("now");
            Dynamic dynamic3 = readableMap3.getDynamic(MetricSummary.JsonKeys.MAX);
            if (dynamic.getType() == ReadableType.Number && dynamic2.getType() == ReadableType.Number && dynamic3.getType() == ReadableType.Number) {
                int iAsInt = dynamic.asInt();
                int iAsInt2 = dynamic2.asInt();
                int iAsInt3 = dynamic3.asInt();
                if (iAsInt3 > iAsInt && iAsInt2 >= iAsInt && iAsInt3 >= iAsInt2) {
                    info.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(0, iAsInt, iAsInt3, iAsInt2));
                }
            }
        }
        String str3 = (String) host.getTag(R.id.react_test_id);
        if (str3 != null) {
            info.setViewIdResourceName(str3);
        }
        CharSequence contentDescription = info.getContentDescription();
        boolean z = contentDescription == null || contentDescription.length() == 0;
        CharSequence text = info.getText();
        boolean z2 = z && (text == null || text.length() == 0);
        boolean z3 = (readableArray == null && readableMap == null && tag2 == null && accessibilityRoleFromViewTag == null) ? false : true;
        if (z2 && z3) {
            info.setContentDescription(INSTANCE.getTalkbackDescription(host, info));
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(event, "event");
        super.onInitializeAccessibilityEvent(host, event);
        ReadableMap readableMap = (ReadableMap) host.getTag(R.id.accessibility_value);
        if (readableMap != null && readableMap.hasKey(MetricSummary.JsonKeys.MIN) && readableMap.hasKey("now") && readableMap.hasKey(MetricSummary.JsonKeys.MAX)) {
            Dynamic dynamic = readableMap.getDynamic(MetricSummary.JsonKeys.MIN);
            Dynamic dynamic2 = readableMap.getDynamic("now");
            Dynamic dynamic3 = readableMap.getDynamic(MetricSummary.JsonKeys.MAX);
            if (dynamic.getType() == ReadableType.Number && dynamic2.getType() == ReadableType.Number && dynamic3.getType() == ReadableType.Number) {
                int iAsInt = dynamic.asInt();
                int iAsInt2 = dynamic2.asInt();
                int iAsInt3 = dynamic3.asInt();
                if (iAsInt3 <= iAsInt || iAsInt2 < iAsInt || iAsInt3 < iAsInt2) {
                    return;
                }
                event.setItemCount(iAsInt3 - iAsInt);
                event.setCurrentItemIndex(iAsInt2);
            }
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        Intrinsics.checkNotNullParameter(host, "host");
        if (action == 524288) {
            host.setTag(R.id.accessibility_state_expanded, false);
        }
        if (action == 262144) {
            host.setTag(R.id.accessibility_state_expanded, true);
        }
        if (this.accessibilityActionsMap.containsKey(Integer.valueOf(action))) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("actionName", this.accessibilityActionsMap.get(Integer.valueOf(action)));
            Context context = host.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            ReactContext reactContext = (ReactContext) context;
            if (reactContext.hasActiveReactInstance()) {
                int id = host.getId();
                int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
                UIManager uIManager = UIManagerHelper.getUIManager(reactContext, ViewUtil.getUIManagerType(id));
                if (uIManager != null) {
                    uIManager.getEventDispatcher().dispatchEvent(new AccessibilityActionEvent(writableMapCreateMap, surfaceId, id));
                }
            } else {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot get RCTEventEmitter, no CatalystInstance"));
            }
            Object tag = host.getTag(R.id.accessibility_role);
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole");
            AccessibilityRole accessibilityRole = (AccessibilityRole) tag;
            ReadableMap readableMap = (ReadableMap) host.getTag(R.id.accessibility_value);
            if (accessibilityRole != AccessibilityRole.ADJUSTABLE || (action != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId() && action != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId())) {
                return true;
            }
            if (readableMap != null && !readableMap.hasKey(ReactTextInputShadowNode.PROP_TEXT)) {
                scheduleAccessibilityEventSender(host);
            }
            return super.performAccessibilityAction(host, action, args);
        }
        return super.performAccessibilityAction(host, action, args);
    }

    private final void scheduleAccessibilityEventSender(View host) {
        if (this.accessibilityEventHandler.hasMessages(1, host)) {
            this.accessibilityEventHandler.removeMessages(1, host);
        }
        Message messageObtainMessage = this.accessibilityEventHandler.obtainMessage(1, host);
        Intrinsics.checkNotNullExpressionValue(messageObtainMessage, "obtainMessage(...)");
        this.accessibilityEventHandler.sendMessageDelayed(messageObtainMessage, 200L);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected void onPopulateNodeForVirtualView(int virtualViewId, AccessibilityNodeInfoCompat node) {
        Intrinsics.checkNotNullParameter(node, "node");
        node.setContentDescription("");
        node.setBoundsInParent(new Rect(0, 0, 1, 1));
    }

    protected final AccessibilityNodeProviderCompat superGetAccessibilityNodeProvider(View host) {
        Intrinsics.checkNotNullParameter(host, "host");
        return super.getAccessibilityNodeProvider(host);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\bE\b\u0086\u0081\u0002\u0018\u0000 E2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001EB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bD¨\u0006F"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", "", "<init>", "(Ljava/lang/String;I)V", "ALERT", "ALERTDIALOG", "APPLICATION", "ARTICLE", "BANNER", "BUTTON", "CELL", "CHECKBOX", "COLUMNHEADER", "COMBOBOX", "COMPLEMENTARY", "CONTENTINFO", "DEFINITION", "DIALOG", "DIRECTORY", "DOCUMENT", "FEED", "FIGURE", "FORM", "GRID", "GROUP", "HEADING", "IMG", "LINK", "LIST", "LISTITEM", "LOG", "MAIN", "MARQUEE", "MATH", "MENU", "MENUBAR", "MENUITEM", "METER", "NAVIGATION", "NONE", "NOTE", "OPTION", "PRESENTATION", "PROGRESSBAR", "RADIO", "RADIOGROUP", "REGION", "ROW", "ROWGROUP", "ROWHEADER", "SCROLLBAR", "SEARCHBOX", "SEPARATOR", "SLIDER", "SPINBUTTON", "STATUS", "SUMMARY", "SWITCH", "TAB", "TABLE", "TABLIST", "TABPANEL", "TERM", "TIMER", "TOOLBAR", "TOOLTIP", "TREE", "TREEGRID", "TREEITEM", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Role {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Role[] $VALUES;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        public static final Role ALERT = new Role("ALERT", 0);
        public static final Role ALERTDIALOG = new Role("ALERTDIALOG", 1);
        public static final Role APPLICATION = new Role("APPLICATION", 2);
        public static final Role ARTICLE = new Role("ARTICLE", 3);
        public static final Role BANNER = new Role("BANNER", 4);
        public static final Role BUTTON = new Role("BUTTON", 5);
        public static final Role CELL = new Role("CELL", 6);
        public static final Role CHECKBOX = new Role("CHECKBOX", 7);
        public static final Role COLUMNHEADER = new Role("COLUMNHEADER", 8);
        public static final Role COMBOBOX = new Role("COMBOBOX", 9);
        public static final Role COMPLEMENTARY = new Role("COMPLEMENTARY", 10);
        public static final Role CONTENTINFO = new Role("CONTENTINFO", 11);
        public static final Role DEFINITION = new Role("DEFINITION", 12);
        public static final Role DIALOG = new Role("DIALOG", 13);
        public static final Role DIRECTORY = new Role("DIRECTORY", 14);
        public static final Role DOCUMENT = new Role("DOCUMENT", 15);
        public static final Role FEED = new Role("FEED", 16);
        public static final Role FIGURE = new Role("FIGURE", 17);
        public static final Role FORM = new Role("FORM", 18);
        public static final Role GRID = new Role("GRID", 19);
        public static final Role GROUP = new Role("GROUP", 20);
        public static final Role HEADING = new Role("HEADING", 21);
        public static final Role IMG = new Role("IMG", 22);
        public static final Role LINK = new Role("LINK", 23);
        public static final Role LIST = new Role("LIST", 24);
        public static final Role LISTITEM = new Role("LISTITEM", 25);
        public static final Role LOG = new Role("LOG", 26);
        public static final Role MAIN = new Role("MAIN", 27);
        public static final Role MARQUEE = new Role("MARQUEE", 28);
        public static final Role MATH = new Role("MATH", 29);
        public static final Role MENU = new Role("MENU", 30);
        public static final Role MENUBAR = new Role("MENUBAR", 31);
        public static final Role MENUITEM = new Role("MENUITEM", 32);
        public static final Role METER = new Role("METER", 33);
        public static final Role NAVIGATION = new Role("NAVIGATION", 34);
        public static final Role NONE = new Role("NONE", 35);
        public static final Role NOTE = new Role("NOTE", 36);
        public static final Role OPTION = new Role("OPTION", 37);
        public static final Role PRESENTATION = new Role("PRESENTATION", 38);
        public static final Role PROGRESSBAR = new Role("PROGRESSBAR", 39);
        public static final Role RADIO = new Role("RADIO", 40);
        public static final Role RADIOGROUP = new Role("RADIOGROUP", 41);
        public static final Role REGION = new Role("REGION", 42);
        public static final Role ROW = new Role("ROW", 43);
        public static final Role ROWGROUP = new Role("ROWGROUP", 44);
        public static final Role ROWHEADER = new Role("ROWHEADER", 45);
        public static final Role SCROLLBAR = new Role("SCROLLBAR", 46);
        public static final Role SEARCHBOX = new Role("SEARCHBOX", 47);
        public static final Role SEPARATOR = new Role("SEPARATOR", 48);
        public static final Role SLIDER = new Role("SLIDER", 49);
        public static final Role SPINBUTTON = new Role("SPINBUTTON", 50);
        public static final Role STATUS = new Role("STATUS", 51);
        public static final Role SUMMARY = new Role("SUMMARY", 52);
        public static final Role SWITCH = new Role("SWITCH", 53);
        public static final Role TAB = new Role("TAB", 54);
        public static final Role TABLE = new Role("TABLE", 55);
        public static final Role TABLIST = new Role("TABLIST", 56);
        public static final Role TABPANEL = new Role("TABPANEL", 57);
        public static final Role TERM = new Role("TERM", 58);
        public static final Role TIMER = new Role("TIMER", 59);
        public static final Role TOOLBAR = new Role("TOOLBAR", 60);
        public static final Role TOOLTIP = new Role("TOOLTIP", 61);
        public static final Role TREE = new Role("TREE", 62);
        public static final Role TREEGRID = new Role("TREEGRID", 63);
        public static final Role TREEITEM = new Role("TREEITEM", 64);

        private static final /* synthetic */ Role[] $values() {
            return new Role[]{ALERT, ALERTDIALOG, APPLICATION, ARTICLE, BANNER, BUTTON, CELL, CHECKBOX, COLUMNHEADER, COMBOBOX, COMPLEMENTARY, CONTENTINFO, DEFINITION, DIALOG, DIRECTORY, DOCUMENT, FEED, FIGURE, FORM, GRID, GROUP, HEADING, IMG, LINK, LIST, LISTITEM, LOG, MAIN, MARQUEE, MATH, MENU, MENUBAR, MENUITEM, METER, NAVIGATION, NONE, NOTE, OPTION, PRESENTATION, PROGRESSBAR, RADIO, RADIOGROUP, REGION, ROW, ROWGROUP, ROWHEADER, SCROLLBAR, SEARCHBOX, SEPARATOR, SLIDER, SPINBUTTON, STATUS, SUMMARY, SWITCH, TAB, TABLE, TABLIST, TABPANEL, TERM, TIMER, TOOLBAR, TOOLTIP, TREE, TREEGRID, TREEITEM};
        }

        @JvmStatic
        public static final Role fromValue(String str) {
            return INSTANCE.fromValue(str);
        }

        public static EnumEntries<Role> getEntries() {
            return $ENTRIES;
        }

        private Role(String str, int i) {
        }

        static {
            Role[] roleArr$values = $values();
            $VALUES = roleArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(roleArr$values);
            INSTANCE = new Companion(null);
        }

        /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role$Companion;", "", "<init>", "()V", "fromValue", "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", "value", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Role fromValue(String value) {
                for (Role role : Role.getEntries()) {
                    if (StringsKt.equals(role.name(), value, true)) {
                        return role;
                    }
                }
                return null;
            }
        }

        public static Role valueOf(String str) {
            return (Role) Enum.valueOf(Role.class, str);
        }

        public static Role[] values() {
            return (Role[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityActionEvent;", "Lcom/facebook/react/uimanager/events/Event;", "accessibilityEventData", "Lcom/facebook/react/bridge/WritableMap;", "surfaceId", "", "viewId", "<init>", "(Lcom/facebook/react/bridge/WritableMap;II)V", "getEventName", "", "getEventData", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class AccessibilityActionEvent extends Event<AccessibilityActionEvent> {
        private final WritableMap accessibilityEventData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AccessibilityActionEvent(WritableMap accessibilityEventData, int i, int i2) {
            super(i, i2);
            Intrinsics.checkNotNullParameter(accessibilityEventData, "accessibilityEventData");
            this.accessibilityEventData = accessibilityEventData;
        }

        @Override // com.facebook.react.uimanager.events.Event
        public String getEventName() {
            return ReactAccessibilityDelegate.TOP_ACCESSIBILITY_ACTION_EVENT;
        }

        @Override // com.facebook.react.uimanager.events.Event
        /* JADX INFO: renamed from: getEventData, reason: from getter */
        public WritableMap getAccessibilityEventData() {
            return this.accessibilityEventData;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b+\b\u0086\u0081\u0002\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001+B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*¨\u0006,"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "BUTTON", "DROPDOWNLIST", "TOGGLEBUTTON", "LINK", "SEARCH", "IMAGE", "IMAGEBUTTON", "KEYBOARDKEY", "TEXT", "ADJUSTABLE", "SUMMARY", "HEADER", "ALERT", "CHECKBOX", "COMBOBOX", "MENU", "MENUBAR", "MENUITEM", "PROGRESSBAR", "RADIO", "RADIOGROUP", "SCROLLBAR", "SPINBUTTON", "SWITCH", "TAB", "TABLIST", "TIMER", "LIST", "GRID", "PAGER", "SCROLLVIEW", "HORIZONTALSCROLLVIEW", "VIEWGROUP", "WEBVIEW", "DRAWERLAYOUT", "SLIDINGDRAWER", "ICONMENU", "TOOLBAR", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AccessibilityRole {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ AccessibilityRole[] $VALUES;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        public static final AccessibilityRole NONE = new AccessibilityRole("NONE", 0);
        public static final AccessibilityRole BUTTON = new AccessibilityRole("BUTTON", 1);
        public static final AccessibilityRole DROPDOWNLIST = new AccessibilityRole("DROPDOWNLIST", 2);
        public static final AccessibilityRole TOGGLEBUTTON = new AccessibilityRole("TOGGLEBUTTON", 3);
        public static final AccessibilityRole LINK = new AccessibilityRole("LINK", 4);
        public static final AccessibilityRole SEARCH = new AccessibilityRole("SEARCH", 5);
        public static final AccessibilityRole IMAGE = new AccessibilityRole("IMAGE", 6);
        public static final AccessibilityRole IMAGEBUTTON = new AccessibilityRole("IMAGEBUTTON", 7);
        public static final AccessibilityRole KEYBOARDKEY = new AccessibilityRole("KEYBOARDKEY", 8);
        public static final AccessibilityRole TEXT = new AccessibilityRole("TEXT", 9);
        public static final AccessibilityRole ADJUSTABLE = new AccessibilityRole("ADJUSTABLE", 10);
        public static final AccessibilityRole SUMMARY = new AccessibilityRole("SUMMARY", 11);
        public static final AccessibilityRole HEADER = new AccessibilityRole("HEADER", 12);
        public static final AccessibilityRole ALERT = new AccessibilityRole("ALERT", 13);
        public static final AccessibilityRole CHECKBOX = new AccessibilityRole("CHECKBOX", 14);
        public static final AccessibilityRole COMBOBOX = new AccessibilityRole("COMBOBOX", 15);
        public static final AccessibilityRole MENU = new AccessibilityRole("MENU", 16);
        public static final AccessibilityRole MENUBAR = new AccessibilityRole("MENUBAR", 17);
        public static final AccessibilityRole MENUITEM = new AccessibilityRole("MENUITEM", 18);
        public static final AccessibilityRole PROGRESSBAR = new AccessibilityRole("PROGRESSBAR", 19);
        public static final AccessibilityRole RADIO = new AccessibilityRole("RADIO", 20);
        public static final AccessibilityRole RADIOGROUP = new AccessibilityRole("RADIOGROUP", 21);
        public static final AccessibilityRole SCROLLBAR = new AccessibilityRole("SCROLLBAR", 22);
        public static final AccessibilityRole SPINBUTTON = new AccessibilityRole("SPINBUTTON", 23);
        public static final AccessibilityRole SWITCH = new AccessibilityRole("SWITCH", 24);
        public static final AccessibilityRole TAB = new AccessibilityRole("TAB", 25);
        public static final AccessibilityRole TABLIST = new AccessibilityRole("TABLIST", 26);
        public static final AccessibilityRole TIMER = new AccessibilityRole("TIMER", 27);
        public static final AccessibilityRole LIST = new AccessibilityRole("LIST", 28);
        public static final AccessibilityRole GRID = new AccessibilityRole("GRID", 29);
        public static final AccessibilityRole PAGER = new AccessibilityRole("PAGER", 30);
        public static final AccessibilityRole SCROLLVIEW = new AccessibilityRole("SCROLLVIEW", 31);
        public static final AccessibilityRole HORIZONTALSCROLLVIEW = new AccessibilityRole("HORIZONTALSCROLLVIEW", 32);
        public static final AccessibilityRole VIEWGROUP = new AccessibilityRole("VIEWGROUP", 33);
        public static final AccessibilityRole WEBVIEW = new AccessibilityRole("WEBVIEW", 34);
        public static final AccessibilityRole DRAWERLAYOUT = new AccessibilityRole("DRAWERLAYOUT", 35);
        public static final AccessibilityRole SLIDINGDRAWER = new AccessibilityRole("SLIDINGDRAWER", 36);
        public static final AccessibilityRole ICONMENU = new AccessibilityRole("ICONMENU", 37);
        public static final AccessibilityRole TOOLBAR = new AccessibilityRole("TOOLBAR", 38);

        private static final /* synthetic */ AccessibilityRole[] $values() {
            return new AccessibilityRole[]{NONE, BUTTON, DROPDOWNLIST, TOGGLEBUTTON, LINK, SEARCH, IMAGE, IMAGEBUTTON, KEYBOARDKEY, TEXT, ADJUSTABLE, SUMMARY, HEADER, ALERT, CHECKBOX, COMBOBOX, MENU, MENUBAR, MENUITEM, PROGRESSBAR, RADIO, RADIOGROUP, SCROLLBAR, SPINBUTTON, SWITCH, TAB, TABLIST, TIMER, LIST, GRID, PAGER, SCROLLVIEW, HORIZONTALSCROLLVIEW, VIEWGROUP, WEBVIEW, DRAWERLAYOUT, SLIDINGDRAWER, ICONMENU, TOOLBAR};
        }

        @JvmStatic
        public static final AccessibilityRole fromRole(Role role) {
            return INSTANCE.fromRole(role);
        }

        @JvmStatic
        public static final AccessibilityRole fromValue(String str) {
            return INSTANCE.fromValue(str);
        }

        @JvmStatic
        public static final AccessibilityRole fromViewTag(View view) {
            return INSTANCE.fromViewTag(view);
        }

        public static EnumEntries<AccessibilityRole> getEntries() {
            return $ENTRIES;
        }

        @JvmStatic
        public static final String getValue(AccessibilityRole accessibilityRole) {
            return INSTANCE.getValue(accessibilityRole);
        }

        private AccessibilityRole(String str, int i) {
        }

        static {
            AccessibilityRole[] accessibilityRoleArr$values = $values();
            $VALUES = accessibilityRoleArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(accessibilityRoleArr$values);
            INSTANCE = new Companion(null);
        }

        /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u000bH\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole$Companion;", "", "<init>", "()V", "getValue", "", ViewProps.ROLE, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "fromValue", "value", "fromRole", "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", "fromViewTag", "view", "Landroid/view/View;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {

            /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
            @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;
                public static final /* synthetic */ int[] $EnumSwitchMapping$1;

                static {
                    int[] iArr = new int[AccessibilityRole.values().length];
                    try {
                        iArr[AccessibilityRole.BUTTON.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[AccessibilityRole.DROPDOWNLIST.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[AccessibilityRole.TOGGLEBUTTON.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[AccessibilityRole.SEARCH.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[AccessibilityRole.IMAGE.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    try {
                        iArr[AccessibilityRole.IMAGEBUTTON.ordinal()] = 6;
                    } catch (NoSuchFieldError unused6) {
                    }
                    try {
                        iArr[AccessibilityRole.KEYBOARDKEY.ordinal()] = 7;
                    } catch (NoSuchFieldError unused7) {
                    }
                    try {
                        iArr[AccessibilityRole.TEXT.ordinal()] = 8;
                    } catch (NoSuchFieldError unused8) {
                    }
                    try {
                        iArr[AccessibilityRole.ADJUSTABLE.ordinal()] = 9;
                    } catch (NoSuchFieldError unused9) {
                    }
                    try {
                        iArr[AccessibilityRole.CHECKBOX.ordinal()] = 10;
                    } catch (NoSuchFieldError unused10) {
                    }
                    try {
                        iArr[AccessibilityRole.RADIO.ordinal()] = 11;
                    } catch (NoSuchFieldError unused11) {
                    }
                    try {
                        iArr[AccessibilityRole.SPINBUTTON.ordinal()] = 12;
                    } catch (NoSuchFieldError unused12) {
                    }
                    try {
                        iArr[AccessibilityRole.SWITCH.ordinal()] = 13;
                    } catch (NoSuchFieldError unused13) {
                    }
                    try {
                        iArr[AccessibilityRole.LIST.ordinal()] = 14;
                    } catch (NoSuchFieldError unused14) {
                    }
                    try {
                        iArr[AccessibilityRole.GRID.ordinal()] = 15;
                    } catch (NoSuchFieldError unused15) {
                    }
                    try {
                        iArr[AccessibilityRole.SCROLLVIEW.ordinal()] = 16;
                    } catch (NoSuchFieldError unused16) {
                    }
                    try {
                        iArr[AccessibilityRole.HORIZONTALSCROLLVIEW.ordinal()] = 17;
                    } catch (NoSuchFieldError unused17) {
                    }
                    try {
                        iArr[AccessibilityRole.PAGER.ordinal()] = 18;
                    } catch (NoSuchFieldError unused18) {
                    }
                    try {
                        iArr[AccessibilityRole.DRAWERLAYOUT.ordinal()] = 19;
                    } catch (NoSuchFieldError unused19) {
                    }
                    try {
                        iArr[AccessibilityRole.SLIDINGDRAWER.ordinal()] = 20;
                    } catch (NoSuchFieldError unused20) {
                    }
                    try {
                        iArr[AccessibilityRole.ICONMENU.ordinal()] = 21;
                    } catch (NoSuchFieldError unused21) {
                    }
                    try {
                        iArr[AccessibilityRole.VIEWGROUP.ordinal()] = 22;
                    } catch (NoSuchFieldError unused22) {
                    }
                    try {
                        iArr[AccessibilityRole.WEBVIEW.ordinal()] = 23;
                    } catch (NoSuchFieldError unused23) {
                    }
                    try {
                        iArr[AccessibilityRole.NONE.ordinal()] = 24;
                    } catch (NoSuchFieldError unused24) {
                    }
                    try {
                        iArr[AccessibilityRole.LINK.ordinal()] = 25;
                    } catch (NoSuchFieldError unused25) {
                    }
                    try {
                        iArr[AccessibilityRole.SUMMARY.ordinal()] = 26;
                    } catch (NoSuchFieldError unused26) {
                    }
                    try {
                        iArr[AccessibilityRole.HEADER.ordinal()] = 27;
                    } catch (NoSuchFieldError unused27) {
                    }
                    try {
                        iArr[AccessibilityRole.ALERT.ordinal()] = 28;
                    } catch (NoSuchFieldError unused28) {
                    }
                    try {
                        iArr[AccessibilityRole.COMBOBOX.ordinal()] = 29;
                    } catch (NoSuchFieldError unused29) {
                    }
                    try {
                        iArr[AccessibilityRole.MENU.ordinal()] = 30;
                    } catch (NoSuchFieldError unused30) {
                    }
                    try {
                        iArr[AccessibilityRole.MENUBAR.ordinal()] = 31;
                    } catch (NoSuchFieldError unused31) {
                    }
                    try {
                        iArr[AccessibilityRole.MENUITEM.ordinal()] = 32;
                    } catch (NoSuchFieldError unused32) {
                    }
                    try {
                        iArr[AccessibilityRole.PROGRESSBAR.ordinal()] = 33;
                    } catch (NoSuchFieldError unused33) {
                    }
                    try {
                        iArr[AccessibilityRole.RADIOGROUP.ordinal()] = 34;
                    } catch (NoSuchFieldError unused34) {
                    }
                    try {
                        iArr[AccessibilityRole.SCROLLBAR.ordinal()] = 35;
                    } catch (NoSuchFieldError unused35) {
                    }
                    try {
                        iArr[AccessibilityRole.TAB.ordinal()] = 36;
                    } catch (NoSuchFieldError unused36) {
                    }
                    try {
                        iArr[AccessibilityRole.TABLIST.ordinal()] = 37;
                    } catch (NoSuchFieldError unused37) {
                    }
                    try {
                        iArr[AccessibilityRole.TIMER.ordinal()] = 38;
                    } catch (NoSuchFieldError unused38) {
                    }
                    try {
                        iArr[AccessibilityRole.TOOLBAR.ordinal()] = 39;
                    } catch (NoSuchFieldError unused39) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                    int[] iArr2 = new int[Role.values().length];
                    try {
                        iArr2[Role.ALERT.ordinal()] = 1;
                    } catch (NoSuchFieldError unused40) {
                    }
                    try {
                        iArr2[Role.BUTTON.ordinal()] = 2;
                    } catch (NoSuchFieldError unused41) {
                    }
                    try {
                        iArr2[Role.CHECKBOX.ordinal()] = 3;
                    } catch (NoSuchFieldError unused42) {
                    }
                    try {
                        iArr2[Role.COMBOBOX.ordinal()] = 4;
                    } catch (NoSuchFieldError unused43) {
                    }
                    try {
                        iArr2[Role.GRID.ordinal()] = 5;
                    } catch (NoSuchFieldError unused44) {
                    }
                    try {
                        iArr2[Role.HEADING.ordinal()] = 6;
                    } catch (NoSuchFieldError unused45) {
                    }
                    try {
                        iArr2[Role.IMG.ordinal()] = 7;
                    } catch (NoSuchFieldError unused46) {
                    }
                    try {
                        iArr2[Role.LINK.ordinal()] = 8;
                    } catch (NoSuchFieldError unused47) {
                    }
                    try {
                        iArr2[Role.LIST.ordinal()] = 9;
                    } catch (NoSuchFieldError unused48) {
                    }
                    try {
                        iArr2[Role.MENU.ordinal()] = 10;
                    } catch (NoSuchFieldError unused49) {
                    }
                    try {
                        iArr2[Role.MENUBAR.ordinal()] = 11;
                    } catch (NoSuchFieldError unused50) {
                    }
                    try {
                        iArr2[Role.MENUITEM.ordinal()] = 12;
                    } catch (NoSuchFieldError unused51) {
                    }
                    try {
                        iArr2[Role.NONE.ordinal()] = 13;
                    } catch (NoSuchFieldError unused52) {
                    }
                    try {
                        iArr2[Role.PROGRESSBAR.ordinal()] = 14;
                    } catch (NoSuchFieldError unused53) {
                    }
                    try {
                        iArr2[Role.RADIO.ordinal()] = 15;
                    } catch (NoSuchFieldError unused54) {
                    }
                    try {
                        iArr2[Role.RADIOGROUP.ordinal()] = 16;
                    } catch (NoSuchFieldError unused55) {
                    }
                    try {
                        iArr2[Role.SCROLLBAR.ordinal()] = 17;
                    } catch (NoSuchFieldError unused56) {
                    }
                    try {
                        iArr2[Role.SEARCHBOX.ordinal()] = 18;
                    } catch (NoSuchFieldError unused57) {
                    }
                    try {
                        iArr2[Role.SLIDER.ordinal()] = 19;
                    } catch (NoSuchFieldError unused58) {
                    }
                    try {
                        iArr2[Role.SPINBUTTON.ordinal()] = 20;
                    } catch (NoSuchFieldError unused59) {
                    }
                    try {
                        iArr2[Role.SUMMARY.ordinal()] = 21;
                    } catch (NoSuchFieldError unused60) {
                    }
                    try {
                        iArr2[Role.SWITCH.ordinal()] = 22;
                    } catch (NoSuchFieldError unused61) {
                    }
                    try {
                        iArr2[Role.TAB.ordinal()] = 23;
                    } catch (NoSuchFieldError unused62) {
                    }
                    try {
                        iArr2[Role.TABLIST.ordinal()] = 24;
                    } catch (NoSuchFieldError unused63) {
                    }
                    try {
                        iArr2[Role.TIMER.ordinal()] = 25;
                    } catch (NoSuchFieldError unused64) {
                    }
                    try {
                        iArr2[Role.TOOLBAR.ordinal()] = 26;
                    } catch (NoSuchFieldError unused65) {
                    }
                    $EnumSwitchMapping$1 = iArr2;
                }
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final String getValue(AccessibilityRole role) {
                Intrinsics.checkNotNullParameter(role, "role");
                switch (WhenMappings.$EnumSwitchMapping$0[role.ordinal()]) {
                    case 1:
                        return "android.widget.Button";
                    case 2:
                        return "android.widget.Spinner";
                    case 3:
                        return "android.widget.ToggleButton";
                    case 4:
                        return "android.widget.EditText";
                    case 5:
                        return SentryReplayOptions.IMAGE_VIEW_CLASS_NAME;
                    case 6:
                        return "android.widget.ImageButton";
                    case 7:
                        return "android.inputmethodservice.Keyboard$Key";
                    case 8:
                        return SentryReplayOptions.TEXT_VIEW_CLASS_NAME;
                    case 9:
                        return "android.widget.SeekBar";
                    case 10:
                        return "android.widget.CheckBox";
                    case 11:
                        return "android.widget.RadioButton";
                    case 12:
                        return "android.widget.SpinButton";
                    case 13:
                        return "android.widget.Switch";
                    case 14:
                        return "android.widget.AbsListView";
                    case 15:
                        return "android.widget.GridView";
                    case 16:
                        return "android.widget.ScrollView";
                    case 17:
                        return "android.widget.HorizontalScrollView";
                    case 18:
                        return "androidx.viewpager.widget.ViewPager";
                    case 19:
                        return "androidx.drawerlayout.widget.DrawerLayout";
                    case 20:
                        return "android.widget.SlidingDrawer";
                    case 21:
                        return "com.android.internal.view.menu.IconMenuView";
                    case 22:
                        return "android.view.ViewGroup";
                    case 23:
                        return SentryReplayOptions.WEB_VIEW_CLASS_NAME;
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                        return "android.view.View";
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }

            @JvmStatic
            public final AccessibilityRole fromValue(String value) {
                if (value == null) {
                    return AccessibilityRole.NONE;
                }
                for (AccessibilityRole accessibilityRole : AccessibilityRole.getEntries()) {
                    if (StringsKt.equals(accessibilityRole.name(), value, true)) {
                        return accessibilityRole;
                    }
                }
                throw new IllegalArgumentException("Invalid accessibility role value: " + value);
            }

            @JvmStatic
            public final AccessibilityRole fromRole(Role role) {
                Intrinsics.checkNotNullParameter(role, "role");
                switch (WhenMappings.$EnumSwitchMapping$1[role.ordinal()]) {
                    case 1:
                        return AccessibilityRole.ALERT;
                    case 2:
                        return AccessibilityRole.BUTTON;
                    case 3:
                        return AccessibilityRole.CHECKBOX;
                    case 4:
                        return AccessibilityRole.COMBOBOX;
                    case 5:
                        return AccessibilityRole.GRID;
                    case 6:
                        return AccessibilityRole.HEADER;
                    case 7:
                        return AccessibilityRole.IMAGE;
                    case 8:
                        return AccessibilityRole.LINK;
                    case 9:
                        return AccessibilityRole.LIST;
                    case 10:
                        return AccessibilityRole.MENU;
                    case 11:
                        return AccessibilityRole.MENUBAR;
                    case 12:
                        return AccessibilityRole.MENUITEM;
                    case 13:
                        return AccessibilityRole.NONE;
                    case 14:
                        return AccessibilityRole.PROGRESSBAR;
                    case 15:
                        return AccessibilityRole.RADIO;
                    case 16:
                        return AccessibilityRole.RADIOGROUP;
                    case 17:
                        return AccessibilityRole.SCROLLBAR;
                    case 18:
                        return AccessibilityRole.SEARCH;
                    case 19:
                        return AccessibilityRole.ADJUSTABLE;
                    case 20:
                        return AccessibilityRole.SPINBUTTON;
                    case 21:
                        return AccessibilityRole.SUMMARY;
                    case 22:
                        return AccessibilityRole.SWITCH;
                    case 23:
                        return AccessibilityRole.TAB;
                    case 24:
                        return AccessibilityRole.TABLIST;
                    case 25:
                        return AccessibilityRole.TIMER;
                    case 26:
                        return AccessibilityRole.TOOLBAR;
                    default:
                        return null;
                }
            }

            @JvmStatic
            public final AccessibilityRole fromViewTag(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                Role role = (Role) view.getTag(R.id.role);
                if (role != null) {
                    return fromRole(role);
                }
                return (AccessibilityRole) view.getTag(R.id.accessibility_role);
            }
        }

        public static AccessibilityRole valueOf(String str) {
            return (AccessibilityRole) Enum.valueOf(AccessibilityRole.class, str);
        }

        public static AccessibilityRole[] values() {
            return (AccessibilityRole[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0007J \u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0007J\u0018\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\"\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0007J\u001c\u0010'\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010)\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001eH\u0007J\u0012\u0010*\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001eH\u0002J\u001c\u0010+\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010,\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001eH\u0007J\u001c\u0010-\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010.\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u001eH\u0007J\u0014\u0010/\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u001c\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007J\u0014\u00102\u001a\u00020\u00052\n\u00103\u001a\u000604j\u0002`5H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Companion;", "", "<init>", "()V", "TOP_ACCESSIBILITY_ACTION_EVENT", "", "actionIdMap", "", "", "TAG", "customActionCounter", "customActionIdMap", "", "TIMEOUT_SEND_ACCESSIBILITY_EVENT", "SEND_EVENT", "delimiter", "delimiterLength", "STATE_DISABLED", "STATE_SELECTED", "STATE_CHECKED", "setDelegate", "", "view", "Landroid/view/View;", "originalFocus", "", "originalImportantForAccessibility", "resetDelegate", "setState", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", ViewProps.ACCESSIBILITY_STATE, "Lcom/facebook/react/bridge/ReadableMap;", "setRole", "nodeInfo", ViewProps.ROLE, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "context", "Landroid/content/Context;", "hasNonActionableSpeakingDescendants", "node", "hasValidRangeInfo", "hasStateDescription", "isSpeakingNode", "hasText", "isAccessibilityFocusable", "isActionableForAccessibility", "createNodeInfoFromView", "getTalkbackDescription", "", "removeFinalDelimiter", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: ReactAccessibilityDelegate.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AccessibilityRole.values().length];
                try {
                    iArr[AccessibilityRole.LINK.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AccessibilityRole.IMAGE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[AccessibilityRole.IMAGEBUTTON.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[AccessibilityRole.BUTTON.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[AccessibilityRole.TOGGLEBUTTON.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[AccessibilityRole.SUMMARY.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[AccessibilityRole.HEADER.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[AccessibilityRole.ALERT.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[AccessibilityRole.COMBOBOX.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[AccessibilityRole.MENU.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[AccessibilityRole.MENUBAR.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[AccessibilityRole.MENUITEM.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[AccessibilityRole.PROGRESSBAR.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr[AccessibilityRole.RADIOGROUP.ordinal()] = 14;
                } catch (NoSuchFieldError unused14) {
                }
                try {
                    iArr[AccessibilityRole.SCROLLBAR.ordinal()] = 15;
                } catch (NoSuchFieldError unused15) {
                }
                try {
                    iArr[AccessibilityRole.SPINBUTTON.ordinal()] = 16;
                } catch (NoSuchFieldError unused16) {
                }
                try {
                    iArr[AccessibilityRole.TAB.ordinal()] = 17;
                } catch (NoSuchFieldError unused17) {
                }
                try {
                    iArr[AccessibilityRole.TABLIST.ordinal()] = 18;
                } catch (NoSuchFieldError unused18) {
                }
                try {
                    iArr[AccessibilityRole.TIMER.ordinal()] = 19;
                } catch (NoSuchFieldError unused19) {
                }
                try {
                    iArr[AccessibilityRole.TOOLBAR.ordinal()] = 20;
                } catch (NoSuchFieldError unused20) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void setDelegate(View view, boolean originalFocus, int originalImportantForAccessibility) {
            Intrinsics.checkNotNullParameter(view, "view");
            if (ViewCompat.hasAccessibilityDelegate(view)) {
                return;
            }
            if (view.getTag(R.id.accessibility_role) == null && view.getTag(R.id.accessibility_state) == null && view.getTag(R.id.accessibility_actions) == null && view.getTag(R.id.react_test_id) == null && view.getTag(R.id.accessibility_collection_item) == null && view.getTag(R.id.accessibility_links) == null && view.getTag(R.id.role) == null) {
                return;
            }
            ViewCompat.setAccessibilityDelegate(view, new ReactAccessibilityDelegate(view, originalFocus, originalImportantForAccessibility));
        }

        @JvmStatic
        public final void resetDelegate(View view, boolean originalFocus, int originalImportantForAccessibility) {
            Intrinsics.checkNotNullParameter(view, "view");
            ViewCompat.setAccessibilityDelegate(view, new ReactAccessibilityDelegate(view, originalFocus, originalImportantForAccessibility));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setState(AccessibilityNodeInfoCompat info, ReadableMap accessibilityState) {
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = accessibilityState.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                Dynamic dynamic = accessibilityState.getDynamic(strNextKey);
                if (Intrinsics.areEqual(strNextKey, ReactAccessibilityDelegate.STATE_SELECTED) && dynamic.getType() == ReadableType.Boolean) {
                    info.setSelected(dynamic.asBoolean());
                } else if (Intrinsics.areEqual(strNextKey, ReactAccessibilityDelegate.STATE_DISABLED) && dynamic.getType() == ReadableType.Boolean) {
                    info.setEnabled(!dynamic.asBoolean());
                } else if (Intrinsics.areEqual(strNextKey, ReactAccessibilityDelegate.STATE_CHECKED) && dynamic.getType() == ReadableType.Boolean) {
                    boolean zAsBoolean = dynamic.asBoolean();
                    info.setCheckable(true);
                    info.setChecked(zAsBoolean);
                }
            }
        }

        @JvmStatic
        public final void setRole(AccessibilityNodeInfoCompat nodeInfo, AccessibilityRole role, Context context) {
            Intrinsics.checkNotNullParameter(nodeInfo, "nodeInfo");
            Intrinsics.checkNotNullParameter(context, "context");
            if (role == null) {
                role = AccessibilityRole.NONE;
            }
            nodeInfo.setClassName(AccessibilityRole.INSTANCE.getValue(role));
            switch (WhenMappings.$EnumSwitchMapping$0[role.ordinal()]) {
                case 1:
                    nodeInfo.setRoleDescription(context.getString(R.string.link_description));
                    break;
                case 2:
                    nodeInfo.setRoleDescription(context.getString(R.string.image_description));
                    break;
                case 3:
                    nodeInfo.setRoleDescription(context.getString(R.string.imagebutton_description));
                    nodeInfo.setClickable(true);
                    break;
                case 4:
                    nodeInfo.setClickable(true);
                    break;
                case 5:
                    nodeInfo.setClickable(true);
                    nodeInfo.setCheckable(true);
                    break;
                case 6:
                    nodeInfo.setRoleDescription(context.getString(R.string.summary_description));
                    break;
                case 7:
                    nodeInfo.setHeading(true);
                    break;
                case 8:
                    nodeInfo.setRoleDescription(context.getString(R.string.alert_description));
                    break;
                case 9:
                    nodeInfo.setRoleDescription(context.getString(R.string.combobox_description));
                    break;
                case 10:
                    nodeInfo.setRoleDescription(context.getString(R.string.menu_description));
                    break;
                case 11:
                    nodeInfo.setRoleDescription(context.getString(R.string.menubar_description));
                    break;
                case 12:
                    nodeInfo.setRoleDescription(context.getString(R.string.menuitem_description));
                    break;
                case 13:
                    nodeInfo.setRoleDescription(context.getString(R.string.progressbar_description));
                    break;
                case 14:
                    nodeInfo.setRoleDescription(context.getString(R.string.radiogroup_description));
                    break;
                case 15:
                    nodeInfo.setRoleDescription(context.getString(R.string.scrollbar_description));
                    break;
                case 16:
                    nodeInfo.setRoleDescription(context.getString(R.string.spinbutton_description));
                    break;
                case 17:
                    nodeInfo.setRoleDescription(context.getString(R.string.rn_tab_description));
                    break;
                case 18:
                    nodeInfo.setRoleDescription(context.getString(R.string.tablist_description));
                    break;
                case 19:
                    nodeInfo.setRoleDescription(context.getString(R.string.timer_description));
                    break;
                case 20:
                    nodeInfo.setRoleDescription(context.getString(R.string.toolbar_description));
                    break;
            }
        }

        @JvmStatic
        public final boolean hasNonActionableSpeakingDescendants(AccessibilityNodeInfoCompat node, View view) {
            if (node != null && view != null && (view instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt != null) {
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
                        ViewCompat.onInitializeAccessibilityNodeInfo(childAt, accessibilityNodeInfoCompatObtain);
                        if (accessibilityNodeInfoCompatObtain.isVisibleToUser() && !isAccessibilityFocusable(accessibilityNodeInfoCompatObtain, childAt) && isSpeakingNode(accessibilityNodeInfoCompatObtain, childAt)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        @JvmStatic
        public final boolean hasValidRangeInfo(AccessibilityNodeInfoCompat node) {
            AccessibilityNodeInfoCompat.RangeInfoCompat rangeInfo;
            if (node == null || (rangeInfo = node.getRangeInfo()) == null) {
                return false;
            }
            float max = rangeInfo.getMax();
            float min = rangeInfo.getMin();
            float current = rangeInfo.getCurrent();
            return max - min > 0.0f && current >= min && current <= max;
        }

        private final boolean hasStateDescription(AccessibilityNodeInfoCompat node) {
            if (node == null) {
                return false;
            }
            CharSequence stateDescription = node.getStateDescription();
            return !(stateDescription == null || stateDescription.length() == 0) || node.isCheckable() || hasValidRangeInfo(node);
        }

        @JvmStatic
        public final boolean isSpeakingNode(AccessibilityNodeInfoCompat node, View view) {
            int importantForAccessibility;
            if (node == null || view == null || (importantForAccessibility = ViewCompat.getImportantForAccessibility(view)) == 4 || (importantForAccessibility == 2 && node.getChildCount() <= 0)) {
                return false;
            }
            return hasText(node) || hasStateDescription(node) || node.isCheckable() || hasNonActionableSpeakingDescendants(node, view);
        }

        @JvmStatic
        public final boolean hasText(AccessibilityNodeInfoCompat node) {
            if (node == null || node.getCollectionInfo() != null) {
                return false;
            }
            CharSequence text = node.getText();
            if (text != null && text.length() != 0) {
                return true;
            }
            CharSequence contentDescription = node.getContentDescription();
            if (contentDescription != null && contentDescription.length() != 0) {
                return true;
            }
            CharSequence hintText = node.getHintText();
            return (hintText == null || hintText.length() == 0) ? false : true;
        }

        @JvmStatic
        public final boolean isAccessibilityFocusable(AccessibilityNodeInfoCompat node, View view) {
            if (node == null || view == null || !node.isVisibleToUser()) {
                return false;
            }
            return node.isScreenReaderFocusable() || isActionableForAccessibility(node);
        }

        @JvmStatic
        public final boolean isActionableForAccessibility(AccessibilityNodeInfoCompat node) {
            if (node == null) {
                return false;
            }
            if (node.isClickable() || node.isLongClickable() || node.isFocusable()) {
                return true;
            }
            List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = node.getActionList();
            Intrinsics.checkNotNullExpressionValue(actionList, "getActionList(...)");
            List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> list = actionList;
            if ((list instanceof Collection) && list.isEmpty()) {
                return false;
            }
            for (AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat : list) {
                if (Intrinsics.areEqual(accessibilityActionCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK) || Intrinsics.areEqual(accessibilityActionCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK) || Intrinsics.areEqual(accessibilityActionCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS)) {
                    return true;
                }
            }
            return false;
        }

        @JvmStatic
        public final AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
            if (view == null) {
                return null;
            }
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
            try {
                ViewCompat.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompatObtain);
                return accessibilityNodeInfoCompatObtain;
            } catch (NullPointerException unused) {
                return null;
            }
        }

        @JvmStatic
        public final CharSequence getTalkbackDescription(View view, AccessibilityNodeInfoCompat info) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain;
            Intrinsics.checkNotNullParameter(view, "view");
            if (info == null) {
                accessibilityNodeInfoCompatObtain = createNodeInfoFromView(view);
            } else {
                accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain(info);
            }
            if (accessibilityNodeInfoCompatObtain == null) {
                return null;
            }
            CharSequence contentDescription = accessibilityNodeInfoCompatObtain.getContentDescription();
            CharSequence text = accessibilityNodeInfoCompatObtain.getText();
            boolean z = text == null || text.length() == 0;
            boolean z2 = view instanceof EditText;
            StringBuilder sb = new StringBuilder();
            if (contentDescription != null && contentDescription.length() != 0 && (!z2 || z)) {
                sb.append(contentDescription);
                return sb;
            }
            if (!z) {
                sb.append(text);
                return sb;
            }
            if (!(view instanceof ViewGroup)) {
                return null;
            }
            StringBuilder sb2 = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain2 = AccessibilityNodeInfoCompat.obtain();
                ViewCompat.onInitializeAccessibilityNodeInfo(childAt, accessibilityNodeInfoCompatObtain2);
                if (isSpeakingNode(accessibilityNodeInfoCompatObtain2, childAt) && !isAccessibilityFocusable(accessibilityNodeInfoCompatObtain2, childAt)) {
                    Intrinsics.checkNotNull(childAt);
                    CharSequence talkbackDescription = getTalkbackDescription(childAt, null);
                    if (talkbackDescription != null && talkbackDescription.length() != 0) {
                        sb2.append(((Object) talkbackDescription) + ReactAccessibilityDelegate.delimiter);
                    }
                }
            }
            return removeFinalDelimiter(sb2);
        }

        private final String removeFinalDelimiter(StringBuilder builder) {
            int length = builder.length();
            if (length > 0) {
                builder.delete(length - 2, length);
            }
            String string = builder.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }
}
