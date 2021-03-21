package com.wang.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Calculator1 extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2);

        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final EditText Input_mA = findViewById(R.id.edit_mA);
        final TextView Output_Percent = findViewById(R.id.text_result_Percent);
        Button Cal_Percent = findViewById(R.id.btn_calc2);

        Button back1 = findViewById(R.id.btn_back1);

        Cal_Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mA = Integer.parseInt(Input_mA.getText().toString());

                float tmp2 = (((float) mA - 4) / (20 - 4)) * 100;
                Output_Percent.setText(Float.toString(tmp2) + " %");

                //Toast.makeText(Calculator1.this, "result "+ mA +" "+ tmp, Toast.LENGTH_LONG).show();
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
