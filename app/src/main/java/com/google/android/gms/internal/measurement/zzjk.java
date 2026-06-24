package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjk;
import com.google.android.gms.internal.measurement.zzjk.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzjk<MessageType extends zzjk<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzhq<MessageType, BuilderType> {
    private static Map<Object, zzjk<?, ?>> zzc = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzme zzb = zzme.zzc();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    protected static class zza<T extends zzjk<T, ?>> extends zzhr<T> {
        public zza(T t) {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    public static abstract class zzb<MessageType extends zzjk<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzhp<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;

        @Override // com.google.android.gms.internal.measurement.zzhp
        /* JADX INFO: renamed from: zzaf */
        public final /* synthetic */ zzhp clone() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.measurement.zzhp
        /* JADX INFO: renamed from: zza */
        public final /* synthetic */ zzhp zzb(zzio zzioVar, zzix zzixVar) throws IOException {
            return (zzb) zzb(zzioVar, zzixVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzhp
        public final /* synthetic */ zzhp zza(byte[] bArr, int i, int i2) throws zzjs {
            return zzb(bArr, 0, i2, zzix.zza);
        }

        @Override // com.google.android.gms.internal.measurement.zzhp
        public final /* synthetic */ zzhp zza(byte[] bArr, int i, int i2, zzix zzixVar) throws zzjs {
            return zzb(bArr, 0, i2, zzixVar);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb.equals(messagetype)) {
                return this;
            }
            if (!this.zza.zzcn()) {
                zzal();
            }
            zza(this.zza, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.measurement.zzhp
        /* JADX INFO: renamed from: zzc */
        public final BuilderType zzb(zzio zzioVar, zzix zzixVar) throws IOException {
            if (!this.zza.zzcn()) {
                zzal();
            }
            try {
                zzlh.zza().zza(this.zza).zza(this.zza, zzis.zza(zzioVar), zzixVar);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzix zzixVar) throws zzjs {
            if (!this.zza.zzcn()) {
                zzal();
            }
            try {
                zzlh.zza().zza(this.zza).zza(this.zza, bArr, 0, i2, new zzhv(zzixVar));
                return this;
            } catch (zzjs e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw zzjs.zzh();
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzks
        /* JADX INFO: renamed from: zzag */
        public final MessageType zzai() {
            MessageType messagetype = (MessageType) zzaj();
            if (zzjk.zza(messagetype, true)) {
                return messagetype;
            }
            throw new zzmc(messagetype);
        }

        @Override // com.google.android.gms.internal.measurement.zzks
        /* JADX INFO: renamed from: zzah */
        public MessageType zzaj() {
            if (!this.zza.zzcn()) {
                return this.zza;
            }
            this.zza.zzck();
            return this.zza;
        }

        @Override // com.google.android.gms.internal.measurement.zzkv
        public final /* synthetic */ zzkt zzcj() {
            return this.zzb;
        }

        @Override // com.google.android.gms.internal.measurement.zzhp
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.zzb.zza(zze.zze, null, null);
            zzbVar.zza = (MessageType) zzaj();
            return zzbVar;
        }

        protected zzb(MessageType messagetype) {
            this.zzb = messagetype;
            if (messagetype.zzcn()) {
                throw new IllegalArgumentException("Default instance must be immutable.");
            }
            this.zza = (MessageType) messagetype.zzcd();
        }

        protected final void zzak() {
            if (this.zza.zzcn()) {
                return;
            }
            zzal();
        }

        protected void zzal() {
            MessageType messagetype = (MessageType) this.zzb.zzcd();
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        private static <MessageType> void zza(MessageType messagetype, MessageType messagetype2) {
            zzlh.zza().zza(messagetype).zza(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.measurement.zzkv
        public final boolean zzcm() {
            return zzjk.zza(this.zza, false);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    static final class zzc implements zzjf<zzc> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final zzks zza(zzks zzksVar, zzkt zzktVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final zzky zza(zzky zzkyVar, zzky zzkyVar2) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final zzmn zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final zzmx zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        public final boolean zze() {
            throw new NoSuchMethodError();
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzjk<MessageType, BuilderType> implements zzkv {
        protected zzjd<zzc> zzc = zzjd.zzb();

        final zzjd<zzc> zza() {
            if (this.zzc.zzf()) {
                this.zzc = (zzjd) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    public static class zzf<ContainingType extends zzkt, Type> extends zziy<ContainingType, Type> {
    }

    private final int zza() {
        return zzlh.zza().zza(this).zzb(this);
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    private final int zzb(zzll<?> zzllVar) {
        if (zzllVar == null) {
            return zzlh.zza().zza(this).zza(this);
        }
        return zzllVar.zza(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzhq
    final int zzbx() {
        return this.zzd & Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final int zzca() {
        return zza((zzll) null);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    public enum zze {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhq
    final int zza(zzll zzllVar) {
        if (zzcn()) {
            int iZzb = zzb(zzllVar);
            if (iZzb >= 0) {
                return iZzb;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + iZzb);
        }
        if (zzbx() != Integer.MAX_VALUE) {
            return zzbx();
        }
        int iZzb2 = zzb(zzllVar);
        zzc(iZzb2);
        return iZzb2;
    }

    public int hashCode() {
        if (zzcn()) {
            return zza();
        }
        if (this.zza == 0) {
            this.zza = zza();
        }
        return this.zza;
    }

    protected final <MessageType extends zzjk<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzcb() {
        return (BuilderType) zza(zze.zze, (Object) null, (Object) null);
    }

    public final BuilderType zzcc() {
        return (BuilderType) ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza(this);
    }

    static <T extends zzjk<?, ?>> T zza(Class<T> cls) {
        T t = (T) zzc.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (T) zzc.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        T t2 = (T) ((zzjk) zzmg.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
        if (t2 == null) {
            throw new IllegalStateException();
        }
        zzc.put(cls, t2);
        return t2;
    }

    final MessageType zzcd() {
        return (MessageType) zza(zze.zzd, (Object) null, (Object) null);
    }

    protected static zzjr zzce() {
        return zzjn.zzd();
    }

    protected static zzjq zzcf() {
        return zzke.zzd();
    }

    protected static zzjq zza(zzjq zzjqVar) {
        int size = zzjqVar.size();
        return zzjqVar.zza(size == 0 ? 10 : size << 1);
    }

    protected static <E> zzjt<E> zzcg() {
        return zzlg.zzd();
    }

    protected static <E> zzjt<E> zza(zzjt<E> zzjtVar) {
        int size = zzjtVar.size();
        return zzjtVar.zza(size == 0 ? 10 : size << 1);
    }

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final /* synthetic */ zzks zzch() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final /* synthetic */ zzks zzci() {
        return ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final /* synthetic */ zzkt zzcj() {
        return (zzjk) zza(zze.zzf, (Object) null, (Object) null);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zza(zzkt zzktVar, String str, Object[] objArr) {
        return new zzlj(zzktVar, str, objArr);
    }

    public String toString() {
        return zzku.zza(this, super.toString());
    }

    protected final void zzck() {
        zzlh.zza().zza(this).zzc(this);
        zzcl();
    }

    final void zzcl() {
        this.zzd &= Integer.MAX_VALUE;
    }

    protected static <T extends zzjk<?, ?>> void zza(Class<T> cls, T t) {
        t.zzcl();
        zzc.put(cls, t);
    }

    @Override // com.google.android.gms.internal.measurement.zzhq
    final void zzc(int i) {
        if (i < 0) {
            throw new IllegalStateException("serialized size must be non-negative, was " + i);
        }
        this.zzd = (i & Integer.MAX_VALUE) | (this.zzd & Integer.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final void zza(zzit zzitVar) throws IOException {
        zzlh.zza().zza(this).zza(this, zziw.zza(zzitVar));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzlh.zza().zza(this).zzb(this, (zzjk) obj);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final boolean zzcm() {
        return zza(this, true);
    }

    protected static final <T extends zzjk<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zza(zze.zza, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzd = zzlh.zza().zza(t).zzd(t);
        if (z) {
            t.zza(zze.zzb, zZzd ? t : null, null);
        }
        return zZzd;
    }

    final boolean zzcn() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }
}
