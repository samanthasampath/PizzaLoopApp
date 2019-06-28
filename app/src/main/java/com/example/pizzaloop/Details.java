package com.example.pizzaloop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    private EditText Amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Amount=(EditText) findViewById(R.id.etamount);
        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.tvname);
        name.setText(intent.getStringExtra("name"));
        TextView details = (TextView) findViewById(R.id.tvdes);
        details.setText(intent.getStringExtra("details"));
        TextView price = (TextView) findViewById(R.id.tvpris);
        price.setText("Unit Price:Rs."+intent.getFloatExtra("price",0) );

        ImageView image=(ImageView) findViewById(R.id.ivimg);
        Picasso.get().load(intent.getStringExtra("imgurl")).into(image);
        System.out.println(intent.getStringExtra("imgurl"));
       Amount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               float quantity=Integer.parseInt(Amount.getText().toString());
               if(Amount!=null){
                   Intent intent = getIntent();
                   float unitprice = intent.getFloatExtra("price",0);
                   float newprice=quantity*unitprice;
                   TextView newpri = (TextView) findViewById(R.id.tvnewpris);
                   newpri.setText("Total Price:Rs."+newprice);

               }else{
                   Toast.makeText(Details.this,"Enter quantity value", Toast.LENGTH_SHORT).show();
               }

           }
       });
    }
}
