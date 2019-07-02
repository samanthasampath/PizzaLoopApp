package com.example.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UserRegistration extends AppCompatActivity {
    private TextView Home;
    private EditText name,password,confPass;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Home =  (TextView)findViewById(R.id.tvsign);
        name =(EditText)findViewById(R.id.etusername);
        password =(EditText)findViewById(R.id.etpass);
        confPass =(EditText)findViewById(R.id.etconfpass);
        SignUp = (Button)findViewById(R.id.btnreg);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserRegistration.this, MainActivity.class));
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(confPass.getText().toString())){
                    AddUser();
                }else{
                    Toast.makeText(UserRegistration.this,"Passwords are not equal...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddUser() {
        String url ="http://192.168.137.1:8080/demo/addLog?username="+ name.getText().toString() +"&password="+ password.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(UserRegistration.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,url,new UserRegistration.HTTPResponseListner(), new UserRegistration.HTTPErrorListner()
        );
        requestQueue.add(stringRequest);
        startActivity(new Intent(UserRegistration.this, MainActivity.class));
    }

    class HTTPResponseListner implements Response.Listener<String>{
        @Override
        public void onResponse(String response){
            Toast.makeText(UserRegistration.this,"Successfully Registered", Toast.LENGTH_SHORT).show();
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(UserRegistration.this,"Registration Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
