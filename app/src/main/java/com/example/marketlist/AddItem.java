package com.example.marketlist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;
import java.net.URI;

public class AddItem extends AppCompatActivity {
    EditText itemName;
    ImageView itemImage;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemName = findViewById(R.id.ItemName);
        itemImage = findViewById(R.id.ItemImage);

        imageUri = null;

        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(
                        Intent.createChooser(intent, "Complete action using"),
                        1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        switch (requestCode)
        {
            case 1:
                imageUri = imageReturnedIntent.getData();

                itemImage.setImageURI(imageUri);
                break;
        }
    }

    private String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }

    public void createItem(View view){
        if (TextUtils.isEmpty(itemName.getText()))
        {
            itemName.setError(getResources().getString(R.string.field_name_is_required));
            return;
        }

        String imagePath = imageUri != null ? imageUri.getPath() : null;

        Item newItem = new Item(itemName.getText().toString(), imagePath);

        Intent returnIntent = new Intent();

        returnIntent.putExtra("item", newItem);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
