package com.example.more_close;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class minigame07 extends AppCompatActivity {
    private FrameLayout touchFrameLayout;
    private View chosenPointView;
    private Random random;
    private Handler handler;
    private ArrayList<float[]> touchPoints;
    private boolean isTouching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame07);

        touchFrameLayout = findViewById(R.id.touchFrameLayout);
        chosenPointView = findViewById(R.id.chosenPoint);
        random = new Random();
        handler = new Handler();
        touchPoints = new ArrayList<>();

        touchFrameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (!isTouching) {
                            isTouching = true;
                            handler.postDelayed(showRandomPoint, 1000);
                        }
                        collectTouchPoints(event);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        collectTouchPoints(event);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        isTouching = false;
                        handler.removeCallbacks(showRandomPoint);
                        break;
                }
                return true;
            }

            private void collectTouchPoints(MotionEvent event) {
                int pointerCount = event.getPointerCount();
                touchPoints.clear();
                for (int i = 0; i < pointerCount; i++) {
                    touchPoints.add(new float[]{event.getX(i), event.getY(i)});
                }
            }

            private Runnable showRandomPoint = new Runnable() {
                @Override
                public void run() {
                    if (isTouching && !touchPoints.isEmpty()) {
                        int randomIndex = random.nextInt(touchPoints.size());
                        float[] chosenPoint = touchPoints.get(randomIndex);

                        chosenPointView.setX(chosenPoint[0] - chosenPointView.getWidth() / 2);
                        chosenPointView.setY(chosenPoint[1] - chosenPointView.getHeight() / 2);
                        chosenPointView.setVisibility(View.VISIBLE);
                    }
                }
            };
        });
    }
}
