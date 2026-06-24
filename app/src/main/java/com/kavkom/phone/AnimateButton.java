package com.kavkom.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes2.dex */
public class AnimateButton extends AppCompatImageView {
    private Animation mAnimation;

    public AnimateButton(Context context) {
        super(context);
    }

    public AnimateButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimateButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private View.OnClickListener getItemClickListener(final View.OnClickListener onClickListener) {
        return new View.OnClickListener() { // from class: com.kavkom.phone.AnimateButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                AnimateButton animateButton = AnimateButton.this;
                animateButton.mAnimation = animateButton.getAnimation();
                if (AnimateButton.this.mAnimation == null || AnimateButton.this.mAnimation.hasEnded()) {
                    if (AnimateButton.this.mAnimation == null) {
                        AnimateButton animateButton2 = AnimateButton.this;
                        animateButton2.mAnimation = animateButton2.createItemDisappearAnimation(100L, true);
                        AnimateButton.this.mAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.kavkom.phone.AnimateButton.1.1
                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationRepeat(Animation animation) {
                            }

                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationEnd(Animation animation) {
                                if (onClickListener != null) {
                                    onClickListener.onClick(view);
                                }
                            }
                        });
                    }
                    AnimateButton animateButton3 = AnimateButton.this;
                    animateButton3.startAnimation(animateButton3.mAnimation);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation createItemDisappearAnimation(long j, boolean z) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.5f));
        animationSet.setDuration(j);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setFillAfter(false);
        return animationSet;
    }
}
