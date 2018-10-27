package org.fonuhuolian.cyclerollingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class XCycleRollingView extends RelativeLayout {


    private int autoScrollInterval = 2000;
    private ViewFlipper flipper;

    private boolean isFirst = true;
    private boolean isCanAdd = false;
    private boolean isAlreadyAdd = false;
    private List<View> list = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                isCanAdd = true;

                if (!isAlreadyAdd)
                    startAnims();
            }
        }
    };

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

        flipper.setAutoStart(true);
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
        ta.recycle();
    }


    public XCycleRollingView addItemView(View view) {
        list.add(view);
        return this;
    }

    public XCycleRollingView startAnim() {

        if (isFirst) {

            for (int i = 0; i < list.size(); i++) {
                flipper.addView(list.get(i));
            }

            isFirst = false;
        }


        // 防止绘制view过慢导致handler加入view失败
        if (isCanAdd)
            startAnims();

        return this;
    }

    private void startAnims() {

        isAlreadyAdd = true;

        for (int i = 0; i < list.size(); i++) {
            flipper.addView(list.get(i));
        }
    }


    public XCycleRollingView clearAllViews() {

        if (isFirst)
            return this;

        isCanAdd = false;
        isAlreadyAdd = false;
        flipper.removeAllViews();
        list.clear();

        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(1, 999);

        return this;
    }

}
