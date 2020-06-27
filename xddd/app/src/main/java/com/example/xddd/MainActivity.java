package com.example.xddd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    SharedPreferences preps;
    boolean firststart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preps = getSharedPreferences("preps", MODE_PRIVATE);
        firststart = preps.getBoolean("firststart", false);
        if (firststart)
        {
            Intent intent = new Intent(this,welcome.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 > x2) {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    SharedPreferences preps=getSharedPreferences("preps",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preps.edit();
                    editor.putBoolean("firststart",true);
                    editor.apply();
                }
                break;
        }
        return false;
    }

}
