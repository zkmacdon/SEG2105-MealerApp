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
;

import java.util.ArrayList;

public class ComplaintAdapter extends ArrayAdapter<Complaint>{
    public ComplaintAdapter(@NonNull Context context, ArrayList<Complaint> complaintList){
        super(context, 0, complaintList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.complaint_item, parent, false); // need to make complaint_item.xml and add the stuff below

        }
        Complaint complaint = getItem(position);
        TextView complaintCook = itemListView.findViewById(R.id.cookNameText);
        TextView complaintStatus = itemListView.findViewById(R.id.complaintText);
        TextView complainttext= itemListView.findViewById(R.id.statusText);



        complaintCook.setText(complaint.getName());
        complaintStatus.setText(complaint.getStatus());
        complainttext.setText(complaint.getComplaint());

        return itemListView;
    }

}
