package com.example.more_close;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.button1);
        Button minigame = findViewById(R.id.minigame);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TlistActivity.class);
                intent.putExtra("key", "list1");
                startActivity(intent);
            }
        });

        minigame.setOnClickListener(new View.OnClickListener() { // 새로운 버튼에 대한 클릭 리스너 설정
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(MainActivity.this, minigameMain.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "게임하기 입장", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



