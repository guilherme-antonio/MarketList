package com.example.marketlist;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private String image;

    public Item(String name, String image)
    {
        this.name = name;
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Item.class)
        {
            Item item = (Item) obj;

            boolean equal;

            if (this.name != null)
                equal = this.name.equals(item.getName());
            else
                equal = item.getName() == null;

            if (this.image != null)
                equal &= this.image.equals(item.getImage());
            else
                equal &= item.getImage() == null;

            return equal;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
