package com.lifeistech.android.myapplication;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.location.LocationManager;
import android.content.Intent;
import android.util.Log;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class walkActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    float[] result =new float[3];
    Walk walk;
    TextView textView;
    TextView debug;

    int oncreate=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        oncreate++;
        Log.d("onCreate",oncreate+"");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);
        walk=new Walk();
        textView=(TextView)findViewById(R.id.textView);
        debug=(TextView)findViewById(R.id.debug);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        findViewById(R.id.imageView).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a_walk));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            debug.setText("error1");
        } else {
            locationStart();
        }
    }

    private void locationStart(){
        Log.d("debug","locationStart()");

        // LocationManager インスタンス生成
        locationManager =
                (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("debug", "location manager Enabled");
        } else {
            // GPSを設定するように促す
            Intent settingsIntent =
                    new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
            Log.d("debug", "not gpsEnable, startActivity");

        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

            Log.d("debug", "checkSelfPermission false");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, this);

    }

    public void onProviderEnabled(String name){
    }
    public void onProviderDisabled(String name){
    }
    public void onLocationChanged(Location location){
        double x=location.getLatitude();
        double y=location.getLongitude();
        debug.setText("x"+x+" y"+y);
        location.distanceBetween(x,y,walk.x,walk.y,result);
        walk.change(x,y,result[0]);
        textView.setText("散歩距離："+String.format("%.1fkm",walk.kyori/1000));
    }
    public void onStatusChanged(String s,int i,Bundle b){

    }

    public void quit(View v){
        Intent intent=new Intent(this,MainActivity.class);
        Toast.makeText(this,String.format("%.1fkm",walk.kyori)+"散歩しました",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        Intent submit=new Intent(this,MainActivity.class);
        submit.putExtra("kyori",walk.kyori);
        finish();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("onDestroy","happened");
    }
}
