package com.example.more_close;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;
public class minigame03 extends AppCompatActivity {
    Button btnTest;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4; int index = 0;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame03);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        btnTest = findViewById(R.id.btnTest);
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
                        break;
                    case 1:
                        imageView2.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        imageView3.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        imageView4.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
    }
    }