package com.example.more_close;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class minigameMain extends AppCompatActivity{

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame_main);

        Button roulettebutton = findViewById(R.id.rouletteButton);
        Button bottlebutton = findViewById(R.id.bottleButton);
        Button introduceButton = findViewById(R.id.introduceButton);
        Button fivesecondButton = findViewById(R.id.fivesecondButton);
        Button cardgameButton = findViewById(R.id.cardgameButton);
        Button banwordButton = findViewById(R.id.banwordButton);
        Button fingerButton = findViewById(R.id.fingerButton);

        roulettebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame01.class);
                startActivity(intent);
            }
        });

        bottlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame02.class);
                startActivity(intent);
            }
        });

        introduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame03.class);
                startActivity(intent);
            }
        });

        fivesecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame04.class);
                startActivity(intent);
            }
        });

        cardgameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame05.class);
                startActivity(intent);
            }
        });

        banwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame06.class);
                startActivity(intent);
            }
        });

        fingerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(minigameMain.this, minigame07.class);
                startActivity(intent);
            }
        });

        // 공유하기 부분


    }
}

