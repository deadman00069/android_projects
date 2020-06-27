package com.example.xddd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.MotionEvent;


public class Main2Activity extends AppCompatActivity {

    float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                }else if(x1 > x2){
                    Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                }
                break;
        }
        return false;
    }
}
