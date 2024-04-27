package com.example.more_close;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
                        if (name.isEmpty() || countStr.isEmpty()) {
                        } else {
                            // LayoutInflater 인스턴스 생성
                            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            // 추가할 XML 레이아웃을 인플레이트
                            View team_btn = inflater.inflate(R.layout.team_button, null);
                            // team_button 레이아웃을 스크롤 뷰에 추가합니다.
                            TextView text1 = team_btn.findViewById(R.id.textView8);
                            TextView text2 = team_btn.findViewById(R.id.textView10);
                            ImageButton btn_D = team_btn.findViewById(R.id.imageButton);
                            text1.setText(name);
                            text2.setText(countStr);
                            scrollVL.addView(team_btn);
                            btn_D.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(TlistActivity.this);
                                    builder1.setTitle("정말로 삭제 하시겠습니까?"); // 다이얼로그 제목 설정
                                    builder1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            scrollVL.removeView(team_btn);
                                        }
                                    });
                                    builder1.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                        }
                                    });
                                    AlertDialog dialog = builder1.create();
                                    builder1.show();
                                }
                            });
                            team_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // 임시문구 나중에 해당 모임 화면으로 넘어갈 예정입니다.
                                    Intent intent = new Intent(TlistActivity.this, Team_pay.class);
                                    startActivity(intent);
                                }
                            });

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