package stany.tictactoe2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 *TicTacToe Game
 * Version 1.0
 * @author Stan Yanakiev
 */

public class MainActivity extends AppCompatActivity
{
    // 0 = blue, 1 = red
    int activePlayer = 0;

    // 2 = unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};


    public void dropIn(View view)
    {

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        if(gameState[tappedCounter] == 2)
        {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(400);
            LinearLayout layout = (LinearLayout) findViewById(R.id.WinnerLayout);

            for( int[] winningPosition: winningPositions)
            {
                if( gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2)
                {

                    String winner = "Red";
                    if(gameState[winningPosition[0]] == 0)
                    {
                        winner = "Blue";
                        layout.setBackgroundColor(Color.CYAN);
                    }
                    else
                    {
                        layout.setBackgroundColor(Color.RED);
                    }


                    System.out.println(gameState[winningPosition[0]]);

                    //Someone Has Won!!!

                    TextView winMessage = (TextView) findViewById(R.id.winMessage);
                    winMessage.setText(winner + " has won!");


                    layout.setVisibility(View.VISIBLE);
                    layout.animate().alpha(1).setDuration(200);
                }
                else
                {
                    boolean gameIsOver = true;
                    for(int counterState: gameState)
                    {
                        if(counterState == 2)
                        {
                            gameIsOver = false;
                        }
                    }

                    if(gameIsOver == true)
                    {
                        TextView winMessage = (TextView) findViewById(R.id.winMessage);
                        winMessage.setText("It's a Draw!");
                        layout.setBackgroundColor(Color.YELLOW);

                        layout.setVisibility(View.VISIBLE);
                        layout.animate().alpha(1).setDuration(200);
                    }
                }
            }
        }

    }

    public void playAgain(View view)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.WinnerLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;


        for(int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}