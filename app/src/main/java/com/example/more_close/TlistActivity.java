package com.example.more_close;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlist);
        Intent intent = getIntent();

        LinearLayout scrollVL = findViewById(R.id.scrollViewLayout);
        Button btn1 = findViewById(R.id.button3);
        Button btn2 = findViewById(R.id.button);

        // 버튼 클릭 이벤트 리스너 설정
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog를 생성하고 설정합니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(TlistActivity.this);
                builder.setTitle("정보 입력"); // 다이얼로그 제목 설정

                // LinearLayout을 생성합니다.
                LinearLayout layout = new LinearLayout(TlistActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL); // 수직으로 배치

                // EditText를 생성하고 LinearLayout에 추가합니다. (이름 입력)
                final EditText nameEditText = new EditText(TlistActivity.this);
                nameEditText.setHint("이름을 입력하세요"); // 입력창에 힌트 설정
                layout.addView(nameEditText);

                // EditText를 생성하고 LinearLayout에 추가합니다. (인원수 입력)
                final EditText countEditText = new EditText(TlistActivity.this);
                countEditText.setHint("인원수를 입력하세요"); // 입력창에 힌트 설정
                countEditText.setInputType(InputType.TYPE_CLASS_NUMBER); // 숫자만 입력 가능하도록 설정
                layout.addView(countEditText);

                // LinearLayout을 다이얼로그에 설정합니다.
                builder.setView(layout);

                // 다이얼로그에 확인 버튼 추가 및 클릭 리스너 설정
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // EditText에서 입력된 값을 가져옵니다.
                        String name = nameEditText.getText().toString();
                        String countStr = countEditText.getText().toString();

                        // 입력된 값을 이용하여 원하는 작업을 수행합니다.
                        // 예를 들어, 입력된 정보를 TextView에 표시하거나 다른 처리를 할 수 있습니다.

                        // 다이얼로그를 닫습니다.
                        dialog.dismiss();
                        if(name.isEmpty() || countStr.isEmpty()) {
                        }
                        else{
                            // ScrollView에 새로운 TextView를 추가합니다.
                            TextView textView = new TextView(TlistActivity.this);
                            textView.setText("이름: " + name + ", 인원수: " + countStr);
                            scrollVL.addView(textView);
                        }
                    }
                });



                // 다이얼로그를 생성하고 표시합니다.
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TlistActivity.this, Fast_Pay.class);
                startActivity(intent);
            }
        });

    }


}
