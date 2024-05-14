package com.example.more_close;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class minigame05 extends AppCompatActivity {

    private TextView[] cardText = new TextView[9];
    private TextView clickTextView;
    private boolean[] viewClickCheck = new boolean[9];

    private List<Integer> listNum = new ArrayList<>();

    int[] imagesources = {R.drawable.soju, R.drawable.soju, R.drawable.soju, R.drawable.shrimp, R.drawable.shrimp, R.drawable.shrimp, R.drawable.losingticket, R.drawable.losingticket, R.drawable.losingticket};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame05);

        for (int i = 1; i < 10; i++) {
            listNum.add(i);
        }
        Collections.shuffle(listNum);

        for (int i = 0; i < cardText.length; i++) {
            String viewName = "image_" + (i + 1);
            cardText[i] = findViewById(getResources().getIdentifier(viewName, "id", getPackageName()));
            cardText[i].setText(String.valueOf(listNum.get(i)));
            viewClickCheck[i] = false;
        }

        textViewOnClickListener();

        findViewById(R.id.re_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reViewInit();
            }
        });
    }

    private void textViewOnClickListener() {
        for (TextView v : cardText) {
            v.setOnClickListener(cardClick(v));
        }
    }

    private void reViewInit() {
        listNum.clear();

        for (int i = 1; i < 10; i++) {
            listNum.add(i);
        }
        Collections.shuffle(listNum);

        for (int i = 0; i < cardText.length; i++) {
            cardText[i].setText(String.valueOf(listNum.get(i)));
            cardText[i].setBackgroundResource(R.drawable.questionmark); //다시하기 버튼 누르면 나오는 그림
            viewClickCheck[i] = false;
        }

        textViewOnClickListener();
    }

    private View.OnClickListener cardClick(final TextView textView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final double d = Math.random() * 9;
                clickTextView = findViewById(view.getId());

                float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                final float distance = clickTextView.getCameraDistance() * (scale + (scale / 3));

                clickTextView.setCameraDistance(distance);
                clickTextView.animate().withLayer()
                        .rotationY(90)
                        .setDuration(150)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                if (!viewClickCheck[Integer.parseInt(clickTextView.getText().toString()) - 1]) {
                                    clickTextView.setBackgroundResource(imagesources[(int) d]);
                                    viewClickCheck[Integer.parseInt(clickTextView.getText().toString()) - 1] = true;
                                } else {
                                    clickTextView.setBackgroundResource(R.drawable.questionmark); //다시 돌리기하면 나오는 버튼
                                    viewClickCheck[Integer.parseInt(clickTextView.getText().toString()) - 1] = false;
                                }
                                clickTextView.setRotationY(-90);
                                clickTextView.animate().withLayer()
                                        .rotationY(0)
                                        .setDuration(250)
                                        .start();
                            }
                        }).start();
            }
        };
    }
}