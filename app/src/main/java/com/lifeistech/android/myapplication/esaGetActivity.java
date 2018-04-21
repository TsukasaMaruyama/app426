package com.lifeistech.android.myapplication;

import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Timer;
import java.util.TimerTask;
import android.view.ViewGroup;

public class esaGetActivity extends AppCompatActivity {

    ImageView glass;
    ImageView esa;
    ImageView rotate;
    TextView esaname;
    FrameLayout layout;
    LinearLayout layout2;
    Animation rotate_anim;
    Animation scale_anim2;
    Handler handler=new Handler();
    Runnable run=new Runnable() {
        int count=0;
        @Override
        public void run() {
            if(count==2){
                layout.addView(rotate);
                rotate.startAnimation(rotate_anim);
                esa.startAnimation(scale_anim2);
                layout2.addView(esa,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout2.addView(esaname,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.removeView(layout2);
                layout.addView(layout2);
                esa.startAnimation(scale_anim2);

                handler.removeCallbacks(run);
                return;
            }else {
                count++;
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esa_get);
        rotate=new ImageView(this);
        rotate.setImageResource(R.drawable.colorfull);
        rotate.setScaleX((float)1.7);
        rotate.setScaleY((float)1.7);
        glass=(ImageView) findViewById(R.id.glass);
        esa=new ImageView(this);
        esa.setImageResource(R.drawable.esa);
        esa.setScaleX((float)1.5);
        esa.setScaleY((float)1.5);
        esa.setPadding(0,0,0,100);

        esaname=new TextView(this);
        esaname.setGravity(Gravity.CENTER);
        esaname.setText("ミドリムシ");
        esaname.setTextSize(30);
        esaname.setTextColor(Color.BLACK);
        esaname.setBackgroundColor(Color.WHITE);

        layout=(FrameLayout) findViewById(R.id.layout);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout2.setGravity(Gravity.CENTER);

        rotate_anim= AnimationUtils.loadAnimation(this, R.anim.rotate);
        scale_anim2=AnimationUtils.loadAnimation(this,R.anim.scale_anim2);
    }

    public void getEsa(View v){
        startScalingXml();
    }

    private void startScalingXml(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        glass.startAnimation(animation);
        layout.removeView(glass);
        //layout.addView(esa);
        handler.post(run);
    }

}
