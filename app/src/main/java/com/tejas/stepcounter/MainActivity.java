package com.tejas.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
SensorManager mSensorManager;
TextView steps;
boolean run=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager= (android.hardware.SensorManager) getSystemService(Context.SENSOR_SERVICE);
        steps=findViewById(R.id.count);
    }
    protected void onResume() {
        super.onResume();
        run=true;
        Sensor cou=mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(cou==null) {
            Toast.makeText(this, "No Sensor Found", Toast.LENGTH_SHORT).show();
        }
       else{
            if(mSensorManager.registerListener(this, cou, SensorManager.SENSOR_DELAY_UI)){
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
      }
    }
    protected void onPause() {
        super.onPause();
        run = false;
    }
public void onSensorChanged(SensorEvent sensorEvent){

            steps.setText(String.valueOf(sensorEvent.values[0]));

    }
public void onAccuracyChanged(Sensor sensor, int i){}
}