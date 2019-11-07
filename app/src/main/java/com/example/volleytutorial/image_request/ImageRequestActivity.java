package com.example.volleytutorial.image_request;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.volleytutorial.Constants;
import com.example.volleytutorial.Mysingleton;
import com.example.volleytutorial.R;

public class ImageRequestActivity extends AppCompatActivity {

    ImageView img;
    String img_url = Constants.IMG_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);

        img = (ImageView)findViewById(R.id.img);
    }

    public void getImageFromServer(View view) {

        ImageRequest imageRequest = new ImageRequest(img_url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        img.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ImageRequestActivity.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });//max width and max height : if we use zero then it will display exact size of image, decoding format : null
        Mysingleton.getInstance(ImageRequestActivity.this).addToRequestQueue(imageRequest);
    }
}
