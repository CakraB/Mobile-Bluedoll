package com.cakrab.project_mobile_bluedoll;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cakrab.project_mobile_bluedoll.Database.DollHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DollAdapter extends BaseAdapter {

    // Init
    ArrayList<Doll> dolls;
    Context context;
    LayoutInflater inflater;
    DollHelper dbDoll;

    // Constructor Doll Adapter
    DollAdapter(Context context, LayoutInflater inflater, ArrayList<Doll> dolls) {
        this.context = context;
        this.inflater = inflater;
        this.dolls = dolls;
    }

    // Array List size
    @Override
    public int getCount() {
        return dolls.size();
    }

    // Get Doll Object Position from Array List
    @Override
    public Object getItem(int position) {
        return dolls.get(position);
    }

    // Get Doll Index from Array
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Delete Doll Method
    public void deleteItem(Doll position) {
        dolls.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Checking if view is not available and get view from layout
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_doll, parent, false);
        }
        // Checking if data is not available and set view to invisible
        if (dolls.isEmpty()) {
            convertView.setVisibility(View.INVISIBLE);
        } else {
            convertView.setVisibility(View.VISIBLE);
            // Get Doll Position based on index array
            Doll thisDoll = dolls.get(position);
            // Init Component
            ImageView dollImage = convertView.findViewById(R.id.image_item);
            TextView dollName = convertView.findViewById(R.id.text_item_name);
            TextView dollCreator = convertView.findViewById(R.id.text_item_creator);
            TextView dollDescription = convertView.findViewById(R.id.text_item_description);
            Button itemView = convertView.findViewById(R.id.button_item_view);
            Button itemEdit = convertView.findViewById(R.id.button_item_edit);
            Button itemDelete = convertView.findViewById(R.id.button_item_delete);
            dbDoll = new DollHelper(context.getApplicationContext());
            // Mapping Image by String Image from database
            Map<String, Integer> setImageView = new HashMap<>();
            setImageView.put("Brown Doll",R.drawable.img_brown);
            setImageView.put("Panda Doll",R.drawable.img_panda);
            setImageView.put("White Doll",R.drawable.img_white);
            // Set Value
            dollImage.setImageResource(setImageView.get(thisDoll.getDollImage()));
            dollName.setText(thisDoll.getDollName());
            dollCreator.setText(thisDoll.getDollCreator());
            dollDescription.setText(thisDoll.getDollDescription());
            // Move to Detail Activity and Send Doll Data
            itemView.setOnClickListener(view -> {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("DOLL IMAGE", thisDoll.getDollImage());
                i.putExtra("DOLL NAME", thisDoll.getDollName());
                i.putExtra("DOLL CREATOR", thisDoll.getDollCreator());
                i.putExtra("DOLL DESC", thisDoll.getDollDescription());
                context.startActivity(i);
            });

            // Move to Modify Activity and Send Doll Data
            itemEdit.setOnClickListener(view -> {
                Intent i = new Intent(context, ModifyActivity.class);
                i.putExtra("DOLL ID", thisDoll.getDollId());
                i.putExtra("DOLL IMAGE", thisDoll.getDollImage());
                i.putExtra("DOLL NAME", thisDoll.getDollName());
                i.putExtra("DOLL CREATOR", thisDoll.getDollCreator());
                i.putExtra("DOLL DESC", thisDoll.getDollDescription());
                context.startActivity(i);
            });

            // Remove Doll Data Based on Position in Arraylist
            itemDelete.setOnClickListener(view -> {
                String getId = thisDoll.getDollId();
                dbDoll.deleteDoll(getId);
                deleteItem(thisDoll);
                Toast.makeText(context, thisDoll.getDollName() + " Delete Successfully", Toast.LENGTH_SHORT).show();
            });
        }
        return convertView;
    }
}
