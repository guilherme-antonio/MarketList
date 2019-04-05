package com.example.marketlist;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MissingItemAdapter extends ArrayAdapter<Item> {
    private Context _context;

    public MissingItemAdapter(Context context, int resource, List<Item> objects)
    {
        super(context, resource, objects);

        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(_context).inflate(R.layout.item,parent,false);

        Item item = getItem(position);

        CheckBox missingItemCheck = view.findViewById(R.id.MissingItemCheck);
        TextView missingItemName = view.findViewById(R.id.list_item_name);

        missingItemCheck.setTag(position);

        missingItemName.setText(item.getName());

        return view;
    }
}
