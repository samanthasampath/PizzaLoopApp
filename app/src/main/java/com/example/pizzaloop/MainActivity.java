package com.example.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Signin;
    private TextView Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.etname);
        Password = findViewById(R.id.etpassword);
        Signup = findViewById(R.id.tvreg);
        Signin = findViewById(R.id.btnsignin);

        Signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadData(Name.getText().toString(),Password.getText().toString());
            }
        });
    }
    public void loadData(String Name,String Password){
        if(Name.equals("a") && Password.equals("1")){
            Toast.makeText(MainActivity.this,"Username and password is correct", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, FoodList.class);
            startActivity(intent);

        }else{
            Toast.makeText(MainActivity.this,"Username and password is NOT correct", Toast.LENGTH_SHORT).show();
        }
    }
}
