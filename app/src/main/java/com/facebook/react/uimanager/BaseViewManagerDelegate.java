package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.yoga.YogaConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewManagerDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0016\b\u0001\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0006B\u000f\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012J%\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017R\u0012\u0010\u0007\u001a\u00028\u00018\u0004X\u0085\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/facebook/react/uimanager/BaseViewManagerDelegate;", "T", "Landroid/view/View;", "U", "Lcom/facebook/react/uimanager/BaseViewManager;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "mViewManager", "<init>", "(Lcom/facebook/react/uimanager/BaseViewManager;)V", "Lcom/facebook/react/uimanager/BaseViewManager;", "setProperty", "", "view", "propName", "", "value", "", "(Landroid/view/View;Ljava/lang/String;Ljava/lang/Object;)V", "receiveCommand", "commandName", "args", "Lcom/facebook/react/bridge/ReadableArray;", "(Landroid/view/View;Ljava/lang/String;Lcom/facebook/react/bridge/ReadableArray;)V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: receiveCommand */
    public void kotlinCompat$receiveCommand(T view, String commandName, ReadableArray args) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(commandName, "commandName");
        Intrinsics.checkNotNullParameter(args, "args");
    }

    public BaseViewManagerDelegate(U mViewManager) {
        Intrinsics.checkNotNullParameter(mViewManager, "mViewManager");
        this.mViewManager = mViewManager;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T view, String propName, Object value) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(propName, "propName");
        switch (propName.hashCode()) {
            case -2018402664:
                if (propName.equals(ViewProps.MIX_BLEND_MODE)) {
                    this.mViewManager.setMixBlendMode(view, (String) value);
                    break;
                }
                break;
            case -1898517556:
                if (propName.equals(ViewProps.ON_POINTER_ENTER_CAPTURE)) {
                    Boolean bool = (Boolean) value;
                    this.mViewManager.setPointerEnterCapture(view, bool != null ? bool.booleanValue() : false);
                    break;
                }
                break;
            case -1721943862:
                if (propName.equals(ViewProps.TRANSLATE_X)) {
                    Double d = (Double) value;
                    this.mViewManager.setTranslateX(view, d != null ? (float) d.doubleValue() : 0.0f);
                    break;
                }
                break;
            case -1721943861:
                if (propName.equals(ViewProps.TRANSLATE_Y)) {
                    Double d2 = (Double) value;
                    this.mViewManager.setTranslateY(view, d2 != null ? (float) d2.doubleValue() : 0.0f);
                    break;
                }
                break;
            case -1589741021:
                if (propName.equals(ViewProps.SHADOW_COLOR)) {
                    U u = this.mViewManager;
                    Context context = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                    u.setShadowColor(view, ColorPropConverter.getColor(value, context, 0));
                    break;
                }
                break;
            case -1489432511:
                if (propName.equals(ViewProps.OUTLINE_COLOR)) {
                    this.mViewManager.setOutlineColor(view, (Integer) value);
                    break;
                }
                break;
            case -1474494833:
                if (propName.equals(ViewProps.OUTLINE_STYLE)) {
                    this.mViewManager.setOutlineStyle(view, (String) value);
                    break;
                }
                break;
            case -1471148380:
                if (propName.equals(ViewProps.OUTLINE_WIDTH)) {
                    Double d3 = (Double) value;
                    this.mViewManager.setOutlineWidth(view, d3 != null ? (float) d3.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case -1351902487:
                if (propName.equals(ViewProps.ON_CLICK)) {
                    Boolean bool2 = (Boolean) value;
                    this.mViewManager.setClick(view, bool2 != null ? bool2.booleanValue() : false);
                    break;
                }
                break;
            case -1274492040:
                if (propName.equals(ViewProps.FILTER)) {
                    this.mViewManager.setFilter(view, (ReadableArray) value);
                    break;
                }
                break;
            case -1267206133:
                if (propName.equals(ViewProps.OPACITY)) {
                    Double d4 = (Double) value;
                    this.mViewManager.setOpacity(view, d4 != null ? (float) d4.doubleValue() : 1.0f);
                    break;
                }
                break;
            case -1247970794:
                if (propName.equals(ViewProps.ON_POINTER_OUT_CAPTURE)) {
                    Boolean bool3 = (Boolean) value;
                    this.mViewManager.setPointerOutCapture(view, bool3 != null ? bool3.booleanValue() : false);
                    break;
                }
                break;
            case -1228066334:
                if (propName.equals(ViewProps.BORDER_TOP_LEFT_RADIUS)) {
                    Double d5 = (Double) value;
                    this.mViewManager.setBorderTopLeftRadius(view, d5 != null ? (float) d5.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case -1219666915:
                if (propName.equals(ViewProps.ON_CLICK_CAPTURE)) {
                    Boolean bool4 = (Boolean) value;
                    this.mViewManager.setClickCapture(view, bool4 != null ? bool4.booleanValue() : false);
                    break;
                }
                break;
            case -1036769289:
                if (propName.equals(ViewProps.ON_POINTER_MOVE_CAPTURE)) {
                    Boolean bool5 = (Boolean) value;
                    this.mViewManager.setPointerMoveCapture(view, bool5 != null ? bool5.booleanValue() : false);
                    break;
                }
                break;
            case -994557277:
                if (propName.equals(ViewProps.SCREEN_READER_FOCUSABLE)) {
                    Boolean bool6 = (Boolean) value;
                    this.mViewManager.setScreenReaderFocusable(view, bool6 != null ? bool6.booleanValue() : false);
                    break;
                }
                break;
            case -908189618:
                if (propName.equals("scaleX")) {
                    Double d6 = (Double) value;
                    this.mViewManager.setScaleX(view, d6 != null ? (float) d6.doubleValue() : 1.0f);
                    break;
                }
                break;
            case -908189617:
                if (propName.equals("scaleY")) {
                    Double d7 = (Double) value;
                    this.mViewManager.setScaleY(view, d7 != null ? (float) d7.doubleValue() : 1.0f);
                    break;
                }
                break;
            case -877170387:
                if (propName.equals(ViewProps.TEST_ID)) {
                    this.mViewManager.setTestId(view, (String) value);
                    break;
                }
                break;
            case -781597262:
                if (propName.equals(ViewProps.TRANSFORM_ORIGIN)) {
                    this.mViewManager.setTransformOrigin(view, (ReadableArray) value);
                    break;
                }
                break;
            case -731417480:
                if (propName.equals(ViewProps.Z_INDEX)) {
                    Double d8 = (Double) value;
                    this.mViewManager.setZIndex(view, d8 != null ? (float) d8.doubleValue() : 0.0f);
                    break;
                }
                break;
            case -112141555:
                if (propName.equals(ViewProps.ON_POINTER_LEAVE_CAPTURE)) {
                    Boolean bool7 = (Boolean) value;
                    this.mViewManager.setPointerLeaveCapture(view, bool7 != null ? bool7.booleanValue() : false);
                    break;
                }
                break;
            case -101663499:
                if (propName.equals(ViewProps.ACCESSIBILITY_HINT)) {
                    this.mViewManager.setAccessibilityHint(view, (String) value);
                    break;
                }
                break;
            case -101359900:
                if (propName.equals(ViewProps.ACCESSIBILITY_ROLE)) {
                    this.mViewManager.setAccessibilityRole(view, (String) value);
                    break;
                }
                break;
            case -80891667:
                if (propName.equals(ViewProps.RENDER_TO_HARDWARE_TEXTURE)) {
                    Boolean bool8 = (Boolean) value;
                    this.mViewManager.setRenderToHardwareTexture(view, bool8 != null ? bool8.booleanValue() : false);
                    break;
                }
                break;
            case -40300674:
                if (propName.equals("rotation")) {
                    Double d9 = (Double) value;
                    this.mViewManager.setRotation(view, d9 != null ? (float) d9.doubleValue() : 0.0f);
                    break;
                }
                break;
            case -4379043:
                if (propName.equals("elevation")) {
                    Double d10 = (Double) value;
                    this.mViewManager.setElevation(view, d10 != null ? (float) d10.doubleValue() : 0.0f);
                    break;
                }
                break;
            case 3506294:
                if (propName.equals(ViewProps.ROLE)) {
                    this.mViewManager.setRole(view, (String) value);
                    break;
                }
                break;
            case 17941018:
                if (propName.equals(ViewProps.ON_POINTER_ENTER)) {
                    Boolean bool9 = (Boolean) value;
                    this.mViewManager.setPointerEnter(view, bool9 != null ? bool9.booleanValue() : false);
                    break;
                }
                break;
            case 24119801:
                if (propName.equals(ViewProps.ON_POINTER_LEAVE)) {
                    Boolean bool10 = (Boolean) value;
                    this.mViewManager.setPointerLeave(view, bool10 != null ? bool10.booleanValue() : false);
                    break;
                }
                break;
            case 36255470:
                if (propName.equals(ViewProps.ACCESSIBILITY_LIVE_REGION)) {
                    this.mViewManager.setAccessibilityLiveRegion(view, (String) value);
                    break;
                }
                break;
            case 132353428:
                if (propName.equals(ViewProps.ON_POINTER_OVER_CAPTURE)) {
                    Boolean bool11 = (Boolean) value;
                    this.mViewManager.setPointerOverCapture(view, bool11 != null ? bool11.booleanValue() : false);
                    break;
                }
                break;
            case 317346576:
                if (propName.equals(ViewProps.ON_POINTER_OUT)) {
                    Boolean bool12 = (Boolean) value;
                    this.mViewManager.setPointerOut(view, bool12 != null ? bool12.booleanValue() : false);
                    break;
                }
                break;
            case 333432965:
                if (propName.equals(ViewProps.BORDER_TOP_RIGHT_RADIUS)) {
                    Double d11 = (Double) value;
                    this.mViewManager.setBorderTopRightRadius(view, d11 != null ? (float) d11.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case 581268560:
                if (propName.equals(ViewProps.BORDER_BOTTOM_LEFT_RADIUS)) {
                    Double d12 = (Double) value;
                    this.mViewManager.setBorderBottomLeftRadius(view, d12 != null ? (float) d12.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case 588239831:
                if (propName.equals(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)) {
                    Double d13 = (Double) value;
                    this.mViewManager.setBorderBottomRightRadius(view, d13 != null ? (float) d13.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case 743055051:
                if (propName.equals(ViewProps.BOX_SHADOW)) {
                    this.mViewManager.setBoxShadow(view, (ReadableArray) value);
                    break;
                }
                break;
            case 746986311:
                if (propName.equals(ViewProps.IMPORTANT_FOR_ACCESSIBILITY)) {
                    this.mViewManager.setImportantForAccessibility(view, (String) value);
                    break;
                }
                break;
            case 1052666732:
                if (propName.equals(ViewProps.TRANSFORM)) {
                    this.mViewManager.setTransform(view, (ReadableArray) value);
                    break;
                }
                break;
            case 1146842694:
                if (propName.equals(ViewProps.ACCESSIBILITY_LABEL)) {
                    this.mViewManager.setAccessibilityLabel(view, (String) value);
                    break;
                }
                break;
            case 1153872867:
                if (propName.equals(ViewProps.ACCESSIBILITY_STATE)) {
                    this.mViewManager.setViewState(view, (ReadableMap) value);
                    break;
                }
                break;
            case 1156088003:
                if (propName.equals(ViewProps.ACCESSIBILITY_VALUE)) {
                    this.mViewManager.setAccessibilityValue(view, (ReadableMap) value);
                    break;
                }
                break;
            case 1247744079:
                if (propName.equals(ViewProps.ON_POINTER_MOVE)) {
                    Boolean bool13 = (Boolean) value;
                    this.mViewManager.setPointerMove(view, bool13 != null ? bool13.booleanValue() : false);
                    break;
                }
                break;
            case 1247809874:
                if (propName.equals(ViewProps.ON_POINTER_OVER)) {
                    Boolean bool14 = (Boolean) value;
                    this.mViewManager.setPointerOver(view, bool14 != null ? bool14.booleanValue() : false);
                    break;
                }
                break;
            case 1287124693:
                if (propName.equals(ViewProps.BACKGROUND_COLOR)) {
                    U u2 = this.mViewManager;
                    Context context2 = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    u2.setBackgroundColor(view, ColorPropConverter.getColor(value, context2, 0));
                    break;
                }
                break;
            case 1349188574:
                if (propName.equals(ViewProps.BORDER_RADIUS)) {
                    Double d14 = (Double) value;
                    this.mViewManager.setBorderRadius(view, d14 != null ? (float) d14.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case 1407295349:
                if (propName.equals(ViewProps.OUTLINE_OFFSET)) {
                    Double d15 = (Double) value;
                    this.mViewManager.setOutlineOffset(view, d15 != null ? (float) d15.doubleValue() : YogaConstants.UNDEFINED);
                    break;
                }
                break;
            case 1505602511:
                if (propName.equals(ViewProps.ACCESSIBILITY_ACTIONS)) {
                    this.mViewManager.setAccessibilityActions(view, (ReadableArray) value);
                    break;
                }
                break;
            case 1761903244:
                if (propName.equals(ViewProps.ACCESSIBILITY_COLLECTION)) {
                    this.mViewManager.setAccessibilityCollection(view, (ReadableMap) value);
                    break;
                }
                break;
            case 1865277756:
                if (propName.equals(ViewProps.ACCESSIBILITY_LABELLED_BY)) {
                    this.mViewManager.setAccessibilityLabelledBy(view, new DynamicFromObject(value));
                    break;
                }
                break;
            case 1993034687:
                if (propName.equals(ViewProps.ACCESSIBILITY_COLLECTION_ITEM)) {
                    this.mViewManager.setAccessibilityCollectionItem(view, (ReadableMap) value);
                    break;
                }
                break;
            case 2045685618:
                if (propName.equals(ViewProps.NATIVE_ID)) {
                    this.mViewManager.setNativeId(view, (String) value);
                    break;
                }
                break;
        }
    }
}
