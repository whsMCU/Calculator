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

public class Calculator3 extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc3);

        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final EditText Input_zero = findViewById(R.id.editZero2);
        final EditText Input_span = findViewById(R.id.editSpan2);
        final EditText Input_mA = findViewById(R.id.editmA);
        Button Cal_result = findViewById(R.id.btn_calc_result);
        final TextView Output_result = findViewById(R.id.text_result);

        Button back2 = findViewById(R.id.btn_back2);


        Cal_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float zero2 = Float.parseFloat(Input_zero.getText().toString());
                float span2 = Float.parseFloat(Input_span.getText().toString());
                float mA = Float.parseFloat(Input_mA.getText().toString());
                //Toast.makeText(Calculator1.this, zero2 +"  "+ span2 +"  " + pressure, Toast.LENGTH_LONG).show();
                float m, y;

                if(zero2 < 0){
                    m = (span2 - zero2)/16;
                    y = (m * (mA - 4)) - zero2;
                }else{
                    m = (span2 - zero2)/16;
                    y = m * (mA - 4);
                }

                Output_result.setText(Float.toString(y) + " Value");
            }
        });


        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
