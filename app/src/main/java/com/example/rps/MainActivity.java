package com.example.rps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView machineHeart1;
    private ImageView machineHeart2;
    private ImageView machineHeart3;
    private ImageView playerHeart1;
    private ImageView playerHeart2;
    private ImageView playerHeart3;
    private TextView drawCounter;
    private ImageView machineChoice;
    private ImageView playerChoice;
    private ImageView choiceRock;
    private ImageView choicePaper;
    private ImageView choiceScissors;
    private Random rnd = new Random();
    private int draws = 0;
    private char machineID;
    private int machineLives = 3;
    private char playerID;
    private int playerLives = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        choiceRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoice.setImageResource(R.drawable.rock);
                playerID = 'r';
                machineRoll();
            }
        });
        choicePaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoice.setImageResource(R.drawable.paper);
                playerID = 'p';
                machineRoll();
            }
        });
        choiceScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoice.setImageResource(R.drawable.scissors);
                playerID = 's';
                machineRoll();
            }
        });
    }
    public void machineRoll(){
        switch (rnd.nextInt(3)){
            case 0:
                machineChoice.setImageResource(R.drawable.rock);
                machineID = 'r';
                break;
            case 1:
                machineChoice.setImageResource(R.drawable.paper);
                machineID = 'p';
                break;
            case 2:
                machineChoice.setImageResource(R.drawable.scissors);
                machineID = 's';
                break;
        }
        if (playerID == machineID){
            draws++;
            drawCounter.setText("Döntetlenek száma: " + draws);
        } else if (playerID == 'r' && machineID == 'p'){
            playerLives--;
            playerLost(playerLives);
        } else if (playerID == 'p' && machineID == 's') {
            playerLives--;
            playerLost(playerLives);
        } else if (playerID == 's' && machineID == 'r') {
            playerLives--;
            playerLost(playerLives);
        }else {
            machineLives--;
            machineLost(machineLives);
        }
    }
    public void playerLost(int num){
        switch (num){
            case 2:
                playerHeart3.setImageResource(R.drawable.heart1);
                break;
            case 1:
                playerHeart2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                playerHeart1.setImageResource(R.drawable.heart1);
                gameEnd();
                break;
        }
    }
    public void machineLost(int num){
        switch (num){
            case 2:
                machineHeart1.setImageResource(R.drawable.heart1);
                break;
            case 1:
                machineHeart2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                machineHeart3.setImageResource(R.drawable.heart1);
                gameEnd();
                break;
        }
    }
    public void gameEnd(){
        String winner;
        if (playerLives == 0){
            winner = "Vesztettél! Szeretnél újból játszani?";
        }else {
            winner = "Nyertél! Szeretnél újból játszani?";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(winner)
                .setCancelable(false)
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playerLives = 3;
                        machineLives = 3;
                        playerHeart1.setImageResource(R.drawable.heart2);
                        playerHeart2.setImageResource(R.drawable.heart2);
                        playerHeart3.setImageResource(R.drawable.heart2);
                        machineHeart1.setImageResource(R.drawable.heart2);
                        machineHeart2.setImageResource(R.drawable.heart2);
                        machineHeart3.setImageResource(R.drawable.heart2);
                    }
                })
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
    }
    public void init(){
        machineHeart1 = findViewById(R.id.machineHeart1);
        machineHeart2 = findViewById(R.id.machineHeart2);
        machineHeart3 = findViewById(R.id.machineHeart3);
        playerHeart1 = findViewById(R.id.playerHeart1);
        playerHeart2 = findViewById(R.id.playerHeart2);
        playerHeart3 = findViewById(R.id.playerHeart3);

        drawCounter = findViewById(R.id.drawCounter);
        machineChoice = findViewById(R.id.machineChoice);
        playerChoice = findViewById(R.id.playerChoice);

        choiceRock = findViewById(R.id.choiceRock);
        choicePaper = findViewById(R.id.choicePaper);
        choiceScissors = findViewById(R.id.choiceScissors);
    }
}