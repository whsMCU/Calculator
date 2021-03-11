package com.wang.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListData> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, movieDataList);

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(i).getMovieName(),
                        Toast.LENGTH_LONG).show();
            }
        });

        Button btn_target = findViewById(R.id.btn_target);
        Button btn_calc =findViewById(R.id.btn_calc);

        btn_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculator.class);
                startActivity(intent);
            }
        });

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculator1.class);
                startActivity(intent);
            }
        });



    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<ListData>();

        movieDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "미션임파서블","15세 이상관람가"));
        movieDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "아저씨","19세 이상관람가"));
        movieDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "어벤져스","12세 이상관람가"));
    }


}
