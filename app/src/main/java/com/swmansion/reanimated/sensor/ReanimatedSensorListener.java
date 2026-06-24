package com.swmansion.reanimated.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import com.facebook.imagepipeline.common.RotationOptions;
import com.swmansion.reanimated.nativeProxy.SensorSetter;

/* JADX INFO: loaded from: classes2.dex */
public class ReanimatedSensorListener implements SensorEventListener {
    private final Display display;
    private final double interval;
    private SensorSetter setter;
    private double lastRead = System.currentTimeMillis();
    private float[] rotation = new float[9];
    private float[] orientation = new float[3];
    private float[] quaternion = new float[4];

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    ReanimatedSensorListener(SensorSetter sensorSetter, double d, Display display) {
        this.setter = sensorSetter;
        this.interval = d;
        this.display = display;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        double dCurrentTimeMillis = System.currentTimeMillis();
        if (dCurrentTimeMillis - this.lastRead < this.interval) {
            return;
        }
        int type = sensorEvent.sensor.getType();
        this.lastRead = dCurrentTimeMillis;
        int rotation = this.display.getRotation();
        int i = rotation != 1 ? rotation != 2 ? rotation != 3 ? 0 : RotationOptions.ROTATE_270 : RotationOptions.ROTATE_180 : 90;
        if (type == 2 || type == 4) {
            this.setter.sensorSetter(new float[]{sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]}, i);
            return;
        }
        switch (type) {
            case 9:
            case 10:
                this.setter.sensorSetter(new float[]{-sensorEvent.values[0], -sensorEvent.values[1], -sensorEvent.values[2]}, i);
                return;
            case 11:
                SensorManager.getQuaternionFromVector(this.quaternion, sensorEvent.values);
                SensorManager.getRotationMatrixFromVector(this.rotation, sensorEvent.values);
                SensorManager.getOrientation(this.rotation, this.orientation);
                float[] fArr = this.quaternion;
                float f = fArr[1];
                float f2 = fArr[3];
                float f3 = -fArr[2];
                float f4 = fArr[0];
                float[] fArr2 = this.orientation;
                this.setter.sensorSetter(new float[]{f, f2, f3, f4, -fArr2[0], -fArr2[1], fArr2[2]}, i);
                return;
            default:
                throw new IllegalArgumentException("[Reanimated] Unknown sensor type.");
        }
    }
}
