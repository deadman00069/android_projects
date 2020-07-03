package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    ConstraintLayout c1,c2;
    Button timer,ques,rit_nextbtn,wrong_text,f_sc_text,sc_disp;
    ImageView imtrue,imfalse;
    ImageView wrong,right;
    ImageView again,cancel;
    String pressed;
    int a,b,w;
    int po;
    int c;
    int score;
    Random r;
    CountDownTimer ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        c1=findViewById(R.id.cons_1);
        c2=findViewById(R.id.cons_2);
        rit_nextbtn=findViewById(R.id.next_btn);
        f_sc_text=findViewById(R.id.final_score_view);
        sc_disp=findViewById(R.id.score_display);
        wrong_text=findViewById(R.id.button_ques_r_w);
        timer=findViewById(R.id.timer_imageView);
        ques=findViewById(R.id.ques_imageView);
        imtrue=findViewById(R.id.true_imageView);
        imfalse=findViewById(R.id.false_imageView14);
        wrong=findViewById(R.id.wrong_imageView);
        right=findViewById(R.id.right_imageView);
        again=findViewById(R.id.wrong_again);
        cancel=findViewById(R.id.wrong_close);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sou);
        ranndom();
        ct=new CountDownTimer(10000,1000) {
            public void onTick(long milli){
                timer.setText("TIme left: "+milli/1000);
            }
            public void onFinish(){
                mp.start();
                Intent intent = new Intent(Main2Activity.this,score.class);
                intent.putExtra("Score is",""+score);
                startActivity(intent);
                finish();
            }
        }.start();
    }
    public void button_click(View view) {
        view.startAnimation(buttonClick);
        pressed=""+view.getTag().toString();
        Log.i("tag is",pressed);
        if(pressed.equals("0"))
        {
            if(po == 1 )
            {
                score++;
                ct.cancel();
                c1.setVisibility(View.INVISIBLE);
                c2.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.INVISIBLE);
                right.setVisibility(View.VISIBLE);
                rit_nextbtn.setVisibility(View.VISIBLE);

                wrong_text.setVisibility(View.VISIBLE);
                f_sc_text.setVisibility(View.INVISIBLE);
                sc_disp.setVisibility(View.INVISIBLE);
                again.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
            }
            else
            {
                ct.cancel();
                c1.setVisibility(View.INVISIBLE);
                c2.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.VISIBLE);
                right.setVisibility(View.INVISIBLE);
                rit_nextbtn.setVisibility(View.INVISIBLE);
                wrong_text.setVisibility(View.VISIBLE);
                f_sc_text.setVisibility(View.VISIBLE);
                sc_disp.setVisibility(View.VISIBLE);
                sc_disp.setText(""+score);
                again.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);





            }

        }
        if(pressed.equals("1"))
        {


            if(po == 0 )
            {
                score++;
                ct.cancel();
                c1.setVisibility(View.INVISIBLE);
                c2.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.INVISIBLE);
                right.setVisibility(View.VISIBLE);
                rit_nextbtn.setVisibility(View.VISIBLE);

                wrong_text.setVisibility(View.VISIBLE);
                f_sc_text.setVisibility(View.INVISIBLE);
                sc_disp.setVisibility(View.INVISIBLE);
                again.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
            }
            else
            {
                ct.cancel();
                c1.setVisibility(View.INVISIBLE);
                c2.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.VISIBLE);
                right.setVisibility(View.INVISIBLE);
                rit_nextbtn.setVisibility(View.INVISIBLE);
                wrong_text.setVisibility(View.VISIBLE);
                f_sc_text.setVisibility(View.VISIBLE);
                sc_disp.setVisibility(View.VISIBLE);
                sc_disp.setText(""+score);
                again.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
            }
        }
    }
    public  void ranndom()
    {
        r=new Random();
        a=r.nextInt(100);
        b=r.nextInt(100);
        w=r.nextInt(100);
        po=r.nextInt(2);
        c=a+b;
        if(po == 0)
        {
            ques.setText(""+a+"+"+b+"="+c);
        }
        if(po == 1)
        {
            ques.setText(""+a+"+"+b+"="+w);
        }
    }


    public void btn_next(View view) {
        ct.start();
        ranndom();
        c1.setVisibility(View.VISIBLE);
        c2.setVisibility(View.INVISIBLE);

    }

    public void again(View view) {
        score=0;
        c1.setVisibility(View.VISIBLE);
        c2.setVisibility(View.INVISIBLE);
        ct.start();
        ranndom();
    }

    public void close(View view) {
        Intent intent = new Intent(this,select_Quickbrain.class);
        startActivity(intent);
        finish();
    }
}
