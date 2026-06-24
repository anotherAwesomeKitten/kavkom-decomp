package io.sentry.protocol;

import com.google.common.base.Ascii;
import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLockReason;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class SentryStackFrame implements JsonUnknown, JsonSerializable {
    private Boolean _native;
    private String _package;
    private String absPath;
    private Integer colno;
    private String contextLine;
    private String filename;
    private List<Integer> framesOmitted;
    private String function;
    private String imageAddr;
    private Boolean inApp;
    private String instructionAddr;
    private Integer lineno;
    private SentryLockReason lock;
    private String module;
    private String platform;
    private List<String> postContext;
    private List<String> preContext;
    private String rawFunction;
    private String symbol;
    private String symbolAddr;
    private Map<String, Object> unknown;
    private Map<String, String> vars;

    public static final class JsonKeys {
        public static final String ABS_PATH = "abs_path";
        public static final String COLNO = "colno";
        public static final String CONTEXT_LINE = "context_line";
        public static final String FILENAME = "filename";
        public static final String FUNCTION = "function";
        public static final String IMAGE_ADDR = "image_addr";
        public static final String INSTRUCTION_ADDR = "instruction_addr";
        public static final String IN_APP = "in_app";
        public static final String LINENO = "lineno";
        public static final String LOCK = "lock";
        public static final String MODULE = "module";
        public static final String NATIVE = "native";
        public static final String PACKAGE = "package";
        public static final String PLATFORM = "platform";
        public static final String RAW_FUNCTION = "raw_function";
        public static final String SYMBOL = "symbol";
        public static final String SYMBOL_ADDR = "symbol_addr";
    }

    public List<String> getPreContext() {
        return this.preContext;
    }

    public void setPreContext(List<String> list) {
        this.preContext = list;
    }

    public List<String> getPostContext() {
        return this.postContext;
    }

    public void setPostContext(List<String> list) {
        this.postContext = list;
    }

    public Map<String, String> getVars() {
        return this.vars;
    }

    public void setVars(Map<String, String> map) {
        this.vars = map;
    }

    public List<Integer> getFramesOmitted() {
        return this.framesOmitted;
    }

    public void setFramesOmitted(List<Integer> list) {
        this.framesOmitted = list;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String str) {
        this.function = str;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String str) {
        this.module = str;
    }

    public Integer getLineno() {
        return this.lineno;
    }

    public void setLineno(Integer num) {
        this.lineno = num;
    }

    public Integer getColno() {
        return this.colno;
    }

    public void setColno(Integer num) {
        this.colno = num;
    }

    public String getAbsPath() {
        return this.absPath;
    }

    public void setAbsPath(String str) {
        this.absPath = str;
    }

    public String getContextLine() {
        return this.contextLine;
    }

    public void setContextLine(String str) {
        this.contextLine = str;
    }

    public Boolean isInApp() {
        return this.inApp;
    }

    public void setInApp(Boolean bool) {
        this.inApp = bool;
    }

    public String getPackage() {
        return this._package;
    }

    public void setPackage(String str) {
        this._package = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getImageAddr() {
        return this.imageAddr;
    }

    public void setImageAddr(String str) {
        this.imageAddr = str;
    }

    public String getSymbolAddr() {
        return this.symbolAddr;
    }

    public void setSymbolAddr(String str) {
        this.symbolAddr = str;
    }

    public String getInstructionAddr() {
        return this.instructionAddr;
    }

    public void setInstructionAddr(String str) {
        this.instructionAddr = str;
    }

    public Boolean isNative() {
        return this._native;
    }

    public void setNative(Boolean bool) {
        this._native = bool;
    }

    public String getRawFunction() {
        return this.rawFunction;
    }

    public void setRawFunction(String str) {
        this.rawFunction = str;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public SentryLockReason getLock() {
        return this.lock;
    }

    public void setLock(SentryLockReason sentryLockReason) {
        this.lock = sentryLockReason;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.filename != null) {
            objectWriter.name("filename").value(this.filename);
        }
        if (this.function != null) {
            objectWriter.name(JsonKeys.FUNCTION).value(this.function);
        }
        if (this.module != null) {
            objectWriter.name("module").value(this.module);
        }
        if (this.lineno != null) {
            objectWriter.name(JsonKeys.LINENO).value(this.lineno);
        }
        if (this.colno != null) {
            objectWriter.name(JsonKeys.COLNO).value(this.colno);
        }
        if (this.absPath != null) {
            objectWriter.name(JsonKeys.ABS_PATH).value(this.absPath);
        }
        if (this.contextLine != null) {
            objectWriter.name(JsonKeys.CONTEXT_LINE).value(this.contextLine);
        }
        if (this.inApp != null) {
            objectWriter.name(JsonKeys.IN_APP).value(this.inApp);
        }
        if (this._package != null) {
            objectWriter.name(JsonKeys.PACKAGE).value(this._package);
        }
        if (this._native != null) {
            objectWriter.name(JsonKeys.NATIVE).value(this._native);
        }
        if (this.platform != null) {
            objectWriter.name("platform").value(this.platform);
        }
        if (this.imageAddr != null) {
            objectWriter.name("image_addr").value(this.imageAddr);
        }
        if (this.symbolAddr != null) {
            objectWriter.name(JsonKeys.SYMBOL_ADDR).value(this.symbolAddr);
        }
        if (this.instructionAddr != null) {
            objectWriter.name(JsonKeys.INSTRUCTION_ADDR).value(this.instructionAddr);
        }
        if (this.rawFunction != null) {
            objectWriter.name(JsonKeys.RAW_FUNCTION).value(this.rawFunction);
        }
        if (this.symbol != null) {
            objectWriter.name(JsonKeys.SYMBOL).value(this.symbol);
        }
        if (this.lock != null) {
            objectWriter.name(JsonKeys.LOCK).value(iLogger, this.lock);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<SentryStackFrame> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // io.sentry.JsonDeserializer
        public SentryStackFrame deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            SentryStackFrame sentryStackFrame = new SentryStackFrame();
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                byte b = -1;
                switch (strNextName.hashCode()) {
                    case -1443345323:
                        if (strNextName.equals("image_addr")) {
                            b = 0;
                        }
                        break;
                    case -1184392185:
                        if (strNextName.equals(JsonKeys.IN_APP)) {
                            b = 1;
                        }
                        break;
                    case -1113875953:
                        if (strNextName.equals(JsonKeys.RAW_FUNCTION)) {
                            b = 2;
                        }
                        break;
                    case -1102671691:
                        if (strNextName.equals(JsonKeys.LINENO)) {
                            b = 3;
                        }
                        break;
                    case -1068784020:
                        if (strNextName.equals("module")) {
                            b = 4;
                        }
                        break;
                    case -1052618729:
                        if (strNextName.equals(JsonKeys.NATIVE)) {
                            b = 5;
                        }
                        break;
                    case -887523944:
                        if (strNextName.equals(JsonKeys.SYMBOL)) {
                            b = 6;
                        }
                        break;
                    case -807062458:
                        if (strNextName.equals(JsonKeys.PACKAGE)) {
                            b = 7;
                        }
                        break;
                    case -734768633:
                        if (strNextName.equals("filename")) {
                            b = 8;
                        }
                        break;
                    case -330260936:
                        if (strNextName.equals(JsonKeys.SYMBOL_ADDR)) {
                            b = 9;
                        }
                        break;
                    case 3327275:
                        if (strNextName.equals(JsonKeys.LOCK)) {
                            b = 10;
                        }
                        break;
                    case 94842689:
                        if (strNextName.equals(JsonKeys.COLNO)) {
                            b = Ascii.VT;
                        }
                        break;
                    case 410194178:
                        if (strNextName.equals(JsonKeys.INSTRUCTION_ADDR)) {
                            b = Ascii.FF;
                        }
                        break;
                    case 1116694660:
                        if (strNextName.equals(JsonKeys.CONTEXT_LINE)) {
                            b = Ascii.CR;
                        }
                        break;
                    case 1380938712:
                        if (strNextName.equals(JsonKeys.FUNCTION)) {
                            b = Ascii.SO;
                        }
                        break;
                    case 1713445842:
                        if (strNextName.equals(JsonKeys.ABS_PATH)) {
                            b = Ascii.SI;
                        }
                        break;
                    case 1874684019:
                        if (strNextName.equals("platform")) {
                            b = Ascii.DLE;
                        }
                        break;
                }
                switch (b) {
                    case 0:
                        sentryStackFrame.imageAddr = objectReader.nextStringOrNull();
                        break;
                    case 1:
                        sentryStackFrame.inApp = objectReader.nextBooleanOrNull();
                        break;
                    case 2:
                        sentryStackFrame.rawFunction = objectReader.nextStringOrNull();
                        break;
                    case 3:
                        sentryStackFrame.lineno = objectReader.nextIntegerOrNull();
                        break;
                    case 4:
                        sentryStackFrame.module = objectReader.nextStringOrNull();
                        break;
                    case 5:
                        sentryStackFrame._native = objectReader.nextBooleanOrNull();
                        break;
                    case 6:
                        sentryStackFrame.symbol = objectReader.nextStringOrNull();
                        break;
                    case 7:
                        sentryStackFrame._package = objectReader.nextStringOrNull();
                        break;
                    case 8:
                        sentryStackFrame.filename = objectReader.nextStringOrNull();
                        break;
                    case 9:
                        sentryStackFrame.symbolAddr = objectReader.nextStringOrNull();
                        break;
                    case 10:
                        sentryStackFrame.lock = (SentryLockReason) objectReader.nextOrNull(iLogger, new SentryLockReason.Deserializer());
                        break;
                    case 11:
                        sentryStackFrame.colno = objectReader.nextIntegerOrNull();
                        break;
                    case 12:
                        sentryStackFrame.instructionAddr = objectReader.nextStringOrNull();
                        break;
                    case 13:
                        sentryStackFrame.contextLine = objectReader.nextStringOrNull();
                        break;
                    case 14:
                        sentryStackFrame.function = objectReader.nextStringOrNull();
                        break;
                    case 15:
                        sentryStackFrame.absPath = objectReader.nextStringOrNull();
                        break;
                    case 16:
                        sentryStackFrame.platform = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        break;
                }
            }
            sentryStackFrame.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return sentryStackFrame;
        }
    }
}
