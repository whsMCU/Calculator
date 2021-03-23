package com.wang.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.wang.calculator.listview.ListData;
import com.wang.calculator.listview.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    ArrayList<ListData> calculatorDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, calculatorDataList);

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), myAdapter.getItem(i).getCalculator_Name(), Toast.LENGTH_LONG).show();

                if(myAdapter.getItem(i).getCalculator_Activity() == Calculator.class){
                    Intent intent = new Intent(MainActivity.this, Calculator.class);
                    startActivity(intent);
                }else if(myAdapter.getItem(i).getCalculator_Activity() == Calculator1.class){
                    Intent intent = new Intent(MainActivity.this, Calculator1.class);
                    startActivity(intent);
                }else if(myAdapter.getItem(i).getCalculator_Activity() == Calculator2.class){
                    Intent intent = new Intent(MainActivity.this, Calculator2.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void InitializeMovieData()
    {
        calculatorDataList = new ArrayList<ListData>();

        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "0~100% 보기","LRV와 URV를 입력하면 25% 단위로 출력", Calculator.class));
        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "mA to percent 계산","mA를 입력하면 %로 출력", Calculator1.class));
        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "mA로 계산","Zero, Span, Value를 입력하면 mA로 출력", Calculator2.class));
    }


}
