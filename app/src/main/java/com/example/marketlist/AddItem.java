package com.example.marketlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class AddItem extends AppCompatActivity {
    Item editItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Serializable item = getIntent().getSerializableExtra("item");
        if (item != null)
            editItem = (Item) item;
    }

    private void createItem(View view){
        Item newItem = new Item("", null);

        Intent returnIntent = new Intent();

        if (editItem == null)
            returnIntent.putExtra("item", newItem);
        else
            editItem = newItem;

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
