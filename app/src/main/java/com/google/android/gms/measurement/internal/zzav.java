package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzin;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzav {
    private static final zzav zza = new zzav(null, 100);
    private final int zzb;
    private final String zzc;
    private final Boolean zzd;
    private final String zze;
    private final EnumMap<zzin.zza, zzim> zzf;

    public final int zza() {
        return this.zzb;
    }

    public final int hashCode() {
        Boolean bool = this.zzd;
        int i = bool == null ? 3 : bool == Boolean.TRUE ? 7 : 13;
        String str = this.zze;
        return this.zzc.hashCode() + (i * 29) + ((str == null ? 17 : str.hashCode()) * 137);
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        Iterator it = this.zzf.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String strZzb = zzin.zzb((zzim) entry.getValue());
            if (strZzb != null) {
                bundle.putString(((zzin.zza) entry.getKey()).zze, strZzb);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.zze;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public static zzav zza(Bundle bundle, int i) {
        if (bundle == null) {
            return new zzav(null, i);
        }
        EnumMap enumMap = new EnumMap(zzin.zza.class);
        for (zzin.zza zzaVar : zzio.DMA.zza()) {
            enumMap.put(zzaVar, zzin.zza(bundle.getString(zzaVar.zze)));
        }
        return new zzav((EnumMap<zzin.zza, zzim>) enumMap, i, bundle.containsKey("is_dma_region") ? Boolean.valueOf(bundle.getString("is_dma_region")) : null, bundle.getString("cps_display_str"));
    }

    static zzav zza(zzim zzimVar, int i) {
        EnumMap enumMap = new EnumMap(zzin.zza.class);
        enumMap.put(zzin.zza.AD_USER_DATA, zzimVar);
        return new zzav((EnumMap<zzin.zza, zzim>) enumMap, -10, (Boolean) null, (String) null);
    }

    public static zzav zza(String str) {
        if (str == null || str.length() <= 0) {
            return zza;
        }
        String[] strArrSplit = str.split(":");
        int i = Integer.parseInt(strArrSplit[0]);
        EnumMap enumMap = new EnumMap(zzin.zza.class);
        zzin.zza[] zzaVarArrZza = zzio.DMA.zza();
        int length = zzaVarArrZza.length;
        int i2 = 1;
        int i3 = 0;
        while (i3 < length) {
            enumMap.put(zzaVarArrZza[i3], zzin.zza(strArrSplit[i2].charAt(0)));
            i3++;
            i2++;
        }
        return new zzav((EnumMap<zzin.zza, zzim>) enumMap, i, (Boolean) null, (String) null);
    }

    public final zzim zzc() {
        zzim zzimVar = this.zzf.get(zzin.zza.AD_USER_DATA);
        return zzimVar == null ? zzim.UNINITIALIZED : zzimVar;
    }

    public static Boolean zza(Bundle bundle) {
        zzim zzimVarZza;
        if (bundle == null || (zzimVarZza = zzin.zza(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int i = zzay.zza[zzimVarZza.ordinal()];
        if (i != 3) {
            return i != 4 ? null : true;
        }
        return false;
    }

    public final Boolean zzd() {
        return this.zzd;
    }

    private final String zzh() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        for (zzin.zza zzaVar : zzio.DMA.zza()) {
            sb.append(":");
            sb.append(zzin.zza(this.zzf.get(zzaVar)));
        }
        return sb.toString();
    }

    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzc;
    }

    public final String toString() {
        StringBuilder sbAppend = new StringBuilder("source=").append(zzin.zza(this.zzb));
        for (zzin.zza zzaVar : zzio.DMA.zza()) {
            sbAppend.append(",");
            sbAppend.append(zzaVar.zze);
            sbAppend.append("=");
            zzim zzimVar = this.zzf.get(zzaVar);
            if (zzimVar == null) {
                sbAppend.append("uninitialized");
            } else {
                int i = zzay.zza[zzimVar.ordinal()];
                if (i == 1) {
                    sbAppend.append("uninitialized");
                } else if (i == 2) {
                    sbAppend.append("eu_consent_policy");
                } else if (i == 3) {
                    sbAppend.append("denied");
                } else if (i == 4) {
                    sbAppend.append("granted");
                }
            }
        }
        if (this.zzd != null) {
            sbAppend.append(",isDmaRegion=").append(this.zzd);
        }
        if (this.zze != null) {
            sbAppend.append(",cpsDisplayStr=").append(this.zze);
        }
        return sbAppend.toString();
    }

    zzav(Boolean bool, int i) {
        this(bool, i, (Boolean) null, (String) null);
    }

    zzav(Boolean bool, int i, Boolean bool2, String str) {
        EnumMap<zzin.zza, zzim> enumMap = new EnumMap<>(zzin.zza.class);
        this.zzf = enumMap;
        enumMap.put(zzin.zza.AD_USER_DATA, zzin.zza(bool));
        this.zzb = i;
        this.zzc = zzh();
        this.zzd = bool2;
        this.zze = str;
    }

    private zzav(EnumMap<zzin.zza, zzim> enumMap, int i, Boolean bool, String str) {
        EnumMap<zzin.zza, zzim> enumMap2 = new EnumMap<>(zzin.zza.class);
        this.zzf = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzb = i;
        this.zzc = zzh();
        this.zzd = bool;
        this.zze = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzav)) {
            return false;
        }
        zzav zzavVar = (zzav) obj;
        if (this.zzc.equalsIgnoreCase(zzavVar.zzc) && Objects.equals(this.zzd, zzavVar.zzd)) {
            return Objects.equals(this.zze, zzavVar.zze);
        }
        return false;
    }

    public final boolean zzg() {
        Iterator<zzim> it = this.zzf.values().iterator();
        while (it.hasNext()) {
            if (it.next() != zzim.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }
}
