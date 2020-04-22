package com.parskavirarvand.witchislonger;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;
import java.util.zip.Inflater;


public class GameFragment extends Fragment {


    private Button leftNumber, rightNumber, equal,newGame;
    TextView txtPoint, txtLevel;

    int levelInt = 0;
    private int leftNumInt, rightnumInt;
    private int pointInt = 0;
    private boolean gameInProgress = false;
    private final int GAME_LEVEL_COUNT = 10;

    private final int LEFT_BUTTON = 0;
    private final int RIGHT_BUTTON = 1;
    private final int EQUAL = 2;
    CountDownTimer countDownTimer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        findViews(view);
        setViews();



        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtLevel.setText(getString(R.string.remaining_time,(int)(millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                gameInProgress = false;
                txtLevel.setText("finished");
            }
        };
        countDownTimer.start();

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
                gameInProgress = true;
                pointInt =0;
                txtPoint.setText("Your Point is: 0");

            }
        });

        generateOneLevel();
        gameInProgress = true;
    }


    private void setViews() {
        leftNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAndContinuGame(LEFT_BUTTON);

            }
        });

        rightNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAndContinuGame(RIGHT_BUTTON);


            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAndContinuGame(EQUAL);

            }
        });



    }

    private void evaluateAndContinuGame(int whatPressed) {
        if (gameInProgress == false){
            return;
        }
        evaluate(whatPressed);

        txtPoint.setText("Your Point is: " + String.valueOf(pointInt));
        generateOneLevel();
    }

    private void evaluate(int whatPressed) {
        if (whatPressed == LEFT_BUTTON) {
            if (leftNumInt > rightnumInt) {
                pointInt++;
            }

        } else if (whatPressed == RIGHT_BUTTON) {
            if (leftNumInt < rightnumInt) {
                pointInt++;
            }

        } else if (whatPressed == EQUAL) {
            if (leftNumInt == rightnumInt) {
                pointInt++;
            }

        }

    }

    private void generateOneLevel() {
        if (gameInProgress == false) {

            return;
        }


        levelInt++;

        Random random = new Random();

        leftNumInt = random.nextInt(20);
        rightnumInt = random.nextInt(20);

        leftNumber.setText(String.valueOf(leftNumInt));
        rightNumber.setText(String.valueOf(rightnumInt));
    }

    private void findViews(View view) {
        leftNumber = view.findViewById(R.id.Left_num);
        rightNumber = view.findViewById(R.id.right_num);
        equal = view.findViewById(R.id.equal);
        txtPoint = view.findViewById(R.id.txt_point);
        txtLevel = view.findViewById(R.id.txt_level);
        newGame = view.findViewById(R.id.btn_new_game);
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
}
