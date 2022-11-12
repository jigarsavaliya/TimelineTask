package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TRX-29887113653");
        Objects.requireNonNull(getSupportActionBar()).setSubtitle("4 August 2022");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomAdapter customAdapter = new CustomAdapter();

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
//        recyclerView.addItemDecoration(new TimeLineIndicatorItemDecoration());
        recyclerView.setAdapter(customAdapter);
        String json = "{\"status_steps\": [\n" +
                "                {\n" +
                "                    \"id\": \"2\",\n" +
                "                    \"status\": \"Payment Successful\",\n" +
                "                    \"info\": \"Using GoPay method\",\n" +
                "                    \"updated_at\": \"2022-06-17 16:27:21\",\n" +
                "                    \"step_status\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"4\",\n" +
                "                    \"status\": \"Order Processed\",\n" +
                "                    \"info\": \"2 hour estimation\",\n" +
                "                    \"updated_at\": \"2022-06-17 17:34:38\",\n" +
                "                    \"step_status\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"6\",\n" +
                "                    \"status\": \"Using express delivery\",\n" +
                "                    \"info\": \"Order Delivered\",\n" +
                "                    \"updated_at\": \"\",\n" +
                "                    \"step_status\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"7\",\n" +
                "                    \"status\": \"Your order has been received\",\n" +
                "                    \"info\": \"Order Delivered\",\n" +
                "                    \"updated_at\": \"\",\n" +
                "                    \"step_status\": \"0\"\n" +
                "                }\n" +
                "            ]}";


        Data data=new Gson().fromJson(json,Data.class);

        if (data.getStatusSteps().size() > 0)
            customAdapter.setArrayList(data.getStatusSteps());

    }


}