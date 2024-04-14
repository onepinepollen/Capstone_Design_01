package com.example.more_close;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Fast_Pay.this);
        if(ex != 0){
            builder.setMessage("한명당 지불한 금액은 " + sum + " 이며,\n"+ex+"가 남았습니다. \n미니게임 화면으로 넘어갈까요?")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Fast_Pay.this, minigameMain.class);
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
    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Fast_Pay.this);
        builder.setMessage("인원수는 필수로 적어야하는 사항입니다!")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
