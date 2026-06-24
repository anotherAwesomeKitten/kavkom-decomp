package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.facebook.react.bridge.ReactContext;
import com.horcrux.svg.FilterProperties;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
class FeCompositeView extends FilterPrimitiveView {
    String mIn1;
    String mIn2;
    float mK1;
    float mK2;
    float mK3;
    float mK4;
    FilterProperties.FeCompositeOperator mOperator;

    public FeCompositeView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setIn2(String str) {
        this.mIn2 = str;
        invalidate();
    }

    public void setK1(Float f) {
        this.mK1 = f.floatValue();
        invalidate();
    }

    public void setK2(Float f) {
        this.mK2 = f.floatValue();
        invalidate();
    }

    public void setK3(Float f) {
        this.mK3 = f.floatValue();
        invalidate();
    }

    public void setK4(Float f) {
        this.mK4 = f.floatValue();
        invalidate();
    }

    public void setOperator(String str) {
        this.mOperator = FilterProperties.FeCompositeOperator.getEnum(str);
        invalidate();
    }

    @Override // com.horcrux.svg.FilterPrimitiveView
    public Bitmap applyFilter(HashMap<String, Bitmap> map, Bitmap bitmap) {
        Bitmap source = getSource(map, bitmap, this.mIn1);
        Bitmap source2 = getSource(map, bitmap, this.mIn2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        canvas.drawBitmap(source, 0.0f, 0.0f, paint);
        switch (AnonymousClass1.$SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[this.mOperator.ordinal()]) {
            case 1:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
                break;
            case 2:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                break;
            case 3:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                break;
            case 4:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
                break;
            case 5:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                break;
            case 6:
                int width = bitmapCreateBitmap.getWidth() * bitmapCreateBitmap.getHeight();
                int[] iArr = new int[width];
                bitmapCreateBitmap.getPixels(iArr, 0, bitmapCreateBitmap.getWidth(), 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
                int width2 = bitmapCreateBitmap.getWidth();
                int width3 = bitmapCreateBitmap.getWidth();
                int height = bitmapCreateBitmap.getHeight();
                int[] iArr2 = new int[width];
                source2.getPixels(iArr2, 0, width2, 0, 0, width3, height);
                int i = 0;
                while (i < width) {
                    int i2 = iArr[i];
                    int i3 = iArr2[i];
                    int i4 = width;
                    int[] iArr3 = iArr2;
                    float f = this.mK1;
                    float f2 = (i2 >> 16) & 255;
                    float f3 = (i3 >> 16) & 255;
                    float f4 = f * f2 * f3;
                    float f5 = this.mK2;
                    float f6 = f4 + (f2 * f5);
                    float f7 = this.mK3;
                    float f8 = f6 + (f3 * f7);
                    float f9 = this.mK4;
                    int i5 = (int) (f8 + f9);
                    float f10 = (i2 >> 8) & 255;
                    float f11 = (i3 >> 8) & 255;
                    int i6 = (int) ((f * f10 * f11) + (f10 * f5) + (f11 * f7) + f9);
                    float f12 = i2 & 255;
                    float f13 = i3 & 255;
                    int i7 = (int) ((f * f12 * f13) + (f12 * f5) + (f13 * f7) + f9);
                    float f14 = i2 >>> 24;
                    float f15 = i3 >>> 24;
                    int i8 = (int) ((f * f14 * f15) + (f14 * f5) + (f7 * f15) + f9);
                    int iMin = Math.min(255, Math.max(0, i5));
                    iArr[i] = (Math.min(255, Math.max(0, i6)) << 8) | (iMin << 16) | (Math.min(255, Math.max(0, i8)) << 24) | Math.min(255, Math.max(0, i7));
                    i++;
                    width = i4;
                    iArr2 = iArr3;
                }
                int width4 = bitmapCreateBitmap.getWidth();
                int width5 = bitmapCreateBitmap.getWidth();
                bitmapCreateBitmap = bitmapCreateBitmap;
                bitmapCreateBitmap.setPixels(iArr, 0, width4, 0, 0, width5, bitmapCreateBitmap.getHeight());
                break;
        }
        if (this.mOperator != FilterProperties.FeCompositeOperator.ARITHMETIC) {
            canvas.drawBitmap(source2, 0.0f, 0.0f, paint);
        }
        return bitmapCreateBitmap;
    }

    /* JADX INFO: renamed from: com.horcrux.svg.FeCompositeView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator;

        static {
            int[] iArr = new int[FilterProperties.FeCompositeOperator.values().length];
            $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator = iArr;
            try {
                iArr[FilterProperties.FeCompositeOperator.OVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[FilterProperties.FeCompositeOperator.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[FilterProperties.FeCompositeOperator.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[FilterProperties.FeCompositeOperator.ATOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[FilterProperties.FeCompositeOperator.XOR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[FilterProperties.FeCompositeOperator.ARITHMETIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
