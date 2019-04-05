package com.example.marketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    List<Item> items;
    List<Item> CheckedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void addToMissings(View view) {
        int position = (int) view.getTag();

        Item checkedItem = items.get(position);
        CheckedItems.add(checkedItem);
    }
}
