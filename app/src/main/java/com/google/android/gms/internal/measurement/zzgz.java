package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.internal.measurement.zzgv;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzgz<T> {
    private static final Object zza = new Object();

    @Nullable
    private static volatile zzhg zzb = null;
    private static volatile boolean zzc = false;
    private static zzhk zzd;
    private static final AtomicInteger zze;
    private final zzhh zzf;
    private final String zzg;
    private Object zzh;
    private volatile int zzi;
    private volatile T zzj;
    private final boolean zzk;
    private volatile boolean zzl;

    /* synthetic */ zzgz(zzhh zzhhVar, String str, Object obj, boolean z, zzhj zzhjVar) {
        this(zzhhVar, str, obj, true);
    }

    static /* synthetic */ boolean zzd() {
        return true;
    }

    abstract T zza(Object obj);

    static /* synthetic */ zzgz zza(zzhh zzhhVar, String str, Boolean bool, boolean z) {
        return new zzhc(zzhhVar, str, bool, true);
    }

    static /* synthetic */ zzgz zza(zzhh zzhhVar, String str, Double d, boolean z) {
        return new zzhf(zzhhVar, str, d, true);
    }

    static /* synthetic */ zzgz zza(zzhh zzhhVar, String str, Long l, boolean z) {
        return new zzhd(zzhhVar, str, l, true);
    }

    static /* synthetic */ zzgz zza(zzhh zzhhVar, String str, String str2, boolean z) {
        return new zzhe(zzhhVar, str, str2, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x007d A[Catch: all -> 0x0098, TryCatch #0 {, blocks: (B:56:0x001c, B:58:0x0020, B:60:0x0029, B:62:0x0039, B:66:0x0056, B:68:0x0061, B:81:0x0081, B:84:0x0089, B:85:0x008e, B:86:0x0092, B:71:0x0068, B:80:0x007d, B:74:0x006f, B:77:0x0076, B:87:0x0096), top: B:94:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T zza() {
        /*
            r8 = this;
            boolean r0 = r8.zzk
            if (r0 != 0) goto L11
            com.google.android.gms.internal.measurement.zzhk r0 = com.google.android.gms.internal.measurement.zzgz.zzd
            java.lang.String r1 = r8.zzg
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error."
            com.google.common.base.Preconditions.checkState(r0, r1)
        L11:
            java.util.concurrent.atomic.AtomicInteger r0 = com.google.android.gms.internal.measurement.zzgz.zze
            int r0 = r0.get()
            int r1 = r8.zzi
            if (r1 >= r0) goto L9b
            monitor-enter(r8)
            int r1 = r8.zzi     // Catch: java.lang.Throwable -> L98
            if (r1 >= r0) goto L96
            com.google.android.gms.internal.measurement.zzhg r1 = com.google.android.gms.internal.measurement.zzgz.zzb     // Catch: java.lang.Throwable -> L98
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch: java.lang.Throwable -> L98
            r3 = 0
            if (r1 == 0) goto L51
            com.google.common.base.Supplier r2 = r1.zzb()     // Catch: java.lang.Throwable -> L98
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> L98
            com.google.common.base.Optional r2 = (com.google.common.base.Optional) r2     // Catch: java.lang.Throwable -> L98
            boolean r4 = r2.isPresent()     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L51
            java.lang.Object r3 = r2.get()     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzgt r3 = (com.google.android.gms.internal.measurement.zzgt) r3     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhh r4 = r8.zzf     // Catch: java.lang.Throwable -> L98
            android.net.Uri r4 = r4.zzb     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhh r5 = r8.zzf     // Catch: java.lang.Throwable -> L98
            java.lang.String r5 = r5.zza     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhh r6 = r8.zzf     // Catch: java.lang.Throwable -> L98
            java.lang.String r6 = r6.zzd     // Catch: java.lang.Throwable -> L98
            java.lang.String r7 = r8.zzg     // Catch: java.lang.Throwable -> L98
            java.lang.String r3 = r3.zza(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L98
        L51:
            if (r1 == 0) goto L55
            r4 = 1
            goto L56
        L55:
            r4 = 0
        L56:
            java.lang.String r5 = "Must call PhenotypeFlagInitializer.maybeInit() first"
            com.google.common.base.Preconditions.checkState(r4, r5)     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhh r4 = r8.zzf     // Catch: java.lang.Throwable -> L98
            boolean r4 = r4.zzf     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L6f
            java.lang.Object r4 = r8.zza(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L68
            goto L81
        L68:
            java.lang.Object r4 = r8.zzb(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L7d
            goto L81
        L6f:
            java.lang.Object r4 = r8.zzb(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L76
            goto L81
        L76:
            java.lang.Object r4 = r8.zza(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L7d
            goto L81
        L7d:
            java.lang.Object r4 = r8.zze()     // Catch: java.lang.Throwable -> L98
        L81:
            boolean r1 = r2.isPresent()     // Catch: java.lang.Throwable -> L98
            if (r1 == 0) goto L92
            if (r3 != 0) goto L8e
            java.lang.Object r4 = r8.zze()     // Catch: java.lang.Throwable -> L98
            goto L92
        L8e:
            java.lang.Object r4 = r8.zza(r3)     // Catch: java.lang.Throwable -> L98
        L92:
            r8.zzj = r4     // Catch: java.lang.Throwable -> L98
            r8.zzi = r0     // Catch: java.lang.Throwable -> L98
        L96:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            goto L9b
        L98:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            throw r0
        L9b:
            T r0 = r8.zzj
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgz.zza():java.lang.Object");
    }

    private final T zze() {
        return (T) this.zzh;
    }

    @Nullable
    private final T zza(zzhg zzhgVar) {
        if (!this.zzf.zze && (this.zzf.zzh == null || this.zzf.zzh.apply(zzhgVar.zza()).booleanValue())) {
            Object objZza = zzgs.zza(zzhgVar.zza()).zza(this.zzf.zze ? null : zza(this.zzf.zzc));
            if (objZza != null) {
                return zza(objZza);
            }
        }
        return null;
    }

    @Nullable
    private final T zzb(zzhg zzhgVar) {
        zzgn zzgnVarZza;
        Object objZza;
        if (this.zzf.zzb != null) {
            if (!zzgx.zza(zzhgVar.zza(), this.zzf.zzb)) {
                zzgnVarZza = null;
            } else if (this.zzf.zzg) {
                zzgnVarZza = zzgk.zza(zzhgVar.zza().getContentResolver(), zzgw.zza(zzgw.zza(zzhgVar.zza(), this.zzf.zzb.getLastPathSegment())), new Runnable() { // from class: com.google.android.gms.internal.measurement.zzgy
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzgz.zzc();
                    }
                });
            } else {
                zzgnVarZza = zzgk.zza(zzhgVar.zza().getContentResolver(), this.zzf.zzb, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzgy
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzgz.zzc();
                    }
                });
            }
        } else {
            zzgnVarZza = zzhi.zza(zzhgVar.zza(), this.zzf.zza, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzgy
                @Override // java.lang.Runnable
                public final void run() {
                    zzgz.zzc();
                }
            });
        }
        if (zzgnVarZza == null || (objZza = zzgnVarZza.zza(zzb())) == null) {
            return null;
        }
        return zza(objZza);
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        return str + this.zzg;
    }

    static {
        new AtomicReference();
        zzd = new zzhk(new zzhn() { // from class: com.google.android.gms.internal.measurement.zzha
            @Override // com.google.android.gms.internal.measurement.zzhn
            public final boolean zza() {
                return zzgz.zzd();
            }
        });
        zze = new AtomicInteger();
    }

    /* JADX INFO: Access modifiers changed from: private */
    zzgz(zzhh zzhhVar, String str, T t, boolean z) {
        this.zzi = -1;
        if (zzhhVar.zza == null && zzhhVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (zzhhVar.zza != null && zzhhVar.zzb != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.zzf = zzhhVar;
        this.zzg = str;
        this.zzh = t;
        this.zzk = z;
        this.zzl = false;
    }

    public static void zzc() {
        zze.incrementAndGet();
    }

    public static void zzb(final Context context) {
        if (zzb != null || context == null) {
            return;
        }
        Object obj = zza;
        synchronized (obj) {
            if (zzb == null && context != null) {
                synchronized (obj) {
                    zzhg zzhgVar = zzb;
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    if (zzhgVar == null || zzhgVar.zza() != context) {
                        if (zzhgVar != null) {
                            zzgk.zzc();
                            zzhi.zza();
                            zzgs.zza();
                        }
                        zzb = new zzgh(context, Suppliers.memoize(new Supplier() { // from class: com.google.android.gms.internal.measurement.zzhb
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return zzgv.zza.zza(context);
                            }
                        }));
                        zze.incrementAndGet();
                    }
                }
            }
        }
    }
}
