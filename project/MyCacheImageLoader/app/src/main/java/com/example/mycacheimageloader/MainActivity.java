package com.example.mycacheimageloader;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private String filename = "log.txt";
    private ImageView imageView;
    private FileOutputStream fileOutputStream;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    public void loadImage(View view) {
        long start = System.nanoTime();
        String url = "https://picsum.photos/600";
        String urlState = ",url";
        String cacheState = ",cache";

        Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

        try {
            long stop = System.nanoTime();
            String ms = String.valueOf((stop - start) / 1000000);
            fileOutputStream = openFileOutput(filename, MODE_APPEND);
            fileOutputStream.write(ms.getBytes());
            if (count == 0) {
                fileOutputStream.write(urlState.getBytes());
                count++;
            } else {
                fileOutputStream.write(cacheState.getBytes());
            }
            fileOutputStream.write("\n".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}