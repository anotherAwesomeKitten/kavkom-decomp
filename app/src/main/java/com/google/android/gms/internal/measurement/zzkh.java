package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkh implements zzlk {
    private static final zzkq zza = new zzkg();
    private final zzkq zzb;

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final <T> zzll<T> zza(Class<T> cls) {
        zzln.zza((Class<?>) cls);
        zzkr zzkrVarZza = this.zzb.zza(cls);
        if (zzkrVarZza.zzc()) {
            return zzkz.zza(zzln.zza(), zzja.zza(), zzkrVarZza.zza());
        }
        return zzkx.zza(cls, zzkrVarZza, zzld.zza(), zzkf.zza(), zzln.zza(), zzkj.zza[zzkrVarZza.zzb().ordinal()] != 1 ? zzja.zza() : null, zzko.zza());
    }

    public zzkh() {
        this(new zzki(zzjl.zza(), zza));
    }

    private zzkh(zzkq zzkqVar) {
        this.zzb = (zzkq) zzjm.zza(zzkqVar, "messageInfoFactory");
    }
}
