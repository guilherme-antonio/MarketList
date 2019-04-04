package com.example.marketlist;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private Drawable image;

    public Item(String name, Drawable image)
    {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }
}
