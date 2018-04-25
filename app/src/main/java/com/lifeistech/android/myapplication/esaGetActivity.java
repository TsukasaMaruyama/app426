package com.lifeistech.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Timer;
import java.util.TimerTask;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Random;

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
                int MP=TableLayout.LayoutParams.MATCH_PARENT;
                int WC=TableLayout.LayoutParams.WRAP_CONTENT;
                LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(MP,-1);
                params1.weight=1;
                LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(MP,-1);
                params2.weight=3;
                layout2.addView(esa,params1);
                layout2.addView(esaname,params2);
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

    Random random;
    int randomnum;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* random=new Random();
        randomnum=random.nextInt(6);

        Log.d("randomnum",""+randomnum);
*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esa_get);


        rotate=new ImageView(this);
        rotate.setImageResource(R.drawable.colorfull);
        rotate.setScaleX((float)1.7);
        rotate.setScaleY((float)1.7);
        glass=(ImageView) findViewById(R.id.glass);
        /*
        esa=new ImageView(this);
        int id = getResources().getIdentifier("esa"+randomnum, "drawable", getPackageName());/////////
        esa.setImageResource(id);///////

        //esa.setScaleX((float)1.5);
        //esa.setScaleY((float)1.5);
        esa.setPadding(0,0,0,100);
        esa.setScaleType(ImageView.ScaleType.FIT_XY);

        esaname=new TextView(this);
        esaname.setGravity(Gravity.CENTER);

        id=getResources().getIdentifier("name"+randomnum, "string", getPackageName());////
        esaname.setText(id);//////

        esaname.setTextSize(30);
        esaname.setTextColor(Color.BLACK);
        esaname.setBackgroundColor(Color.WHITE);*/

        layout=(FrameLayout) findViewById(R.id.layout);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout2.setGravity(Gravity.CENTER);

        rotate_anim= AnimationUtils.loadAnimation(this, R.anim.rotate);
        scale_anim2=AnimationUtils.loadAnimation(this,R.anim.scale_anim2);


    }

    public void getEsa(View v){
        Log.d("aaa","aaa");
        random=new Random();
        randomnum=random.nextInt(6/*R.integer.esanum*/);
        esa=new ImageView(this);
        int id = getResources().getIdentifier("esa"+randomnum, "drawable", getPackageName());/////////
        esa.setImageResource(id);///////

        //esa.setBackgroundColor(Color.BLUE);

        esaname=new TextView(this);
        esaname.setGravity(Gravity.CENTER);

        id=getResources().getIdentifier("name"+randomnum, "string", getPackageName());////
        esaname.setText(getResources().getString(id));//////これだとダメです

        esaname.setTextSize(30);
        esaname.setTextColor(Color.BLACK);
        //esaname.setBackgroundColor(Color.WHITE);

        SharedPreferences pre = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        int sum=pre.getInt("esa"+randomnum,0);
        sum++;
        editor.putInt("esa"+randomnum,sum);
        editor.commit();

        for(int i=0;i<7;i++){
            Toast.makeText(this,"esa"+i+":"+pre.getInt("esa"+i,0),Toast.LENGTH_SHORT).show();
        }
        startScalingXml();

    }

    private void startScalingXml(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        glass.startAnimation(animation);
        layout.removeView(glass);
        handler.post(run);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height = findViewById(R.id.main).getHeight();
    }

    public void quit(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
