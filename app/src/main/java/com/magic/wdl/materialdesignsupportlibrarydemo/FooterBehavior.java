package com.magic.wdl.materialdesignsupportlibrarydemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdongliang on 16/12/15.
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {
    private boolean visible = false;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0 || dyUnconsumed > 0) {
            moveOupAnimation(child);
        }
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        if (velocityY < -0.5) {
            moveInAnimation(child);
        } else {
            moveOupAnimation(child);
        }

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    private void moveInAnimation(View v) {
        if (!visible) {
            v.setVisibility(View.VISIBLE);

            ObjectAnimator moveTopAnim = ObjectAnimator.ofFloat(v, "translationY",
                   v.getHeight(), 0);
            moveTopAnim.setDuration(300);
            moveTopAnim.start();

            visible = true;
        }
    }

    private void moveOupAnimation(View v) {
        if (visible) {
            ObjectAnimator moveTopAnim = ObjectAnimator.ofFloat(v, "translationY",
                    0, v.getHeight());
            moveTopAnim.setDuration(300);
            moveTopAnim.start();

            visible = false;
        }
    }
}
