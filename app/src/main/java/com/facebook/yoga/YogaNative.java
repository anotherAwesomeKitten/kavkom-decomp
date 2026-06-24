package com.facebook.yoga;

import com.facebook.react.uimanager.ViewProps;
import com.facebook.soloader.SoLoader;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.SentryEvent;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: YogaNative.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0003\b\u0082\u0001\n\u0002\u0010\u0014\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u0087 J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J!\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0087 J\u0019\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\rH\u0087 J\u0019\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0087 J\u0019\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000bH\u0087 J\u0011\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0087 J\t\u0010\u0019\u001a\u00020\u0005H\u0087 J\u0011\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0087 J\u0011\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J!\u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u000bH\u0087 J!\u0010!\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u000bH\u0087 J\u0019\u0010\"\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\rH\u0087 J\u0011\u0010$\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010%\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010&\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0087 J<\u0010'\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0087 ¢\u0006\u0002\u0010/J\u0011\u00100\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u00101\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0005H\u0087 J\u0011\u00105\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u00106\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u000bH\u0087 J\u0011\u00108\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u00109\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u000bH\u0087 J\u0011\u0010;\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010<\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u000bH\u0087 J\u0011\u0010>\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010?\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u000bH\u0087 J\u0011\u0010A\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010B\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u000bH\u0087 J\u0011\u0010D\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010E\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u000bH\u0087 J\u0011\u0010G\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010H\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010I\u001a\u00020\u000bH\u0087 J\u0011\u0010J\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010K\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u000bH\u0087 J\u0011\u0010M\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010N\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u000bH\u0087 J\u0011\u0010P\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010Q\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010R\u001a\u00020\u000bH\u0087 J\u0011\u0010S\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010T\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010U\u001a\u00020\u000bH\u0087 J\u0011\u0010V\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010W\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u0012H\u0087 J\u0011\u0010Y\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010Z\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010[\u001a\u00020\u0012H\u0087 J\u0011\u0010\\\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010]\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010^\u001a\u00020\u0012H\u0087 J\u0011\u0010_\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010`\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010a\u001a\u00020\u0012H\u0087 J\u0019\u0010b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0011\u0010d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010g\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010h\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000bH\u0087 J!\u0010j\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010k\u001a\u00020\u0012H\u0087 J!\u0010l\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0019\u0010m\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000bH\u0087 J\u0019\u0010n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000bH\u0087 J!\u0010o\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u0012H\u0087 J!\u0010q\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0019\u0010r\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000bH\u0087 J!\u0010s\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010t\u001a\u00020\u0012H\u0087 J\u0019\u0010u\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000bH\u0087 J!\u0010v\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010w\u001a\u00020\u0012H\u0087 J!\u0010x\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0019\u0010y\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u000bH\u0087 J\u0011\u0010z\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0019\u0010{\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0012H\u0087 J\u0019\u0010|\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0011\u0010}\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010~\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0011\u0010\u007f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0080\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0081\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001a\u0010\u0082\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0012H\u0087 J\u001a\u0010\u0083\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0012\u0010\u0084\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0085\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0086\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0087\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0088\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001b\u0010\u0089\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010\u008a\u0001\u001a\u00020\u0012H\u0087 J\u001a\u0010\u008b\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0012\u0010\u008c\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u008d\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u008e\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u008f\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001b\u0010\u0090\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010\u0091\u0001\u001a\u00020\u0012H\u0087 J\u001a\u0010\u0092\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0012\u0010\u0093\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0094\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0095\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u0096\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001b\u0010\u0097\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010\u0098\u0001\u001a\u00020\u0012H\u0087 J\u001a\u0010\u0099\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0012\u0010\u009a\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u009b\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u009c\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010\u009d\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001b\u0010\u009e\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010\u009f\u0001\u001a\u00020\u0012H\u0087 J\u001a\u0010 \u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u0012H\u0087 J\u0012\u0010¡\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010¢\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010£\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u0012\u0010¤\u0001\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001b\u0010¥\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010¦\u0001\u001a\u00020\u0012H\u0087 J\u001b\u0010§\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u000bH\u0087 J$\u0010©\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u000b2\u0007\u0010ª\u0001\u001a\u00020\u0012H\u0087 J$\u0010«\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u000b2\u0007\u0010ª\u0001\u001a\u00020\u0012H\u0087 J\u001b\u0010¬\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010\u00ad\u0001\u001a\u00020\rH\u0087 J\u001b\u0010®\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010\u00ad\u0001\u001a\u00020\rH\u0087 J%\u0010¯\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\u0010°\u0001\u001a\u00030±\u00012\u0007\u0010²\u0001\u001a\u00020\u000bH\u0087 J\u0012\u0010³\u0001\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\u001b\u0010´\u0001\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0007\u0010µ\u0001\u001a\u00020\rH\u0087 ¨\u0006¶\u0001"}, d2 = {"Lcom/facebook/yoga/YogaNative;", "", "<init>", "()V", "jni_YGConfigNewJNI", "", "jni_YGConfigFreeJNI", "", "nativePointer", "jni_YGConfigSetExperimentalFeatureEnabledJNI", "feature", "", ViewProps.ENABLED, "", "jni_YGConfigSetUseWebDefaultsJNI", "useWebDefaults", "jni_YGConfigSetPointScaleFactorJNI", "pixelsInPoint", "", "jni_YGConfigSetErrataJNI", "errata", "jni_YGConfigGetErrataJNI", "jni_YGConfigSetLoggerJNI", SentryEvent.JsonKeys.LOGGER, "Lcom/facebook/yoga/YogaLogger;", "jni_YGNodeNewJNI", "jni_YGNodeNewWithConfigJNI", "configPointer", "jni_YGNodeFinalizeJNI", "jni_YGNodeResetJNI", "jni_YGNodeInsertChildJNI", "childPointer", FirebaseAnalytics.Param.INDEX, "jni_YGNodeSwapChildJNI", "jni_YGNodeSetIsReferenceBaselineJNI", "isReferenceBaseline", "jni_YGNodeIsReferenceBaselineJNI", "jni_YGNodeRemoveAllChildrenJNI", "jni_YGNodeRemoveChildJNI", "jni_YGNodeCalculateLayoutJNI", "width", "height", "nativePointers", "", "nodes", "", "Lcom/facebook/yoga/YogaNodeJNIBase;", "(JFF[J[Lcom/facebook/yoga/YogaNodeJNIBase;)V", "jni_YGNodeMarkDirtyJNI", "jni_YGNodeIsDirtyJNI", "jni_YGNodeCopyStyleJNI", "dstNativePointer", "srcNativePointer", "jni_YGNodeStyleGetDirectionJNI", "jni_YGNodeStyleSetDirectionJNI", "direction", "jni_YGNodeStyleGetFlexDirectionJNI", "jni_YGNodeStyleSetFlexDirectionJNI", ViewProps.FLEX_DIRECTION, "jni_YGNodeStyleGetJustifyContentJNI", "jni_YGNodeStyleSetJustifyContentJNI", ViewProps.JUSTIFY_CONTENT, "jni_YGNodeStyleGetAlignItemsJNI", "jni_YGNodeStyleSetAlignItemsJNI", ViewProps.ALIGN_ITEMS, "jni_YGNodeStyleGetAlignSelfJNI", "jni_YGNodeStyleSetAlignSelfJNI", ViewProps.ALIGN_SELF, "jni_YGNodeStyleGetAlignContentJNI", "jni_YGNodeStyleSetAlignContentJNI", ViewProps.ALIGN_CONTENT, "jni_YGNodeStyleGetPositionTypeJNI", "jni_YGNodeStyleSetPositionTypeJNI", "positionType", "jni_YGNodeStyleGetBoxSizingJNI", "jni_YGNodeStyleSetBoxSizingJNI", "boxSizing", "jni_YGNodeStyleGetFlexWrapJNI", "jni_YGNodeStyleSetFlexWrapJNI", "wrapType", "jni_YGNodeStyleGetOverflowJNI", "jni_YGNodeStyleSetOverflowJNI", ViewProps.OVERFLOW, "jni_YGNodeStyleGetDisplayJNI", "jni_YGNodeStyleSetDisplayJNI", "display", "jni_YGNodeStyleGetFlexJNI", "jni_YGNodeStyleSetFlexJNI", ViewProps.FLEX, "jni_YGNodeStyleGetFlexGrowJNI", "jni_YGNodeStyleSetFlexGrowJNI", ViewProps.FLEX_GROW, "jni_YGNodeStyleGetFlexShrinkJNI", "jni_YGNodeStyleSetFlexShrinkJNI", ViewProps.FLEX_SHRINK, "jni_YGNodeStyleGetFlexBasisJNI", "jni_YGNodeStyleSetFlexBasisJNI", ViewProps.FLEX_BASIS, "jni_YGNodeStyleSetFlexBasisPercentJNI", ProfileMeasurement.UNIT_PERCENT, "jni_YGNodeStyleSetFlexBasisAutoJNI", "jni_YGNodeStyleSetFlexBasisMaxContentJNI", "jni_YGNodeStyleSetFlexBasisFitContentJNI", "jni_YGNodeStyleSetFlexBasisStretchJNI", "jni_YGNodeStyleGetMarginJNI", "edge", "jni_YGNodeStyleSetMarginJNI", ViewProps.MARGIN, "jni_YGNodeStyleSetMarginPercentJNI", "jni_YGNodeStyleSetMarginAutoJNI", "jni_YGNodeStyleGetPaddingJNI", "jni_YGNodeStyleSetPaddingJNI", ViewProps.PADDING, "jni_YGNodeStyleSetPaddingPercentJNI", "jni_YGNodeStyleGetBorderJNI", "jni_YGNodeStyleSetBorderJNI", "border", "jni_YGNodeStyleGetPositionJNI", "jni_YGNodeStyleSetPositionJNI", ViewProps.POSITION, "jni_YGNodeStyleSetPositionPercentJNI", "jni_YGNodeStyleSetPositionAutoJNI", "jni_YGNodeStyleGetWidthJNI", "jni_YGNodeStyleSetWidthJNI", "jni_YGNodeStyleSetWidthPercentJNI", "jni_YGNodeStyleSetWidthAutoJNI", "jni_YGNodeStyleSetWidthMaxContentJNI", "jni_YGNodeStyleSetWidthFitContentJNI", "jni_YGNodeStyleSetWidthStretchJNI", "jni_YGNodeStyleGetHeightJNI", "jni_YGNodeStyleSetHeightJNI", "jni_YGNodeStyleSetHeightPercentJNI", "jni_YGNodeStyleSetHeightAutoJNI", "jni_YGNodeStyleSetHeightMaxContentJNI", "jni_YGNodeStyleSetHeightFitContentJNI", "jni_YGNodeStyleSetHeightStretchJNI", "jni_YGNodeStyleGetMinWidthJNI", "jni_YGNodeStyleSetMinWidthJNI", ViewProps.MIN_WIDTH, "jni_YGNodeStyleSetMinWidthPercentJNI", "jni_YGNodeStyleSetMinWidthMaxContentJNI", "jni_YGNodeStyleSetMinWidthFitContentJNI", "jni_YGNodeStyleSetMinWidthStretchJNI", "jni_YGNodeStyleGetMinHeightJNI", "jni_YGNodeStyleSetMinHeightJNI", ViewProps.MIN_HEIGHT, "jni_YGNodeStyleSetMinHeightPercentJNI", "jni_YGNodeStyleSetMinHeightMaxContentJNI", "jni_YGNodeStyleSetMinHeightFitContentJNI", "jni_YGNodeStyleSetMinHeightStretchJNI", "jni_YGNodeStyleGetMaxWidthJNI", "jni_YGNodeStyleSetMaxWidthJNI", ViewProps.MAX_WIDTH, "jni_YGNodeStyleSetMaxWidthPercentJNI", "jni_YGNodeStyleSetMaxWidthMaxContentJNI", "jni_YGNodeStyleSetMaxWidthFitContentJNI", "jni_YGNodeStyleSetMaxWidthStretchJNI", "jni_YGNodeStyleGetMaxHeightJNI", "jni_YGNodeStyleSetMaxHeightJNI", "maxheight", "jni_YGNodeStyleSetMaxHeightPercentJNI", "jni_YGNodeStyleSetMaxHeightMaxContentJNI", "jni_YGNodeStyleSetMaxHeightFitContentJNI", "jni_YGNodeStyleSetMaxHeightStretchJNI", "jni_YGNodeStyleGetAspectRatioJNI", "jni_YGNodeStyleSetAspectRatioJNI", ViewProps.ASPECT_RATIO, "jni_YGNodeStyleGetGapJNI", "gutter", "jni_YGNodeStyleSetGapJNI", "gapLength", "jni_YGNodeStyleSetGapPercentJNI", "jni_YGNodeSetHasMeasureFuncJNI", "hasMeasureFunc", "jni_YGNodeSetHasBaselineFuncJNI", "jni_YGNodeSetStyleInputsJNI", "styleInputsArray", "", RRWebVideoEvent.JsonKeys.SIZE, "jni_YGNodeCloneJNI", "jni_YGNodeSetAlwaysFormsContainingBlockJNI", "alwaysFormContainingBlock", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaNative {
    public static final YogaNative INSTANCE = new YogaNative();

    @JvmStatic
    public static final native void jni_YGConfigFreeJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGConfigGetErrataJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGConfigNewJNI();

    @JvmStatic
    public static final native void jni_YGConfigSetErrataJNI(long nativePointer, int errata);

    @JvmStatic
    public static final native void jni_YGConfigSetExperimentalFeatureEnabledJNI(long nativePointer, int feature, boolean z);

    @JvmStatic
    public static final native void jni_YGConfigSetLoggerJNI(long nativePointer, YogaLogger yogaLogger);

    @JvmStatic
    public static final native void jni_YGConfigSetPointScaleFactorJNI(long nativePointer, float pixelsInPoint);

    @JvmStatic
    public static final native void jni_YGConfigSetUseWebDefaultsJNI(long nativePointer, boolean useWebDefaults);

    @JvmStatic
    public static final native void jni_YGNodeCalculateLayoutJNI(long nativePointer, float width, float height, long[] nativePointers, YogaNodeJNIBase[] nodes);

    @JvmStatic
    public static final native long jni_YGNodeCloneJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeCopyStyleJNI(long dstNativePointer, long srcNativePointer);

    @JvmStatic
    public static final native void jni_YGNodeFinalizeJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeInsertChildJNI(long nativePointer, long childPointer, int i);

    @JvmStatic
    public static final native boolean jni_YGNodeIsDirtyJNI(long nativePointer);

    @JvmStatic
    public static final native boolean jni_YGNodeIsReferenceBaselineJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeMarkDirtyJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeNewJNI();

    @JvmStatic
    public static final native long jni_YGNodeNewWithConfigJNI(long configPointer);

    @JvmStatic
    public static final native void jni_YGNodeRemoveAllChildrenJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeRemoveChildJNI(long nativePointer, long childPointer);

    @JvmStatic
    public static final native void jni_YGNodeResetJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeSetAlwaysFormsContainingBlockJNI(long nativePointer, boolean alwaysFormContainingBlock);

    @JvmStatic
    public static final native void jni_YGNodeSetHasBaselineFuncJNI(long nativePointer, boolean hasMeasureFunc);

    @JvmStatic
    public static final native void jni_YGNodeSetHasMeasureFuncJNI(long nativePointer, boolean hasMeasureFunc);

    @JvmStatic
    public static final native void jni_YGNodeSetIsReferenceBaselineJNI(long nativePointer, boolean isReferenceBaseline);

    @JvmStatic
    public static final native void jni_YGNodeSetStyleInputsJNI(long nativePointer, float[] styleInputsArray, int i);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetAlignContentJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetAlignItemsJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetAlignSelfJNI(long nativePointer);

    @JvmStatic
    public static final native float jni_YGNodeStyleGetAspectRatioJNI(long nativePointer);

    @JvmStatic
    public static final native float jni_YGNodeStyleGetBorderJNI(long nativePointer, int edge);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetBoxSizingJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetDirectionJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetDisplayJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetFlexBasisJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetFlexDirectionJNI(long nativePointer);

    @JvmStatic
    public static final native float jni_YGNodeStyleGetFlexGrowJNI(long nativePointer);

    @JvmStatic
    public static final native float jni_YGNodeStyleGetFlexJNI(long nativePointer);

    @JvmStatic
    public static final native float jni_YGNodeStyleGetFlexShrinkJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetFlexWrapJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetGapJNI(long nativePointer, int gutter);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetHeightJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetJustifyContentJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetMarginJNI(long nativePointer, int edge);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetMaxHeightJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetMaxWidthJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetMinHeightJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetMinWidthJNI(long nativePointer);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetOverflowJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetPaddingJNI(long nativePointer, int edge);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetPositionJNI(long nativePointer, int edge);

    @JvmStatic
    public static final native int jni_YGNodeStyleGetPositionTypeJNI(long nativePointer);

    @JvmStatic
    public static final native long jni_YGNodeStyleGetWidthJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetAlignContentJNI(long nativePointer, int i);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetAlignItemsJNI(long nativePointer, int i);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetAlignSelfJNI(long nativePointer, int i);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetAspectRatioJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetBorderJNI(long nativePointer, int edge, float border);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetBoxSizingJNI(long nativePointer, int boxSizing);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetDirectionJNI(long nativePointer, int direction);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetDisplayJNI(long nativePointer, int display);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexBasisAutoJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexBasisFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexBasisJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexBasisMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexBasisPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexBasisStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexDirectionJNI(long nativePointer, int i);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexGrowJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexShrinkJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetFlexWrapJNI(long nativePointer, int wrapType);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetGapJNI(long nativePointer, int gutter, float gapLength);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetGapPercentJNI(long nativePointer, int gutter, float gapLength);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetHeightAutoJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetHeightFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetHeightJNI(long nativePointer, float height);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetHeightMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetHeightPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetHeightStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetJustifyContentJNI(long nativePointer, int i);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMarginAutoJNI(long nativePointer, int edge);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMarginJNI(long nativePointer, int edge, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMarginPercentJNI(long nativePointer, int edge, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxHeightFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxHeightJNI(long nativePointer, float maxheight);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxHeightMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxHeightPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxHeightStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxWidthFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxWidthJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxWidthMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxWidthPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMaxWidthStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinHeightFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinHeightJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinHeightMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinHeightPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinHeightStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinWidthFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinWidthJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinWidthMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinWidthPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetMinWidthStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetOverflowJNI(long nativePointer, int i);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetPaddingJNI(long nativePointer, int edge, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetPaddingPercentJNI(long nativePointer, int edge, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetPositionAutoJNI(long nativePointer, int edge);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetPositionJNI(long nativePointer, int edge, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetPositionPercentJNI(long nativePointer, int edge, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetPositionTypeJNI(long nativePointer, int positionType);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetWidthAutoJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetWidthFitContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetWidthJNI(long nativePointer, float width);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetWidthMaxContentJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetWidthPercentJNI(long nativePointer, float f);

    @JvmStatic
    public static final native void jni_YGNodeStyleSetWidthStretchJNI(long nativePointer);

    @JvmStatic
    public static final native void jni_YGNodeSwapChildJNI(long nativePointer, long childPointer, int i);

    private YogaNative() {
    }

    static {
        SoLoader.loadLibrary("yoga");
    }
}
