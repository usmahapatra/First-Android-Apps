package com.example.ujjwalsmahapatra.myapplication

import java.sql.DriverManager.println

public open class FirstKotlin {
    public open fun make() {

        for (i in 1..10){
            println("$i")
        }

    }
}

 class B: FirstKotlin()
 {
     override fun make() {}
 }