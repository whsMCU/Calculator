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

public class Calculator extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final EditText zero = findViewById(R.id.editZero);
        final EditText span = findViewById(R.id.editSpan);
        final TextView result_0 = findViewById(R.id.text_result0);
        final TextView result_1 = findViewById(R.id.text_result1);
        final TextView result_2 = findViewById(R.id.text_result2);
        final TextView result_3 = findViewById(R.id.text_result3);
        final TextView result_4 = findViewById(R.id.text_result4);
        Button calc = findViewById(R.id.btn_calc1);
        Button back = findViewById(R.id.btn_back);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input_zero = Integer.parseInt(zero.getText().toString());
                int input_span = Integer.parseInt(span.getText().toString());
                float tmp = ((float)input_span - (float)input_zero) / 4;
                //Toast.makeText(Calculator.this, "Result"+tmp, Toast.LENGTH_LONG).show();
                result_0.setText("    0 % : "+Float.toString((float)input_zero));
                result_1.setText("  25 % : "+Float.toString(input_zero + tmp));
                result_2.setText("  50 % : "+Float.toString(input_zero + (2 * tmp)));
                result_3.setText("  75 % : "+Float.toString(input_zero + (3 * tmp)));
                result_4.setText("100 % : "+Float.toString((float)input_span));
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
