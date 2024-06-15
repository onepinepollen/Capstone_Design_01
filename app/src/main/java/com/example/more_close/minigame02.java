package com.example.more_close;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;


public class minigame02 extends AppCompatActivity {
    ImageView iv_bottle;
    float startDegree = 0f;
    float endDegree = 0f;

    //공유버튼 내용 start
    private Button shareButton;
    //공유버튼 내용 end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame02);

        // 애니메이션 이미지 인식
        iv_bottle = (ImageView)findViewById(R.id.bottle);

        //공유버튼 내용 start
        shareButton = findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareScreen();
            }
        });
        //공유버튼 내용 end
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