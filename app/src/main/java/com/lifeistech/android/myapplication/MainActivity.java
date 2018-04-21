package com.lifeistech.android.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.app.ActivityManager;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView muscleView;
    TextView lengthView;

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

        handler=new Handler();
        mijinko=new Mijinko(1.000,0.1);
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

        /*
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        // 起動中のアプリ情報を取得
        List<RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
        PackageManager packageManager = getPackageManager();
        if(runningApp != null) {
            for(RunningAppProcessInfo app : runningApp) {
                try {
                    // アプリ名をリストに追加
                    ApplicationInfo appInfo = packageManager.getApplicationInfo(app.processName, 0);
                    Log.d("appinfo",(String) packageManager.getApplicationLabel(appInfo));
                    Toast.makeText(this,(String) packageManager.getApplicationLabel(appInfo),Toast.LENGTH_SHORT).show();
                }
                catch(NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent get=getIntent();
        mijinko.walk(get.getIntExtra("kyori",0));
       //mijinko.walk(10000);
        Log.d("onResume","happened"+mijinko.fatigue);
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
}
