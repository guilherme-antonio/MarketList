package com.example.marketlist;

import android.media.Image;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private Image image;

    public Item(String name, Image image)
    {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }
}
