package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class quick_Match extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    Button timer,ques;
    Button score_disp_onwrong,r_w,final_view;
    ImageView w_again,w_close;
    Random r;
    int a,b,c,o1,o2,o3;
    int po;
    Button op1,op2,op3,op4,next_qus;
    ImageView right,wrong;
    int score;
    CountDownTimer ct;
    ConstraintLayout c1,c2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick__match);
        c1=findViewById(R.id.cons_layout1);
        c2=findViewById(R.id.cons_layout2);
        score_disp_onwrong=findViewById(R.id.score_display);
        r_w=findViewById(R.id.button_ques_r_w);
        final_view=findViewById(R.id.final_score_view);
        w_again=findViewById(R.id.wrong_again);
        w_close=findViewById(R.id.wrong_close);
        timer=findViewById(R.id.imageView7);
        ques=findViewById(R.id.ques_button);
        op1=findViewById(R.id.iv_op1);
        op2=findViewById(R.id.iv_op2);
        op3=findViewById(R.id.iv_op3);
        op4=findViewById(R.id.iv_op4);
        right=findViewById(R.id.right_imageView);
        wrong=findViewById(R.id.wrong_imageView);
        next_qus=findViewById(R.id.next_btn);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sou);
        randdom();
        ct=new CountDownTimer(10000,1000) {
            public void onTick(long milli){
                timer.setText("TIme left: "+milli/1000);
            }
            public void onFinish()
            {
                mp.start();
                Intent intent = new Intent(quick_Match.this,score.class);
                intent.putExtra("Score is",""+score);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    public void button_clicked(View view) {

        view.startAnimation(buttonClick);
        String button_pressed=view.getTag().toString();
        if(button_pressed.equals(""+po))
        {
            score++;
            ct.cancel();
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.VISIBLE);
            right.setVisibility(View.VISIBLE);
            wrong.setVisibility(View.INVISIBLE);
            r_w.setVisibility(View.VISIBLE);
            r_w.setText("RIGHT ANSWER");
            next_qus.setVisibility(View.VISIBLE);
            final_view.setVisibility(View.INVISIBLE);
            score_disp_onwrong.setVisibility(View.INVISIBLE);
            w_again.setVisibility(View.INVISIBLE);
            w_close.setVisibility(View.INVISIBLE);
            randdom();
        }
        else
            {
                ct.cancel();
                c1.setVisibility(View.INVISIBLE);
                c2.setVisibility(View.VISIBLE);
                next_qus.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
                wrong.setVisibility(View.VISIBLE);
                r_w.setVisibility(View.VISIBLE);
                r_w.setText("WRONG ANSWER");
                final_view.setVisibility(View.VISIBLE);
                score_disp_onwrong.setVisibility(View.VISIBLE);
                score_disp_onwrong.setText(""+score);
                w_again.setVisibility(View.VISIBLE);
                w_close.setVisibility(View.VISIBLE);

                //textView.setVisibility(View.VISIBLE);
                //textView.setText("WRONG ANSWER");
          //  randdom();
        }
    }
    public void randdom()
    { r=new Random();
     a=r.nextInt(100);
     b=r.nextInt(100);
     o1=r.nextInt(100);
     o2=r.nextInt(100);
     o3=r.nextInt(100);
     po=r.nextInt(4);
     c=a+b;
     ques.setText("" + a + "+" + b);
     if(po == 0)
     {
        op1.setText(""+c);
        op2.setText(""+o2);
        op3.setText(""+o3);
        op4.setText(""+o1);
     }
     if(po == 1)
     {
         op1.setText(""+o1);
         op2.setText(""+c);
         op3.setText(""+o2);
         op4.setText(""+o3);
     }
     if(po == 2)
     {
         op1.setText(""+o1);
         op2.setText(""+o3);
         op3.setText(""+c);
         op4.setText(""+o2);
     }
     if(po == 3)
     {
         op1.setText(""+o1);
         op2.setText(""+o2);
         op3.setText(""+o3);
         op4.setText(""+c);
     }
    }

    public void btn_next(View view) {
       ct.start();
        c1.setVisibility(View.VISIBLE);
        c2.setVisibility(View.INVISIBLE);
    }

    public void close(View view) {
        Intent intent = new Intent(this,select_Quickbrain.class);
        startActivity(intent);
        finish();
    }

    public void again(View view) {
        score=0;
        ct.start();
        randdom();
        c2.setVisibility(View.INVISIBLE);
        c1.setVisibility(View.VISIBLE);

    }
}
