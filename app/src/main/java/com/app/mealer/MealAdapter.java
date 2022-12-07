package com.app.mealer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MealAdapter extends ArrayAdapter<MealData>{
    public MealAdapter(@NonNull Context context, ArrayList<MealData> mealData){
        super(context,0,mealData);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.meal_item, parent, false);

        }
        MealData mealData = getItem(position);
        TextView mealName = itemListView.findViewById(R.id.meal_name);
        TextView mealDescription = itemListView.findViewById(R.id.meal_description);
        TextView mealPrice= itemListView.findViewById(R.id.meal_price);



        mealName.setText(mealData.getMealName());
        mealDescription.setText(mealData.getMealDescription());
        mealPrice.setText(Double.toString(mealData.getPrice()));

        return itemListView;
    }
}
