package com.project.archives.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.project.archives.R;


/**
 * Created by Lxuyang on 2016/1/25.
 *
 */
public class SpinView extends ImageView {

    private float mRotateDegrees;
    private Animation anim;

    public SpinView(Context context) {
        super(context);

        init(context);
    }

    public SpinView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        setImageResource(R.drawable.progress_hud_spinner);

        setAdjustViewBounds(true);
        setMaxHeight(72);
        setMaxWidth(72);

        anim = AnimationUtils.loadAnimation(context, R.anim.progressbar);
        setAnimation(anim);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
