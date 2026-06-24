package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhp;
import com.google.android.gms.internal.measurement.zzhq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzhq<MessageType extends zzhq<MessageType, BuilderType>, BuilderType extends zzhp<MessageType, BuilderType>> implements zzkt {
    protected int zza = 0;

    int zzbx() {
        throw new UnsupportedOperationException();
    }

    int zza(zzll zzllVar) {
        int iZzbx = zzbx();
        if (iZzbx != -1) {
            return iZzbx;
        }
        int iZza = zzllVar.zza(this);
        zzc(iZza);
        return iZza;
    }

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final zzia zzby() {
        try {
            zzif zzifVarZzc = zzia.zzc(zzca());
            zza(zzifVarZzc.zzb());
            return zzifVarZzc.zza();
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzjm.zza(iterable);
        if (iterable instanceof zzka) {
            List<?> listZza = ((zzka) iterable).zza();
            zzka zzkaVar = (zzka) list;
            int size = list.size();
            for (Object obj : listZza) {
                if (obj == null) {
                    String str = "Element at index " + (zzkaVar.size() - size) + " is null.";
                    for (int size2 = zzkaVar.size() - 1; size2 >= size; size2--) {
                        zzkaVar.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof zzia) {
                    zzkaVar.zza((zzia) obj);
                } else if (obj instanceof byte[]) {
                    zzkaVar.zza(zzia.zza((byte[]) obj));
                } else {
                    zzkaVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzlf) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size3 = list.size();
        for (T t : iterable) {
            if (t == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                    list.remove(size4);
                }
                throw new NullPointerException(str2);
            }
            list.add(t);
        }
    }

    void zzc(int i) {
        throw new UnsupportedOperationException();
    }

    public final byte[] zzbz() {
        try {
            byte[] bArr = new byte[zzca()];
            zzit zzitVarZzb = zzit.zzb(bArr);
            zza(zzitVarZzb);
            zzitVarZzb.zzb();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
