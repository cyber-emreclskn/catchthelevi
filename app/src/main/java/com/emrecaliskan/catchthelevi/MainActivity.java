package com.emrecaliskan.catchthelevi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayoutTime;
    LinearLayout linearLayoutScore;
    ConstraintLayout mainTable;
    FrameLayout gameTable;
    ImageView leviAckerman;
    Random random;
    Runnable runnable;
    Handler handler;
    TextView textView1;
    TextView textView2;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutTime = findViewById(R.id.linearLayoutTime);
        linearLayoutScore = findViewById(R.id.linearLayoutScore);
        mainTable = findViewById(R.id.mainTable);
        gameTable = findViewById(R.id.GameTable);
        leviAckerman = findViewById(R.id.imageView);

        textView1 = findViewById(R.id.textTime);

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView1.setText("Time : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textView1.setText("Time Off");
                handler.removeCallbacks(runnable);
                leviAckerman.setVisibility(View.INVISIBLE);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("GAME OVER");
                alert.setMessage("Are you sure?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                alert.show();
            }
        }.start();

        gameTable();

    }

    public void gameTable(){

        handler = new Handler();

        int height = gameTable.getHeight();
        int width = gameTable.getWidth();

        random = new Random();

        int x = height;
        int y = width;

        runnable = new Runnable() {
            @Override
            public void run() {
                int imageX = random.nextInt(x+800)+100;
                int imageY = random.nextInt(y+700)+100;

                leviAckerman.setX((float) imageX);
                leviAckerman.setY((float) imageY);
                handler.postDelayed(runnable,300);
            }
        };

        handler.post(runnable);



    }

    public void imagePoint(View view){

        score++;
        textView2 = findViewById(R.id.scoreText);
        textView2.setText("Score : " + score);


    }


}