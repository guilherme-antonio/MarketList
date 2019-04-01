package com.example.marketlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> items;
    ArrayAdapter<Item> adaptador;
    ListView itemsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsView = findViewById(R.id.items);

        items = new ArrayList<>();

        adaptador = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, items);
        itemsView.setAdapter(adaptador);
        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), AddItem.class);
                //intent.putExtra("position", position);
                intent.putExtra("item", items.get(position));
                startActivityForResult(intent, 2);
            }
        });
    }

    private void createNewItem(View view){
        Intent intent = new Intent(this, AddItem.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Item newItem = (Item) data.getSerializableExtra("item");

                if (Collections.frequency(items, new Item(newItem.getName(), null)) == 0)
                    adaptador.add(newItem);
            }
        }
    }
}
