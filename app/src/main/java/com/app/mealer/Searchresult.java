package com.app.mealer;

import android.widget.Button;

public class Searchresult {
    private String MealName;
    private double Price;
    private String CookID;
    private String MealDescription;
    private boolean IsOffered;
    private int Rating;


    public Searchresult(){}
    public Searchresult(String cookID,String mealname,double price, boolean isOffered, String mealDescription,int rating){
        MealName=mealname;
        Price=price;
        IsOffered=isOffered;
        MealDescription=mealDescription;
        CookID=cookID;
        Rating=rating;
    }
    public String getCookId(){return CookID;}
    public void setCookId(String id){CookID=id;}
    public String getMealName(){return MealName;};
    public void setMealName(String name){MealName=name;}
    public int getRating(){return Rating;}
    public String getMealDescription(){return MealDescription;};
    public void setMealDescription(String des){MealDescription=des;}
    public boolean isAvailable(){return IsOffered;}
    public void setAvailable(boolean a){IsOffered=a;}
    public double getPrice(){return Price;}
    public void setPrice(double pr){Price=pr;};
}
