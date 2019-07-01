package com.example.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PaymentMethod extends AppCompatActivity {
    private RadioGroup method;
    private RadioButton card;
    private RadioButton cash;
    private Button submit;
    private Button  viewCart;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        card=findViewById(R.id.rbtnCard);
        cash=findViewById(R.id.rbtnCash);
        submit=findViewById(R.id.btnSubmit);
        method=findViewById(R.id.rgMethod);
        viewCart=findViewById(R.id.btnViewCart);


        method.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbtnCard:
                        count=1;
                        break;
                    case R.id.rbtnCash:
                        count=2;
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==1){
                    startActivity(new Intent(PaymentMethod.this, CardDetails.class));
                    Toast.makeText(PaymentMethod.this," Card payment option selected", Toast.LENGTH_SHORT).show();
                }
                else if(count==2){
                    startActivity(new Intent(PaymentMethod.this, MapDirection.class));
                    Toast.makeText(PaymentMethod.this," Cash on Delivery payment option selected", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PaymentMethod.this,"Please Select a method", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMethod.this, CartView.class));
            }
        });


    }
}
