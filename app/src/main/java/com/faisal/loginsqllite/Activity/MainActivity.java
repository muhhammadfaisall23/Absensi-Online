package com.faisal.loginsqllite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.faisal.loginsqllite.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView cvprofile,cvkehadiran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cvprofile = findViewById(R.id.profile);
        cvkehadiran = findViewById(R.id.kehadiran);


        cvprofile.setOnClickListener(this);
        cvkehadiran.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.profile:
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
        break;
        case R.id.kehadiran:
        Intent ii = new Intent(MainActivity.this, AbsenActivity.class);
        startActivity(ii);
        break;
        }
    }
}
