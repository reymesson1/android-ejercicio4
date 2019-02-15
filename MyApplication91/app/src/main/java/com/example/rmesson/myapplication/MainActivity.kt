package com.example.rmesson.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import android.util.Log
import android.widget.FrameLayout

class MainActivity : AppCompatActivity(), SensorEventListener{

    var move : Int = 200
    var moveBlocks : Int = 10
    var moveBullet : Int = 700
    var timeLeftInMilliseconds: Long = 60000 //10min
    var timeRunning: Boolean = false
    var countDownTimer : CountDownTimer? = null
    var moveAft : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var SM = getSystemService(SENSOR_SERVICE) as SensorManager

        var mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL)

        startStop()

        var galaga = Ship()

        galagaFrameLayout.setOnClickListener{

            moveBullet = 700
            moveAft = move

            var paramsBullet = bullet.layoutParams as FrameLayout.LayoutParams

//            paramsBullet.setMargins(move,moveBullet,0,0)
            paramsBullet.setMargins(moveAft,moveBullet,0,0)

            bullet.layoutParams = paramsBullet

        }

        Log.i("Classes", galaga.health.toString())

    }

    fun stopTimer(){

        countDownTimer?.cancel()
    }

    fun startStop(){

        startTimer()
    }

    fun startTimer(){

        countDownTimer = object: CountDownTimer(timeLeftInMilliseconds,100){
            override fun onFinish() {

            }

            override fun onTick(l: Long) {

                timeLeftInMilliseconds = l
                var params = blocks1.getLayoutParams() as LinearLayout.LayoutParams
                var paramsBullet = bullet.layoutParams as FrameLayout.LayoutParams


                params.setMargins(90,moveBlocks,0, 0)
                paramsBullet.setMargins(moveAft,moveBullet,0,0)

                var rnds = (50..99).random()

                moveBlocks+=10
                moveBullet-=10

                if(moveBlocks.equals(moveBullet)){

                    countDownTimer?.cancel()

                }

                blocks1.layoutParams = params
                bullet.layoutParams = paramsBullet


                updateTimer()

            }
        }.start()
        timeRunning = true

    }

    fun updateTimer(){

        var minutes = timeLeftInMilliseconds / 6000
        var seconds = timeLeftInMilliseconds % 60000 / 1000

        var timeLeftText: String

        timeLeftText = "" + minutes
        timeLeftText += ":"
        if(seconds<10) timeLeftText += "0"
        timeLeftText += seconds

//        textView3.setText(seconds.toString())

        if(seconds.toString()=="50"){

            stopTimer()

            var params = blocks1.getLayoutParams() as LinearLayout.LayoutParams

            moveBlocks = 0

            params.setMargins(moveAft,moveBlocks,0, 0)

            blocks1.layoutParams = params

            timeLeftInMilliseconds = 60000

            startStop()

        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

//        textView.text = event!!?.values[0].toString()
//        textView2.text = event!!?.values[1].toString()
//        textView3.text = event!!?.values[2].toString()

        var params = nave2.layoutParams!! as FrameLayout.LayoutParams

            if(event!!?.values[0]>2.8 && event.values[0]<9.0){

                params.setMargins(move,700,0, 0)

                nave2.layoutParams = params

                move-=20

            }
            if(event!!?.values[0]>-9.8 && event.values[0]<-2.8){

                params.setMargins(move,700,0, 0)

                nave2.layoutParams = params

                move+=20

            }

        }
}