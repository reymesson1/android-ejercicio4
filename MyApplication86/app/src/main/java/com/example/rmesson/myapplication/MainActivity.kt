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

class MainActivity : AppCompatActivity(), SensorEventListener{

    var move : Int = 100
    var moveBlocks : Int = 100
    var timeLeftInMilliseconds: Long = 600000 //10min
    var timeRunning: Boolean = false
    var countDownTimer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var SM = getSystemService(SENSOR_SERVICE) as SensorManager

        var mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL)

        startStop()

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
                var params = nave.getLayoutParams() as LinearLayout.LayoutParams

                params.setMargins(0,moveBlocks,0, 0)

                moveBlocks+=10

                blocks1.layoutParams = params

                updateTimer()
            }
        }.start()
        timeRunning = true


    }

    fun updateTimer(){

        var minutes = timeLeftInMilliseconds / 60000
        var seconds = timeLeftInMilliseconds % 60000 / 1000

        var timeLeftText: String

        timeLeftText = "" + minutes
        timeLeftText += ":"
        if(seconds<10) timeLeftText += "0"
        timeLeftText += seconds

        textView3.setText(timeLeftText)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        textView.text = event!!?.values[0].toString()
        textView2.text = event!!?.values[1].toString()
//        textView3.text = event!!?.values[2].toString()


        var params = nave.getLayoutParams() as LinearLayout.LayoutParams

        if(event!!?.values[0]>2.8 && event.values[0]<9.0){


            params.setMargins(move,0,0, 0)

            nave.layoutParams = params

            move-=20

        }
        if(event!!?.values[0]>-9.8 && event.values[0]<-2.8){

            params.setMargins(move,0,0, 0)

            nave.layoutParams = params

            move+=20

        }


        }


}
