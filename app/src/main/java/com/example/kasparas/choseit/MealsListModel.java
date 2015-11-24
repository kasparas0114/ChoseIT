package com.example.kasparas.choseit;

/**
 * Created by kaspa on 11/24/2015.
 */
public class MealsListModel {

    private  String Name="";
    private  String Description="";

    /*********** Set Methods ******************/

    public void setMealName(String name)
    {
        Name = name;
    }

    public void setPrice(String description)
    {
        Description = description;
    }

    /*********** Get Methods ****************/

    public String getMealName()
    {
        return Name;
    }

    public String getPrice()
    {
        return Description;
    }
}