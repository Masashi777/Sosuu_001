package com.lifeistech.android.sosuuapp_001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    int DefMaxNum = 50;
    int DefQuestionNum = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        DefMaxNum = intent.getIntExtra("max", 50);
        DefQuestionNum = intent.getIntExtra("num", 10
        );
    }

    public void start(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("max", DefMaxNum);
        intent.putExtra("num", DefQuestionNum);
        startActivity(intent);

        finish();
    }

    public void option(View v) {
        Intent intent = new Intent(this, OptionActivity.class);
        intent.putExtra("max", DefMaxNum);
        intent.putExtra("num", DefQuestionNum);
        startActivity(intent);

        finish();
    }
}
