package com.example.jetpackman;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;
/*import android.view.View;
import android.widget.Button;*/
import android.widget.TextView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;

    private GameView viewJetpack;
    private Spikes spikes;

    public int counter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewJetpack = (GameView)findViewById(R.id.viewJetpack);
        spikes = (Spikes)findViewById(R.id.spikes);
        textView = (TextView) findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "The device has no Gyroscope !", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        new CountDownTimer(Long.MAX_VALUE, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                textView.setText("SHOULD BE ENDLESS");
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(viewJetpack, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(viewJetpack);
    }
}