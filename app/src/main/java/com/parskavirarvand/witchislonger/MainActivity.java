package com.parskavirarvand.witchislonger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame = findViewById(R.id.btn_start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragment gameFragment = new GameFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, gameFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}
