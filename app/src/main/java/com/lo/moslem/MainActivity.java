package com.lo.moslem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lo.moslem.jadwal_sholat.JadwalSholatActivity;
import com.lo.moslem.quran.QuranActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnQuran, btnJadwalSholat, btnArahKiblat, btnTasbih;
    private Intent move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuran = findViewById(R.id.btnQuran);
        btnJadwalSholat = findViewById(R.id.btnJadwalSholat);
        btnArahKiblat = findViewById(R.id.btnArahKiblat);
        btnTasbih = findViewById(R.id.btnTasbih);

        btnQuran.setOnClickListener(this);
        btnJadwalSholat.setOnClickListener(this);
        btnArahKiblat.setOnClickListener(this);
        btnTasbih.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnQuran) {
            move = new Intent(this, QuranActivity.class);
            startActivity(move);
        } else if (v.getId() == R.id.btnJadwalSholat) {
            move = new Intent(this, JadwalSholatActivity.class);
            startActivity(move);
        } else if (v.getId() == R.id.btnArahKiblat) {
            move = new Intent(this, KiblatActivity.class);
            startActivity(move);
        } else if (v.getId() == R.id.btnTasbih) {
            move = new Intent(this, TasbihActivity.class);
            startActivity(move);
        }
    }
}
