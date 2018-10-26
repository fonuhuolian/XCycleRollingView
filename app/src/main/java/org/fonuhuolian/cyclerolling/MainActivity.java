package org.fonuhuolian.cyclerolling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.fonuhuolian.cyclerollingview.XCycleRollingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XCycleRollingView view = (XCycleRollingView) findViewById(R.id.xv);


        for (int i = 0; i < 5; i++) {

            View inflate = View.inflate(this, R.layout.a, null);

            view.addItemView(inflate);
        }
    }
}
