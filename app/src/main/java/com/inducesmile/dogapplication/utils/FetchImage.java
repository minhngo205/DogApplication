package com.inducesmile.dogapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FetchImage extends Thread{
    String URL;
    Bitmap bitmap;
    Handler handler;
    ImageView image;

    public FetchImage(String URL, ImageView image, Handler handler){
        this.URL = URL;
        this.image = image;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = new java.net.URL(URL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler.post(() -> image.setImageBitmap(bitmap));
    }
}
