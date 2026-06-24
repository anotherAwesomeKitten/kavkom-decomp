package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzoj;
import com.google.android.gms.internal.measurement.zzph;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkn extends zzmx {
    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    @Override // com.google.android.gms.measurement.internal.zzmx
    protected final boolean zzc() {
        return false;
    }

    public zzkn(zznc zzncVar) {
        super(zzncVar);
    }

    public final byte[] zza(zzbd zzbdVar, String str) {
        zznq next;
        List<zznq> list;
        zzfn.zzj.zzb zzbVar;
        Bundle bundle;
        byte[] bArr;
        zzg zzgVar;
        zzfn.zzk.zza zzaVar;
        zzaz zzazVarZza;
        long j;
        zzt();
        this.zzu.zzy();
        Preconditions.checkNotNull(zzbdVar);
        Preconditions.checkNotEmpty(str);
        if (!zze().zze(str, zzbf.zzbg)) {
            zzj().zzc().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        }
        if (!"_iap".equals(zzbdVar.zza) && !"_iapx".equals(zzbdVar.zza)) {
            zzj().zzc().zza("Generating a payload for this event is not available. package_name, event_name", str, zzbdVar.zza);
            return null;
        }
        zzfn.zzj.zzb zzbVarZzb = zzfn.zzj.zzb();
        zzh().zzp();
        try {
            zzg zzgVarZze = zzh().zze(str);
            if (zzgVarZze == null) {
                zzj().zzc().zza("Log and bundle not available. package_name", str);
                return new byte[0];
            }
            if (!zzgVarZze.zzar()) {
                zzj().zzc().zza("Log and bundle disabled. package_name", str);
                return new byte[0];
            }
            zzfn.zzk.zza zzaVarZzp = zzfn.zzk.zzw().zzh(1).zzp("android");
            if (!TextUtils.isEmpty(zzgVarZze.zzac())) {
                zzaVarZzp.zzb(zzgVarZze.zzac());
            }
            if (!TextUtils.isEmpty(zzgVarZze.zzae())) {
                zzaVarZzp.zzd((String) Preconditions.checkNotNull(zzgVarZze.zzae()));
            }
            if (!TextUtils.isEmpty(zzgVarZze.zzaf())) {
                zzaVarZzp.zze((String) Preconditions.checkNotNull(zzgVarZze.zzaf()));
            }
            if (zzgVarZze.zze() != -2147483648L) {
                zzaVarZzp.zze((int) zzgVarZze.zze());
            }
            zzaVarZzp.zzf(zzgVarZze.zzq()).zzd(zzgVarZze.zzo());
            String strZzah = zzgVarZze.zzah();
            String strZzaa = zzgVarZze.zzaa();
            if (!TextUtils.isEmpty(strZzah)) {
                zzaVarZzp.zzm(strZzah);
            } else if (!TextUtils.isEmpty(strZzaa)) {
                zzaVarZzp.zza(strZzaa);
            }
            zzaVarZzp.zzj(zzgVarZze.zzw());
            zzin zzinVarZzb = this.zzf.zzb(str);
            zzaVarZzp.zzc(zzgVarZze.zzn());
            if (this.zzu.zzac() && zze().zzj(zzaVarZzp.zzt()) && zzinVarZzb.zzi() && !TextUtils.isEmpty(null)) {
                zzaVarZzp.zzj((String) null);
            }
            zzaVarZzp.zzg(zzinVarZzb.zzg());
            if (zzinVarZzb.zzi() && zzgVarZze.zzaq()) {
                Pair<String, Boolean> pairZza = zzn().zza(zzgVarZze.zzac(), zzinVarZzb);
                if (zzgVarZze.zzaq() && pairZza != null && !TextUtils.isEmpty((CharSequence) pairZza.first)) {
                    zzaVarZzp.zzq(zza((String) pairZza.first, Long.toString(zzbdVar.zzd)));
                    if (pairZza.second != null) {
                        zzaVarZzp.zzc(((Boolean) pairZza.second).booleanValue());
                    }
                }
            }
            zzf().zzac();
            zzfn.zzk.zza zzaVarZzi = zzaVarZzp.zzi(Build.MODEL);
            zzf().zzac();
            zzaVarZzi.zzo(Build.VERSION.RELEASE).zzj((int) zzf().zzg()).zzs(zzf().zzh());
            if (zzinVarZzb.zzj() && zzgVarZze.zzad() != null) {
                zzaVarZzp.zzc(zza((String) Preconditions.checkNotNull(zzgVarZze.zzad()), Long.toString(zzbdVar.zzd)));
            }
            if (!TextUtils.isEmpty(zzgVarZze.zzag())) {
                zzaVarZzp.zzl((String) Preconditions.checkNotNull(zzgVarZze.zzag()));
            }
            String strZzac = zzgVarZze.zzac();
            List<zznq> listZzk = zzh().zzk(strZzac);
            Iterator<zznq> it = listZzk.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if ("_lte".equals(next.zzc)) {
                    break;
                }
            }
            if (next == null || next.zze == null) {
                list = listZzk;
                zznq zznqVar = new zznq(strZzac, "auto", "_lte", zzb().currentTimeMillis(), 0L);
                list.add(zznqVar);
                zzh().zza(zznqVar);
            } else {
                list = listZzk;
            }
            zzfn.zzo[] zzoVarArr = new zzfn.zzo[list.size()];
            for (int i = 0; i < list.size(); i++) {
                zzfn.zzo.zza zzaVarZzb = zzfn.zzo.zze().zza(list.get(i).zzc).zzb(list.get(i).zzd);
                g_().zza(zzaVarZzb, list.get(i).zze);
                zzoVarArr[i] = (zzfn.zzo) ((com.google.android.gms.internal.measurement.zzjk) zzaVarZzb.zzai());
            }
            zzaVarZzp.zze(Arrays.asList(zzoVarArr));
            g_().zza(zzaVarZzp);
            this.zzf.zza(zzgVarZze, zzaVarZzp);
            if (zzoj.zza() && zze().zza(zzbf.zzcm)) {
                this.zzf.zzb(zzgVarZze, zzaVarZzp);
            }
            zzga zzgaVarZza = zzga.zza(zzbdVar);
            zzq().zza(zzgaVarZza.zzb, zzh().zzd(str));
            zzq().zza(zzgaVarZza, zze().zzb(str));
            Bundle bundle2 = zzgaVarZza.zzb;
            bundle2.putLong("_c", 1L);
            zzj().zzc().zza("Marking in-app purchase as real-time");
            bundle2.putLong("_r", 1L);
            bundle2.putString("_o", zzbdVar.zzc);
            if (zzq().zzd(zzaVarZzp.zzt(), zzgVarZze.zzam())) {
                zzq().zza(bundle2, "_dbg", (Object) 1L);
                zzq().zza(bundle2, "_r", (Object) 1L);
            }
            zzaz zzazVarZzd = zzh().zzd(str, zzbdVar.zza);
            if (zzazVarZzd == null) {
                bundle = bundle2;
                bArr = null;
                zzgVar = zzgVarZze;
                zzaVar = zzaVarZzp;
                zzbVar = zzbVarZzb;
                zzazVarZza = new zzaz(str, zzbdVar.zza, 0L, 0L, zzbdVar.zzd, 0L, null, null, null, null);
                j = 0;
            } else {
                zzbVar = zzbVarZzb;
                bundle = bundle2;
                bArr = null;
                zzgVar = zzgVarZze;
                zzaVar = zzaVarZzp;
                long j2 = zzazVarZzd.zzf;
                zzazVarZza = zzazVarZzd.zza(zzbdVar.zzd);
                j = j2;
            }
            zzaz zzazVar = zzazVarZza;
            zzh().zza(zzazVar);
            zzba zzbaVar = new zzba(this.zzu, zzbdVar.zzc, str, zzbdVar.zza, zzbdVar.zzd, j, bundle);
            zzfn.zzf.zza zzaVarZza = zzfn.zzf.zze().zzb(zzbaVar.zzc).zza(zzbaVar.zzb).zza(zzbaVar.zzd);
            for (String str2 : zzbaVar.zze) {
                zzfn.zzh.zza zzaVarZza2 = zzfn.zzh.zze().zza(str2);
                Object objZzc = zzbaVar.zze.zzc(str2);
                if (objZzc != null) {
                    g_().zza(zzaVarZza2, objZzc);
                    zzaVarZza.zza(zzaVarZza2);
                }
            }
            zzfn.zzk.zza zzaVar2 = zzaVar;
            zzaVar2.zza(zzaVarZza).zza(zzfn.zzl.zza().zza(zzfn.zzg.zza().zza(zzazVar.zzc).zza(zzbdVar.zza)));
            zzaVar2.zza(zzg().zza(zzgVar.zzac(), Collections.emptyList(), zzaVar2.zzab(), Long.valueOf(zzaVarZza.zzc()), Long.valueOf(zzaVarZza.zzc())));
            if (zzaVarZza.zzg()) {
                zzaVar2.zzi(zzaVarZza.zzc()).zze(zzaVarZza.zzc());
            }
            long jZzs = zzgVar.zzs();
            if (jZzs != 0) {
                zzaVar2.zzg(jZzs);
            }
            long jZzu = zzgVar.zzu();
            if (jZzu != 0) {
                zzaVar2.zzh(jZzu);
            } else if (jZzs != 0) {
                zzaVar2.zzh(jZzs);
            }
            String strZzal = zzgVar.zzal();
            if (zzph.zza() && zze().zze(str, zzbf.zzbr) && strZzal != null) {
                zzaVar2.zzr(strZzal);
            }
            zzgVar.zzap();
            zzaVar2.zzf((int) zzgVar.zzt()).zzl(97001L).zzk(zzb().currentTimeMillis()).zzd(Boolean.TRUE.booleanValue());
            this.zzf.zza(zzaVar2.zzt(), zzaVar2);
            zzfn.zzj.zzb zzbVar2 = zzbVar;
            zzbVar2.zza(zzaVar2);
            zzg zzgVar2 = zzgVar;
            zzgVar2.zzr(zzaVar2.zzf());
            zzgVar2.zzp(zzaVar2.zze());
            zzh().zza(zzgVar2, false, false);
            zzh().zzw();
            try {
                return g_().zzb(((zzfn.zzj) ((com.google.android.gms.internal.measurement.zzjk) zzbVar2.zzai())).zzbz());
            } catch (IOException e) {
                zzj().zzg().zza("Data loss. Failed to bundle and serialize. appId", zzfw.zza(str), e);
                return bArr;
            }
        } catch (SecurityException e2) {
            zzj().zzc().zza("app instance id encryption failed", e2.getMessage());
            return new byte[0];
        } catch (SecurityException e3) {
            zzj().zzc().zza("Resettable device id encryption failed", e3.getMessage());
            return new byte[0];
        } finally {
            zzh().zzu();
        }
    }
}
