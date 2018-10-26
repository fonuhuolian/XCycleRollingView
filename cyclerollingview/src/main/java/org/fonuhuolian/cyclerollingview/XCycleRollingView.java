package org.fonuhuolian.cyclerollingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class XCycleRollingView extends RelativeLayout {


    private boolean isAutoScroll = false;
    private int autoScrollInterval = 2000;
    private ViewFlipper flipper;

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
        flipper = (ViewFlipper) findViewById(R.id.scrollView);

        flipper.setAutoStart(isAutoScroll);
        flipper.setFlipInterval(autoScrollInterval);
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
        isAutoScroll = ta.getBoolean(R.styleable.XCycleRollingView_isAutoScroll, isAutoScroll);
        ta.recycle();
    }


    public XCycleRollingView addItemView(View view) {
        flipper.addView(view);
        return this;
    }


    public XCycleRollingView clearAllViews() {
        flipper.stopFlipping();
        flipper.removeAllViews();
        return this;
    }

}
