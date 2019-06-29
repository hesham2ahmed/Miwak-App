package com.example.miwakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView number = findViewById(R.id.numbers);
        TextView colors = findViewById(R.id.colors);
        TextView familiy = findViewById(R.id.family);
        TextView ph = findViewById(R.id.phrases);


        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent number = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(number);
            }
        });


        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent col=new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(col);
            }
        });


        familiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fm=new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(fm);
            }
        });


        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p=new Intent(MainActivity.this,PharsesActivity.class);
                startActivity(p);
            }
        });

    }

}
