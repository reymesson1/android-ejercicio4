package com.example.rmesson.myapplication

import android.support.constraint.solver.widgets.Rectangle

class GameObject : Rectangle {

    var imageIcon : String = ""
    var alive : Boolean
    var attribute : String = ""

    constructor(){

        x = 200
        y = 200
        width = 50
        height = 50
        alive = true
    }

    fun setPicture(p : String){

        setPicture(p)

    }

    fun draw(){


    }
}