package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzpg;
import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzq extends BroadcastReceiver {
    private final zzhj zza;

    public zzq(zzhj zzhjVar) {
        this.zza = zzhjVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            this.zza.zzj().zzu().zza("App receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            this.zza.zzj().zzu().zza("App receiver called with null action");
            return;
        }
        action.hashCode();
        if (action.equals("com.google.android.gms.measurement.TRIGGERS_AVAILABLE")) {
            final zzhj zzhjVar = this.zza;
            if (zzpg.zza() && zzhjVar.zzf().zzf(null, zzbf.zzca)) {
                zzhjVar.zzj().zzp().zza("App receiver notified triggers are available");
                zzhjVar.zzl().zzb(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzs
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzhj zzhjVar2 = zzhjVar;
                        if (!zzhjVar2.zzt().zzw()) {
                            zzhjVar2.zzj().zzu().zza("registerTrigger called but app not eligible");
                            return;
                        }
                        final zziv zzivVarZzp = zzhjVar2.zzp();
                        Objects.requireNonNull(zzivVarZzp);
                        new Thread(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzp
                            @Override // java.lang.Runnable
                            public final void run() {
                                zzivVarZzp.zzao();
                            }
                        }).start();
                    }
                });
                return;
            }
            return;
        }
        this.zza.zzj().zzu().zza("App receiver called with unknown action");
    }
}
