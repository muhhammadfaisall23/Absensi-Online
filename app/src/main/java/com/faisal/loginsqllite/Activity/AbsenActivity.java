package com.faisal.loginsqllite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.faisal.loginsqllite.R;

public class AbsenActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout absen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        absen = findViewById(R.id.LineAbsen);

        absen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LineAbsen:
                Intent i = new Intent(AbsenActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(this, "Berhasil Absen", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
