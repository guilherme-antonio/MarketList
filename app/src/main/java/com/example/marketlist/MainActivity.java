package com.example.marketlist;

import android.app.Activity;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> items;
    List<Item> missingItems;
    ArrayAdapter<Item> adaptador;
    ListView itemsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsView = findViewById(R.id.items);

        items = new ArrayList<>();
        missingItems = new ArrayList<>();

        adaptador = new CustomAdapter(MainActivity.this, R.layout.item, items);

        itemsView.setAdapter(adaptador);
    }

    public void createNewItem(View view){
        Intent intent = new Intent(this, AddItem.class);
        startActivityForResult(intent, 1);
    }

    public void startList(View view){
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("items", (Serializable) missingItems);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode == Activity.RESULT_OK){
                    Item newItem = (Item) data.getSerializableExtra("item");

                    if (Collections.frequency(items, newItem) == 0)
                        adaptador.add(newItem);
                }
                break;
            case 2:
                if(resultCode == Activity.RESULT_OK){
                    ArrayList<Item> removedItems = (ArrayList<Item>) data.getSerializableExtra("items");

                    missingItems.removeAll(removedItems);

                    adaptador.notifyDataSetChanged();
                }

                break;
        }
    }

    public void removeItem(View view) {
        int position = (int) view.getTag();

        Item itemToRemove = items.get(position);

        missingItems.remove(itemToRemove);
        adaptador.remove(itemToRemove);
    }

    public void addToMissings(View view) {
        int position = (int) view.getTag();

        Item missingItem = items.get(position);
        if (Collections.frequency(missingItems, missingItem) == 0)
            missingItems.add(missingItem);
    }
}
