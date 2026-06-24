package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzpn;
import java.util.HashMap;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzna extends zzmy {
    @Override // com.google.android.gms.measurement.internal.zzij, com.google.android.gms.measurement.internal.zzil
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzij, com.google.android.gms.measurement.internal.zzil
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzmy
    public final /* bridge */ /* synthetic */ zzu zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzij, com.google.android.gms.measurement.internal.zzil
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzmy
    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzij, com.google.android.gms.measurement.internal.zzil
    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzmy
    public final /* bridge */ /* synthetic */ zzgt zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzij, com.google.android.gms.measurement.internal.zzil
    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzmy
    public final /* bridge */ /* synthetic */ zzmc zzn() {
        return super.zzn();
    }

    public final zzmz zza(String str) {
        zzg zzgVarZze;
        if (zzpn.zza() && zze().zza(zzbf.zzbs)) {
            zzq();
            if (zznp.zzf(str)) {
                zzj().zzp().zza("sgtm feature flag enabled.");
                zzg zzgVarZze2 = zzh().zze(str);
                if (zzgVarZze2 == null) {
                    return new zzmz(zzb(str), 1);
                }
                String strZzad = zzgVarZze2.zzad();
                zzfi.zzd zzdVarZzc = zzm().zzc(str);
                if (zzdVarZzc == null || (zzgVarZze = zzh().zze(str)) == null || ((!zzdVarZzc.zzr() || zzdVarZzc.zzh().zza() != 100) && !zzq().zzd(str, zzgVarZze.zzam()) && (TextUtils.isEmpty(strZzad) || strZzad.hashCode() % 100 >= zzdVarZzc.zzh().zza()))) {
                    return new zzmz(zzb(str), 1);
                }
                zzmz zzmzVar = null;
                if (zzgVarZze2.zzat()) {
                    zzj().zzp().zza("sgtm upload enabled in manifest.");
                    zzfi.zzd zzdVarZzc2 = zzm().zzc(zzgVarZze2.zzac());
                    if (zzdVarZzc2 != null && zzdVarZzc2.zzr()) {
                        String strZze = zzdVarZzc2.zzh().zze();
                        if (!TextUtils.isEmpty(strZze)) {
                            String strZzd = zzdVarZzc2.zzh().zzd();
                            zzj().zzp().zza("sgtm configured with upload_url, server_info", strZze, TextUtils.isEmpty(strZzd) ? "Y" : "N");
                            if (TextUtils.isEmpty(strZzd)) {
                                zzmzVar = new zzmz(strZze, 3);
                            } else {
                                HashMap map = new HashMap();
                                map.put("x-sgtm-server-info", strZzd);
                                if (!TextUtils.isEmpty(zzgVarZze2.zzam())) {
                                    map.put("x-gtm-server-preview", zzgVarZze2.zzam());
                                }
                                zzmzVar = new zzmz(strZze, map, 3);
                            }
                        }
                    }
                }
                if (zzmzVar != null) {
                    return zzmzVar;
                }
            }
        }
        return new zzmz(zzb(str), 1);
    }

    @Override // com.google.android.gms.measurement.internal.zzmy
    public final /* bridge */ /* synthetic */ zzna zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzmy
    public final /* bridge */ /* synthetic */ zznl g_() {
        return super.g_();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    private final String zzb(String str) {
        String strZzf = zzm().zzf(str);
        if (!TextUtils.isEmpty(strZzf)) {
            Uri uri = Uri.parse(zzbf.zzq.zza(null));
            Uri.Builder builderBuildUpon = uri.buildUpon();
            builderBuildUpon.authority(strZzf + "." + uri.getAuthority());
            return builderBuildUpon.build().toString();
        }
        return zzbf.zzq.zza(null);
    }

    zzna(zznc zzncVar) {
        super(zzncVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzij
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }
}
