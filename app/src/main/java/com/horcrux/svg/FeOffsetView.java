package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
class FeOffsetView extends FilterPrimitiveView {
    SVGLength mDx;
    SVGLength mDy;
    String mIn1;

    public FeOffsetView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setDx(Dynamic dynamic) {
        this.mDx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setDy(Dynamic dynamic) {
        this.mDy = SVGLength.from(dynamic);
        invalidate();
    }

    @Override // com.horcrux.svg.FilterPrimitiveView
    public Bitmap applyFilter(HashMap<String, Bitmap> map, Bitmap bitmap) {
        Bitmap source = getSource(map, bitmap, this.mIn1);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        SVGLength sVGLength = this.mDx;
        float fRelativeOnWidth = sVGLength != null ? (float) relativeOnWidth(sVGLength) : 0.0f;
        SVGLength sVGLength2 = this.mDy;
        RectF rectF = new RectF(0.0f, 0.0f, fRelativeOnWidth, sVGLength2 != null ? (float) relativeOnHeight(sVGLength2) : 0.0f);
        getSvgView().getCtm().mapRect(rectF);
        canvas.drawBitmap(source, rectF.left < 0.0f ? rectF.left : rectF.width(), rectF.top < 0.0f ? rectF.top : rectF.height(), (Paint) null);
        return bitmapCreateBitmap;
    }
}
