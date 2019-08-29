package com.wang.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2);

        final EditText Input_mA = findViewById(R.id.edit_mA);
        final TextView Output_Percent = findViewById(R.id.text_result_Percent);
        Button Cal_Percent = findViewById(R.id.btn_calc2);

        final EditText Input_zero = findViewById(R.id.editZero2);
        final EditText Input_span = findViewById(R.id.editSpan2);
        final EditText Input_Value = findViewById(R.id.editValue);
        Button Cal_result = findViewById(R.id.btn_calc_result);
        final TextView Output_result = findViewById(R.id.text_result);

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

        Cal_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float zero2 = Float.parseFloat(Input_zero.getText().toString());
                float span2 = Float.parseFloat(Input_span.getText().toString());
                float pressure = Float.parseFloat(Input_Value.getText().toString());
                //Toast.makeText(Calculator1.this, zero2 +"  "+ span2 +"  " + pressure, Toast.LENGTH_LONG).show();
                //y=mx+b
                float m = 16/(span2 - zero2);
                float y = (m * pressure) + 4;

                Output_result.setText(Float.toString(y) + " mA");
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
