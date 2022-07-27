package com.example.accelerometerextractor;
import java.net.*;
import java.io.*;
import java.io.DataOutputStream;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView Xfield,Yfield,Zfield;
    private   SensorManager mSensorManager;
    private   Sensor mAccelerometer;
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
private String address = "192.168.66.75";
    private int port = 12345;
    client client;
    Thread Thread1 = null;


    private SensorEventListener senesorEventListner = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

        Xfield.setText(String.valueOf(x));
            Yfield.setText(String.valueOf(y));
            Zfield.setText(String.valueOf(z));
            String data = String.valueOf(x)+"," + String.valueOf(y) +"," + String.valueOf(z);
            Thread1   = new Thread(new Thread1());
            Thread1.start();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Xfield = findViewById(R.id.textViewX);
        Yfield = findViewById(R.id.textViewY);
        Zfield = findViewById(R.id.textViewZ);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



    }

    protected void onResume() {
            super.onResume();
        mSensorManager.registerListener(senesorEventListner, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(senesorEventListner);
    }

    class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println("in");
            try {

                socket = new Socket(address, port);
                System.out.println("Connected");

                // takes input from terminal


                // sends output to the socket
                input  = new DataInputStream(System.in);
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("TestT");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}