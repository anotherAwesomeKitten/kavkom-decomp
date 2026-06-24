package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznt implements zznu {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zzhhVarZza.zza("measurement.dma_consent.client", true);
        zzhhVarZza.zza("measurement.dma_consent.client_bow_check2", true);
        zzhhVarZza.zza("measurement.dma_consent.separate_service_calls_fix", true);
        zzhhVarZza.zza("measurement.dma_consent.service", true);
        zza = zzhhVarZza.zza("measurement.dma_consent.service_database_update_fix", true);
        zzhhVarZza.zza("measurement.dma_consent.service_dcu_event", true);
        zzb = zzhhVarZza.zza("measurement.dma_consent.service_dcu_event2", true);
        zzhhVarZza.zza("measurement.dma_consent.service_npa_remote_default", true);
        zzhhVarZza.zza("measurement.dma_consent.service_split_batch_on_consent", true);
        zzhhVarZza.zza("measurement.dma_consent.set_consent_inline_on_worker", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznu
    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznu
    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
