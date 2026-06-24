package com.facebook.react.devsupport;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import io.sentry.SentryEvent;
import java.io.File;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: StackTraceHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001,B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001aJ\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u001b¢\u0006\u0002\u0010\u001cJ\u0019\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0005¢\u0006\u0002\u0010\u001dJ\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001f\u001a\u00020 H\u0007¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0017J%\u0010$\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0007¢\u0006\u0002\u0010&J\u0015\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/facebook/react/devsupport/StackTraceHelper;", "", "<init>", "()V", "COLUMN_KEY", "", "LINE_NUMBER_KEY", "FILE_KEY", "METHOD_NAME_KEY", "COLLAPSE_KEY", "MESSAGE_KEY", "ORIGINAL_MESSAGE_KEY", "NAME_KEY", "COMPONENT_STACK_KEY", "STACK_KEY", "ID_KEY", "IS_FATAL_KEY", "EXTRA_DATA_KEY", "STACK_FRAME_PATTERN1", "Ljava/util/regex/Pattern;", "STACK_FRAME_PATTERN2", "convertJsStackTrace", "", "Lcom/facebook/react/devsupport/interfaces/StackFrame;", StackTraceHelper.STACK_KEY, "Lcom/facebook/react/bridge/ReadableArray;", "(Lcom/facebook/react/bridge/ReadableArray;)[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "Lorg/json/JSONArray;", "(Lorg/json/JSONArray;)[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "(Ljava/lang/String;)[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "convertJavaStackTrace", SentryEvent.JsonKeys.EXCEPTION, "", "(Ljava/lang/Throwable;)[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "formatFrameSource", TypedValues.Attributes.S_FRAME, "formatStackTrace", "title", "(Ljava/lang/String;[Lcom/facebook/react/devsupport/interfaces/StackFrame;)Ljava/lang/String;", "convertProcessedError", "Lcom/facebook/react/bridge/JavaOnlyMap;", "error", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ProcessedError;", "convertProcessedError$ReactAndroid_release", "StackFrameImpl", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class StackTraceHelper {
    public static final String COLLAPSE_KEY = "collapse";
    public static final String COLUMN_KEY = "column";
    public static final String COMPONENT_STACK_KEY = "componentStack";
    public static final String EXTRA_DATA_KEY = "extraData";
    public static final String FILE_KEY = "file";
    public static final String ID_KEY = "id";
    public static final StackTraceHelper INSTANCE = new StackTraceHelper();
    public static final String IS_FATAL_KEY = "isFatal";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    public static final String MESSAGE_KEY = "message";
    public static final String METHOD_NAME_KEY = "methodName";
    public static final String NAME_KEY = "name";
    public static final String ORIGINAL_MESSAGE_KEY = "originalMessage";
    private static final Pattern STACK_FRAME_PATTERN1;
    private static final Pattern STACK_FRAME_PATTERN2;
    public static final String STACK_KEY = "stack";

    private StackTraceHelper() {
    }

    static {
        Pattern patternCompile = Pattern.compile("^(?:(.*?)@)?(.*?)\\:([0-9]+)\\:([0-9]+)$");
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(...)");
        STACK_FRAME_PATTERN1 = patternCompile;
        Pattern patternCompile2 = Pattern.compile("\\s*(?:at)\\s*(.+?)\\s*[@(](.*):([0-9]+):([0-9]+)[)]$");
        Intrinsics.checkNotNullExpressionValue(patternCompile2, "compile(...)");
        STACK_FRAME_PATTERN2 = patternCompile2;
    }

    @JvmStatic
    public static final StackFrame[] convertJsStackTrace(ReadableArray readableArray) {
        StackFrameImpl stackFrameImpl;
        int size = readableArray != null ? readableArray.size() : 0;
        if (readableArray == null) {
            return new StackFrame[0];
        }
        StackFrame[] stackFrameArr = new StackFrame[size];
        for (int i = 0; i < size; i++) {
            ReadableType type = readableArray.getType(i);
            if (type == ReadableType.Map) {
                ReadableMap map = readableArray.getMap(i);
                if (map == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                String string = map.getString("methodName");
                if (string == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                String string2 = map.getString("file");
                if (string2 == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                boolean z = map.hasKey(COLLAPSE_KEY) && !map.isNull(COLLAPSE_KEY) && map.getBoolean(COLLAPSE_KEY);
                int i2 = -1;
                int i3 = (!map.hasKey("lineNumber") || map.isNull("lineNumber")) ? -1 : map.getInt("lineNumber");
                if (map.hasKey("column") && !map.isNull("column")) {
                    i2 = map.getInt("column");
                }
                stackFrameImpl = new StackFrameImpl(string2, null, string, i3, i2, z, 2, null);
            } else {
                if (type != ReadableType.String) {
                    throw new IllegalStateException(("Cannot parse the stackframe for " + readableArray).toString());
                }
                String string3 = readableArray.getString(i);
                if (string3 != null) {
                    stackFrameImpl = new StackFrameImpl(null, null, string3, -1, -1, false, 34, null);
                } else {
                    throw new IllegalStateException("Required value was null.".toString());
                }
            }
            stackFrameArr[i] = stackFrameImpl;
        }
        return stackFrameArr;
    }

    public final StackFrame[] convertJsStackTrace(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new StackFrame[0];
        }
        int length = jSONArray.length();
        try {
            StackFrame[] stackFrameArr = new StackFrame[length];
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("methodName");
                String string2 = jSONObject.getString("file");
                int i2 = -1;
                int i3 = (!jSONObject.has("lineNumber") || jSONObject.isNull("lineNumber")) ? -1 : jSONObject.getInt("lineNumber");
                if (jSONObject.has("column") && !jSONObject.isNull("column")) {
                    i2 = jSONObject.getInt("column");
                }
                int i4 = i2;
                boolean z = jSONObject.has(COLLAPSE_KEY) && !jSONObject.isNull(COLLAPSE_KEY) && jSONObject.getBoolean(COLLAPSE_KEY);
                Intrinsics.checkNotNull(string);
                stackFrameArr[i] = new StackFrameImpl(string2, null, string, i3, i4, z, 2, null);
            }
            return stackFrameArr;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public final StackFrame[] convertJsStackTrace(String str) {
        List listEmptyList;
        StackFrameImpl stackFrameImpl;
        Intrinsics.checkNotNullParameter(str, "stack");
        List<String> listSplit = new Regex(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).split(str, 0);
        if (!listSplit.isEmpty()) {
            ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                    break;
                }
            }
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
        int length = strArr.length;
        StackFrame[] stackFrameArr = new StackFrame[length];
        for (int i = 0; i < length; i++) {
            Matcher matcher = STACK_FRAME_PATTERN1.matcher(strArr[i]);
            Matcher matcher2 = STACK_FRAME_PATTERN2.matcher(strArr[i]);
            if (matcher2.find()) {
                matcher = matcher2;
            } else if (!matcher.find()) {
                matcher = null;
            }
            if (matcher != null) {
                String strGroup = matcher.group(2);
                String strGroup2 = matcher.group(1);
                if (strGroup2 == null) {
                    strGroup2 = "(unknown)";
                }
                String str2 = strGroup2;
                String strGroup3 = matcher.group(3);
                String strGroup4 = matcher.group(4);
                if (strGroup3 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                int i2 = Integer.parseInt(strGroup3);
                if (strGroup4 != null) {
                    stackFrameImpl = new StackFrameImpl(strGroup, null, str2, i2, Integer.parseInt(strGroup4), false, 34, null);
                } else {
                    throw new IllegalStateException("Required value was null.".toString());
                }
            } else {
                stackFrameImpl = new StackFrameImpl(null, null, strArr[i], -1, -1, false, 34, null);
            }
            stackFrameArr[i] = stackFrameImpl;
        }
        return stackFrameArr;
    }

    @JvmStatic
    public static final StackFrame[] convertJavaStackTrace(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "exception");
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        StackFrame[] stackFrameArr = new StackFrame[length];
        for (int i = 0; i < length; i++) {
            String className = stackTrace[i].getClassName();
            String fileName = stackTrace[i].getFileName();
            String methodName = stackTrace[i].getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            stackFrameArr[i] = new StackFrameImpl(className, fileName, methodName, stackTrace[i].getLineNumber(), -1, false, 32, null);
        }
        return stackFrameArr;
    }

    public final String formatFrameSource(StackFrame stackFrame) {
        Intrinsics.checkNotNullParameter(stackFrame, "frame");
        StringBuilder sb = new StringBuilder();
        sb.append(stackFrame.getFileName());
        int line = stackFrame.getLine();
        if (line > 0) {
            sb.append(":").append(line);
            int column = stackFrame.getColumn();
            if (column > 0) {
                sb.append(":").append(column);
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @JvmStatic
    public static final String formatStackTrace(String title, StackFrame[] stackFrameArr) {
        Intrinsics.checkNotNullParameter(stackFrameArr, "stack");
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        for (StackFrame stackFrame : stackFrameArr) {
            sb.append(stackFrame.getMethod()).append("\n    ").append(INSTANCE.formatFrameSource(stackFrame)).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @JvmStatic
    public static final JavaOnlyMap convertProcessedError$ReactAndroid_release(ReactJsExceptionHandler.ProcessedError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        for (ReactJsExceptionHandler.ProcessedError.StackFrame stackFrame : error.getStack()) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            if (stackFrame.getColumn() != null) {
                javaOnlyMap.putDouble("column", r4.intValue());
            }
            if (stackFrame.getLineNumber() != null) {
                javaOnlyMap.putDouble("lineNumber", r4.intValue());
            }
            javaOnlyMap.putString("file", stackFrame.getFile());
            javaOnlyMap.putString("methodName", stackFrame.getMethodName());
            javaOnlyArray.pushMap(javaOnlyMap);
        }
        JavaOnlyMap javaOnlyMap2 = new JavaOnlyMap();
        javaOnlyMap2.putString("message", error.getMessage());
        String originalMessage = error.getOriginalMessage();
        if (originalMessage != null) {
            javaOnlyMap2.putString(ORIGINAL_MESSAGE_KEY, originalMessage);
        }
        String name = error.getName();
        if (name != null) {
            javaOnlyMap2.putString("name", name);
        }
        String componentStack = error.getComponentStack();
        if (componentStack != null) {
            javaOnlyMap2.putString(COMPONENT_STACK_KEY, componentStack);
        }
        javaOnlyMap2.putArray(STACK_KEY, javaOnlyArray);
        javaOnlyMap2.putInt("id", error.getId());
        javaOnlyMap2.putBoolean(IS_FATAL_KEY, error.isFatal());
        javaOnlyMap2.putMap("extraData", error.getExtraData());
        return javaOnlyMap2;
    }

    /* JADX INFO: compiled from: StackTraceHelper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/devsupport/StackTraceHelper$StackFrameImpl;", "Lcom/facebook/react/devsupport/interfaces/StackFrame;", "file", "", "fileName", "method", "line", "", "column", "isCollapsed", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZ)V", "getFile", "()Ljava/lang/String;", "getFileName", "getMethod", "getLine", "()I", "getColumn", "()Z", "toJSON", "Lorg/json/JSONObject;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class StackFrameImpl implements StackFrame {
        private final int column;
        private final String file;
        private final String fileName;
        private final boolean isCollapsed;
        private final int line;
        private final String method;

        public StackFrameImpl(String str, String str2, String method, int i, int i2, boolean z) {
            Intrinsics.checkNotNullParameter(method, "method");
            this.file = str;
            this.fileName = str2;
            this.method = method;
            this.line = i;
            this.column = i2;
            this.isCollapsed = z;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFile() {
            return this.file;
        }

        public /* synthetic */ StackFrameImpl(String str, String str2, String str3, int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i3 & 2) != 0 ? str != null ? new File(str).getName() : null : str2, str3, i, i2, (i3 & 32) != 0 ? false : z);
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFileName() {
            return this.fileName;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getMethod() {
            return this.method;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getLine() {
            return this.line;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getColumn() {
            return this.column;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        /* JADX INFO: renamed from: isCollapsed, reason: from getter */
        public boolean getIsCollapsed() {
            return this.isCollapsed;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public JSONObject toJSON() {
            Pair[] pairArr = new Pair[5];
            String file = getFile();
            if (file == null) {
                file = "";
            }
            pairArr[0] = TuplesKt.to("file", file);
            pairArr[1] = TuplesKt.to("methodName", getMethod());
            pairArr[2] = TuplesKt.to("lineNumber", Integer.valueOf(getLine()));
            pairArr[3] = TuplesKt.to("column", Integer.valueOf(getColumn()));
            pairArr[4] = TuplesKt.to(StackTraceHelper.COLLAPSE_KEY, Boolean.valueOf(getIsCollapsed()));
            return new JSONObject(MapsKt.mapOf(pairArr));
        }
    }
}
