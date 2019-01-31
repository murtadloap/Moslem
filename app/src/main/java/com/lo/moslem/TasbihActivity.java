package com.lo.moslem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TasbihActivity extends AppCompatActivity {
    Button btnCounter, btnFinger;
    TextView tvCounter;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);

        btnCounter = findViewById(R.id.btnCounter);
        btnFinger = findViewById(R.id.btnFinger);
        tvCounter = findViewById(R.id.tvCounter);
    }

    public void countIn(View v) {
        counter++;
        tvCounter.setText(Integer.toString(counter));
    }

    public void btnReset (View v) {
        counter = 0;
        tvCounter.setText(String.valueOf(counter));
    }
}
