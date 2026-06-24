package com.facebook.soloader;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedByInterruptException;
import kotlin.UShort;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes.dex */
public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;
    private static final String TAG = "MinElf";

    public interface ISA {
        public static final String AARCH64 = "arm64-v8a";
        public static final String ARM = "armeabi-v7a";
        public static final String X86 = "x86";
        public static final String X86_64 = "x86_64";
    }

    public static String[] extract_DT_NEEDED(File file) throws IOException {
        ElfFileChannel elfFileChannel = new ElfFileChannel(file);
        try {
            String[] strArrExtract_DT_NEEDED = extract_DT_NEEDED(elfFileChannel);
            elfFileChannel.close();
            return strArrExtract_DT_NEEDED;
        } catch (Throwable th) {
            try {
                elfFileChannel.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static String[] extract_DT_NEEDED_with_retries(ElfFileChannel elfFileChannel) throws IOException {
        int i = 0;
        while (true) {
            try {
                return extract_DT_NEEDED_no_retries(elfFileChannel);
            } catch (ClosedByInterruptException e) {
                i++;
                if (i > 4) {
                    throw e;
                }
                Thread.interrupted();
                LogUtil.e(TAG, "retrying extract_DT_NEEDED due to ClosedByInterruptException", e);
                elfFileChannel.openChannel();
            }
        }
    }

    public static String[] extract_DT_NEEDED(ElfByteChannel elfByteChannel) throws IOException {
        if (elfByteChannel instanceof ElfFileChannel) {
            return extract_DT_NEEDED_with_retries((ElfFileChannel) elfByteChannel);
        }
        return extract_DT_NEEDED_no_retries(elfByteChannel);
    }

    private static String[] extract_DT_NEEDED_no_retries(ElfByteChannel elfByteChannel) throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        long j12;
        long j13;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        long j14 = getu32(elfByteChannel, byteBufferAllocate, 0L);
        if (j14 != 1179403647) {
            throw new ElfError("file is not ELF: magic is 0x" + Long.toHexString(j14) + ", it should be " + Long.toHexString(1179403647L));
        }
        boolean z = getu8(elfByteChannel, byteBufferAllocate, 4L) == 1;
        long j15 = 5;
        if (getu8(elfByteChannel, byteBufferAllocate, 5L) == 2) {
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        }
        long j16 = z ? getu32(elfByteChannel, byteBufferAllocate, 28L) : get64(elfByteChannel, byteBufferAllocate, 32L);
        if (z) {
            j = 44;
            j2 = getu16(elfByteChannel, byteBufferAllocate, 44L);
        } else {
            j = 44;
            j2 = getu16(elfByteChannel, byteBufferAllocate, 56L);
        }
        int i = getu16(elfByteChannel, byteBufferAllocate, z ? 42L : 54L);
        boolean z2 = z;
        long j17 = 40;
        if (j2 == WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            long j18 = z2 ? getu32(elfByteChannel, byteBufferAllocate, 32L) : get64(elfByteChannel, byteBufferAllocate, 40L);
            if (z2) {
                j2 = getu32(elfByteChannel, byteBufferAllocate, j18 + 28);
            } else {
                j2 = getu32(elfByteChannel, byteBufferAllocate, j18 + j);
            }
        }
        long j19 = j16;
        long j20 = 0;
        while (true) {
            if (j20 >= j2) {
                j3 = 0;
                break;
            }
            if (z2) {
                j13 = getu32(elfByteChannel, byteBufferAllocate, j19);
            } else {
                j13 = getu32(elfByteChannel, byteBufferAllocate, j19);
            }
            if (j13 != 2) {
                j19 += (long) i;
                j20++;
                j17 = j17;
            } else if (z2) {
                j3 = getu32(elfByteChannel, byteBufferAllocate, j19 + 4);
            } else {
                j3 = get64(elfByteChannel, byteBufferAllocate, j19 + 8);
            }
        }
        long j21 = j17;
        if (j3 == 0) {
            throw new ElfError("ELF file does not contain dynamic linking information");
        }
        long j22 = j3;
        long j23 = 0;
        int i2 = 0;
        while (true) {
            long j24 = z2 ? getu32(elfByteChannel, byteBufferAllocate, j22) : get64(elfByteChannel, byteBufferAllocate, j22);
            long j25 = j15;
            if (j24 == 1) {
                if (i2 == Integer.MAX_VALUE) {
                    throw new ElfError("malformed DT_NEEDED section");
                }
                i2++;
            } else if (j24 == j25) {
                j23 = z2 ? getu32(elfByteChannel, byteBufferAllocate, j22 + 4) : get64(elfByteChannel, byteBufferAllocate, j22 + 8);
            }
            j22 += z2 ? 8L : 16L;
            if (j24 == 0) {
                if (j23 == 0) {
                    throw new ElfError("Dynamic section string-table not found");
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= j2) {
                        j4 = 0;
                        break;
                    }
                    if (z2) {
                        j6 = j16;
                        j7 = getu32(elfByteChannel, byteBufferAllocate, j6);
                    } else {
                        j6 = j16;
                        j7 = getu32(elfByteChannel, byteBufferAllocate, j6);
                    }
                    if (j7 == 1) {
                        if (z2) {
                            j8 = j2;
                            j9 = getu32(elfByteChannel, byteBufferAllocate, j6 + 8);
                        } else {
                            j8 = j2;
                            j9 = get64(elfByteChannel, byteBufferAllocate, j6 + 16);
                        }
                        if (z2) {
                            j10 = j9;
                            j11 = getu32(elfByteChannel, byteBufferAllocate, j6 + 20);
                        } else {
                            j10 = j9;
                            j11 = get64(elfByteChannel, byteBufferAllocate, j6 + j21);
                        }
                        if (j10 <= j23 && j23 < j10 + j11) {
                            if (z2) {
                                j12 = getu32(elfByteChannel, byteBufferAllocate, j6 + 4);
                            } else {
                                j12 = get64(elfByteChannel, byteBufferAllocate, j6 + 8);
                            }
                            j4 = j12 + (j23 - j10);
                        }
                    } else {
                        j8 = j2;
                    }
                    j16 = j6 + ((long) i);
                    i3++;
                    j2 = j8;
                }
                if (j4 == 0) {
                    throw new ElfError("did not find file offset of DT_STRTAB table");
                }
                String[] strArr = new String[i2];
                int i4 = 0;
                do {
                    j5 = z2 ? getu32(elfByteChannel, byteBufferAllocate, j3) : get64(elfByteChannel, byteBufferAllocate, j3);
                    if (j5 == 1) {
                        strArr[i4] = getSz(elfByteChannel, byteBufferAllocate, (z2 ? getu32(elfByteChannel, byteBufferAllocate, j3 + 4) : get64(elfByteChannel, byteBufferAllocate, j3 + 8)) + j4);
                        if (i4 == Integer.MAX_VALUE) {
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                        i4++;
                    }
                    j3 += z2 ? 8L : 16L;
                } while (j5 != 0);
                if (i4 == i2) {
                    return strArr;
                }
                throw new ElfError("malformed DT_NEEDED section");
            }
            j15 = j25;
        }
    }

    private static String getSz(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(elfByteChannel, byteBuffer, j);
            if (u8Var != 0) {
                sb.append((char) u8Var);
                j = j2;
            } else {
                return sb.toString();
            }
        }
    }

    private static void read(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int i2;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (i2 = elfByteChannel.read(byteBuffer, j)) != -1) {
            j += (long) i2;
        }
        if (byteBuffer.remaining() > 0) {
            throw new ElfError("ELF file truncated");
        }
        byteBuffer.position(0);
    }

    private static long get64(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long getu32(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int getu16(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    private static short getu8(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    protected static class ElfError extends UnsatisfiedLinkError {
        ElfError(String str) {
            super(str);
        }
    }
}
