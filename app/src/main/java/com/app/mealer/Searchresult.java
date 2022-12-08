package com.app.mealer;

public class Searchresult {
    private String MealName;
    private double Price;
    private String CookID;
    private String MealDescription;
    private boolean IsOffered;

    public Searchresult(){}
    public Searchresult(String cookID,String mealname,double price, boolean isOffered, String mealDescription){
        MealName=mealname;
        Price=price;
        IsOffered=isOffered;
        MealDescription=mealDescription;
        CookID=cookID;
    }
    public String getCookId(){return CookID;}
    public void setCookId(String id){CookID=id;}
    public String getMealName(){return MealName;};
    public void setMealName(String name){MealName=name;}

    public String getMealDescription(){return MealDescription;};
    public void setMealDescription(String des){MealDescription=des;}
    public boolean isAvailable(){return IsOffered;}
    public void setAvailable(boolean a){IsOffered=a;}
    public double getPrice(){return Price;}
    public void setPrice(double pr){Price=pr;};
}
