package com.example.more_close;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import androidx.core.content.FileProvider;

import android.widget.Button;
public class minigame03 extends AppCompatActivity {
    Button btnTest;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    int index = 0;
    Random random = new Random();

    //공유버튼 내용 start
    private Button shareButton;
    //공유버튼 내용 end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame03);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        btnTest = findViewById(R.id.btnTest);
        //공유버튼 내용 start
        shareButton = findViewById(R.id.s_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareScreen();
            }
        });
        //공유버튼 내용 end
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = random.nextInt(4);
                switch (index) {
                    case 0:
                        imageView.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);

                        break;
                    case 1:
                        imageView2.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        imageView3.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        imageView4.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.VISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                    case 5:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.VISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                    case 6:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.VISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                    case 7:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.VISIBLE);
                        imageView9.setVisibility(View.INVISIBLE);
                    case 8:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);
                        imageView8.setVisibility(View.INVISIBLE);
                        imageView9.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    //공유버튼 내용 start
    private void shareScreen() {
        // Capture the screen
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        try {
            // Save the screenshot
            File cachePath = new File(getExternalCacheDir(), "my_images/");
            cachePath.mkdirs();
            File file = new File(cachePath, "image.png");
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

            // Share the screenshot
            Uri contentUri = FileProvider.getUriForFile(this, "com.example.yourapp.fileprovider", file);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Share via"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //공유버튼 내용  end
    }