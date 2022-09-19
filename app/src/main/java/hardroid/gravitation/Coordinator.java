package hardroid.gravitation;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class Coordinator extends AppCompatActivity {
    //
    private SensorManager sManager;
    private TextView TestSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator);


        TestSensor = (TextView) findViewById(R.id.test_view);
        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //SensorManager lets you access the device's sensors
        //getSystemService -- receive system services. System Services like
        //Gods for various apps. Most functions of apps must be rely for System Services.

        Sensor sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //This method is used to get the default sensor for a given type


        SensorEventListener sListener = new SensorEventListener(){
            //Registers a SensorEventListener for the given sensor at the given sampling frequency
            //SensorEventListener is used for receiving notifications from the SensorManager when there is new sensor data
            @Override
            public void onSensorChanged(SensorEvent event) {
                //Called when there is a new sensor event
                //event.values -- give some values
                //position 0 - from X
                //position 1 - from Y
                //position 2 - from Z
                float[]   CurrentVals = new float[3];
                for(int i=0; i<3;i++){
                    CurrentVals[i] = event.values[i];
                }

                TestSensor.setText("\tX: "+CurrentVals[0]+"\n\tY: "  + CurrentVals[1] + "\n\tZ: "+CurrentVals[2]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //called when the accuracy of the registered sensor has changed
            }
        };
        sManager.registerListener(sListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}

