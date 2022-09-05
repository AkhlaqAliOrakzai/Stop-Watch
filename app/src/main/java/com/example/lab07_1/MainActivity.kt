package com.example.lab07_1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.String

class MainActivity : AppCompatActivity() {
    var textView: TextView ? = null
    var t: Thread? = null
    var second = 0
    var minute = 0
    var hour = 0

    var text = ""

    var buttonStart: Button? = null

    var isSleep = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun buttonStart(view: View){
        t = object : Thread() {
            override fun run() {
                while (!isSleep) {
                    try {
                        sleep(1000) //1000ms = 1 sec
                        runOnUiThread {
                            if(second == 60){
                                second = 0
                                minute++
                                if(minute == 60){
                                    minute = 0
                                    hour++
                                }
                            }
                            second++
                            var hourM: kotlin.String = hour.toString()
                            var minuteM: kotlin.String = minute.toString()
                            var secondM: kotlin.String = second.toString()
                            if(hour<10){
                                hourM = "0"+hourM
                            }
                            if(minute<10){
                                minuteM = "0"+minute
                            }
                            if(second<10){
                                secondM = "0"+second
                            }

                            text = "$hourM:$minuteM:$secondM"
                            textView = findViewById(R.id.textView)
                            textView?.setText(String.valueOf(text))
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
                isSleep = false;
            }

        }

        (t as Thread).start()

        buttonStart = findViewById<Button>(R.id.button)
        buttonStart?.isEnabled = false


    } // start
    fun buttonStop(view:View){

        isSleep = true;

    }
    fun buttonReset(view:View){
        t = null
        buttonStart?.isEnabled = true
        isSleep = false
        second = 0
        minute = 0
        hour = 0
        text = "00:00:00"
        textView?.text = text



    }
}