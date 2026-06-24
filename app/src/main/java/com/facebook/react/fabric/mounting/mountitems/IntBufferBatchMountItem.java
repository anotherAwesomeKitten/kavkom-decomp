package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.systrace.Systrace;
import io.sentry.clientreport.DiscardedEvent;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: IntBufferBatchMountItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/fabric/mounting/mountitems/IntBufferBatchMountItem;", "Lcom/facebook/react/fabric/mounting/mountitems/BatchMountItem;", "surfaceId", "", "intBuffer", "", "objBuffer", "", "", "commitNumber", "<init>", "(I[I[Ljava/lang/Object;I)V", "[Ljava/lang/Object;", "intBufferLen", "objBufferLen", "beginMarkers", "", DiscardedEvent.JsonKeys.REASON, "", "endMarkers", "execute", "mountingManager", "Lcom/facebook/react/fabric/mounting/MountingManager;", "getSurfaceId", "isBatchEmpty", "", "toString", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IntBufferBatchMountItem implements BatchMountItem {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int INSTRUCTION_CREATE = 2;
    public static final int INSTRUCTION_DELETE = 4;
    public static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    public static final int INSTRUCTION_INSERT = 8;
    public static final int INSTRUCTION_REMOVE = 16;
    public static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    public static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    public static final int INSTRUCTION_UPDATE_OVERFLOW_INSET = 1024;
    public static final int INSTRUCTION_UPDATE_PADDING = 512;
    public static final int INSTRUCTION_UPDATE_PROPS = 32;
    public static final int INSTRUCTION_UPDATE_STATE = 64;
    private final int commitNumber;
    private final int[] intBuffer;
    private final int intBufferLen;
    private final Object[] objBuffer;
    private final int objBufferLen;
    private final int surfaceId;

    public IntBufferBatchMountItem(int i, int[] intBuffer, Object[] objBuffer, int i2) {
        Intrinsics.checkNotNullParameter(intBuffer, "intBuffer");
        Intrinsics.checkNotNullParameter(objBuffer, "objBuffer");
        this.surfaceId = i;
        this.intBuffer = intBuffer;
        this.objBuffer = objBuffer;
        this.commitNumber = i2;
        this.intBufferLen = intBuffer.length;
        this.objBufferLen = objBuffer.length;
    }

    private final void beginMarkers(String reason) {
        Systrace.beginSection(0L, "IntBufferBatchMountItem::" + reason);
        if (this.commitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, null, this.commitNumber);
        }
    }

    private final void endMarkers() {
        if (this.commitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, null, this.commitNumber);
        }
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        int i;
        long j;
        int i2;
        ReadableMap readableMap;
        boolean z;
        int i3;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.surfaceId);
        if (surfaceManager == null) {
            FLog.e("IntBufferBatchMountItem", "Skipping batch of MountItems; no SurfaceMountingManager found for [%d].", Integer.valueOf(this.surfaceId));
            return;
        }
        if (surfaceManager.isStopped()) {
            FLog.e("IntBufferBatchMountItem", "Skipping batch of MountItems; was stopped [%d].", Integer.valueOf(this.surfaceId));
            return;
        }
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            FLog.d("IntBufferBatchMountItem", "Executing IntBufferBatchMountItem on surface [%d]", Integer.valueOf(this.surfaceId));
        }
        beginMarkers("mountViews");
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.intBufferLen) {
            int[] iArr = this.intBuffer;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            int i10 = i9 & (-2);
            if ((i9 & 1) != 0) {
                int i11 = iArr[i8];
                i8 = i6 + 2;
                i = i11;
            } else {
                i = 1;
            }
            long j2 = 0;
            Systrace.beginSection(0L, "IntBufferBatchMountItem::mountInstructions::" + INSTANCE.nameForInstructionString(i10), new String[]{"numInstructions", String.valueOf(i)}, 2);
            int i12 = 0;
            int i13 = i7;
            while (i12 < i) {
                if (i10 == 2) {
                    j = j2;
                    i2 = i12;
                    int i14 = i13 + 1;
                    String str = (String) this.objBuffer[i13];
                    if (str == null) {
                        str = "";
                    }
                    String fabricComponentName = FabricNameComponentMapping.getFabricComponentName(str);
                    int[] iArr2 = this.intBuffer;
                    int i15 = iArr2[i8];
                    Object[] objArr = this.objBuffer;
                    ReadableMap readableMap2 = (ReadableMap) objArr[i14];
                    int i16 = i13 + 3;
                    StateWrapper stateWrapper = (StateWrapper) objArr[i13 + 2];
                    i13 += 4;
                    EventEmitterWrapper eventEmitterWrapper = (EventEmitterWrapper) objArr[i16];
                    int i17 = i8 + 2;
                    if (iArr2[i8 + 1] == 1) {
                        readableMap = readableMap2;
                        z = true;
                    } else {
                        readableMap = readableMap2;
                        z = false;
                    }
                    surfaceManager.createView(fabricComponentName, i15, readableMap, stateWrapper, eventEmitterWrapper, z);
                    i8 = i17;
                } else if (i10 == 4) {
                    j = j2;
                    i2 = i12;
                    surfaceManager.deleteView(this.intBuffer[i8]);
                    i8++;
                } else if (i10 == 8) {
                    j = j2;
                    i2 = i12;
                    int[] iArr3 = this.intBuffer;
                    int i18 = iArr3[i8];
                    int i19 = i8 + 2;
                    int i20 = iArr3[i8 + 1];
                    i8 += 3;
                    surfaceManager.addViewAt(i20, i18, iArr3[i19]);
                } else if (i10 != 16) {
                    if (i10 == 32) {
                        j = j2;
                        i2 = i12;
                        i3 = i8 + 1;
                        i4 = i13 + 1;
                        surfaceManager.updateProps(this.intBuffer[i8], (ReadableMap) this.objBuffer[i13]);
                    } else if (i10 == 64) {
                        j = j2;
                        i2 = i12;
                        i3 = i8 + 1;
                        i4 = i13 + 1;
                        surfaceManager.updateState(this.intBuffer[i8], (StateWrapper) this.objBuffer[i13]);
                    } else if (i10 == 128) {
                        int[] iArr4 = this.intBuffer;
                        i2 = i12;
                        j = j2;
                        surfaceManager.updateLayout(iArr4[i8], iArr4[i8 + 1], iArr4[i8 + 2], iArr4[i8 + 3], iArr4[i8 + 4], iArr4[i8 + 5], iArr4[i8 + 6], iArr4[i8 + 7]);
                        i8 += 8;
                    } else if (i10 != 256) {
                        if (i10 == 512) {
                            int[] iArr5 = this.intBuffer;
                            i5 = i8 + 5;
                            surfaceManager.updatePadding(iArr5[i8], iArr5[i8 + 1], iArr5[i8 + 2], iArr5[i8 + 3], iArr5[i8 + 4]);
                        } else if (i10 == 1024) {
                            int[] iArr6 = this.intBuffer;
                            i5 = i8 + 5;
                            surfaceManager.updateOverflowInset(iArr6[i8], iArr6[i8 + 1], iArr6[i8 + 2], iArr6[i8 + 3], iArr6[i8 + 4]);
                        } else {
                            throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i10 + " at index: " + i8);
                        }
                        i2 = i12;
                        i8 = i5;
                        j = j2;
                    } else {
                        int i21 = i13 + 1;
                        EventEmitterWrapper eventEmitterWrapper2 = (EventEmitterWrapper) this.objBuffer[i13];
                        if (eventEmitterWrapper2 != null) {
                            surfaceManager.updateEventEmitter(this.intBuffer[i8], eventEmitterWrapper2);
                            i13 = i21;
                            i8++;
                        } else {
                            i13 = i21;
                        }
                        j = j2;
                        i2 = i12;
                    }
                    i8 = i3;
                    i13 = i4;
                } else {
                    j = j2;
                    i2 = i12;
                    int[] iArr7 = this.intBuffer;
                    int i22 = iArr7[i8];
                    int i23 = i8 + 2;
                    int i24 = iArr7[i8 + 1];
                    i8 += 3;
                    surfaceManager.removeViewAt(i22, i24, iArr7[i23]);
                }
                i12 = i2 + 1;
                j2 = j;
            }
            Systrace.endSection(j2);
            i6 = i8;
            i7 = i13;
        }
        endMarkers();
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.surfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.BatchMountItem
    public boolean isBatchEmpty() {
        return this.intBufferLen == 0;
    }

    public String toString() {
        String str;
        int i;
        int i2;
        int i3;
        String string;
        String string2;
        String str2 = "";
        try {
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            int i4 = 1;
            String str3 = String.format(Locale.ROOT, "IntBufferBatchMountItem [surface:%d]:\n", Arrays.copyOf(new Object[]{Integer.valueOf(this.surfaceId)}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            sb.append(str3);
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.intBufferLen) {
                int[] iArr = this.intBuffer;
                int i7 = i5 + 1;
                int i8 = iArr[i5];
                int i9 = i8 & (-2);
                if ((i8 & i4) != 0) {
                    int i10 = i5 + 2;
                    i = iArr[i7];
                    i7 = i10;
                } else {
                    i = i4;
                }
                i5 = i7;
                int i11 = 0;
                while (i11 < i) {
                    if (i9 != 2) {
                        if (i9 == 4) {
                            str = str2;
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            i2 = i5 + 1;
                            String str4 = String.format(Locale.ROOT, "DELETE [%d]\n", Arrays.copyOf(new Object[]{Integer.valueOf(this.intBuffer[i5])}, 1));
                            Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
                            sb.append(str4);
                        } else if (i9 == 8) {
                            str = str2;
                            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                            Locale locale = Locale.ROOT;
                            Integer numValueOf = Integer.valueOf(this.intBuffer[i5]);
                            int i12 = i5 + 2;
                            Integer numValueOf2 = Integer.valueOf(this.intBuffer[i5 + 1]);
                            i5 += 3;
                            String str5 = String.format(locale, "INSERT [%d]->[%d] @%d\n", Arrays.copyOf(new Object[]{numValueOf, numValueOf2, Integer.valueOf(this.intBuffer[i12])}, 3));
                            Intrinsics.checkNotNullExpressionValue(str5, "format(...)");
                            sb.append(str5);
                        } else if (i9 != 16) {
                            String str6 = "<null>";
                            if (i9 == 32) {
                                str = str2;
                                i3 = i6 + 1;
                                Object obj = this.objBuffer[i6];
                                if (!FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
                                    str6 = "<hidden>";
                                } else if (obj != null && (string = obj.toString()) != null) {
                                    str6 = string;
                                }
                                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                                i2 = i5 + 1;
                                String str7 = String.format(Locale.ROOT, "UPDATE PROPS [%d]: %s\n", Arrays.copyOf(new Object[]{Integer.valueOf(this.intBuffer[i5]), str6}, 2));
                                Intrinsics.checkNotNullExpressionValue(str7, "format(...)");
                                sb.append(str7);
                            } else if (i9 == 64) {
                                str = str2;
                                i3 = i6 + 1;
                                StateWrapper stateWrapper = (StateWrapper) this.objBuffer[i6];
                                if (!FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
                                    str6 = "<hidden>";
                                } else if (stateWrapper != null && (string2 = stateWrapper.toString()) != null) {
                                    str6 = string2;
                                }
                                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                                i2 = i5 + 1;
                                String str8 = String.format(Locale.ROOT, "UPDATE STATE [%d]: %s\n", Arrays.copyOf(new Object[]{Integer.valueOf(this.intBuffer[i5]), str6}, 2));
                                Intrinsics.checkNotNullExpressionValue(str8, "format(...)");
                                sb.append(str8);
                            } else if (i9 == 128) {
                                str = str2;
                                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                                Locale locale2 = Locale.ROOT;
                                Integer numValueOf3 = Integer.valueOf(this.intBuffer[i5]);
                                Integer numValueOf4 = Integer.valueOf(this.intBuffer[i5 + 1]);
                                Integer numValueOf5 = Integer.valueOf(this.intBuffer[i5 + 2]);
                                Integer numValueOf6 = Integer.valueOf(this.intBuffer[i5 + 3]);
                                Integer numValueOf7 = Integer.valueOf(this.intBuffer[i5 + 4]);
                                Integer numValueOf8 = Integer.valueOf(this.intBuffer[i5 + 5]);
                                int i13 = i5 + 7;
                                Integer numValueOf9 = Integer.valueOf(this.intBuffer[i5 + 6]);
                                i5 += 8;
                                String str9 = String.format(locale2, "UPDATE LAYOUT [%d]->[%d]: x:%d y:%d w:%d h:%d displayType:%d layoutDirection:%d\n", Arrays.copyOf(new Object[]{numValueOf3, numValueOf4, numValueOf5, numValueOf6, numValueOf7, numValueOf8, numValueOf9, Integer.valueOf(this.intBuffer[i13])}, 8));
                                Intrinsics.checkNotNullExpressionValue(str9, "format(...)");
                                sb.append(str9);
                            } else if (i9 == 256) {
                                str = str2;
                                i6++;
                                StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                                i2 = i5 + 1;
                                String str10 = String.format(Locale.ROOT, "UPDATE EVENTEMITTER [%d]\n", Arrays.copyOf(new Object[]{Integer.valueOf(this.intBuffer[i5])}, 1));
                                Intrinsics.checkNotNullExpressionValue(str10, "format(...)");
                                sb.append(str10);
                            } else if (i9 == 512) {
                                str = str2;
                                StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                                Locale locale3 = Locale.ROOT;
                                Integer numValueOf10 = Integer.valueOf(this.intBuffer[i5]);
                                Integer numValueOf11 = Integer.valueOf(this.intBuffer[i5 + 1]);
                                Integer numValueOf12 = Integer.valueOf(this.intBuffer[i5 + 2]);
                                int i14 = i5 + 4;
                                Integer numValueOf13 = Integer.valueOf(this.intBuffer[i5 + 3]);
                                i5 += 5;
                                String str11 = String.format(locale3, "UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", Arrays.copyOf(new Object[]{numValueOf10, numValueOf11, numValueOf12, numValueOf13, Integer.valueOf(this.intBuffer[i14])}, 5));
                                Intrinsics.checkNotNullExpressionValue(str11, "format(...)");
                                sb.append(str11);
                            } else if (i9 == 1024) {
                                StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                                Locale locale4 = Locale.ROOT;
                                Integer numValueOf14 = Integer.valueOf(this.intBuffer[i5]);
                                Integer numValueOf15 = Integer.valueOf(this.intBuffer[i5 + 1]);
                                Integer numValueOf16 = Integer.valueOf(this.intBuffer[i5 + 2]);
                                int i15 = i5 + 4;
                                Integer numValueOf17 = Integer.valueOf(this.intBuffer[i5 + 3]);
                                str = str2;
                                try {
                                    i5 += 5;
                                    String str12 = String.format(locale4, "UPDATE OVERFLOWINSET [%d]: left:%d top:%d right:%d bottom:%d\n", Arrays.copyOf(new Object[]{numValueOf14, numValueOf15, numValueOf16, numValueOf17, Integer.valueOf(this.intBuffer[i15])}, 5));
                                    Intrinsics.checkNotNullExpressionValue(str12, "format(...)");
                                    sb.append(str12);
                                } catch (Exception e) {
                                    e = e;
                                    FLog.e("IntBufferBatchMountItem", "Caught exception trying to print", e);
                                    StringBuilder sb2 = new StringBuilder();
                                    for (int i16 = 0; i16 < this.intBufferLen; i16++) {
                                        sb2.append(this.intBuffer[i16]);
                                        sb2.append(", ");
                                    }
                                    FLog.e("IntBufferBatchMountItem", sb2.toString());
                                    for (int i17 = 0; i17 < this.objBufferLen; i17++) {
                                        Object obj2 = this.objBuffer[i17];
                                        FLog.e("IntBufferBatchMountItem", obj2 != null ? String.valueOf(obj2) : "null");
                                    }
                                    return str;
                                }
                            } else {
                                FLog.e("IntBufferBatchMountItem", "String so far: " + ((Object) sb));
                                throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i9 + " at index: " + i5);
                            }
                            i6 = i3;
                        } else {
                            str = str2;
                            StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
                            Locale locale5 = Locale.ROOT;
                            Integer numValueOf18 = Integer.valueOf(this.intBuffer[i5]);
                            int i18 = i5 + 2;
                            Integer numValueOf19 = Integer.valueOf(this.intBuffer[i5 + 1]);
                            i5 += 3;
                            String str13 = String.format(locale5, "REMOVE [%d]->[%d] @%d\n", Arrays.copyOf(new Object[]{numValueOf18, numValueOf19, Integer.valueOf(this.intBuffer[i18])}, 3));
                            Intrinsics.checkNotNullExpressionValue(str13, "format(...)");
                            sb.append(str13);
                        }
                        i5 = i2;
                    } else {
                        str = str2;
                        String str14 = (String) this.objBuffer[i6];
                        if (str14 == null) {
                            str14 = str;
                        }
                        String fabricComponentName = FabricNameComponentMapping.getFabricComponentName(str14);
                        i6 += 4;
                        StringCompanionObject stringCompanionObject11 = StringCompanionObject.INSTANCE;
                        Locale locale6 = Locale.ROOT;
                        int i19 = i5 + 1;
                        Integer numValueOf20 = Integer.valueOf(this.intBuffer[i5]);
                        i5 += 2;
                        String str15 = String.format(locale6, "CREATE [%d] - layoutable:%d - %s\n", Arrays.copyOf(new Object[]{numValueOf20, Integer.valueOf(this.intBuffer[i19]), fabricComponentName}, 3));
                        Intrinsics.checkNotNullExpressionValue(str15, "format(...)");
                        sb.append(str15);
                    }
                    i11++;
                    str2 = str;
                    i4 = 1;
                }
            }
            String string3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
            return string3;
        } catch (Exception e2) {
            e = e2;
            str = str2;
        }
    }

    /* JADX INFO: compiled from: IntBufferBatchMountItem.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/fabric/mounting/mountitems/IntBufferBatchMountItem$Companion;", "", "<init>", "()V", "INSTRUCTION_FLAG_MULTIPLE", "", "INSTRUCTION_CREATE", "INSTRUCTION_DELETE", "INSTRUCTION_INSERT", "INSTRUCTION_REMOVE", "INSTRUCTION_UPDATE_PROPS", "INSTRUCTION_UPDATE_STATE", "INSTRUCTION_UPDATE_LAYOUT", "INSTRUCTION_UPDATE_EVENT_EMITTER", "INSTRUCTION_UPDATE_PADDING", "INSTRUCTION_UPDATE_OVERFLOW_INSET", "nameForInstructionString", "", "type", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String nameForInstructionString(int type) {
            if (type == 2) {
                return "CREATE";
            }
            if (type == 4) {
                return "DELETE";
            }
            if (type == 8) {
                return "INSERT";
            }
            if (type == 16) {
                return "REMOVE";
            }
            if (type == 32) {
                return "UPDATE_PROPS";
            }
            if (type == 64) {
                return "UPDATE_STATE";
            }
            if (type == 128) {
                return "UPDATE_LAYOUT";
            }
            if (type == 256) {
                return "UPDATE_EVENT_EMITTER";
            }
            if (type == 512) {
                return "UPDATE_PADDING";
            }
            if (type == 1024) {
                return "UPDATE_OVERFLOW_INSET";
            }
            return "UNKNOWN";
        }
    }
}
