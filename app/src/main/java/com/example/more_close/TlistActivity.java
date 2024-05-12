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
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TlistActivity extends AppCompatActivity {

    private TeamData teamdata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlist);
        Intent intent = getIntent();

        LinearLayout scrollVL = findViewById(R.id.scrollViewLayout);

        Button btn1 = findViewById(R.id.button3);
        Button btn2 = findViewById(R.id.button);

        teamdata = new TeamData(this);
        teamdata.open();
        List<Team> teamList = teamdata.getAllTeams();

        //db탐색 및 저장된 팀리스트 불러오기
        if (teamList.isEmpty()){

        }else{
            for(Team team : teamList) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View team_btn = inflater.inflate(R.layout.team_button, null);
                TextView text1 = team_btn.findViewById(R.id.textView8);
                TextView text2 = team_btn.findViewById(R.id.textView10);
                ImageButton btn_D = team_btn.findViewById(R.id.imageButton);

                text1.setText(team.getName());
                text2.setText(team.getExplanation());
                scrollVL.addView(team_btn);

                String key = team.getId();

                btn_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(TlistActivity.this);
                        builder1.setTitle("정말로 삭제 하시겠습니까?"); // 다이얼로그 제목 설정
                        builder1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                scrollVL.removeView(team_btn);
                                teamdata.delTeams(String.valueOf(key));
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
                        // 데이터베이스 키를 넘긴다
                        Intent intent = new Intent(TlistActivity.this, Team_pay.class);
                        intent.putExtra("id", String.valueOf(key));
                        startActivity(intent);
                    }
                });
            }
        }
        // 버튼 클릭 이벤트 리스너 설정
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog를 생성하고 설정합니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(TlistActivity.this);
                builder.setTitle("모임 추가"); // 다이얼로그 제목 설정

                // LinearLayout을 생성합니다.
                LinearLayout layout = new LinearLayout(TlistActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL); // 수직으로 배치

                // EditText를 생성하고 LinearLayout에 추가합니다. (이름 입력)
                final EditText nameEditText = new EditText(TlistActivity.this);
                nameEditText.setHint("이름을 입력하세요"); // 입력창에 힌트 설정
                layout.addView(nameEditText);

                // EditText를 생성하고 LinearLayout에 추가합니다. (인원수 입력)
                final EditText countEditText = new EditText(TlistActivity.this);
                countEditText.setHint("모임의 설명을 입력하세요"); // 입력창에 힌트 설정
                layout.addView(countEditText);

                // LinearLayout을 다이얼로그에 설정합니다.
                builder.setView(layout);

                // 다이얼로그에 확인 버튼 추가 및 클릭 리스너 설정
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        teamdata.open();
                        // EditText에서 입력된 값을 가져옵니다.
                        String name = nameEditText.getText().toString();
                        String explain = countEditText.getText().toString();

                        // 입력된 값을 이용하여 원하는 작업을 수행합니다.
                        // 예를 들어, 입력된 정보를 TextView에 표시하거나 다른 처리를 할 수 있습니다.

                        // 다이얼로그를 닫습니다.
                        dialog.dismiss();
                        if (name.isEmpty() || explain.isEmpty()) {
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
                            text2.setText(explain);
                            scrollVL.addView(team_btn);
                            //데이터 베이스에 내용을 추가합니다
                            long key = teamdata.addTeams(name, explain, "");
                            btn_D.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(TlistActivity.this);
                                    builder1.setTitle("정말로 삭제 하시겠습니까?"); // 다이얼로그 제목 설정
                                    builder1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            scrollVL.removeView(team_btn);
                                            teamdata.delTeams(String.valueOf(key));
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
                                    // 데이터베이스 키를 넘긴다
                                    Intent intent = new Intent(TlistActivity.this, Team_pay.class);
                                    intent.putExtra("id", String.valueOf(key));
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