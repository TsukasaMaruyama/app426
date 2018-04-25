package com.lifeistech.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Toast;

public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SharedPreferences pre = getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        if(pre.getBoolean("fast_start", true)){
            editor.putBoolean("fast_start",false);
            Toast.makeText(this,"初回ログインです" ,Toast.LENGTH_SHORT).show();
            editor.putInt("esa0",0);
            editor.putInt("esa1",0);
            editor.putInt("esa2",0);
            editor.putInt("esa3",0);
            editor.putInt("esa4",0);
            editor.putInt("esa5",0);
            editor.putInt("esa6",0);

            editor.putFloat("length",1.0f);
            editor.putFloat("muscle",0.1f);
            editor.putInt("protain",0);
            editor.putInt("sleep",0);
            editor.putInt("energy",0);
            editor.putInt("calcium",0);
            editor.putInt("fat",0);
            editor.putFloat("walk",0);

            editor.commit();
        }
    }

    public void start(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
