package com.example.more_close;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.CountDownTimer;
import android.widget.TextView;

public class minigame04 extends AppCompatActivity {
    Button startminigame;
    TextView textView;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4; int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame04);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        startminigame = findViewById(R.id.startminigame);

        textView = findViewById(R.id.textView);

        Random random = new Random();

        startminigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        int num = (int) (millisUntilFinished / 1000);
                        textView.setText(Integer.toString(num + 1));
                    }

                    public void onFinish() {
                        textView.setText("끝");
                    }
                }.start();

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