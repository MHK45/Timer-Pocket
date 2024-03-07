package com.login.myawsome.timerpocket

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.login.myawsome.timerpocket.ui.theme.TimerPocketTheme

class MainActivity : ComponentActivity() {
    var Start_In_Miles:Long = 5*60*1000
    var remain_Time = Start_In_Miles
    var timers :CountDownTimer?= null
    var isTimerunnig = false


    lateinit var  title :TextView
    lateinit var  timer:TextView
    lateinit var  startbtn:Button
    lateinit var  resetbtn:TextView
    lateinit var  pb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_pocket)

        title = findViewById(R.id.title_id)
        timer = findViewById(R.id.timer_id)
        startbtn = findViewById(R.id.start_btn)
        resetbtn = findViewById(R.id.resetText)
        pb = findViewById(R.id.progressBar)


        //Reset button !!!!
        resetbtn.setOnClickListener {
              resetTimer()
             isTimerunnig =false
        }

        //Start button !!!!!!

        startbtn.setOnClickListener{

            if (!isTimerunnig){

                title.setText(resources.getText(R.string.keep_going))

                timers = object :CountDownTimer(Start_In_Miles , 1000){
                    override fun onTick(timeleft: Long) {
                        remain_Time = timeleft
                        UpdateTimerText()
                        pb.progress = remain_Time.toDouble().div(Start_In_Miles.toDouble()).times(100).toInt()
                    }
                    override fun onFinish() {
                        Toast.makeText(this@MainActivity,"Finshed !",Toast.LENGTH_SHORT).show()
                        isTimerunnig =false
                    }

                }.start()
                isTimerunnig =true
            }


        }


    }

    private fun resetTimer(){
          timers?.cancel()
          remain_Time=Start_In_Miles
          UpdateTimerText()
         isTimerunnig=false
         pb.progress=100
         title.setText(resources.getText(R.string.take_promodoro))

    }



    private fun UpdateTimerText(){
           val minute = remain_Time.div(1000).div(60)
           val second = remain_Time.div(1000)%60
           val foramted = String.format("%02d:%02d",minute,second)
          timer.setText(foramted)
    }








}