package com.example.myapplication

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class GameActivity : AppCompatActivity(), SensorEventListener {

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
        setContentView(R.layout.activity_game)

        var SM = getSystemService(SENSOR_SERVICE) as SensorManager

        var mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        SM.registerListener(this,mySensor, SensorManager.SENSOR_DELAY_NORMAL)

        var params = nave.layoutParams!! as FrameLayout.LayoutParams
        params.setMargins(100,1000,0, 0)
        nave.layoutParams = params

        galagaFrameLayout.setOnClickListener{

            moveBullet = 700
            actualPosition = moveNave

            var paramsBullet = bullet.layoutParams as FrameLayout.LayoutParams

            paramsBullet.setMargins(actualPosition,moveBullet,0,0)

            bullet.layoutParams = paramsBullet

        }

        doAsync{

            for(i in 1..2000) {
                Thread.sleep(1000)
                activityUiThread {

                    var paramsOne = asteroid_one.getLayoutParams() as LinearLayout.LayoutParams
                    paramsOne.setMargins(0,moveOne,0, 0)
                    asteroid_one.layoutParams = paramsOne

                    var paramsTwo = asteroid_two.getLayoutParams() as LinearLayout.LayoutParams
                    paramsTwo.setMargins(0,moveTwo,0, 0)
                    asteroid_two.layoutParams = paramsTwo

                    var paramsThree = asteroid_three.getLayoutParams() as LinearLayout.LayoutParams
                    paramsThree.setMargins(0,moveThree,0, 0)
                    asteroid_three.layoutParams = paramsThree

                    var paramsFour = asteroid_four.getLayoutParams() as LinearLayout.LayoutParams
                    paramsFour.setMargins(0,moveFour,0, 0)
                    asteroid_four.layoutParams = paramsFour

                    var paramsBullet = bullet.layoutParams as FrameLayout.LayoutParams
                    paramsBullet.setMargins(actualPosition,moveBullet,0,0)
                    moveBullet-=100

                    bullet.layoutParams = paramsBullet

                    if(moveBullet.equals(moveOne)){

                    }

                    moveOne+=100
                    moveTwo+=10
                    moveThree+=50
                    moveFour+=200

                    if(moveOne==1200) {   moveOne = 0 }
                    if(moveTwo==1200) {   moveTwo = 0 }
                    if(moveThree==1200) {   moveThree = 0 }
                    if(moveFour==1200) {   moveFour = 0 }

                    textViewData2.setText("moveTwo " + moveTwo.toString())

                    if(moveOne.equals(1100) && (moveNave>=0 && moveNave<=120) ){

                        var intent = Intent(this@GameActivity,SecondActivity::class.java)
                        startActivity(intent)
                        finish()

                    }

                    if(moveTwo.equals(1100) && (moveNave>=170 && moveNave<=320) ){

                        var intent = Intent(this@GameActivity, SecondActivity::class.java)
                        startActivity(intent)
                        finish()

                    }



                }

            }
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        var params = nave.layoutParams!! as FrameLayout.LayoutParams

        if(event!!?.values[0]>2.8 && event.values[0]<9.0){

            params.setMargins(moveNave,1000,0, 0)

            nave.layoutParams = params

            moveNave-=20

        }

        if(event!!?.values[0]>-9.8 && event.values[0]<-2.8){

            params.setMargins(moveNave,1000,0, 0)

            nave.layoutParams = params

            moveNave += 20

        }

        textViewData.setText("moveNave " + moveNave.toString())

    }
}
