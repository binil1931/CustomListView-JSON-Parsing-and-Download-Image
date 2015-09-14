package com.example.binil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binil.adapter.ImageLoader;


/**
 * Created by binil1931 on 11-Sep-15.
 */

public class DetailActivity extends Activity {

    TextView detail;
    ImageView detail_img;
    String description;
    String imageUrl;

    public ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        detail = (TextView) findViewById(R.id.detail_text);
        detail_img = (ImageView) findViewById(R.id.detail_img);
        imageLoader=new ImageLoader(DetailActivity.this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            description = extras.getString("des");
            imageUrl = extras.getString("img_url");
        }

        imageLoader.DisplayImage(imageUrl,detail_img);
        detail.setText(description);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DetailActivity.this,MainActivity.class);
        finish();
        startActivity(i);
    }
}
