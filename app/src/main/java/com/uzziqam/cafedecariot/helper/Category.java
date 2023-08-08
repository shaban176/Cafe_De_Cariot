package com.uzziqam.cafedecariot.helper;

public class Category {
  public String img;
  public String name;

    public Category() {
    }

    public Category(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
