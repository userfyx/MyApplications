package com.bw.test.myapplication;

/**
 * Created by a on 2016/11/3.
 */
public class ImageView {
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ImageView(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageView{" +
                "image=" + image +
                '}';
    }
}
