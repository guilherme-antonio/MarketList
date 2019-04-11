package com.example.marketlist;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    List<Item> items;
    List<Item> checkedItems;
    ArrayAdapter<Item> adaptador;
    ListView itemsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemsView = findViewById(R.id.items);

        items = (ArrayList<Item>) getIntent().getSerializableExtra("items");

        checkedItems = new ArrayList<>();

        adaptador = new MissingItemAdapter(ListActivity.this, R.layout.missing_item, items);

        itemsView.setAdapter(adaptador);

        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = view.findViewById(R.id.missing_item_check);

                checkBox.performClick();
            }
        });
    }

    public void checkItem(View view)
    {
        int position = (int) view.getTag();

        Item checkedItem = items.get(position);

        if (((CheckBox) view).isChecked())
            checkedItems.add(checkedItem);
        else
            checkedItems.remove(checkedItem);
    }

    public void endList(View view)
    {
        Intent returnIntent = new Intent();

        returnIntent.putExtra("items", (Serializable) checkedItems);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
