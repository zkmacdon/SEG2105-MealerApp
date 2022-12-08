package com.app.mealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<Searchresult> {
    public SearchAdapter(@NonNull Context context, ArrayList<Searchresult> searchResults){
        super(context,0,searchResults);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.search_item, parent, false);

        }
        Searchresult searchresult = getItem(position);
        TextView cookName=itemListView.findViewById(R.id.chefname);
        TextView mealName = itemListView.findViewById(R.id.meal_name);
        TextView mealDescription = itemListView.findViewById(R.id.meal_description);
        TextView mealPrice= itemListView.findViewById(R.id.meal_price);


        cookName.setText(searchresult.getCookId());
        mealName.setText(searchresult.getMealName());
        mealDescription.setText(searchresult.getMealDescription());
        mealPrice.setText(Double.toString(searchresult.getPrice()));

        return itemListView;}

}
