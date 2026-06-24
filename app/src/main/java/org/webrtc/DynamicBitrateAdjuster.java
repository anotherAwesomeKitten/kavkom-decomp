package org.webrtc;

/* JADX INFO: loaded from: classes3.dex */
class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp;
    private double deviationBytes;
    private double timeSinceLastAdjustmentMs;

    DynamicBitrateAdjuster() {
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int i, double d) {
        if (this.targetBitrateBps > 0 && i < this.targetBitrateBps) {
            this.deviationBytes = (this.deviationBytes * ((double) i)) / ((double) this.targetBitrateBps);
        }
        super.setTargets(i, d);
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i) {
        if (this.targetFramerateFps == 0.0d) {
            return;
        }
        this.deviationBytes += ((double) i) - ((((double) this.targetBitrateBps) / BITS_PER_BYTE) / this.targetFramerateFps);
        this.timeSinceLastAdjustmentMs += 1000.0d / this.targetFramerateFps;
        double d = ((double) this.targetBitrateBps) / BITS_PER_BYTE;
        double d2 = 3.0d * d;
        double dMin = Math.min(this.deviationBytes, d2);
        this.deviationBytes = dMin;
        double dMax = Math.max(dMin, -d2);
        this.deviationBytes = dMax;
        if (this.timeSinceLastAdjustmentMs <= 3000.0d) {
            return;
        }
        if (dMax > d) {
            int i2 = this.bitrateAdjustmentScaleExp - ((int) ((dMax / d) + 0.5d));
            this.bitrateAdjustmentScaleExp = i2;
            this.bitrateAdjustmentScaleExp = Math.max(i2, -20);
            this.deviationBytes = d;
        } else {
            double d3 = -d;
            if (dMax < d3) {
                int i3 = this.bitrateAdjustmentScaleExp + ((int) (((-dMax) / d) + 0.5d));
                this.bitrateAdjustmentScaleExp = i3;
                this.bitrateAdjustmentScaleExp = Math.min(i3, 20);
                this.deviationBytes = d3;
            }
        }
        this.timeSinceLastAdjustmentMs = 0.0d;
    }

    private double getBitrateAdjustmentScale() {
        return Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, ((double) this.bitrateAdjustmentScaleExp) / 20.0d);
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return (int) (((double) this.targetBitrateBps) * getBitrateAdjustmentScale());
    }
}
