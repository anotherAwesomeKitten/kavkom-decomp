package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzge implements zzgg {
    @Override // com.google.android.gms.internal.measurement.zzgg
    public final String zza(ContentResolver contentResolver, String str) throws zzgf {
        Cursor cursorQuery = contentResolver.query(zzfy.zza, null, null, new String[]{str}, null);
        try {
            if (cursorQuery == null) {
                throw new zzgf("Failed to connect to GservicesProvider");
            }
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(1);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return string;
            }
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.close();
            return null;
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            try {
                cursorQuery.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    public final <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzgd<T> zzgdVar) throws zzgf {
        Cursor cursorQuery = contentResolver.query(zzfy.zzb, null, null, strArr, null);
        try {
            if (cursorQuery == null) {
                throw new zzgf("Failed to connect to GservicesProvider");
            }
            T t = (T) zzgdVar.zza(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                t.put(cursorQuery.getString(0), cursorQuery.getString(1));
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return t;
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            try {
                cursorQuery.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }
}
