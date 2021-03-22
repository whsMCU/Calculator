package com.wang.calculator.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.calculator.R;
import com.wang.calculator.listview.ListData;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ListData> calculator;

    public MyAdapter(Context context, ArrayList<ListData> data) {
        mContext = context;
        calculator = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return calculator.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ListData getItem(int position) {
        return calculator.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.activity_listview, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.picture);
        TextView movieName = (TextView)view.findViewById(R.id.calculator_Name);
        TextView grade = (TextView)view.findViewById(R.id.calculator_Description);

        imageView.setImageResource(calculator.get(position).getPicture());
        movieName.setText(calculator.get(position).getCalculator_Name());
        grade.setText(calculator.get(position).getCalculator_Description());

        return view;
    }
}
