package com.example.more_close;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class minigameMain extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame_main);

        Button roulettebutton = findViewById(R.id.rouletteButton);
        Button bottlebutton = findViewById(R.id.bottleButton);

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
    }
}