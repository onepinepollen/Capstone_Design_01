package com.example.more_close;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class minigame02 extends AppCompatActivity {
    ImageView iv_bottle;
    float startDegree = 0f;
    float endDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame02);

        // 애니메이션 이미지 인식
        iv_bottle = (ImageView)findViewById(R.id.bottle);
    }

    // 룰렛 이미지 터치 시에 호출되는 메소드
    public void rotate(View v) {
        // ---------- 회전각도 설정 ----------
        startDegree = endDegree;    // 이전 정지 각도를 시작 각도로 설정
        Random rand = new Random(); // 랜덤 객체 생성
        int degree_rand = rand.nextInt(360);    // 0~359 사이의 정수 추출
        endDegree = startDegree + 360 * 5 + degree_rand;  // 회전 종료각도 설정(초기 각도에서 세바퀴 돌고 0~359 난수만큼 회전)

        // ---------- 애니메이션 실행 ----------
        // 애니메이션 이미지에 대해 초기 각도에서 회전종료 각도까지 회전하는 애니메이션 객체 생성
        ObjectAnimator object = ObjectAnimator.ofFloat(iv_bottle, "rotation", startDegree, endDegree);

        object.setInterpolator(new AccelerateDecelerateInterpolator()); // 애니메이션 속력 설정
        object.setDuration(6000);   // 애니메이션 시간(5초)
        object.start();   // 애니메이션 시작
    }
}