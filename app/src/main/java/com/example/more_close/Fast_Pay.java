package com.example.more_close;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Fast_Pay extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fast_pay);
        Intent intent = getIntent();

        EditText pay1 = findViewById(R.id.editTextNumber);
        EditText pay2 = findViewById(R.id.editTextNumber2);
        EditText pay3 = findViewById(R.id.editTextNumber3);
        EditText pay4 = findViewById(R.id.editTextNumber4);
        EditText people = findViewById(R.id.editTextNumber5);

        Button btn1 = findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pay1 = pay1.getText().toString();
                String Pay2 = pay2.getText().toString();
                String Pay3 = pay3.getText().toString();
                String Pay4 = pay4.getText().toString();
                String PeoPle = people.getText().toString();

                String[] pays = {Pay1, Pay2, Pay3, Pay4};
                int sum = 0;

                for(int i = 0; i < pays.length; i++) {
                    if(pays[i].isEmpty()){
                    }
                    else{
                        sum += Integer.parseInt(pays[i]);
                    }
                }
                if(PeoPle.isEmpty()){
                    showErrorDialog();
                }else{
                    sum = sum / Integer.parseInt(PeoPle);
                    int ex = sum % Integer.parseInt(PeoPle);

                    showResultDialog(sum,ex);
                }
            }
        });
    }

    private void showResultDialog(int sum,int ex) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Fast_Pay.this);
        if(ex != 0){
            LayoutInflater inflater = getLayoutInflater();
            View normalView = inflater.inflate(R.layout.popup_nomal, null);

            TextView popupText = normalView.findViewById(R.id.textView18);
            Button PositiveButton2 = normalView.findViewById(R.id.button8);
            Button NegativeButton2 = normalView.findViewById(R.id.button7);
            popupText.setText("한명당 지불한 금액은 " + sum + " 이며,\n"+ex+"가 남았습니다. \n미니게임 화면으로 넘어갈까요?");

            builder1.setView(normalView); // builder1에 setView를 설정합니다.
            AlertDialog dialog = builder1.create(); // dialog 객체를 생성합니다.

            PositiveButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Fast_Pay.this, minigameMain.class);
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
        }else {
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
    private void showErrorDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View basicView = inflater.inflate(R.layout.popup_basic, null);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Fast_Pay.this);

        TextView popupText = basicView.findViewById(R.id.textView20);
        Button PositiveButton2 = basicView.findViewById(R.id.button01);
        popupText.setText("인원수는 필수로 적어야하는 사항입니다!");

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
