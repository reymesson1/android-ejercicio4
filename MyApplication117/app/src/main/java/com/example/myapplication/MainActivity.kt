package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity(), SensorEventListener {


    var moveOne : Int = 0
    var moveTwo : Int = 0
    var moveThree : Int = 0
    var moveFour : Int = 0
    var moveNave : Int = 0
    var trial : Int = 0
    var moveBullet : Int = 0
    var actualPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var SM = getSystemService(SENSOR_SERVICE) as SensorManager

        var mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL)

        var paramsNave = nave.getLayoutParams() as LinearLayout.LayoutParams

        paramsNave.setMargins(0,100,0, 0)

        nave.layoutParams = paramsNave

        galagaFrameLayout.setOnClickListener{

            moveBullet = 700
            actualPosition = moveNave

            var paramsBullet = bullet.layoutParams as FrameLayout.LayoutParams

            paramsBullet.setMargins(actualPosition,moveBullet,0,0)

            bullet.layoutParams = paramsBullet
        }

        doAsync {

            for(i in 1..2000){

                Thread.sleep(2000)

                activityUiThread {

                    var paramsOne = blocks1.getLayoutParams() as LinearLayout.LayoutParams
                    paramsOne.setMargins(0,moveOne,0, 0)
                    blocks1.layoutParams = paramsOne

                    var paramsTwo = blocks2.getLayoutParams() as LinearLayout.LayoutParams
                    paramsTwo.setMargins(0,moveTwo,0, 0)
                    blocks2.layoutParams = paramsTwo

                    var paramsThree = blocks3.getLayoutParams() as LinearLayout.LayoutParams
                    paramsThree.setMargins(0,moveThree,0, 0)
                    blocks3.layoutParams = paramsThree

                    var paramsFour = blocks4.getLayoutParams() as LinearLayout.LayoutParams
                    paramsFour.setMargins(0,moveFour,0, 0)
                    blocks4.layoutParams = paramsFour

                    moveOne +=100
                    moveTwo +=10
                    moveThree +=50
                    moveFour +=70

                    if(moveOne==900) {   moveOne = 0 }
                    if(moveTwo==900) {   moveTwo = 0 }
                    if(moveThree==900) {   moveThree = 0 }
                    if(moveFour==900) {   moveFour = 0 }


                }
            }
        }


        

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        var paramsNave = nave.getLayoutParams() as LinearLayout.LayoutParams

        if(event!!?.values[0]>2.8 && event.values[0]<9.0){

            paramsNave.setMargins(0,moveNave,0, 0)

            nave.layoutParams = paramsNave

            moveNave-=20

        }

        if(event!!?.values[0]>-9.8 && event.values[0]<-2.8){

            paramsNave.setMargins(0,moveNave,0, 0)

            nave.layoutParams = paramsNave

            moveNave += 20

        }

    }

}