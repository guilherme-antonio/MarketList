package com.example.marketlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class AddItem extends AppCompatActivity {
    Item editItem;
    EditText itemName;
    ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemName = findViewById(R.id.ItemName);
        itemImage = findViewById(R.id.ItemImage);

        Serializable item = getIntent().getSerializableExtra("item");
        if (item != null)
        {
            editItem = (Item) item;

            itemName.setText(editItem.getName());
            itemImage.setImageDrawable(editItem.getImage());
        }
    }

    public void createItem(View view){
        if (TextUtils.isEmpty(itemName.getText()))
        {
            itemName.setError("O campo nome é obrigatório");
            return;
        }

        Item newItem = new Item(itemName.getText().toString(), itemImage.getDrawable());

        Intent returnIntent = new Intent();

        if (editItem == null)
            returnIntent.putExtra("item", newItem);
        else
            editItem = newItem;

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
