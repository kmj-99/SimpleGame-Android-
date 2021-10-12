package org.techtown.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import java.util.Random
import android.location.LocationListener
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.myapplication.databinding.ActivityMainBinding
import java.lang.Exception
import java.sql.Time
import java.util.*
import java.util.function.ToLongBiFunction
import kotlin.concurrent.schedule
import kotlin.concurrent.thread
import kotlin.reflect.typeOf
import java.io.InputStream as InputStream1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var started1 = false
    private var started2 = false
    private var started3 = false
    private var started4=false
    private var started5=false
    private var started6=false
    private var started7=false
    private var Break=false

    private var Plus_Sound=MediaPlayer()
    private var Minus_Sound=MediaPlayer()
    private var Finish_Sound=MediaPlayer()



    private val Speed=17
    private var Timer=10

    private val Layout_SizeX = 1080
    private val Layout_SizeY = 2088

    private var Icon_Location_X = 0
    private var Icon_Location_Y = 0
    private var Icon_LocationList_X = ArrayList<Int>()
    private var Icon_LocationList_Y = ArrayList<Int>()


    private var Zet_Width=0
    private var Zet_Height=0

    private var moveX = 0f
    private var moveY = 0f

    private lateinit var CountText:TextView
    private lateinit var TimerText:TextView
    private lateinit var Result_Intent:Intent
    private lateinit var transaction:FragmentTransaction
    private lateinit var TestThread1:Unit
    private lateinit var TestThread2:Unit

    private var Count=0
    private var Test_Value=0

    private var ZetMove = Zet_Move_Start()
    private var Timer_Finish=Timer_Thread()



    @SuppressLint("ClickableViewAccessibility")
    //onTouch 메소드를 쓸 때 접근성을 위해 performClick 을 호출해야한다. 호출을 안하면 워닝이 생김 두번째 차선책으로는 위에 코드를 작성하면된다

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var TimerText=binding.TimerText

        Result_Intent= Intent(this,Result_Activity::class.java)


        Plus_Sound=MediaPlayer.create(this,R.raw.plus_sound)
        Minus_Sound=MediaPlayer.create(this,R.raw.minus_sound)
        Finish_Sound=MediaPlayer.create(this,R.raw.game_finish)


        CountText = binding.CountText
        ZetMove.start()



        when(intent.getIntExtra("Level",0)){
            1->{
                started6=true
                started5=true
                started3=true
                started4=true
            }

            2->{
                started6=true
                started5=true
            }
            else->{
                return
            }

        }


    }





    override fun onResume() {
        super.onResume()
        Root()
        Timer_Finish.priority=Thread.MAX_PRIORITY

        Timer_Finish.start()
    }





    inner class Timer_Thread:Thread(){
        override fun run() {
            super.run()
            try {
                while (Timer > 0) {
                    Thread.sleep(1000)

                    binding.TimerText.text = Timer.toString()
                    Timer -= 1

                }

                started1=false
                started2=false
                started3=false
                started4=false
                started5=false
                started6=false
                started7=false
                Result_Intent.putExtra("Result",Count)
                startActivity(Result_Intent)
                Thread.sleep(4000)
                finish()
            }catch (e:Exception){}
        }
    }







    @SuppressLint("ClickableViewAccessibility")
    inner class Zet_Move_Start : Thread() {
        override fun run() {
            super.run()
            try {

                binding.Zet.setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            moveX = v.x - event.rawX
                            moveY = v.y - event.rawY

                        }
                        MotionEvent.ACTION_MOVE -> {
                            when {
                                v.x <= 0 -> {
                                    v.x = 0f + 1
                                }
                                v.x + binding.Zet.width >= Layout_SizeX -> {
                                    v.x = Layout_SizeX.toFloat() - (binding.Zet.width + 1)
                                }
                                v.y <= 0 -> {
                                    v.y = 0f + 1
                                }
                                v.y + binding.Zet.height >= Layout_SizeY -> {
                                    v.y = Layout_SizeY.toFloat() - (binding.Zet.height + 1)
                                }
                                else -> {
                                    Icon_Location_X = v.x.toInt()
                                    Icon_Location_Y = v.y.toInt()
                                    Zet_Height = binding.Zet.height
                                    Zet_Width = binding.Zet.width

                                    for (i in Icon_Location_X..Icon_Location_X + Zet_Width) {
                                        Icon_LocationList_X.add(i)
                                    }
                                    //for (j in Icon_Location_Y.toInt()..Icon_Location_Y.toInt() + binding.imageView.height) {
                                    //    Icon_LocationList_Y.add(j)
//
                                    //}
                                    v.animate()
                                        .x(event.rawX + moveX)
                                        .y(event.rawY + moveY)
                                        .setDuration(0)
                                        .start()

                                }
                            }
                        }
                    }
                    true

                }
            }catch (e:InterruptedException){
                Thread.sleep(1000)
            }
        }

    }





        fun Root(){
        Thread{
            try{
                while(Timer>0){
                    Thread.sleep(100)
                    if(!started1 && Timer>0){
                        Thread.sleep(100)
                        Game_Start1()
                    }
                    if(!started2&&Timer>0){
                        Thread.sleep(100)
                        Game_Start2()
                    }
                    if(!started3&&Timer>0){
                        Thread.sleep(100)
                        Game_Start3()
                    }
                    if(!started4&&Timer>0){
                        Thread.sleep(100)
                        Game_Start4()
                    }
                    if(!started5&&Timer>0){
                        Thread.sleep(100)
                        Game_Start5()
                    }
                    if(!started6&&Timer>0){
                        Thread.sleep(100)
                        Game_Start6()
                    }
                    if(!started7&&Timer>0){
                        Thread.sleep(100)
                        Game_Start7()
                    }
                }
                Log.d("Root_Thread","1")






            }catch (e:InterruptedException){}
        }.start()
    }




        // 이미지가 내려오게 하는 스레드
        fun Game_Start1() {
            started1=true
            TestThread1=Thread {
                try {
                    var i = 1
                    var x1 = 0
                    var x2 = 0
                    var y1 = 0
                    var y2 = 0
                    var Icon1_location_X = 0f
                    var imageView1 = binding.imageView1
                    var x_width=imageView1.width
                    var y_height=imageView1.height
                        while (started1) {
                            Thread.sleep(200)

                            //200

                            if (i == 1) {
                                handler1_Image.postAtFrontOfQueue {
                                    imageView1.visibility = View.VISIBLE
                                    Thread.sleep(100)
                                }
                                Icon1_location_X =
                                    Random().nextInt(Layout_SizeX - imageView1.width).toFloat()
                                imageView1.x = Icon1_location_X
                            }

                            Thread.sleep(50)

                            imageView1.y = (i * Speed).toFloat()


                            x1 = imageView1.x.toInt()
                            y1 = imageView1.y.toInt()

                            x2 = x1 + x_width
                            y2 = y1 + y_height

                            if (y2 in Icon_Location_Y..Icon_Location_Y + Zet_Height) {
                                if (x1 in Icon_Location_X..Icon_Location_X + Zet_Width || x2 in Icon_Location_X..Icon_Location_X + Zet_Width) {
                                    handler1.postAtFrontOfQueue {
                                        Plus_Sound.start()
                                        imageView1.visibility = View.INVISIBLE
                                        started1 = false
                                    }
                                    Count += 1
                                    handler_Count.postAtFrontOfQueue {
                                        CountText.text = Count.toString()
                                    }
                                }
                            }
                            Thread.sleep(5)

                            if (y2 in Layout_SizeY..Layout_SizeY + 50) {
                                Log.d("LayoutSize", "21")
                                started1 = false
                            }

                            i += 1

                        }

                }catch (e:InterruptedException){}
            }.start()

        }

        fun Game_Start2() {
            started2=true
            TestThread2=Thread {
                try {
                    var i = 1
                    var x1 = 0
                    var x2 = 0
                    var y1 = 0
                    var y2 = 0
                    var Icon2_location_X = 0f
                    var x_width=imageView2.width
                    var y_height=imageView2.height
                    var imageView2 = binding.imageView2
                        while (started2 ) {
                            Thread.sleep(200)


                            if (i == 1) {
                                handler2_Image.postAtFrontOfQueue {
                                    imageView2.visibility = View.VISIBLE
                                    Thread.sleep(100)
                                }
                                Icon2_location_X =
                                    Random().nextInt(Layout_SizeX - imageView2.width).toFloat()
                                imageView2.x = Icon2_location_X
                            }
                            Thread.sleep(50)

                            imageView2.y = (i * Speed).toFloat()


                            x1 = imageView2.x.toInt()
                            y1 = imageView2.y.toInt()
                            x2 = x1 + x_width
                            y2 = y1 + y_height




                            if (y2 in Icon_Location_Y..Icon_Location_Y + Zet_Height) {
                                if (x1 in Icon_Location_X..Icon_Location_X + Zet_Width || x2 in Icon_Location_X..Icon_Location_X + Zet_Width) {
                                    handler2.postAtFrontOfQueue {
                                        Minus_Sound.start()
                                        imageView2.visibility = View.INVISIBLE
                                        started2 = false
                                    }
                                    Count -= 1
                                    handler_Count.postAtFrontOfQueue {
                                        CountText.text = Count.toString()
                                    }
                                }

                            }

                            Thread.sleep(5)

                            if (y2 in Layout_SizeY..Layout_SizeY + 50) {
                                Log.d("LayoutSize", "21")
                                started2 = false
                            }

                            i += 1
                        }
                }catch (e:InterruptedException){}
                }.start()

        }

        fun Game_Start3() {
            started3 = true



            Thread {
                try {
                    var i = 1
                    var x1 = 0
                    var x2 = 0
                    var y1 = 0
                    var y2 = 0
                    var Icon3_location_X = 0f
                    var x_width=imageView2.width
                    var y_height=imageView2.height
                    var imageView3 = binding.imageView3
                    while (started3) {
                        Thread.sleep(200)


                        if (i == 1) {
                            handler3_Image.postAtFrontOfQueue {
                                imageView3.visibility = View.VISIBLE
                                Thread.sleep(100)
                            }
                            Icon3_location_X =
                                Random().nextInt(Layout_SizeX - imageView3.width).toFloat()
                            imageView3.x = Icon3_location_X
                        }
                        Thread.sleep(50)

                        imageView3.y = (i * Speed).toFloat()


                        x1 = imageView3.x.toInt()
                        y1 = imageView3.y.toInt()
                        x2 = x1 + x_width
                        y2 = y1 + y_height



                        if (y2 in Icon_Location_Y..Icon_Location_Y +Zet_Height) {
                            if (x1 in Icon_Location_X..Icon_Location_X+Zet_Width || x2 in Icon_Location_X..Icon_Location_X+Zet_Width) {
                                handler3.postAtFrontOfQueue {
                                    Plus_Sound.start()
                                    imageView3.visibility = View.INVISIBLE
                                    started3 = false
                                }
                                Count+=1
                                handler_Count.postAtFrontOfQueue{
                                    CountText.text=Count.toString()
                                }
                            }
                        }

                        Thread.sleep(5)

                        if(y2 in Layout_SizeY..Layout_SizeY+50){
                            Log.d("LayoutSize","21")
                            started3=false
                        }

                        i += 1
                    }
                }catch (e:InterruptedException){}
            }.start()


        }

        fun Game_Start4() {
        started4=true

        Thread {
            try {
                var i = 1
                var x1 = 0
                var x2 = 0
                var y1 = 0
                var y2 = 0
                var Icon4_location_X = 0f
                var imageView4 = binding.imageView4
                var x_width=imageView4.width
                var y_height=imageView4.height
                while (started4) {
                    Thread.sleep(200)


                    if (i == 1) {
                        handler4_Image.postAtFrontOfQueue {
                            imageView4.visibility = View.VISIBLE
                            Thread.sleep(100)
                        }
                        Icon4_location_X =
                            Random().nextInt(Layout_SizeX - imageView4.width).toFloat()
                        imageView4.x = Icon4_location_X
                    }
                    Thread.sleep(50)

                    imageView4.y = (i * Speed).toFloat()


                    x1 = imageView4.x.toInt()
                    y1 = imageView4.y.toInt()

                    x2 = x1 + x_width
                    y2 = y1 + y_height



                    if (y2 in Icon_Location_Y..Icon_Location_Y+Zet_Height) {
                        if (x1 in Icon_Location_X..Icon_Location_X+Zet_Width || x2 in Icon_Location_X..Icon_Location_X+Zet_Width) {
                            handler4.postAtFrontOfQueue {
                                Minus_Sound.start()
                                imageView4.visibility = View.INVISIBLE
                                started4 = false
                            }

                            Count-=1
                            handler_Count.postAtFrontOfQueue{
                                CountText.text=Count.toString()
                            }
                        }
                    }

                    Thread.sleep(5)

                    if(y2 in Layout_SizeY..Layout_SizeY+50){
                        Log.d("LayoutSize","21")
                        started4=false
                    }
                    i += 1
                }
            }catch (e:InterruptedException){}
        }.start()

    }

        fun Game_Start5() {
        started5=true

        Thread {
            try {
                var i = 1
                var x1 = 0
                var x2 = 0
                var y1 = 0
                var y2 = 0
                var Icon5_location_X = 0f
                var imageView5 = binding.imageView5
                var x_width=imageView5.width
                var y_height=imageView5.height
                while (started5) {
                    Thread.sleep(200)


                    if (i == 1) {
                        handler5_Image.postAtFrontOfQueue {
                            imageView5.visibility = View.VISIBLE
                            Thread.sleep(100)
                        }
                        Icon5_location_X =
                            Random().nextInt(Layout_SizeX - imageView5.width).toFloat()
                        imageView5.x = Icon5_location_X
                    }
                    Thread.sleep(50)

                    imageView5.y = (i * Speed).toFloat()


                    x1 = imageView5.x.toInt()
                    y1 = imageView5.y.toInt()

                    x2 = x1 + x_width
                    y2 = y1 + y_height



                    if (y2 in Icon_Location_Y..Icon_Location_Y+Zet_Height) {
                        if (x1 in Icon_Location_X..Icon_Location_X+Zet_Width || x2 in Icon_Location_X..Icon_Location_X+Zet_Width) {
                            handler5.postAtFrontOfQueue {
                                Minus_Sound.start()
                                imageView5.visibility = View.INVISIBLE
                                started5 = false
                            }
                            Count-=1
                            handler_Count.postAtFrontOfQueue{
                                CountText.text=Count.toString()
                            }
                        }
                    }
                    Thread.sleep(5)


                    if(y2 in Layout_SizeY..Layout_SizeY+50){
                        Log.d("LayoutSize","21")
                        started5=false
                    }


                    i += 1
                }
            }catch (e:InterruptedException){}
        }.start()

    }

        fun Game_Start6() {
        started6=true

        Thread {
            try {
                var i = 1
                var x1 = 0
                var x2 = 0
                var y1 = 0
                var y2 = 0
                var Icon6_location_X = 0f
                var imageView6 = binding.imageView6
                var x_width=imageView6.width
                var y_height=imageView6.height
                while (started6) {
                    Thread.sleep(200)

                    if (i == 1) {
                        handler6_Image.postAtFrontOfQueue {
                            imageView6.visibility = View.VISIBLE
                            Thread.sleep(100)
                        }
                        Icon6_location_X =
                            Random().nextInt(Layout_SizeX - imageView6.width).toFloat()
                        imageView6.x = Icon6_location_X
                    }

                    Thread.sleep(50)

                    imageView6.y = (i * Speed).toFloat()


                    x1 = imageView6.x.toInt()
                    y1 = imageView6.y.toInt()

                    x2 = x1 + x_width
                    y2 = y1 + y_height



                    if (y2 in Icon_Location_Y..Icon_Location_Y+Zet_Height) {
                        if (x1 in Icon_Location_X..Icon_Location_X+Zet_Width || x2 in Icon_Location_X..Icon_Location_X+Zet_Width) {
                            handler6.postAtFrontOfQueue {
                                Plus_Sound.start()
                                imageView6.visibility = View.INVISIBLE
                                started6 = false
                            }
                            Count+=1
                            handler_Count.postAtFrontOfQueue{
                                CountText.text=Count.toString()
                            }
                        }
                    }

                    Thread.sleep(5)

                    if(y2 in Layout_SizeY..Layout_SizeY+50){
                        started6=false
                    }


                    i += 1
                }
            }catch (e:InterruptedException){}
        }.start()

    }

        fun Game_Start7() {
        started7=true

        Thread {
            try {
                var i = 1
                var x1 = 0
                var x2 = 0
                var y1 = 0
                var y2 = 0
                var Icon7_location_X = 0f
                var imageView7 = binding.imageView7
                var x_width=imageView7.width
                var y_height=imageView7.height
                while (started7) {
                    Thread.sleep(200)

                    if (i == 1) {
                        handler7_Image.postAtFrontOfQueue {
                            imageView7.visibility = View.VISIBLE
                            Thread.sleep(100)
                        }
                        Icon7_location_X =
                            Random().nextInt(Layout_SizeX - imageView7.width).toFloat()
                        imageView7.x = Icon7_location_X
                    }

                    Thread.sleep(50)

                    imageView7.y = (i * Speed).toFloat()


                    x1 = imageView7.x.toInt()
                    y1 = imageView7.y.toInt()

                    x2 = x1 + x_width
                    y2 = y1 + y_height



                    if (y2 in Icon_Location_Y..Icon_Location_Y+Zet_Height) {
                        if (x1 in Icon_Location_X..Icon_Location_X+Zet_Width || x2 in Icon_Location_X..Icon_Location_X+Zet_Width) {
                            handler7.postAtFrontOfQueue {
                                Minus_Sound.start()
                                imageView7.visibility = View.INVISIBLE
                                started7 = false
                            }
                            Count-=1
                            handler_Count.postAtFrontOfQueue{
                                CountText.text=Count.toString()
                            }
                        }
                    }

                    Thread.sleep(5)

                    if(y2 in Layout_SizeY..Layout_SizeY+50){
                        started7=false
                    }


                    i += 1
                }
            }catch (e:InterruptedException){}
        }.start()

    }






    var handler1: Handler = Handler(Looper.getMainLooper())
    var handler1_Image: Handler = Handler(Looper.getMainLooper())

    var handler2: Handler = Handler(Looper.getMainLooper())
    var handler2_Image:Handler=Handler(Looper.getMainLooper())

    var handler3: Handler = Handler(Looper.getMainLooper())
    var handler3_Image:Handler=Handler(Looper.getMainLooper())

    var handler4:Handler=Handler(Looper.getMainLooper())
    var handler4_Image:Handler=Handler(Looper.getMainLooper())

    var handler5:Handler=Handler(Looper.getMainLooper())
    var handler5_Image:Handler=Handler(Looper.getMainLooper())

    var handler6:Handler=Handler(Looper.getMainLooper())
    var handler6_Image:Handler=Handler(Looper.getMainLooper())

    var handler7:Handler=Handler(Looper.getMainLooper())
    var handler7_Image:Handler=Handler(Looper.getMainLooper())

    var handler_Count:Handler=Handler(Looper.getMainLooper())

    var handler_Timer:Handler=Handler(Looper.getMainLooper())



    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()



    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
