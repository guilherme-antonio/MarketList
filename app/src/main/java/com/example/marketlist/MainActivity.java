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

        adaptador = new ArrayAdapter<Item>(MainActivity.this, R.layout.item, items)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = getLayoutInflater()
                        .inflate(R.layout.item, parent, false);


                Item item = items.get(position);

                TextView itemName = view.findViewById(R.id.list_item_name);
                ImageView itemImage = view.findViewById(R.id.list_item_image);

                itemName.setText(item.getName());
                itemImage.setImageDrawable(item.getImage());

                return view;
            }
        };
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
