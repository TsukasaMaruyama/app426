package com.lifeistech.android.myapplication;

import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.ViewGroup;
import java.lang.Runnable;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import java.lang.Math;
import android.util.Log;
import android.content.Intent;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    TextView muscleView;
    TextView lengthView;

    BlankFragment fragment;
    ImageView imageView;
    ImageView esa;
    int e=0;
    RelativeLayout main;
    Handler handler;
    Mijinko mijinko;
    LinearLayout.LayoutParams layout;

    Runnable  runnable= new Runnable() {
        @Override
        public void run() {
            if (width != 0){
                width -= 100;
                height -= 100;
                esa.setLayoutParams(new ViewGroup.LayoutParams(width, height));
                main.removeView(esa);
                main.addView(esa);
                System.out.println(width);
                handler.postDelayed(runnable, 1000);
            }else{
                width=300;
                height=300;
                main.removeView(esa);
            }
        }
    };
    int width=300;
    int height=300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


        SharedPreferences pre=getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();

        handler=new Handler();
        mijinko=new Mijinko(pre.getFloat("length",1.0f),pre.getFloat("muscle",0.1f),pre.getInt("protain",0),pre.getInt("sleep",0),pre.getInt("energy",0),pre.getInt("calcium",0),pre.getInt("fat",0));
        layout=new LinearLayout.LayoutParams((int)(mijinko.length*100),(int)(mijinko.length*100));
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.daphnia);
        imageView.setLayoutParams(layout);

        findViewById(R.id.imageView).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a));
        handler=new Handler();

        muscleView=(TextView)findViewById(R.id.muscleView);
        lengthView=(TextView)findViewById(R.id.lengthView);
        Log.d("checknull " ,""+ lengthView);
        Log.d("checknull",""+R.id.lengthView);
        muscleView.setText("マッチョ度："+String.valueOf(String.format("%.2f",mijinko.muscle*100))+"%");
        lengthView.setText("身長："+String.valueOf(String.format("%.4f",mijinko.length)+"nm"));

        sycledebug("onCreate");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent get=getIntent();
        mijinko.walk(get.getIntExtra("kyori",0));
        Log.d("onResume","happened"+mijinko.fatigue);
        sycledebug("onResume");
    }
    public void esayari(View v){
        if(e!=-100) {
            esa = new ImageView(this);
            esa.setImageResource(R.drawable.esa);
            esa.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            main = (RelativeLayout) findViewById(R.id.main);
            main.addView(esa);
            e--;
            handler.postDelayed(runnable, 1000);
        }else{
            mijinko.meal(100,100);
        }
    }

    public void walk(View v){
        Intent intent=new Intent(this,walkActivity.class);
        startActivity(intent);
    }

    public void getEsa(View v){
        Intent intent=new Intent(this,esaGetActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart(){
        super.onStart();
        sycledebug("onStart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        sycledebug("onDestroy");
    }

    @Override
    protected void onPause(){
        super.onPause();
        sycledebug("onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        sycledebug("onStop");
    }

    private void sycledebug(String status){
        Toast.makeText(this,"Mainactivity:"+status,Toast.LENGTH_SHORT).show();
        Log.d("Mainactivity",status);
    }

}
