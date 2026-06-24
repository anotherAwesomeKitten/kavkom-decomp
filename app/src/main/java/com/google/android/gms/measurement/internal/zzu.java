package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.internal.measurement.zzfn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzu extends zzmx {
    private String zza;
    private Set<Integer> zzb;
    private Map<Integer, zzw> zzc;
    private Long zzd;
    private Long zze;

    private final zzw zza(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzw zzwVar = new zzw(this, this.zza);
        this.zzc.put(num, zzwVar);
        return zzwVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzmx
    protected final boolean zzc() {
        return false;
    }

    final List<zzfn.zzd> zza(String str, List<zzfn.zzf> list, List<zzfn.zzo> list2, Long l, Long l2) {
        return zza(str, list, list2, l, l2, false);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.google.android.gms.measurement.internal.zzw.<init>(com.google.android.gms.measurement.internal.zzu, java.lang.String, com.google.android.gms.internal.measurement.zzfn$zzm, java.util.BitSet, java.util.BitSet, java.util.Map, java.util.Map, com.google.android.gms.measurement.internal.zzv):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:298)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:197)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1118)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    final java.util.List<com.google.android.gms.internal.measurement.zzfn.zzd> zza(java.lang.String r26, java.util.List<com.google.android.gms.internal.measurement.zzfn.zzf> r27, java.util.List<com.google.android.gms.internal.measurement.zzfn.zzo> r28, java.lang.Long r29, java.lang.Long r30, boolean r31) {
        /*
            Method dump skipped, instruction units count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzu.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long, boolean):java.util.List");
    }

    private final List<zzfn.zzd> zzu() {
        ArrayList arrayList = new ArrayList();
        Set<Integer> setKeySet = this.zzc.keySet();
        setKeySet.removeAll(this.zzb);
        Iterator<Integer> it = setKeySet.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            zzw zzwVar = this.zzc.get(Integer.valueOf(iIntValue));
            Preconditions.checkNotNull(zzwVar);
            zzfn.zzd zzdVarZza = zzwVar.zza(iIntValue);
            arrayList.add(zzdVarZza);
            zzal zzalVarZzh = zzh();
            String str = this.zza;
            zzfn.zzm zzmVarZzd = zzdVarZza.zzd();
            zzalVarZzh.zzal();
            zzalVarZzh.zzt();
            Preconditions.checkNotEmpty(str);
            Preconditions.checkNotNull(zzmVarZzd);
            byte[] bArrZzbz = zzmVarZzd.zzbz();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(iIntValue));
            contentValues.put("current_results", bArrZzbz);
            try {
                if (zzalVarZzh.e_().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                    zzalVarZzh.zzj().zzg().zza("Failed to insert filter results (got -1). appId", zzfw.zza(str));
                }
            } catch (SQLiteException e) {
                zzalVarZzh.zzj().zzg().zza("Error storing filter results. appId", zzfw.zza(str), e);
            }
        }
        return arrayList;
    }

    zzu(zznc zzncVar) {
        super(zzncVar);
    }

    private final void zza(List<zzfn.zzf> list, boolean z) {
        ArrayMap arrayMap;
        zzy zzyVar;
        zzaz zzazVar;
        Object obj;
        if (list.isEmpty()) {
            return;
        }
        Object obj2 = null;
        zzy zzyVar2 = new zzy(this);
        ArrayMap arrayMap2 = new ArrayMap();
        for (zzfn.zzf zzfVar : list) {
            zzfn.zzf zzfVarZza = zzyVar2.zza(this.zza, zzfVar);
            if (zzfVarZza != null) {
                zzal zzalVarZzh = zzh();
                String str = this.zza;
                String strZzg = zzfVarZza.zzg();
                zzaz zzazVarZzd = zzalVarZzh.zzd(str, zzfVar.zzg());
                if (zzazVarZzd == null) {
                    zzalVarZzh.zzj().zzu().zza("Event aggregate wasn't created during raw event logging. appId, event", zzfw.zza(str), zzalVarZzh.zzi().zza(strZzg));
                    zzyVar = zzyVar2;
                    arrayMap = arrayMap2;
                    zzazVar = new zzaz(str, zzfVar.zzg(), 1L, 1L, 1L, zzfVar.zzd(), 0L, null, null, null, null);
                } else {
                    arrayMap = arrayMap2;
                    zzyVar = zzyVar2;
                    zzazVar = new zzaz(zzazVarZzd.zza, zzazVarZzd.zzb, zzazVarZzd.zzc + 1, zzazVarZzd.zzd + 1, zzazVarZzd.zze + 1, zzazVarZzd.zzf, zzazVarZzd.zzg, zzazVarZzd.zzh, zzazVarZzd.zzi, zzazVarZzd.zzj, zzazVarZzd.zzk);
                }
                zzh().zza(zzazVar);
                if (com.google.android.gms.internal.measurement.zznk.zza()) {
                    obj = null;
                    if (zze().zzf(null, zzbf.zzcv) && z) {
                        obj2 = null;
                        arrayMap2 = arrayMap;
                    }
                    zzyVar2 = zzyVar;
                } else {
                    obj = null;
                }
                long j = zzazVar.zzc;
                String strZzg2 = zzfVarZza.zzg();
                ArrayMap arrayMap3 = arrayMap;
                Map<Integer, List<zzff.zzb>> mapZzf = (Map) arrayMap3.get(strZzg2);
                if (mapZzf == null) {
                    mapZzf = zzh().zzf(this.zza, strZzg2);
                    arrayMap3.put(strZzg2, mapZzf);
                }
                Iterator<Integer> it = mapZzf.keySet().iterator();
                while (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    if (this.zzb.contains(Integer.valueOf(iIntValue))) {
                        zzj().zzp().zza("Skipping failed audience ID", Integer.valueOf(iIntValue));
                    } else {
                        Iterator<zzff.zzb> it2 = mapZzf.get(Integer.valueOf(iIntValue)).iterator();
                        boolean z2 = true;
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            zzff.zzb next = it2.next();
                            zzaa zzaaVar = new zzaa(this, this.zza, iIntValue, next);
                            boolean zZza = zzaaVar.zza(this.zzd, this.zze, zzfVarZza, j, zzazVar, zza(iIntValue, next.zzb()));
                            if (zZza) {
                                zza(Integer.valueOf(iIntValue)).zza(zzaaVar);
                                z2 = zZza;
                            } else {
                                this.zzb.add(Integer.valueOf(iIntValue));
                                z2 = zZza;
                                break;
                            }
                        }
                        if (!z2) {
                            this.zzb.add(Integer.valueOf(iIntValue));
                        }
                    }
                }
                Object obj3 = obj;
                arrayMap2 = arrayMap3;
                obj2 = obj3;
                zzyVar2 = zzyVar;
            } else {
                arrayMap2 = arrayMap2;
                obj2 = obj2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:93:0x0114, code lost:
    
        r5 = zzj().zzu();
        r7 = com.google.android.gms.measurement.internal.zzfw.zza(r13.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0126, code lost:
    
        if (r6.zzi() == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0128, code lost:
    
        r8 = java.lang.Integer.valueOf(r6.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0130, code lost:
    
        r5.zza("Invalid property filter ID. appId, id", r7, java.lang.String.valueOf(r8));
        r6 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zza(java.util.List<com.google.android.gms.internal.measurement.zzfn.zzo> r14) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzu.zza(java.util.List):void");
    }

    private final boolean zza(int i, int i2) {
        zzw zzwVar = this.zzc.get(Integer.valueOf(i));
        if (zzwVar == null) {
            return false;
        }
        return zzwVar.zzd.get(i2);
    }
}
