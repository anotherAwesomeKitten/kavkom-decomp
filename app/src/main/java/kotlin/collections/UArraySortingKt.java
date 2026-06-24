package kotlin.collections;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UArraySorting.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001e\u0010\u000b\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001f\u0010\u0010\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b \u0010\u0015\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", ViewProps.RIGHT, "partition-4UcCI2c", "([BII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class UArraySortingKt {
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m1407partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM1023getw2LRezQ = UByteArray.m1023getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM1023getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m1023getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m1023getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM1023getw2LRezQ2 = UByteArray.m1023getw2LRezQ(bArr, i);
                UByteArray.m1028setVurrAj0(bArr, i, UByteArray.m1023getw2LRezQ(bArr, i2));
                UByteArray.m1028setVurrAj0(bArr, i2, bM1023getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m1411quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM1407partition4UcCI2c = m1407partition4UcCI2c(bArr, i, i2);
        int i3 = iM1407partition4UcCI2c - 1;
        if (i < i3) {
            m1411quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM1407partition4UcCI2c < i2) {
            m1411quickSort4UcCI2c(bArr, iM1407partition4UcCI2c, i2);
        }
    }

    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m1408partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM1286getMh2AYeg = UShortArray.m1286getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM1286getMh2AYeg = UShortArray.m1286getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = sM1286getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(iM1286getMh2AYeg, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m1286getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM1286getMh2AYeg2 = UShortArray.m1286getMh2AYeg(sArr, i);
                UShortArray.m1291set01HTLdE(sArr, i, UShortArray.m1286getMh2AYeg(sArr, i2));
                UShortArray.m1291set01HTLdE(sArr, i2, sM1286getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m1412quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM1408partitionAa5vz7o = m1408partitionAa5vz7o(sArr, i, i2);
        int i3 = iM1408partitionAa5vz7o - 1;
        if (i < i3) {
            m1412quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM1408partitionAa5vz7o < i2) {
            m1412quickSortAa5vz7o(sArr, iM1408partitionAa5vz7o, i2);
        }
    }

    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m1409partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM1102getpVg5ArA = UIntArray.m1102getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compare(UIntArray.m1102getpVg5ArA(iArr, i) ^ Integer.MIN_VALUE, iM1102getpVg5ArA ^ Integer.MIN_VALUE) < 0) {
                i++;
            }
            while (Integer.compare(UIntArray.m1102getpVg5ArA(iArr, i2) ^ Integer.MIN_VALUE, iM1102getpVg5ArA ^ Integer.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM1102getpVg5ArA2 = UIntArray.m1102getpVg5ArA(iArr, i);
                UIntArray.m1107setVXSXFK8(iArr, i, UIntArray.m1102getpVg5ArA(iArr, i2));
                UIntArray.m1107setVXSXFK8(iArr, i2, iM1102getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m1413quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM1409partitionoBK06Vg = m1409partitionoBK06Vg(iArr, i, i2);
        int i3 = iM1409partitionoBK06Vg - 1;
        if (i < i3) {
            m1413quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM1409partitionoBK06Vg < i2) {
            m1413quickSortoBK06Vg(iArr, iM1409partitionoBK06Vg, i2);
        }
    }

    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m1406partitionnroSd4(long[] jArr, int i, int i2) {
        long jM1181getsVKNKU = ULongArray.m1181getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compare(ULongArray.m1181getsVKNKU(jArr, i) ^ Long.MIN_VALUE, jM1181getsVKNKU ^ Long.MIN_VALUE) < 0) {
                i++;
            }
            while (Long.compare(ULongArray.m1181getsVKNKU(jArr, i2) ^ Long.MIN_VALUE, jM1181getsVKNKU ^ Long.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM1181getsVKNKU2 = ULongArray.m1181getsVKNKU(jArr, i);
                ULongArray.m1186setk8EXiF4(jArr, i, ULongArray.m1181getsVKNKU(jArr, i2));
                ULongArray.m1186setk8EXiF4(jArr, i2, jM1181getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m1410quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM1406partitionnroSd4 = m1406partitionnroSd4(jArr, i, i2);
        int i3 = iM1406partitionnroSd4 - 1;
        if (i < i3) {
            m1410quickSortnroSd4(jArr, i, i3);
        }
        if (iM1406partitionnroSd4 < i2) {
            m1410quickSortnroSd4(jArr, iM1406partitionnroSd4, i2);
        }
    }

    /* JADX INFO: renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m1415sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1411quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m1416sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1412quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m1417sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1413quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m1414sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1410quickSortnroSd4(array, i, i2 - 1);
    }
}
