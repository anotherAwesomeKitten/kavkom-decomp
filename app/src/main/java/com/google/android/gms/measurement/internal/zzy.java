package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzy {
    private zzfn.zzf zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzu zzd;

    final zzfn.zzf zza(String str, zzfn.zzf zzfVar) {
        String strZzg = zzfVar.zzg();
        List<zzfn.zzh> listZzh = zzfVar.zzh();
        this.zzd.g_();
        Long l = (Long) zznl.zzb(zzfVar, "_eid");
        boolean z = l != null;
        if (z && strZzg.equals("_ep")) {
            Preconditions.checkNotNull(l);
            this.zzd.g_();
            String str2 = (String) zznl.zzb(zzfVar, "_en");
            if (TextUtils.isEmpty(str2)) {
                this.zzd.zzj().zzm().zza("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzfn.zzf, Long> pairZza = this.zzd.zzh().zza(str, l);
                if (pairZza == null || pairZza.first == null) {
                    this.zzd.zzj().zzm().zza("Extra parameter without existing main event. eventName, eventId", str2, l);
                    return null;
                }
                this.zza = (zzfn.zzf) pairZza.first;
                this.zzc = ((Long) pairZza.second).longValue();
                this.zzd.g_();
                this.zzb = (Long) zznl.zzb(this.zza, "_eid");
            }
            long j = this.zzc - 1;
            this.zzc = j;
            if (j <= 0) {
                zzal zzalVarZzh = this.zzd.zzh();
                zzalVarZzh.zzt();
                zzalVarZzh.zzj().zzp().zza("Clearing complex main event info. appId", str);
                try {
                    zzalVarZzh.e_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    zzalVarZzh.zzj().zzg().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzh().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzfn.zzh zzhVar : this.zza.zzh()) {
                this.zzd.g_();
                if (zznl.zza(zzfVar, zzhVar.zzg()) == null) {
                    arrayList.add(zzhVar);
                }
            }
            if (arrayList.isEmpty()) {
                this.zzd.zzj().zzm().zza("No unique parameters in main event. eventName", str2);
            } else {
                arrayList.addAll(listZzh);
                listZzh = arrayList;
            }
            strZzg = str2;
        } else if (z) {
            this.zzb = l;
            this.zza = zzfVar;
            this.zzd.g_();
            long jLongValue = ((Long) zznl.zza(zzfVar, "_epc", (Object) 0L)).longValue();
            this.zzc = jLongValue;
            if (jLongValue <= 0) {
                this.zzd.zzj().zzm().zza("Complex event with zero extra param count. eventName", strZzg);
            } else {
                this.zzd.zzh().zza(str, (Long) Preconditions.checkNotNull(l), this.zzc, zzfVar);
            }
        }
        return (zzfn.zzf) ((com.google.android.gms.internal.measurement.zzjk) zzfVar.zzcc().zza(strZzg).zzd().zza(listZzh).zzai());
    }

    private zzy(zzu zzuVar) {
        this.zzd = zzuVar;
    }
}
