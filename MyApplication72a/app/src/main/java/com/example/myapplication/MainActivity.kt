package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    var moveOne : Int = 0
    var moveTwo : Int = 0
    var moveThree : Int = 0
    var moveFour : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var params = nave.layoutParams!! as FrameLayout.LayoutParams
        params.setMargins(100,1000,0, 0)
        nave.layoutParams = params

        doAsync{

            for(i in 1..200) {
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

                    moveOne+=100
                    moveTwo+=10
                    moveThree+=50
                    moveFour+=200

                    if(moveOne==1200) {   moveOne = 0 }
                    if(moveTwo==1200) {   moveTwo = 0 }
                    if(moveThree==1200) {   moveThree = 0 }
                    if(moveFour==1200) {   moveFour = 0 }

                }
            }
        }
    }
}