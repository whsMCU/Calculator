package com.wang.calculator;

public class ListData {
    private int picture;
    private String calculator_Name;
    private String calculator_Description;

    public ListData(int picture, String calculator_Name, String calculator_Description){
        this.picture = picture;
        this.calculator_Name = calculator_Name;
        this.calculator_Description = calculator_Description;
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
}
