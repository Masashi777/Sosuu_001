package com.lifeistech.android.sosuuapp_001;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import static android.R.attr.max;

public class OptionActivity extends AppCompatActivity {

    EditText eMaxNum;
    EditText eQuestionNum;

    int DefMaxNum;
    int DefQuestionNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        eMaxNum = (EditText)findViewById(R.id.maxNum);
        eQuestionNum = (EditText)findViewById(R.id.questioNum);

        Intent intent = getIntent();
        DefMaxNum = intent.getIntExtra("max", 50);
        DefQuestionNum = intent.getIntExtra("num", 10);

        eMaxNum.setHint(String.valueOf(DefMaxNum));
        eQuestionNum.setHint(String.valueOf(DefQuestionNum));


    }

    public void def(View v) {

        DefMaxNum = 50;
        DefQuestionNum = 10;

        eMaxNum.setHint(String.valueOf(DefMaxNum));
        eQuestionNum.setHint(String.valueOf(DefQuestionNum));

        eMaxNum.setText("");
        eQuestionNum.setText("");
    }

    public void back(View v) {

        if(eMaxNum.getText().toString().equals("")) {
            DefMaxNum = Integer.parseInt(eMaxNum.getHint().toString());
        } else {
            DefMaxNum = Integer.parseInt(eMaxNum.getText().toString());
        }

        if(eQuestionNum.getText().toString().equals("")) {
            DefQuestionNum = Integer.parseInt(eQuestionNum.getHint().toString());
        } else {
            DefQuestionNum = Integer.parseInt(eQuestionNum.getText().toString());
        }

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("max", DefMaxNum);
        intent.putExtra("num", DefQuestionNum);
        startActivity(intent);

        finish();
    }

    //戻るボタンの設定
    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("max", DefMaxNum);
        intent.putExtra("num", DefQuestionNum);
        startActivity(intent);

        finish();

    }

}
