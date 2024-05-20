package com.example.more_close;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class minigame06 extends AppCompatActivity {
    Button startminigame;
    TextView textView;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;

    SoundPool msound;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame06);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        startminigame = findViewById(R.id.startminigame);

        textView = findViewById(R.id.textView);

        Random random = new Random();

        SoundPool mSoundPool;
        int mSoundId;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .build();
        } else {
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        }
        mSoundId = mSoundPool.load(getApplicationContext(), R.raw.gameover, 1);

        startminigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountDownTimer countDownTimer = new CountDownTimer(300000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        int num = (int) (millisUntilFinished / 1000);
                        textView.setText(Integer.toString(num + 1));
                    }

                    public void onFinish() {
                        textView.setText("끝");
                        mSoundPool.play(mSoundId, 1.0f, 1.0f, 1, 0, 1);
                    }

                }.start();

                index = random.nextInt(6);
                switch (index) {
                    case 0:
                        imageView.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);


                        break;
                    case 1:
                        imageView2.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);

                        break;
                    case 2:
                        imageView3.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);

                        break;
                    case 3:
                        imageView4.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);

                        break;
                    case 4:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.VISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);

                    case 5:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.VISIBLE);
                        imageView7.setVisibility(View.INVISIBLE);

                    case 6:
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        imageView7.setVisibility(View.VISIBLE);


                }
            }
        });


    }
}