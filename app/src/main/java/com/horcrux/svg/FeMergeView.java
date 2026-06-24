package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
class FeMergeView extends FilterPrimitiveView {
    private ReadableArray mNodes;

    public FeMergeView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setNodes(ReadableArray readableArray) {
        this.mNodes = readableArray;
        invalidate();
    }

    @Override // com.horcrux.svg.FilterPrimitiveView
    public Bitmap applyFilter(HashMap<String, Bitmap> map, Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int size = this.mNodes.size();
        for (int i = 0; i < size; i++) {
            String string = this.mNodes.getString(i);
            Bitmap bitmap2 = string.isEmpty() ? bitmap : map.get(string);
            if (bitmap2 != null) {
                canvas.drawBitmap(bitmap2, 0.0f, 0.0f, new Paint());
            }
        }
        return bitmapCreateBitmap;
    }
}
