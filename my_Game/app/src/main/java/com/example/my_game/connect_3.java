package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class connect_3 extends AppCompatActivity {
    RelativeLayout layout;
    GridLayout gridLayout;
    TextView winnerTextview;
    boolean gameisactive=true;
    //0 fr yellow 1 fr red
    int active_Player=0;
    //initial state is 0
    int game_State[]={2,2,2,2,2,2,2,2,2};
    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_3);
        layout =findViewById(R.id.linearlayout);
        gridLayout=findViewById(R.id.grid);
        winnerTextview=findViewById(R.id.txt);
    }
    public void imgclick(View view) {
        ImageView counter = (ImageView) view;
        int tapped_Counter = Integer.parseInt(counter.getTag().toString());
        if (game_State[tapped_Counter] == 2 && gameisactive)
        {
            game_State[tapped_Counter]=active_Player;
            counter.setTranslationY(-1000f);
            if (active_Player == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_Player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active_Player = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winning : winningPosition)
            {
                if(game_State[winning[0]] == game_State[winning[1]] && game_State[winning[1]] == game_State[winning[2]] && game_State[winning[0]] !=2)

                {
                    gameisactive=false;
                    String winner = "red player";
                    if (game_State[winning[0]] == 0)
                    {
                        winner="yellow player";
                        //code for gameover

                    }
                    winnerTextview.setText(winner+" won");

                    layout.setVisibility(View.VISIBLE);
                    gridLayout.setVisibility(View.INVISIBLE);
                }
                else
                {
                    boolean gameIsover=true;
                    for(int counterState : game_State)
                    {
                        if(counterState == 2)
                        {
                            gameIsover=false;
                        }
                    }
                    if(gameIsover)
                    {
                        winnerTextview.setText("Its a draw");
                        layout.setVisibility(View.VISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);
                    }

                }
            }
        }
    }

    public void play_overagain(View view)
    {
        gameisactive=true;
        layout.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        active_Player=0;
        for(int i=0;i<game_State.length;i++)
        {
            game_State[i]=2;
        }
        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public void btn_close(View view) {
        Intent intent= new Intent(this,select_page.class);
        startActivity(intent);
        finish();
    }
}
