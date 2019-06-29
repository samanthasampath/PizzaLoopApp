package com.example.pizzaloop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {
    ListView listview;
    private List<Pizza> pizzaDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        listview = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.activity_list_item);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listview.getItemAtPosition(position);
                Pizza pizza = (Pizza) o;
                Intent intent = new Intent(view.getContext(), Details.class);
                intent.putExtra("name",pizza.getName());
                intent.putExtra("details",pizza.getDetails());
                intent.putExtra("price",pizza.getPrice());
                intent.putExtra("imgurl",pizza.getImageURL());
                startActivityForResult(intent, 0);

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.8.179:8080/demo/all";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(request);
    }

    private class HTTPResponseListner implements Response.Listener<JSONArray>{
        @Override
        public void onResponse(JSONArray jsonArray) {
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object= jsonArray.getJSONObject(i);
                    Pizza pizza = new Pizza();
                    pizza.setPizzaId(Integer.parseInt(object.get("pizzaId").toString()));
                    pizza.setName(object.get("name").toString());
                    pizza.setPrice(Float.parseFloat(object.get("price").toString()));
                    pizza.setImageURL(object.get("imageUrl").toString());
                    pizza.setDetails(object.get("description").toString());
                    pizzaDetails.add(pizza);

                    ListView pizzaList = findViewById(R.id.listView);
                    CustomAdapter listAdapter = new CustomAdapter(getApplicationContext(), R.layout.list_item, pizzaDetails);
                    pizzaList.setAdapter(listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
    }

    private class CustomAdapter extends ArrayAdapter<Pizza> {
        private List<Pizza> itemsList;

        CustomAdapter(Context context, int resource, List<Pizza> items) {
            super(context, resource, items);
            itemsList = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            Pizza food = itemsList.get(position);
            TextView tvdes = convertView.findViewById(R.id.tvdec);
            TextView tvpri = convertView.findViewById(R.id.tvpri);
            ImageView ivpic = convertView.findViewById(R.id.ivpic);
            //assign
            Picasso.get().load(food.getImageURL()).into(ivpic);
            tvdes.setText(food.getName());
            tvpri.setText("Rs." + food.getPrice());
            return convertView;
        }
    }
}
