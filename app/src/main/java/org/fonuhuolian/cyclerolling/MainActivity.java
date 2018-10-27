package org.fonuhuolian.cyclerolling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.fonuhuolian.cyclerollingview.XCycleRollingView;

public class MainActivity extends AppCompatActivity {

    XCycleRollingView xv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xv = (XCycleRollingView) findViewById(R.id.xv);

        xv.clearAllViews();

        for (int i = 0; i < 5; i++) {

            View inflate = View.inflate(this, R.layout.a, null);

            xv.addItemView(inflate);
        }

        xv.startAnim();
    }

    public void btn(View view) {
        xv.clearAllViews();
        for (int i = 0; i < 5; i++) {

            View inflate = View.inflate(this, R.layout.b, null);
            xv.addItemView(inflate);
        }

        xv.startAnim();
    }
}
