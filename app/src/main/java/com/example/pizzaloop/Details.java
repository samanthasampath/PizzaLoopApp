package com.example.pizzaloop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.tvname);
        name.setText(intent.getStringExtra("name"));
        TextView details = (TextView) findViewById(R.id.tvdes);
        details.setText(intent.getStringExtra("details"));
        TextView price = (TextView) findViewById(R.id.tvpris);
        price.setText("Rs."+intent.getFloatExtra("price",0) );

        ImageView image=(ImageView) findViewById(R.id.ivimg);
        Picasso.get().load(intent.getStringExtra("imgurl")).into(image);
    }
}
