package com.lifeistech.android.sosuuapp_001;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.firstDayOfWeek;
import static android.R.attr.id;
import static android.R.drawable.ic_delete;

public class MainActivity extends AppCompatActivity {

    TextView number;
    TextView result;

    Button startButton;
    ImageButton maruB;
    ImageButton batuB;

    //問題数を指定
    int DefQuestionNum = 20;
    //発生させる乱数の最大値
    int DefMaxNum = 50;


    //カウント
    int point;
    int answercount;

    //表示する数字の管理
    int num;
    //ライフ数のカウント
    int lifenum;

    //乱数の生成
    Random numRandom = new Random();

    //ライフのイメージビューを配列
    ImageView[] life = new ImageView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ひもづけ
        life[0] = (ImageView)findViewById(R.id.life1);
        life[1] = (ImageView)findViewById(R.id.life2);
        life[2] = (ImageView)findViewById(R.id.life3);
        life[3] = (ImageView)findViewById(R.id.life4);
        life[4] = (ImageView)findViewById(R.id.life5);

        number = (TextView)findViewById(R.id.number);
        result = (TextView)findViewById(R.id.result);

        startButton = (Button)findViewById(R.id.startButton);
        maruB = (ImageButton) findViewById(R.id.maruB);
        batuB = (ImageButton) findViewById(R.id.batuB);

        //ライフ画像セット
        life[0].setImageResource(R.drawable.hart1);
        life[1].setImageResource(R.drawable.hart1);
        life[2].setImageResource(R.drawable.hart1);
        life[3].setImageResource(R.drawable.hart1);
        life[4].setImageResource(R.drawable.hart1);

        //ボタンの管理
        maruB.setEnabled(true);
        batuB.setEnabled(true);
        startButton.setText("START");
        startButton.setEnabled(false);

        //数値設定管理
        Intent intent = getIntent();
        DefMaxNum = intent.getIntExtra("max", 50);
        DefQuestionNum = intent.getIntExtra("num", 10);

        //ゲームスタート
        num = numRandom.nextInt(DefMaxNum) + 1;
        lifenum = 0;
        point = 0;
        answercount = 0;

        //Main Textview
        number.setText(String.valueOf(num));

        //Result Textview
        result.setText("残り " + (DefQuestionNum - answercount) + " 問");

    }

    public void start(View v){
        num = numRandom.nextInt(DefMaxNum) + 1;
        lifenum = 0;
        point = 0;
        answercount = 0;

        //Main Textview
        number.setText(String.valueOf(num));

        //Result Textview
        result.setText("残り " + (DefQuestionNum - answercount) + " 問");

        //ボタン復活
        maruB.setEnabled(true);
        batuB.setEnabled(true);
        startButton.setEnabled(false);
        startButton.setText("START");

        //ライフ画像セット
        life[0].setImageResource(R.drawable.hart1);
        life[1].setImageResource(R.drawable.hart1);
        life[2].setImageResource(R.drawable.hart1);
        life[3].setImageResource(R.drawable.hart1);
        life[4].setImageResource(R.drawable.hart1);

    }

    public void maru(View v){

        if(BuildConfig.DEBUG) {
            Log.e("TAG",String.valueOf(num));
        }

        boolean answer = true;

        if(num == 1) {
            answer = false;
        } else if(num == 2) {
            answer = true;
        } else {
            //2より大きい数字を判定
            for (int i = 2; i < num; i++){
                if (num % i == 0){
                    answer = false;
                    break;
                }
            }
        }



        if (answer){
            Toast.makeText(this,"正解",Toast.LENGTH_SHORT).show();
            point++;

            if(BuildConfig.DEBUG) {
                Log.d("maru","正解" + point);
            }

        } else {
            Toast.makeText(this,"不正解",Toast.LENGTH_SHORT).show();
            life[lifenum].setImageResource(R.drawable.hart2);
            lifenum++;

            if(BuildConfig.DEBUG) {
                Log.d("batu","不正解");
            }
        }


        answercount++;
        if (answercount == DefQuestionNum) {
            number.setText(point + "問正解");
            result.setText("Finish");
            maruB.setEnabled(false);
            batuB.setEnabled(false);
            startButton.setEnabled(true);
            startButton.setText("RESTART");
        } else if (lifenum == 5){
            number.setText("GAME OVER");
            result.setText("over");
            startButton.setText("RESTART");
            maruB.setEnabled(false);
            batuB.setEnabled(false);
            startButton.setEnabled(true);
            startButton.setText("RESTART");
        } else {
            num = numRandom.nextInt(DefMaxNum) + 1;
            number.setText(String.valueOf(num));
            result.setText("残り" + (DefQuestionNum - answercount) + "問　" + point + "問正解");
        }



    }

    public void batu(View v){

        if(BuildConfig.DEBUG) {
            Log.e("TAG",String.valueOf(num));
        }

        boolean answer = true;

        if(num == 1) {
            answer = true;
        } else if(num == 2) {
            answer = false;
        } else {
            //2より大きい数字を判定
            for (int i = 2; i < num; i++){
                if (num % i == 0 || num == 2){
                    answer = true;
                    break;
                } else {
                    answer = false;
                }
            }
        }


        if (answer){
            Toast.makeText(this,"正解",Toast.LENGTH_SHORT).show();
            point++;

            if(BuildConfig.DEBUG) {
                Log.d("maru","正解" + point);
            }

        } else {
            Toast.makeText(this,"不正解",Toast.LENGTH_SHORT).show();
            life[lifenum].setImageResource(R.drawable.hart2);
            lifenum++;

            if(BuildConfig.DEBUG) {
                Log.d("batu","不正解");
            }
        }


        answercount++;
        if (answercount == DefQuestionNum) {
            number.setText(point + "問正解");
            result.setText("Finish");
            maruB.setEnabled(false);
            batuB.setEnabled(false);
            startButton.setEnabled(true);
            startButton.setText("RESTART");
        } else if (lifenum == 5) {
            number.setText("GAME OVER");
            result.setText("over");
            startButton.setText("RESTART");
            maruB.setEnabled(false);
            batuB.setEnabled(false);
            startButton.setEnabled(true);
            startButton.setText("RESTART");
        } else {
            num = numRandom.nextInt(DefMaxNum) + 1;
            number.setText(String.valueOf(num));
            result.setText("残り" + (DefQuestionNum - answercount) + "問　" + point + "問正解");
        }

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
