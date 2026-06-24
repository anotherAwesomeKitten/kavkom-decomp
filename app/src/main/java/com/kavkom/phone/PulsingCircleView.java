package com.kavkom.phone;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public class PulsingCircleView extends View {
    private static final int BACKGROUND_COLOR = -13878969;
    private static final String COLOR_AVATAR_WAVE = "#ACC0D2";
    private static final int DELAY_MS = 3000;
    private static final int DURATION_MS = 6000;
    private static final int NUMBER_OF_CIRCLES = 4;
    private AnimatorSet animatorSet;
    private Paint backgroundPaint;
    private float baseMaskRadius;
    private float baseRadius;
    private float[] circleMaskRadii;
    private float[] circleOpacities;
    private Paint[] circlePaints;
    private float[] circleRadii;
    private Paint innerCirclePaint;
    private float maxMaskRadius;
    private float maxRadius;

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public PulsingCircleView(Context context) {
        super(context);
        init();
    }

    public PulsingCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PulsingCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setClickable(false);
        setFocusable(false);
        setEnabled(false);
        this.circlePaints = new Paint[4];
        this.circleRadii = new float[4];
        this.circleMaskRadii = new float[4];
        this.circleOpacities = new float[4];
        Paint paint = new Paint(1);
        this.backgroundPaint = paint;
        paint.setColor(BACKGROUND_COLOR);
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        this.innerCirclePaint = new Paint(1);
        int color = Color.parseColor(COLOR_AVATAR_WAVE);
        this.innerCirclePaint.setColor(color);
        this.innerCirclePaint.setStyle(Paint.Style.FILL);
        this.innerCirclePaint.setAlpha(25);
        float f = getResources().getDisplayMetrics().density;
        for (int i = 0; i < 4; i++) {
            Paint paint2 = new Paint(1);
            paint2.setColor(color);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAlpha(5);
            this.circlePaints[i] = paint2;
            this.circleRadii[i] = 0.0f;
            this.circleMaskRadii[i] = 0.0f;
            this.circleOpacities[i] = 0.02f;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        float f = getResources().getDisplayMetrics().density;
        float f2 = getResources().getDisplayMetrics().widthPixels;
        float f3 = getResources().getDisplayMetrics().heightPixels;
        float f4 = (f2 / 4.0f) - (30.0f * f);
        this.baseRadius = f4;
        float f5 = (120.0f * f) + f4;
        this.maxRadius = f5;
        float f6 = f * 1.0f;
        this.baseMaskRadius = f4 - f6;
        this.maxMaskRadius = f5 - f6;
        setMeasuredDimension((int) f2, (int) f3);
        for (int i3 = 0; i3 < 4; i3++) {
            this.circleRadii[i3] = this.baseRadius;
            this.circleMaskRadii[i3] = this.baseMaskRadius;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i <= 0 || i2 <= 0) {
            return;
        }
        startAnimation();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        canvas.drawCircle(width, height, this.baseMaskRadius, this.innerCirclePaint);
        for (int i = 0; i < 4; i++) {
            Paint paint = this.circlePaints[i];
            paint.setAlpha((int) (this.circleOpacities[i] * 255.0f));
            canvas.drawCircle(width, height, this.circleRadii[i], paint);
            float f = this.circleMaskRadii[i];
            if (f > this.baseMaskRadius) {
                canvas.drawCircle(width, height, f, this.innerCirclePaint);
            }
        }
    }

    private void startAnimation() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.animatorSet = new AnimatorSet();
        AnimatorSet.Builder builderPlay = null;
        for (final int i = 0; i < 3; i++) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.baseRadius, this.maxRadius);
            valueAnimatorOfFloat.setDuration(6000L);
            valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat.setRepeatCount(-1);
            valueAnimatorOfFloat.setRepeatMode(1);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kavkom.phone.PulsingCircleView.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PulsingCircleView.this.circleRadii[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    PulsingCircleView.this.invalidate();
                }
            });
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(this.baseMaskRadius, this.maxMaskRadius);
            valueAnimatorOfFloat2.setDuration(6000L);
            valueAnimatorOfFloat2.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat2.setRepeatCount(-1);
            valueAnimatorOfFloat2.setRepeatMode(1);
            valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kavkom.phone.PulsingCircleView.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PulsingCircleView.this.circleMaskRadii[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    PulsingCircleView.this.invalidate();
                }
            });
            ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.02f, 0.0f);
            valueAnimatorOfFloat3.setDuration(6000L);
            valueAnimatorOfFloat3.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat3.setRepeatCount(-1);
            valueAnimatorOfFloat3.setRepeatMode(1);
            valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kavkom.phone.PulsingCircleView.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PulsingCircleView.this.circleOpacities[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    PulsingCircleView.this.invalidate();
                }
            });
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2, valueAnimatorOfFloat3);
            animatorSet2.setStartDelay(i * 3000);
            if (builderPlay == null) {
                builderPlay = this.animatorSet.play(animatorSet2);
            } else {
                builderPlay.with(animatorSet2);
            }
        }
        if (builderPlay != null) {
            this.animatorSet.start();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        startAnimation();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.animatorSet = null;
        }
    }
}
