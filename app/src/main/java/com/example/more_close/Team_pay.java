package com.example.more_close;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Team_pay extends AppCompatActivity {

    private List<View> personViews;
    private TeamData teamdata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_pay);
        Intent intent = getIntent();
        String key = intent.getStringExtra("id");

        LinearLayout scrollVL = findViewById(R.id.scrollViewLayout2);

        TextView team_name = findViewById(R.id.textView2);

        EditText pay1 = findViewById(R.id.editTextNumber8);
        EditText pay2 = findViewById(R.id.editTextNumber9);
        EditText pay3 = findViewById(R.id.editTextNumber10);
        EditText name = findViewById(R.id.editTextTextPersonName);

        Button btn1 = findViewById(R.id.button4); //결제버튼
        Button btn2 = findViewById(R.id.button5); //인원추가 버튼
        ImageView btn3 = findViewById(R.id.imageView5); //팀명 수정

        teamdata = new TeamData(this);
        teamdata.open();
        List<Team> teamList = teamdata.getIdTeams(key);
        Team team = teamList.get(0);

        personViews = new ArrayList<>();
        name.setHint("이름을 입력하세요");

        //로드시 데이터 불러와 화면에 표시
        team_name.setText(team.getName());
        if (team.getMember().isEmpty()){

        }else{
            String[] members1 = team.getMember().split(",");
            for(String member : members1){
                String People = member;
                View person = getLayoutInflater().inflate(R.layout.person_view, null);
                TextView name1 = person.findViewById(R.id.textView11);
                ImageView icon = person.findViewById(R.id.imageView);
                Switch switch_box = person.findViewById(R.id.switch1);

                name1.setText(People);
                scrollVL.addView(person);

                // personViews 리스트에 해당 뷰를 추가합니다.
                personViews.add(person);

                person.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);
                        builder1.setTitle("정말로 삭제 하시겠습니까?"); // 다이얼로그 제목 설정
                        builder1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                teamdata.updateMem_Del(key,People);
                                scrollVL.removeView(person);
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
            }
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int switchCount = 0;

                // 눌려진 스위치
                if (personViews != null && !personViews.isEmpty()) {
                    // 모든 person_view에서 스위치 버튼이 눌러진 횟수를 세어줍니다.
                    for (View personView : personViews) {
                        Switch switchButton = personView.findViewById(R.id.switch1);
                        if (switchButton != null && switchButton.isChecked()) {
                            switchCount++;
                        }
                    }
                }
                String Pay1 = pay1.getText().toString();
                String Pay2 = pay2.getText().toString();
                String Pay3 = pay3.getText().toString();

                String[] pays = {Pay1, Pay2, Pay3};
                int sum = 0;

                for(int i = 0; i < pays.length; i++) {
                    if(pays[i].isEmpty()){
                    }
                    else{
                        sum += Integer.parseInt(pays[i]);
                    }
                }
                if(switchCount == 0){
                    showPeopleErrorDialog();
                }else{
                    sum = sum / switchCount;
                    int ex = sum % switchCount;

                    showResultDialog(sum,ex);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String People = name.getText().toString();
                View person = getLayoutInflater().inflate(R.layout.person_view, null);
                TextView name1 = person.findViewById(R.id.textView11);
                ImageView icon = person.findViewById(R.id.imageView);
                Switch switch_box = person.findViewById(R.id.switch1);
                if(People.isEmpty()){
                    showNameErrorDialog();
                }
                else{
                    name1.setText(People);
                    scrollVL.addView(person);

                    // personViews 리스트에 해당 뷰를 추가합니다.
                    personViews.add(person);
                    teamdata.updateMem_Add(key,People);

                    person.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);
                            builder1.setTitle("정말로 삭제 하시겠습니까?"); // 다이얼로그 제목 설정
                            builder1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    teamdata.updateMem_Del(key,People);
                                    scrollVL.removeView(person);
                                }
                            });
                            builder1.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            AlertDialog dialog = builder1.create();
                            dialog.show();
                        }
                    });
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() { //팀명 수정 코드
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);
                builder.setTitle("모임명 수정");

                // LinearLayout을 생성합니다.
                LinearLayout layout = new LinearLayout(Team_pay.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText nameEditText = new EditText(Team_pay.this);
                nameEditText.setHint("이름을 입력하세요");
                layout.addView(nameEditText);

                builder.setView(layout);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEditText.getText().toString();
                        teamdata.updateName(key,name);
                        team_name.setText(name);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    private void showPeopleErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);
        builder.setMessage("한명 이상 선택해주세요!")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showNameErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);
        builder.setMessage("이름을 적어주세요!")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showResultDialog(int sum,int ex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);
        if(ex != 0){
            builder.setMessage("한명당 지불한 금액은 " + sum + " 이며,\n"+ex+"가 남았습니다. \n미니게임 화면으로 넘어갈까요?")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Team_pay.this, minigameMain.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }else{
            builder.setMessage("한명당 지불한 금액은 " + sum + " 입니다.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
