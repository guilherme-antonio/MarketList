package com.example.marketlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    private Context _context;

    public CustomAdapter(Context context, int resource, List<Item> objects)
    {
        super(context, resource, objects);

        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(_context).inflate(R.layout.item, parent,false);

        Item item = getItem(position);

        TextView itemName = view.findViewById(R.id.list_item_name);
        ImageView itemImage = view.findViewById(R.id.list_item_image);
        ImageButton itemRemoveButton = view.findViewById(R.id.list_item_remove);
        ImageButton addToMissing = view.findViewById(R.id.add_to_missing);

        addToMissing.setTag(position);
        itemRemoveButton.setTag(position);

        itemName.setText(item.getName());

        String path = item.getImage();

        if (path != null)
        {
            File imgFile = new  File(item.getImage());

            if(imgFile.exists()){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inJustDecodeBounds = false;

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);

                itemImage.setImageURI(Uri.fromFile(imgFile));
            }
        }

        return view;
    }
}
