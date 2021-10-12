package org.techtown.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.techtown.myapplication.databinding.ActivityGameStartBinding

open class GameStart_Activity : AppCompatActivity() {
    private lateinit var binding:ActivityGameStartBinding

    var mediaPlayer=MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGameStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mediaPlayer=MediaPlayer.create(this,R.raw.game_bgm)
        mediaPlayer.isLooping=true
        //mediaPlayer.start()


        var Game_Intent= Intent(this,GameLevel_Activity::class.java)

        binding.GameStartButton.setOnClickListener {
            startActivity(Game_Intent)
        }



    }


    override fun onRestart() {
        super.onRestart()
        Log.d("Certification","onRestart()")
        mediaPlayer.start()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}