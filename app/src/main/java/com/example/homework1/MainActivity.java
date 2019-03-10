package com.example.homework1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button rightButton;
    Button leftButton;
    Button startButton;
    TextView endgame;
    TextView highScoreText;
    int correctNumber;
    int level = 1;
    int trueAnswerCount = 0;
    int highScore = 0;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        highScore = sharedPreferences.getInt("highScore",0);
        rightButton = (Button) findViewById(R.id.rightBtn);
        leftButton = (Button) findViewById(R.id.leftBtn);
        startButton = (Button) findViewById(R.id.startBtn);
        endgame = (TextView) findViewById(R.id.endgame);
        highScoreText = (TextView) findViewById(R.id.highScore);
        rightButton.setOnClickListener(answerButtonClick);
        leftButton.setOnClickListener(answerButtonClick);
        startButton.setOnClickListener(startButtonClick);
        highScoreText.setText(Integer.toString(highScore));
        endgame.setVisibility(View.GONE);
    }

    public View.OnClickListener startButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rightButton.setEnabled(true);
            leftButton.setEnabled(true);
            v.setVisibility(View.GONE);
            endgame.setVisibility(View.GONE);
            initializeNumbers();
        }
    };
    public View.OnClickListener answerButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button tempBtn = (Button) v;
            if(correctNumber == Integer.parseInt(tempBtn.getText().toString())){
                trueAnswerCount += 1;
                if(trueAnswerCount % 5 == 0){
                    level += 1;
                }
                if (level == 11){
                    finishGame();
                }
                TextView pointText = (TextView) findViewById(R.id.point);
                TextView levelText = (TextView) findViewById(R.id.level);
                pointText.setText(Integer.toString(trueAnswerCount));
                levelText.setText(Integer.toString(level));
                initializeNumbers();
            }
            else{
                finishGame();
            }
        }
    };

    public void initializeNumbers() {
        Random rnd = new Random();
        int tempNum1 = 0;
        int tempNum2 = 0;
        switch (level) {
            case 1:
                tempNum1 = rnd.nextInt(11);
                tempNum2 = rnd.nextInt(11);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(11);
                }
                break;
            case 2:
                tempNum1 = rnd.nextInt(21);
                tempNum2 = rnd.nextInt(21);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(21);
                }
                break;
            case 3:
                tempNum1 = rnd.nextInt(31);
                tempNum2 = rnd.nextInt(31);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(31);
                }
                break;
            case 4:
                tempNum1 = rnd.nextInt(41);
                tempNum2 = rnd.nextInt(41);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(41);
                }
                break;
            case 5:
                tempNum1 = rnd.nextInt(41) * (rnd.nextBoolean() ? -1 : 1);
                tempNum2 = rnd.nextInt(41) * (rnd.nextBoolean() ? -1 : 1);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(41) * (rnd.nextBoolean() ? -1 : 1);
                }
                break;
            case 6:
                tempNum1 = rnd.nextInt(61) * (rnd.nextBoolean() ? -1 : 1);
                tempNum2 = rnd.nextInt(61) * (rnd.nextBoolean() ? -1 : 1);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(61) * (rnd.nextBoolean() ? -1 : 1);
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                tempNum1 = rnd.nextInt(101) * (rnd.nextBoolean() ? -1 : 1);
                tempNum2 = rnd.nextInt(101) * (rnd.nextBoolean() ? -1 : 1);
                while (tempNum1 == tempNum2) {
                    tempNum2 = rnd.nextInt(101) * (rnd.nextBoolean() ? -1 : 1);
                }
                break;
            default:
                break;
        }
        if (tempNum1 > tempNum2) {
            correctNumber = tempNum1;
        }else{
            correctNumber = tempNum2;
        }
        rightButton.setText(Integer.toString(tempNum1));
        leftButton.setText(Integer.toString(tempNum2));
    }

    public void finishGame(){
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        startButton.setVisibility(View.VISIBLE);
        endgame.setVisibility(View.VISIBLE);
        if(trueAnswerCount > highScore){
            sharedPreferences.edit().putInt("highScore",trueAnswerCount).apply();
            highScoreText.setText(Integer.toString(trueAnswerCount));
        }
        level = 1;
        trueAnswerCount = 0;

    }
}
