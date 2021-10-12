package org.techtown.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.myapplication.databinding.ActivityResultBinding

class Result_Activity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding

    private var Finish_Sound=MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Finish_Sound=MediaPlayer.create(this,R.raw.game_finish)
        Finish_Sound.start()

        val Score=intent.getIntExtra("Result",0)

        var Restart_Intent= Intent(this,GameStart_Activity::class.java)

        binding.ResultText.text=Score.toString()

        binding.RestartButton.setOnClickListener {
            startActivity(Restart_Intent)
            finish()

        }

    }


    override fun onRestart() {
        super.onRestart()

        Finish_Sound.start()
    }


    override fun onStop() {
        super.onStop()

        Finish_Sound.pause()
    }
}