package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public class zzkb {
    private volatile zzkt zza;
    private volatile zzia zzb;

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return this.zzb.zzb();
        }
        if (this.zza != null) {
            return this.zza.zzca();
        }
        return 0;
    }

    public final zzia zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                return this.zzb;
            }
            if (this.zza == null) {
                this.zzb = zzia.zza;
            } else {
                this.zzb = this.zza.zzby();
            }
            return this.zzb;
        }
    }

    private final zzkt zzb(zzkt zzktVar) {
        if (this.zza == null) {
            synchronized (this) {
                if (this.zza == null) {
                    try {
                        this.zza = zzktVar;
                        this.zzb = zzia.zza;
                    } catch (zzjs unused) {
                        this.zza = zzktVar;
                        this.zzb = zzia.zza;
                    }
                }
            }
        }
        return this.zza;
    }

    public final zzkt zza(zzkt zzktVar) {
        zzkt zzktVar2 = this.zza;
        this.zzb = null;
        this.zza = zzktVar;
        return zzktVar2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkb)) {
            return false;
        }
        zzkb zzkbVar = (zzkb) obj;
        zzkt zzktVar = this.zza;
        zzkt zzktVar2 = zzkbVar.zza;
        if (zzktVar == null && zzktVar2 == null) {
            return zzb().equals(zzkbVar.zzb());
        }
        if (zzktVar != null && zzktVar2 != null) {
            return zzktVar.equals(zzktVar2);
        }
        if (zzktVar != null) {
            return zzktVar.equals(zzkbVar.zzb(zzktVar.zzcj()));
        }
        return zzb(zzktVar2.zzcj()).equals(zzktVar2);
    }
}
