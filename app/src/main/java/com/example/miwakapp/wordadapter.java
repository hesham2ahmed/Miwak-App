package com.example.miwakapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class wordadapter extends ArrayAdapter<word> {


    private  int backgroundcolor;

    public wordadapter(Activity context, ArrayList<word> androidFlavors,int color) {
        super(context, 0, androidFlavors);
        backgroundcolor = color;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //
        // Check if the existing view is being reused, otherwise inflate the view

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        word currentAndroidWord = getItem(position);

        TextView enTextView =  convertView.findViewById(R.id.default_text_view);

        enTextView.setText(currentAndroidWord.getEnglishtrans());

        TextView miTextView =  convertView.findViewById(R.id.miwok_text_view);

        miTextView.setText(currentAndroidWord.getMiwok());

        ImageView image =  convertView.findViewById(R.id.image);

        //check if view has image
        if(currentAndroidWord.hasimage()) {
            image.setImageResource(currentAndroidWord.getImageId());
            image.setVisibility(View.VISIBLE);
        }
        else
            image.setVisibility(View.GONE);

        //To change color automatically
        View textcontainer = convertView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(),backgroundcolor);

        textcontainer.setBackgroundColor(color);

        return convertView;
    }

}
