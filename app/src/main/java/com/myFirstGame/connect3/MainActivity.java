package com.myFirstGame.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0 = yellow; 1 = red; 2 = empty;
    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    boolean gameActive = true;

    int drawCounter = 0;

    public void dropin(View view) {

        ImageView counter = (ImageView) view;
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);

        //Fade Animation
//        counter.setAlpha(0.0f);
//        counter.setImageResource(R.drawable.yellow);
//        counter.animate().alpha(1).setDuration(500);

        //Scale animation
//        counter.setScaleX(0);
//        counter.setScaleY(0);
//        counter.setImageResource(R.drawable.yellow);
//        counter.animate().scaleX(1).scaleY(1).setDuration(200);

        //Drop Animation

        //Track which player tapped which image
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {
            drawCounter++;
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                textView.setText("RED's Turn");
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                textView.setText("YELLOW's Turn");
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(1800).setDuration(300);

            //Check if someone wins
//        for (int[] winningPosition : winningPositions) {
//        if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && ...)
//        }
            for (int i = 0; i < 8; i++) {
                if (gameState[winningPositions[i][0]] == gameState[winningPositions[i][1]] && gameState[winningPositions[i][1]] == gameState[winningPositions[i][2]] && gameState[winningPositions[i][0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "YELLOW";
                    } else {
                        winner = "RED";
                    }
                    textView.setText(winner + " Won");
                    gameActive = false;
                    button.setVisibility(View.VISIBLE);
                    button.animate().scaleY(1).scaleX(1).alpha(1).setDuration(300);
                }
            }

            //Check if draw
            if(drawCounter == 9 && gameActive){
                gameActive = false;
                textView.setText("It's a Draw");
                button.setVisibility(View.VISIBLE);
                button.animate().scaleY(1).scaleX(1).alpha(1).setDuration(300);
            }

        }

    }

    public void reMatch(View view) {
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        gameActive = true;
        activePlayer = 0;
        drawCounter = 0;

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("YELLOW's Turn");

//        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setScaleX(0);
        button.setScaleY(0);
        button.setAlpha(0);
        button.setVisibility(View.INVISIBLE);
    }
}
