package com.example.more_close;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Team_pay extends AppCompatActivity {

    private List<View> menuViews;
    private List<View> personViews;
    private TeamData teamdata;
    private MenuData menudata;
    private MemberData memberdata;

    //공유버튼 내용 start
    private Button shareButton;
    //공유버튼 내용 end

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_pay);
        Intent intent = getIntent();
        String key = intent.getStringExtra("id");

        LinearLayout scrollVL = findViewById(R.id.scrollViewLayout2);
        LinearLayout scrollVL2 = findViewById(R.id.scrollViewLayout3);

        TextView team_name = findViewById(R.id.textView2);
        TextView sum_result = findViewById(R.id.textView24);
        EditText name = findViewById(R.id.editTextTextPersonName);
        name.setHint("이름을 입력하세요");

        //계산 방식 결정
        RadioButton rdb1 = findViewById(R.id.radioButton);
        RadioButton rdb2 = findViewById(R.id.radioButton2);
        RadioButton rdb3 = findViewById(R.id.radioButton3);

        Button btn1 = findViewById(R.id.button4); //결제버튼
        Button btn2 = findViewById(R.id.button5); //인원추가 버튼
        ImageView btn3 = findViewById(R.id.imageView5); //팀명 수정
        ImageButton btn4 = findViewById(R.id.imageButton2); //메뉴 추가
        ImageButton btn5 = findViewById(R.id.imageButton3); //메뉴 삭제


        teamdata = new TeamData(this);
        teamdata.open();
        List<Team> teamList = teamdata.getIdTeams(key);
        Team team = teamList.get(0);

        menudata = new MenuData(this);
        menudata.open();
        List<Menu> menuList = menudata.getNameMenus(team.getName());

        memberdata = new MemberData(this);
        memberdata.open();
        List<Member> memberList = memberdata.getNameMembers(team.getName());

        menuViews = new ArrayList<>();
        personViews = new ArrayList<>();

        //로드시 데이터 불러와 화면에 표시
        team_name.setText(team.getName());
        if (memberList.isEmpty()){
            //예외처리
        }else{
            for(Member member : memberList){
                View person = getLayoutInflater().inflate(R.layout.person_view, null);
                TextView name1 = person.findViewById(R.id.textView11);
                ImageView icon = person.findViewById(R.id.imageView12);
                Switch switch_box = person.findViewById(R.id.switch1);

                name1.setText(member.getMember());
                scrollVL.addView(person);
                personViews.add(person);

                person.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater inflater = getLayoutInflater();
                        View normalView = inflater.inflate(R.layout.popup_nomal, null);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

                        TextView popupText = normalView.findViewById(R.id.textView18);
                        Button PositiveButton2 = normalView.findViewById(R.id.button8);
                        Button NegativeButton2 = normalView.findViewById(R.id.button7);
                        popupText.setText("삭제 하시겠습니까?");

                        builder1.setView(normalView); // builder1에 setView를 설정합니다.
                        AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

                        PositiveButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                memberdata.delMembers(member.getId());
                                scrollVL.removeView(person);
                                dialog.dismiss(); // 다이얼로그 닫기
                            }
                        });
                        NegativeButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss(); // 다이얼로그 닫기
                            }
                        });

                        dialog.show(); // 다이얼로그 표시
                    }
                });
            }
        }

        if(menuList.isEmpty()){
            //예외처리
        }else{
            for(Menu menus : menuList) {
                String menu = menus.getMenu();
                String key1 = menus.getId();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View menu_btn = inflater.inflate(R.layout.menu_view, null);
                TextView menu_name = menu_btn.findViewById(R.id.textView14);
                menu_name.setText(menu);
                CheckBox check_box = menu_btn.findViewById(R.id.checkBox);

                menuViews.add(menu_btn);

                menu_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater inflater = getLayoutInflater();
                        View normalView = inflater.inflate(R.layout.popup_nomal, null);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

                        TextView popupText = normalView.findViewById(R.id.textView18);
                        Button PositiveButton2 = normalView.findViewById(R.id.button8);
                        Button NegativeButton2 = normalView.findViewById(R.id.button7);
                        popupText.setText("삭제 하시겠습니까?");

                        builder1.setView(normalView); // builder1에 setView를 설정합니다.
                        AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

                        PositiveButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                scrollVL2.removeView(menu_btn);
                                menudata.delMenus(String.valueOf(key1));
                                dialog.dismiss();
                            }
                        });
                        NegativeButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show(); // 다이얼로그 표시
                    }
                });

                check_box.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(check_box.isChecked()){
                            List<Menu> menu_List = menudata.getMenu(team_name.getText().toString(),menu_name.getText().toString());
                            Menu pay = menu_List.get(0);
                            int pay_sum = Integer.parseInt(pay.getPay());
                            int result = Integer.parseInt(sum_result.getText().toString()) + pay_sum;
                            sum_result.setText(Integer.toString(result));
                        }else{
                            List<Menu> menu_List = menudata.getMenu(team_name.getText().toString(),menu_name.getText().toString());
                            Menu pay = menu_List.get(0);
                            int pay_sum = Integer.parseInt(pay.getPay());
                            int result = Integer.parseInt(sum_result.getText().toString()) - pay_sum;
                            sum_result.setText(Integer.toString(result));
                        }
                    }
                });

                scrollVL2.addView(menu_btn);

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

                int sum = 0;

                //선택된 체크박스
                if (menuList != null && !menuList.isEmpty()){
                    for (View menuViews :menuViews){
                        CheckBox checkBox = menuViews.findViewById(R.id.checkBox);
                        if (checkBox != null && checkBox.isChecked()){
                            TextView menu = menuViews.findViewById(R.id.textView14);
                            String menu_name = menu.getText().toString();
                            List<Menu> menu_List = menudata.getMenu(team_name.getText().toString(),menu_name);
                            Menu pay = menu_List.get(0);
                            sum += Integer.parseInt(pay.getPay());
                        }
                    }
                }

                if(switchCount == 0){
                    showPeopleErrorDialog();
                }else if(sum == 0){
                    showSumErrorDialog();
                } else{
                    int old_sum = sum;
                    sum = sum / switchCount;
                    int ex = sum % switchCount;

                    if(rdb1.isChecked()){
                        //일반 계산
                        showResultDialog(sum,ex);
                    }else if(rdb2.isChecked()){
                        //랜덤 몰빵
                        showAllinDialog(sum,ex);
                    }else {
                        //랜덤 분배
                        showRandomDialog(old_sum);
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String People = name.getText().toString();
                View person = getLayoutInflater().inflate(R.layout.person_view, null);
                TextView name1 = person.findViewById(R.id.textView11);
                ImageView icon = person.findViewById(R.id.imageView12);
                Switch switch_box = person.findViewById(R.id.switch1);
                if(People.isEmpty()){
                    showNameErrorDialog();
                }
                else{
                    name1.setText(People);
                    scrollVL.addView(person);

                    // personViews 리스트에 해당 뷰를 추가합니다.
                    personViews.add(person);
                    long id = memberdata.addMembers(team_name.getText().toString(),People);

                    person.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LayoutInflater inflater = getLayoutInflater();
                            View normalView = inflater.inflate(R.layout.popup_nomal, null);
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

                            TextView popupText = normalView.findViewById(R.id.textView18);
                            Button PositiveButton2 = normalView.findViewById(R.id.button8);
                            Button NegativeButton2 = normalView.findViewById(R.id.button7);
                            popupText.setText("삭제 하시겠습니까?");

                            builder1.setView(normalView); // builder1에 setView를 설정합니다.
                            AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

                            PositiveButton2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    memberdata.delMembers(String.valueOf(id));
                                    scrollVL.removeView(person);
                                    dialog.dismiss(); // 다이얼로그 닫기
                                }
                            });
                            NegativeButton2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss(); // 다이얼로그 닫기
                                }
                            });

                            dialog.show(); // 다이얼로그 표시
                        }
                    });
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() { //팀명 수정 코드
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.popup_changename, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);


                EditText nameEditText = dialogView.findViewById(R.id.editTextTextPersonName5);
                nameEditText.setHint("이름을 입력하세요");;
                Button PositiveButton = dialogView.findViewById(R.id.button9);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                PositiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = nameEditText.getText().toString();
                        teamdata.updateName(key,name);
                        menudata.updateName(team_name.getText().toString(),name);
                        memberdata.updateName(team_name.getText().toString(),name);
                        team_name.setText(name);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        //메뉴 추가버튼
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AlertDialog를 생성하고 설정합니다.
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.popup_addteam, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);

                EditText menuEditText = dialogView.findViewById(R.id.editTextTextPersonName4);
                EditText payEditText = dialogView.findViewById(R.id.editTextTextPersonName2);
                menuEditText.setHint("메뉴를 입력하세요"); // 입력창에 힌트 설정
                payEditText.setHint("가격을 입력하세요"); // 입력창에 힌트 설정

                TextView text1 = dialogView.findViewById(R.id.textView16);
                TextView text2 = dialogView.findViewById(R.id.textView17);
                text1.setText("메뉴 이름:");
                text2.setText("가격:");

                Button PositiveButton = dialogView.findViewById(R.id.button6);
                builder.setView(dialogView);

                AlertDialog dialog = builder.create();

                PositiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String menu = menuEditText.getText().toString();
                        String pay = payEditText.getText().toString();
                        if (menu.isEmpty() || pay.isEmpty()) {
                        } else {
                            // 데이터로 메뉴 목록 생성
                            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View menu_btn = inflater.inflate(R.layout.menu_view, null);
                            TextView menu_name = menu_btn.findViewById(R.id.textView14);
                            menu_name.setText(menu);
                            CheckBox check_box = menu_btn.findViewById(R.id.checkBox);

                            //데이터 추가
                            menuViews.add(menu_btn);
                            long key = menudata.addMenus(team_name.getText().toString(), menu, pay);
                            dialog.dismiss();
                            menu_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LayoutInflater inflater = getLayoutInflater();
                                    View normalView = inflater.inflate(R.layout.popup_nomal, null);
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

                                    TextView popupText = normalView.findViewById(R.id.textView18);
                                    Button PositiveButton2 = normalView.findViewById(R.id.button8);
                                    Button NegativeButton2 = normalView.findViewById(R.id.button7);
                                    popupText.setText("삭제 하시겠습니까?");

                                    builder1.setView(normalView); // builder1에 setView를 설정합니다.
                                    AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

                                    PositiveButton2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            scrollVL2.removeView(menu_btn);
                                            menudata.delMenus(String.valueOf(key));
                                            dialog.dismiss();
                                        }
                                    });
                                    NegativeButton2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });

                                    dialog.show(); // 다이얼로그 표시
                                }
                            });
                            check_box.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(check_box.isChecked()){
                                        List<Menu> menu_List = menudata.getMenu(team_name.getText().toString(),menu_name.getText().toString());
                                        Menu pay = menu_List.get(0);
                                        int pay_sum = Integer.parseInt(pay.getPay());
                                        int result = Integer.parseInt(sum_result.getText().toString()) + pay_sum;
                                        sum_result.setText(Integer.toString(result));
                                    }else{
                                        List<Menu> menu_List = menudata.getMenu(team_name.getText().toString(),menu_name.getText().toString());
                                        Menu pay = menu_List.get(0);
                                        int pay_sum = Integer.parseInt(pay.getPay());
                                        int result = Integer.parseInt(sum_result.getText().toString()) - pay_sum;
                                        sum_result.setText(Integer.toString(result));
                                    }
                                }
                            });

                            scrollVL2.addView(menu_btn);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        //메뉴 전원 삭제 버튼
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View normalView = inflater.inflate(R.layout.popup_nomal, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(Team_pay.this);

                TextView popupText = normalView.findViewById(R.id.textView18);
                Button PositiveButton2 = normalView.findViewById(R.id.button8);
                Button NegativeButton2 = normalView.findViewById(R.id.button7);
                popupText.setText("삭제 하시겠습니까?");

                builder.setView(normalView); // builder1에 setView를 설정합니다.
                AlertDialog dialog = builder.create(); // dialog 객체를 생성합니다.

                PositiveButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menudata.delAllMenus(team_name.getText().toString());
                        scrollVL2.removeAllViews();
                        dialog.dismiss();
                    }
                });
                NegativeButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    //경고 용 함수들
    private void showPeopleErrorDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View basicView = inflater.inflate(R.layout.popup_basic, null);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

        TextView popupText = basicView.findViewById(R.id.textView20);
        Button PositiveButton2 = basicView.findViewById(R.id.button01);
        popupText.setText("한명 이상 선택해 주세요.");

        builder1.setView(basicView); // builder1에 setView를 설정합니다.
        AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

        PositiveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });
        dialog.show();
    }

    private void showNameErrorDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View basicView = inflater.inflate(R.layout.popup_basic, null);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

        TextView popupText = basicView.findViewById(R.id.textView20);
        Button PositiveButton2 = basicView.findViewById(R.id.button01);
        popupText.setText("이름을 적어주세요!");

        builder1.setView(basicView); // builder1에 setView를 설정합니다.
        AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

        PositiveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });
        dialog.show();
    }

    private void showSumErrorDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View basicView = inflater.inflate(R.layout.popup_basic, null);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);

        TextView popupText = basicView.findViewById(R.id.textView20);
        Button PositiveButton2 = basicView.findViewById(R.id.button01);
        popupText.setText("메뉴를 선택해 주세요!");

        builder1.setView(basicView); // builder1에 setView를 설정합니다.
        AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

        PositiveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });
        dialog.show();
    }

    private void showResultDialog(int sum,int ex) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);
        if(ex != 0){
            LayoutInflater inflater = getLayoutInflater();
            View normalView = inflater.inflate(R.layout.popup_nomal, null);

            TextView popupText = normalView.findViewById(R.id.textView18);
            Button PositiveButton2 = normalView.findViewById(R.id.button8);
            Button NegativeButton2 = normalView.findViewById(R.id.button7);
            //공유버튼 내용 start
            Button shareButton = normalView.findViewById(R.id.share_button);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareScreen();
                }
            });
            //공유버튼 내용 end
            popupText.setText("한명당 지불한 금액은 " + sum + " 이며,\n"+ex+"가 남았습니다. \n미니게임 화면으로 넘어갈까요?");

            builder1.setView(normalView); // builder1에 setView를 설정합니다.
            AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

            PositiveButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Team_pay.this, minigameMain.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            NegativeButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss(); // 다이얼로그 닫기
                }
            });
            dialog.show();
        }else{
            LayoutInflater inflater = getLayoutInflater();
            View basicView = inflater.inflate(R.layout.popup_basic, null);

            TextView popupText = basicView.findViewById(R.id.textView20);
            Button PositiveButton2 = basicView.findViewById(R.id.button01);
            popupText.setText("한명당 지불한 금액은 " + sum + " 입니다.");

            builder1.setView(basicView); // builder1에 setView를 설정합니다.
            AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

            PositiveButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss(); // 다이얼로그 닫기
                }
            });
            dialog.show();
        }
    }

    private void showAllinDialog(int sum, int ex){
        if (personViews != null && !personViews.isEmpty()) {
            List<String> names = new ArrayList<>();
            for (View personView : personViews) {
                Switch switchButton = personView.findViewById(R.id.switch1);
                if (switchButton != null && switchButton.isChecked()) {
                    TextView name = personView.findViewById(R.id.textView11);
                    names.add(name.getText().toString());
                }
            }
            if (names == null || names.isEmpty()) {
            }
            Random random = new Random();
            int randomIndex = random.nextInt(names.size());
            String allIn = names.get(randomIndex);

            //팝업창 생성
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);
            LayoutInflater inflater = getLayoutInflater();
            View basicView = inflater.inflate(R.layout.popup_basic, null);

            TextView popupText = basicView.findViewById(R.id.textView20);
            Button PositiveButton2 = basicView.findViewById(R.id.button01);
            //공유버튼 내용 start
            Button shareButton = basicView.findViewById(R.id.share_button);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareScreen();
                }
            });
            //공유버튼 내용 end
            int result = sum + ex ;
            popupText.setText("몰빵 당첨자는... " + allIn +"님 입니다.\n" + allIn + " : "+ result + "\n" + "나머지 분들 : " + sum);

            builder1.setView(basicView); // builder1에 setView를 설정합니다.
            AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

            PositiveButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    private void showRandomDialog(int sum){
        List<String> names = new ArrayList<>();
        for (View personView : personViews) {
            Switch switchButton = personView.findViewById(R.id.switch1);
            if (switchButton != null && switchButton.isChecked()) {
                TextView name = personView.findViewById(R.id.textView11);
                names.add(name.getText().toString());
            }
        }
        //순위 랜덤 분배
        List<String> rankList = new ArrayList<>(names);
        Collections.shuffle(rankList);

        //금액 차등 분배
        int[] distribution = new int[rankList.size()];
        int sumRanks = 0;
        for (int i = 1; i <= rankList.size(); i++) {
            sumRanks += i;
        }
        int remain = sum;
        for (int i = 0; i < rankList.size(); i++) {
            distribution[i] = (sum * (rankList.size() - i)) / sumRanks;
            remain -= distribution[i];
        }
        // 분배 후 남은 잔액을 1위에게 추가
        distribution[0] += remain;

        //1등이 가장 많이 내는 식으로 작동한다.
        String answer = "";
        for(int i = 0; i < rankList.size(); i++){
            int rank = i + 1;
            answer += rank + "등 : " + rankList.get(i) + "(" + distribution[i] + "원)\n";
        }

        //팝업창 생성
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Team_pay.this);
        LayoutInflater inflater = getLayoutInflater();
        View basicView = inflater.inflate(R.layout.popup_basic, null);

        TextView popupText = basicView.findViewById(R.id.textView20);
        Button PositiveButton2 = basicView.findViewById(R.id.button01);
        //공유버튼 내용 start
        Button shareButton = basicView.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareScreen();
            }
        });
        //공유버튼 내용 end

        popupText.setText(answer);

        builder1.setView(basicView); // builder1에 setView를 설정합니다.
        AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

        PositiveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

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
