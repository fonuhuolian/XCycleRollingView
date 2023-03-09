package org.fonuhuolian.cyclerollingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class XCycleRollingView extends RelativeLayout {


    private int autoScrollInterval = 2000;
    private ViewFlipper flipper1;
    private ViewFlipper flipper2;

    private boolean isVisiableFlipper1 = true;


    public XCycleRollingView(Context context) {
        this(context, null);
    }

    public XCycleRollingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCycleRollingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getAttrs(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.x_cycle_rolling_layout, this, true);
        flipper1 = (ViewFlipper) findViewById(R.id.scrollView1);
        flipper2 = (ViewFlipper) findViewById(R.id.scrollView2);

        flipper1.setAutoStart(true);
        flipper1.setFlipInterval(autoScrollInterval);
        flipper2.setAutoStart(true);
        flipper2.setFlipInterval(autoScrollInterval);
    }

    /**
     * 得到属性值
     *
     * @param context
     * @param attrs
     */
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XCycleRollingView);
        autoScrollInterval = ta.getInteger(R.styleable.XCycleRollingView_autoScrollInterval, autoScrollInterval);
        ta.recycle();
    }


    public XCycleRollingView addItemView(View view) {
        if (isVisiableFlipper1) {
            flipper1.addView(view);
            if (flipper1.getChildCount() > 1)
                flipper1.startFlipping();
            else
                flipper1.stopFlipping();
        } else {
            flipper2.addView(view);
            if (flipper2.getChildCount() > 1)
                flipper2.startFlipping();
            else
                flipper2.stopFlipping();
        }
        return this;
    }


    public XCycleRollingView clearAllViews() {

        flipper1.removeAllViews();
        flipper2.removeAllViews();

        if (isVisiableFlipper1) {
            flipper1.setVisibility(GONE);
            flipper2.setVisibility(VISIBLE);
            isVisiableFlipper1 = false;
        } else {
            flipper1.setVisibility(VISIBLE);
            flipper2.setVisibility(GONE);
            isVisiableFlipper1 = true;
        }

        return this;
    }


    public void onResume() {
        if (flipper1.getChildCount() > 1)
            flipper1.startFlipping();
        if (flipper2.getChildCount() > 1)
            flipper2.startFlipping();
    }

    public void onPause() {
        flipper1.stopFlipping();
        flipper2.stopFlipping();
    }

}
