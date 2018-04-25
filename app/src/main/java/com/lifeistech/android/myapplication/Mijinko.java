package com.lifeistech.android.myapplication;

/**
 * Created by tsu-chan on 2018/04/19.
 */

public class Mijinko {
    double length;
    double muscle;
    int fatigue;

    int protain;
    int sleep;
    int energy;
    int calcium;
    int fat;

    Mijinko(double length,double muscle,int protain,int sleep,int energy,int calcium,int fat){
        this.length=length;
        this.muscle=muscle;
        fatigue=0;

        this.protain=protain;
        this.sleep=sleep;
        this.energy=energy;
        this.calcium=calcium;
        this.fat=fat;

    }

    void meal(int protain,int energy){
        this.protain=protain;
        this.energy=energy;
    }

    void walk(double kyori){
        double score=length*muscle;
        if(kyori>3*score){
            fatigue+=3;
        }else if(kyori>2*score){
            fatigue+=2;
        }else if(kyori>score) {
            fatigue+=1;
        }
    }

    void sleep(){
        double score=fatigue*100;
        double p=0;//マッチョ度変化量
        if(protain>score){//score=筋肉増加量
            protain-=score;//タンパク質消費
            score/=100;
            muscle+=score/(muscle*length);
        }
    }
}
