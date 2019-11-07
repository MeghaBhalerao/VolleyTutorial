package com.example.volleytutorial.json_array_request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.volleytutorial.R;

import java.util.ArrayList;

public class JsonArrayRequestActivity extends AppCompatActivity {

    RecyclerView
            list_rv;

    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array_request);


        list_rv = (RecyclerView)findViewById(R.id.list_rv);
        layoutManager = new LinearLayoutManager(this);
        list_rv.setLayoutManager(layoutManager);
        list_rv.setHasFixedSize(true);


    }

    public void displayList(View view) {

        BackgroundTask backgroundTask = new BackgroundTask(JsonArrayRequestActivity.this);
        backgroundTask.getList(new ReturnList() {
            @Override
            public void returnList(ArrayList<Contact> contacts) {

                adapter = new RecyclerAdapter(contacts);
                list_rv.setAdapter(adapter);
            }
        });

    }

}
