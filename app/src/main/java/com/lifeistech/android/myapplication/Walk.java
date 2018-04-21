package com.lifeistech.android.myapplication;

import android.util.Log;
/**
 * Created by tsu-chan on 2018/04/20.
 */

public class Walk {
    double kyori;
    double x,y;
    public Walk(){
        kyori=0;
        x=0;y=0;
    }

    public void change(double x,double y,double kyori){
        if(this.x==0&&this.y==0&&this.kyori==0){
            this.x=x;
            this.y=y;
            Log.d("debug","0");
        }else {
            this.x = x;
            this.y = y;
            this.kyori += kyori;
        }
    }
}

