package com.example.kasparas.choseit;

/**
 * Created by Edvinas.Barickis on 11/24/2015.
 */
public class RestaurantListModel {

    private  String Name="";
    private  String Description="";
    private  String Image="";

    /*********** Set Methods ******************/

    public void setRestaurantName(String name)
    {
        Name = name;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public void setImage(String image)
    {
        Image = image;
    }

    /*********** Get Methods ****************/

    public String getRestaurantName()
    {
        return Name;
    }

    public String getDescription()
    {
        return Description;
    }
    public String getImage()
    {
        return Image;
    }
}