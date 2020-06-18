package com.example.rubafikri.ass1sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor s;
    FadingTextView tv1 ;
    String[] dd;
    Intent ii;
    Sensor ss;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv1 = findViewById(R.id.fadingText);

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 0; i < sensorList.size(); i++) {
            System.out.println("Sensor is Found >>>: "+sensorList.get(i).getName() + sensorList.get(i).getMaximumRange());

        }
        sensorManager   = ( SensorManager ) getSystemService(this.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            s = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }

         if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            ss = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }


    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if (event.values[0] == s.getMaximumRange()) {
                ii = getIntent();
                dd = ii.getStringArrayListExtra("questions").toArray(new String[0]);
                tv1.setTexts(dd);
                tv1.setTimeout(1);
                tv1.resume();
            } else {
                dd = ii.getStringArrayListExtra("questions").toArray(new String[0]);
                tv1.pause();
            }

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (event.values[2] < 0) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,ss,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
