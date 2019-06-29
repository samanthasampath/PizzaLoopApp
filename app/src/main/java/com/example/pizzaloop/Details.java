package com.example.pizzaloop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    private EditText Amount;
    private Button AddCart;
    private Button Back;
    float newprice,unitprice;
    int quantity=0;
    String pname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Amount=(EditText) findViewById(R.id.etamount);
        AddCart=(Button) findViewById(R.id.btnAddCart);
        Back=(Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        TextView name = (TextView) findViewById(R.id.tvname);
        pname=intent.getStringExtra("name");
        name.setText(pname);
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
               quantity=Integer.parseInt(Amount.getText().toString());
                   Intent intent = getIntent();
                   unitprice = intent.getFloatExtra("price",0);
                   newprice=quantity*unitprice;
                   TextView newpri = (TextView) findViewById(R.id.tvnewpris);
                   newpri.setText("Total Price:Rs."+newprice);
           }
       });

       AddCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            if(quantity!=0){
               addToCart();
            }else{
                Toast.makeText(Details.this,"Enter quantity value...", Toast.LENGTH_SHORT).show();
            }
           }
       });
       Back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(Details.this, FoodList.class));
           }
       });
    }

    public void addToCart() {
        String url ="http://192.168.8.179:8080/demo/addCart?pname="+ pname +"&quantity="+ quantity +"&fprice="+ newprice;
        RequestQueue requestQueue = Volley.newRequestQueue(Details.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                ,url
                ,new HTTPResponseListner(),
                new HTTPErrorListner()
        );
        requestQueue.add(stringRequest);
        startActivity(new Intent(Details.this, PaymentMethod.class));
    }
}
     class HTTPResponseListner implements Response.Listener<String>{
        @Override
        public void onResponse(String response){
            System.out.println("Data "+response+"....");
           // Toast.makeText(Details.this,"Successfully added to cart", Toast.LENGTH_SHORT).show();
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error....");
        }
    }
