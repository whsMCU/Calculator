package com.wang.calculator.listview;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class ListData {
    private int picture;
    private String calculator_Name;
    private String calculator_Description;
    private Class<?> calculator_Activity;

    public ListData(int picture, String calculator_Name, String calculator_Description, Class<?> calculator_Activity) {
        this.picture = picture;
        this.calculator_Name = calculator_Name;
        this.calculator_Description = calculator_Description;
        this.calculator_Activity = calculator_Activity;
    }

    public int getPicture()
    {
        return this.picture;
    }

    public String getCalculator_Name()
    {
        return this.calculator_Name;
    }

    public String getCalculator_Description()
    {
        return this.calculator_Description;
    }

    public Class<?> getCalculator_Activity() {
        return calculator_Activity;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setCalculator_Name(String calculator_Name) {
        this.calculator_Name = calculator_Name;
    }

    public void setCalculator_Description(String calculator_Description) {
        this.calculator_Description = calculator_Description;
    }

    public void setCalculator_Activity(Class<?> calculator_Activity) {
        this.calculator_Activity = calculator_Activity;
    }
}
